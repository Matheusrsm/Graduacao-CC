package lab2;

public class Disciplina {
	
	/*
	 * Declaração de variáveis
	 */
	
	String nomeDisciplina;
	int horas_total;
	double[] notas = new double[4];
	double media;
	
	/*
	 * Definição e criação do Construtor Disciplina
	 */
	
	public Disciplina(String nomeDisciplina) {
		this.nomeDisciplina = nomeDisciplina;
	}
	
	/*
	 * Definição e criação do Método cadastraHoras
	 * que aumenta a quantidade de horas estudadas
	 * O método não tem retorno
	 */
	
	public void cadastraHoras(int horas) {
		horas_total += horas;
	}
	
	/*
	 * Definição e criação do Método cadastraNota
	 * que adiciona no array notas o valor da nota passada
	 * O método não tem retorno
	 */
	
	public void cadastraNota(int nota, double valorNota) {
		notas[nota - 1] = valorNota;		
	}
	
	/*
	 * Definição e criação do Método calculaMedia
	 * que faz o cálculo da Média das notas passadas
	 * O método retorna o valor de media
	 */
	
	private double calculaMedia() {
		double soma = 0;
		for(int i = 0; i < 4; i++) {
			soma += notas[i];
		}
		return media = soma / 4;
	}
	
	/*
	 * Definição e criação do Método aprovado
	 * que retorna true ou false se foi aprovado ou não na disciplina
	 */

	public boolean aprovado() {
		if(calculaMedia() >= 7.0) return true;
		else return false;
	}
	
	/*
	 * Definição e criação do Método saida_array
	 * que retorna a string em formato de array
	 */
	
	private String saida_array() {
		return "[" + notas[0] + ", " + notas[1]+ ", " + notas[2]+ ", " + notas[3] + "]";
	}
	
	/*
	 * Sobrescritura do método toString
	 * Retorno da representação textual do objeto Disciplina
	 */
	
	@Override
	public String toString() {
		return nomeDisciplina + " " + horas_total + " " + calculaMedia() + " " + saida_array();
	}
}