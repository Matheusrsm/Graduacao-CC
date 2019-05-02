package lab4.auxiliares;

import lab4.entidades.ControleAlunos;
import lab4.entidades.ControleGrupos;

/**
 * Representa��o de um Controller que gerencia os Controles de Grupo e Aluno
 * e releciona-se com a classe Sistema, a interface com o usu�rio.
 *  
 * @author Matheus Silva - 117110412
 */
public class Controller {

	private Menu input = new Menu();
	private ControleAlunos alunos = new ControleAlunos();
	private ControleGrupos grupos = new ControleGrupos();
	
	/**
	 * M�todo que invoca o Menu e faz a leitura da opera��o desejada
	 * pelo usu�rio.
	 * 
	 * @return uma String que representa a opera��o.
	 */
	public String leituraOperacao() {
		return input.leOperacao();
	}
	
	/**
	 * M�todo que invoca o Menu e o Controle de Alunos, faz a leitura dos dados
	 * do usu�rio e realiza ou n�o a opera��o de Cadastro de um Aluno.
	 */
	public void cadastrarAluno() {
		String matricula = input.leString("Matr�cula: ");
		String nome = input.leString("Nome: ");
		String curso = input.leString("Curso: ");
		System.out.println(alunos.cadastraAluno(matricula, nome, curso));
	}
	
	/**
	 * M�todo que invoca o Menu e o Controle de Alunos, faz a leitura dos dados
	 * do usu�rio e realiza ou n�o a opera��o de Consulta de um Aluno.
	 */
	public void consultarAluno() {
		String matricula_procurada = input.leString("Matr�cula: ");
		System.out.println(alunos.consultaAluno(matricula_procurada));
	}
	
	/**
	 * M�todo que invoca o Menu e o Controle de Grupos, faz a leitura dos dados
	 * do usu�rio e realiza ou n�o a opera��o de Cadastro de um Grupo.
	 */
	public void cadastrarGrupo() {
		String nome_grupo = input.leString("Grupo: ");
		System.out.println(grupos.cadastraGrupo(nome_grupo));
	}
	
	/**
	 * M�todo que invoca o Menu e o Controle de Grupos, faz a leitura dos dados
	 * do usu�rio e realiza ou n�o duas opera��es:
	 * Alocar um Aluno em um Grupo ou Imprimir um Grupo.
	 */
	public void alocarOuImprimir() {
		String opcao = input.leString("(A)locar Aluno ou (I)mprimir Grupo? ");
		if(opcao.equals("A")) {
			String matriculaAluno = input.leString("Matr�cula: ");
			String nomeGrupo = input.leString("Grupo: ");
			System.out.println(grupos.alocaAlunoNoGrupo(nomeGrupo, matriculaAluno, alunos));
		}
		else {
			String nomeGrupo = input.leString("Grupo: ");
			System.out.println(grupos.imprimeGrupo(nomeGrupo));
		}
	}
	
	/**
	 * M�todo que invoca o Menu e o Controle de Alunos, faz a leitura dos dados
	 * do usu�rio e realiza ou n�o a opera��o de Registro de um Aluno que respondeu.
	 */
	public void registrar() {
		String matriculaAlunoRespondeu = input.leString("Matr�cula: ");
		System.out.println(alunos.registraAlunoRespondeu(matriculaAlunoRespondeu));
	}
	
	/**
	 * M�todo que invoca o Controle de Alunos e realiza ou n�o a opera��o de Imprimir
	 * os Alunos que responderam.
	 */
	public void imprimir() {
		System.out.println(alunos.imprimirAlunosResponderam());
	}
}