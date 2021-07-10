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
	public static int main(String[] args) throws FileNotFoundException {

		String escopoAtual = "global";
		String tokenLido = "";
		char caractere;
		
		//declaração da tabela de palavras reservadas
		PalavraReservada palavrasReservadas = new PalavraReservada();
		//criação da estrutura da tabela de simbolos
		ArrayList<Simbolos> TabelaSimbolos = new ArrayList<>();
		Simbolos simboloAux = new Simbolos();
		
		
		
		//definição do caminho
		String caminho = "C:\\Users\\Amanda\\Desktop\\Trabalho\\compiladores\\Analisador Lexico\\input1.txt";
		//leitura do arquivo
		FileInputStream stream;
		try {
			stream = new FileInputStream(caminho);
			/*InputStreamReader reader = new InputStreamReader(stream);
	        BufferedReader br = new BufferedReader(reader);
	        String linha = br.readLine();
	        while(linha != null) {
	        	//le e exibe o conteudo
	            System.out.println(linha);
	            linha = br.readLine();
	        }*/
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
	        		}while (caractere == '\n' && c != -1);
	        	}
	        	
	        	//pegar valor das strings
	        	else if (caractere == '"' && tokenLido == "") {
	        		//ao ler a primeira aspa dupla, que não esteja no meio do token,
	        		//continua a leitura até encontrar o fim da string
	        		do {
	        			caractere = (char) c;
	        			tokenLido = tokenLido + caractere;
	        			c = entradaFormatada.read();
	        			if (c == -1) {
	        				System.out.println("Erro encontrado!");
	        				return 0;
	        			}
	        		}while (caractere == '"');
	        		//adicionar na tabela
	        	}
	        	//separar os simbolos que podem ser identificados mesmo sem espaço
	        	else if(palavrasReservadas.simboloDispensaEspaço(caractere)) {
	        		//coloca o token na tabela e o simbolo também
	        	}
	        	//ler o token até cair em algo que faça parar
	        	else if (caractere == ' ' || caractere == '\n') {
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
						TabelaSimbolos.add(new Simbolos("", tokenLido, "", escopoAtual));
					}
					//verifica se é uma variável
					else if (tokenLido.matches("[a-zA-Z]+[a-zA-z0-9]*")){
						
					}
					//verifica se é um numeral valido
					else if (tokenLido.matches("^[+-]?(([0-9]*[\\.][0-9]+)|([0-9]+[\\.][0-9]*)|([0-9]+))")){

					}
					else {
						System.out.print("Erro encontrado próximo à " + tokenLido );
					}
				}
				else {
					tokenLido = tokenLido + c;
				}
				//le o proximo caractere
				c = entradaFormatada.read();
	        }
	        entradaFormatada.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		//exibir a tabela de simbolos
		int i = 0;
		for(Simbolos s : TabelaSimbolos){
			System.out.print("\n" + i);
			s.exibeSimbolo();
		}
		return 0;
		
		
		
		
	}

}
