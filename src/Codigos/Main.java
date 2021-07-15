/**
 * 
 */
package Codigos;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

/**
 * @author Amanda
 *
 */
public class Main {

	/**
	 * @param args
	 */
	public static void main(String[] args) throws FileNotFoundException {

		String escopoAtual = "global";
		String tokenLido = "";
		char caractere;
		
		//declaração da tabela de palavras reservadas
		PalavraReservada palavrasReservadas = new PalavraReservada();
		//criação da estrutura da tabela de simbolos
		ArrayList<Simbolos> TabelaSimbolos = new ArrayList<>();
		
		
		//definição do caminho
		String caminho = "C:\\Users\\Amanda\\Desktop\\Trabalho\\compiladores\\Analisador Lexico\\input1.txt";
		//leitura do arquivo
		FileInputStream stream;
		try {
			stream = new FileInputStream(caminho);
	        InputStreamReader entradaFormatada = new InputStreamReader(stream);
	        int c = entradaFormatada.read();
	        while( c != -1){
	        	//leio o primeiro caractere
	        	caractere = (char) c;

	        	if (tokenLido == "" && caractere == '!') {
	        		//se corresponde à um comentário, continua a leitura até terminar e ignora
	        		do {
	        			caractere = (char) c;
	        			c = entradaFormatada.read();
	        		}while (caractere != '\n' && c != -1);
	        		tokenLido = "";
	        	}
	        	
	        	//pegar valor das strings
	        	else if (caractere == '"' && tokenLido == "") {
	        		//ao ler a primeira aspa dupla, que não esteja no meio do token,
	        		//continua a leitura até encontrar o fim da string
	        		caractere = (char) c;
	        		do {
	        			tokenLido = tokenLido + caractere;
	        			c = entradaFormatada.read();
	        			caractere = (char) c;
	        			if (c == -1) {
	        				//parar a execução
	        				System.out.println("Fim do arquivo");
	        				break;
	        			}
	        		}while (caractere != '"');
	        		//adicionar na tabela
	        		tokenLido = tokenLido + caractere;
	        		TabelaSimbolos.add(new Simbolos("String", tokenLido, "", escopoAtual));
	        		tokenLido = "";
	        	}
	        	//separar os simbolos que podem ser identificados mesmo sem espaço
	        	//ex: var=num+num
	        	else if(palavrasReservadas.simboloDispensaEspaço(caractere)) {
	        		//coloca o token na tabela e o simbolo também
	        		//verifica se é variavel
	        		if (tokenLido.matches("[a-zA-Z]+[a-zA-z0-9]*")) {
	        			TabelaSimbolos.add(new Simbolos ("Variavel", tokenLido, "", escopoAtual));
	        		//verifica se é um numeral
	        		} else if (tokenLido.matches("^[+-]?(([0-9]*[\\.][0-9]+)|([0-9]+[\\.][0-9]*)|([0-9]+))")){
	        			TabelaSimbolos.add(new Simbolos ("Numeral", tokenLido, "", escopoAtual));
	        		}
	        		//se é outro simbolo
	        		else if(palavrasReservadas.estaReservada(tokenLido)) {
	        			TabelaSimbolos.add(new Simbolos("Palavra Reservada", tokenLido,"", escopoAtual));
	        		}
	        		//para demais tipos
	        		else {
	        			TabelaSimbolos.add(new Simbolos ("Unknow", tokenLido, "", escopoAtual));
	        		}
	        		TabelaSimbolos.add( new Simbolos("Palavra reservada", String.valueOf(caractere), "", escopoAtual));
	        		tokenLido = "";
	        	}
	        	//ler o token até cair em algo que faça parar
	        	else if (caractere == ' ' || caractere == '\n' || caractere == '\t'){
	        		if (tokenLido != "") {
						//se for palavra reservada só guarda na tabela de simbolos
		        		if (palavrasReservadas.estaReservada(tokenLido)) {
							//verifica se existe alteração de escopo
							if (tokenLido == "subrotine" || tokenLido == "function") {
								if (TabelaSimbolos.get(TabelaSimbolos.size() -1).getToken() == "end") {
									escopoAtual = "global";
								}
								else {
									//salva o nome da subrotina
									escopoAtual = tokenLido;
								}
							}
							//guarda na tabela de simbolos
							TabelaSimbolos.add(new Simbolos("Palavra Reservada", tokenLido, "", escopoAtual));
							tokenLido = "";
						}
						//verifica se é uma variável
						else if (tokenLido.matches("[a-zA-Z]+[a-zA-z0-9]*")){
							TabelaSimbolos.add(new Simbolos("Variavel", tokenLido, "", escopoAtual));
							tokenLido = "";
						}
						//verifica se é um numeral valido
						else if (tokenLido.matches("^[+-]?(([0-9]*[\\.][0-9]+)|([0-9]+[\\.][0-9]*)|([0-9]+))")){
							TabelaSimbolos.add( new Simbolos("Numeral", tokenLido, "", escopoAtual));
							tokenLido = "";
						}
						else {
	 						System.out.println("Erro encontrado próximo à " + tokenLido );
							tokenLido = "";
						}
	        		}
				}
				else {
					tokenLido = tokenLido + caractere;
				}
				//le o proximo caractere
				c = entradaFormatada.read();
	        }
	        entradaFormatada.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		System.out.println("------------------------------------");
		//exibir a tabela de simbolos
		int i = 0;
		for(Simbolos s : TabelaSimbolos){
			System.out.print("\n" + i + "   ");
			s.exibeSimbolo();
			i++;
		}
		
		
		
		
	}

}
