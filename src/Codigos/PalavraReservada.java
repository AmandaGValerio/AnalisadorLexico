/**
 * 
 */
package Codigos;

import java.util.ArrayList;

/**
 * @author Amanda
 *
 * Esta classe simula uma estrutura fixa onde ficam armazenadas as palaras reservadas
 * por conveniencia e questões de processamento, estas palavras foram definidas no código
 * o que também torna um pouco mais próxima da ideia da estrutura original
 * Esta não é a estrutura mais economica em processamento, mas foi escolhida por questão de praticidade
 */
public class PalavraReservada {
	private ArrayList<String> palavras = new ArrayList<>();
	
	public PalavraReservada() {
		palavras.add("program");
		palavras.add("implicit");
		palavras.add("none");
		palavras.add("integer");
		palavras.add("complex");
		palavras.add("character");
		palavras.add("logical");
		palavras.add("read");
		palavras.add("print");
		palavras.add("if");
		palavras.add("then");
		palavras.add("else");
		palavras.add("end");
		palavras.add("go");
		palavras.add("endif");
		palavras.add("enddo");
		palavras.add("to");
		palavras.add("pause");
		palavras.add("parameter");
		palavras.add("while");
		palavras.add("do");
		palavras.add("call");
		palavras.add("subroutine");
		palavras.add("function");
		palavras.add("return");
		palavras.add(".eq.");
		palavras.add(".ne.");
		palavras.add(".lt.");
		palavras.add(".le.");
		palavras.add(".gt.");
		palavras.add(".ge.");
		palavras.add(".or.");
		palavras.add(".and.");
		palavras.add(".not.");
		palavras.add("+");
		palavras.add("-");
		palavras.add("*");
		palavras.add("/");
		palavras.add("**");
		palavras.add("(");
		palavras.add(")");
		palavras.add("\"");
		palavras.add("\"");
		palavras.add("//");
		palavras.add("!");
		palavras.add(".");
		palavras.add(",");
	}
	public ArrayList<String> getPalavras() {
		return palavras;
	}
	
	public boolean estaReservada(String token) {
		if (this.palavras.contains(token)) {
			return true;
		} 
		else {
			
			return false;
		}
	}
	
	public boolean simboloDispensaEspaço(char c) {
		
		return (c == '+' || c == '-' || c == '*' || c == '/' || c == '\\' || c == ',' || c =='(' || c == ')' || c == '=') ? true : false;
		
	}
	
}
