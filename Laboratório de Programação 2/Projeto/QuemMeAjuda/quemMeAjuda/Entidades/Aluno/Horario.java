package quemMeAjuda.Entidades.Aluno;

import java.io.Serializable;

/**
 * Classe Horario.
 * Representa um horario com hora (String) e dia (String) da semana
 * @author lukasnascimento
 *
 */
public class Horario implements Serializable{
	
	private static final long serialVersionUID = 8287375288579395764L;
	private String horario, dia;
	
	/**
	 * Constri um Horario a partir da hora e do dia em que o Tutor atende.
	 * @param horario represencao textual da hora em que o Tutor atende
	 * @param dia dia da semana em que o Tutor atende.
	 */
	public Horario(String horario, String dia) {
		this.horario = horario;
		this.dia = dia;
	}

	public String getHorario() {
		return horario;
	}

	public String getDia() {
		return dia;
	}
}