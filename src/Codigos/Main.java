/**
 * 
 */
package Codigos;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * @author Amanda
 *
 */
public class Main {

	/**
	 * @param args
	 */
	public static void main(String[] args) throws FileNotFoundException {

		//definição do caminho
		String caminho = "C:\\Users\\Amanda\\Desktop\\Trabalho\\compiladores\\Analisador Lexico\\input1.txt";
		//leitura do arquivo
		FileInputStream stream;
		try {
			stream = new FileInputStream(caminho);
			InputStreamReader reader = new InputStreamReader(stream);
	        BufferedReader br = new BufferedReader(reader);
	        String linha = br.readLine();
	        while(linha != null) {
	        	//le e exibe o conteudo
	            System.out.println(linha);
	            linha = br.readLine();
	        }
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
