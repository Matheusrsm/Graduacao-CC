package quemMeAjuda.Sistemas;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.Map;

import quemMeAjuda.Entidades.Aluno.Aluno;
import quemMeAjuda.Entidades.PedidoDeAjuda.PedidoDeAjuda;

/**
 * Classe Sistema que tem os Controllers de Aluno e Tutoria 
 * e a implementação dos métodos de armazenamento de dados.
 * 
 * @author Matheus, Lukas, Wesley
 *
 */
public class Sistema {
	
	private ControllerAlunos controladorAlunos;
	private ControllerTutoria controladorTutoria;
	
	public Sistema() {
		controladorAlunos = new ControllerAlunos();
		controladorTutoria = new ControllerTutoria();
	}
	
	/**
	 * Recupera o mapa de alunos do ControllerAlunos
	 * @return Map<String, Alunos> 
	 */
	public Map<String, Aluno> getAlunos() {
		return ControllerAlunos.getAlunos();
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
	public void cadastrarAluno(String  nome, String matricula, int codigoCurso, String telefone, String email) throws Exception {
		try{controladorAlunos.cadastrarAluno(nome, matricula, codigoCurso, telefone, email);}
		catch(Exception e) {throw new Exception(e.getMessage());
		}
	}
	
	/**
	 * Lista os Alunos registrados no sistema
	 * @return String
	 */
	public String listarAlunos() {return controladorAlunos.listarAlunos();}
	
	/**
	 * Recupera um objeto Aluno registrado no sistema
	 * @param matricula String
	 * 		Matricula do Aluno a ser recuperado 
	 * @return String
	 * 		Formato String do Aluno a ser recuperado
	 * 		(matricula + " - " + nome + " - " + codigoCurso + " - " + telefone + " - " +  email)
	 * @throws Exception
	 */
	public String recuperaAluno(String matricula) throws Exception {return controladorAlunos.recuperaAluno(matricula);}
	
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
	public String getInfoAluno(String matricula, String atributo) throws Exception {return controladorAlunos.getInfoAluno(matricula, atributo);}
	
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
	public void tornarTutor(String matricula, String disciplina, int proficiencia) throws Exception {controladorAlunos.tornarTutor(matricula, disciplina, proficiencia);}
	
	/**
	 * Recupera o Aluno, se for tutor, registrado no sistema
	 * @param matricula String
	 * 		Matricula do tutor a ser recuperado
	 * @return String
	 * @throws Exception
	 */
	public String recuperaTutor(String matricula) throws Exception {return controladorAlunos.recuperaTutor(matricula).toString();}
	
	/**
	 * Lista todos os Alunos tutores registrados no sistema
	 * @return String
	 */
	public String listarTutores() {return controladorAlunos.listarTutores();}
	
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
	public void cadastrarHorario(String email, String horario, String dia) throws Exception {controladorAlunos.cadastrarHorario(email, horario, dia);}
	
	/**
	 * Registra um local de atendimento que o Aluno(tutor) tem disponível para atendimento
	 * (não relacionado, diretamente com o horário)
	 * @param email
	 * @param local
	 * @throws Exception
	 */
	public void cadastrarLocalDeAtendimento(String email, String local) throws Exception {controladorAlunos.cadastrarLocalDeAtendimento(email, local);}
	
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
	public boolean consultaHorario(String email, String horario, String dia) {return controladorAlunos.consultaHorario(email, horario, dia);}
	
	/**
	 * Verifica se um Aluno(tutor) tem o dia (parâmetro) disponível
	 * @param email String
	 * 		E-mail do tutor a realizar a verificação
	 * @param local String
	 * 		Local a verficar
	 * @return
	 */
	public boolean consultaLocal(String email, String local) {return controladorAlunos.consultaLocal(email, local);}
	
	/**
	 * Cria um novo objeto PedidoDeAjudaPresencial e implementa qual tutor está sendo associado ao pedido.
	 * O novo objeto é adicionado à lista de pedidos de ajuda do sistema.
	 * @param matrAluno String
	 *		matricula do tutor a ajudar
	 * @param disciplina String
	 * 		disciplina que o aluno deseja ajuda
	 * @param horario String
	 * 		horario que o aluno contratante quer a ajuda
	 * @param dia String static
	 * 		dia que o aluno contratante quer a ajuda
	 * @param localInteresse
	 * 		local que o aluno contratante quer a ajuda
	 * @return
	 * @throws Exception 
	 */
	public int pedirAjudaPresencial(String matrAluno, String disciplina, String horario, String dia, String localInteresse) throws Exception {
		return controladorTutoria.pedirAjudaPresencial(matrAluno, disciplina, horario, dia, localInteresse);
	}

	/**
	 * Registra um novo objeto PedidoDeAjudaOnline no sistema, ficando disponível para ajuda
	 * @param matrAluno String
	 * 		Matricula do Aluno que registra o pedido
	 * @param disciplina String
	 * 		Disciplina que o Aluno precisa de ajuda
	 * @return int
	 * 		iD do objeto PedidoDeAjudaOnline registrado
	 * @throws Exception
	 */
	public int pedirAjudaOnline(String matrAluno, String disciplina) throws Exception {return controladorTutoria.pedirAjudaOnline(matrAluno, disciplina);}

	/**
	 * Recupera um tutor que está associado ao pedido de ajuda (registrado no sistema)
	 * @param idAjuda int
	 * 		iD do objeto PedidoDeAjuda registrado no Sistema
	 * @return String
	 * 		Objeto Aluno(tutor) em String 
	 * @throws Exception
	 */
	public String pegarTutor(int idAjuda) throws Exception {return controladorTutoria.pegaTutor(idAjuda);}

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
	public String getInfoAjuda(int idAjuda, String atributo) throws Exception {return controladorTutoria.getInfoAjuda(idAjuda, atributo);}

	/**
	 * Avalia um Aluno(tutor) que forneceu ajuda
	 * @param idAjuda int
	 * 		iD do PedidoDeAjuda que o tutor a ser avaliado prestou serviço
	 * @param nota int
	 * 		Nota que o tutor irá receber por seu serviço
	 * @throws Exception
	 */
	public void avaliarTutor(int idAjuda, int nota) throws Exception {controladorTutoria.avaliarTutor(idAjuda, nota);}
	
	/**
	 * Recupera a nota do Aluno(tutor) dado no parametro
	 * @param matriculaTutor String
	 * 		Matricula do tutor registrado no sistema
	 * @return String
	 */
	public String pegarNota(String matriculaTutor) {return controladorAlunos.pegarNota(matriculaTutor);}

	/**
	 * Recupera o nível do Aluno(tutor) dado no parametro
	 * @param matriculaTutor String
	 * 		Matricula do tutor registrado no sistema
	 * @return String
	 */	
	public String pegarNivel(String matriculaTutor) {return controladorAlunos.pegarNivel(matriculaTutor);}

	/**
	 * Doa a um Aluno(tutor) um valor em centavos
	 * @param matriculaTutor String
	 * 		Matricula do tutor a receber a doação
	 * @param totalCentavos int
	 * 		Valor da doação em centavos
	 * @throws Exception
	 */
	public void doar(String matriculaTutor, int totalCentavos) throws Exception {controladorTutoria.doar(matriculaTutor, totalCentavos);}

	/**
	 * Recupera o total de dinheiro, em centavos, do Aluno(tutor) dado no parametro
	 * @param emailTutor String
	 * 		email do tutor registrado no sistema
	 * @return String
	 */
	public int totalDinheiroTutor(String emailTutor) throws Exception {return controladorAlunos.totalDinheiroTutor(emailTutor);}

	/**
	 * Recupera o caixa do Sistema de Tutoria
	 * @return int
	 */
	public int totalDinheiroSistema() {return controladorTutoria.getCaixa();}

	/**
	 * Ordem atribuída aos Alunos do sistema
	 * @param atributo String
	 */
	public void configurarOrdem(String atributo) {controladorAlunos.configurarOrdem(atributo);}
	
	/**
	 * Salva os Alunos, Tutores, Pedidos de Ajuda e Caixa em arquivos distintos.
	 */
	public void salvar() {
		try {
		    File arquivoAlunosETurores = new File("Quem Me Ajuda - Alunos e Tutores.txt");
		    File arquivoPedidosDeAjuda = new File("Quem Me Ajuda - Pedidos De Ajuda.txt");
		    File arquivoCaixa = new File("Quem Me Ajuda - Caixa.txt");
		    ObjectOutputStream writerAlunosETurores = new ObjectOutputStream(new FileOutputStream(arquivoAlunosETurores));
		    ObjectOutputStream writerPedidosDeAjuda = new ObjectOutputStream(new FileOutputStream(arquivoPedidosDeAjuda));
		    ObjectOutputStream writerCaixa = new ObjectOutputStream(new FileOutputStream(arquivoCaixa));
		    writerAlunosETurores.writeObject(getAlunos());
		    writerAlunosETurores.close();
		    writerPedidosDeAjuda.writeObject(controladorTutoria.getPedidos());
		    writerPedidosDeAjuda.close();
		    writerCaixa.writeObject(controladorTutoria.getCaixa());
		    writerCaixa.close();
		} catch(IOException e) {e.printStackTrace();}
	}
	
	/**
	 * Carrega os arquivos que guardam os Alunos, Tutores, Pedidos de Ajuda e Caixa
	 */
	@SuppressWarnings("unchecked")
	public void carregar() {
		ObjectInputStream arquivo = null;
		try {
			arquivo = new ObjectInputStream(new FileInputStream("Quem Me Ajuda - Alunos e Tutores.txt"));
			try {ControllerAlunos.carregaMapaAlunos((Map<String, Aluno>) arquivo.readObject());} 
			catch (ClassNotFoundException e) {e.printStackTrace();}
			arquivo = new ObjectInputStream(new FileInputStream("Quem Me Ajuda - Pedidos De Ajuda.txt"));
			try {controladorTutoria.carregaMapaPedidos((Map<Integer, PedidoDeAjuda>) arquivo.readObject());} 
			catch (ClassNotFoundException e) {e.printStackTrace();}
			arquivo = new ObjectInputStream(new FileInputStream("Quem Me Ajuda - Caixa.txt"));
			try {controladorTutoria.carregaCaixa((int) arquivo.readObject());} 
			catch (ClassNotFoundException e) {e.printStackTrace();}
		} catch(IOException e) {e.printStackTrace();}	 
	}
	
	/**
	 * Limpa os arquivos que guardam os Alunos, Tutores, Pedidos de Ajuda e Caixa
	 */
	public void limpar() {
		File arquivo = null;
		try {
			arquivo = new File("Quem Me Ajuda - Alunos e Tutores.txt");
			arquivo.delete();
			arquivo = new File("Quem Me Ajuda - Pedidos De Ajuda.txt");
			arquivo.delete();
			arquivo = new File("Quem Me Ajuda - Caixa.txt");
			arquivo.delete();
		} catch(Exception e) {e.printStackTrace();}
	}
}