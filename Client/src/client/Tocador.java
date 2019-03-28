/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package client;

import java.io.ByteArrayInputStream;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javazoom.jl.decoder.JavaLayerException;
import javazoom.jl.player.Player;

/**
 *
 * @author ismae
 */
public class Tocador extends Thread {

    private byte[] bits;
    private Player player;

    public Tocador(byte[] bits) {
        this.bits = bits;
    }

    @Override
    public void run() {

        try {
            player = new Player(new ByteArrayInputStream(bits));
            player.play();
        } catch (JavaLayerException ex) {
           
        }
        

    }
}
