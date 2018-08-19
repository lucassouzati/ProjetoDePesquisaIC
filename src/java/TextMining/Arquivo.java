/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package TextMining;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

/**
 *
 * @author lefoly
 */
public class Arquivo {

private String ListaArq;

    /**
     * @return the arq
     */
    public String getListaArq() {
        return ListaArq;
    }

    /**
     * @param arq the arq to set
     */
    public void setArq(String ListaArq) {
        this.ListaArq = ListaArq;
    }

    public String processar(String entrada) throws FileNotFoundException {
        int i;
        String t = "";
        File arq = new File (entrada);
        try {
            BufferedReader in = new BufferedReader (new FileReader(arq));
            String s = in.readLine();
            do {
                t = t + s + " ";
                s =  in.readLine();
            } while (s != null);
            in.close();
        } catch (FileNotFoundException e1) {
            System.out.println("Arquivo não encontrado" + e1);
        } catch (IOException e2) {
            System.out.println("Ocorreu a exceção - " + e2);
        }
        return t;
    }
}
