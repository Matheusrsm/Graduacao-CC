package lab03;

import static org.junit.Assert.*;

import org.junit.Test;

public class ContatoTest {

	Contato contato1, contato2;
	
	/*
	 * Testa o construtor do contato com nome, sobrenome e telefone válidos.
	 */
	@Test
	public void testContatoValido() {
		contato1 = new Contato("Matheus", "Silva", "0500-2017-020");
	}
	
	/*
	 * Testa o construtor do contato com nome vazio.
	 */
	@Test(expected = IllegalArgumentException.class)
	public void testNomeInvalido() {
		contato1 = new Contato("", "Silva", "0500-2017-020");
	}

	/*
	 * Testa o construtor do contato com sobrenome vazio.
	 */
	@Test(expected = IllegalArgumentException.class)
	public void testSobrenomeInvalido() {
		contato1 = new Contato("Matheus", "   ", "0500-2017-020");
	}

	/*
	 * Testa o construtor do contato com telefone vazio.
	 */
	@Test(expected = IllegalArgumentException.class)
	public void testTelefoneInvalido() {
		contato1 = new Contato("Matheus", "Silva", "  ");
	}

	/*
	 * Testa o construtor do contato com nome nulo.
	 */
	@Test(expected = NullPointerException.class)
	public void testNomeNulo() {
		contato1 = new Contato(null, "Silva", "0500-2017-020");
	}

	/*
	 * Testa o construtor do contato com sobrenome nulo.
	 */
	@Test(expected = NullPointerException.class)
	public void testSobrenomeNulo() {
		contato1 = new Contato("Matheus", null, "0500-2017-020");
	}

	/*
	 * Testa o construtor do contato com telefone nulo.
	 */
	@Test(expected = NullPointerException.class)
	public void testTelefoneNulo() {
		contato1 = new Contato("Matheus", "Silva", null);
	}

	/*
	 * Testa o método equals, quando os contatos tem nomes iguais.
	 */
	@Test
	public void testContatosIguais() {
		contato1 = new Contato("Matheus", "Silva", "0500-2017-020");
		contato2 = new Contato("Matheus", "Silva", "0500-2017-020");
		assertTrue(contato1.equals(contato2));
	}

	/*
	 * Testa o método equals, quando os contatos tem nomes diferentes.
	 */
	@Test
	public void testContatosDiferentes() {
		contato1 = new Contato("Matheus", "Silva", "0500-2017-020");
		contato2 = new Contato("Marcos", "Silva", "0500-2017-040");
		assertFalse(contato1.equals(contato2));
	}
}