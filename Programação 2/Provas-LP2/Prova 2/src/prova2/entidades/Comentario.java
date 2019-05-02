package prova2.entidades;

public class Comentario {

	private Candidato candidatoAutor;
	private String descricaoComentario;
	private Candidato candidatoComentado;
	
	public Comentario(String descricaoComentario, Candidato candidatoComentado) {
		this.descricaoComentario = descricaoComentario;
		this.candidatoComentado = candidatoComentado;
	}

	public Candidato getCandidatoAutor() {
		return candidatoAutor;
	}

	public String getDescricaoComentario() {
		return descricaoComentario;
	}
	
	public Candidato getCandidatoComentado() {
		return candidatoComentado;
	}

	@Override
	public String toString() {
		return descricaoComentario + " --" + candidatoComentado.getNome();
	}
}
