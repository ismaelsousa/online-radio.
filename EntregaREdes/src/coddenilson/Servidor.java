/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package coddenilson;

import java.io.IOException;

import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Scanner;

/**
 *
 * @author Sammy Guergachi <sguergachi at gmail.com>
 */
public class Servidor{
   public static boolean ciclo = true;
   public static int portaUdp = 5000;
    public static ArrayList<Estacao> radios = new ArrayList<>();
    public static Scanner entrada = new Scanner(System.in);
    public static void main(String[] args) throws IOException {
        ServerSocket server = new ServerSocket(6000);
        Estacao s1 = new Estacao("Menos de um Segundo", "Lembro de nós dois-"
                + "Sorrindo na escada aqui-"
                + "Estava tudo tão bem-"
                + "E de repente acabou");
        radios.add(s1);
        Estacao s2 = new Estacao("A Lua me traiu","Parece até conto de fadas-"
                + "Mas assim aconteceu-"
                + "Éramos dois apaixonados-"
                + "Julieta e Romeu");
        radios.add(s2);
        ComandosServidor cmd = new ComandosServidor();
        System.out.println("Esperando Clientes");
        while (ciclo) {
            Socket cliente = server.accept();
            System.out.println("Novo Cliente Conectado");
            Admin func = new Admin(cliente, portaUdp++);

        }
        System.exit(0);

    }
   
}
