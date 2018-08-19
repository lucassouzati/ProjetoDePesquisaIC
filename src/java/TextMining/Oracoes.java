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
            
           //if(entradas[i].contains(".") || entradas[i].contains(",")){//Verifica t�rmino de frase ou v�rgula
           if(verificaPontuacao(entradas[i])){
                oracaoaux += entradas[i] + " ";
                //oracao.setTipooracao("afirmativa"); // Por enquanto n�o estou classificando as oracoes
                //oracao.setOracao(oracaoaux); //Conte�do da ora��o
                Oracao oracao = new Oracao("afirmativa", oracaoaux);
                oracoes.add(oracao); //Adicionando a ora��o atual ao array de ora��es
                //System.out.println(oracaoaux);
                oracaoaux = "";
                
           }
           else if (verificaverbo(entradas[i])){ //Caso a palavra seja um verbo
                j = i;
                do{ //Vari�vel oracaoaux vai receber o restante das palavras ...
                    //System.out.println(entradas[j]);
                    oracaoaux += entradas[j] + " ";
                    j++;
                }while(!verificaverbo(entradas[j]) && !verificaPontuacao(entradas[j]) /*&& j <= entradas.length*/); // ... at� que ache outro verbo.
                if(verificaPontuacao(entradas[j]) && j <= entradas.length){//Caso haja uma pontua��o, esta palavra acoplada a pontua��o ainda faz parte da ora��o que estar a ser analisada.
                    oracaoaux += entradas[j] + " ";
                    j++;//Ent�o a pr�xima ora��o vai come�ar da palavra a partir da pontua��o
                }
                //oracao.setTipooracao("afirmativa"); // Por enquanto n�o estou classificando as oracoes
                //oracao.setOracao(oracaoaux); //Conte�do da ora��o
                Oracao oracao = new Oracao("afirmativa", oracaoaux); // Por enquanto n�o estou classificando as oracoes, e classificando todas como afirmativas.
                oracoes.add(oracao); //Adicionando a ora��o atual ao array de ora��es
                //System.out.println(oracaoaux);
                //System.out.println(oracoes);
                oracaoaux = ""; //Limpando vari�vel auxiliar
                i = j; //i come�a a contar da onde o j parou
                if (i < entradas.length){//Coloquei esta condi��o para nunca tentar adicionar uma palavra a mais que o tamanho total do vetor de palavras.
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
"ar�;\n" +
"aras;\n" +
"are;\n" +
"ares;\n" +
"emos;\n" +
"eou;\n" +
"era;\n" +
"eras;\n" +
"er�;\n" +
"er�s;\n" +
"ere;\n" +
"eres;\n" +
"iam;\n" +
"�ei;\n" +
"imos;\n" +
"ira;\n" +
"iras;\n" +
"ir�s;\n" +
"ir�;\n" +
"ires;\n" +
"�do;\n" +
"ear;\n" +
"uei;\n" +
"u�a;\n" +
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
