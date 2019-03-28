/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package util;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author ismae
 */
public class Mensagem implements Serializable{

    private String operacao;
    private Status status;

    Map<String, Object> params;

    public Mensagem(String operacao) {
        this.operacao = operacao;
        params = new HashMap<>();
    }

    public String getOperacao() {
        return operacao;
    }

    void setParam(String mensagem, Object valor) {
        params.put(mensagem, valor);
    }

    public Object getParam(String chave) {
        return params.get(chave);
    }

    public void setStatus(Status s) {

    }

    public void SetParam(String sobrenome, String da_silva) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public void setStatus(String string) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
