package quemMeAjuda.Sistemas;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import quemMeAjuda.Comparadores.*;
import quemMeAjuda.Entidades.Aluno.*;
import quemMeAjuda.Excecoes.Validacoes;

/**
 * Controlador de Alunos
 * 
 * @author Matheus, Lukas, Wesley
 *
 */
public class ControllerAlunos implements Serializable {

	private static final long serialVersionUID = -5902969503242499205L;
	private static Map<String, Aluno> alunos;
	private Validacoes validacoes;
	private Comparator<Aluno> ordem;
	
	public ControllerAlunos() {
		alunos = new HashMap<>();
		this.validacoes = new Validacoes();
		this.ordem = new ComparadorNome();
	}
	
	/**
	 * Recupera o mapa de Alunos registrados no Sistema
	 * @return Map<String, Aluno>
	 */
	public static Map<String, Aluno> getAlunos() {
		return alunos;
	}
	
	/**
	 * Carrega um novo mapa de Alunos
	 * @param Map<String, Aluno>
	 * 		Novo mapa de Alunos 
	 */
	public static void carregaMapaAlunos(Map<String, Aluno> novoMapa) {
		alunos = novoMapa;
	}
	
	/**
	 * Cadastrar um novo objeto Aluno no Sistema
	 * @param nome String
	 * 		Nome do Aluno a ser registrado
	 * @param matricula String
	 * 		Matricula do Aluno a ser registrado
	 * @param codigoCurso int
	 * 		Codigo do curso do Aluno a ser registrado
	 * @param telefone String
	 * 		Telefone do Aluno a ser registrado
	 * @param email String
	 * 		E-mail do Aluno a ser registrado
	 * @throws Exception
	 */
	public void cadastrarAluno(String nome, String matricula, int codigoCurso, String telefone, String email) throws Exception {
		String erroCadastrarAluno = "Erro no cadastro de aluno: ";
		validacoes.emailInvalidoOuNulo(email, erroCadastrarAluno);
		validacoes.matriculaJaCadastrada(matricula, alunos, erroCadastrarAluno);
		validacoes.nomeInvalidoOuNulo(nome, erroCadastrarAluno);
		alunos.put(matricula, new Aluno(alunos.size() + 1, nome, matricula, codigoCurso, telefone, email));
	}
	
	/**
	 * Recupera um objeto Aluno registrado no sistema
	 * @param matricula String
	 * 		Matricula do Aluno a ser recuperado 
	 * @return String
	 * 		Formato String do Aluno a ser recuperado
	 * 		(matricula + " - " + nome + " - " + codigoCurso + " - " + telefone + " - " +  email)
	 * @throws Exception
	 */
	public String recuperaAluno(String matricula) throws Exception {
		String erroRecuperaAluno = "Erro na busca por aluno: ";
		validacoes.alunoNaoCadastrado(matricula, alunos, erroRecuperaAluno);
		return alunos.get(matricula).toString();
	}
	
	/**
	 * Lista os Alunos registrados no sistema
	 * @return String
	 */
	public String listarAlunos() {
		List<Aluno> listaDeAlunos = new ArrayList<Aluno>();
		for(Aluno a: alunos.values()) listaDeAlunos.add(a);
		Collections.sort(listaDeAlunos, this.ordem);
		String listaAlunos = "";
		for(Aluno a: listaDeAlunos) listaAlunos += a.toString() + ", ";
		listaAlunos = listaAlunos.substring(0, listaAlunos.length() - 2);
		return listaAlunos;
	}
	
	/**
	 * Recupera a informação de um Aluno desejada no parâmetro 
	 * @param matricula String
	 * 		Matricula do Aluno
	 * @param atributo String
	 * 		Informação desejada
	 * @return String
	 *		Informação
	 * @throws Exception
	 */
	public String getInfoAluno(String matricula, String atributo) throws Exception {
		String erroGetInfoAluno = "Erro na obtencao de informacao de aluno: ";
		validacoes.alunoNaoCadastrado(matricula, alunos, erroGetInfoAluno);
		switch (atributo.toLowerCase()){
			case "nome":
				return alunos.get(matricula).getNome();
			case "codigo do curso":
				return Integer.toString(alunos.get(matricula).getCodigoCurso());
			case "telefone":
				return alunos.get(matricula).getTelefone();
			case "email":
				return alunos.get(matricula).getEmail();
			default:
				return null;
		}
	}
	
	/**
	 * Registra um Aluno (já registrado) do sistema como tutor
	 * @param matricula String
	 * 		Matricula do Aluno a se tornar tutor
	 * @param disciplina String
	 * 		Disciplina qual o Aluno tornarar-se tutor
	 * @param proficiencia
	 * 		Proficiencia do Aluno na disciplina
	 * @throws Exception
	 */
	public void tornarTutor(String matricula, String disciplina, int proficiencia) throws Exception {
		String erroTornarTutor = "Erro na definicao de papel: ";
		validacoes.disciplinaVazia(disciplina, erroTornarTutor);
		validacoes.naoEhTutor(matricula, alunos, erroTornarTutor);
		Aluno alunoViraTutor = alunos.get(matricula);
		if(alunoViraTutor.isTutor()) {
			List<Disciplina> disciplinasDoTutor = alunoViraTutor.getTutoria().getDisciplinas();
			validacoes.disciplinaJaEhTutor(disciplina, disciplinasDoTutor, erroTornarTutor);
			validacoes.proficienciaInvalida(proficiencia, erroTornarTutor);
			alunoViraTutor.getTutoria().adicionarDisciplina(disciplina, proficiencia);
		}
		else alunoViraTutor.tornaAlunoTutor(disciplina, proficiencia);
	}
	
	/**
	 * Recupera o Aluno, se for tutor, registrado no sistema
	 * @param matricula String
	 * 		Matricula do tutor a ser recuperado
	 * @return String
	 * @throws Exception
	 */
	public String recuperaTutor(String matricula) throws Exception {
		String erroRecuperaTutor = "Erro na busca por tutor: ";
		validacoes.naoEhTutor(matricula, alunos, erroRecuperaTutor);
		if(alunos.get(matricula).isTutor()) return alunos.get(matricula).toString();
		return null;
	}
	
	/**
	 * Lista todos os Alunos tutores registrados no sistema
	 * @return String
	 */
	public String listarTutores() {
		ArrayList<Aluno> listaDeTutores = new ArrayList<Aluno>();
		for(Aluno aluno: alunos.values())
			if(aluno.isTutor()) listaDeTutores.add(aluno);
		Collections.sort(listaDeTutores, ordem);
		String listaTutores = "";
		for(Aluno aluno: listaDeTutores) listaTutores += aluno.toString() + ", ";
		return listaTutores.substring(0, listaTutores.length() - 2);
	}
	
	/**
	 * Verifica, ao mesmo tempo, se o email do parametro é igual do Aluno e 
	 * se o Aluno é tutor.
	 * @param email String
	 * @param aluno Aluno
	 * @return Se tudo for verdadeiro, retorna true. Senão, retorna false.
	 */
	private boolean verificaEmailETutoria(String email, Aluno aluno) {
		return aluno.getEmail().equals(email) && aluno.isTutor();
	}
	
	/**
	 * Registra um Horario que o Aluno(tutor) tem disponível para tutoria
	 * (não relacionado diretamente como o local)
	 * @param email String
	 * 		E-mail do tutor a ter o horário registrado
	 * @param horario String
	 * 		Horario para atendimento
	 * @param dia String
	 * 		Dia para atendimento
	 * @throws Exception
	 */
	public void cadastrarHorario(String email, String horario, String dia) throws Exception {
		String erroCadastrarHorario = "Erro no cadastrar horario: ";
		validacoes.emailInvalidoOuNulo(email, erroCadastrarHorario);
		validacoes.horarioInvalido(horario, dia, erroCadastrarHorario);
		validacoes.tutorEmailNaoCadastrado(email, alunos, erroCadastrarHorario + "tutor nao cadastrado");
		for(Aluno aluno : alunos.values())
			if(verificaEmailETutoria(email, aluno)) 
				aluno.getTutoria().getHorarios().add(new Horario(horario, dia));
	}
	
	/**
	 * Registra um local de atendimento que o Aluno(tutor) tem disponível para atendimento
	 * (não relacionado, diretamente com o horário)
	 * @param email
	 * @param local
	 * @throws Exception
	 */
	public void cadastrarLocalDeAtendimento(String email, String local) throws Exception {
		String erroCadastrarLocal = "Erro no cadastrar local de atendimento: ";
		validacoes.localInvalidoOuNulo(local, erroCadastrarLocal);
		validacoes.emailInvalidoOuNulo(email, erroCadastrarLocal);
		validacoes.tutorEmailNaoCadastrado(email, alunos, erroCadastrarLocal + "tutor nao cadastrado");
		for(Aluno aluno : alunos.values())
			if(verificaEmailETutoria(email, aluno))
					aluno.getTutoria().getLocais().add(local);
	}
	
	/**
	 * Verifica se um Aluno(tutor) tem o horário (parâmetro) disponível 
	 * @param email String
	 * 		E-mail do tutor a realizar a verificação
	 * @param horario String
	 * 		Hora a verificar
	 * @param dia String
	 * 		Dia a verificar
	 * @return
	 */
	public boolean consultaHorario(String email, String horario, String dia) {
		for(Aluno aluno : alunos.values())
			if(verificaEmailETutoria(email, aluno))
				for(Horario h : aluno.getTutoria().getHorarios())
					if(h.getDia().equalsIgnoreCase(dia) && h.getHorario().equalsIgnoreCase(horario)) 
						return true;
		return false;
	}
	
	/**
	 * Verifica se um Aluno(tutor) tem o dia (parâmetro) disponível
	 * @param email String
	 * 		E-mail do tutor a realizar a verificação
	 * @param local String
	 * 		Local a verficar
	 * @return
	 */
	public boolean consultaLocal(String email, String local) {
		for(Aluno aluno : alunos.values())
			if(verificaEmailETutoria(email, aluno))
				return aluno.getTutoria().getLocais().contains(local);
		return false;
	}
	
	/**
	 * Recupera a nota do Aluno(tutor) dado no parametro
	 * @param matriculaTutor String
	 * 		Matricula do tutor
	 * @return String
	 */
	public String pegarNota(String matriculaTutor) {
		Aluno alunoPossivelTutor = alunos.get(matriculaTutor);
		if(alunoPossivelTutor.isTutor()) return alunoPossivelTutor.getTutoria().toStringNotaDeAvaliacao();
		return null;
	}
	
	/**
	 * Recupera o nível do Aluno(tutor) dado no parametro
	 * @param matriculaTutor String
	 * 		Matricula do tutor
	 * @return String
	 */	
	public String pegarNivel(String matriculaTutor) {
		alunos.get(matriculaTutor).getTutoria().setNivel();
		return alunos.get(matriculaTutor).getTutoria().getNivel();
	}
	
	/**
	 * Verifica o total de dinheiro no caixa do Aluno(tutor) registrado no sistema
	 * através do email do parametro
	 * @param emailTutor String
	 * @return int Se o email for válido e o Aluno é tutor, retorna a bolsa do tutor. Senão, retorna 0
	 * @throws Exception
	 */
	public int totalDinheiroTutor(String emailTutor) throws Exception {
		String erroTotalDinheiroTutor = "Erro na consulta de total de dinheiro do tutor: ";
		validacoes.emailTutorInvalidoOuNulo(emailTutor, erroTotalDinheiroTutor);
		validacoes.tutorEmailNaoCadastrado(emailTutor, alunos, erroTotalDinheiroTutor + "Tutor nao encontrado");
		for(Aluno a:alunos.values())
			if(verificaEmailETutoria(emailTutor, a))
				return (int) a.getTutoria().getBolsa();
		return 0;
	}
	
	/**
	 * Configura a ordem de exibição dos alunos e tutores
	 * @param atributo String
	 */
	public void configurarOrdem(String atributo) {
		if(atributo.equals(Ordem.NOME.getDescricao())) this.ordem = new ComparadorNome();
		else if(atributo.equals(Ordem.MATRICULA.getDescricao())) this.ordem = new ComparadorMatricula();
		else if(atributo.equals(Ordem.EMAIL.getDescricao())) this.ordem = new ComparadorEmail();
		else System.out.println("Nao deu certo");
	}
}