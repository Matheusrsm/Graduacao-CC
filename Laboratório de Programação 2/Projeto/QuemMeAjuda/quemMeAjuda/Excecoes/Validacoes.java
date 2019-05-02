package quemMeAjuda.Excecoes;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import quemMeAjuda.Entidades.Aluno.*;
import quemMeAjuda.Entidades.PedidoDeAjuda.PedidoDeAjuda;

/**
 * Classe que verifica caminhos de "erro" e lanca excecoes.
 * 
 * @author Matheus, Lukas, Wesley
 *
 */
public class Validacoes implements Serializable{
		
	private static final long serialVersionUID = -4420266001017456149L;
	
	/**
	 * Verifica se um nome eh vazio ou nulo.
	 * Se for vazio, lanca um DadoInvalidoException com a mensagem passada no parametro.
	 * Se for nulo, lanca um DadoNuloException com a mensagem passada no parametro.
	 * @param nome nome a ser validado
	 * @param msg mensagem lancada na excecao
	 * @throws Exception
	 */
	public void nomeInvalidoOuNulo(String nome, String msg) throws Exception {
		if(nome == null) throw new DadoNuloException(msg + "Nome nao pode ser vazio ou nulo");
		if(nome.trim().isEmpty()) throw new DadoInvalidoException(msg + "Nome nao pode ser vazio ou nulo");
	}
	
	/**
	 * Verifica se um email eh invalido ou nulo.
	 * Um email eh invalido quando: Eh vazio, nao possui arroba, possui arroba no inicio, possui arroba no final
	 * Se for vazio, lanca um DadoInvalidoException com a mensagem passada no parametro.
	 * Se for nulo, lanca um DadoNuloException com a mensagem passada no parametro.
	 * @param email email a ser validado
	 * @param msg mensagem lancada na excecao 
	 * @throws Exception
	 */
	public void emailInvalidoOuNulo(String email, String msg) throws Exception {
		String msgEmailInvalido = "Email invalido";
		if(email == null) throw new DadoNuloException(msg + msgEmailInvalido);
		int a = 0;
		boolean erro = false;
		if(email.trim().isEmpty()) {
			erro = true;
			msgEmailInvalido = "email nao pode ser vazio ou em branco";
		}
		else if(!email.contains("@")) erro = true;
		else {
			a = email.indexOf("@");
			if(a == 0 || a == email.length() - 1) erro = true;
		}
		if(erro) throw new DadoInvalidoException(msg + msgEmailInvalido);
	}
	
	/**
	 * Verifica se algum tutor possui o email passado no parametro.
	 * Se nao possuir tutor com esse email eh lancada um DadoInvalidoException
	 * @param email email a ser verificado.
	 * @param alunos mapa dos alunos que verificamos os emails.
	 * @param msg mensagem lancada na excecao.
	 * @throws Exception
	 */
	public void tutorEmailNaoCadastrado(String email, Map<String, Aluno> alunos, String msg) throws Exception {
		List<String> emails = new ArrayList<String>();
		for (Aluno a : alunos.values()) 
			if (a.isTutor()) emails.add(a.getEmail());
		if (!emails.contains(email)) throw new DadoInvalidoException(msg);
	}
	 /**
	  * Verifica se um Tutor ja tutora na disciplina passada.
	  * Isso eh feito pegando somente a lista de disciplinas do Tutore verificando.
	  * Se ja for tutor da disciplina passada eh lancada um DadoInvalidoException com a mensagem do parametro.
	  * @param disciplina disciplina a verificar se o Tutor possui
	  * @param disciplinas lista das disciplinas do tutor
	  * @param msg mensagem lancada na excecao.
	  * @throws Exception
	  */
	public void disciplinaJaEhTutor(String disciplina, List<Disciplina> disciplinas, String msg) throws Exception {
		for(Disciplina d: disciplinas)
			if(d.getNome().equals(disciplina)) throw new DadoInvalidoException(msg + "Ja eh tutor dessa disciplina");
	}
	
	/**
	 * Verifica se a proficiencia eh invalida.
	 * Uma proficiencia eh invalida quando eh maior que 5 ou menor que 0(negativa).
	 * @param proficiencia proficiencia a ser validada
	 * @param msg mensagem lancada na excecao.
	 * @throws Exception
	 */
	public void proficienciaInvalida(int proficiencia, String msg) throws Exception{
		if(proficiencia < 0 || proficiencia > 5) throw new DadoInvalidoException(msg + "Proficiencia invalida");
	}
	
	/**
	 * Verifica se um horario eh invalido.
	 * Um horario eh invalido se a hora ou o dia da semana estiverem vazios.
	 * @param horario representacao textual do horario
	 * @param dia representacao textual do dia da semana 
	 * @param msg mensagem lancada na excecao.
	 * @throws Exception
	 */
	public void horarioInvalido(String horario, String dia, String msg) throws Exception {
		String msgErroHorario = "horario nao pode ser vazio ou em branco";
		String msgErroDia = "dia nao pode ser vazio ou em branco";
		
		if(horario.trim().isEmpty()) throw new DadoInvalidoException(msg + msgErroHorario);
		else if (dia.trim().isEmpty()) throw new DadoInvalidoException(msg + msgErroDia);
	}
	
	/**
	 * Verifica se um local eh invalido ou nulo.
	 * Um local eh invalido quando eh vazio.
	 * Se for vazio, lanca um DadoInvalidoException com a mensagem passada no parametro.
	 * Se for nulo, lanca um DadoNuloException com a mensagem passada no parametro.
	 * @param local representacao textual do local.
	 * @param msg mensagem lancada na excecao.
	 * @throws Exception
	 */
	public void localInvalidoOuNulo(String local, String msg) throws Exception {
		if(local == null) throw new DadoNuloException(msg + "local nao pode ser vazio ou em branco");
		if(local.trim().isEmpty()) throw new DadoInvalidoException(msg + "local nao pode ser vazio ou em branco");
	}
	
	/**
	 * Verifica se um aluno ja possui a matricula passada no parametro.
	 * Se sim, lanca um DadoInvalidoException com a mensagem passada no parametro.
	 * @param matricula matricula a ser verificada.
	 * @param alunos mapa dos alunos.
	 * @param msg mensagem lancada na excecao.
	 * @throws Exception
	 */
	public void matriculaJaCadastrada(String matricula, Map<String, Aluno> alunos, String msg) throws Exception {
		if(alunos.containsKey(matricula)) throw new DadoInvalidoException(msg + "Aluno de mesma matricula ja cadastrado");
	}
	
	/**
	 * Verifica se um aluno com a matricula passada no parametro existe.
	 * Se não existir, lanca um DadoInvalidoException com a mensagem passada no parametro.
	 * @param matricula matricula a ser verificada
	 * @param alunos mapa dos alunos.
	 * @param msg mensagem lancada na excecao.
	 * @throws Exception
	 */
	public void alunoNaoCadastrado(String matricula, Map<String, Aluno> alunos, String msg) throws Exception {
		if(!alunos.containsKey(matricula)) throw new DadoInvalidoException(msg + "Aluno nao encontrado");
	}
	
	/**
	 * Verifica se um Tutor com a matricula passa existe.
	 * Se não existir, lanca um DadoInvalidoException com a mensagem passada no parametro.
	 * @param matricula matricula do Tutor a ser verificada
	 * @param alunos mapa de alunos.
	 * @param msg mensagem lancada na excecao.
	 * @throws Exception
	 */
	public void naoEhTutor(String matricula, Map<String, Aluno> alunos, String msg) throws Exception {
		if(!alunos.containsKey(matricula)) throw new DadoInvalidoException(msg + "Tutor nao encontrado");
	}
	
	/**
	 * Verifica se uma matricula eh vazia.
	 * Se for vazia, lanca um DadoInvalidoException com a mensagem passada no parametro.
	 * @param matricula matricula a ser validada.
	 * @param msg mensagem lancada na excecao.
	 * @throws Exception
	 */
	public void matriculaVazia(String matricula, String msg) throws Exception {
		if(matricula.trim().isEmpty()) throw new DadoInvalidoException(msg + "matricula de aluno nao pode ser vazio ou em branco");
	}
	
	/**
	 * Verifica se a disciplina passada no parametro eh vazia.
	 * Se for vazia, lanca um DadoInvalidoException com a mensagem passada no parametro.
	 * @param disciplina representacao textual da disciplina a ser validada
	 * @param msg mensagem lancada na excecao.
	 * @throws Exception
	 */
	public void disciplinaVazia(String disciplina, String msg) throws Exception {
		if(disciplina.trim().isEmpty()) throw new DadoInvalidoException(msg + "disciplina nao pode ser vazio ou em branco");
	}
	
	/**
	 * Verifica se o horario passado no parametro eh vazio.
	 * Se for vazio, lanca um DadoInvalidoException com a mensagem passada no parametro.
	 * @param horario representacao textual do horario a ser verificado
	 * @param msg mensagem lancada na excecao.
	 * @throws Exception
	 */
	public void horarioVazio(String horario, String msg) throws Exception {
		if(horario.trim().isEmpty()) throw new DadoInvalidoException(msg + "horario nao pode ser vazio ou em branco");
	}
	
	/**
	 * Verifica se o dia passado no parametro eh vazio.
	 * Se for vazio, lanca um DadoInvalidoException com a mensagem passada no parametro.
	 * @param dia representacao textual do dia da semana a ser verificado
	 * @param msg mensagem lancada na excecao.
	 * @throws Exception
	 */
	public void diaVazio(String dia, String msg) throws Exception {
		if(dia.trim().isEmpty()) throw new DadoInvalidoException(msg + "dia nao pode ser vazio ou em branco");
	}
	
	/**
	 * Verifica se o local passado no parametro eh vazio.
	 * Se for vazio, lanca um DadoInvalidoException com a mensagem passada no parametro.
	 * @param local representacao textual do local a ser verificada
	 * @param msg mensagem lancada na excecao.
	 * @throws Exception
	 */
	public void localVazio(String local, String msg) throws Exception {
		if(local.trim().isEmpty()) throw new DadoInvalidoException(msg + "local de interesse nao pode ser vazio ou em branco");
	}
	
	/**
	 * Verifica de o ID de um pedido de ajuda eh invalido.
	 * Um ID eh invalido se for negativo ou se ja existe um pedido de ajuda com esse ID.
	 * @param id id a ser verificado
	 * @param pedidos mapa dos pedidos de ajuda
	 * @param msg mensagem lancada na excecao.
	 * @throws Exception
	 */
	public void idAjudaInvalido(int id, Map<Integer, PedidoDeAjuda> pedidos, String msg) throws Exception {
		if(id < 0) throw new DadoInvalidoException(msg + "id nao pode menor que zero ");
		if(!(pedidos.containsKey(id))) throw new DadoInvalidoException(msg + "id nao encontrado ");
	}
	
	/**
	 * Verifica se o atributo de um pedido de ajuda passado no parametro eh invalido.
	 * Ele eh invalido se for vazio ou se não for igual a disciplina, horario, dia ou localInteresse. 
	 * @param atributo atributo a ser verificado
	 * @param msg mensagem lancada na excecao.
	 * @throws Exception
	 */
	public void atributoInvalido(String atributo, String msg) throws Exception {
		if(atributo.trim().isEmpty()) throw new DadoInvalidoException(msg + "atributo nao pode ser vazio ou em branco");
		if(!atributo.equals("disciplina") && !atributo.equals("horario") && !atributo.equals("dia") && !atributo.equals("localInteresse")) 
			throw new DadoInvalidoException(msg + "atributo nao encontrado");
	}
	
	/**
	 * Verifica se uma nota de avaliacao eh invalida.
	 * Um nota eh invalida quando for negativa ou maior que 5.
	 * @param nota nota a ser verificada
	 * @param msg mensagem lancada na excecao.
	 * @throws Exception
	 */
	public void notaInvalida(double nota, String msg) throws Exception {
		if(nota < 0) throw new DadoInvalidoException(msg + "nota nao pode ser menor que 0");
		else if(nota > 5) throw new DadoInvalidoException(msg + "nota nao pode ser maior que 5");
	}
	
	/**
	 * Verifica se um email de um tutor eh invalido ou nulo.
	 * Um email eh invalido nesse caso, se for vazio.
	 * Se o email for vazio, lanca um DadoInvalidoException com a mensagem passada no parametro.
	 * Se o email for nulo, lanca um DadoNuloException com a mensagem passada no parametro.
	 * @param email email a ser verificado
	 * @param msg mensagem lancada na excecao.
	 * @throws Exception
	 */
	public void emailTutorInvalidoOuNulo(String email, String msg) throws Exception {
		if(email == null) throw new DadoNuloException(msg + "emailTutor nao pode ser vazio ou nulo");
		else if(email.trim().isEmpty()) throw new DadoInvalidoException(msg + "emailTutor nao pode ser vazio ou nulo");
	}
	
	/**
	 * Verifica se o total de centavos eh negativo.
	 * Se for negativo, lanca um DadoInvalidoException com a mensagem passada no parametro.
	 * @param totalCentavos total de centavos(inteiro) a ser verificado.
	 * @param msg mensagem lancada na excecao.
	 * @throws Exception
	 */
	public void totalCentavosInvalido(int totalCentavos, String msg) throws Exception {
		if(totalCentavos < 0) throw new DadoInvalidoException(msg + "totalCentavos nao pode ser menor que zero");
	}
	
	/**
	 * Verifica se a partir do ID se um pedido de ajuda eh fechado ou nao.
	 * Se for fechado, lanca um DadoInvalidoException com a mensagem passada no parametro.
	 * @param id id do pedido de ajuda a ser verificado.
	 * @param pedidos mapa dos pedidos de ajuda.
	 * @param msg mensagem lancada na excecao.
	 * @throws Exception
	 */
	public void ajudaJaFechada(int id, Map<Integer, PedidoDeAjuda> pedidos, String msg) throws Exception {
		if(pedidos.get(id).getFinalizacao()) throw new DadoInvalidoException(msg + "Ajuda ja avaliada");
	}
}