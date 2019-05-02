package sistemaDeApostas;

import easyaccept.EasyAccept;
import sistemaDeApostas.auxiliares.Controlador;

public class Sistema {

	private Controlador controlador;
	
	public Sistema() {
		this.controlador = new Controlador();
	}
	
	public static void main(String[] args) {
		args = new String[]
		{"sistemaDeApostas.Sistema", "acceptance_test/us1_test.txt", "acceptance_test/us2_test.txt",
		 "acceptance_test/us3_test.txt", "acceptance_test/us4_test.txt", "acceptance_test/us5_test.txt",
		 "acceptance_test/us6_test.txt", "acceptance_test/us7_test.txt"};
		EasyAccept.main(args);
	}
	
	public void inicializa(int caixa, double taxa) {
		controlador.inicializa(caixa, taxa);
	}
	
	public int getCaixa() {
		return controlador.getCaixa();
	}
	
	public int cadastrarCenario(String descricao) {
		return controlador.cadastrarCenario(descricao);
	}
	
	public int cadastrarCenario(String descricao, int bonus) {
		return controlador.cadastrarCenario(descricao, bonus);
	}
	
	public String exibirCenario(int cenario) {
		return controlador.exibirCenario(cenario);
	}
	
	public String exibirCenarios() {
		return controlador.exibirCenarios();
	}
	
	public void alterarOrdem(String ordem) {
		controlador.alterarOrdem(ordem);
	}
	
	public String exibirCenarioOrdenado(int cenario) {
		return controlador.exibirCenarioOrdenado(cenario);
	}
	
	public void cadastrarAposta(int cenario, String nomeApostador, int valor, String previsao) {
		controlador.cadastrarAposta(cenario, nomeApostador, valor, previsao);
	}
	
	public int cadastrarApostaSeguraValor(int cenario, String apostador, int valor, String previsao, int valorSeguro, int custo) {
		return controlador.cadastrarApostaSeguraValor(cenario, apostador, valor, previsao, valorSeguro, custo);
	}
	
	public int cadastrarApostaSeguraTaxa(int cenario, String apostador, int valor, String previsao, double taxa, int custo) {
		return controlador.cadastrarApostaSeguraTaxa(cenario, apostador, valor, previsao, taxa, custo);
	}
	
	public void alterarSeguroValor(int cenario, int apostaAssegurada, int valor) {
		controlador.alterarSeguroValor(cenario, apostaAssegurada, valor);
	}
	
	public void alterarSeguroTaxa(int cenario, int apostaAssegurada, double taxa) {
		controlador.alterarSeguroTaxa(cenario, apostaAssegurada, taxa);
	}
	
	public int totalDeApostas(int cenario) {
		return controlador.totalDeApostas(cenario);
	}
	
	public int valorTotalDeApostas(int cenario) {
		return controlador.valorTotalDeApostas(cenario);
	}
	
	public String exibeApostas(int cenario) {
		return controlador.exibeApostas(cenario);
	}
	
	public void fecharAposta(int cenario, boolean ocorreu) {
		controlador.fecharCenario(cenario, ocorreu);
	}
	
	public int getCaixaCenario(int cenario) {
		return controlador.getCaixaCenario(cenario);
	}
	
	public int getTotalRateioCenario(int cenario) {
		return controlador.getTotalRateioCenario(cenario);
	}
}