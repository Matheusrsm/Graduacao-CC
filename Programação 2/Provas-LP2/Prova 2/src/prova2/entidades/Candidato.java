package prova2.entidades;

import java.util.ArrayList;

public class Candidato {

	private String nome;
	private String cargo;
	private int codigo;
	private ArrayList<Comentario> comentariosRecebidos;
	
	public Candidato(int codigo, String cargo, String nome) {
		this.nome = nome;
		this.cargo = cargo;
		this.codigo = codigo;
		this.comentariosRecebidos = new ArrayList<Comentario>();
	}

	public String getNome() {
		return nome;
	}

	public String getCargo() {
		return cargo;
	}

	public int getCodigo() {
		return codigo;
	}

	public ArrayList<Comentario> getComentarios() {
		return comentariosRecebidos;
	}
	
	@Override
	public String toString() {
		return codigo + " - " + cargo + " - " + nome;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + codigo;
		result = prime * result + ((nome == null) ? 0 : nome.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Candidato other = (Candidato) obj;
		if (codigo != other.codigo)
			return false;
		if (nome == null) {
			if (other.nome != null)
				return false;
		} else if (!nome.equals(other.nome))
			return false;
		return true;
	}
}