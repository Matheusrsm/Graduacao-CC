package quemMeAjuda.Sistemas;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

import quemMeAjuda.Entidades.Aluno.*;
import quemMeAjuda.Entidades.PedidoDeAjuda.*;
import quemMeAjuda.Excecoes.Validacoes;

/**
 * Controlador de Tutoria e Pedidos de Ajuda
 * 
 * @author Lukas Nascimento, Matheus Silva, Wesley Monte
 *
 */
public class ControllerTutoria implements Serializable {
	
	private static final long serialVersionUID = 3848134710337632641L;
	private Map<String, Aluno> mapaAlunos;
	private Map<Integer, PedidoDeAjuda> pedidos;
	private int caixa;
	
	public void setCaixa(int caixa) {
		this.caixa = caixa;
	}

	private Validacoes validacoes;
	
	public ControllerTutoria() {
		this.mapaAlunos = ControllerAlunos.getAlunos();
		this.pedidos = new HashMap<>();
		this.caixa = 0;
		this.validacoes = new Validacoes();
	}
	
	/**
	 * Retorna o mapa de pedidos registrados no sistema
	 * @return
	 */
	public Map<Integer, PedidoDeAjuda> getPedidos() {
		return pedidos;
	}
	/**
	 * Registra um novo mapa de PedidoDeAjuda no sistema
	 * @param novoMapa
	 */
	public void carregaMapaPedidos(Map<Integer, PedidoDeAjuda> novoMapa) {
		pedidos = novoMapa;
	}
	
	/**
	 * Recupera um tutor que está associado ao pedido de ajuda (registrado no sistema)
	 * @param idAjuda int
	 * 		iD do objeto PedidoDeAjuda registrado no Sistema
	 * @return String
	 * 		Objeto Aluno(tutor) em String
	 * @throws Exception
	 */
	public String pegaTutor(int idAjuda) throws Exception {
		String erroPegaTutor = "Erro ao tentar recuperar tutor : ";
		validacoes.idAjudaInvalido(idAjuda, pedidos, erroPegaTutor);
		return pedidos.get(idAjuda).toString();
	}
	
	/**
	 * Recupera o caixa do Sistema de Tutoria
	 * @return int
	 */
	public int getCaixa() {
		return caixa;
	}
	
	/**
	 * Carrega um novo valor de caixa ao sistema.
	 * @param novoCaixa int
	 * 		Novo caixa do sistema
	 */
	public void carregaCaixa(int novoCaixa) {
		this.caixa = novoCaixa;
	}

	/**
	 * Cria um novo objeto PedidoDeAjudaPresencial e implementa qual tutor está sendo associado ao pedido.
	 * O novo objeto é adicionado à lista de pedidos de ajuda do sistema.
	 * @param matrAluno String
	 *		Matricula do aluno a registrar o pedido
	 * @param disciplina String
	 * 		Disciplina que o aluno deseja ajuda
	 * @param horario String
	 * 		Horario que o aluno contratante quer a ajuda
	 * @param dia String static
	 * 		Dia que o aluno contratante quer a ajuda
	 * @param localInteresse
	 * 		Local que o aluno contratante quer a ajuda
	 * @return
	 * @throws Exception 
	 */
	public int pedirAjudaPresencial(String matrAluno, String disciplina, String horario, String dia, String localInteresse) throws Exception {
		String erroPedirAjudaPresencial = "Erro no pedido de ajuda presencial: ";
		validacoes.matriculaVazia(matrAluno, erroPedirAjudaPresencial);
		validacoes.disciplinaVazia(disciplina, erroPedirAjudaPresencial);
		validacoes.horarioVazio(horario, erroPedirAjudaPresencial);
		validacoes.diaVazio(dia, erroPedirAjudaPresencial);
		validacoes.localVazio(localInteresse, erroPedirAjudaPresencial);
		pedidos.put(pedidos.size() + 1, new PedidoDeAjudaPresencial(tutorAdequado(disciplina, horario, dia, localInteresse), matrAluno, disciplina, horario, dia, localInteresse));
		return pedidos.size();
		
	}
	
	/**
	 * Verifica qual o Tutor adequado para um pedido de ajuda presencial de acordo com as especifícações de disciplina, horario, dia e local de interesse.
	 * Primeiro é verificado se o Aluno é um Tutor.
	 * Segundo se ele dá tutoria da disciplina desejada.
	 * E em seguida se ele atende no horario, no dia e no local de desejados.
	 * E por fim é escolhido aquele que possui maior proficiencia na disciplina
	 * @param disciplina
	 * 				Disciplina que o Aluno precisa de ajuda
	 * @param horario
	 * 				Horario de interesse do Aluno
	 * @param dia
	 * 				Dia de interesse do Aluno
	 * @param localInteresse
	 * 				Local de interesse do Aluno
	 * @return Matricula do Tutor, uma String.
	 */
	private Aluno tutorAdequado(String disciplina, String horario, String dia, String localInteresse) {
		Aluno tutor = new Aluno(0, "", "", 0, "", "");
		tutor.tornaAlunoTutor("", 0);
		for(Aluno a : mapaAlunos.values()) {
			if(a.isTutor() && a.getTutoria().temDisciplina(disciplina) && verificaHorario(a, horario, dia) && a.getTutoria().getLocais().contains(localInteresse)) {
				if(a.getTutoria().getProficiencia(disciplina) > tutor.getTutoria().getProficiencia(disciplina)) tutor = a;
				else if(a.getTutoria().getProficiencia(disciplina) == tutor.getTutoria().getProficiencia(disciplina)) {
					if(tutor.getID() > a.getID()) tutor = a;
				}
			}
		}
		return tutor;
	}
	
	/**
	 * Verifica se o Tutor atende no horario e no dia especificados.
	 * @param horario 
	 * @param dia
	 * @return
	 */
	private boolean verificaHorario(Aluno a, String horario, String dia) {
		for(Horario h : a.getTutoria().getHorarios()) {
			if(h.getHorario().equals(horario) && h.getDia().equals(dia)) return true;
		}
		return false;
	}
	
	/**
	 * Método usado para definir o tutor adequado para um pedido de ajuda online.
	 * @param disciplina
	 * 				Disciplina que o Aluno precisa de ajuda
	 * @return matricula do Tutor, uma String.
	 */
	private Aluno tutorAdequado(String disciplina) {
		Aluno tutor = new Aluno(0, "", "", 0, "", "");
		tutor.tornaAlunoTutor("", 0);
		for(Aluno a : mapaAlunos.values()) 
			if(a.isTutor() && a.getTutoria().temDisciplina(disciplina))
				if(a.getTutoria().getProficiencia(disciplina) > tutor.getTutoria().getProficiencia(disciplina)) tutor = a;
				else if(a.getTutoria().getProficiencia(disciplina) == tutor.getTutoria().getProficiencia(disciplina))
					if(tutor.getID() > a.getID()) tutor = a;
		return tutor;
	}
	
	/**
	 * Registra um novo objeto PedidoDeAjudaOnline no sistema, ficando disponível para ajuda
	 * @param matrAluno String
	 * 		Matricula do aluno a registrar o pedido
	 * @param disciplina String
	 * 		Disciplina que o Aluno precisa de ajuda
	 * @return int
	 * 		iD do objeto PedidoDeAjudaOnline registrado
	 * @throws Exception
	 */
	public int pedirAjudaOnline(String matrAluno, String disciplina) throws Exception {
		String erroPedirAjudaOnline = "Erro no pedido de ajuda online: ";
		validacoes.matriculaVazia(matrAluno, erroPedirAjudaOnline);
		validacoes.disciplinaVazia(disciplina, erroPedirAjudaOnline);
		pedidos.put(pedidos.size() + 1, new PedidoDeAjudaOnline(tutorAdequado(disciplina), matrAluno, disciplina));
		return pedidos.size();
	}
	
	/**
	 * Recupera a informação desejada (parâmetro) de um objeto PedidoDeAjuda registrado no sistema
	 * @param idAjuda int
	 * 		iD do PedidoDeAjuda registrado no sistema
	 * @param atributo String
	 * 		Informação do pedido de ajuda desejada
	 * @return String
	 * 		Informação pedida
	 * @throws Exception
	 */
	public String getInfoAjuda(int idAjuda, String atributo) throws Exception {
		String erroGetInfoAjuda = "Erro ao tentar recuperar info da ajuda : ";
		validacoes.idAjudaInvalido(idAjuda, pedidos, erroGetInfoAjuda);
		validacoes.atributoInvalido(atributo, erroGetInfoAjuda);
		return pedidos.get(idAjuda).getInfoAjuda(atributo);
	}
	
	/**
	 * Avalia um Aluno(tutor) que forneceu ajuda
	 * @param idAjuda int
	 * 		iD do PedidoDeAjuda que o tutor a ser avaliado prestou serviço
	 * @param nota int
	 * 		Nota que o tutor irá receber por seu serviço
	 * @throws Exception
	 */
	public void avaliarTutor(int idAjuda, int nota) throws Exception {
		String erroAvaliarTutor = "Erro na avaliacao de tutor: ";
		validacoes.notaInvalida(nota, erroAvaliarTutor);
		validacoes.idAjudaInvalido(idAjuda, pedidos, erroAvaliarTutor);
		validacoes.ajudaJaFechada(idAjuda, pedidos, erroAvaliarTutor);
		pedidos.get(idAjuda).getTutor().getTutoria().setNotaDeAvaliacao(nota);
		pedidos.get(idAjuda).setFinalizacao(true);
	}
	
	/**
	 * Calcula a taxa do Aluno(tutor) através do seu nível
	 * @param matriculaTutor String matricula do tutor registrado no sistema
	 * @return double taxa do tutor
	 */
	private double calculaTaxaTutor(String matriculaTutor) {
		Aluno tutor = mapaAlunos.get(matriculaTutor);
		double taxa = 0;
		if(tutor.isTutor())
			if(tutor.getTutoria().getNivel().equals("TOP")) {
				double valorAMais = tutor.getTutoria().getNotaDeAvaliacao() - 4.5;
				taxa = 0.9 + (valorAMais / 10);
			}
			else if(tutor.getTutoria().getNivel().equals("Tutor")) {
				taxa = 0.8;
			}
			else if(tutor.getTutoria().getNivel().equals("Aprendiz")) {
				double valorAMenos = 3.0 - tutor.getTutoria().getNotaDeAvaliacao();
				taxa = 0.4 - (valorAMenos / 10);
			}
		return taxa;
	}
	
	/**
	 * Doa a um Aluno(tutor) um valor em centavos
	 * @param matriculaTutor String
	 * 		Matricula do tutor a receber a doação
	 * @param totalCentavos int
	 * 		Valor da doação em centavos
	 * @throws Exception
	 */
	public void doar(String matriculaTutor, int totalCentavos) throws Exception {
		String erroDoar = "Erro na doacao para tutor: ";
		validacoes.totalCentavosInvalido(totalCentavos, erroDoar);
		validacoes.naoEhTutor(matriculaTutor, mapaAlunos, erroDoar);
		double totalSistema = Math.ceil((1 - calculaTaxaTutor(matriculaTutor)) * totalCentavos);
		this.caixa += totalSistema;
		mapaAlunos.get(matriculaTutor).getTutoria().recebeDoacao(totalCentavos - totalSistema);
	}
}	