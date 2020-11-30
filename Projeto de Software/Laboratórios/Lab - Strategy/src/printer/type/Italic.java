package printer.type;

public class Italic implements Type {

	public String formatText(String text) {
		return "\033[3m" + text + "\033[0m";
	}

}