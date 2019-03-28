/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


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

    public static Estacao esta0 = new Estacao("tocando 123");
    public static Estacao esta1 = new Estacao("lascolg");

    private ServerSocket serverSocket;

    private void criarServerSocket(int porta) throws IOException {
        serverSocket = new ServerSocket(porta);
    }

    private Socket esperaConexao() throws IOException {
        Socket socket = serverSocket.accept();
        System.out.println("conectou a :" + socket.getInetAddress() + " " + socket.getLocalPort());
        return socket;
    }
    private void fechaScoket(Socket f) throws IOException {
        f.close();
    }
    public static void main(String[] args) {

        try {
            //iniciar a estacao 0 
            Thread t = new Thread(esta0);
            t.start();

            Thread t2 = new Thread(esta1);
            t2.start();

            //instancia a classe server 
            Server server = new Server();
            System.out.println("aguardadando conexão");

            //depois chama o método que cria o servidor passando a porta 
            server.criarServerSocket(5555);

            //o while faz com que o servidor consiga ter mais de um cliente 
            while (true) {
                //chama o método que tem o accept e retorna o socket criado 
                Socket socket = server.esperaConexao();

                System.out.println("cliente conectado");

                //trata a conexão é uma thread responsável por trocar mensagens com o cliente
                TrataConexao trata = new TrataConexao(socket);
                Thread trate = new Thread(trata);//inicia a thread
                trate.start();

            }
        } catch (IOException ex) {
              System.out.println("Erro ao esperar conexao");

        }

    }
}
