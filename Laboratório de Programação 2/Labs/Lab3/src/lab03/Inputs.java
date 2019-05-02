package lab03;

import java.util.Scanner;

public class Inputs {
	
	/*
	 * Classe que imprime o Menu na tela e lê as entradas do programa.
	 */
	
	public Scanner input = new Scanner(System.in);
	final String MENU = "\n(C)adastrar Contato\n" + 
						"(L)istar Contatos\n" +
						"(E)xibir Contato\n" +
						"(S)air\n" + 
						"\nOpção> ";
	/*
	 * Método que lê a operação a ser realizada pelo programa.
	 * @return a operação.
	 */
	public String leOperacao() {
		System.out.print(MENU);
		return input.next();
	}
	
	/*
	 * Método que lê dados como: nome, sobrenome e telefone.
	 * @return o dado.
	 */
	public String leString(String msg) {
		System.out.print(msg);
		return input.nextLine();
	}

	/*
	 * Método que lê um número que aponta para a posição do objeto na agenda.
	 * @return o número.
	 */
	public int leInteiro(String msg) {
		System.out.print(msg);
		int numero = input.nextInt();
		input.nextLine();
		return numero;
	}
}
