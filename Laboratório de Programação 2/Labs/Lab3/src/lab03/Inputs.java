package lab03;

import java.util.Scanner;

public class Inputs {
	
	/*
	 * Classe que imprime o Menu na tela e l� as entradas do programa.
	 */
	
	public Scanner input = new Scanner(System.in);
	final String MENU = "\n(C)adastrar Contato\n" + 
						"(L)istar Contatos\n" +
						"(E)xibir Contato\n" +
						"(S)air\n" + 
						"\nOp��o> ";
	/*
	 * M�todo que l� a opera��o a ser realizada pelo programa.
	 * @return a opera��o.
	 */
	public String leOperacao() {
		System.out.print(MENU);
		return input.next();
	}
	
	/*
	 * M�todo que l� dados como: nome, sobrenome e telefone.
	 * @return o dado.
	 */
	public String leString(String msg) {
		System.out.print(msg);
		return input.nextLine();
	}

	/*
	 * M�todo que l� um n�mero que aponta para a posi��o do objeto na agenda.
	 * @return o n�mero.
	 */
	public int leInteiro(String msg) {
		System.out.print(msg);
		int numero = input.nextInt();
		input.nextLine();
		return numero;
	}
}
