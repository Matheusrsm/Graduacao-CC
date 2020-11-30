package lab10;

public class MarioGrande implements SituacaoMario {

	@Override
	public void pegarCogumelo(Mario mario) {
		mario.acrescentaPontuacao(1000);
		
	}

	@Override
	public void levarDano(Mario mario) throws Exception {
		mario.mudaSituacao(new MarioPequeno());
		
	}

	@Override
	public void pegarFlor(Mario mario) {
		mario.mudaSituacao(new MarioFogo());
		
	}
	
}