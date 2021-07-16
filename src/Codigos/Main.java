/**
 * 
 */
package Codigos;

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
		String lexema = "";
		char caractere;
		
		//declaração da tabela de palavras reservadas
		PalavraReservada palavrasReservadas = new PalavraReservada();
		//criação da estrutura da tabela de simbolos
		ArrayList<Simbolos> TabelaSimbolos = new ArrayList<>();
		
		
		//definição do caminho
		String caminho = "input2.txt";
		//leitura do arquivo
		FileInputStream stream;
		try {
			stream = new FileInputStream(caminho);
	        InputStreamReader entradaFormatada = new InputStreamReader(stream);
	        int c = entradaFormatada.read();
	        while( c != -1){
	        	//leio o primeiro caractere
	        	caractere = (char) c;

	        	if (lexema == "" && caractere == '!') {
	        		//se corresponde à um comentário, continua a leitura até terminar e ignora
	        		do {
	        			caractere = (char) c;
	        			c = entradaFormatada.read();
	        		}while (caractere != '\n' && c != -1);
	        		lexema = "";
	        	}
	        	
	        	//pegar valor das strings
	        	else if (caractere == '"' && lexema == "") {
	        		//ao ler a primeira aspa dupla, que não esteja no meio do token,
	        		//continua a leitura até encontrar o fim da string
	        		caractere = (char) c;
	        		do {
	        			lexema = lexema + caractere;
	        			c = entradaFormatada.read();
	        			caractere = (char) c;
	        			if (c == -1) {
	        				//parar a execução
	        				System.out.println("Fim do arquivo");
	        				break;
	        			}
	        		}while (caractere != '"');
	        		//adicionar na tabela
	        		lexema = lexema + caractere;
	        		TabelaSimbolos.add(new Simbolos(lexema, "<String>", "", escopoAtual));
	        		lexema = "";
	        	}
	        	//separar os simbolos que podem ser identificados mesmo sem espaço
	        	//ex: var=num+num
	        	else if(palavrasReservadas.simboloDispensaEspaço(caractere)) {
	        		//coloca o token na tabela e o simbolo também
	        		//verifica se é variavel
	        		if (lexema.matches("[a-zA-Z]+[a-zA-z0-9]*")) {
	        			TabelaSimbolos.add(new Simbolos (lexema,"<ID>", "", escopoAtual));
	        		//verifica se é um numeral
	        		} else if (lexema.matches("^[+-]?(([0-9]*[\\.][0-9]+)|([0-9]+[\\.][0-9]*)|([0-9]+))")){
	        			TabelaSimbolos.add(new Simbolos (lexema, "<NUM>", "", escopoAtual));
	        		}
	        		//se é outro simbolo
	        		else if(palavrasReservadas.estaReservada(lexema)) {
	        			TabelaSimbolos.add(new Simbolos(lexema,"<OP>", "", escopoAtual));
	        		}
	        		//para demais tipos
	        		else {
	        			TabelaSimbolos.add(new Simbolos ( lexema, "<Unknow>", "", escopoAtual));
	        		}
	        		TabelaSimbolos.add( new Simbolos(String.valueOf(caractere), "<OP>", "", escopoAtual));
	        		lexema = "";
	        	}
	        	//ler o token até cair em algo que faça parar
	        	else if (caractere == ' ' || caractere == '\n' || caractere == '\t'){
	        		if (lexema != "") {
						//se for palavra reservada só guarda na tabela de simbolos
		        		if (palavrasReservadas.estaReservada(lexema)) {
							//verifica se existe alteração de escopo
							if (lexema == "subroutine" || lexema == "function") {
								if (TabelaSimbolos.get(TabelaSimbolos.size() -1).getToken() == "end") {
									escopoAtual = "global";
								}
								else {
									//salva o nome da subrotina
									escopoAtual = lexema;
								}
							}
							//guarda na tabela de simbolos
							TabelaSimbolos.add(new Simbolos(lexema,"<RESERVED>", "", escopoAtual));
							lexema = "";
						}
						//verifica se é uma variável
						else if (lexema.matches("[a-zA-Z]+[a-zA-z0-9]*")){
							TabelaSimbolos.add(new Simbolos(lexema, "<ID>", "", escopoAtual));
							lexema = "";
						}
						//verifica se é um numeral valido
						else if (lexema.matches("^[+-]?(([0-9]*[\\.][0-9]+)|([0-9]+[\\.][0-9]*)|([0-9]+))")){
							TabelaSimbolos.add( new Simbolos(lexema, "<NUM>", "", escopoAtual));
							lexema = "";
						}
						else {
	 						System.out.println("Erro encontrado próximo à " + lexema );
							lexema = "";
						}
	        		}
				}
				else {
					lexema = lexema + caractere;
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
