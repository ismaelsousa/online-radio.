/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fonte;

/**
 *
 * @author ismae
 */
public class No {
    private int TemI;
    private int TemF;
    private String cor;
    private int pararou;
    private int id;

    No() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public No(String cor) {
        this.cor = cor;
    }

    public String getCor() {
        return cor;
    }

    public void setCor(String cor) {
        this.cor = cor;
    }

    public int getTemI() {
        return TemI;
    }

    public void setTemI(int TemI) {
        this.TemI = TemI;
    }

    public int getTemF() {
        return TemF;
    }

    public void setTemF(int TemF) {
        this.TemF = TemF;
    }

    public int getPararou() {
        return pararou;
    }

    public void setPararou(int pararou) {
        this.pararou = pararou;
    }
    
    
}
