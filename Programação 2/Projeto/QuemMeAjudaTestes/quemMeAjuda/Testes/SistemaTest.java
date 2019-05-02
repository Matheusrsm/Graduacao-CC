package quemMeAjuda.Testes;
import static org.junit.Assert.*;

import org.junit.Test;

import quemMeAjuda.Excecoes.*;
import quemMeAjuda.Sistemas.*;

/**
 * Classe de testes da Classe Sistema
 * 
 * @author Wesley Monte, Lukas Nascimento, Matheus Silva
 *
 */
public class SistemaTest {
	
	private Sistema sis = new Sistema();
	
	/**
	 * Testa o cadastro de um Aluno valido.
	 * @throws Exception
	 */
	@Test
	public void testCadastrarAlunoValido() throws Exception{
		sis.cadastrarAluno("Pedro", "123123123", 100, "88889999", "pedro@gmail.com");
		assertEquals("123123123 - Pedro - 100 - 88889999 - pedro@gmail.com", sis.getAlunos().get("123123123").toString());
	}
	
	/**
	 * Testa o cadastro de um Aluno sem telefone.
	 * @throws Exception
	 */
	@Test
	public void testCadastrarAlunoSemTelefone() throws Exception {
		sis.cadastrarAluno("Wesley", "123456789", 100, "", "wesley@gmail.com");
		assertEquals("123456789 - Wesley - 100 - wesley@gmail.com", sis.getAlunos().get("123456789").toString());
	}
	
	/**
	 * Testa o cadastro de um Aluno com nome vazio.
	 * @throws Exception
	 */
	@Test(expected = Exception.class)
	public void testCadastrarAlunoNomeVazio() throws Exception {
		sis.cadastrarAluno("", "123456789", 100, "55551111", "wesley@gmail.com");
	}
	
	/**
	 * Testa o cadastro de um Aluno com nome nulo.
	 * @throws Exception
	 */
	@Test(expected = Exception.class)
	public void testCadastrarAlunoNomeNulo() throws Exception {
		sis.cadastrarAluno(null, "123456789", 100, "99996666", "eu@gmail.com");
	}
	
	/**
	 * Testa o cadastro de um Aluno com email vazio.
	 * @throws Exception
	 */
	@Test(expected = Exception.class)
	public void testCadastrarEmailVazio() throws Exception {
		sis.cadastrarAluno("Lukas", "111111000", 100, "9888745214", "");
	}
	
	/**
	 * Testa o cadastro de um Aluno com email sem "@".
	 * @throws Exception
	 */
	@Test(expected = Exception.class)
	public void testCadastrarEmailSemArroba() throws Exception {
		sis.cadastrarAluno("Matheus", "110555999", 100, "2030105040", "matheus.gmail.com");
	}
	
	/**
	 * Testa o cadastro de um Aluno de email com "@" no inicio.
	 * @throws Exception
	 */
	@Test(expected = Exception.class)
	public void testCadastrarEmailArrobaNoInicio() throws Exception {
		sis.cadastrarAluno("Wesley", "123456789", 100, "33335555", "@wesley@gmail.com");
	}
	
	/**
	 * Testa o cadastro de um Aluno de email com "@" no final.
	 * @throws Exception
	 */
	@Test(expected = Exception.class)
	public void testCadastrarEmailArrobaNoFinal() throws Exception {
		sis.cadastrarAluno("Wesley", "123456789", 100, "10108989", "wesley.gmail.com@");
	}
	
	/**
	 * Testa o cadastro de um Aluno com email igual a "@".
	 * @throws Exception
	 */
	@Test(expected = Exception.class)
	public void testCadastrarEmailSoArroba() throws Exception {
		sis.cadastrarAluno("Wesley", "1230101010", 100, "999995623", "@");
	}
	
	/**
	 * Testa o cadastro de um Aluno com email nulo.
	 * @throws Exception
	 */
	@Test(expected = Exception.class)
	public void testCadastrarEmailNulo() throws Exception {
		sis.cadastrarAluno("Wesley", "123456789", 100, "10001000", null);
	}
	
	/**
	 * Testa o cadastro de um Aluno com matricula ja cadastrada.
	 * @throws Exception
	 */
	@Test(expected = Exception.class)
	public void testCadastrarMatriculaJaCadastrada() throws Exception {
		sis.cadastrarAluno("Wesley", "123456789", 100, "978548855", "wesley@gmail.com");
		sis.cadastrarAluno("Lukas", "123456789", 100, "988554774", "lukas@gmail.com");
	}
	
	/**
	 * Testa recuperar um Aluno válido.
	 * @throws Exception
	 */
	@Test
	public void testRecuperaAluno() throws Exception {
		sis.cadastrarAluno("Matheus", "123456789", 100, "10008000", "matheus@gmail.com");
		assertEquals("123456789 - Matheus - 100 - 10008000 - matheus@gmail.com", sis.recuperaAluno("123456789"));
	}
	
	/**
	 * Testa recuperar um Aluno nao cadastrado.
	 * @throws Exception
	 */
	@Test(expected = DadoInvalidoException.class)
	public void testRecuperaAlunoNaoCadastrado() throws Exception {
		sis.recuperaAluno("100100100");
	}
	
	/**
	 * Testa listar os Alunos ordenados pelo nome.
	 * @throws Exception
	 */
	@Test
	public void testListarAlunosPorNome() throws Exception {
		String lista = "147258369 - Lukas - 100 - 99998888 - lukas@gmail.com, 100101102 - Matheus - 100 - 987654321 - matheus@gmail.com, "
				+ "123456789 - Wesley - 100 - wesley@gmail.com";
		sis.cadastrarAluno("Matheus", "100101102", 100, "987654321", "matheus@gmail.com");
		sis.cadastrarAluno("Lukas", "147258369", 100, "99998888", "lukas@gmail.com");
		sis.cadastrarAluno("Wesley", "123456789", 100, "", "wesley@gmail.com");
		assertEquals(lista, sis.listarAlunos());
	}
	
	/**
	 * Testa listar os Alunos ordenados pela matricula.
	 * @throws Exception
	 */
	@Test
	public void testListarAlunosPorMatricula() throws Exception {
		String lista = "123456789 - Wesley - 100 - wesley@gmail.com, 147258369 - Lukas - 100 - 99998888 - lukas@gmail.com, "
				+ "987654321 - Matheus - 100 - 987654321 - matheus@gmail.com";
		sis.cadastrarAluno("Matheus", "987654321", 100, "987654321", "matheus@gmail.com");
		sis.cadastrarAluno("Lukas", "147258369", 100, "99998888", "lukas@gmail.com");
		sis.cadastrarAluno("Wesley", "123456789", 100, "", "wesley@gmail.com");
		sis.configurarOrdem("MATRICULA");
		assertEquals(lista, sis.listarAlunos());
	}
	
	/**
	 * Testa listar os Alunos ordenados pelo email.
	 * @throws Exception
	 */
	@Test
	public void testListarAlunosPorEmail() throws Exception {
		String lista = "123456789 - Wesley - 100 - monte.wesley@gmail.com, 147258369 - Lukas - 100 - 99998888 - nascimento.lukas@gmail.com, "
				+ "100101102 - Matheus - 100 - 987654321 - silva.matheus@gmail.com";
		sis.cadastrarAluno("Matheus", "100101102", 100, "987654321", "silva.matheus@gmail.com");
		sis.cadastrarAluno("Lukas", "147258369", 100, "99998888", "nascimento.lukas@gmail.com");
		sis.cadastrarAluno("Wesley", "123456789", 100, "", "monte.wesley@gmail.com");
		sis.configurarOrdem("EMAIL");
		assertEquals(lista, sis.listarAlunos());
	}
	
	/**
	 * Testa pegar o nome de um Aluno.
	 * @throws Exception
	 */
	@Test
	public void testGetInfoAlunoNome() throws Exception {
		sis.cadastrarAluno("Wesley", "987654321", 100, "33339999", "wesley@gmail.com");
		assertEquals("Wesley", sis.getInfoAluno("987654321", "nome"));
	}
	
	/**
	 * Testa pegar o codigo do curso de um Aluno.
	 * @throws Exception
	 */
	@Test
	public void testGetInfoAlunoCodigoCurso() throws Exception {
		sis.cadastrarAluno("Wesley", "987654321", 100, "33339999", "wesley@gmail.com");
		assertEquals("100", sis.getInfoAluno("987654321", "codigo do curso"));
	}
	
	/**
	 * Testa pegar o telefone de um Aluno.
	 * @throws Exception
	 */
	@Test
	public void testGetInfoAlunoTelefone() throws Exception {
		sis.cadastrarAluno("Wesley", "987654321", 100, "33339999", "wesley@gmail.com");
		assertEquals("33339999", sis.getInfoAluno("987654321", "telefone"));
	}
	
	/**
	 * Testa pegar o email de um Aluno.
	 * @throws Exception
	 */
	@Test
	public void testGetInfoAlunoEmail() throws Exception {
		sis.cadastrarAluno("Wesley", "987654321", 100, "33339999", "wesley@gmail.com");
		assertEquals("wesley@gmail.com", sis.getInfoAluno("987654321", "email"));
	}
	
	/**
	 * Testa pegar uma informacao nula de um Aluno.
	 * @throws Exception
	 */
	@Test
	public void testGetInfoAlunoDefault() throws Exception {
		sis.cadastrarAluno("Wesley", "987654321", 100, "33339999", "wesley@gmail.com");
		assertEquals(null, sis.getInfoAluno("987654321", "idade"));
	}
	
	/**
	 * Testa pegar uma informacao de um Aluno nao cadastrado.
	 * @throws Exception
	 */
	@Test(expected = DadoInvalidoException.class)
	public void testGetInfoAlunoNaoCadastrado() throws Exception {
		sis.cadastrarAluno("Wesley", "987654321", 100, "33339999", "wesley@gmail.com");
		sis.getInfoAluno("123456789", "email");
	}
	
	/**
	 * Testa tornar um Aluno um tutor.
	 * @throws Exception
	 */
	@Test
	public void testTornarTutor() throws Exception {
		sis.cadastrarAluno("Wesley", "123456789", 100, "33336666", "wesley@gmail.com");
		sis.tornarTutor("123456789", "Calculo 2", 3);
		assertEquals("Calculo 2", sis.getAlunos().get("123456789").getTutoria().getDisciplinas().get(0).getNome(), "Calculo 2");
	}
	
	/**
	 * Testa tornar um Aluno um tutor de outra(s) disciplina(s).
	 * @throws Exception
	 */
	@Test
	public void testTornarTutorUmTutor() throws Exception{
		sis.cadastrarAluno("Wesley", "123456789", 100, "33336666", "wesley@gmail.com");
		sis.tornarTutor("123456789", "Calculo 2", 3);
		sis.tornarTutor("123456789", "Vetorial", 4);
		assertEquals("Calculo 2", sis.getAlunos().get("123456789").getTutoria().getDisciplinas().get(0).getNome(), "Calculo 2");
		assertEquals("Vetorial", sis.getAlunos().get("123456789").getTutoria().getDisciplinas().get(1).getNome(), "Vetorial");
	}
	
	/**
	 * Testa tornar um Aluno um tutor de uma disciplina que ja eh tutor.
	 * @throws Exception
	 */
	@Test(expected = DadoInvalidoException.class)
	public void testTornarTutorJaComDisciplina() throws Exception{
		sis.cadastrarAluno("Wesley", "123456789", 100, "33336666", "wesley@gmail.com");
		sis.tornarTutor("123456789", "Calculo 2", 3);
		sis.tornarTutor("123456789", "Calculo 2", 4);
	}
	
	/**
	 * Testa tornar um Aluno um tutor com proficiencia invalida.
	 * @throws Exception
	 */
	@Test(expected = DadoInvalidoException.class)
	public void testTornarTutorUmTutorProficienciaInvalida() throws Exception{
		sis.cadastrarAluno("Wesley", "123456789", 100, "33336666", "wesley@gmail.com");
		sis.tornarTutor("123456789", "Calculo 2", 3);
		sis.tornarTutor("123456789", "Vetorial", 6);
	}
	
	/**
	 * Testa tornar um Aluno inexistente um tutor.
	 * @throws Exception
	 */
	@Test(expected = DadoInvalidoException.class)
	public void testTornarTutorInexistente() throws Exception{
		sis.cadastrarAluno("Wesley", "123456789", 100, "33336666", "wesley@gmail.com");
		sis.tornarTutor("987654321", "Calculo 2", 3);
	}
	
	/**
	 * Testa tornar um Aluno um tutor com disciplina vazia.
	 * @throws Exception
	 */
	@Test (expected = DadoInvalidoException.class)
	public void testTornarTutorDisciplinaVazio() throws Exception {
		sis.cadastrarAluno("Lukas", "666", 666, "", "lukas@live.com");
		sis.tornarTutor("666", "", 3);
	}
	
	/**
	 * Testa recuperar um tutor.
	 * @throws Exception
	 */
	@Test
	public void testRecuperaTutor() throws Exception {
		sis.cadastrarAluno("Lukas", "123456789", 100, "33336666", "lukas@live.com");
		sis.tornarTutor("123456789", "Calculo 1", 3);
		sis.tornarTutor("123456789", "Vetorial", 4);
		assertEquals("123456789 - Lukas - 100 - 33336666 - lukas@live.com", sis.recuperaTutor("123456789").toString());
	}
	
	/**
	 * Testa recuperar um tutor inexistente.
	 * @throws Exception
	 */
	@Test (expected = NullPointerException.class)
	public void testRecuperaTutorInexistente() throws Exception {
		sis.cadastrarAluno("Wesley", "123456789", 100, "33336666", "wesley@gmail.com");
		sis.recuperaTutor("123456789");
	}
	
	/**
	 * Testa listar tutores pelo nome.
	 * @throws Exception
	 */
	@Test
	public void testListarTutoresPorNome() throws Exception {
		String lista = "147258369 - Lukas - 100 - 99998888 - lukas@gmail.com, 100101102 - Matheus - 100 - 987654321 - matheus@gmail.com, "
				+ "123456789 - Wesley - 100 - wesley@gmail.com";
		sis.cadastrarAluno("Matheus", "100101102", 100, "987654321", "matheus@gmail.com");
		sis.cadastrarAluno("Lukas", "147258369", 100, "99998888", "lukas@gmail.com");
		sis.cadastrarAluno("Wesley", "123456789", 100, "", "wesley@gmail.com");
		sis.tornarTutor("100101102", "P2", 4);
		sis.tornarTutor("147258369", "P2", 4);
		sis.tornarTutor("123456789", "P2", 4);
		assertEquals(lista, sis.listarTutores());
	}
	
	/**
	 * Testa listar tutores pela matricula.
	 * @throws Exception
	 */
	@Test
	public void testListarTutoresPorMatricula() throws Exception {
		String lista = "123456789 - Wesley - 100 - wesley@gmail.com, 147258369 - Lukas - 100 - 99998888 - lukas@gmail.com, "
				+ "987654321 - Matheus - 100 - 987654321 - matheus@gmail.com";
		sis.cadastrarAluno("Matheus", "987654321", 100, "987654321", "matheus@gmail.com");
		sis.cadastrarAluno("Lukas", "147258369", 100, "99998888", "lukas@gmail.com");
		sis.cadastrarAluno("Wesley", "123456789", 100, "", "wesley@gmail.com");
		sis.tornarTutor("987654321", "P2", 4);
		sis.tornarTutor("147258369", "P2", 4);
		sis.tornarTutor("123456789", "P2", 4);
		sis.configurarOrdem("MATRICULA");
		assertEquals(lista, sis.listarTutores());
	}
	
	/**
	 * Testa listar tutores pelo email.
	 * @throws Exception
	 */
	@Test
	public void testListarTutoresPorEmail() throws Exception {
		String lista = "123456789 - Wesley - 100 - monte.wesley@gmail.com, 147258369 - Lukas - 100 - 99998888 - nascimento.lukas@gmail.com, "
				+ "100101102 - Matheus - 100 - 987654321 - silva.matheus@gmail.com";
		sis.cadastrarAluno("Matheus", "100101102", 100, "987654321", "silva.matheus@gmail.com");
		sis.cadastrarAluno("Lukas", "147258369", 100, "99998888", "nascimento.lukas@gmail.com");
		sis.cadastrarAluno("Wesley", "123456789", 100, "", "monte.wesley@gmail.com");
		sis.tornarTutor("100101102", "P2", 4);
		sis.tornarTutor("147258369", "P2", 4);
		sis.tornarTutor("123456789", "P2", 4);
		sis.configurarOrdem("EMAIL");
		assertEquals(lista, sis.listarTutores());
	}
	
	/**
	 * Testa cadastrar horario de um tutor.
	 * @throws Exception
	 */
	@Test
	public void testCadastrarHorario() throws Exception {
		sis.cadastrarAluno("Lukas", "123456789", 100, "33336666", "lukas@live.com");
		sis.tornarTutor("123456789", "Calculo 1", 3);
		sis.tornarTutor("123456789", "Vetorial", 4);
		sis.cadastrarHorario("lukas@live.com", "15:00", "seg");
		assertEquals("15:00", sis.getAlunos().get("123456789").getTutoria().getHorarios().get(0).getHorario());
		assertEquals("seg", sis.getAlunos().get("123456789").getTutoria().getHorarios().get(0).getDia());
	}
	
	/**
	 * Testa cadastrar horario de um tutor com email vazio.
	 * @throws Exception
	 */
	@Test(expected = DadoInvalidoException.class)
	public void testCadastrarHorarioEmailVazio() throws Exception{
		sis.cadastrarHorario(" ", "10:00", "qua");
	}
	
	/**
	 * Testa cadastrar horario de um tutor com email invalido.
	 * @throws Exception
	 */
	@Test(expected = DadoInvalidoException.class)
	public void testCadastrarHorarioEmailInvalido() throws Exception{
		sis.cadastrarHorario("@wesley.gmail.com", "10:00", "qua");
	}
	
	/**
	 * Testa cadastrar horario de um tutor com email nulo.
	 * @throws Exception
	 */
	@Test(expected = DadoNuloException.class)
	public void testCadastrarHorarioEmailNulo() throws Exception{
		sis.cadastrarHorario(null, "10:00", "qua");
	}
	
	/**
	 * Testa cadastrar horario com hora vazia de um tutor.
	 * @throws Exception
	 */
	@Test(expected = DadoInvalidoException.class)
	public void testCadastrarHorarioVazio() throws Exception{
		sis.cadastrarAluno("Wesley", "123456789", 100, "", "wesley@gmail.com");
		sis.tornarTutor("123456789", "Vetorial", 4);
		sis.cadastrarHorario("wesley@gmail.com", "", "qua");
	}
	
	/**
	 * Testa cadastrar horario com dia vazio de um tutor.
	 * @throws Exception
	 */
	@Test(expected = DadoInvalidoException.class)
	public void testCadastrarHorarioDiaVazio() throws Exception{
		sis.cadastrarAluno("Wesley", "123456789", 100, "", "wesley@gmail.com");
		sis.tornarTutor("123456789", "Vetorial", 4);
		sis.cadastrarHorario("wesley@gmail.com", "11:00", "");
	}
	
	/**
	 * Testa cadastrar horario de um tutor inexistente.
	 * @throws Exception
	 */
	@Test(expected = DadoInvalidoException.class)
	public void testCadastrarHorarioTutorInexistente() throws Exception{
		sis.cadastrarAluno("Wesley", "123456789", 100, "", "wesley@gmail.com");
		sis.tornarTutor("123456789", "Vetorial", 4);
		sis.cadastrarHorario("lukas@gmail.com", "11:00", "qua");
	}
	
	/**
	 * Testa cadastrar local de atendimento de um tutor.
	 * @throws Exception
	 */
	@Test
	public void testCadastrarLocalDeAtendimento() throws Exception {
		sis.cadastrarAluno("Lukas", "123456789", 100, "33336666", "lukas@live.com");
		sis.tornarTutor("123456789", "Calculo 1", 3);
		sis.tornarTutor("123456789", "Vetorial", 4);
		sis.cadastrarLocalDeAtendimento("lukas@live.com", "LCC3");
		assertEquals("LCC3", sis.getAlunos().get("123456789").getTutoria().getLocais().get(0));
	}
	
	/**
	 * Testa cadastrar local de atendimento vazio de um tutor.
	 * @throws Exception
	 */
	@Test(expected = DadoInvalidoException.class)
	public void testCadastrarLocalDeAtendimentoLocalVazio() throws Exception {
		sis.cadastrarAluno("Wesley", "123456789", 100, "33336666", "wesley@gmail.com");
		sis.tornarTutor("123456789", "Calculo 2", 3);
		sis.tornarTutor("123456789", "Vetorial", 4);
		sis.cadastrarLocalDeAtendimento("wesley@gmail.com", "");
	}
	
	/**
	 * Testa cadastrar local de atendimento nulo de um tutor.
	 * @throws Exception
	 */
	@Test(expected = DadoNuloException.class)
	public void testCadastrarLocalDeAtendimentoLocalNulo() throws Exception {
		sis.cadastrarAluno("Wesley", "123456789", 100, "33336666", "wesley@gmail.com");
		sis.tornarTutor("123456789", "Calculo 2", 3);
		sis.tornarTutor("123456789", "Vetorial", 4);
		sis.cadastrarLocalDeAtendimento("wesley@gmail.com", null);
	}
	
	/**
	 * Testa cadastrar local de atendimento de um tutor com email vazio.
	 * @throws Exception
	 */
	@Test(expected = DadoInvalidoException.class)
	public void testCadastrarLocalDeAtendimentoEmailVazio() throws Exception {
		sis.cadastrarLocalDeAtendimento("", "LCC3");
	}
	
	/**
	 * Testa cadastrar local de atendimento de um tutor com email nulo.
	 * @throws Exception
	 */
	@Test(expected = DadoNuloException.class)
	public void testCadastrarLocalDeAtendimentoEmailNulo() throws Exception {
		sis.cadastrarLocalDeAtendimento(null, "LCC3");
	}
	
	/**
	 * Testa cadastrar local de atendimento de um tutor nao cadastrado.
	 * @throws Exception
	 */
	@Test(expected = DadoInvalidoException.class)
	public void testCadastrarLocalDeAtendimentoTutorNaoCadastrado() throws Exception {
		sis.cadastrarAluno("Wesley", "123456789", 100, "33336666", "wesley@gmail.com");
		sis.tornarTutor("123456789", "Calculo 2", 3);
		sis.tornarTutor("123456789", "Vetorial", 4);
		sis.cadastrarLocalDeAtendimento("matheus@gmail.com", "LCC3");
	}
	
	/**
	 * Testa consultar horario de um tutor (sendo verdade).
	 * @throws Exception
	 */
	@Test
	public void testConsultaHorarioTrue() throws Exception {
		sis.cadastrarAluno("Lukas", "123456789", 100, "33336666", "lukas@live.com");
		sis.tornarTutor("123456789", "Calculo 1", 3);
		sis.tornarTutor("123456789", "Vetorial", 4);
		sis.cadastrarHorario("lukas@live.com", "15:00", "seg");
		assertEquals(true, sis.consultaHorario("lukas@live.com", "15:00", "seg"));
	}
	
	/**
	 * Testa consultar horario de um tutor (sendo falso).
	 * @throws Exception
	 */
	@Test
	public void testConsultaHorarioFalse() throws Exception {
		sis.cadastrarAluno("Lukas", "123456789", 100, "33336666", "lukas@live.com");
		sis.tornarTutor("123456789", "Calculo 1", 3);
		sis.tornarTutor("123456789", "Vetorial", 4);
		sis.cadastrarHorario("lukas@live.com", "15:00", "seg");
		assertEquals(false, sis.consultaHorario("lukas@live.com", "15:00", "sex"));
	}
	
	/**
	 * Testa consultar local de atendimento de um tutor (sendo verdade).
	 * @throws Exception
	 */
	@Test
	public void testConsultaLocalDeAtendimentoTrue() throws Exception {
		sis.cadastrarAluno("Lukas", "123456789", 100, "33336666", "lukas@live.com");
		sis.tornarTutor("123456789", "Calculo 1", 3);
		sis.tornarTutor("123456789", "Vetorial", 4);
		sis.cadastrarLocalDeAtendimento("lukas@live.com", "LCC3");
		assertEquals(true, sis.consultaLocal("lukas@live.com", "LCC3"));
	}
	
	/**
	 * Testa consultar local de atendimento de um tutor (sendo falso).
	 * @throws Exception
	 */
	@Test
	public void testConsultaLocalDeAtendimentoFalse() throws Exception {
		sis.cadastrarAluno("Lukas", "123456789", 100, "33336666", "lukas@live.com");
		sis.tornarTutor("123456789", "Calculo 1", 3);
		sis.tornarTutor("123456789", "Vetorial", 4);
		sis.cadastrarLocalDeAtendimento("lukas@live.com", "LCC3");
		assertEquals(false, sis.consultaLocal("lukas@live.com", "LCC2"));
	}
	
	/**
	 * Testa pedir ajuda presencial.
	 * @throws Exception
	 */
	@Test
	public void testPedirAjudaPresencial() throws Exception {
		sis.pedirAjudaPresencial("123", "Calculo 1", "15:00", "seg", "LCC3");
	}
	
	/**
	 * Testa pedir ajuda presencial com disciplina invalida.
	 * @throws Exception
	 */
	@Test (expected = DadoInvalidoException.class)
	public void testPedirAjudaPresencialDisciplinaInvalida() throws Exception {
		sis.pedirAjudaPresencial("123", " ", "15:00", "seg", "LCC3");
	}
	
	/**
	 * Testa pedir ajuda presencial com horario invalido.
	 * @throws Exception
	 */
	@Test (expected = DadoInvalidoException.class)
	public void testPedirAjudaPresencialHorarioInvalido() throws Exception {
		sis.pedirAjudaPresencial("123", "Calculo 1", " ", "seg", "LCC3");
	}
	
	/**
	 * Testa pedir ajuda presencial com matricula invalida.
	 * @throws Exception
	 */
	@Test (expected = DadoInvalidoException.class)
	public void testPedirAjudaPresencialMatriculaInvalida() throws Exception {
		sis.pedirAjudaPresencial(" ", "Calculo 1", "15:00", "seg", "LCC3");
	}
	
	/**
	 * Testa pedir ajuda presencial com local invalido.
	 * @throws Exception
	 */
	@Test (expected = DadoInvalidoException.class)
	public void testPedirAjudaPresencialLocalInvalido() throws Exception {
		sis.pedirAjudaPresencial("123", "Calculo 1", "15:00", "seg", " ");
	}
	
	/**
	 * Testa pedir ajuda presencial com dia invalido.
	 * @throws Exception
	 */
	@Test (expected = DadoInvalidoException.class)
	public void testPedirAjudaPresencialDiaInvalido() throws Exception {
		sis.pedirAjudaPresencial("123", "Calculo 1", "15:00", " ", "LCC3");
	}
	
	/**
	 * Testa pedir ajuda online.
	 * @throws Exception
	 */
	@Test
	public void testPedirAjudaOnline() throws Exception {
		sis.pedirAjudaOnline("123", "P1");
	}
	
	/**
	 * Testa pedir ajuda online com matricula invalida.
	 * @throws Exception
	 */
	@Test (expected = DadoInvalidoException.class)
	public void testPedirAjudaOnlineMatriculaInvalida() throws Exception {
		sis.pedirAjudaOnline(" ", "P1");
	}
	
	/**
	 * Testa pedir ajuda online com disciplina invalida.
	 * @throws Exception
	 */
	@Test (expected = DadoInvalidoException.class)
	public void testPedirAjudaOnlineDisciplinaInvalida() throws Exception {
		sis.pedirAjudaOnline("123", " ");
	}
	
	/**
	 * Testa pegar o tutor de um pedido de ajuda.
	 * @throws Exception
	 */
	@Test 
	public void testPegarTutor() throws Exception {
		sis.cadastrarAluno("Lukas", "123", 100, "40028922", "lukas@live.com");
		sis.tornarTutor("123", "P1", 5);
		sis.tornarTutor("123", "Vetorial", 4);
		int a = sis.pedirAjudaOnline("321", "P1");
		assertEquals(sis.pegarTutor(a), "Tutor - 123, disciplina - P1");
	}
	
	/**
	 * Testa pegar o tutor de um pedido de ajuda com ID negativo.
	 * @throws Exception
	 */
	@Test (expected = DadoInvalidoException.class)
	public void testPegarTutorIdNegativo() throws Exception {
		sis.cadastrarAluno("Wesley", "123456789", 100, "33336666", "wesley@gmail.com");
		sis.tornarTutor("123456789", "Calculo 2", 3);
		sis.pegarTutor(-1);
	}

	/**
	 * Testa pegar o tutor de uma ajuda nao pedida.
	 * @throws Exception
	 */
	@Test (expected = DadoInvalidoException.class)
	public void testPegarTutorIdNaoCadastrado() throws Exception {
		sis.cadastrarAluno("Wesley", "123456789", 100, "33336666", "wesley@gmail.com");
		sis.tornarTutor("123456789", "Calculo 2", 3);
		sis.pedirAjudaOnline("123456789", "Calculo 2");
		sis.pegarTutor(3);
	}
	
	/**
	 * Testa avaliar um tutor.
	 * @throws Exception
	 */
	@Test
	public void testAvaliarTutor() throws Exception {
		sis.cadastrarAluno("Wesley", "123456789", 100, "33336666", "wesley@gmail.com");
		sis.cadastrarAluno("Henrique", "555", 100, "99996666", "henrique@gmail.com");
		sis.tornarTutor("123456789", "Calculo 2", 3);
		sis.pedirAjudaOnline("555", "Calculo 2");
		sis.avaliarTutor(1, 5);
		sis.getAlunos().get("123456789").getTutoria().setNivel();
		assertEquals("4,17", sis.getAlunos().get("123456789").getTutoria().toStringNotaDeAvaliacao());
	}
	
	/**
	 * Testa avaliar um tutor com nota invalida.
	 * @throws Exception
	 */
	@Test(expected = DadoInvalidoException.class)
	public void testAvaliarTutorNotaInvalida() throws Exception {
		sis.cadastrarAluno("Wesley", "123456789", 100, "33336666", "wesley@gmail.com");
		sis.cadastrarAluno("Henrique", "555", 100, "99996666", "henrique@gmail.com");
		sis.tornarTutor("123456789", "Calculo 2", 3);
		sis.pedirAjudaOnline("555", "Calculo 2");
		sis.avaliarTutor(1, 7);
	}
	
	/**
	 * Testa avaliar um tutor com id da ajuda invalido.
	 * @throws Exception
	 */
	@Test(expected = DadoInvalidoException.class)
	public void testAvaliarTutorIdInvalido() throws Exception {
		sis.cadastrarAluno("Wesley", "123456789", 100, "33336666", "wesley@gmail.com");
		sis.cadastrarAluno("Henrique", "555", 100, "99996666", "henrique@gmail.com");
		sis.tornarTutor("123456789", "Calculo 2", 3);
		sis.pedirAjudaOnline("555", "Calculo 2");
		sis.avaliarTutor(0, 5);
	}
	
	/**
	 * Testa avaliar um tutor com ajuda ja fechada.
	 * @throws Exception
	 */
	@Test(expected = DadoInvalidoException.class)
	public void testAvaliarTutorAjudaFechada() throws Exception {
		sis.cadastrarAluno("Wesley", "123456789", 100, "33336666", "wesley@gmail.com");
		sis.cadastrarAluno("Henrique", "555", 100, "99996666", "henrique@gmail.com");
		sis.tornarTutor("123456789", "Calculo 2", 3);
		sis.pedirAjudaOnline("555", "Calculo 2");
		sis.avaliarTutor(1, 5);
		sis.avaliarTutor(1, 5);
	}
	
	/**
	 * Testa pegar a nota de avaliacao de um tutor.
	 * @throws Exception
	 */
	@Test
	public void testPegarNota() throws Exception {
		sis.cadastrarAluno("Wesley", "123456789", 100, "33336666", "wesley@gmail.com");
		sis.cadastrarAluno("Henrique", "555", 100, "99996666", "henrique@gmail.com");
		sis.tornarTutor("123456789", "Calculo 2", 3);
		sis.pedirAjudaOnline("555", "Calculo 2");
		sis.avaliarTutor(1, 5);
		assertEquals("4,17", sis.pegarNota("123456789"));
	}
	
	/**
	 * Testa pegar a nota de avaliacao de um tutor inexistente.
	 * @throws Exception
	 */
	@Test(expected = NullPointerException.class)
	public void testPegarNotaTutorInexistente() throws Exception {
		sis.cadastrarAluno("Wesley", "123456789", 100, "33336666", "wesley@gmail.com");
		sis.cadastrarAluno("Henrique", "555", 100, "99996666", "henrique@gmail.com");
		sis.tornarTutor("123456789", "Calculo 2", 3);
		sis.pedirAjudaOnline("555", "Calculo 2");
		sis.avaliarTutor(1, 5);
		sis.pegarNota("987654321");
	}
	
	/**
	 * Testa pegar o nivel de um tutor.
	 * @throws Exception
	 */
	@Test
	public void testPegarNivel() throws Exception {
		sis.cadastrarAluno("Wesley", "123456789", 100, "33336666", "wesley@gmail.com");
		sis.cadastrarAluno("Henrique", "555", 100, "99996666", "henrique@gmail.com");
		sis.tornarTutor("123456789", "Calculo 2", 3);
		sis.pedirAjudaOnline("555", "Calculo 2");
		sis.avaliarTutor(1, 5);
		assertEquals("Tutor", sis.pegarNivel("123456789"));
	}
	
	/**
	 * Testa doar dinheiro a um tutor com nivel Aprendiz.
	 * @throws Exception
	 */
	@Test
	public void testDoarNivelAprendiz() throws Exception {
		sis.cadastrarAluno("Wesley", "123456789", 100, "33336666", "wesley@gmail.com");
		sis.cadastrarAluno("Henrique", "555", 100, "99996666", "henrique@gmail.com");
		sis.tornarTutor("123456789", "Calculo 2", 3);
		sis.pedirAjudaOnline("555", "Calculo 2");
		sis.pedirAjudaOnline("555", "Calculo 2");
		sis.avaliarTutor(1, 0);
		sis.avaliarTutor(2, 0);
		sis.getAlunos().get("123456789").getTutoria().setNivel();
		sis.doar("123456789", 100);
		assertEquals("Aprendiz", sis.pegarNivel("123456789"));
		assertEquals("2,78", sis.pegarNota("123456789"));
		assertEquals(37, sis.totalDinheiroTutor("wesley@gmail.com"));
		assertEquals(63,sis.totalDinheiroSistema());
	}
	
	/**
	 * Testa doar dinheiro a um tutor com nivel Tutor.
	 * @throws Exception
	 */
	@Test
	public void testDoarNivelTutor() throws Exception {
		sis.cadastrarAluno("Wesley", "123456789", 100, "33336666", "wesley@gmail.com");
		sis.tornarTutor("123456789", "Calculo 2", 3);
		sis.doar("123456789", 100);
		assertEquals("Tutor", sis.pegarNivel("123456789"));
		assertEquals(80, sis.totalDinheiroTutor("wesley@gmail.com"));
		assertEquals(20,sis.totalDinheiroSistema());
	}
	
	/**
	 * Testa doar dinheiro a um tutor com nivel TOP.
	 * @throws Exception
	 */
	@Test
	public void testDoarNivelTop() throws Exception {
		sis.cadastrarAluno("Wesley", "123456789", 100, "33336666", "wesley@gmail.com");
		sis.cadastrarAluno("Henrique", "555", 100, "99996666", "henrique@gmail.com");
		sis.tornarTutor("123456789", "Calculo 2", 3);
		sis.pedirAjudaOnline("555", "Calculo 2");
		sis.pedirAjudaOnline("555", "Calculo 2");
		sis.pedirAjudaOnline("555", "Calculo 2");
		sis.pedirAjudaOnline("555", "Calculo 2");
		sis.avaliarTutor(1, 5);
		sis.avaliarTutor(2, 5);
		sis.avaliarTutor(3, 5);
		sis.avaliarTutor(4, 5);
		sis.getAlunos().get("123456789").getTutoria().setNivel();
		sis.doar("123456789", 100);
		assertEquals("TOP", sis.pegarNivel("123456789"));
		assertEquals("4,52", sis.pegarNota("123456789"));
		assertEquals(90, sis.totalDinheiroTutor("wesley@gmail.com"));
		assertEquals(10,sis.totalDinheiroSistema());
	}
	
	/**
	 * Testa doar dinheiro com valor negativo a um tutor.
	 * @throws Exception
	 */
	@Test(expected = DadoInvalidoException.class)
	public void testDoarValorNegativo() throws Exception{
		sis.cadastrarAluno("Wesley", "123456789", 100, "33336666", "wesley@gmail.com");
		sis.cadastrarAluno("Henrique", "555", 100, "99996666", "henrique@gmail.com");
		sis.tornarTutor("123456789", "Calculo 2", 3);
		sis.doar("123456789", -50);
	}
	
	/**
	 * Testa doar dinheiro a um tutor inexistente.
	 * @throws Exception
	 */
	@Test(expected = DadoInvalidoException.class)
	public void testDoarTutorInexistente() throws Exception{
		sis.cadastrarAluno("Wesley", "123456789", 100, "33336666", "wesley@gmail.com");
		sis.cadastrarAluno("Henrique", "555", 100, "99996666", "henrique@gmail.com");
		sis.tornarTutor("123456789", "Calculo 2", 3);
		sis.doar("456789123", -50);
	}
	
	/**
	 * Testa pegar o total de dinheiro de um tutor.
	 * @throws Exception
	 */
	@Test
	public void testTotalDinheiroTutor() throws Exception {
		sis.cadastrarAluno("Wesley", "123456789", 100, "33336666", "wesley@gmail.com");
		sis.tornarTutor("123456789", "Calculo 2", 3);
		sis.doar("123456789", 100);
		assertEquals(80, sis.totalDinheiroTutor("wesley@gmail.com"));
	}
	
	/**
	 * Testa pegar o total de dinheiro de um tutor com email vazio.
	 * @throws Exception
	 */
	@Test (expected = DadoInvalidoException.class)
	public void testTotalDinheiroTutorEmailVazio() throws Exception {
		sis.cadastrarAluno("Wesley", "123456789", 100, "33336666", "wesley@gmail.com");
		sis.tornarTutor("123456789", "Calculo 2", 3);
		sis.doar("123456789", 100);
		sis.totalDinheiroTutor("");
	}
	
	/**
	 * Testa pegar o total de dinheiro com email igual a "@".
	 * @throws Exception
	 */
	@Test (expected = DadoInvalidoException.class)
	public void testTotalDinheiroTutorEmailSomenteArroba() throws Exception {
		sis.cadastrarAluno("Wesley", "123456789", 100, "33336666", "wesley@gmail.com");
		sis.tornarTutor("123456789", "Calculo 2", 3);
		sis.doar("123456789", 100);
		sis.totalDinheiroTutor("@");
	}
	
	/**
	 * Testa pegar o total de dinheiro de um tutor com email com "@" no inicio.
	 * @throws Exception
	 */
	@Test (expected = DadoInvalidoException.class)
	public void testTotalDinheiroTutorEmailArrobaNoInicio() throws Exception {
		sis.cadastrarAluno("Wesley", "123456789", 100, "33336666", "wesley@gmail.com");
		sis.tornarTutor("123456789", "Calculo 2", 3);
		sis.doar("123456789", 100);
		sis.totalDinheiroTutor("@wesley.gmail.com");
	}
	
	/**
	 * Testa pegar o total de dinheiro de um tutor com email com "@" no final.
	 * @throws Exception
	 */
	@Test (expected = DadoInvalidoException.class)
	public void testTotalDinheiroTutorEmailArrobaNoFinal() throws Exception {
		sis.cadastrarAluno("Wesley", "123456789", 100, "33336666", "wesley@gmail.com");
		sis.tornarTutor("123456789", "Calculo 2", 3);
		sis.doar("123456789", 100);
		sis.totalDinheiroTutor("wesley.gmail.com@");
	}
	
	/**
	 * Testa pegar o total de dinheiro de um tutor nao cadastrado.
	 * @throws Exception
	 */
	@Test (expected = DadoInvalidoException.class)
	public void testTotalDinheiroTutorNaoCadastrado() throws Exception {
		sis.cadastrarAluno("Wesley", "123456789", 100, "33336666", "wesley@gmail.com");
		sis.tornarTutor("123456789", "Calculo 2", 3);
		sis.doar("123456789", 100);
		sis.totalDinheiroTutor("pedro@gmail.com");
	}
	
	/**
	 * Testa pegar o total de dinheiro de um tutor com email nulo.
	 * @throws Exception
	 */
	@Test (expected = DadoNuloException.class)
	public void testTotalDinheiroTutorEmailNulo() throws Exception {
		sis.cadastrarAluno("Wesley", "123456789", 100, "33336666", "wesley@gmail.com");
		sis.tornarTutor("123456789", "Calculo 2", 3);
		sis.doar("123456789", 100);
		sis.totalDinheiroTutor(null);
	}	
}