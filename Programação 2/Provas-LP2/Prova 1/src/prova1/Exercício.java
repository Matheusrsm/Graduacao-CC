package prova1;

public class Exercício {

	public String nome;
	public int calorias;
	
	public Exercício(String nome, int calorias) {
		this.nome = nome;
		this.calorias = calorias;
	}

	public String getNome() {
		return nome;
	}

	public int getCalorias() {
		return calorias;
	}

	@Override
	public String toString() {
		return nome + " - " + calorias + " cal/h";
	}
}