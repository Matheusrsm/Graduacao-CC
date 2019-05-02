package lab2;

public class ContaLaboratorio {
	
	/*
	 * Declaração de variáveis
	 */
	
	String nomeLaboratorio;
	int cota;
	int espaco_ocupado;
	
	/*
	 * Definição e criação do Construtor ContaLaboratorio
	 * com apenas nomeLaboratorio como parâmetro
	 */
	
	public ContaLaboratorio(String nomeLaboratorio) {
		this.nomeLaboratorio = nomeLaboratorio;
		this.cota = 2000;
	}
	
	/*
	 * Definição e criação do Construtor ContaLaboratorio
	 * com nomeLaboratorio e cota como parâmetros
	 */
	
	public ContaLaboratorio(String nomeLaboratorio, int cota) {
		this.nomeLaboratorio = nomeLaboratorio;
		this.cota = cota;
	}
	
	/*
	 * Definição e criação do Método consomeEspaco
	 * que aumenta o espaço ocupado com o valor passado
	 * O método não tem retorno
	 */
	
	public void	consomeEspaco(int mbytes) {
		espaco_ocupado += mbytes;
	}
	
	/*
	 * Definição e criação do Método liberaEspaco
	 * que diminui o espaço ocupado com o valor passado
	 * O método não tem retorno
	 */
	
	public void liberaEspaco(int mbytes) {
		espaco_ocupado -= mbytes;
	}
	
	/*
	 * Definição e criação do Método atingiuCota
	 * que retorna true ou false se atinigiu o limite 
	 * de espaço ocupado
	 */
	
	public boolean atingiuCota() {
		if(espaco_ocupado >= cota) return true;
		else return false;
	}
	
	/*
	 * Sobrescritura do método toString
	 * Retorno da representação textual do objeto ContaLaboratorio
	 */
	
	@Override	
	public String toString() {
		return nomeLaboratorio + " " + espaco_ocupado + "/" + cota;
	}
}