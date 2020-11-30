package printer.type;

public class Lowercase implements Type {

	public String formatText(String text) {
		return text.toLowerCase();
	}
}
