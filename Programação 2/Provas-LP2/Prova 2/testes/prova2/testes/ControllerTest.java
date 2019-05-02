package prova2.testes;

import static org.junit.Assert.*;
import org.junit.Test;
import prova2.auxiliares.Controller;

public class ControllerTest {

	private Controller controllerTest = new Controller();
	
	@Test
	public void testCadastraCandidato() {
		controllerTest.cadastrarCandidato(2222, "Coordenador", "Tiririca");
		controllerTest.cadastrarCandidato(1515, "Diretor", "Tirullipa");
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void testCadastraCandidatosMESMOCODIGO() {
		controllerTest.cadastrarCandidato(2222, "Coordenador", "Tiririca");
		controllerTest.cadastrarCandidato(2222, "Diretor", "Tirullipa");
	}
	
	@Test(expected=NullPointerException.class)
	public void testCadastraCandidatoArgumentosNulos() {
		controllerTest.cadastrarCandidato(2222, null, "Tiririca");
		controllerTest.cadastrarCandidato(1515, "Diretor", null);
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void testCadastraCandidatoArgumentosInvalidos() {
		controllerTest.cadastrarCandidato(-2222, "Coordenador", "Tiririca");
		controllerTest.cadastrarCandidato(1515, " ", "Tirullipa");
		controllerTest.cadastrarCandidato(4006, "Reitor", "");
	}
	
	@Test
	public void testRecuperaCandidato() {
		controllerTest.cadastrarCandidato(2222, "Coordenador", "Tiririca");
		controllerTest.cadastrarCandidato(4006, "Reitor", "Whinderson");
		assertEquals("2222 - Coordenador - Tiririca", controllerTest.recuperaCandidato(2222));
		assertEquals("4006 - Reitor - Whinderson", controllerTest.recuperaCandidato(4006));
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void testRecuperaCandidatoCodigoInvalido() {
		controllerTest.recuperaCandidato(-2222);
		controllerTest.recuperaCandidato(-4006);
	}
	
	@Test(expected=NullPointerException.class)
	public void testRecuperaCandidatoNaoCadastrado() {
		controllerTest.recuperaCandidato(2222);
	}
	
	@Test
	public void testCadastraComentario() {
		controllerTest.cadastrarCandidato(2222, "Coordenador", "Tiririca");
		controllerTest.cadastrarCandidato(4006, "Reitor", "Whinderson");
		controllerTest.cadastrarComentario(2222, 4006, "Top dms!");
	}
	
	@Test(expected=NullPointerException.class)
	public void testCadastraComentarioCandidatoComentadoNaoCadastrado() {
		controllerTest.cadastrarCandidato(2222, "Coordenador", "Tiririca");
		controllerTest.cadastrarComentario(2222, 4006, "Top dms!");
	}
	
	@Test(expected=NullPointerException.class)
	public void testCadastraComentarioCandidatoAutorNaoCadastrado() {
		controllerTest.cadastrarCandidato(4006, "Reitor", "Whinderson");
		controllerTest.cadastrarComentario(2222, 4006, "Top dms!");
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void testCadastraComentarioArgumentosInvalidos() {
		controllerTest.cadastrarCandidato(2222, "Coordenador", "Tiririca");
		controllerTest.cadastrarCandidato(4006, "Reitor", "Whinderson");
		controllerTest.cadastrarComentario(-2222, 4006, "Top dms!");
		controllerTest.cadastrarComentario(2222, -4006, "Top dms!");
		controllerTest.cadastrarComentario(2222, 4006, "  ");
	}
	
	@Test(expected=NullPointerException.class)
	public void testCadastraComentarioArgumentosNulos() {
		controllerTest.cadastrarCandidato(2222, "Coordenador", "Tiririca");
		controllerTest.cadastrarCandidato(4006, "Reitor", "Whinderson");
		controllerTest.cadastrarComentario(2222, 4006, null);
		controllerTest.cadastrarComentario(2222, 4006, null);
	}
	
	@Test
	public void testPegaComentarios() {
		controllerTest.cadastrarCandidato(2222, "Coordenador", "Tiririca");
		controllerTest.cadastrarCandidato(4006, "Reitor", "Whinderson");
		controllerTest.cadastrarCandidato(1515, "Diretor", "Tirullipa");
		controllerTest.cadastrarComentario(2222, 4006, "Top dms!");
		controllerTest.cadastrarComentario(1515, 4006, "Melhor de todos");
		assertEquals("Top dms! --Tiririca\nMelhor de todos --Tirullipa\n", controllerTest.pegaComentarios(4006));
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void testPegaComentariosCodigoInvalido() {
		controllerTest.cadastrarCandidato(2222, "Coordenador", "Tiririca");
		controllerTest.cadastrarCandidato(4006, "Reitor", "Whinderson");
		controllerTest.cadastrarComentario(2222, 4006, "Top dms!");
		controllerTest.cadastrarComentario(2222, 4006, "Melhor de todos");
		controllerTest.pegaComentarios(-2222);
	}
	
	@Test
	public void testContaCandidatos() {
		controllerTest.cadastrarCandidato(2222, "Coordenador", "Tiririca");
		controllerTest.cadastrarCandidato(4006, "Coordenador", "Whinderson");
		controllerTest.cadastrarCandidato(1515, "Reitor", "Tirullipa");
		controllerTest.cadastrarCandidato(7875, "Coordenador", "Lucas Veloso");
		assertEquals(3, controllerTest.contaCandidatos("Coordenador"));
	}
	
	@Test
	public void testContaCandidatosZERO() {
		controllerTest.cadastrarCandidato(2222, "Coordenador", "Tiririca");
		controllerTest.cadastrarCandidato(4006, "Coordenador", "Whinderson");
		controllerTest.cadastrarCandidato(1515, "Reitor", "Tirullipa");
		controllerTest.cadastrarCandidato(7875, "Coordenador", "Lucas Veloso");
		assertEquals(0, controllerTest.contaCandidatos("Diretor"));
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void testContaCandidatosCargoInvalido() {
		controllerTest.contaCandidatos("");
		controllerTest.contaCandidatos("  ");
	}
	
	@Test(expected=NullPointerException.class)
	public void testContaCandidatosCargoNulo() {
		controllerTest.contaCandidatos(null);
	}
}	