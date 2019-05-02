/**
* Laboratório de Programação 2 - Lab 1
* 
* Matheus Silva Medeiros - 117110412
*/

import java.util.Scanner;

public class Media {
	public static void main(String[] args) throws Exception {
		Scanner sc = new Scanner(System.in);
		float numero1 = sc.nextFloat();
		float numero2 = sc.nextFloat();
		float media = (numero1 + numero2) / 2;
		if(media >= 7.0) {
			System.out.println("pass: True!");
		}else{
			System.out.println("pass: False!");
		}
	}
}
