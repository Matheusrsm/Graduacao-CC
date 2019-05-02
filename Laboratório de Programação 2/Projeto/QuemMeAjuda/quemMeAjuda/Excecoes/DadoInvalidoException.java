package quemMeAjuda.Excecoes;

/**
 * Classe de Exceção de DadoInvalido
 * 
 * @author Lukas, Matheus, Wesley
 *
 */

public class DadoInvalidoException extends RuntimeException {
	
	private static final long serialVersionUID = 1L;

	/**
	 * Mostra mensagem de excecao de dado inválido
	 * @param msg
	 * 		mensagem a ser exibida
	 */
	public DadoInvalidoException(String msg) {
		super(msg);
	}
}