package server;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.Socket;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Estacao extends Thread {

    //vou ter que implementar o udp aqui    
    public ArrayList<NoC> noclientes = new ArrayList<>();
    private ArrayList<byte[]> pedacoDaMusica = new ArrayList<>();

    //porta que eu tô enviando as musicas      
    public String nome;
    private String arquivo;

    public Estacao(String nome, String arquivo) {
        this.nome = nome;
        this.arquivo = arquivo;
        quebrarMusica();
    }

    @Override
    public void run() {
        try {
            //criar um servidor 
            System.out.println("inicia o servidor udp");
            iniciarServidorUDP();

        } catch (IOException ex) {
            System.out.println("erro ao iniciar a estacao");
        }
    }

    private void iniciarServidorUDP() throws IOException {

        //criar o servidor
        DatagramSocket serverSocket = new DatagramSocket();

        int cont = 0;
        while (true) {
            //quando chega ao final da musica envia a msg para o tcp
            if (cont == pedacoDaMusica.size()) {
                cont = 0;
                for (NoC nocliente : noclientes) {
                    nocliente.output.writeObject("repetindo a musica");
                }
            }

            for (NoC nocliente : noclientes) {
                byte musicByte[] = new byte[50000];
                //aqui pego pega a parte da musica q vai mandar 
                musicByte = pedacoDaMusica.get(cont);
                //
                DatagramPacket pacote = new DatagramPacket(musicByte, musicByte.length, nocliente.cliente.getInetAddress(), nocliente.portaUdp);
                System.out.println("mandei para porta" + nocliente.portaUdp);
                serverSocket.send(pacote);
            }

            cont++;

            try {
                Thread.sleep(2000);

            } catch (InterruptedException ex) {
                System.out.println("travou na hora que foi dormir");
            }
        }
    }

    public void quebrarMusica() {
        try {
            FileInputStream in = new FileInputStream(new File(arquivo));
            BufferedInputStream bufferMusica = new BufferedInputStream(in);
            int n = 0;
            while (n != -1) {
                byte[] byteDeMusica = new byte[50000];
                n = bufferMusica.read(byteDeMusica);
                pedacoDaMusica.add(byteDeMusica);
            }
        } catch (FileNotFoundException ex) {
            System.out.println("Erro ao pegar a musica " + ex);
        } catch (IOException ex) {
            System.out.println("Erro IO" + ex);
        }

    }

    public void removerClientes() {
        while (noclientes.size() != 0) {
            noclientes.remove(0);
        }
    }
}
