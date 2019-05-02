package quemMeAjuda.Entidades.PedidoDeAjuda;

import java.io.Serializable;

import quemMeAjuda.Entidades.Aluno.Aluno;

/**
 * Classe abstrata PedidoDeAjuda
 * Um objeto PedidoDeAjuda é composto por:
 * Matricula do Aluno;
 * Disciplina;
 * Tutor (objeto Aluno);
 * Variável booleana que representa se o pedido foi finalizado;
 * 
 * @author Lukas, Wesley, Matheus
 *
 */
public abstract class PedidoDeAjuda implements Serializable {
	
	private static final long serialVersionUID = 8035229549214584828L;
	private String matriculaAluno;
	protected String disciplina;
	private Aluno tutor;
	private boolean finalizado;
	
	/**
	 * Constroi um pedido de ajuda a partir do Tutor que deve atender a essa ajuda,
	 * a matricula do aluno que a pediu e a disciplina que o aluno deseja receber ajuda.
	 * @param tutor objeto do Tutor que deve atender a essa ajuda.
	 * @param matricula matricula do aluno que pediu ajuda.
	 * @param disciplina nome da disciplina que o aluno pediu ajuda.
	 */
	public PedidoDeAjuda(Aluno tutor, String matricula, String disciplina) {
		this.tutor = tutor;
		this.matriculaAluno = matricula;
		this.disciplina = disciplina;
		this.finalizado = false;
	}
	
	/**
	 * Retorna o atributo do pedido de ajuda passado no parametro.
	 * @param atributo nome do atributo desejado
	 * @return representação textual do atributo.
	 */
	public abstract String getInfoAjuda(String atributo);
	
	public Aluno getTutor() {
		return tutor;
	}
	
	public String getMatriculaAluno() {
		return matriculaAluno;
	}
	
	public boolean getFinalizacao() {
		return finalizado;
	}
	
	/**
	 * Define se o objeto PedidoDeAjuda foi, ou não, finalizado
	 * @param fechouOuNao
	 */
	public void setFinalizacao(boolean fechouOuNao) {
		this.finalizado = fechouOuNao;
	}

	@Override
	public String toString() {
		return "Tutor - " + tutor.getMatricula();
	}
}
