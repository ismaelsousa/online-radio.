/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package server;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

/**
 *
 * @author ismae
 */
public class NoC {

    Socket cliente;
    int portaUdp;
    ObjectOutputStream output;
    ObjectInputStream input;

    public NoC(Socket cliente, int portaUdp, ObjectOutputStream output,ObjectInputStream input) {
        this.cliente = cliente;
        this.portaUdp = portaUdp;
        this.output = output;
        this.input = input;
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

    public ObjectOutputStream getOutput() {
        return output;
    }

    public void setOutput(ObjectOutputStream output) {
        this.output = output;
    }

 

}
