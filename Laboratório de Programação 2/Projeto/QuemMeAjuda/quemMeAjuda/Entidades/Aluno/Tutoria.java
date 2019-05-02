package quemMeAjuda.Entidades.Aluno;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Classe Tutoria
 * Um objeto Tutoria é composta por:
 * Lista de objetos Disciplina;
 * Lista de locais de atendimento;
 * Lista de objetos Horario;
 * Nota de avaliação (do tutor);
 * Bolsa (do tutor);
 * Nível (do tutor);
 * @author Lukas, Wesley, Matheus
 *
 */
public class Tutoria implements Serializable {

	private static final long serialVersionUID = 6980912988371418811L;
	private List<Disciplina> disciplinas;
	private List<String> locais;
	private List<Horario> horarios;
	private double notaDeAvaliacao;
	private double  bolsa;
	private NivelTutoria nivel;
	
	public Tutoria() {
		this.disciplinas = new ArrayList<>();
		this.locais      = new ArrayList<>();
		this.horarios    = new ArrayList<>();
		this.notaDeAvaliacao = 4.0;
		this.nivel = NivelTutoria.TUTOR;
		this.bolsa = 0.0;
	}
	
	/**
	 * Adiciona uma nova Disciplina(Objeto) na qual o Tutor pode tutorar.
	 * @param disciplina nome da disciplina.
	 * @param proficiencia proficiencia do tutor na disciplina.
	 */
	public void adicionarDisciplina(String disciplina, int proficiencia) {
		disciplinas.add(new Disciplina(disciplina, proficiencia));
	}
	
	/**
	 * Retorna a proficiencia do Tutor em determinada disciplina.
	 * Se o tutor não tutorar nessa disciplina, retorna 0.
	 * @param disciplina nome da disciplina.
	 * @return inteiro representando a proficiencia do Tutor na disciplina
	 */
	public double getProficiencia(String disciplina) {
		for (Disciplina d : disciplinas)
			if (d.getNome().equals(disciplina)) return d.getProficiencia();
		return 0;
	}
	
	/**
	 * Método que verifica se o tutor tem a disciplina passada
	 * @param disciplina
	 * 				Disciplina a ser consultada
	 * @return boolean 
	 */
	public boolean temDisciplina(String disciplina) {
		for (Disciplina d : disciplinas)
			if (d.getNome().equals(disciplina)) return true;
		return false;
	}
	
	/**
	 * Adiciona um valor(doacao) na bolsa do Tutor.
	 * @param doacao valor em double da doacao.
	 */
	public void recebeDoacao(double doacao) {
		this.bolsa += doacao;
	}

	public List<Disciplina> getDisciplinas() {
		return this.disciplinas;
	}

	public List<String> getLocais() {
		return locais;
	}

	public List<Horario> getHorarios() {
		return horarios;
	}

	public double getNotaDeAvaliacao() {
		return notaDeAvaliacao;
	}
	/**
	 * Calcula e define a nota de avaliacao do Tutor a partir da nota atual e de uma nova avaliação recebida por ele.
	 * Invoca o metodo setNivel que altera automaticamente o nivel da Tutoria de acordo com a Nota de Avaliacao.
	 * @param novaAvaliacao double da nota de avaliacao recebida por ele.
	 */
	public void setNotaDeAvaliacao(double novaAvaliacao) {
		this.notaDeAvaliacao = ((notaDeAvaliacao * 5) + novaAvaliacao) / 6;
		setNivel();
	}
	/**
	 * Representação textual da nota de avaliação do Tutor.
	 * Troca-se o ponto por virgula e limita para duas casas decimais.
	 * @return
	 */
	public String toStringNotaDeAvaliacao() {
		return String.format("%.2f", notaDeAvaliacao).replace('.', ',');
	}

	public double getBolsa() {
		return bolsa;
	}

	public String getNivel() {
		return nivel.getDescricao();
	}
	
	/**
	 * Set do nivel do Tutor tendo como criterio a sua nota de avaliacao.
	 * Se a nota de avaliacao for maior que 4.5, o Tutor eh do nivel "TOP".
	 * Se a nota de avaliacao for entre 4.5(incluso) e 3.0, o Tutor eh do nivel "Tutor".
	 * Se a nota de avaliacao for entre 0.0 e 3.0(incluso), o Tutor eh do nivel "Aprendiz".
	 */
	public void setNivel() {
		if(notaDeAvaliacao > 4.5) this.nivel = NivelTutoria.TOP;
		else if(notaDeAvaliacao > 3.0 && notaDeAvaliacao <= 4.5) this.nivel = NivelTutoria.TUTOR;
		else if(notaDeAvaliacao > 0.0 && notaDeAvaliacao <= 3.0) this.nivel = NivelTutoria.APRENDIZ;
	}
}