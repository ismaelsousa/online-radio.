/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package client;

import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javazoom.jl.decoder.JavaLayerException;
import javazoom.jl.player.Player;
import static sun.audio.AudioPlayer.player;

/**
 *
 * @author ismae
 */
public class CUDP implements Runnable {

    private int portaUdP;
    public boolean roda = true;

    public CUDP(int porta) {
        System.out.println("estou criando o udp na porta:" + porta);
        this.portaUdP = porta;
    }

    /*
    public static void main(String[] args) throws SocketException, UnknownHostException, IOException {
       
        BufferedReader inFromUser = new BufferedReader(new InputStreamReader(System.in));

        DatagramSocket clienteSocket = new DatagramSocket();

        String servidor = "localhost";
        int porta = 9876;
        
        InetAddress IPAddress = InetAddress.getByName(servidor);
        
        byte[] sendData = new byte[1024];
        byte[] receiveData = new byte[1024];
        
        System.out.println("cliente : digite o texto a ser enviado ");
        String sentence = inFromUser.readLine();
        
        sendData = sentence.getBytes();
        
        DatagramPacket sendPacket = new DatagramPacket(sendData, sendData.length, IPAddress, porta);
        
        System.out.println("cliente : enviando pacote udp para o servidor");
        
        clienteSocket.send(sendPacket);
        
        DatagramPacket receivePacket = new DatagramPacket(receiveData, receiveData.length);
        
        clienteSocket.receive(receivePacket);
        System.out.println("cliente : pacote udp recebido...");
        
        String modifiedSentence = new String(receivePacket.getData());
        
        System.out.println("cliente : texto recebidoo do servidor ");
        clienteSocket.close();
        System.out.println("cliente : socete cliente fechado");                        
    }
     */
    @Override
    public void run() {
        System.out.println("oii eu sou a thread do cliente udp");
        try {
            esperaMusic();
        } catch (SocketException ex) {
            System.out.println("erro no udp");
        } catch (IOException ex) {
            System.out.println("n foi possivel criar");
        } catch (JavaLayerException ex) {
          
        }
    }

    private void esperaMusic() throws SocketException, IOException, JavaLayerException {
        //criar o cliente UDP para esperar as musicas
        DatagramSocket clienteSocket = new DatagramSocket(portaUdP);

        while (roda) {
            byte musicaRecebida[] = new byte[50000];
            DatagramPacket pacote = new DatagramPacket(musicaRecebida, musicaRecebida.length);
            clienteSocket.receive(pacote);            
            new Tocador(musicaRecebida).start();        
        }
        System.exit(0);
    }

}
