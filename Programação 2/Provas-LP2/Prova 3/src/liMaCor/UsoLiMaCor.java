package liMaCor;

public class UsoLiMaCor {

	public static void main(String[] args) {
		Sistema sis = new Sistema();
		sis.novoFuncionario("Matheus Gaudencio", "1234");
		sis.definirDepartamentoParaFuncionario("Pesquisa em Tecnologias Web 3.4", "1234");
		sis.novoFuncionario("Livia Maria", "40028922");
		sis.definirDepartamentoParaFuncionario("Software de Padaria", "40028922");
		System.out.println(sis.recuperaFuncionario("1234"));
		System.out.println(sis.recuperaFuncionario("40028922"));
		sis.configurarOrdem("NOME");
		/*sis.addProjeto("1234");
		sis.addProjeto("1234");
		sis.addProjeto("40028922");*/
		System.out.println(sis.recuperaFuncionario("1234"));
		System.out.println(sis.recuperaFuncionario("40028922"));
		System.out.println(sis.listarFuncionarios());
		sis.gravarRepresentacaoFuncionarios();
		sis.configurarOrdem("PROJETOS");
		System.out.println(sis.listarFuncionarios());
		sis.gravarRepresentacaoFuncionarios();
	}

}
