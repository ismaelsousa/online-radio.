

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.Socket;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Estacao implements Runnable {

    //vou ter que implementar o udp aqui 
    private ArrayList<Socket> estacao = new ArrayList<Socket>();
    //porta que eu tô enviando as musicas      
    private String musica;
    public synchronized void setCliente(Socket cliente) {
        System.out.println("coloquei ele na lista");
        estacao.add(cliente);
    }

    public synchronized void removerCliente(Socket cliente) {
        estacao.remove(cliente);
    }

   

    @Override
    public void run() {
        try {
            //criar um servidor 
            System.out.println("inicia o servidor");
            iniciarServidorUDP();
        } catch (IOException ex) {
            System.out.println("erro ao iniciar a estacao");
        }
    }

    public Estacao(String music) {
        this.musica = music;
    }
    

    private void iniciarServidorUDP() throws IOException {        

        //criar o servidor
        DatagramSocket serverSocket = new DatagramSocket();

        //definir o tamanho que vai ser enviado        
        byte[] sendData = new byte[1024];
        String musica2 = "lascou";
        String m[] = new String[2];
        m[0] = musica;
        m[1] = musica2;
        
        int cont = 0;
        while (true) {
            
             if(cont==2) cont = 0;
            
            System.out.println("estacao rodadando ");
            //vou enviar a musica para cada um que tiver se conectado

            if (estacao.isEmpty() == true) {
                System.out.println("ta vaziaaaaaaaaaaaaaaaaaaaaaaaaaaaaa");
            }
            for (Socket socket : estacao) {
                if (socket != null) {     
                    System.out.println("tem gente ");
                    //pegar o endereço da porta 
                    InetAddress IPAddresse = socket.getInetAddress();
                    //pegando a porta que o cliete está esperando
                    //aqui vou colocar o trecho da musica para enviar
                    sendData = m[cont].getBytes();
                    //vou criar o pacote
                    DatagramPacket sendPacket = new DatagramPacket(sendData, sendData.length, IPAddresse, 5556);                    
                    //vou enviar o pacote para o cliente
                    serverSocket.send(sendPacket);

                }
            }
            
            cont++;
            
           
            try {
                Thread.sleep(1000);

            } catch (InterruptedException ex) {
                System.out.println("travou na hora que foi dormir");
            }
        }
    }

   
      
}
