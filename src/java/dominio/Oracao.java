/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dominio;

/**
 *
 * @author Lucas Siqueira
 */
public class Oracao {
    private String tipooracao;
    private String oracao;
    
    public Oracao(){
        
    }
    
    public Oracao(String tipooracao, String oracao) {
        this.tipooracao = tipooracao;
        this.oracao = oracao;
    }
    
    public String getTipooracao() {
        return tipooracao;
    }

    public void setTipooracao(String tipooracao) {
        this.tipooracao = tipooracao;
    }

    public String getOracao() {
        return oracao;
    }

    public void setOracao(String oracao) {
        this.oracao = oracao;
    }

    @Override
    public String toString() {
        return "Oracao{" + "oracao=" + oracao + "}\n";
    }
    
    
}
