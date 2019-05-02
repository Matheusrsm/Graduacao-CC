/**
* Laboratório de Programação 2 - Lab 1
* 
* Matheus Silva Medeiros - 117110412
*/

import java.util.Scanner;

public class DobroeTriplo {
	public static void main(String[] args) throws Exception {
		Scanner sc = new Scanner(System.in);
		int numero = sc.nextInt();
		int dobro = numero * 2;
		int triplo = numero * 3;
		System.out.println("dobro: " + dobro + ", triplo: " + triplo);
	}
}
