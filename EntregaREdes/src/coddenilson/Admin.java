/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package coddenilson;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import static java.lang.Integer.parseInt;
import java.net.Socket;

/**
 *
 * @author Sammy Guergachi <sguergachi at gmail.com>
 */
public class Admin extends Thread {

    Socket cliente;
    private int radioLocal;
    boolean isConnect;//para saber se o usuario ja esta conectado
    static int radioConnect = -1; // identifica qual radio o cliente esta conectado
    private int portaUdp;
   

    Admin(Socket cliente, int portaUdp) {
        this.cliente = cliente;
        this.portaUdp = portaUdp;
        this.start();
    }

    @Override
    public void run() {
        try {
            boolean ciclo = true;
            ObjectOutputStream output = new ObjectOutputStream(this.cliente.getOutputStream());//enviar
            ObjectInputStream input = new ObjectInputStream(this.cliente.getInputStream());//receber
            while (ciclo) {

                String msgCliente = (String) input.readObject();//espera resposta do cliente
                String msgQuebrada[] = msgCliente.split("-");

                if (msgCliente != null) {
                    if ("0".equals(msgQuebrada[0]) && isConnect == false) {
                        output.writeObject("Welcome-"+portaUdp);
                        isConnect = true;
                        msgCliente = null;
                    } else if ("q".equals(msgQuebrada[0]) || "Q".equals(msgQuebrada[0])) {// caso o usuario queira sair
                        cliente.close();
                        ciclo = false;
                        if (radioConnect != -1) {
                            Servidor.radios.get(radioConnect).ouvintes.remove(cliente);//tira o ouvinte da radio
                        }

                    } else if (msgQuebrada[0].equals("1")) {

                        int radioEscolhida = parseInt(msgQuebrada[1]);//convertendo para inteiro
                        radioLocal = radioEscolhida;
                        if (radioConnect == -1) {//primeira vez que o cliente conectar
                            if (radioEscolhida >= 0 && radioEscolhida <= Servidor.radios.size()) {
                                Servidor.radios.get(radioEscolhida).setNoCliente(cliente,portaUdp);
                                output.writeObject("1-" + Servidor.radios.get(radioEscolhida).nomeMusica);
                                radioConnect = radioEscolhida;
                            } else {
                                output.writeObject("Invalid Command");
                                cliente.close();

                            }
                        } else {//caso ja esteja conectado em uma radio
                            Servidor.radios.get(radioConnect).ouvintes.remove(new NoCliente(cliente, portaUdp));
                            Servidor.radios.get(radioEscolhida).ouvintes.add(new NoCliente(cliente, portaUdp));
                            output.writeObject("1-" + Servidor.radios.get(radioEscolhida).nomeMusica);
                            radioConnect = radioEscolhida;
                        }
                    } else {
                        output.writeObject("Invalid Command");
                        cliente.close();
                    }
                }

            }

        } catch (IOException ex) {
            System.out.println("Erro Input/Output: " + ex);
        } catch (ClassNotFoundException ex) {
            System.out.println("Erro de Classe Nao encontrada :" + ex);
        }

    }

}
