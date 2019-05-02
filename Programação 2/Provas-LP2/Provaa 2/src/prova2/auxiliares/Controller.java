package prova2.auxiliares;

import java.util.HashMap;
import java.util.Set;
import prova2.entidades.*;
import prova2.auxiliares.Excecoes;

public class Controller {

	private HashMap<Integer, Candidato> candidatos = new HashMap<>();
	private Excecoes excecoes = new Excecoes();
	private Set<Integer> codigosCandidatos = candidatos.keySet();

	public void cadastrarCandidato(int codigo, String cargo, String nome) {
		excecoes.codigoCandidatoJaCadastrado(codigo, candidatos);
		excecoes.codigoInvalido(codigo);
		excecoes.cargoVazioOuNulo(cargo);
		excecoes.nomeVazioOuNulo(nome);
		candidatos.put(codigo, new Candidato(codigo, cargo, nome));
	}
	
	public String recuperaCandidato(int codigo) {
		excecoes.codigoInvalido(codigo);
		return candidatos.get(codigo).toString();
	}
	
	public void cadastrarComentario(int codigoCandidatoAutor, int codigoCandidatoComentado, String comentario) {
		excecoes.codigoInvalido(codigoCandidatoComentado);
		excecoes.codigoInvalido(codigoCandidatoAutor);
		excecoes.comentarioVazioOuNulo(comentario);
		excecoes.candidatoNaoCadastrado(codigoCandidatoAutor, candidatos);
		excecoes.candidatoNaoCadastrado(codigoCandidatoComentado, candidatos);
		Candidato candidatoQueFoiComentado = candidatos.get(codigoCandidatoComentado);
		Candidato candidatoQueComentou = candidatos.get(codigoCandidatoAutor);
		candidatoQueFoiComentado.getComentarios().add(new Comentario(comentario, candidatoQueComentou));
	}
	
	public String pegaComentarios(int codigoCandidatoComentado) {
		Candidato candidatoQueFoiComentado = candidatos.get(codigoCandidatoComentado);
		excecoes.codigoInvalido(codigoCandidatoComentado);
		String saida = "";
		for(Comentario comentario: candidatoQueFoiComentado.getComentarios()) {
			saida += comentario.toString() + "\n";
		}
		return saida;
	}
	
	public int contaCandidatos(String cargo) {
		excecoes.cargoVazioOuNulo(cargo);
		int qtdCandidatos = 0;
		for(Integer codigoCandidato: codigosCandidatos) {
			if(candidatos.get(codigoCandidato).getCargo().equals(cargo)) qtdCandidatos++;
		}
		return qtdCandidatos;
	}
}