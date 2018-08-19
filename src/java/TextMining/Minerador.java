package TextMining;


//import java.io.FileNotFoundException;
import ptstemmer.Stemmer;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author lefoly
 */
public class Minerador  {

  public static String processar(String arg) //throws FileNotFoundException
  {

     //Abrir Arquivo
    String t = arg;
    //Arquivo a = new Arquivo();
    //t = a.processar(arg);
    
    //Detector de adverbios
    
    

    //Tokenizar
    Tokenizador passo1 = new Tokenizador();
    t = passo1.processar(t);
    //System.out.println("Após Tokenizador:\n" + t);
   
  
    //Filtrar Stopwords
    Stopwords passo2 = new Stopwords();
    t = passo2.processar(t);
    System.out.println("Após Stopwords:\n" + t);

    //Aplicar Stemming

    String[] entrada = t.split("\\s");
    String saida = "";

    Stemmer st = Stemmer.StemmerFactory(Stemmer.StemmerType.ORENGO);
	st.enableCaching(1000);

    for (int i=0; i < entrada.length; i++) {
   	    saida = saida + st.wordStemming(entrada[i]) + " ";
	}
    //System.out.println("Após Stemming:\n" + saida);
    return saida;      
  }

  public static String FiltrarStopwords(String arg) //throws FileNotFoundException
  {
    Tokenizador passo1 = new Tokenizador();
    arg = passo1.processar(arg);
    Stopwords passo2 = new Stopwords();
    arg = passo2.processar(arg);
    return arg;
  }

  public static String Radicalizar(String arg) {
        Stemmer st = Stemmer.StemmerFactory(Stemmer.StemmerType.ORENGO);
	st.enableCaching(1000);
        String saida = st.wordStemming(arg);
        return saida;
  }

    public static String RadicalizarFrase(String arg) {
        Stemmer st = Stemmer.StemmerFactory(Stemmer.StemmerType.ORENGO);
	st.enableCaching(1000);
        String[] fraseVetor = st.phraseStemming(arg);
        String saida = "";
        for (int i =0; i < fraseVetor.length; i++)
            saida = saida + " " + fraseVetor[i];
        return saida;
  }

}
