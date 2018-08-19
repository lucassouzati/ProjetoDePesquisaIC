/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package TextMining;

import dominio.*;
import conexao.*;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.util.PDFTextStripper;

/**
 *
 * @author lefoly
 */
public class Comparador {

    public static ArrayList<String> termosTempP = new ArrayList<String>();
    public static ArrayList<String> termosTempR = new ArrayList<String>();
    public static ArrayList<String> conceitosTempP = new ArrayList<String>();
    public static ArrayList<String> conceitosTempR = new ArrayList<String>();
    public static ArrayList<String> stemsRelevantesP = new ArrayList<String>();
    public static ArrayList<String> stemsRelevantesR = new ArrayList<String>();
    public static ArrayList stemsFreqP = new ArrayList();
    public static ArrayList stemsFreqR = new ArrayList();
    public static float resultado;

    //Mudan�a no c�digo para o SCTI - 20-09-2015
    public static ArrayList<Palavra> Palavras = new ArrayList<Palavra>();
    public static ArrayList<Palavra> palavrasImportantes = new ArrayList<Palavra>();

    public static ArrayList<Palavra> palavrasTexto1 = new ArrayList<Palavra>();
    public static ArrayList<Palavra> palavrasTexto2 = new ArrayList<Palavra>();
    public static Double frequenciaGeral = 0D;
    public static Double resultadoPreciso = 0D;

    //Mudan�a no c�digo para COTB 2016
    private static ArrayList<Palavra> termos = new ArrayList<Palavra>();
    private static ArrayList<Palavra> termosConceito = new ArrayList<Palavra>();
    private static ArrayList<Palavra> termosPergunta = new ArrayList<Palavra>();
    private static ArrayList<Palavra> adverbiosConceito = new ArrayList<Palavra>();
    private static ArrayList<Palavra> adverbiosResposta = new ArrayList<Palavra>();
    ;
    private static ArrayList<Palavra> termosResposta = new ArrayList<Palavra>();

    ;
    

    public static void obterTermosRelevantes(String origem, int idDisciplina, String sentenca) throws SQLException {
        sentenca = sentenca.toLowerCase();
        TermoDAO dao = new TermoDAOImp();
        List<Termo> termos = dao.getListaFiltrada(idDisciplina);
        for (int i = 0; i <= termos.size() - 1; i++) {
            Termo termo = termos.get(i);
            String termoDesc = Minerador.Radicalizar(termo.getNome().toLowerCase());
            //System.out.println(termo.getNome().toLowerCase() + " -> " + termoDesc);
            if (sentenca.contains(termoDesc)) {
                //if (origem.equals("Pergunta")) {
                //    termosTempP.add(termoDesc);
                //} else if (origem.equals("Resposta")) {
                //    termosTempR.add(termoDesc);
                //}
                obterConceitosRelevantes(origem, termo.getID());
            }
        }
        //System.out.println("Total termosTempP: " + termosTempP.size());
    }

    public static void obterConceitosRelevantes(String origem, int idTermo) throws SQLException {
        ConceitoDAO dao = new ConceitoDAOImp();
        List<Conceito> conceitos = dao.getListaFiltrada(idTermo);
        if (conceitos.size() > 0) {
            String[] conceitosDesc = Minerador.processar(conceitos.get(0).getDescricao()).split(" ");
            if (origem.equals("Pergunta")) {
                for (int i = 0; i <= conceitosDesc.length - 1; i++) {
                    conceitosTempP.add(conceitosDesc[i]);
                }
            } else if (origem.equals("Resposta")) {
                for (int i = 0; i <= conceitosDesc.length - 1; i++) {
                    conceitosTempR.add(conceitosDesc[i]);
                }
            }
        }
    }

    public static void obterFrequenciaTermos(String origem) {
        int frequencia, i, tamanho;
        if (origem.equals("Pergunta")) {
            Collections.sort(termosTempP);
            i = 0;
            String termo;
            tamanho = termosTempP.size();
            //System.out.println("Tamanho do termosTempP: " + tamanho);
            while (i < tamanho) {
                frequencia = 0;
                termo = termosTempP.get(i);
                stemsRelevantesP.add(termo);
                while ((i < tamanho) && (termo.equals(termosTempP.get(i)))) {
                    frequencia++;
                    i++;
                }
                stemsFreqP.add(frequencia);
            }
        } else if (origem.equals("Resposta")) {
            Collections.sort(termosTempR);
            i = 0;
            String termo;
            tamanho = termosTempR.size();
            //System.out.println("Tamanho do termosTempR: " + tamanho);
            while (i < tamanho) {
                frequencia = 0;
                termo = termosTempR.get(i);
                stemsRelevantesR.add(termo);
                while ((i < tamanho) && (termo.equals(termosTempR.get(i)))) {
                    frequencia++;
                    i++;
                }
                stemsFreqR.add(frequencia);
            }
        }
    }

    public static void obterFrequenciaConceitos(String origem) {
        int frequencia, i, tamanho;
        if (origem.equals("Pergunta")) {
            Collections.sort(conceitosTempP);
            i = 0;
            String conceito;
            tamanho = conceitosTempP.size();
            while (i < tamanho) {
                frequencia = 0;
                conceito = conceitosTempP.get(i);
                stemsRelevantesP.add(conceito);
                while ((i < tamanho) && (conceito.equals(conceitosTempP.get(i)))) {
                    frequencia++;
                    i++;
                }
                stemsFreqP.add(frequencia);
            }
        } else if (origem.equals("Resposta")) {
            Collections.sort(conceitosTempR);
            i = 0;
            String conceito;
            tamanho = conceitosTempR.size();
            while (i < tamanho) {
                frequencia = 0;
                conceito = conceitosTempR.get(i);
                stemsRelevantesR.add(conceito);
                while ((i < tamanho) && (conceito.equals(conceitosTempR.get(i)))) {
                    frequencia++;
                    i++;
                }
                stemsFreqR.add(frequencia);
            }
        }
    }

    public static void main(String args[]) throws SQLException, IOException {
        String pergTeste = "Conceitue: objetos, atributo, diagrama de componentes e caso de uso:";
        String respTeste = "Objeto � a inst�ncia de uma classe. "
                + "Atributo � uma caracter�stica que os objetos possuem. "
                + "Diagrama de Componentes mostra a intera��o entre os componentes em um sistema. "
                + "E o caso de uso mostra a a��o de um ator dentro de um sistema.";
        String pergTeste2 = "O que � um objeto?";
        String respTeste2 = "Desta forma que organizamos a classe num sistema.";

        String pergTeste3 = "Explique a utilidade do Diagrama de Classes no processo de Projeto:";
        String respTeste3 = "O diagrama de classes mostra as classes, n�o somente conceituais, mas tamb�m as do software.";
        //String respTeste3 = "O diagrama de classes.";

        String pergTeste4 = "Conceitue Requisitos de Dom�nio:";
        String respTeste4 = "S�o caracter�sticas que refletem um dado dom�nio da aplica��o. Considere um sistema de solu��o de sistemas lineares, pode haver o requisito de solu��o do sistema atrav�s de um m�todo espec�fico, como Gauss-Seidel.";

        String pergTeste5 = "O que � Com�rcio Eletr�nico";
        String respTeste5 = "Pode-se dizer que n�o envolve qualquer transa��o eletronica de neg�cios eletronica eletronica eletronica.";

        String pergTeste6 = "O que � um atributo?";
        String respTeste6 = "S�o as caracter�sticas de objetos e classes.";
        
        String pergTeste7 = "O que s�o Casos de Uso?";
        String respTeste7 = "� um tipo de classificador representando uma unidade funcional coerente provida pelo sistema, subsistema, ou classe manifestada por seq��ncias de mensagens intercambi�veis entre os sistemas e um ou mais atores.";
        
        String pergTeste8 = "O que � Diagrama de Classes?";
        String respTeste8 = "Representa��o da UML atrav�s de diagramas para representar classes de um Projeto Orientado a Objetos.";
                             
        
        System.out.println(Etiquetador.processaTexto(pergTeste8));
        System.out.println(Etiquetador.processaTexto(respTeste8));
                
         //TESTES COM A AFERI��O DE CONHECIMENTO - IN�CIO
        
         Processar(pergTeste8, respTeste8, 1);
         ListarTudo();
        
         System.out.printf("\nO aluno acertou %.2f%% da quest�o.\n", resultado);
         System.out.printf("A nota sugerida| �: %.1f\n\n", resultado/10);
        
         processarPorEtiquetador(pergTeste8, respTeste8, 1);
        
         System.out.printf("\nO aluno acertou %.2f%% da quest�o.\n", resultado);
         System.out.printf("A nota sugerida| �: %.1f\n\n", resultado/10);
         
        //TESTES COM A AFERI��O DE CONHECIMENTO - FIM
        /* - Testando a nega��o
         System.out.println(Etiquetador.processaTexto("O projeto de pesquisa de forma alguma est� em desenvolvimento."));
         if (Etiquetador.verificaNegacao()){
         System.out.println("\n � nega��o");
         }
         else
         System.out.println("\n N�o � nega��o.");
         System.out.println("------------------------------------------------");
         System.out.println(Etiquetador.processaTexto("Por enquanto, de jeito algum chegamos aos resultados esperados."));
        
         if (Etiquetador.verificaNegacao()){
         System.out.println("\n � nega��o");
         }
         else
         System.out.println("\n N�o � nega��o.");
         */
        /* Extraindo texto de pdf */
        //TESTES COM A COMPARA��O DE TEXTOS - IN�CIO
        /*
        PDDocument documento = new PDDocument();
        documento.load("0051.pdf");

        PDFTextStripper stripper = new PDFTextStripper();
       // stripper.setStartPage( 2 );
        // stripper.setEndPage( 3 );
        //;
        String texto1 = "A virtualiza��o permite que em uma mesma m�quina sejam executadas simultaneamente dois ou mais ambientes distintos e isolados. Esse conceito de virtualiza��o remonta aos antigos mainframes, que deviam ser divididos por v�rios usu�rios em ambientes de aplica��o completamente diferentes. Essa realidade da d�cada de 1970 foi em grande parte superada nos anos de 1980 e 1990, com o surgimento dos computadores pessoais. No entanto, atualmente h� uma onda crescente de interesse sobre as t�cnicas de virtualiza��o. Os primeiros computadores que surgiram eram gigantescos e muito caros. No entanto, devido � grande demanda por uso, estes rapidamente se tornaram indispens�veis. Para socializar o uso dos computadores foi criado, no final dos anos 1960, o time-sharing, que permitia o uso de um mesmo computador por v�rios usu�rios simultaneamente de forma transparente. Embora este tenhasido um grande passo na hist�ria da computa��o, surgia assim um novo problema, o compartilhamento de um �nico computador com outras aplica��es suscet�veis a falhas. A fim de sanar esse problema, a primeira solu��o proposta foi o uso de v�rios computadores, o que se reverteria em um aumento significativo do desempenho e na garantia de isolamento entre as aplica��es. Entretanto esta solu��o apresentava um alt�ssimo custo, al�m de ser um desperd�cio de recursos, j� que os computadores ficavam grande parte do tempo ociosos. Tendo isto em vista, nos anos 60 a IBM come�ou a desenvolver a primeira m�quina virtual, que permitia que um �nico computador fosse dividido em v�rios. O primeiro sistema de virtualiza��o desenvolvido foi o CP-67, software para o mainframe IBM 360/67, que disponibilizava ao usu�rio um sistema virtual do /360 da IBM. Os resultados obtidos com esse sistema foram �timos.";//stripper.getText(documento.load("0051.pdf"));

        //String texto2 = "O artigo aborda de forma geral o conceito de virtualiza��o, apresentando como ela pode atuar como uma poss�vel solu��o de baixo custo para obter confiabilidade, isolamento e escalabilidade a alguns sistemas, e comparando dois tipos de virtualiza��o: a completa, representada neste estudo pelo VMWare, e a para-virtualiza��o, representada pelo Xen. 	O conceito de virtualiza��o come�ou a existir no final da d�cada de 60, na qual os computadores eram gigantescos e caros, e na tentativa de socializar o uso deles foi criado o time-sharing, que permite o uso de um computador por v�rias pessoas. Mas esta solu��o ainda teve falhas em algumas aplica��es, e a IBM a fim de sanar esse problema come�ou a desenvolver a primeira m�quina virtual, que permitia um �nico computador ser dividido em v�rios. Atualmente, com o advento dos computadores pessoais usando a arquitetura x86, a qual n�o foi projetada considerando a virtualiza��o, se tornou dif�cil projetar m�quinas virtuais. Mas algumas t�cnicas podem romper essa dificuldade, ent�o o VMWare e o Xen s�o propostos para isso. 	Entre os conceitos relacionados a sistemas operacionais, primeiramente podemos citar o de instru��es privilegiadas e n�o privilegiadas. Considerando a arquitetura x86, as privilegiadas s�o aquelas que conseguem modificar aloca��o ou estado de recursos como o processador e mem�ria. J� as n�o privilegiadas n�o podem. Tamb�m se pode destacar o modo de opera��o do computador, em que pode ser dois distintos: modo de usu�rio e o de supervisor. O primeiro, � onde as aplica��es s�o normalmente executadas, mas atrav�s de instru��es n�o privilegiadas. O segundo, tem controle total da CPU, e � nesse modo onde o sistema operacional atua. Especialmente na arquitetura x86, existem quatro n�veis de privil�gios, que s�o chamados de rings. 	Agora em um ambiente virtualizado, temos mais dois conceitos: o sistema operacional hospedeiro, e o sistema operacional visitante. O Virtual Machine Monitor atua em modo supervisor e � o respons�vel pela virtualiza��o do sistema visitante, este atuando em modo usu�rio. 	Com os principais conceitos explicitados, pode-se comparar os dois tipos de virtualiza��o. A virtualiza��o total fornece ao SO visitante uma r�plica do hardware subjacente. Desta forma, a virtualiza��o usa dispositivos gen�ricos n�o garantindo o uso total da capacidade. O resultado disso � uma perca significativa de desempenho. A para-virtualiza��o contorna este problema modificando o SO visitante para solicitar ao VMM sempre que executar uma instru��o privilegiada, acabando com a necessidade do VMM testar instru��o por instru��o como era feita na virtualiza��o total. 	Apesar das vantagens da para-virtualiza��o, a virtualiza��o total (representada pelo VMWare) teve melhores resultados nos testes realizados nesse estudo. As configura��es das m�quinas eram diferentes, por�m todas davam suporte de hardware a virtualiza��o. O Xen, representante da para-virtualiza��o, se saiu melhor em alguns aspectos, por�m n�o foram resultados significativos. 	Um dos pontos que est� fazendo a virtualiza��o voltar a ter sua import�ncia � o aumento expressivo do poder computacional que n�o � seguido por sua taxa de utiliza��o, deixando assim muitos recursos ociosos. Ela ainda tem suas falhas e uma aplica��o radical da mesma precisa ser em longo prazo, pois leva muito fatores em conta.";
        String texto2 = "A virtualiza��o permite que em uma mesma m�quina sejam executadas simultaneamente dois ou mais ambientes distintos e isolados. Esse conceito de virtualiza��o remonta aos antigos mainframes, que deviam ser divididos por v�rios usu�rios em ambientes de aplica��o completamente diferentes. Essa realidade da d�cada de 1970 foi em grande parte superada nos anos de 1980 e 1990, com o surgimento dos computadores pessoais. No entanto, atualmente h� uma onda crescente de interesse sobre as t�cnicas de virtualiza��o. Os primeiros computadores que surgiram eram gigantescos e muito caros. No entanto, devido � grande demanda por uso, estes rapidamente se tornaram indispens�veis. Para socializar o uso dos computadores foi criado, no final dos anos 1960, o time-sharing, que permitia o uso de um mesmo computador por v�rios usu�rios simultaneamente de forma transparente. Embora este tenhasido um grande passo na hist�ria da computa��o, surgia assim um novo problema, o compartilhamento de um �nico computador com outras aplica��es suscet�veis a falhas. A fim de sanar esse problema, a primeira solu��o proposta foi o uso de v�rios computadores, o que se reverteria em um aumento significativo do desempenho e na garantia de isolamento entre as aplica��es. Entretanto esta solu��o apresentava um alt�ssimo custo, al�m de ser um desperd�cio de recursos, j� que os computadores ficavam grande parte do tempo ociosos. Tendo isto em vista, nos anos 60 a IBM come�ou a desenvolver a primeira m�quina virtual, que permitia que um �nico computador fosse dividido em v�rios. O primeiro sistema de virtualiza��o desenvolvido foi o CP-67, software para o mainframe IBM 360/67, que disponibilizava ao usu�rio um sistema virtual do /360 da IBM. Os resultados obtidos com esse sistema foram �timos. Ap�s o CP-67, a IBM lan�ou o VM/370, um VMM (Virtual Machine Monitor, ou Monitor de M�quina Virtual) para o Sistema /370 com arquitetura estendida, ou seja, com algumas instru��es extras que permitiam a virtualiza��o. Essas foram as primeiras tentativas de virtualiza��o. Em um cen�rio mais atual, a arquitetura mais comum � a x86 (IA-32). Essa � a arquitetura adotada pelos PCs, que se tornaram commodities. Ao contr�rio da arquitetura dos antigos sistemas /370 com arquitetura estendida, que apresentavam instru��es que visavam a virtualiza��o, a arquitetura x86 n�o foi projetada considerando a virtualiza��o.";
        //System.out.println(Etiquetador.processaTexto(stripper.getText(documento.load("0051.pdf"))));

        //Palavras = Etiquetador.getPalavras();
        // Identificando preposi��es e termos compostos
        //System.out.println(Etiquetador.processaTexto("Jo�o Silva foi � faculdade ontem."));
        
        
        
        comparaTextos(texto1, texto2);
        System.out.println("A abrag�ncia de um texto no outro �:" + resultadoPreciso);
        //Essa string contem varios termpos compostos, como Sistemas de Informa��o, Engenharia de Software, engenharia Computacional, An�lise Orientada a Objetos.
        //System.out.println(Etiquetador.verificaTermosCompostos());
        */
        //TESTES COM A COMPARA��O DE TEXTOS - FIM
    }

    public static void ordenaPorFrequencia() {
        Palavra palavraux = new Palavra();
        int maiorfrequencia = 0;
        for (int i = 0; i < Palavras.size(); i++) {
            if (i == 0) {
                maiorfrequencia = Palavras.get(i).getFrequencia();
            } else {
                if (Palavras.get(i).getFrequencia() > maiorfrequencia);
            }
        }
    }

    public static float Processar(String pergunta, String resposta, int disciplina) throws SQLException {
        termosTempP.clear();
        termosTempR.clear();
        conceitosTempP.clear();
        conceitosTempR.clear();
        stemsRelevantesP.clear();
        stemsRelevantesR.clear();
        stemsFreqP.clear();
        stemsFreqR.clear();
        resultado = 0;

        //int disciplina = 2;
        obterTermosRelevantes("Pergunta", disciplina, pergunta);
        obterFrequenciaTermos("Pergunta");
        obterFrequenciaConceitos("Pergunta");

        //obterTermosRelevantes("Resposta", disciplina, resposta);
        //esse � o novo obterTErmosRelevantes("Resposta")
        String respostaNova = Minerador.processar(resposta);
        String respostaNovaV[] = respostaNova.split("\\s");
        for (int i = 0; i <= respostaNovaV.length - 1; i++) {
            //stemsRelevantesR.add(respostaNovaV[i]);
            termosTempR.add(respostaNovaV[i]);
        }
        obterFrequenciaTermos("Resposta");
        //obterFrequenciaConceitos("Resposta");

        Comparar();

        return resultado;
    }

    private static void ListarTudo() {
        System.out.println("Pergunta");
        for (int i = 0; i < stemsRelevantesP.size(); i++) {
            System.out.println("      " + stemsRelevantesP.get(i) + " = " + stemsFreqP.get(i));
        }
        System.out.println("-----------------------------------");
        System.out.println("Resposta");
        for (int i = 0; i < stemsRelevantesR.size(); i++) {
            System.out.println("      " + stemsRelevantesR.get(i) + " = " + stemsFreqR.get(i));
        }
    }

    private static void Comparar() {

        ArrayList<String> termosEspelho = new ArrayList<String>();
        ArrayList<String> conceitosEspelho = new ArrayList<String>();
        ArrayList termosFreqEspelho = new ArrayList();
        ArrayList conceitosFreqEspelho = new ArrayList();
        int tamanhoP, tamanhoR, tamanhoE;

        tamanhoP = stemsRelevantesP.size();
        tamanhoR = stemsRelevantesR.size();

        System.out.println("tamanhoP = " + tamanhoP);
        System.out.println("tamanhoR = " + tamanhoR);

        for (int i = 0; i <= tamanhoP - 1; i++) {
            String termo = stemsRelevantesP.get(i);
            Object freq = stemsFreqP.get(i);
            int j = 0;
            if (tamanhoR > 0) {
                while ((j < tamanhoR - 1) && (!stemsRelevantesR.get(j).equals(termo))) {
                    j++;
                }
                if (stemsRelevantesR.get(j).equals(termo)) {
                    termosEspelho.add(termo);
                    termosFreqEspelho.add(freq);
                }
            }
        }
        tamanhoE = termosEspelho.size();
        float somaPergunta = 0, somaEspelho = 0;
        for (int i = 0; i <= tamanhoP - 1; i++) {
            somaPergunta = somaPergunta + Integer.parseInt(stemsFreqP.get(i).toString());
        }
        for (int i = 0; i <= tamanhoE - 1; i++) {
            somaEspelho = somaEspelho + Integer.parseInt(termosFreqEspelho.get(i).toString());
        }
        System.out.println(somaEspelho);
        System.out.println(somaPergunta);
        resultado = (somaEspelho / somaPergunta) * 100;
    }

    public static String processarPorEtiquetador(String pergunta, String resposta, int disciplina) throws IOException, SQLException {
        String retorno = "";
        termosTempP.clear();
        termosTempR.clear();
        conceitosTempP.clear();
        conceitosTempR.clear();
        stemsRelevantesP.clear();
        stemsRelevantesR.clear();
        stemsFreqP.clear();
        stemsFreqR.clear();
        resultado = 0;

        termos.clear();
        termosResposta.clear();
        termosConceito.clear();
        termosPergunta.clear();
        adverbiosConceito.clear();
        adverbiosResposta.clear();
        
        
        boolean negacaoConceito = false;
        boolean negacaoResposta = false;

        //Pegando Palavras importantes na pergunta e na resposta
        Etiquetador.processaTexto(pergunta);
        termosPergunta.addAll(Etiquetador.getPalavrasImportantes());
       // System.out.println(termosPergunta.toString());

        Etiquetador.processaTexto(resposta);
        termosResposta.addAll(Etiquetador.getPalavrasImportantes());
        
        ArrayList<String> radicaisResposta = new ArrayList<String>();
        /*for(int i = 0; i<termosResposta.size(); i++){
            for(int j = 0; j < termosResposta.size(); j++){
                if(termosResposta.get(i).getRadical().equals(termosResposta.get(j).getRadical())){
                    
                }
            }
        }*/
        
        //Agrupando os radicais para evitar repeti��es na hora de comparar
        for(int i = 0; i<termosResposta.size(); i++){
            if(i == 0){
                radicaisResposta.add(termosResposta.get(i).getRadical());
            }
            else{
                if (!radicaisResposta.contains(termosResposta.get(i).getRadical()))
                    radicaisResposta.add(termosResposta.get(i).getRadical());
                
            }
        }
        
        adverbiosResposta.addAll(Etiquetador.getAdverbios());
        //System.out.println(termosResposta.toString());
        
        System.out.println(adverbiosResposta);
        
        for (int i =0; i<adverbiosResposta.size(); i++){
            if (adverbiosResposta.get(i).getDescricao().equalsIgnoreCase("n�o"))
                negacaoResposta = true;
        }
        
        //Pega lista de termos da disciplina
        TermoDAO termodao = new TermoDAOImp();
        List<Termo> termosbase = termodao.getListaFiltrada(disciplina);
        //System.out.println(termosbase.toString());
        //Filtra termos da pergunta  de acordo com os termos encontrados

        for (int i = 0; i < termosPergunta.size(); i++) {
            for (int j = 0; j < termosbase.size(); j++) {
                //System.out.println(termosbase.get(i).getNome() + " = " + termosPergunta.get(j).getDescricao() + "\n");
                //if (termosbase.get(j).getNome().equalsIgnoreCase(termosPergunta.get(i).getDescricao())) {
                if (Minerador.RadicalizarFrase(termosbase.get(j).getNome()).equalsIgnoreCase(Minerador.RadicalizarFrase(termosPergunta.get(i).getDescricao()))) {
                    termosPergunta.get(i).setId(termosbase.get(j).getID());
                    termosConceito.add(termosPergunta.get(i));
                }
            }
        }

        //Pega todos conceitos encotrados
        //System.out.println(termosConceito.toString());
        List<Conceito> conceitos = new ArrayList<Conceito>();
        ConceitoDAO dao = new ConceitoDAOImp();
        if (!termosConceito.isEmpty()) {
            for (int i = 0; i < termosConceito.size(); i++) {
                conceitos.addAll(dao.getListaFiltrada(termosConceito.get(i).getId()));
            }

        }
        System.out.println(conceitos.toString());
        //Retirando palavras importantes dos conceitos

        ArrayList<Palavra> palavrasImportantesConceitos = new ArrayList<Palavra>();
        String conceitosPalavras = "";
        for (int i = 0; i < conceitos.size(); i++) {
            conceitosPalavras += conceitos.get(i).getDescricao() + " ";
        }
        if(!conceitosPalavras.isEmpty()){
            Etiquetador.processaTexto(conceitosPalavras);
            palavrasImportantesConceitos.addAll(Etiquetador.getPalavrasImportantes());
            
             //Agrupando os radicais para evitar repeti��es na hora de comparar
            
             ArrayList<String> radicaisConceito = new ArrayList<String>();
            for(int i = 0; i<palavrasImportantesConceitos.size(); i++){
                if(i == 0){
                    radicaisConceito.add(palavrasImportantesConceitos.get(i).getRadical());
                }
                else{
                    if (!radicaisConceito.contains(palavrasImportantesConceitos.get(i).getRadical()))
                        radicaisConceito.add(palavrasImportantesConceitos.get(i).getRadical());

                }
            }
            
            adverbiosConceito.addAll(Etiquetador.getAdverbios());

            System.out.println(adverbiosConceito);

            for (int i =0; i<adverbiosConceito.size(); i++){
                if (adverbiosConceito.get(i).getDescricao().equalsIgnoreCase("n�o"))
                    negacaoConceito = true;
            }

            Float nota = 0F;
            Float pontos = 0F;

            //System.out.println(termosConceito.toString());
            System.out.println("Conceitos importantes encontrados: \n" + palavrasImportantesConceitos.toString());
            System.out.println("Palavras importantes encontradas na resposta: \n" + termosResposta.toString());
            
            
            
            
           /* 

            for (int i = 0; i < termosResposta.size(); i++) {
                for (int j = 0; j < palavrasImportantesConceitos.size(); j++) {
                    //System.out.println(termosResposta.get(i).getDescricao() + " = " + termosConceito.get(j).getDescricao() + "\n");    
                    if (termosResposta.get(i).getRadical().equalsIgnoreCase(palavrasImportantesConceitos.get(j).getRadical()) && !termosResposta.get(i).getDescricao().equalsIgnoreCase(palavrasImportantesConceitos.get(j).getDescricao())) {

                        pontos++;
                    }
                }
            }
            */
            
                       
            //Comparando atrav�s dos radicais
            
            for (int i = 0; i < radicaisResposta.size(); i++) {
                for (int j = 0; j < radicaisConceito.size(); j++) {
                    //System.out.println(radicaisResposta.get(i) + " = " + radicaisConceito.get(j) + "\n");    
                    if (radicaisResposta.get(i).equalsIgnoreCase(radicaisConceito.get(j))) {
                        
                        pontos++;
                        
                    }
                }
            }
            
            

            if(negacaoConceito == negacaoResposta)
                resultado = (pontos / radicaisConceito.size()) * 100;
            else
                resultado = 0;
        }
        /*
         if(!termosbase.isEmpty()){
         for(int i=0; i< termosbase.size(); i++){
         ConceitoDAO dao = new ConceitoDAOImp();
         conceitos.addAll(dao.getListaFiltrada(termosbase.get(i).getID()));
         }
            
         }*/
        return retorno;
    }

    public static void obterRelevancias() {

        for (int i = 0; i < palavrasTexto1.size(); i++) {
            frequenciaGeral += palavrasTexto1.get(i).getFrequencia();
        }
        for (int i = 0; i < palavrasTexto1.size(); i++) {
            palavrasTexto1.get(i).setRelevancia(palavrasTexto1.get(i).getFrequencia() / frequenciaGeral);
        }

    }

    public static void comparaTextos(String texto1, String texto2) throws IOException {
        Palavras.clear();
        palavrasImportantes.clear();
        palavrasTexto1.clear();
        palavrasTexto2.clear();
        resultado = 0;
        frequenciaGeral = 0D;
        Double pontos = 0D;

        Etiquetador.processaTexto(texto1);
        palavrasTexto1.addAll(Etiquetador.getPalavrasImportantes());
        

        Etiquetador.processaTexto(texto2);
        palavrasTexto2.addAll(Etiquetador.getPalavrasImportantes());
        

        obterRelevancias();

        //System.out.println(palavrasTexto1);
        System.out.println("A frequencia geral das palavras �: " + frequenciaGeral + "\n");

        for (int i = 0; i < palavrasTexto1.size(); i++) {
            for (int j = 0; j < palavrasTexto2.size(); j++) {
                //      System.out.println(palavrasTexto1.get(i).getDescricao() + " = " + palavrasTexto2.get(j).getDescricao() + "\n");    
                if (palavrasTexto1.get(i).getRadical().equalsIgnoreCase(palavrasTexto2.get(j).getRadical())) {

                    //pontos++;
                    pontos += palavrasTexto1.get(i).getRelevancia();
                    //System.out.println("Pontos: " + pontos + "\n");
                }
            }
        }
        resultadoPreciso = (pontos) * 100;

    }

}
