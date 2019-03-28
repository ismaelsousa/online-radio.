/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servidorudp;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;
import java.net.UnknownHostException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author ismae
 */
public class ClienteUDP implements Runnable{

    public static void main(String[] args) throws SocketException, UnknownHostException, IOException {
       /*
        BufferedReader inFromUser = new BufferedReader(new InputStreamReader(System.in));

        DatagramSocket clienteSocket = new DatagramSocket();

        String servidor = "localhost";
        int porta = 9876;
        
        InetAddress IPAddress = InetAddress.getByName(servidor);
        
        byte[] sendData = new byte[1024];
        byte[] receiveData = new byte[1024];
        
        System.out.println("cliente : digite o texto a ser enviado ");
        String sentence = "me envia a musica";
        
        sendData = sentence.getBytes();
        
        DatagramPacket sendPacket = new DatagramPacket(sendData, sendData.length, IPAddress, porta);
        
        System.out.println("cliente : enviando pacote udp para o servidor");
        
        clienteSocket.send(sendPacket);
        
        DatagramPacket receivePacket = new DatagramPacket(receiveData, receiveData.length);
        
        clienteSocket.receive(receivePacket);
        System.out.println("cliente : pacote udp recebido...");
        
        String modifiedSentence = new String(receivePacket.getData());
        
        System.out.println("cliente : texto recebidoo do servidor " + modifiedSentence);
        clienteSocket.close();
        System.out.println("cliente : socete cliente fechado");  */                      
       
    }
    private int port;

    public ClienteUDP(int porta) {
        this.port= porta;
    }

    
    @Override
    public void run() {
        System.out.println("oii eu sou a thread do cliente udp");
        try {
            esperaMusic();
        } catch (SocketException ex) {
            Logger.getLogger(ClienteUDP.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(ClienteUDP.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void esperaMusic() throws SocketException, IOException {
        //criar o cliente UDP para esperar as musicas
        DatagramSocket clienteSocket = new DatagramSocket(port);        
        
        //criar o buffer para receber
        byte[] receiveData = new byte[1024];
        
        //criando o pacoteS
        DatagramPacket receivePacket = new DatagramPacket(receiveData, receiveData.length);
       
        //esperando o servidor enviar o pacote
        clienteSocket.receive(receivePacket);
       
       //só para testar com texto vou transformar em string
       String modifiedSentence = new String(receivePacket.getData());
       
       //imprimir a musica
       System.out.println(modifiedSentence);      
    }
}
