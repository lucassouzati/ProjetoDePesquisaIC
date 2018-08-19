/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package TextMining;

/**
 *
 * @author Leandro
 */
public class Tokenizador {

    private String texto;
    private String[] tokens;
    
    public String processar(String texto)
    {
      tokens = texto.split(" ");
      for (int i=0; i < tokens.length; i++) {
         tokens[i] = tokens[i].replace('.', ' ');
         tokens[i] = tokens[i].replace(',', ' ');
         tokens[i] = tokens[i].replace(';', ' ');
         tokens[i] = tokens[i].replace(':', ' ');
         tokens[i] = tokens[i].replace('?', ' ');
         tokens[i] = tokens[i].replace('!', ' ');
         tokens[i] = tokens[i].replace('(', ' ');
         tokens[i] = tokens[i].replace(')', ' ');
         tokens[i] = tokens[i].replace('"', ' ');
         tokens[i] = tokens[i].replace('\'', ' ');
         tokens[i] = tokens[i].toLowerCase();
         tokens[i] = tokens[i].trim();
      }
      return listaTokens();
    }

    /**
     * @return the texto
     */
    public String getTexto() {
        return texto;
    }

    /**
     * @param texto the texto to set
     */
    public void setTexto(String texto) {
        this.texto = texto;
    }

    /**
     * @return the tokens
     */
    public String[] getTokens() {
        return tokens;
    }

    /**
     * @param tokens the tokens to set
     */
    public void setTokens(String[] tokens) {
        this.tokens = tokens;
    }

    public String listaTokens() {
        String lista;
        lista = "";
        for (int i=0; i < tokens.length; i++) {
            lista = lista + tokens[i] + "\n";
        }
       return lista;
    }

}
