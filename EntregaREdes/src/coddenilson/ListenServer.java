/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package coddenilson;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.net.Socket;


/**
 * 
 * @author Sammy Guergachi <sguergachi at gmail.com>
 */
public class ListenServer extends Thread{
    Socket server;
    ObjectInputStream input;
    public boolean ciclo = true;
    public ListenServer(Socket server,ObjectInputStream input ){
        if(server != null)
            this.server = server;
        this.input = input;
        this.start();
        
    
    
    }
    @Override
    public void run() {
        try {
            
            while(ciclo){
                String msg = (String)input.readObject();                                
                if(msg.equals("Desconectado")){
                    ciclo = false;
                }
                System.out.println(msg);
            }
          
        } catch (IOException ex) {
            System.out.println("Erro Input "+ex);
        } catch (ClassNotFoundException ex) {
            System.out.println("Erro ao receber Annonce do servidor "+ex);
        }
       
    }

}
