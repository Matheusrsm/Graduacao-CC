package lab4.auxiliares;

import java.util.Scanner;

/**
 * Classe Menu do Sistema. Possui as poss�veis leituras do usu�rio e o MENU de op��es.
 * 
 * @author Matheus Silva - 117110412
 */
public class Menu {

	private Scanner operacao = new Scanner(System.in);
	private Scanner scannerString = new Scanner(System.in);
	private final String MENU = "\n(C)adastrar Aluno\n" + 
								  "(E)xibir Aluno\n" +
								  "(N)ovo Grupo\n" +
								  "(A)locar Aluno no Grupo e Imprimir Grupos\n" + 
								  "(R)egistrar Resposta de Aluno\n" +
								  "(I)mprimir Alunos que Responderam\n" +
								  "(O)ra, vamos fechar o programa!\n" +
								  "\nOp��o> ";
	
	/**
	 * M�todo que exibe o MENU e l� a opera��o desejada pelo usu�rio.
	 * 
	 * @return a opera��o em String.
	 */
	public String leOperacao() {
		System.out.print(MENU);
		return operacao.next();
	}

	/**
	 * M�todo que imprime a mensagem do que � pedido para o usu�rio e
	 * l� as Strings que precedem os dados que ser�o usados pelo Sistema.
	 * 
	 * @return o dado em String.
	 */
	public String leString(String msg) {
		System.out.print(msg);
		String entrada = scannerString.nextLine();
		return entrada;
	}
}
