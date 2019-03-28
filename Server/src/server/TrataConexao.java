/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package server;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import static java.lang.Integer.parseInt;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author ismae
 */
public class TrataConexao implements Runnable {

    private NoC noCliente;
    private int estacaoAtual = -1;//não escolheu nada ainda

    public TrataConexao(NoC no) {
        this.noCliente = no;
    }

    @Override
    public void run() {
        try {
            trataConexao();
        } catch (IOException ex) {
            //so entra se ele estiver em alguma estacao se n nem entra
            if(estacaoAtual!=-1)
            Server.estacoes.get(estacaoAtual).noclientes.remove(noCliente);            
            System.out.println("O cliente saiu de uma vez");
            //printa e sai sem derrubar o servidor 
        } catch (ClassNotFoundException ex) {
           
        }
    }

    public void trataConexao() throws ClassNotFoundException, IOException {
        //espero hello aq
        String msgCliente = (String) noCliente.input.readObject();
        
        //aqui eu envio a porta para ele escultar e quantas radios tem para o cara digitar 
        noCliente.output.writeObject("welcome-"+noCliente.portaUdp+"-"+Server.estacoes.size());
        
        
        boolean ciclo = true;
        
        while (ciclo) {
            String MsgRecebida = null;
            try {
                //aqui já voou esperar qualquer msg
                MsgRecebida = (String) noCliente.input.readObject();
                System.out.println(MsgRecebida);
                
            } catch (ClassNotFoundException ex) {
                System.out.println("erro ao receber mensagem do cliente 400");
            }

            if ("q".equals(MsgRecebida) || "Q".equals(MsgRecebida)) {

                noCliente.cliente.close();
                ciclo = false;
                if (estacaoAtual != -1) {
                    Server.estacoes.get(estacaoAtual).noclientes.remove(noCliente);
                }
                
            } else {
                
                int estacaoNova = parseInt(MsgRecebida);//aqui eu garanto q vai chegar um numero 
                if (estacaoAtual == -1) { //ainda n esta em nenhuma 
                    if (estacaoNova >= 0 && estacaoNova < Server.estacoes.size()) {//verifico se tem estacao no tamanho q ele digitou
                        Server.estacoes.get(estacaoNova).noclientes.add(noCliente);
                        estacaoAtual = estacaoNova;
                        noCliente.output.writeObject(Server.estacoes.get(estacaoNova).nome); //aqui passo o nome da musica para o tcp               

                    } else {
                        noCliente.output.writeObject("Invalid Command");
                        noCliente.cliente.close();
                        ciclo = false;
                        
                    }

                } else {
                    if (estacaoNova >= 0 && estacaoNova < Server.estacoes.size()) {

                        Server.estacoes.get(estacaoAtual).noclientes.remove(noCliente);
                        Server.estacoes.get(estacaoNova).noclientes.add(noCliente);
                        noCliente.output.writeObject(Server.estacoes.get(estacaoNova).nome);
                        estacaoAtual = estacaoNova;                       
                    } else {
                        //não está na faixa do tamanho das estacoes disponiveis 
                        noCliente.output.writeObject("Invalid Command");
                        ciclo = false;                        
                    }

                }

            }
        }
    }
}
