package lab10;

public class MarioFogo implements SituacaoMario {

	@Override
	public void pegarCogumelo(Mario mario) {
		mario.acrescentaPontuacao(1000);
		
	}

	@Override
	public void levarDano(Mario mario) throws Exception {
		mario.mudaSituacao(new MarioGrande());
		
	}

	@Override
	public void pegarFlor(Mario mario) {
		mario.acrescentaPontuacao(1000);
		
	}

}