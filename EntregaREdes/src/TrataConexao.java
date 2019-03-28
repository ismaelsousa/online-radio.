/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author ismae
 */
public class TrataConexao implements Runnable {

    private Socket socket;

    public TrataConexao(Socket socket) {
        this.socket = socket;
    }

    @Override
    public void run() {
        try {
            try {
                trataConexao(socket);
            } catch (ClassNotFoundException ex) {
                System.out.println("não conseguiu tratar a conexao");
            }
            System.out.println("colocado socket na thread");
        } catch (IOException ex) {
            System.out.println("erro de entrada ou saida de dados");
        }

    }

    public void trataConexao(Socket socket) throws IOException, ClassNotFoundException {
        //aqui eu vou ter que colocar esse socket na lista para o envio 
        //ante tenho que fazer o cliente escolher qual estacao 
        //depois add a lista para a estaçao enviar 
        try {
            /*protocolo
            criar streams de entrada e saídas
            tratar a conversação entre cliente e servidor (tratar protocolo
             */
            ObjectOutputStream output = new ObjectOutputStream(socket.getOutputStream());
            ObjectInputStream input = new ObjectInputStream(socket.getInputStream());

            //1-receber a mensagem do cliente dizendo Hello
            //2-enviar qual estacao ele gostaria de se conectar
            //incluir ele na radio do servidor
            String msg = (String) input.readObject();
            System.out.println("Mensagem recebida: " + msg);
            output.writeObject("welcome");
            boolean teste = true;
            while (teste) {
                String receive = (String) input.readObject();
                System.out.println(receive);
                if (receive == "q") {
                    System.out.println("fechei ");
                    fechaScoket(socket);
                    break;
                }
                //coloco o cliente na radio que ele desejou 
                if (receive == "0") {
                    Server.esta0.setCliente(socket);
                } else if (receive == "1") {
                    Server.esta1.setCliente(socket);
                } else {
                    System.out.println("Digitou um valor errado");
                    fechaScoket(socket);//cliente digitou errado então corta a conexao
                }
            }

            

        } catch (IOException ex) {
            //tratamento de falha
            System.out.println("erro na conexao");
            fechaScoket(socket);
        }

    }

    private void fechaScoket(Socket f) throws IOException {
        f.close();
    }

}
