package lab10;

public class Mario {
	
	public SituacaoMario situacao;
	public long pontuacao;
	
	public Mario() {
		this.situacao = new MarioPequeno();
		this.pontuacao = 0;
	}
	
	public void pegarCogumelo() {
		situacao.pegarCogumelo(this);
		
	}
	
	public void levarDano() throws Exception {
		situacao.levarDano(this);
	
	}
	
	public void pegarFlor() {
		situacao.pegarFlor(this);
	
	}
	
	public void mudaSituacao(SituacaoMario situacao) {
		this.situacao = situacao;
	}
	
	public void acrescentaPontuacao(long pontuacao) {
		this.pontuacao += pontuacao;
	}

}