/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package coddenilson;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.SocketException;


/**
 * 
 * @author Sammy Guergachi <sguergachi at gmail.com>
 */

public class ClienteUDP extends Thread{
    private int porta;
    public boolean vivo = true;
    public static int incremento = 0;
    public ClienteUDP(int porta){
        super();
        incremento++;
        this.porta = porta;
        this.start();
        
    
    }
    @Override
    public void run() {
        try {
            DatagramSocket clienteUDP = new DatagramSocket(porta);
            while(vivo){
                byte music[] = new byte[200];
                DatagramPacket pkt = new DatagramPacket(music, music.length);
                clienteUDP.receive(pkt);
                String clip = new String(pkt.getData());
                System.out.println(clip);                
            }
            System.exit(0);
           
        } catch (SocketException ex) {
            System.out.println("Erro ao criar cliente UDP "+ex);
        } catch (IOException ex) {
            System.out.println("Erro ao receber pacote da musica "+ex);
        }
    }
    
    
    
}
