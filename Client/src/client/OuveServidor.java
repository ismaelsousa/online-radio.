/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package client;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author ismae
 */
public class OuveServidor extends Thread {
    private Socket tcp;
    private ObjectInputStream input;    
    public boolean cicloOuvir = true;
    
    public OuveServidor(Socket tcp, ObjectInputStream input) {
        this.input = input;
        this.tcp =tcp;
     
    }

    @Override
    public void run() {
        
        while (cicloOuvir) {
            try {
                String msgDoServidor = (String) input.readObject();
                //se chegar o nome da musica ele printa
                System.out.println(msgDoServidor);
                
                if("invalid Command".equals(msgDoServidor)){
                //se tiver qualquer comando errado ele fechaa                  
                    System.exit(0);
                    
                }
                
            } catch (IOException ex) {
                //se cair nessa exceção é pq o servidor desligou                
                System.exit(0);
            } catch (ClassNotFoundException ex) {
                System.out.println("erro ao ouvir a msg do servidor");
            }
        }
    }
}
