package lab2;

public class Saude {
	
	/*
	 * Declaração de variáveis
	 * A Saúde Mental e Física do aluno são iniciadas como boas
	 */
	
	String saudeMental = "boa";
	String saudeFisica = "boa";
	
	/*
	 * Definição e criação do Método defineSaudeMental
	 * que altera a variável saudeMental
	 * O método não tem retorno
	 */

	public void defineSaudeMental(String valor) {
		saudeMental = valor;
	}
	
	/*
	 * Definição e criação do Método defineSaudeFisica
	 * que altera a variável saudeFisica
	 * O método não tem retorno
	 */
	
	public void defineSaudeFisica(String valor) {
		saudeFisica = valor;
	}
	
	/*
	 * Definição e criação do Método geral
	 * que retorna o valor da saúde geral do aluno
	 * de acordo com as especificações dadas
	 */
	
	public String geral() {
		if(saudeMental.equals("boa") && saudeFisica.equals("boa")) return "boa";
		else if(saudeMental.equals("fraca") && saudeFisica.equals("fraca")) return "fraca";
		else return "ok";	
	}
}