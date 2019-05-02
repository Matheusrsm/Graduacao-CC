package liMaCor;

public class Funcionario implements Comparable<Funcionario> {

	private String cpf, nome, departamento;
	private int qtdProjetos;
	
	public Funcionario(String nome, String cpf) {
		this.cpf = cpf;
		this.nome = nome;
		this.departamento = null;
		this.qtdProjetos = 0;
	}

	public String getCpf() {
		return cpf;
	}

	public String getNome() {
		return nome;
	}

	public String getDepartamento() {
		return departamento;
	}

	public void setDepartamento(String departamento) {
		this.departamento = departamento;
	}

	public int getQtdProjetos() {
		return qtdProjetos;
	}

	public void setQtdProjetos() {
		this.qtdProjetos ++;
	}

	@Override
	public String toString() {
		return nome + " - " + cpf + " - " + departamento + " - " + qtdProjetos;
	}

	@Override
	public int compareTo(Funcionario f) {
		return this.nome.compareTo(f.getNome());
	}
}