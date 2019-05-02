package lab2;

public class ContaCantina {
	
	/*
	 * Declaração de variáveis
	 */
	
	String nomeDaCantina;
	int valor_conta;
	int quantidade;

	/*
	 * Definição e criação do Construtor ContaCantina
	 */
	
	public ContaCantina(String nomeDaCantina) {
		this.nomeDaCantina = nomeDaCantina;
	}
	
	/*
	 * Definição e criação do Método cadastraLanche
	 * que acrescenta a quantidade de Lanches e 
	 * acrescenta o valor do Lanche em centavos à conta
	 * O método não tem retorno
	 */
	
	public void cadastraLanche(int qtdItens, int valorCentavos) {
		quantidade += qtdItens;
		valor_conta += valorCentavos;
	}
	
	/*
	 * Definição e criação do Método pagaConta
	 * que retira da conta o valor pago em centavos
	 * O método não tem retorno
	 */
	
	public void pagaConta(int valorCentavos) {
		valor_conta -= valorCentavos;
	}
	
	/*
	 * Sobrescritura do método toString
	 * Retorno da representação textual do objeto ContaCantina
	 */
	
	@Override
	public String toString() {
		return nomeDaCantina + " " + quantidade + " " + valor_conta;
	}
}