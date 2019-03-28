/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servidorudp;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;

/**
 *
 * @author ismae
 */
public class ServidorUDP {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws SocketException, IOException {
        // TODO code application logic here
        int porta = 9876;
        int numCinn = 1;
        
        //criar o servidor
        DatagramSocket serverSocket = new DatagramSocket(porta);
        
        //definir o tamanho que vai ser enviado
        byte [] receiveData = new byte[1024];
        byte[] sendData = new byte[1024];
        
        while(true){
           /* DatagramPacket receivePacket = new DatagramPacket(receiveData, receiveData.length);
            System.out.println("servidor: esperando por datagrama udp");
            
            serverSocket.receive(receivePacket);
            System.out.println("servidor: datagram udp ["+ numCinn +"]");
            
            String sentence = new String(receivePacket.getData());
            System.out.println(sentence);
            
            InetAddress IPAddresse = receivePacket.getAddress();
            
            int port = receivePacket.getPort();;
            */
            String capitalizedSentece = "tocando 123";
            InetAddress IPAddress = InetAddress.getByName("localhost");
            sendData = capitalizedSentece.getBytes();
            
            DatagramPacket sendPacket = new DatagramPacket(sendData, sendData.length, IPAddress,5556);
            
            System.out.println("servidor: eviando "+ capitalizedSentece);
            
            serverSocket.send(sendPacket);
            System.out.println("servidor: ok\n");
            
            
            
            
            
            
            
            
            
            
            
            
            
            
            
            
            
        }
        
    }
    
}
