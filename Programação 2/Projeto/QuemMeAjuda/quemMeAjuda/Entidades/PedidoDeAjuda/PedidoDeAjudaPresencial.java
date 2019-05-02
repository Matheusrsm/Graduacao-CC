package quemMeAjuda.Entidades.PedidoDeAjuda;

import quemMeAjuda.Entidades.Aluno.*;

/**
 * Classe PedidoDeAjudaPresencial (estende PedidoDeAjuda)
 * Um objeto PedidoDeAjudaPresencial é composto por:
 * Matricula do Aluno;
 * Disciplina;
 * Tutor (objeto Aluno);
 * Horario;
 * Dia;
 * Local;
 * 
 * @author Lukas, Wesley, Matheus
 *
 */
public class PedidoDeAjudaPresencial extends PedidoDeAjuda {

	private static final long serialVersionUID = 353215511292577905L;
	private String dia, local, horario;
	
	public PedidoDeAjudaPresencial(Aluno tutor, String matricula, String disciplina, String horario, String dia, String local) {
		super(tutor, matricula, disciplina);
		this.horario = horario;
		this.dia = dia;
		this.local = local;
	}

	@Override
	public String getInfoAjuda(String atributo) {
		switch(atributo) {
		case "horario":
			return horario;
		case "dia":
			return dia;
		case "localInteresse":
			return local;
		case "disciplina":
			return disciplina;
		default:
			return null;
		}
	}
	
	@Override
	public String toString() {
		return super.toString() + ", horario - " + horario + ", dia - " + dia + ", local - " + local + ", disciplina - " + disciplina;
	}
}