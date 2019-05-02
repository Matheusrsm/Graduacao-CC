package liMaCor;

import java.util.List;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;

public class Sistema {

	private Map<String, Funcionario> funcionarios;
	private Validacoes validacoes;
	private Comparator<Funcionario> ordem;
	private int numeroRepresentacao;

	public Sistema() {
		this.funcionarios = new HashMap<>();
		this.validacoes = new Validacoes();
		this.numeroRepresentacao = 0;
	}
	
	public void novoFuncionario(String nome, String cpf) {
		validacoes.validaCPFCadastramento(cpf, funcionarios);
		validacoes.validaNome(nome);
		funcionarios.put(cpf, new Funcionario(nome, cpf));
	}
	
	public String recuperaFuncionario(String cpf) {
		validacoes.validaCPFNaoCadastrado(cpf, funcionarios);
		return funcionarios.get(cpf).toString();
	}
	
	public void definirDepartamentoParaFuncionario(String nomeDpt, String cpf) {
		validacoes.validaNome(nomeDpt);
		validacoes.validaCPFNaoCadastrado(cpf, funcionarios);
		funcionarios.get(cpf).setDepartamento(nomeDpt);
	}
	
	public void configurarOrdem(String ordem) { 
		if(ordem.equalsIgnoreCase("nome")) this.ordem = new ComparadorNome();
		else if(ordem.equalsIgnoreCase("projetos")) this.ordem = new ComparadorQtdProjetos();
	}
	
	public String listarFuncionarios() {
		String saida = "";
		List<Funcionario> listaDeFuncionarios = new ArrayList<>();
		for(Funcionario f: funcionarios.values()) listaDeFuncionarios.add(f);
		Collections.sort(listaDeFuncionarios, ordem);
		for(Funcionario f: listaDeFuncionarios) saida += f.toString() + System.lineSeparator();
		return saida;
	}
	
	public void gravarRepresentacaoFuncionarios() {
		numeroRepresentacao ++;
		File representacaoArquivo = null;
		try {
			representacaoArquivo = new File("resumoFuncionarios_" + numeroRepresentacao + ".txt");
			ObjectOutputStream escritaObjeto = new ObjectOutputStream(new FileOutputStream(representacaoArquivo));
			escritaObjeto.writeObject(listarFuncionarios());
			escritaObjeto.close();
		} catch(IOException e) {e.printStackTrace();}
	}
	
	/*public void addProjeto(String cpf) {
		funcionarios.get(cpf).setQtdProjetos();
	}*/
}