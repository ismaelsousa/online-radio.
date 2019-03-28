/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package coddenilson;

import java.net.Socket;

/**
 *
 * @author ismae
 */
public class NoCliente {
    private Socket cliente;
    private int portaUdp;

    public NoCliente(Socket cliente, int portaUdp) {
        this.cliente = cliente;
        this.portaUdp = portaUdp;
    }

    public Socket getCliente() {
        return cliente;
    }

    public void setCliente(Socket cliente) {
        this.cliente = cliente;
    }

    public int getPortaUdp() {
        return portaUdp;
    }

    public void setPortaUdp(int portaUdp) {
        this.portaUdp = portaUdp;
    }
    
}
