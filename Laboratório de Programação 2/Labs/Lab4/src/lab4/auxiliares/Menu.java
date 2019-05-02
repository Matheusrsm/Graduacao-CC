package lab4.auxiliares;

import java.util.Scanner;

/**
 * Classe Menu do Sistema. Possui as possíveis leituras do usuário e o MENU de opções.
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
								  "\nOpção> ";
	
	/**
	 * Método que exibe o MENU e lê a operação desejada pelo usuário.
	 * 
	 * @return a operação em String.
	 */
	public String leOperacao() {
		System.out.print(MENU);
		return operacao.next();
	}

	/**
	 * Método que imprime a mensagem do que é pedido para o usuário e
	 * lê as Strings que precedem os dados que serão usados pelo Sistema.
	 * 
	 * @return o dado em String.
	 */
	public String leString(String msg) {
		System.out.print(msg);
		String entrada = scannerString.nextLine();
		return entrada;
	}
}
