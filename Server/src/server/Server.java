/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package server;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author ismae
 */
public class Server {    
    public static ArrayList<Estacao> estacoes = new ArrayList<>();
    public static Estacao esta0 = new Estacao("Metallica .The Unforgiven","C:\\Users\\ismae\\Documents\\NetBeansProjects\\Server\\src\\server\\Metallica .The Unforgiven.mp3");
    public static Estacao esta1 = new Estacao("johnny cash.hurt","C:\\Users\\ismae\\Documents\\NetBeansProjects\\Server\\src\\server\\johnny cash.hurt.mp3");

    private ServerSocket serverSocket; 

    private ServerSocket criarServidor(int porta) throws IOException {
        serverSocket = new ServerSocket(porta);
        return serverSocket;
    }

    public static void main(String[] args) {

        esta0.start();
        estacoes.add(esta0);

        
        esta1.start();
        estacoes.add(esta1);

        //instancia a classe server 
        Server server = new Server();

        System.out.println("aguardadando conexão");

        try {
            //aqui eu crio uma thread para ouvir os cliente, mas antes tenho que instanciar o servidor
            //assim eu crio o servidor e já passo para a thread
            EsperaConexao esperandoNaPorta = new EsperaConexao(server.criarServidor(5555));
        } catch (IOException ex) {
            System.out.println("A porta está sendo usada" + ex);
        }
        
        Scanner teclado = new Scanner(System.in);
        
        
        while (true) {
            //sempre vou esperar um comando 
            System.out.println("Digite p ou q");
            String te = teclado.nextLine();

            if ("p".equals(te) || "P".equals(te)) {
                System.out.println("entrou p");
                for (int i = 0; i < estacoes.size(); i++) {
                    System.out.println("a estacao " + i + "tem " + estacoes.get(i).noclientes.size());
                }
            } else if ("q".equals(te) || "Q".equals(te)) {
                for (int i = 0; i < estacoes.size(); i++) {
                    estacoes.get(i).removerClientes();                    
                }
                System.out.println("entrou");
                System.exit(0);
            }
            

        }

    }
}
