package lab4.entidades;

import java.util.HashMap;
import java.util.Set;

import lab4.auxiliares.Excecoes;

/**
 * Representação de um controle dos Grupos de estudo que contém o mapeamento do objeto
 * Grupo, pelo seu nome e uma lista de. Também pode cadastrar, imprimir, 
 * exibir um grupo e alocar alunos.
 * 
 * @author Matheus Silva - 117110412
 */
public class ControleGrupos {
	
	private HashMap<String, Grupo> controleGrupos = new HashMap<>();
	private Set<String> nomesGrupos = controleGrupos.keySet();
	private Excecoes excecoes = new Excecoes();
	
	/**
	 * Método que invoca o construtor de um grupo, e o cadastra no mapa, com seu
	 * nome como chave, caso ele não tenha sido cadastrado ainda.
	 * 
	 * @param nome
	 *            O nome do grupo.
	 * @return uma String que diz se o grupo foi cadastrado ou se ele já havia 
	 * sido cadastrado.
	 */
	public String cadastraGrupo(String nome) {
		excecoes.nomeDoGrupoInvalido(nome);
		if(grupoExiste(nome)) {return "GRUPO JÁ CADASTRADO!";}
		controleGrupos.put(nome, new Grupo(nome));
		return "CADASTRO REALIZADO!";
	}
	
	/**
	 * Aloca um aluno a um grupo utilizando o método alocaAluno da classe Grupo. 
	 * Os objetos Grupo e Aluno são acessados pela sua matrícula e nome.
	 * 
	 * @param matriculaAluno
	 *            A matrícula do aluno a ser alocado.
	 * @param nomeGrupo
	 *            O nome do grupo que vai receber o aluno.
	 * @param controleAlunos
	 *            O Controle de Alunos que será usado pelo método alocaAluno da classe Grupo.
	 * @return uma String dizendo se o grupo não é cadastrado, se o aluno foi alocado
	 * ou se o aluno não está cadastrado no controle.
	 */
	public String alocaAlunoNoGrupo(String nomeGrupo, String matriculaAluno, ControleAlunos controleAlunos) {
		excecoes.matriculaInvalida(matriculaAluno);
		excecoes.nomeDoGrupoInvalido(nomeGrupo);
		if(!grupoExiste(nomeGrupo)) {return "Grupo não cadastrado.";}
		else {return getGrupo(nomeGrupo).alocaAluno(matriculaAluno, controleAlunos);}
	}
	
	/**
	 * Imprime a representação em String de um grupo, acessando-o pelo seu nome.
	 * E que utiliza a função imprimirGrupo da classe Grupo.
	 * 
	 * @param nomeGrupo
	 *            O nome do grupo a ser impresso.
	 * @return a representação em String do grupo ou uma String dizendo que o grupo
	 * não foi cadastrado.
	 */
	public String imprimeGrupo(String nomeGrupo) {
		excecoes.nomeDoGrupoInvalido(nomeGrupo);
		if(!grupoExiste(nomeGrupo)) {return "Grupo não cadastrado.";}
		else {return getGrupo(nomeGrupo).imprimirGrupo();}
	}
	
	/**
	 * Método que verifica a existência de um Grupo no mapa pelo seu nome.
	 * 
	 * @param nomeDoGrupo
	 *            O nome do grupo.
	 * @return um valor booleano indicando a existência do grupo.
	 */
	private boolean grupoExiste(String nomeDoGrupo) {
		for(String nome: nomesGrupos) {return nomeDoGrupo.equalsIgnoreCase(nome);}
		return false;
	}

	/**
	 * Método que retorna o Grupo procurado no mapa pelo seu nome.
	 * 
	 * @param nomeGrupoProcurado
	 *            O nome do grupo procurado.
	 * @return o Grupo procurado.
	 */
	private Grupo getGrupo(String nomeGrupoProcurado) {
		for(String nome: nomesGrupos) {
			if(nomeGrupoProcurado.equalsIgnoreCase(nome)) {return controleGrupos.get(nome);}
		}
		return null;
	}
}