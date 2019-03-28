/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author aluno
 */
public class No {

    private String cor;
    private int valor;
    private List<No> visinhos;
    private int tempoI;
    private int tempF;
    private int quantidadeVertice;
    private No [] grafo;

    public int getQuantidadeVertice() {
        return quantidadeVertice;
    }

    public No[] getGrafo() {
        return grafo;
    }
    
    public No(){
        super();
    }
    public No(String cor, int valor) {
        this.cor = cor;
        this.valor = valor;
        this.visinhos = new ArrayList<No>();

    }

    public No adj(No no, int i) {
        if(i < no.getVisinhos().size()){
            return no.getVisinhos().get(i);
        }else{
            return null;
        }
        
    }

    public No verificaMenorVizinho(No no) {
        int cont = 0;
        No aux = new No();
            while(cont < no.getVisinhos().size()){
                if(no.adj(no, cont).getCor() == "BRANCO"){
                    aux = no.adj(no,cont);
                    break;
                }
                cont++;
            }
            if(aux.getCor() == null){
                aux = null;
            }
            if(aux == null){
                return null;
            }
        while (cont <= no.getVisinhos().size()) {
            cont++;
            if(no.getVisinhos().size() == 1){
                if(no.adj(no, 0).getCor() == "BRANCO"){
                    return no.adj(no, 0);
                }else{
                    return null;
                }
                
            }else
            if (no.adj(no, cont) != null) {
                if ("BRANCO".equals(no.adj(no, cont).getCor()) && aux.getValor() > no.adj(no, cont).getValor()) {
                    aux = no.adj(no, cont);
                }
            }
        }
        if(aux.getCor() == "CINZA" || aux.getCor() == "PRETO"){
            return null;
        }
        return aux;

    }

    public String getCor() {
        return cor;
    }

    public int getValor() {
        return valor;
    }

    public void setValor(int valor) {
        this.valor = valor;
    }

    public void setCor(String cor) {
        this.cor = cor;
    }

    public List<No> getVisinhos() {
        return visinhos;
    }

    public void addVisinho(No no) {
        if (no != null) {
            this.visinhos.add(no);
        }
    }

    public int getTempoI() {
        return tempoI;
    }

    public void setTempoI(int tempoI) {
        this.tempoI = tempoI;
    }

    public void setTempF(int tempF) {
        this.tempF = tempF;
    }

    public int getTempF() {
        return tempF;
    }
    
  public void criarGrafo(){
        String linha;
        String v1,v2;
        v1="";
        v2=v1;
        int vt1=0,vt2=0;
        boolean sabeV1=false;
       
       
        int i=1;
        
        
        
        try {
            File arquivoOficial= new File("Grafo1_1000_12.txt");
            FileReader arquivo= new FileReader(arquivoOficial);
            BufferedReader ler = new BufferedReader(arquivo);
            
            try {
                while  (ler.ready()){
                    
                   linha=ler.readLine();
                   
                   if(linha!=null){
                   
                    if (i==1) {
                        
                        
                        char aux[]=linha.toCharArray();
                        
                        linha="";
                        for (int j = 0; j < 4; j++) {
                            linha=linha+Character.toString(aux[j]);
                        }
                        
                     this.quantidadeVertice=Integer.parseInt(linha);
                     this.grafo=new No[this.quantidadeVertice];
                     
                        for (int j = 0; j < this.quantidadeVertice; j++) {
                            grafo[j]=new No("BRANCO",j);//Posição terá o mesmo label do vertice
                        }
                     
                    // 
                 
                        i++;
                    }else {
                         char aux2[]=linha.toCharArray();
                    
                        
                        //inicio else
                  //  System.out.println(linha);
                  //Parte de pegar as arestas do grafo
                    
                  
                    
                    for (int j = 0; j < aux2.length; j++) {
                        if (aux2[j]!=' '&& !sabeV1) {
                            v1=v1+Character.toString(aux2[j]);
                        }else if(aux2[j]==' '){
                            sabeV1=true;
                        }else{
                         v2=v2+Character.toString(aux2[j]);
                        }
                    }
                    
                    
                    i++;
                 //   System.out.println(linha);
                    sabeV1=false;
                    //System.out.println("V1:"+v1+" V2:"+v2);
                 //Inicializando valores
                 
               //verificar string
               
                 
                 //fim de inicialização de valores
                 
                        //System.out.println("v2:  "+v2);
                        if (i>3) {
                          vt1=Integer.parseInt(v1);
                          vt2=Integer.parseInt(v2);
                          
                   //         System.out.println("V1="+vt1+"  V2="+vt2);
                    
                        }
                          
                
                   
                  
                    v1="";
                    v2="";
                    //Adicionar as arestas// ou visinhos
                 grafo[vt1].addVisinho(grafo[vt2]);
                }//fim else
                   }
                }
                
                
            } catch (IOException ex) {
                Logger.getLogger(No.class.getName()).log(Level.SEVERE, null, ex);
            }
            
            
        } catch (FileNotFoundException ex) {
            Logger.getLogger(No.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        
    }

}
