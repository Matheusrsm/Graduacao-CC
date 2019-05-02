package lab4.testes;

import static org.junit.Assert.*;
import org.junit.Test;
import lab4.entidades.Grupo;

/**
 * Classe de Testes do Objeto Grupo.
 * 
 * @author Matheus Silva - 117110412
 */
public class GrupoTest {

	private Grupo grupo1, grupo2;
	
	// Testa se dois grupos com mesmo nome (indistintos por maiúsculas e minúsculas) 
	// são iguais.
	@Test
	public void testEquals() {
		grupo1 = new Grupo("LP2");
		grupo2 = new Grupo("lp2");
		assertTrue(grupo1.equals(grupo2));
		grupo1 = new Grupo("INFOSO");
		grupo2 = new Grupo("inFosO");
		assertTrue(grupo2.equals(grupo1));
	}
	
	// Testa se dois grupos com mesmo nome (indistintos por maiúsculas e minúsculas)
	// têm o mesmo Hashcode.
	@Test
	public void testHashcode() {
		grupo1 = new Grupo("LP2");
		grupo2 = new Grupo("lp2");
		assertTrue(grupo1.hashCode() == grupo2.hashCode());
		grupo1 = new Grupo("INFOSO");
		grupo2 = new Grupo("inFosO");
		assertTrue(grupo1.hashCode() == grupo2.hashCode());
	}
}