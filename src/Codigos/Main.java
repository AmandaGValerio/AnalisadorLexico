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

		ArrayList<Simbolos> TabelaSimbolos = new ArrayList<>();
		String escopoAtual = "global";
		String tokenLido = "";
		char caractere;
		ArrayList<String> PalavrasReservadas = new ArrayList<>();
		
		//preencher palavrasReservadas, por leitura de arquivo ou pelo código
		
		
		//definição do caminho
		String caminho = "C:\\Users\\Amanda\\Desktop\\Trabalho\\compiladores\\Analisador Lexico\\input1.txt";
		//leitura do arquivo
		FileInputStream stream;
		//ler palavras reservadas e armazenar em um vetor
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
	        while( c!=-1){
				//System.out.print( (char) c);
	        	caractere = (char) c;
	        	//ler o token até cair em algo que faça parar
				//ignorar espaços em branco e quebras de linha para as analises
				if (caractere == ' ' || caractere == '\n') {
					//se for palavra reservada só guarda na tabela de simbolos
					if (PalavrasReservadas.contains(tokenLido)) {
						
					}
					//se não, passar no AFD para ver se é id ou numero válido
						//se for ta ok
						//se não, exibe uma mensagem de erro com a falha sem parar a execução
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
		
		
		
		
	}

}
