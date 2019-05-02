package quemMeAjuda.Excecoes;

/**
 * Classe de Exceção de DadoNulo
 * 
 * @author Matheus, Lukas, Wesley
 *
 */
public class DadoNuloException extends RuntimeException {

	private static final long serialVersionUID = 1L;
	
	/**
	 * Mostra a mensagem de exceção de dado nulo
	 * @param msg
	 * 		mensagem a ser exibida
	 */
	public DadoNuloException(String msg) {
		super(msg);
	}
}