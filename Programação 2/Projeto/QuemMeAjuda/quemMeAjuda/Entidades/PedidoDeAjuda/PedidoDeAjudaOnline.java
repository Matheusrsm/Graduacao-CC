package quemMeAjuda.Entidades.PedidoDeAjuda;

import quemMeAjuda.Entidades.Aluno.*;

/**
 * Classe PedidoDeAjudaOnline (estende PedidoDeAJuda)
 * Um objeto PedidoDeAjudaOnline é composto por:
 * Matricula do Aluno;
 * Disciplina;
 * Tutor (objeto Aluno);
 * 
 * @author Lukas, Wesley, Matheus
 *
 */
public class PedidoDeAjudaOnline extends PedidoDeAjuda {

	private static final long serialVersionUID = 7358644632213745813L;

	public PedidoDeAjudaOnline(Aluno tutor, String matricula, String disciplina) {
		super(tutor, matricula, disciplina);
	}
	
	@Override
	public String getInfoAjuda(String atributo) {
		if(atributo.equals("disciplina"))
			return disciplina;
		return null;
	}

	@Override
	public String toString() {
		return super.toString() + ", disciplina - " + disciplina;
	}
}