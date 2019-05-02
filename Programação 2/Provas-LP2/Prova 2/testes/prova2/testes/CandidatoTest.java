package prova2.testes;

import static org.junit.Assert.*;
import org.junit.Test;
import prova2.entidades.Candidato;

public class CandidatoTest {

	private Candidato candidato1, candidato2, candidato3, candidato4;
	@Test
	public void testEquals() {
		candidato1 = new Candidato(2222, "Coordenador", "Tiririca");
		candidato2 = new Candidato(7875, "Vice-Coordenador", "Lucas Veloso");
		candidato3 = new Candidato(2222, "Diretor", "Tiririca");
		candidato4 = new Candidato(7875, "Reitor", "Whinderson");
		assertFalse(candidato1.equals(candidato2));
		assertTrue(candidato1.equals(candidato3));
		assertFalse(candidato2.equals(candidato4));
	}
	
	@Test
	public void testHashCode() {
		candidato1 = new Candidato(2222, "Coordenador", "Tiririca");
		candidato2 = new Candidato(7875, "Vice-Coordenador", "Lucas Veloso");
		candidato3 = new Candidato(2222, "Diretor", "Tiririca");
		candidato4 = new Candidato(7875, "Reitor", "Whinderson");
		assertFalse(candidato1.hashCode() == candidato2.hashCode());
		assertTrue(candidato1.hashCode() == candidato3.hashCode());
		assertFalse(candidato2.hashCode() == candidato4.hashCode());
	}
}