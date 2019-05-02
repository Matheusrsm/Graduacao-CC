package quemMeAjuda.Entidades.Aluno;

/**
 * Enumeracao dos Niveis de uma Tutoria.
 * 
 * @author Matheus Silva
 *
 */
public enum NivelTutoria {
	
	APRENDIZ("Aprendiz"), TUTOR("Tutor"), TOP("TOP");
	
	private String descricao;
	
	private NivelTutoria(String descricao) {
		this.descricao = descricao;
	}
	
	public String getDescricao() {
		return descricao;
	}
}