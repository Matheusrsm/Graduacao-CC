package quemMeAjuda.Comparadores;

/**
 * Enumeracao das Ordens de comparacao.
 * 
 * @author Matheus Silva
 *
 */
public enum Ordem {

	NOME("NOME"), MATRICULA("MATRICULA"), EMAIL("EMAIL");
	
	private String descricao;
	
	private Ordem(String descricao) {
		this.descricao = descricao;
	}
	
	public String getDescricao() {
		return descricao;
	}
}