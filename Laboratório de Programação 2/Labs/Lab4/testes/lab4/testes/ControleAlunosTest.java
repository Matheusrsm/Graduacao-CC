package lab4.testes;

import static org.junit.Assert.*;
import org.junit.Test;
import lab4.entidades.ControleAlunos;

/**
 * Classe de Testes do Controle de Alunos.
 * 
 * @author Matheus Silva - 117110412
 */
public class ControleAlunosTest {

	private ControleAlunos controleAlunos = new ControleAlunos();
	
	// Testa o Cadastro de um Aluno válido. 
	@Test
	public void testCadastraAluno() {
		assertEquals("CADASTRO REALIZADO!", controleAlunos.cadastraAluno("117110412", "Matheus Silva", "CC"));
		assertEquals("CADASTRO REALIZADO!", controleAlunos.cadastraAluno("117110413", "Marcos Medeiros", "CC"));
	}
	
	// Testa o Cadastro de Alunos com matrículas iguais. 
	@Test
	public void testCadastraAlunosMATRICULASIGUAIS() {
		assertEquals("CADASTRO REALIZADO!", controleAlunos.cadastraAluno("117110412", "Matheus Silva", "CC"));
		assertEquals("MATRÍCULA JÁ CADASTRADA!", controleAlunos.cadastraAluno("117110412", "Marcos Medeiros", "CC"));
	}
	
	// Testa o Cadastro de Aluno com algum argumento inválido. 
	@Test(expected = IllegalArgumentException.class)
	public void testCadastraAlunoARGUMENTOINVALIDO() {
		controleAlunos.cadastraAluno("117110413", "", "CC");
		controleAlunos.cadastraAluno("    ", "Matheus Silva", "CC");
		controleAlunos.cadastraAluno("", "Matheus Silva", "       ");
	}

	// Testa o Cadastro de um Aluno com algum argumento nulo. 
	@Test(expected = NullPointerException.class)
	public void testCadastraAlunoARGUMENTONULO() {
		controleAlunos.cadastraAluno("117110413", null, "CC");
		controleAlunos.cadastraAluno(null, "Matheus Silva", "CC");
		controleAlunos.cadastraAluno("117110412", "Matheus Silva", null);
	}
	
	// Testa a Consulta de um Aluno cadastrado. 
	@Test
	public void testConsultaAluno() {
		controleAlunos.cadastraAluno("117110412", "Matheus Silva", "CC");
		assertEquals("\nAluno: 117110412 - Matheus Silva - CC", controleAlunos.consultaAluno("117110412"));
		controleAlunos.cadastraAluno("117110413", "Marcos Medeiros", "CC");
		assertEquals("\nAluno: 117110413 - Marcos Medeiros - CC", controleAlunos.consultaAluno("117110413"));
	}

	// Testa a Consulta de um Aluno não cadastrado. 
	@Test
	public void testConsultaAlunoNAOCADASTRADO() {
		assertEquals("Aluno não cadastrado.", controleAlunos.consultaAluno("117110412"));
		assertEquals("Aluno não cadastrado.", controleAlunos.consultaAluno("117110413"));
	}
	
	// Testa a Consulta de um Aluno inválido. 
	@Test(expected = IllegalArgumentException.class)
	public void testConsultaAlunoARGUMENTOINVALIDO() {
		controleAlunos.cadastraAluno("117110412", "Matheus Silva", "CC");
		controleAlunos.consultaAluno("");
		controleAlunos.consultaAluno("  ");
	}
	
	// Testa a Consulta de um Aluno nulo.
	@Test(expected = NullPointerException.class)
	public void testConsultaAlunoARGUMENTONULO() {
		controleAlunos.cadastraAluno("117110412", "Matheus Silva", "CC");
		controleAlunos.consultaAluno(null);
	}
	
	// Testa o Registro de um Aluno cadastrado que respondeu.
	@Test
	public void testRegistraAlunoRespondeu() {
		controleAlunos.cadastraAluno("117110412", "Matheus Silva", "CC");
		assertEquals("ALUNO REGISTRADO!", controleAlunos.registraAlunoRespondeu("117110412"));
		controleAlunos.cadastraAluno("117110413", "Marcos Medeiros", "CC");
		assertEquals("ALUNO REGISTRADO!", controleAlunos.registraAlunoRespondeu("117110413"));
	}
	
	// Testa o Registro de um Aluno cadastrado que respondeu mais uma vez.
	@Test
	public void testRegistraAlunoRespondeuDENOVO() {
		controleAlunos.cadastraAluno("117110412", "Matheus Silva", "CC");
		assertEquals("ALUNO REGISTRADO!", controleAlunos.registraAlunoRespondeu("117110412"));
		assertEquals("ALUNO REGISTRADO!", controleAlunos.registraAlunoRespondeu("117110412"));
	}

	// Testa o Registro de um Aluno não cadastrado.
	@Test
	public void testRegistraAlunoNaoCadastrado() {
		assertEquals("Aluno não cadastrado.", controleAlunos.registraAlunoRespondeu("117110412"));
		assertEquals("Aluno não cadastrado.", controleAlunos.registraAlunoRespondeu("117110413"));
	}
	
	// Testa o Registro de um Aluno inválido.
	@Test(expected = IllegalArgumentException.class)
	public void testRegistraAlunoRespondeuARGUMENTOINVALIDO() {
		controleAlunos.registraAlunoRespondeu("");
		controleAlunos.registraAlunoRespondeu("   ");
	}
	
	// Testa o Registro de um Aluno nulo.
	@Test(expected = NullPointerException.class)
	public void testRegistraAlunoRespondeuARGUMENTONULO() {
		controleAlunos.registraAlunoRespondeu(null);
	}
	
	// Testa a Impressão dos Alunos que responderam vazio.
	@Test
	public void testImprimirAlunosResponderamVAZIO() {
		assertEquals("Alunos: \n", controleAlunos.imprimirAlunosResponderam());
	}

	// Testa a Impressão de um Aluno que respondeu.
	@Test
	public void testImprimirAlunosResponderam1ALUNO() {
		controleAlunos.cadastraAluno("117110412", "Matheus Silva", "CC");
		controleAlunos.registraAlunoRespondeu("117110412");
		assertEquals("Alunos: \n1. 117110412 - Matheus Silva - CC\n", controleAlunos.imprimirAlunosResponderam());
	}
	
	// Testa a Impressão de um Aluno que respondeu mais de uma vez.
	@Test
	public void testImprimirAlunosResponderam1ALUNOCOM2REGISTROS() {
		controleAlunos.cadastraAluno("117110412", "Matheus Silva", "CC");
		controleAlunos.registraAlunoRespondeu("117110412");
		controleAlunos.registraAlunoRespondeu("117110412");
		assertEquals("Alunos: \n1. 117110412 - Matheus Silva - CC\n2. 117110412 - Matheus Silva - CC\n", controleAlunos.imprimirAlunosResponderam());
	}
	
	// Testa a Impressão de Alunos que responderam.
	@Test
	public void testImprimirAlunosResponderam() {
		controleAlunos.cadastraAluno("117110412", "Matheus Silva", "CC");
		controleAlunos.cadastraAluno("117110413", "Marcos Medeiros", "CC");
		controleAlunos.cadastraAluno("117110414", "Lucas Ramos", "CC");
		controleAlunos.registraAlunoRespondeu("117110412");
		controleAlunos.registraAlunoRespondeu("117110413");
		controleAlunos.registraAlunoRespondeu("117110414");
		String saida = "Alunos: \n1. 117110412 - Matheus Silva - CC\n2. 117110413 - Marcos Medeiros - CC\n3. 117110414 - Lucas Ramos - CC\n";
		assertEquals(saida, controleAlunos.imprimirAlunosResponderam());
	}
}
