/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package coddenilson;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.Socket;
import java.net.SocketException;
import java.util.ArrayList;

/**
 *
 * @author Sammy Guergachi <sguergachi at gmail.com>
 */
public class Estacao extends Thread {

    public static int quantEstacoes;
    public String nomeMusica;
    public int cont = 0;
    ArrayList<NoCliente> ouvintes = new ArrayList<>();
    private String musica[];

    public Estacao(String nome, String musica) {
        this.musica = musica.split("-");
        this.nomeMusica = nome;
        quantEstacoes++;
        this.start();
    }

    public void setNoCliente(Socket tcp, int portaUdp){
        NoCliente novo = new NoCliente(tcp, portaUdp);
        ouvintes.add(novo);
    }
    @Override
    public void run() {
        try {
            DatagramSocket servidorUDP = new DatagramSocket();
            while (true) {
                if (cont == musica.length) {
                    cont = 0;
                    //avisar que a musica acabou e começou de novo
                }
                for (NoCliente ouvinte : ouvintes) {
                    byte musicByte[] = new byte[200];
                    musicByte = musica[cont].getBytes();
                    DatagramPacket pkt = new DatagramPacket(musicByte, musicByte.length, ouvinte.getCliente().getInetAddress(), ouvinte.getPortaUdp());
                    servidorUDP.send(pkt);
                }
                cont++;
                Thread.sleep(1000);
            }
        } catch (SocketException ex) {
            System.out.println("erro ao tentar criar servidor UDP " + ex);
        } catch (IOException ex) {
            System.out.println("Erro ao enviar pacote para  cliente " + ex);
        } catch (InterruptedException ex) {
            System.out.println("Erro na Thread.Sleep " + ex);
        }
    }

}
