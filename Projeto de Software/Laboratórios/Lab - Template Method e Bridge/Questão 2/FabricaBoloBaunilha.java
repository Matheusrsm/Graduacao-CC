package lab9;

public class FabricaBoloBaunilha extends FabricaBolo {

	public FabricaBoloBaunilha(Cobertura cobertura) {
		super(cobertura);
	}

	@Override
	protected void fazerMassa() {
		System.out.println("Faz a massa de baunilha");
	}
	
}