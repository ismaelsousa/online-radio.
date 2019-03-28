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
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author ismae
 */
public class EsperaConexao extends Thread{

    private ServerSocket server;

    public EsperaConexao(ServerSocket serve) {
        this.server = serve;
        this.start();
    }

    @Override
    public void run() {
        //incrementa para cada novo cliente
        int portaUDp = 9876;
        
        while (true) {
            //chama o método que tem o accept e retorna o socket criado 
            Socket socket = null;
            try {
                //fica esperando o cliente....
                socket = server.accept();
                System.out.println("cliente conectado");
                
                ObjectOutputStream output = new ObjectOutputStream(socket.getOutputStream());
                ObjectInputStream Input = new ObjectInputStream(socket.getInputStream());
                
                NoC noCliente = new NoC(socket,portaUDp++,output, Input );
                
                
                //trata a conexão é uma thread responsável por trocar mensagens com o cliente
                TrataConexao trata = new TrataConexao(noCliente);
                Thread trate = new Thread(trata);//inicia a thread
                trate.start();

            } catch (IOException ex) {
                System.out.println("Erro ao esperar o socket" + ex);
            }
        }
    }
}
