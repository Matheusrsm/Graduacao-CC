package prova2.auxiliares;

import java.util.HashMap;
import prova2.entidades.Candidato;

public class Excecoes {

	public void codigoInvalido(int codigo) {
		if(codigo < 0) throw new IllegalArgumentException("Codigo Invalido!");
	}
	
	@SuppressWarnings("unused")
	public void nomeVazioOuNulo(String nome) {
		if(nome.trim().isEmpty()) throw new IllegalArgumentException("Nome Vazio!");
		if(nome == null) throw new NullPointerException("Nome Nulo!");
	}
	
	@SuppressWarnings("unused")
	public void cargoVazioOuNulo(String cargo) {
		if(cargo.trim().isEmpty()) throw new IllegalArgumentException("Cargo Vazio!");
		if(cargo == null) throw new NullPointerException("Cargo Nulo!");
	}
	
	@SuppressWarnings("unused")
	public void comentarioVazioOuNulo(String comentario) {
		if(comentario.trim().isEmpty()) throw new IllegalArgumentException("Cargo Vazio!");
		if(comentario == null) throw new NullPointerException("Cargo Nulo!");
	}
	
	public void candidatoNaoCadastrado(int codigoCandidato, HashMap<Integer, Candidato> candidatos) {
		if(candidatos.get(codigoCandidato) == null) throw new NullPointerException("Candidato nao Cadastrado!");
	}
	
	public void codigoCandidatoJaCadastrado(int codigo, HashMap<Integer, Candidato> candidatos) {
		if(candidatos.get(codigo) != null) throw new IllegalArgumentException("Codigo ja cadastrado!");
	}
}