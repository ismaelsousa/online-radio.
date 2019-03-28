/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package client;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutput;
import java.io.ObjectOutputStream;
import static java.lang.Integer.parseInt;
import java.net.Socket;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author ismae
 */
public class Client {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws ClassNotFoundException, IOException {
        
            //estabelecer uma conexão            
            Socket socket = new Socket("localhost", 5555);

            //criar stream entrada e saídas
            ObjectOutputStream output = new ObjectOutputStream(socket.getOutputStream());
            ObjectInputStream input = new ObjectInputStream(socket.getInputStream());

            try {
                output.writeObject("hello");
            } catch (IOException ex) {
                //se ele n conseguir enviar é porq o servidor caiu
                System.out.println("Servidor caiu");
                System.exit(0);
            }
            String welcome_porta_radio = null;
            try {
                welcome_porta_radio = (String) input.readObject();
            } catch (IOException ex) {
                //se ele n conseguir enviar é porq o servidor caiu
                System.out.println("Servidor caiu");
                System.exit(0);
            }
            String quebra[] = welcome_porta_radio.split("-");

            CUDP c = new CUDP(parseInt(quebra[1]));
            Thread udp = new Thread(c);
            udp.start();

            System.out.println("voce pode escolher as estacoes de 0 a " + (parseInt(quebra[2]) - 1));
            int numDeRadio = parseInt(quebra[2]);
            //aqui ele vai esperar as msgs do servidor             
            OuveServidor ouviservidor = new OuveServidor(socket, input);            
            ouviservidor.start();
            
            Scanner teclado = new Scanner(System.in);

            while (true) {

                String r = teclado.nextLine();
                if ("q".equals(r) || "Q".equals(r)) {
                    try {
                        //fecha o admin
                        output.writeObject("q");
                        
                    } catch (IOException ex) {
                        //se ele n conseguir enviar é porq o servidor caiu
                        System.out.println("Servidor caiu");
                        System.exit(0);
                    }
                    // fecha UDP
                    c.roda = false;
                    //fecha o ouvinte do adimn
                    ouviservidor.cicloOuvir = false;
                    System.exit(0);
                } else {

                    try {
                        //se por acaso digitar algo que n seja um numero nem vai mandar 
                        int numeroDaEstacao = Integer.parseInt(r);
                        
                        try {
                            //vou tentar enviar
                            if(numeroDaEstacao>= 0 && numeroDaEstacao < numDeRadio){
                            output.writeObject(""+numeroDaEstacao);                           
                            }else{
                                System.out.println("digite uma estacao dentro do limite");
                            }
                        } catch (IOException ex) {
                            //se ele n conseguir enviar é porq o servidor caiu
                            System.out.println("Servidor caiu");
                            System.exit(0);
                        }
                    } catch (NumberFormatException ex) {
                        System.out.println("Invalid Command");
                        System.exit(0);
                    }
                }
            }
        
    }
}
