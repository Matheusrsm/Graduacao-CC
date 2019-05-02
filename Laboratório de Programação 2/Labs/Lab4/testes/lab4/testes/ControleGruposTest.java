package lab4.testes;

import static org.junit.Assert.*;
import org.junit.Test;

import lab4.entidades.ControleAlunos;
import lab4.entidades.ControleGrupos;

/**
 * Classe de Testes do Controle de Grupos.
 * 
 * @author Matheus Silva - 117110412
 */
public class ControleGruposTest {

	private ControleGrupos controleGrupos = new ControleGrupos();
	private ControleAlunos controleAlunos = new ControleAlunos();
	
	// Testa o Cadastro de um Grupo válido.
	@Test
	public void testCadastraGrupo() {
		assertEquals("CADASTRO REALIZADO!", controleGrupos.cadastraGrupo("LP2"));
	}

	// Testa o Cadastro de um Grupo já Cadastrado.
	@Test
	public void testCadastraGrupoJACADASTRADO() {
		assertEquals("CADASTRO REALIZADO!", controleGrupos.cadastraGrupo("LP2"));
		assertEquals("GRUPO JÁ CADASTRADO!", controleGrupos.cadastraGrupo("LP2"));
	}
	
	// Testa o Cadastro de um Grupo com nome inválido.
	@Test(expected = IllegalArgumentException.class)
	public void testCadastraGrupoARGUMENTOINVALIDO() {
		controleGrupos.cadastraGrupo("  ");
		controleGrupos.cadastraGrupo("");
	}
	
	// Testa o Cadastro de um Grupo com nome nulo.
	@Test(expected = NullPointerException.class)
	public void testCadastraGrupoARGUMENTONULO() {
		controleGrupos.cadastraGrupo(null);
	}
	
	// Testa a Alocação de um Aluno cadastrado em um Grupo.
	@Test
	public void testAlocaAlunoNoGrupo() {
		controleAlunos.cadastraAluno("117110412", "Matheus Silva", "CC");
		controleAlunos.cadastraAluno("117110413", "Marcos Medeiros", "CC");
		controleGrupos.cadastraGrupo("LP2");
		assertEquals("ALUNO ALOCADO!", controleGrupos.alocaAlunoNoGrupo("lp2", "117110412", controleAlunos));
		assertEquals("ALUNO ALOCADO!", controleGrupos.alocaAlunoNoGrupo("lp2", "117110413", controleAlunos));
	}
	
	// Testa a Alocação de um Aluno já alocado no Grupo.
	@Test
	public void testAlocaAlunoNoGrupoDENOVO() {
		controleAlunos.cadastraAluno("117110412", "Matheus Silva", "CC");
		controleGrupos.cadastraGrupo("LP2");
		assertEquals("ALUNO ALOCADO!", controleGrupos.alocaAlunoNoGrupo("lp2", "117110412", controleAlunos));
		assertEquals("ALUNO ALOCADO!", controleGrupos.alocaAlunoNoGrupo("lp2", "117110412", controleAlunos));
	}
	
	// Testa a Alocação de um Aluno não cadastrado em um Grupo.
	@Test
	public void testAlocaAlunoNoGrupoNAOCADASTRADO() {
		controleAlunos.cadastraAluno("117110412", "Matheus Silva", "CC");
		assertEquals("Grupo não cadastrado.", controleGrupos.alocaAlunoNoGrupo("lp2", "117110412", controleAlunos));
		controleGrupos.cadastraGrupo("LP2");
		assertEquals("Aluno não cadastrado.", controleGrupos.alocaAlunoNoGrupo("lp2", "117110413", controleAlunos));		
	}
	
	// Testa a Alocação de um Aluno com nome do Grupo ou matrícula do Aluno inválidos.
	@Test(expected = IllegalArgumentException.class)
	public void testAlocaAlunoNoGrupoARGUMENTOINVALIDO() {
		controleGrupos.alocaAlunoNoGrupo("   ", "117110412", controleAlunos);
		controleGrupos.alocaAlunoNoGrupo("lp2", "", controleAlunos);
	}

	// Testa a Alocação de um Aluno com nome do Grupo ou matrícula do Aluno nulos.
	@Test(expected = NullPointerException.class)
	public void testAlocaAlunoNoGrupoARGUMENTONULO() {
		controleGrupos.alocaAlunoNoGrupo(null, "117110412", controleAlunos);
		controleGrupos.alocaAlunoNoGrupo("lp2", null, controleAlunos);
	}
	
	// Testa a Impressão de um Grupo cadastrado.
	@Test
	public void testImprimeGrupo() {
		controleAlunos.cadastraAluno("117110412", "Matheus Silva", "CC");
		controleAlunos.cadastraAluno("117110413", "Marcos Medeiros", "CC");
		controleAlunos.cadastraAluno("117110414", "Lucas Ramos", "CC");
		controleGrupos.cadastraGrupo("LP2");
		controleGrupos.alocaAlunoNoGrupo("lp2", "117110412", controleAlunos);
		controleGrupos.alocaAlunoNoGrupo("lp2", "117110413", controleAlunos);
		controleGrupos.alocaAlunoNoGrupo("lp2", "117110414", controleAlunos);
		String saida = "Alunos do grupo LP2:\n* 117110412 - Matheus Silva - CC\n* 117110413 - Marcos Medeiros - CC\n* 117110414 - Lucas Ramos - CC\n";
		assertEquals(saida, controleGrupos.imprimeGrupo("lp2"));
	}
	
	// Testa a Impressão de um Grupo vazio.
	@Test
	public void testImprimeGrupoVAZIO() {
		controleGrupos.cadastraGrupo("LP2");
		assertEquals("Alunos do grupo LP2:\n", controleGrupos.imprimeGrupo("lp2"));
	}
	
	// Testa a Impressão de um Grupo não cadastrado.
	@Test
	public void testImprimeGrupoNAOCADASTRADO() {
		assertEquals("Grupo não cadastrado.", controleGrupos.imprimeGrupo("lp2"));
	}
	
	// Testa a Impressão de um Grupo inválido.
	@Test(expected = IllegalArgumentException.class)
	public void testImprimeGrupoARGUMENTOINVALIDO() {
		controleGrupos.imprimeGrupo("");
		controleGrupos.imprimeGrupo("   ");
	}

	// Testa a Impressão de um Grupo nulo.
	@Test(expected = NullPointerException.class)
	public void testImprimeGrupoARGUMENTONULO() {
		controleGrupos.imprimeGrupo(null);
	}

}
