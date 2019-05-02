package prova1;

public class Academia {

	public String nome;
	public Exercício[] treino;
	
	public Academia(String nome) {
		this.nome = nome;
		treino = new Exercício[4];
	}
	
	public Academia(String nome, int qtdExerc) {
		this.nome = nome;
		treino = new Exercício[qtdExerc];
	}
	
	public String adicionaExercicio(Exercício exercicio) {
		for(int i = 0; i < treino.length; i++) {
			if(treino[i] == null) {
				treino[i] = exercicio;
				return "Exercício adicionado!";
			}
		}
		return "Impossível adicionar novo exercício. Treino completo!";
	}
	
	public String listaExercicios() {
		String exercicios = "";
		for(int i = 0; i < treino.length; i++) {
			if(treino[i] != null) {
				exercicios += "\n" + (i + 1) + " - " + treino[i].getNome() + " - " + treino[i].getCalorias() + " cal/h";
			}
		}
		return exercicios;
	}
	
	public String calcularCalorias(String[] exercicios, int horas, String[] desconhecidos) {
		if(exercicioDesconhecido(desconhecidos) == false) {
			int caloriasTotal = 0;
			for(int i = 0; i < exercicios.length; i++) {
				for(int j = 0; j < treino.length; j++) {
					if(treino[j] != null) {
						if(treino[j].getNome().equals(exercicios[i])) {
							caloriasTotal += treino[j].getCalorias();
						}
					}
				}
			}
			return caloriasTotal * horas + " calorias gastas";
		}
		throw new IllegalArgumentException("Há um exercício desconhecido da Academia!");	
	}
	
	private boolean exercicioDesconhecido(String[] desconhecidos) {
		for(int j = 0; j < treino.length; j++) {
			for(int i = 0; i < desconhecidos.length; i++) {
				if(treino[j].getNome().equals(desconhecidos[i])) {
					return true;
				}
			}
		}
		return false;
	}
}