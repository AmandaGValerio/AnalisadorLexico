/**
 * 
 */
package Codigos;

/**
 * @author Amanda
 *
 */
public class Simbolos {

	//private int Posicao; //posição é o indice no array da tabela
	private String Lexema;
	private String Token;
	private String Valor;
	private String scopo; //global ou nome da subrotina
	private int[] tamanho = new int[2];
	
	public Simbolos() {	}

	public Simbolos(String lexema, String token, String valor, String scopo) {
		//Posicao = posicao;
		Lexema = lexema;
		Token = token;
		Valor = valor;
		this.scopo = scopo;
	}
	
	public Simbolos(String lexema, String token, String valor, String scopo, int linhas, int colunas) {
		Lexema = lexema;
		Token = token;
		Valor = valor;
		this.scopo = scopo;
		this.tamanho[0] = linhas;
		this.tamanho[1] = colunas;
	}

	public String getLexema() {
		return Lexema;
	}
	public void setLexema(String lexema) {
		Lexema = lexema;
	}
	public String getToken() {
		return Token;
	}
	public void setToken(String token) {
		Token = token;
	}
	public String getValor() {
		return Valor;
	}
	public void setValor(String valor) {
		Valor = valor;
	}

	public String getScopo() {
		return scopo;
	}

	public void setScopo(String scopo) {
		this.scopo = scopo;
	}
	
	public void exibeSimbolo() {
		System.out.println(this.Lexema + "    " + this.Token + "    " + this.scopo);
	}

	
	
}
