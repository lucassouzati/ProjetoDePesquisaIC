/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package TextMining;

//import java.io.FileNotFoundException;

/**
 *
 * @author lefoly
 */
public class Stopwords {
    
    public String processar(String e) //throws FileNotFoundException
    {
        int i, j;
        int k = 0;
        String lista = "";

        String[] entrada = e.split("\\s");
        Arquivo a = new Arquivo();

        String listaS = "o; a; os; as; um; uma; uns; umas; em; para; de; da; das; do; dos; com; sem; é; são; " +
                        "até; ainda; quando; como; onde; mas; porém; contudo; todavia; e; ou; à ; às; ao; a; as; " +
                        "algum; alguma; alguns; algumas; seu; seus; sua; suas; no; nos; na; nas; num; " +
                        "aí; lá; este; estes; esta; estas; esse; esses; " +
                        "essa; essas; aquele; aqueles; aquela; aquelas; porque; porquê; quê; que; por; " +
                        "sim; sempre; portanto; assim; então; desse; dessa; deste; desta; " +
                        "desses; dessas; destes; destas; mais; menos; entre; pelo; pelos; pela; pelas; se; " +
                        "cada; qual; quais; já; aonde; entretanto; sobre; não; nem; nunca; nenhum; nenhuma; nenhuns; nenhumas; "; //coloquei de volta, acho que não vou precisar alterar aqui
        //Retirando os advérbios de negação para implantação do reconhecimento de negação:
        // não; nem; nunca; nenhum; nenhuma; nenhuns; nenhumas; 

        //String caminho = "../Recursos/Stopwords.txt";
//        String caminho = "";
//          if (SO().equals("Windows Vista") || SO().equals("Windows XP")) {
//            caminho = "c:\\Leandro\\stopwords.txt";
//        } else {
//            caminho = "/media/windows/Leandro/stopwords.txt"; }

        //String stop = a.processar(caminho);
        //String[] stopwords = stop.split("\\s");
        String[] stopwords = listaS.split("; ");

        for (i = 0; i < entrada.length; i++) {
            //System.out.println("i = " + i + " : " + entrada[i]);
            j = 0;
            if (entrada[i].length() > 0) {
                while ((j < stopwords.length) && (!entrada[i].toUpperCase().equals(stopwords[j].toUpperCase()))) {
                      //System.out.println("    j = " + j + " : " + stopwords[j]);
                      if (j == stopwords.length-1) {
                         lista = lista + entrada[i] + "\n";
                      }
                  j++;
                }
            }
        }
        return lista;
    }

//    public static String SO() {
//        String nomeOs = System.getProperty("os.name");
//        return nomeOs;
//    }
}
