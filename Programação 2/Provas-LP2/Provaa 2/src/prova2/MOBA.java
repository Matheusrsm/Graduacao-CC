package prova2;

import prova2.auxiliares.Controller;

public class MOBA {

	private Controller controller = new Controller();
	
	public void cadastraCandidato(int codigo, String nome, String cargo) {
		controller.cadastrarCandidato(codigo, cargo, nome);
	}
	
	public String recuperaCandidato(int codigo) {
		return controller.recuperaCandidato(codigo);
	}
	
	public void cadastrarComentario(int codigoCandidatoAutor, int codigoCandidatoComentado, String comentario) {
		controller.cadastrarComentario(codigoCandidatoAutor, codigoCandidatoComentado, comentario);
	}
	
	public String pegaComentarios(int codigoCandidatoAutor) {
		return controller.pegaComentarios(codigoCandidatoAutor);
	}
	
	public int contaCandidatos(String cargo) {
		return controller.contaCandidatos(cargo);
	}
}