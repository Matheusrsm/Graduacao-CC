package lab03;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class AgendaTest {
	Agenda agenda1, agenda2;

	/*
	 * Inicializa as agendas.
	 */
	@Before
	public void criaAgendas() {
		agenda1 = new Agenda();
		agenda2 = new Agenda();
	}

	/*
	 * Testa o m�todo cadastraContato, quando o cadastro � v�lido.
	 */
	@Test
	public void testCadastroValido() {
		assertEquals("CADASTRO REALIZADO!", agenda1.cadastraContato(1, "Matheus", "Silva", "0500-2017-020"));
		assertEquals("CADASTRO REALIZADO!",  agenda1.cadastraContato(100, "Matheus", "Medeiros", "0500-2017-040"));
	}
	
	/*
	 * Testa o m�todo cadastraContato, quando um contato � cadastrado em uma 
	 * posi��o que j� tem contato.
	 */
	@Test
	public void testCadastroSobrescrito() {
		assertEquals("CADASTRO REALIZADO!", agenda1.cadastraContato(1, "Matheus", "Silva", "0500-2017-020"));
		assertEquals("CADASTRO REALIZADO!",  agenda1.cadastraContato(1, "Matheus", "Medeiros", "0500-2017-040"));
	}
	
	/*
	 * Testa o m�todo cadastraContato quando a posi��o passada for inv�lida.
	 */
	@Test
	public void testCadastraPosi��oInvalida() {
		assertEquals("POSI��O INV�LIDA!", agenda1.cadastraContato(0, "Matheus", "Medeiros", "0500-2017-040"));
		assertEquals("POSI��O INV�LIDA!", agenda1.cadastraContato(101, "Matheus", "Silva", "0500-2017-020"));
	}

	/*
	 * Testa o m�todo exibeContato para um contato cadastrado.
	 */
	@Test
	public void testExibeContato() {
		agenda1.cadastraContato(43, "Matheus", "Silva", "0500-2017-020");
		assertEquals(("\nMatheus Silva - 0500-2017-020"), agenda1.exibeContato(43));
	}

	/*
	 * Testa o m�todo listaContatos � medida que s�o adicionados novos contatos.
	 */
	@Test
	public void testListaContatos() {
		agenda1.cadastraContato(25, "Matheus", "Silva", "0500-2017-020");
		assertEquals("\n25 - Matheus Silva", agenda1.listaContatos());
		agenda1.cadastraContato(77, "Matheus", "Medeiros", "0500-2017-040");
		assertEquals("\n25 - Matheus Silva\n" + "77 - Matheus Medeiros", agenda1.listaContatos());
	}

	/*
	 * Testa o m�todo equals quando as agendas s�o iguais.
	 */
	@Test
	public void testAgendasIguais() {
		agenda1.cadastraContato(15, "Matheus", "Silva", "0500-2017-020");
		agenda1.cadastraContato(98, "Matheus", "Medeiros", "0500-2017-040");
		agenda2.cadastraContato(15, "Matheus", "Silva", "0500-2017-020");
		agenda2.cadastraContato(98, "Matheus", "Medeiros", "0500-2017-040");
		assertTrue(agenda1.equals(agenda2));
	}

	/*
	 * Testa o m�todo equals, quando as agendas s�o diferentes.
	 */
	@Test
	public void testAgendasDiferentes() {
		agenda1.cadastraContato(15, "Matheus", "Silva", "0500-2017-020");
		agenda1.cadastraContato(98, "Matheus", "Medeiros", "0500-2017-040");
		agenda2.cadastraContato(15, "Medeiros", "Matheus", "0500-2017-040");
		agenda2.cadastraContato(98, "Silva", "Matheus", "0500-2017-020");
		assertFalse(agenda1.equals(agenda2));
	}
}