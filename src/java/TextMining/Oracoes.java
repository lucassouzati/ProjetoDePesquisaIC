/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package TextMining;

import java.util.ArrayList;
import dominio.*;
import conexao.*;

/**
 *
 * @author Lucas Siqueira
 */
public class Oracoes {
    public static ArrayList<Oracao> oracoes = new ArrayList<Oracao>();
    //public static Oracao oracao = new Oracao();
    public static String oracaoaux = "";
    int j;
    
    public ArrayList<Oracao> processar(String entrada){
        String[] entradas = entrada.split(" ");
        for(int i = 0; i<entradas.length; i++){
            
           //if(entradas[i].contains(".") || entradas[i].contains(",")){//Verifica término de frase ou vírgula
           if(verificaPontuacao(entradas[i])){
                oracaoaux += entradas[i] + " ";
                //oracao.setTipooracao("afirmativa"); // Por enquanto não estou classificando as oracoes
                //oracao.setOracao(oracaoaux); //Conteúdo da oração
                Oracao oracao = new Oracao("afirmativa", oracaoaux);
                oracoes.add(oracao); //Adicionando a oração atual ao array de orações
                //System.out.println(oracaoaux);
                oracaoaux = "";
                
           }
           else if (verificaverbo(entradas[i])){ //Caso a palavra seja um verbo
                j = i;
                do{ //Variável oracaoaux vai receber o restante das palavras ...
                    //System.out.println(entradas[j]);
                    oracaoaux += entradas[j] + " ";
                    j++;
                }while(!verificaverbo(entradas[j]) && !verificaPontuacao(entradas[j]) /*&& j <= entradas.length*/); // ... até que ache outro verbo.
                if(verificaPontuacao(entradas[j]) && j <= entradas.length){//Caso haja uma pontuação, esta palavra acoplada a pontuação ainda faz parte da oração que estar a ser analisada.
                    oracaoaux += entradas[j] + " ";
                    j++;//Então a próxima oração vai começar da palavra a partir da pontuação
                }
                //oracao.setTipooracao("afirmativa"); // Por enquanto não estou classificando as oracoes
                //oracao.setOracao(oracaoaux); //Conteúdo da oração
                Oracao oracao = new Oracao("afirmativa", oracaoaux); // Por enquanto não estou classificando as oracoes, e classificando todas como afirmativas.
                oracoes.add(oracao); //Adicionando a oração atual ao array de orações
                //System.out.println(oracaoaux);
                //System.out.println(oracoes);
                oracaoaux = ""; //Limpando variável auxiliar
                i = j; //i começa a contar da onde o j parou
                if (i < entradas.length){//Coloquei esta condição para nunca tentar adicionar uma palavra a mais que o tamanho total do vetor de palavras.
                    oracaoaux += entradas[i] + " ";
                }
            }
            else
               oracaoaux += entradas[i] + " ";
        }
        return oracoes;
    }
    
    public static boolean verificaPontuacao(String entrada){
        String ponto = ". , ; : ? !";
        String[] pontos = ponto.split(" ");
        
        for(int i=0; i<pontos.length; i++){
            if (entrada.contains(pontos[i]))
                    return true;
        }
        
        return false;
    }
    
    public static boolean verificaverbo(String verbo){
        
        String terminacoes = "irmos;\n" +
"isse;\n" +
"isses;\n" +
"iste;\n" +
"istes;\n" +
"izar;\n" +
"itar;\n" +
"amos;\n" +
"ara;\n" +
"ará;\n" +
"aras;\n" +
"are;\n" +
"ares;\n" +
"emos;\n" +
"eou;\n" +
"era;\n" +
"eras;\n" +
"erá;\n" +
"erás;\n" +
"ere;\n" +
"eres;\n" +
"iam;\n" +
"íei;\n" +
"imos;\n" +
"ira;\n" +
"iras;\n" +
"irás;\n" +
"irá;\n" +
"ires;\n" +
"ído;\n" +
"ear;\n" +
"uei;\n" +
"uía;\n" +
"ai;\n" +
"am;\n" +
"ar;\n" +
"ei;\n" +
"eis;\n" +
"em;\n" +
"er;\n" +
"eu;\n" +
"ia;\n" +
"ir;\n" +
"ia;\n" +
"iu;\n" +
"ou;\n" +
"i;\n";
        
        String[] sufixos = terminacoes.split(";\n");
        
        for(int i=0; i<sufixos.length; i++){
            if (verbo.endsWith(sufixos[i]))
                return true;
        }
        return false;
    }
    
}
