package printer.type;

public class Uppercase implements Type {

	public String formatText(String text) {
		return text.toUpperCase();
	}
}
