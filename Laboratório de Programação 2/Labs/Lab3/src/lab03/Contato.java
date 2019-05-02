package lab03;

public class Contato {

	private String nome;
	private String sobrenome;
	private String telefone;

	/*
	 * Constrói um Contato com nome, sobrenome e telefone.
	 */
	public Contato(String nome, String sobrenome, String telefone) {
		if (nome == null || sobrenome == null || telefone == null) {
			throw new NullPointerException("Argumento Nulo!");
		}
		if (nome.trim().equals("") || sobrenome.trim().equals("") || telefone.trim().equals("")) {
			throw new IllegalArgumentException("Argumento Inválido!");
		}
			this.nome = nome;
			this.sobrenome = sobrenome;
			this.telefone = telefone;
		}

	/*
	 * Método equals que compara um objeto com outro a partir de seu nome.
	 * @return um booleano que indica a igualdade de dois objetos.
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Contato outro = (Contato) obj;
		if (nome == null) {
			if (outro.nome != null)
				return false;
		} else if (!nome.equals(outro.nome))
			return false;
		return true;
	}

	/*
	 * @return a representação em String de um Contato.
	 */
	@Override
	public String toString() {
		return this.nome + " " + this.sobrenome + " - " + this.telefone;
	}
	
	/*
	 * @return String que representa o nome e sobrenome de um Contato.
	 */
	public String getNomeESobrenome() {
		return nome + " " + sobrenome;
	}
}