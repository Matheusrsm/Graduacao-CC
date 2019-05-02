package sistemaDeApostas.testes;

import static org.junit.Assert.*;
import org.junit.Test;
import sistemaDeApostas.auxiliares.Controlador;

public class ControladorTest {

	Controlador controladorTest = new Controlador();
	
	/**
	 * Testa a inicialização com um caixa negativo
	 */
	@Test(expected=IllegalArgumentException.class)
	public void testInicializaCaixaNegativo(){
		controladorTest.inicializa(-100, 0.02);
	}
	
	/**
	 * Testa a inicialização com uma taxa com valor igual a zero
	 */
	@Test(expected=IllegalArgumentException.class)
	public void testInicializaTaxaZERO(){
		controladorTest.inicializa(2500, 0);
	}
	
	/**
	 * Testa a inicialização com uma taxa negativa
	 */
	@Test(expected=IllegalArgumentException.class)
	public void testInicializaTaxaNegativa(){
		controladorTest.inicializa(100, -0.5);
	}

	/**
	 * Testa a possibilidade de inicializar casa com parametros válidos
	 */
	@Test
	public void testInicializa(){
		controladorTest.inicializa(100, 0.5);
	}

	/**
	 * Testa o getCaixa com um Caixa Nulo
	 */
	@Test(expected=NullPointerException.class)
	public void testGetCaixaNulo(){
		controladorTest.getCaixa();
	}
	
	/**
	 * Testa o getCaixa com um Caixa Válido
	 */
	@Test
	public void testGetCaixa(){
		controladorTest.inicializa(680, 0.5);
		assertEquals(680, controladorTest.getCaixa());
	}

	/**
	 * Testa o cadastro de um cenário com descrição nula
	 */
	@Test(expected=NullPointerException.class)
	public void testCadastrarCenarioDescricaoNula(){
		controladorTest.cadastrarCenario(null);
	}
	
	/**
	 * Testa o cadastro de um cenário com descrição vazia
	 */
	@Test(expected=IllegalArgumentException.class)
	public void testCadastrarCenarioDescricaoVazia(){
		controladorTest.cadastrarCenario("  ");
		controladorTest.cadastrarCenario("");
	}
	
	/**
	 * Testa o cadastro de um cenário com descrição válida
	 */
	@Test
	public void testCadastrarCenario(){
		assertEquals(1, controladorTest.cadastrarCenario("Matheus vai pagar Discreta"));
	}
	
	/**
	 * Testa o cadastro de um cenário com bônus
	 */
	@Test
	public void testCadastrarCenarioBonus() {
		controladorTest.inicializa(100, 0.5);
		assertEquals(1, controladorTest.cadastrarCenario("Matheus vai pagar Discreta", 100));
	}
	
	/**
	 * Testa o cadastro de um cenário com bônus inválido
	 */
	@Test(expected=IllegalArgumentException.class)
	public void testCadastrarCenarioBonusInvalido() {
		controladorTest.inicializa(100, 0.5);
		controladorTest.cadastrarCenario("Matheus vai pagar Discreta", 0);
		controladorTest.cadastrarCenario("Matheus vai pagar Discreta", -13);
	}
	
	/**
	 * Testa o cadastro de um cenário bonificado com descrição inválida
	 */
	@Test(expected=IllegalArgumentException.class)
	public void testCadastrarCenarioBonusDescricaoInvalida() {
		controladorTest.inicializa(100, 0.5);
		controladorTest.cadastrarCenario("", 0);
		controladorTest.cadastrarCenario("Matheus vai pagar Discreta", -13);
	}
	
	/**
	 * Testa o cadastro de vários cenários bonificados ou não, com descrições válidas
	 */
	@Test
	public void testCadastrarVariosCenarios(){
		controladorTest.inicializa(100, 0.5);
		assertEquals(1, controladorTest.cadastrarCenario("Matheus vai pagar Discreta"));
		assertEquals(2, controladorTest.cadastrarCenario("Matheus vai pagar Calc 2", 100));
		assertEquals(3, controladorTest.cadastrarCenario("Matheus vai pagar LP2"));
	}
	
	/**
	 * Testa a exibição de um possível cenário com ID inválido
	 */
	@Test(expected=IllegalArgumentException.class)
	public void testExibirCenarioIDInvalido(){
		controladorTest.exibirCenario(0);
		controladorTest.exibirCenario(-10);
	}
	
	/**
	 * Testa a exibição de um cenário não cadastrado
	 */
	@Test(expected=ArrayIndexOutOfBoundsException.class)
	public void testExibirCenarioNaoCadastrado(){
		controladorTest.exibirCenario(4);
	}
	
	/**
	 * Testa a exibição de um cenário cadastrado
	 */
	@Test
	public void testExibirCenario(){
		controladorTest.cadastrarCenario("Matheus vai pagar Discreta");
		assertEquals("1 - Matheus vai pagar Discreta - Nao finalizado", controladorTest.exibirCenario(1));
	}

	/**
	 * Testa a exibição de todos os cenários bonificados ou não
	 */
	@Test
	public void testExibirCenarios(){
		controladorTest.inicializa(100, 0.5);
		controladorTest.cadastrarCenario("Matheus vai pagar Discreta");
		controladorTest.cadastrarCenario("Matheus vai pagar Calc 2", 1000);
		String saida = "\n1 - Matheus vai pagar Discreta - Nao finalizado\n2 - Matheus vai pagar Calc 2 - Nao finalizado - R$ 10,00";
		assertEquals(saida, controladorTest.exibirCenarios());
	}
	
	/**
	 * Testa o cadastro de apostas com argumentos nulos
	 */
	@Test(expected=NullPointerException.class)
	public void testCadastrarApostaArgumentosNulos(){
		controladorTest.cadastrarCenario("Matheus vai pagar Discreta");
		controladorTest.cadastrarAposta(1, null, 2850, "VAI ACONTECER");
		controladorTest.cadastrarAposta(1, "Marcos", 1200, null);
	}
	
	/**
	 * Testa o cadastro de apostas com argumentos inválidos
	 */
	@Test(expected=IllegalArgumentException.class)
	public void testCadastrarApostaArgumentosInvalidos(){
		controladorTest.cadastrarCenario("Matheus vai pagar Discreta");
		controladorTest.cadastrarAposta(1, "  ", 34000, "VAI ACONTECER");
		controladorTest.cadastrarAposta(1, "Marcos", 0, "VAI ACONTECER");
		controladorTest.cadastrarAposta(0, "Marcos", 150, "N VAI ACONTECER");
		controladorTest.cadastrarAposta(1, "Marcos", 1200, "ACONTECERÁ");
	}
	
	/**
	 * Testa o cadastro de uma aposta em um cenário não cadastrado
	 */
	@Test(expected=ArrayIndexOutOfBoundsException.class)
	public void testCadastrarApostaCenarioNaoCadastrado(){
		controladorTest.cadastrarAposta(1, "Marcos", 10000, "N VAI ACONTECER");
	}
	
	/**
	 * Testa o cadastro de uma aposta em um cenário válido
	 */
	@Test
	public void testCadastrarAposta(){
		controladorTest.cadastrarCenario("Matheus vai pagar Discreta");
		controladorTest.cadastrarAposta(1, "Marcos", 76000, "VAI ACONTECER");
	}

	/**
	 * Testa o cadastro de duas apostas de um mesmo apostador em um cenário válido 
	 */
	@Test
	public void testCadastrar2ApostasMesmoApostador(){
		controladorTest.cadastrarCenario("Matheus vai pagar Discreta");
		controladorTest.cadastrarAposta(1, "Marcos", 76000, "VAI ACONTECER");
		controladorTest.cadastrarAposta(1, "Marcos", 1000000, "VAI ACONTECER");
	}
	
	/**
	 * Testa o cadastro de uma aposta assegurada por valor 
	 */
	@Test
	public void testCadastrarApostaSeguraValor() {
		controladorTest.inicializa(100, 0.5);
		controladorTest.cadastrarCenario("Matheus vai pagar Discreta");
		controladorTest.cadastrarApostaSeguraValor(1, "Marcos", 2000, "VAI ACONTECER", 100, 50);
	}
	
	/**
	 * Testa o cadastro de uma aposta assegurada por taxa 
	 */
	@Test
	public void testCadastrarApostaSeguraTaxa() {
		controladorTest.inicializa(100, 0.5);
		controladorTest.cadastrarCenario("Matheus vai pagar Discreta");
		controladorTest.cadastrarApostaSeguraTaxa(1, "Marcos", 2000, "VAI ACONTECER", 0.01, 50);
	}
	
	/**
	 * Testa alterar o seguro de uma aposta assegurada por taxa para valor
	 */
	@Test
	public void testAlterarSeguroValor() {
		controladorTest.inicializa(100, 0.5);
		controladorTest.cadastrarCenario("Matheus vai pagar Discreta");
		controladorTest.cadastrarAposta(1, "Marcos", 76000, "VAI ACONTECER");
		controladorTest.cadastrarAposta(1, "Joao", 130, "N VAI ACONTECER");
		controladorTest.cadastrarApostaSeguraTaxa(1, "Lucas", 2000, "VAI ACONTECER", 0.01, 50);
		controladorTest.alterarSeguroValor(1, 2, 150);
	}
	
	/**
	 * Testa alterar o seguro de uma aposta assegurada por valor para taxa
	 */
	@Test
	public void testAlterarSeguroTaxa() {
		controladorTest.inicializa(100, 0.5);
		controladorTest.cadastrarCenario("Matheus vai pagar Discreta");
		controladorTest.cadastrarAposta(1, "Marcos", 76000, "VAI ACONTECER");
		controladorTest.cadastrarAposta(1, "Joao", 130, "N VAI ACONTECER");
		controladorTest.cadastrarApostaSeguraValor(1, "Lucas", 2000, "VAI ACONTECER", 6570, 50);
		controladorTest.alterarSeguroTaxa(1, 2, 0.34);
	}
	
	/**
	 * Testa pegar o valor total de apostas de um cenário inválido
	 */
	@Test(expected=IllegalArgumentException.class)
	public void testValorTotalDeApostasCenarioInvalido(){
		controladorTest.valorTotalDeApostas(0);
	}
	
	/**
	 * Testa pegar o valor total de apostas de um cenário não cadastrado
	 */
	@Test(expected=ArrayIndexOutOfBoundsException.class)
	public void testValorTotalDeApostasCenarioNaoCadastrado(){
		controladorTest.valorTotalDeApostas(35);
	}
	
	/**
	 * Testa pegar o valor total de apostas de um cenário válido
	 */
	@Test
	public void testValorTotalDeApostas(){
		controladorTest.inicializa(100, 0.5);
		controladorTest.cadastrarCenario("Matheus vai pagar Discreta");
		controladorTest.cadastrarAposta(1, "Marcos", 2400, "VAI ACONTECER");
		controladorTest.cadastrarApostaSeguraValor(1, "Arthur", 2000, "VAI ACONTECER", 6570, 50);
		controladorTest.cadastrarAposta(1, "Lucas", 100, "N VAI ACONTECER");
		assertEquals(150, controladorTest.getCaixa());
		assertEquals(4500, controladorTest.valorTotalDeApostas(1));
	}

	/**
	 * Testa pegar o total de apostas de um cenário inválido
	 */
	@Test(expected=IllegalArgumentException.class)
	public void testTotalDeApostasCenarioInvalido(){
		controladorTest.totalDeApostas(0);
		controladorTest.totalDeApostas(-25);
	}
	
	/**
	 * Testa pegar o total de apostas de um cenário não cadastrado
	 */
	@Test(expected=ArrayIndexOutOfBoundsException.class)
	public void testTotalDeApostasCenarioNaoCadastrado(){
		controladorTest.totalDeApostas(22);
		controladorTest.totalDeApostas(1);
	}
	
	/**
	 * Testa pegar o total de apostas de um cenário válido
	 */
	@Test
	public void testTotalDeApostas(){
		controladorTest.inicializa(100, 0.5);
		controladorTest.cadastrarCenario("Matheus vai pagar Discreta");
		controladorTest.cadastrarAposta(1, "Marcos", 2500, "VAI ACONTECER");
		controladorTest.cadastrarAposta(1, "Lucas", 100, "N VAI ACONTECER");
		controladorTest.cadastrarApostaSeguraValor(1, "Lucas", 2000, "VAI ACONTECER", 6570, 50);
		assertEquals(3, controladorTest.totalDeApostas(1));
	}

	/**
	 * Testa pegar a representação de todas as apostas de um cenário inválido
	 */
	@Test(expected=IllegalArgumentException.class)
	public void testExibirApostasCenarioInvalido(){
		controladorTest.exibeApostas(-20);
		controladorTest.exibeApostas(0);
	}
	
	/**
	 * Testa pegar a representação de todas as apostas de um cenário não cadastrado
	 */
	@Test(expected=ArrayIndexOutOfBoundsException.class)
	public void testExibirApostasCenarioNaoCadastrado(){
		controladorTest.exibeApostas(35);
	}
	
	/**
	 * Testa a exibição da representação de todas as apostas de um cenário válido
	 */
	@Test
	public void testExibeApostas(){
		controladorTest.inicializa(100, 0.5);
		controladorTest.cadastrarCenario("Matheus vai pagar Discreta");
		controladorTest.cadastrarAposta(1, "Marcos Silva", 2500, "VAI ACONTECER");
		controladorTest.cadastrarAposta(1, "Lucas Silva", 100, "N VAI ACONTECER");
		controladorTest.cadastrarApostaSeguraValor(1, "Joao Silva", 3400, "VAI ACONTECER", 65, 50);
		String saida = "\nMarcos Silva - R$25,00 - VAI ACONTECER\nLucas Silva - R$1,00 - N VAI ACONTECER\nJoao Silva - R$34,00 - VAI ACONTECER - ASSEGURADA (VALOR) - R$65,00";
		assertEquals(saida, controladorTest.exibeApostas(1));
	}

	/**
	 * Testa fechar um cenário inválido
	 */
	@Test(expected=IllegalArgumentException.class)
	public void testFecharCenarioInvalido(){
		controladorTest.fecharCenario(0, true);
		controladorTest.fecharCenario(-98, false);
	}
	
	/**
	 * Testa fechar um cenário não cadastrado
	 */
	@Test(expected=ArrayIndexOutOfBoundsException.class)
	public void testFecharCenarioNaoCadastrado(){
		controladorTest.fecharCenario(67, true);
	}
	
	/**
	 * Testa fechar um cenário válido
	 */
	@Test
	public void testFecharCenario(){
		controladorTest.cadastrarCenario("Matheus vai pagar Discreta");
		controladorTest.fecharCenario(1, true);
	}
	
	/**
	 * Testa fechar um cenário válido
	 */
	@Test
	public void testFecharCenarioBonus(){
		controladorTest.inicializa(24500, 0.03);
		controladorTest.cadastrarCenario("Matheus vai pagar Discreta", 1000);
		controladorTest.fecharCenario(1, true);
	}
	
	/**
	 * Testa pegar o caixa de um cenário inválido
	 */
	@Test(expected=IllegalArgumentException.class)
	public void testGetCaixaCenarioInvalido(){
		controladorTest.inicializa(24500, 0.03);
		controladorTest.getCaixaCenario(-2);
	}
	
	/**
	 * Testa pegar o caixa de um cenário não cadastrado
	 */
	@Test(expected=ArrayIndexOutOfBoundsException.class)
	public void testGetCaixaCenarioNaoCadastrado(){
		controladorTest.inicializa(24500, 0.03);
		controladorTest.getCaixaCenario(31);
	}
	
	/**
	 * Testa pegar o caixa de um cenário não finalizado
	 */
	@Test(expected=IllegalArgumentException.class)
	public void testGetCaixaCenarioNaoFinalizado(){
		controladorTest.inicializa(24500, 0.03);
		controladorTest.cadastrarCenario("Matheus vai pagar Discreta");
		controladorTest.getCaixaCenario(1);
	}
	
	/**
	 * Testa pegar o caixa de um cenário válido e finalizado
	 */
	@Test
	public void testGetCaixaCenario(){
		controladorTest.inicializa(24500, 0.03);
		controladorTest.cadastrarCenario("Matheus vai pagar Discreta");
		controladorTest.cadastrarAposta(1, "Marcos", 25080, "VAI ACONTECER");
		controladorTest.cadastrarAposta(1, "Lucas", 100, "N VAI ACONTECER");
		controladorTest.fecharCenario(1, true);
		assertEquals(3, controladorTest.getCaixaCenario(1));
	}

	/**
	 * Testa pegar o caixa de um cenário bonificado válido e finalizado
	 */
	@Test
	public void testGetCaixaCenarioBonificado(){
		controladorTest.inicializa(24500, 0.03);
		controladorTest.cadastrarCenario("Matheus vai pagar Discreta", 1000);
		controladorTest.cadastrarAposta(1, "Marcos", 25080, "VAI ACONTECER");
		controladorTest.cadastrarAposta(1, "Lucas", 100, "N VAI ACONTECER");
		controladorTest.fecharCenario(1, true);
		assertEquals(3, controladorTest.getCaixaCenario(1));
	}
	
	/**
	 * Testa pegar o valor de rateio de um cenário inválido
	 */
	@Test(expected=IllegalArgumentException.class)
	public void testGetTotalRateioCenarioInvalido(){
		controladorTest.inicializa(24500, 0.03);
		controladorTest.getTotalRateioCenario(-2);
		controladorTest.getTotalRateioCenario(0);
	}
	
	/**
	 * Testa pegar o valor de rateio de um cenário não cadastrado
	 */
	@Test(expected=ArrayIndexOutOfBoundsException.class)
	public void testGetTotalRateioCenarioNaoCadastrado(){
		controladorTest.inicializa(24500, 0.03);
		controladorTest.getTotalRateioCenario(84);
		controladorTest.getTotalRateioCenario(1);
	}
	
	/**
	 * Testa pegar o valor de rateio de um cenário não finalizado
	 */
	@Test(expected=IllegalArgumentException.class)
	public void testGetTotalRateioCenarioNaoFinalizado(){
		controladorTest.inicializa(24500, 0.03);
		controladorTest.cadastrarCenario("Matheus vai pagar Discreta");
		controladorTest.getTotalRateioCenario(1);
	}
	
	/**
	 * Testa pegar o valor de rateio de um cenário válido e finalizado
	 */
	@Test
	public void testGetTotalRateioCenario(){
		controladorTest.inicializa(24500, 0.03);
		controladorTest.cadastrarCenario("Matheus vai pagar Discreta");
		controladorTest.cadastrarAposta(1, "Marcos", 25080, "VAI ACONTECER");
		controladorTest.cadastrarAposta(1, "Lucas", 100, "N VAI ACONTECER");
		controladorTest.fecharCenario(1, true);
		assertEquals(97, controladorTest.getTotalRateioCenario(1));
	}
	
	/**
	 * Testa pegar o valor de rateio de um cenário bonificado válido e finalizado
	 */
	@Test
	public void testGetTotalRateioCenarioBonus() {
		controladorTest.inicializa(24500, 0.03);
		controladorTest.cadastrarCenario("Matheus vai pagar Discreta", 2000);
		controladorTest.cadastrarAposta(1, "Marcos", 25080, "VAI ACONTECER");
		controladorTest.cadastrarAposta(1, "Lucas", 100, "N VAI ACONTECER");
		controladorTest.fecharCenario(1, true);
		assertEquals(2097, controladorTest.getTotalRateioCenario(1));
	}
	
	/**
	 * Testa pegar o valor de rateio de um cenário bonificado válido e finalizado com apostas asseguradas
	 */
	@Test
	public void testGetTotalRateioCenarioBonusComApostasAsseguradas() {
		controladorTest.inicializa(24500, 0.03);
		controladorTest.cadastrarCenario("Matheus vai pagar Discreta", 2000);
		controladorTest.cadastrarAposta(1, "Marcos", 25080, "VAI ACONTECER");
		controladorTest.cadastrarApostaSeguraValor(1, "Lucas", 100, "N VAI ACONTECER", 250, 50);
		controladorTest.cadastrarApostaSeguraTaxa(1, "Joao", 200, "N VAI ACONTECER", 0.5, 50);
		assertEquals(22600, controladorTest.getCaixa());
		controladorTest.fecharCenario(1, true);
		assertEquals(22259, controladorTest.getCaixa());
		assertEquals(2291, controladorTest.getTotalRateioCenario(1));
	}
	
	/**
	 * Testa alterar a ordem de exibição dos Cenários e a exibição de um dado Cenário Ordenado
	 */
	
	@Test
	public void testAlteraOrdemExibeOrdenadoCenarios() {
		controladorTest.inicializa(24500, 0.03);
		controladorTest.cadastrarCenario("Matheus vai pagar Discreta", 2000);
		controladorTest.cadastrarCenario("Lucas vai pagar Calc 2");
		controladorTest.cadastrarCenario("UFCG vai entrar em Greve");
		controladorTest.alterarOrdem("NOME");
		assertEquals("2 - Lucas vai pagar Calc 2 - Nao finalizado", controladorTest.exibirCenarioOrdenado(1));
		assertEquals("1 - Matheus vai pagar Discreta - Nao finalizado - R$ 20,00", controladorTest.exibirCenarioOrdenado(2));
		assertEquals("3 - UFCG vai entrar em Greve - Nao finalizado", controladorTest.exibirCenarioOrdenado(3));
		controladorTest.cadastrarAposta(1, "Matheus", 3400, "VAI ACONTECER");
		controladorTest.cadastrarAposta(1, "Marcos", 3500, "N VAI ACONTECER");
		controladorTest.cadastrarAposta(3, "Matheus", 2800, "VAI ACONTECER");
		controladorTest.alterarOrdem("ApOsTAs");
		assertEquals("1 - Matheus vai pagar Discreta - Nao finalizado - R$ 20,00", controladorTest.exibirCenarioOrdenado(1));
		assertEquals("3 - UFCG vai entrar em Greve - Nao finalizado", controladorTest.exibirCenarioOrdenado(2));
		assertEquals("2 - Lucas vai pagar Calc 2 - Nao finalizado", controladorTest.exibirCenarioOrdenado(3));
		controladorTest.alterarOrdem("cadastro");
		assertEquals("1 - Matheus vai pagar Discreta - Nao finalizado - R$ 20,00", controladorTest.exibirCenarioOrdenado(1));
		assertEquals("2 - Lucas vai pagar Calc 2 - Nao finalizado", controladorTest.exibirCenarioOrdenado(2));
		assertEquals("3 - UFCG vai entrar em Greve - Nao finalizado", controladorTest.exibirCenarioOrdenado(3));
	}
}