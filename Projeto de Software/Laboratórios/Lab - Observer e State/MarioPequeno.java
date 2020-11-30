package lab10;

public class MarioPequeno implements SituacaoMario {

	@Override
	public void pegarCogumelo(Mario mario) {
		mario.mudaSituacao(new MarioGrande());

	}

	@Override
	public void levarDano(Mario mario) throws Exception {
		throw new Exception("Mario morreu.");

	}

	@Override
	public void pegarFlor(Mario mario) {
		mario.mudaSituacao(new MarioFogo());

	}

}
