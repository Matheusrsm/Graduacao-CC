package liMaCor;

import java.util.Map;

public class Validacoes {

	public void validaCPFCadastramento(String cpf, Map<String, Funcionario> funcionarios) {
		validaCPF(cpf);
		if(funcionarios.get(cpf) != null) throw new SystemHasObjectException("Funcionario ja existe");
	}
	
	public void validaCPF(String cpf) {
		if(cpf == null) throw new NullPointerException("CPF nao pode ser nulo!");
		if(cpf.trim().isEmpty()) throw new IllegalArgumentException("CPF nao pode ser vazio!");
	}
	
	public void validaNome(String nome) {
		if(nome == null) throw new NullPointerException("Nome nao pode ser nulo!");
		if(nome.trim().isEmpty()) throw new IllegalArgumentException("Nome nao pode ser vazio!");
	}
	
	public void validaCPFNaoCadastrado(String cpf, Map<String, Funcionario> funcionarios) {
		validaCPF(cpf);
		if(funcionarios.get(cpf) == null) throw new SystemDoesNotHaveObjectException("Funcionario nao existe");
	}
}
