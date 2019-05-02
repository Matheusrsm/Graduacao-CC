/**
* Laboratório de Programação 2 - Lab 1
* 
* Matheus Silva Medeiros - 117110412
*/

import java.util.Scanner;

public class Entrada {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		String entrada = sc.nextLine();
		if(entrada.equals("+") || entrada.equals("-") || entrada.equals("*") || entrada.equals("/")) {
			float num1 = sc.nextFloat();
			float num2 = sc.nextFloat();
			if(entrada.equals("+")) {
				float resultado = num1 + num2;
				System.out.println("RESULTADO: " + resultado);
			} else if(entrada.equals("-")) {
				float resultado = num1 - num2;
				System.out.println("RESULTADO: " + resultado);
			} else if(entrada.equals("*")) {
				float resultado = num1 * num2;
				System.out.println("RESULTADO: " + resultado);
			} else if(entrada.equals("/") && num2 != 0.0) {
				float resultado = num1 / num2;
				System.out.println("RESULTADO: " + resultado);
			} else {
				System.out.println("ERRO");
			}
		} else {
			System.out.println("ENTRADA INVALIDA");
		}
	}
}
