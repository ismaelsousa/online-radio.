/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */



import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutput;
import java.io.ObjectOutputStream;
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
    public static void main(String[] args) throws ClassNotFoundException {
        try {
            //estabelecer uma conexão            
            Socket socket = new Socket("localhost", 5555);

            //criar stream entrada e saídas
            ObjectOutputStream output = new ObjectOutputStream(socket.getOutputStream());
            ObjectInputStream input = new ObjectInputStream(socket.getInputStream());
            //manda o hello
            String msg = "HELLO";
            output.writeObject(msg);
            System.out.println(msg);
            output.flush();                                  
            CUPD c = new CUPD();
            System.out.println("criei ");         
            Thread udp_c = new Thread(c);   
            udp_c.start();
            System.out.println("rodei");
            Scanner teclado = new Scanner(System.in);
            boolean teste = true;
                  
                System.out.println("digite algo");
                String envio = teclado.nextLine();
                System.out.println(envio);
                if (envio.equals("q") ) {
                    System.out.println("é igual");
                    c.setRoda(false);
                    teste = false;
                }
                output.writeObject(envio);
                
           
            
          
            
        } catch (IOException ex) {
            System.out.println(ex);
            System.out.println("erro no cliente");
        } finally{
            System.out.println("saiu    ");
        }

    }
}
