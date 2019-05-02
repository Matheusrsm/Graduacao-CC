package liMaCor;

import static org.junit.Assert.*;

import org.junit.Test;

public class SistemaTest {

	private Sistema sistema = new Sistema();
	
	@Test
	public void novoFuncionario_DefinirDepartamento_RecuperaFuncionario_test() {
		sistema.novoFuncionario("Matheus Gaudencio", "1234");
		sistema.novoFuncionario("Livia Maria", "40028922");
		sistema.definirDepartamentoParaFuncionario("Pesquisa em Tecnologias Web 3.4", "1234");
		sistema.definirDepartamentoParaFuncionario("Software de Padaria", "40028922");
		assertEquals("Matheus Gaudencio - 1234 - Pesquisa em Tecnologias Web 3.4 - 0", sistema.recuperaFuncionario("1234"));
		assertEquals("Livia Maria - 40028922 - Software de Padaria - 0", sistema.recuperaFuncionario("40028922"));
	}

	@Test(expected = SystemHasObjectException.class)
	public void funcionarioJaCadastrado_test() {
		sistema.novoFuncionario("Matheus Gaudencio", "1234");
		sistema.novoFuncionario("Livia Maria", "1234");
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void novoFuncionario_ArgumentosInvalidos_test() {
		sistema.novoFuncionario("  ", "1234");
		sistema.novoFuncionario("Livia Maria", "    ");
	}
	
	@Test(expected = NullPointerException.class)
	public void novoFuncionario_ArgumentosNulos_test() {
		sistema.novoFuncionario(null, "1234");
		sistema.novoFuncionario("Livia Maria", "40028922");
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void definirDepartamento_ArgumentosInvalidos_test() {
		sistema.novoFuncionario("Matheus Gaudencio", "1234");
		sistema.definirDepartamentoParaFuncionario("  ", "1234");
		sistema.definirDepartamentoParaFuncionario("Software de Padaria", "    ");
	}
	
	@Test(expected = NullPointerException.class)
	public void definirDepartamento_ArgumentosNulos_test() {
		sistema.novoFuncionario("Matheus Gaudencio", "1234");
		sistema.definirDepartamentoParaFuncionario(null, "1234");
		sistema.definirDepartamentoParaFuncionario("Software de Padaria", null);
	}
	
	@Test(expected = SystemDoesNotHaveObjectException.class)
	public void definirDepartamento_FuncionarioNaoExiste_test() {
		sistema.definirDepartamentoParaFuncionario("Software de Padaria", "1234");
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void recuperaFuncionario_ArgumentoInvalido_test() {
		sistema.novoFuncionario("Matheus Gaudencio", "1234");
		sistema.recuperaFuncionario("  ");	
	}
	
	@Test
	public void listarFuncionarios_Nome_test() {
		sistema.novoFuncionario("Matheus Gaudencio", "1234");
		sistema.novoFuncionario("Livia Maria", "40028922");
		sistema.definirDepartamentoParaFuncionario("Pesquisa em Tecnologias Web 3.4", "1234");
		sistema.definirDepartamentoParaFuncionario("Software de Padaria", "40028922");
		sistema.configurarOrdem("nome");
		assertEquals("Livia Maria - 40028922 - Software de Padaria - 0\nMatheus Gaudencio - 1234 - Pesquisa em Tecnologias Web 3.4 - 0\n", sistema.listarFuncionarios());
	}
	
	@Test
	public void listarFuncionarios_Projetos_test() {
		sistema.novoFuncionario("Matheus Gaudencio", "1234");
		sistema.novoFuncionario("Livia Maria", "40028922");
		sistema.definirDepartamentoParaFuncionario("Pesquisa em Tecnologias Web 3.4", "1234");
		sistema.definirDepartamentoParaFuncionario("Software de Padaria", "40028922");
		sistema.configurarOrdem("projetos");
		assertEquals("Livia Maria - 40028922 - Software de Padaria - 0\nMatheus Gaudencio - 1234 - Pesquisa em Tecnologias Web 3.4 - 0\n", sistema.listarFuncionarios());
	}
}