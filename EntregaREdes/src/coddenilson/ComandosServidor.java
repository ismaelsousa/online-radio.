/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package coddenilson;

import static coddenilson.Servidor.entrada;


/**
 *
 * @author Sammy Guergachi <sguergachi at gmail.com>
 */
public class ComandosServidor extends Thread {
    public ComandosServidor(){
    this.start();
    }
    @Override
    public void run() {
        int cont = 0;
        while (true) {
            System.out.println("Comandos");
            String comando = entrada.nextLine();
            
            if (comando.equals("p") || comando.equals("P")) {
                while (cont <Servidor.radios.size()) {
                    System.out.println("Radio " + cont);
                    System.out.println("      Quantidade de ouvintes : " + Servidor.radios.get(cont).ouvintes.size());
                    cont++;

                }
                cont = 0;

            }else if (comando.equals("q") || comando.equals("Q")) {
                while (cont <Servidor.radios.size()) {
                    Servidor.radios.get(cont).ouvintes.clear();
                    Servidor.ciclo = false;
                    cont++;
                }
                cont = 0;
                 System.exit(0);

            }
        }
    }

}
