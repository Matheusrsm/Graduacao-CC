package printer;

import java.util.Scanner;

import printer.type.Type;
import printer.type.TypeNames;

public class Printer {
	
	public static void main(String[] args) {
		Scanner input = new Scanner(System.in);
		System.out.print("Digit your text here: ");
		String text = input.nextLine();
		System.out.print("What is the type of your print? (1) Italic, (2) Bold, (3) Uppercase, (4) Lowercase: ");
		int type = input.nextInt();
		TypeNames typeName = TypeNames.values()[type - 1];
		
		Type printType = typeName.getType();
		String formatedText = printType.formatText(text);
		System.out.println(formatedText);
		input.close();
	}

}
