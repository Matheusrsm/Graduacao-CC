package prova1;

import static org.junit.Assert.*;

import org.junit.Test;

public class AcademiaTest {

	Academia academia1 = new Academia("SESI", 5);

	@Test
	public void testAdicionaExercicio() {
		Exercício exercicio1 = new Exercício("esteira", 100);
		assertEquals("Exercício adicionado!", academia1.adicionaExercicio(exercicio1));
		Exercício exercicio2 = new Exercício("bicicleta", 250);
		assertEquals("Exercício adicionado!", academia1.adicionaExercicio(exercicio2));
	}
	
	@Test
	public void testLimiteExercicios() {
		Exercício exercicio1 = new Exercício("esteira", 100);
		assertEquals("Exercício adicionado!", academia1.adicionaExercicio(exercicio1));
		Exercício exercicio2 = new Exercício("bicicleta", 250);
		assertEquals("Exercício adicionado!", academia1.adicionaExercicio(exercicio2));
		Exercício exercicio3 = new Exercício("extensao quadril", 50);
		assertEquals("Exercício adicionado!", academia1.adicionaExercicio(exercicio3));
		Exercício exercicio4 = new Exercício("supino vertical", 150);
		assertEquals("Exercício adicionado!", academia1.adicionaExercicio(exercicio4));
		Exercício exercicio5 = new Exercício("puxada a frente", 300);
		assertEquals("Exercício adicionado!", academia1.adicionaExercicio(exercicio5));
		Exercício exercicio6 = new Exercício("sentadilha", 250);
		assertEquals("Impossível adicionar novo exercício. Treino completo!", academia1.adicionaExercicio(exercicio6));
	}
	
	@Test
	public void testListaExercicios() {
		Exercício exercicio1 = new Exercício("esteira", 100);
		academia1.adicionaExercicio(exercicio1);
		Exercício exercicio2 = new Exercício("bicicleta", 250);
		academia1.adicionaExercicio(exercicio2);
		assertEquals("\n1 - esteira - 100 cal/h\n2 - bicicleta - 250 cal/h", academia1.listaExercicios());
	}
	
	@Test
	public void testListaExerciciosVazia() {
			assertEquals("", academia1.listaExercicios());
	}

	@Test
	public void testCalcularCalorias() {
		Exercício exercicio1 = new Exercício("esteira", 100);
		academia1.adicionaExercicio(exercicio1);
		Exercício exercicio2 = new Exercício("bicicleta", 250);
		academia1.adicionaExercicio(exercicio2);
		Exercício exercicio3 = new Exercício("extensao", 50);
		academia1.adicionaExercicio(exercicio3);
		String[] exercicios = {"esteira", "bicicleta"};
		String[] desconhecidos = {};
		assertEquals("700 calorias gastas", academia1.calcularCalorias(exercicios, 2, desconhecidos));
	}
	
	@Test
	public void testCalcularZeroCalorias() {
		Exercício exercicio1 = new Exercício("esteira", 100);
		academia1.adicionaExercicio(exercicio1);
		Exercício exercicio2 = new Exercício("bicicleta", 250);
		academia1.adicionaExercicio(exercicio2);
		String[] exercicios = {"free runner", "supino vertical"};
		String[] desconhecidos = {};
		assertEquals("0 calorias gastas", academia1.calcularCalorias(exercicios, 2, desconhecidos));
	}
	
	@Test
	public void testExercicioDesconhecido() {
		Exercício exercicio1 = new Exercício("esteira", 100);
		academia1.adicionaExercicio(exercicio1);
		Exercício exercicio2 = new Exercício("bicicleta", 250);
		academia1.adicionaExercicio(exercicio2);
		Exercício exercicio3 = new Exercício("natação", 300);
		academia1.adicionaExercicio(exercicio3);
		String[] exercicios = {"free runner", "supino vertical", "natação"};
		String[] desconhecidos = {"natação"};
		try {
			academia1.calcularCalorias(exercicios, 2, desconhecidos);
			fail("Erro de exceção!");
		} catch(IllegalArgumentException excecao) {
			assertEquals("Há um exercício desconhecido da Academia!", excecao.getMessage());
		}
	}
}