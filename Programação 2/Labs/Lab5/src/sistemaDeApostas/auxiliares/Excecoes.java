package sistemaDeApostas.auxiliares;

import java.util.ArrayList;

import sistemaDeApostas.cenario.Cenario;

/**
 * Classe de Validação dos Argumentos passados. Verifica argumentos inválidos e lança
 * exceções, caso sejam.
 * 
 * @author Matheus Silva - 117110412
 */
public class Excecoes {

	public void argumentoInvalido(String argumento, String msg) {
		if(argumento.trim().isEmpty()) throw new IllegalArgumentException(msg);
	}
	
	public void argumentoNulo(String argumento, String msg) {
		if(argumento == null) throw new NullPointerException(msg);
	}
	
	@SuppressWarnings("unused")
	public void argumentoInvalidoOuNulo(String argumento, String msg) {
		if(argumento.trim().isEmpty()) throw new IllegalArgumentException(msg);
		if(argumento == null) throw new NullPointerException(msg);
	}
	
	public void ordemInvalida(String ordem, String msg) {
		if(!ordem.equalsIgnoreCase("apostas") && !ordem.equalsIgnoreCase("nome") && !ordem.equalsIgnoreCase("cadastro")) {
			throw new IllegalArgumentException(msg);
		}
	}
		
	public void cenarioNaoCadastrado(int numeroCenario, ArrayList<Cenario> cenarios, String msg) {
		if(numeroCenario > cenarios.size()) throw new ArrayIndexOutOfBoundsException(msg);
	}
	
	public void cenarioBonusInvalido(int bonus, String msg) {
		if(bonus <= 0) throw new IllegalArgumentException(msg);
	}
	
	public void cenarioJaFechado(int numeroCenario, ArrayList<Cenario> cenarios, String msg) {
		if(!cenarios.get(numeroCenario - 1).getEstado().equals("Nao finalizado")) {
			throw new IllegalArgumentException(msg);
		}
	}
	
	public void cenarioAindaAberto(int numeroCenario, ArrayList<Cenario> cenarios, String msg) {
		if(cenarios.get(numeroCenario - 1).getEstado().equals("Nao finalizado")) {
			throw new IllegalArgumentException(msg);
		}
	}
	
	public void cenarioInvalido(int numeroCenario, String msg) {
		if(numeroCenario < 1) throw new IllegalArgumentException(msg);
	}
	
	public void valorInvalido(int valor, String msg) {
		if(valor <= 0) throw new IllegalArgumentException(msg);
	}
	
	public void previsaoInvalida(String previsao, String msg) {
		if(!previsao.equals("VAI ACONTECER") && !previsao.equals("N VAI ACONTECER")) {
			throw new IllegalArgumentException(msg);
		}
	}
	
	public void inicializacaoCaixaInvalido(int valorCaixa, String msg) {
		if(valorCaixa < 0) throw new IllegalArgumentException(msg);
	}
	
	public void inicializacaoTaxaInvalida(double valorTaxa, String msg) {
		if(valorTaxa <= 0) throw new IllegalArgumentException(msg);
	}
}