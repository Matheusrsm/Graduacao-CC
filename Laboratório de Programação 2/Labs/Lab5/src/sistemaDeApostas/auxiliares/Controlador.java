package sistemaDeApostas.auxiliares;

import java.util.ArrayList;
import java.util.Collections;

import sistemaDeApostas.aposta.*;
import sistemaDeApostas.caixa.*;
import sistemaDeApostas.cenario.*;
import sistemaDeApostas.ordenacoes.*;

/**
 * Classe de Controle dos cenários, todos os métodos que estão na Facade mantém
 * relação com os métodos públicos desse Controller.
 * 
 * @author Matheus Silva - 117110412
 */
public class Controlador{

	private ArrayList<Cenario> cenarios;
	private Caixa caixa;
	private Excecoes excecoes;
	private ArrayList<Cenario> cenariosOrdenados;
	
	public Controlador() {
		this.cenarios = new ArrayList<Cenario>();
		this.excecoes = new Excecoes();
		this.cenariosOrdenados = new ArrayList<Cenario>();
	}
	
	/**
	 * Método que inicializa o Caixa com um valor de caixa inicial e uma taxa fixa.
	 * 
	 * @param caixa 
	 * 		Valor armazenado na casa em centavos, no formato inteiro.
	 * @param taxa 
	 * 		Taxa de desconto no rateio de apostas dos cenários, no formato double.
	 */
	public void inicializa(int caixa, double taxa) {
		excecoes.inicializacaoCaixaInvalido(caixa, "Erro na inicializacao: Caixa nao pode ser inferior a 0");
		excecoes.inicializacaoTaxaInvalida(taxa, "Erro na inicializacao: Taxa nao pode ser inferior a 0");
		this.caixa = new Caixa(caixa, taxa);
	}
	
	/**
	 * 
	 * @return o valor do Caixa.
	 */
	public int getCaixa() {
		return caixa.getValorCaixa();
	}
	
	/**
	 * Método que cria e cadastra um Cenário no sistema, com identificador igual ao indentificador do ultimo cadastrado mais 1.
	 * 
	 * @param descricao 
	 * 		Representação textual do Cenário que o descreve, no formato String.
	 * @return o identificador do Cenário, ou seja, a sua posição na Lista de Cenários.
	 */
	public int cadastrarCenario(String descricao) {
		excecoes.argumentoInvalido(descricao, "Erro no cadastro de cenario: Descricao nao pode ser vazia");
		int id = cenarios.size() + 1;
		Cenario cenario = new Cenario(id, descricao);
		cenarios.add(cenario);
		return id;
	}
	
	/**
	 * Método que cria e cadastra um Cenário Bonificado no sistema, com identificador igual ao indentificador do ultimo cadastrado mais 1.
	 * 
	 * @param descricao 
	 * 		Representação textual do Cenário Bonificado que o descreve, no formato String.
	 * @return o identificador do Cenário Bonificado, ou seja, a sua posição na Lista de Cenários.
	 */
	public int cadastrarCenario(String descricao, int bonus) {
		excecoes.argumentoInvalido(descricao, "Erro no cadastro de cenario: Descricao nao pode ser vazia");
		excecoes.cenarioBonusInvalido(bonus, "Erro no cadastro de cenario: Bonus invalido");
		int id = cenarios.size() + 1;
		CenarioBonus cenario = new CenarioBonus(id, descricao, bonus);
		caixa.retiraValorCaixa(bonus);
		cenarios.add(cenario);
		return id;
	}
	
	/**
	 * Método que busca um Cenário pelo seu identificador.
	 * @param cenario
	 * 		Representação numérica de um cenário, no formato Inteiro.
	 * @return o Cenário Procurado.
	 */
	private Cenario cenarioProcurado(int cenario) {
		return cenarios.get(cenario - 1);
	}
	
	/**
	 * Método que busca um Cenário Ordenado pelo seu identificador.
	 * @param cenario
	 * 		Representação numérica de um cenário ordenado, no formato Inteiro.
	 * @return o Cenário Procurado.
	 */
	private Cenario cenarioProcuradoOrdenado(int cenario) {
		return cenariosOrdenados.get(cenario - 1);
	}
	
	/**
	 * Método que retorna a representação textual de um cenário especificado pelo seu identificador.
	 * 
	 * @param cenario 
	 * 		Número identificador do cenário, no formato inteiro.
	 * @return a representação textual do cenário procurado.
	 */
	public String exibirCenario(int cenario) {
		excecoes.cenarioNaoCadastrado(cenario, cenarios, "Erro na consulta de cenario: Cenario nao cadastrado");
		excecoes.cenarioInvalido(cenario, "Erro na consulta de cenario: Cenario invalido");
		return cenarioProcurado(cenario).toString();
	}
	
	/**
	 * @return a representação textual de todos os Cenários cadastrados no Sistema.
	 */
	public String exibirCenarios() {
		String saida = "";
		for(int i = 0; i < cenarios.size(); i++) {
			saida += "\n" + exibirCenario(i + 1);		
		}
		return saida;
	}
	
	/**
	 * Cadastra uma Aposta em um Cenário, ou seja, adiciona uma nova Aposta na Lista de Apostas do Cenário passado.
	 * @param cenario
	 * 		O identificador do Cenário que será cadastrado uma Aposta.
	 * @param nomeApostador 
	 * 		Nome do apostador, no formato String.
	 * @param valor
	 * 		Valor da aposta em centavos, no formato Inteiro.
	 * @param previsao 
	 * 		Previsao dada pelo apostador, no formato String.
	 */
	public void cadastrarAposta(int cenario, String nomeApostador, int valor, String previsao) {
		excecoes.cenarioInvalido(cenario, "Erro no cadastro de aposta: Cenario invalido");
		excecoes.cenarioNaoCadastrado(cenario, cenarios, "Erro no cadastro de aposta: Cenario nao cadastrado");
		excecoes.argumentoInvalidoOuNulo(nomeApostador, "Erro no cadastro de aposta: Apostador nao pode ser vazio ou nulo");
		excecoes.valorInvalido(valor, "Erro no cadastro de aposta: Valor nao pode ser menor ou igual a zero");
		excecoes.argumentoInvalidoOuNulo(previsao, "Erro no cadastro de aposta: Previsao nao pode ser vazia ou nula");
		excecoes.previsaoInvalida(previsao, "Erro no cadastro de aposta: Previsao invalida");
		cenarioProcurado(cenario).cadastroAposta(nomeApostador, valor, previsao);	
	}
	
	/**
	 * Cadastra uma Aposta Assegurada por Valor em um Cenário, ou seja, adiciona uma nova Aposta na Lista de 
	 * Apostas do Cenário passado.
	 * @param cenario
	 * 		O identificador do Cenário que será cadastrado uma Aposta Assegurada por Valor.
	 * @param nomeApostador 
	 * 		Nome do apostador, no formato String.
	 * @param valor
	 * 		Valor da aposta em centavos, no formato Inteiro.
	 * @param previsao 
	 * 		Previsao dada pelo apostador, no formato String.
	 * @param valorSeguro
	 * 		Valor do Seguro da Aposta, no formato Inteiro.
	 * @param custo
	 * 		O custo de se ter um Seguro em uma Aposta, no formato Inteiro.
	 * @return o identificador da Aposta Assegurada por Valor.
	 */
	public int cadastrarApostaSeguraValor(int cenario, String nomeApostador, int valor, String previsao, int valorSeguro, int custo) {
		excecoes.cenarioInvalido(cenario, "Erro no cadastro de aposta assegurada por valor: Cenario invalido");
		excecoes.cenarioNaoCadastrado(cenario, cenarios, "Erro no cadastro de aposta assegurada por valor: Cenario nao cadastrado");
		excecoes.argumentoInvalidoOuNulo(nomeApostador, "Erro no cadastro de aposta assegurada por valor: Apostador nao pode ser vazio ou nulo");
		excecoes.valorInvalido(valor, "Erro no cadastro de aposta assegurada por valor: Valor nao pode ser menor ou igual a zero");
		excecoes.argumentoInvalidoOuNulo(previsao, "Erro no cadastro de aposta assegurada por valor: Previsao nao pode ser vazia ou nula");
		excecoes.previsaoInvalida(previsao, "Erro no cadastro de aposta assegurada por valor: Previsao invalida");
		int idAposta = cenarioProcurado(cenario).cadastroApostaSeguroValor(nomeApostador, valor, previsao, valorSeguro, custo);
		caixa.adicionaValorCaixa(custo);
		return idAposta;
	}
	
	/**
	 * Cadastra uma Aposta Assegurada por Taxa em um Cenário, ou seja, adiciona uma nova Aposta na Lista de 
	 * Apostas do Cenário passado.
	 * @param cenario
	 * 		O identificador do Cenário que será cadastrado uma Aposta Assegurada por Taxa.
	 * @param nomeApostador 
	 * 		Nome do apostador, no formato String.
	 * @param valor
	 * 		Valor da aposta em centavos, no formato Inteiro.
	 * @param previsao 
	 * 		Previsao dada pelo apostador, no formato String.
	 * @param taxaSeguro
	 * 		Taxa do Seguro da Aposta, no formato Double.
	 * @param custo
	 * 		O custo de se ter um Seguro em uma Aposta, no formato Inteiro.
	 * @return o identificador da Aposta Assegurada por Taxa.
	 */
	public int cadastrarApostaSeguraTaxa(int cenario, String nomeApostador, int valor, String previsao, double taxaSeguro, int custo) {
		excecoes.cenarioInvalido(cenario, "Erro no cadastro de aposta assegurada por taxa: Cenario invalido");
		excecoes.cenarioNaoCadastrado(cenario, cenarios, "Erro no cadastro de aposta assegurada por taxa: Cenario nao cadastrado");
		excecoes.argumentoInvalidoOuNulo(nomeApostador, "Erro no cadastro de aposta assegurada por taxa: Apostador nao pode ser vazio ou nulo");
		excecoes.valorInvalido(valor, "Erro no cadastro de aposta assegurada por taxa: Valor nao pode ser menor ou igual a zero");
		excecoes.argumentoInvalidoOuNulo(previsao, "Erro no cadastro de aposta assegurada por taxa: Previsao nao pode ser vazia ou nula");
		excecoes.previsaoInvalida(previsao, "Erro no cadastro de aposta assegurada por taxa: Previsao invalida");
		int idAposta = cenarioProcurado(cenario).cadastroApostaSeguroTaxa(nomeApostador, previsao, valor, taxaSeguro, custo);
		caixa.adicionaValorCaixa(custo);
		return idAposta;
	}
	
	/**
	 * Método que altera a Taxa do Seguro de uma Aposta para um Valor, ou seja, a Aposta antes Assegurada por Taxa, 
	 * agora passa a ser Assegurada por Valor.
	 * @param cenario 
	 * 		Representação numerica de um cenário, no formato Inteiro.
	 * @param apostaAssegurada 
	 * 		Identificador numérico da aposta.
	 * @param valor 
	 * 		O Valor do Seguro que será alterado no Seguro da Aposta.
	 */
	public void alterarSeguroValor(int cenario, int apostaAssegurada, int valor) {
		int custo = cenarioProcurado(cenario).getApostasDoCenario().get(apostaAssegurada).getSeguro().getCusto();
		int id = cenarioProcurado(cenario).getApostasDoCenario().get(apostaAssegurada).getSeguro().getIdentificador();
		cenarioProcurado(cenario).getApostasDoCenario().get(apostaAssegurada).setSeguro(new SeguroValor(valor, custo, id));
		
	}
	
	/**
	 * Método que altera o Valor do Seguro de uma Aposta para uma Taxa, ou seja, a Aposta antes Assegurada por Valor, 
	 * agora passa a ser Assegurada por Taxa.
	 * @param cenario 
	 * 		Representação numerica de um cenário, no formato Inteiro.
	 * @param apostaAssegurada 
	 * 		Identificador numérico da aposta.
	 * @param valor 
	 * 		A Taxa do Seguro que será alterada no Seguro da Aposta.
	 */
	public void alterarSeguroTaxa(int cenario, int apostaAssegurada, double taxa) {
		int custo = cenarioProcurado(cenario).getApostasDoCenario().get(apostaAssegurada).getSeguro().getCusto();
		int id = cenarioProcurado(cenario).getApostasDoCenario().get(apostaAssegurada).getSeguro().getIdentificador();
		cenarioProcurado(cenario).getApostasDoCenario().get(apostaAssegurada).setSeguro(new SeguroTaxa(taxa, custo, id));
	}

	/**
	 * Método que calcula a quantidade de apostas no cenário especificado. 
	 * @param cenario 
	 * 		Representação numerica de um cenário, no formato Inteiro.
	 * @return um inteiro que representa a quantidade de apostas do Cenário especificado.
	 */
	public int totalDeApostas(int cenario) {
		excecoes.cenarioInvalido(cenario, "Erro na consulta do total de apostas: Cenario invalido");
		excecoes.cenarioNaoCadastrado(cenario, cenarios, "Erro na consulta do total de apostas: Cenario nao cadastrado");
		return cenarioProcurado(cenario).getApostasDoCenario().size();
	}
	
	/**
	 * Método que calcula o somatório de todos os valores apostados no cenário especificado.
	 * @param cenario
	 * 		Representação numerica de um cenário, no formato Inteiro.
	 * @return o valor do somatório de todos os valores apostados no Cenário, no formato Inteiro.
	 */
	public int valorTotalDeApostas(int cenario) {
		excecoes.cenarioInvalido(cenario, "Erro na consulta do valor total de apostas: Cenario invalido");
		excecoes.cenarioNaoCadastrado(cenario, cenarios, "Erro na consulta do valor total de apostas: Cenario nao cadastrado");
		int valorTotal = 0;
		for(Aposta aposta: cenarioProcurado(cenario).getApostasDoCenario()) {
			valorTotal += aposta.getValorAposta();
		}
		return valorTotal;
	}
	
	/**
	 * Método que retorna uma representação textual de todas apostas do Cenário especificado.
	 * @param cenario
	 * 		Representação numerica de um cenário, no formato Inteiro.
	 * @return a representação textual das apostas realizadas no Cenário especificado.
	 */
	public String exibeApostas(int cenario) {
		excecoes.cenarioInvalido(cenario, "Erro na consulta de aposta: Cenario invalido");
		excecoes.cenarioNaoCadastrado(cenario, cenarios, "Erro na consulta de aposta: Cenario nao cadastrado");
		return cenarioProcurado(cenario).toStringApostas();
	}
	
	/**
	 * Altera a ordem de disposição dos cenários na lista de cenários do sistema.
	 * Os cenários podem ser ordenados de três formas:
	 * Ordem Alfabética
	 * Ordem de Cadastro
	 * Ordem por Numero de Apostas
	 * @param ordem
	 * 		A representação textual da ordem utilizada para ordenar os Cenários, no formato String. 
	 */
	public void alterarOrdem(String ordem) {
		excecoes.argumentoInvalido(ordem, "Erro ao alterar ordem: Ordem nao pode ser vazia ou nula");
		excecoes.ordemInvalida(ordem, "Erro ao alterar ordem: Ordem invalida");
		switch(ordem.toLowerCase()) {
		case "apostas":
			cenariosOrdenados.clear();
			cenariosOrdenados.addAll(cenarios);
			Collections.sort(cenariosOrdenados, new ComparadorApostas());
			break;
		case "nome":
			cenariosOrdenados.clear();
			cenariosOrdenados.addAll(cenarios);
			Collections.sort(cenariosOrdenados, new ComparadorNome());
			break;
		case "cadastro":
			cenariosOrdenados.clear();
			cenariosOrdenados.addAll(cenarios);
			Collections.sort(cenariosOrdenados, new ComparadorID());
			break;
		}
	}
	
	/**
	 * Exibe a representação textual de um cênario na lista já ordenada do sistema.
	 * 
	 * @param cenario 
	 * 		Identificador inteiro do Cenário procurado.
	 * @return a representação textual do Cenário na lista ordenada de Cenários.
	 */
	public String exibirCenarioOrdenado(int cenario) {
		excecoes.cenarioInvalido(cenario, "Erro na consulta de cenario ordenado: Cenario invalido");
		excecoes.cenarioNaoCadastrado(cenario, cenarios, "Erro na consulta de cenario ordenado: Cenario nao cadastrado");
		if(cenariosOrdenados.isEmpty()) {cenariosOrdenados.addAll(cenarios);}
		return cenarioProcuradoOrdenado(cenario).toString();
	}
	
	/**
	 * Método que encerra o Cenário especificado e que modifica o estado do mesmo.
	 * @param cenario 
	 * 		Representação numerica de um cenário, no formato Inteiro.
	 * @param ocorreu
	 * 		O valor concretizado para a proposição do cenário, no formato boolean.
	 */
	public void fecharCenario(int cenario, boolean ocorreu) {
		excecoes.cenarioInvalido(cenario, "Erro ao fechar aposta: Cenario invalido");
		excecoes.cenarioNaoCadastrado(cenario, cenarios, "Erro ao fechar aposta: Cenario nao cadastrado");
		excecoes.cenarioJaFechado(cenario, cenarios, "Erro ao fechar aposta: Cenario ja esta fechado");
		if(ocorreu) cenarioProcurado(cenario).setEstado("Finalizado (ocorreu)");
		if(!ocorreu) cenarioProcurado(cenario).setEstado("Finalizado (n ocorreu)");
		cenarioProcurado(cenario).pagamentoSeguro(caixa);
	}
	
	/**
	 * Método que retorna o valor a ser recolhido para o Caixa, de um Cenário especificado.
	 * @param cenario
	 * 		Representação numerica de um cenário, no formato Inteiro.
	 * @return o Valor a ser descontado no rateio dos valores das apostas perdedoras do Cenário.
	 */
	public int getCaixaCenario(int cenario) {
		excecoes.cenarioInvalido(cenario, "Erro na consulta do caixa do cenario: Cenario invalido");
		excecoes.cenarioNaoCadastrado(cenario, cenarios, "Erro na consulta do caixa do cenario: Cenario nao cadastrado");
		excecoes.cenarioAindaAberto(cenario, cenarios, "Erro na consulta do caixa do cenario: Cenario ainda esta aberto");
		return (int) (cenarioProcurado(cenario).valorPerdedores() * caixa.getTaxa());
	}
	
	/**
	 * Retorna o Valor total rateado para apostadores vencedores de um cenário especificado.
	 * @param cenario
	 * 		Representação numerica de um cenário, no formato Inteiro.
	 * @return o Valor inteiro que representa o total a ser repassado para os apostadores vencedores do Cenário.
	 */
	public int getTotalRateioCenario(int cenario) {
		excecoes.cenarioInvalido(cenario, "Erro na consulta do total de rateio do cenario: Cenario invalido");
		excecoes.cenarioNaoCadastrado(cenario, cenarios, "Erro na consulta do total de rateio do cenario: Cenario nao cadastrado");
		excecoes.cenarioAindaAberto(cenario, cenarios, "Erro na consulta do total de rateio do cenario: Cenario ainda esta aberto");
		double valorCaixa = (cenarioProcurado(cenario).valorPerdedores() * caixa.getTaxa());
		caixa.adicionaValorCaixa(valorCaixa);
		return (int) Math.ceil(cenarioProcurado(cenario).valorPerdedores() - valorCaixa) + cenarioProcurado(cenario).getBonus();
	}
}