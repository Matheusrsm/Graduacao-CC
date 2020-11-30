package badcode;

import java.util.List;

public class Projeto {

	public int d;

	public boolean isEntregue() {
		return false;
	}
	public List<Cliente> getClientes() {
		return null;
	}

	public String checarAndamento() {
		if (d < 90) return dentroDoPrazo(); 
		else return foraDoPrazo();
	}

	private String dentroDoPrazo() {
		if (!isEntregue()) return "Projeto está apertado";
		else return "Projeto entregue";
	}

	private String foraDoPrazo() {
		for (Cliente c: getClientes()) c.avisaAtraso();
		return "Projeto atrasado";
	}
 }