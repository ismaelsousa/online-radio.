package coddenilson;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.Scanner;

/**
 *
 * @author Sammy Guergachi <sguergachi at gmail.com>
 */
public class ClienteTCP {

    static Scanner entrada = new Scanner(System.in);
    private boolean ciclo = true;

    public static void main(String[] args) throws IOException, InterruptedException, ClassNotFoundException {
        Socket socketServer = new Socket("localhost", 6000);
        ObjectOutputStream output = new ObjectOutputStream(socketServer.getOutputStream());//enviar
        ObjectInputStream input = new ObjectInputStream(socketServer.getInputStream());//receber

        int portaudp = 0;
        if (Admin.radioConnect == -1) {
            output.writeObject("0");            
            String pegaporta = (String) input.readObject();
            String div[] = pegaporta.split("-");
            int portaPegada = Integer.parseInt(div[1]);
            portaudp = portaPegada;           
        }
        ClienteUDP c = new ClienteUDP(portaudp);
        ListenServer ls = new ListenServer(socketServer, input);
        while (true) {
            System.out.println("Digite um Comando");
            String comando = entrada.nextLine();
            if ("q".equals(comando) || "Q".equals(comando)) {
                output.writeObject("q");
                c.vivo = false; // fechando UDP
                ls.ciclo = false; // fechando ListenServer
                System.exit(0);
            } else {
                comando = "1-" + comando;
                output.writeObject(comando);

            }

        }

    }

}
