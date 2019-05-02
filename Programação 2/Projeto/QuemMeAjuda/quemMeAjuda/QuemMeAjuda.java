package quemMeAjuda;

import easyaccept.EasyAccept;
import quemMeAjuda.Sistemas.*;

public class QuemMeAjuda {
	
	private Sistema sistema = new Sistema();
	
	public static void main(String[] args){
		args = new String[] {"quemMeAjuda.QuemMeAjuda", "acceptance_test/us1_test.txt",
											  			"acceptance_test/us2_test.txt",
											  			"acceptance_test/us3_test.txt",
											  			"acceptance_test/us4_test.txt",
											  			"acceptance_test/us5_test.txt",
											  			"acceptance_test/us6_test.txt"};
		EasyAccept.main(args);
	}
	
	public void cadastrarAluno(String  nome, String matricula, int codigoCurso, String telefone, String email) throws Exception {
		sistema.cadastrarAluno(nome, matricula, codigoCurso, telefone, email);
	}
	
	public String listarAlunos() {
		return sistema.listarAlunos();
	}
	
	public String recuperaAluno(String matricula) throws Exception {
		return sistema.recuperaAluno(matricula);		
	}
	
	public String getInfoAluno(String matricula, String atributo) throws Exception {
		return sistema.getInfoAluno(matricula, atributo);
	}
	
	public void tornarTutor(String matricula, String disciplina, int proficiencia) throws Exception {
		sistema.tornarTutor(matricula, disciplina, proficiencia);
	}
	
	public String recuperaTutor(String matricula) throws Exception {
		return sistema.recuperaTutor(matricula).toString();
	}
	
	public String listarTutores() {
		return sistema.listarTutores();
	}
	
	public void cadastrarHorario(String email, String horario, String dia) throws Exception {
		sistema.cadastrarHorario(email, horario, dia);
	}
	
	public void cadastrarLocalDeAtendimento(String email, String local) throws Exception {
		sistema.cadastrarLocalDeAtendimento(email, local);
	}
	
	public boolean consultaHorario(String email, String horario, String dia) {
		return sistema.consultaHorario(email, horario, dia);
	}
	
	public boolean consultaLocal(String email, String local) {
		return sistema.consultaLocal(email, local);
	}
	
	public int pedirAjudaPresencial(String matrAluno, String disciplina, String horario, String dia, String localInteresse) throws Exception {
		return sistema.pedirAjudaPresencial(matrAluno, disciplina, horario, dia, localInteresse);
	}
	
	public int pedirAjudaOnline(String matrAluno, String disciplina) throws Exception {
		return sistema.pedirAjudaOnline(matrAluno, disciplina);
	}
	
	public String pegarTutor(int idAjuda) throws Exception {
		return sistema.pegarTutor(idAjuda);
	}
	
	public String getInfoAjuda(int idAjuda, String atributo) throws Exception {
		return sistema.getInfoAjuda(idAjuda, atributo);
	}
	
	public void avaliarTutor(int idAjuda, int nota) throws Exception {
		sistema.avaliarTutor(idAjuda, nota);
	}
	
	public String pegarNota(String matriculaTutor) {
		return sistema.pegarNota(matriculaTutor);
	}

	public String pegarNivel(String matriculaTutor) {
		return sistema.pegarNivel(matriculaTutor);
	}
	
	public void doar(String matriculaTutor, int totalCentavos) throws Exception {
		sistema.doar(matriculaTutor, totalCentavos);
	}
	
	public int totalDinheiroTutor(String emailTutor) throws Exception {
		return sistema.totalDinheiroTutor(emailTutor);
	}
	
	public int totalDinheiroSistema() {
		return sistema.totalDinheiroSistema();
	}
	
	public void configurarOrdem(String atributo) {
		sistema.configurarOrdem(atributo);
	}
	
	public void salvar() {
		sistema.salvar();
	}
	
	public void carregar() {
		sistema.carregar();
	}
	
	public void limpar() {
		sistema.limpar();
	}
}