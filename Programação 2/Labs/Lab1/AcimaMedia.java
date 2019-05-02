/**
* Laboratório de Programação 2 - Lab 1
* 
* Matheus Silva Medeiros - 117110412
*/

import java.util.Scanner;

public class AcimaMedia {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		String sequencia = sc.nextLine();
		int soma = 0;
		int cont = 0;
		for(String numeros : sequencia.split(" ")) {
			int numero = Integer.parseInt(numeros);
			soma = soma + numero;
			cont++;
		} 
		double media = soma / cont;
		String saida = "";
		for(String numeros : sequencia.split(" ")) {
			int numero = Integer.parseInt(numeros);
			if(numero > media) { 
			saida = saida + numeros + " ";
			}
		}
		System.out.println(saida);
	}
}
