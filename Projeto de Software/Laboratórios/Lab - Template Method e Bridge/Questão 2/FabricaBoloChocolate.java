package lab9;

public class FabricaBoloChocolate extends FabricaBolo {
	
	public FabricaBoloChocolate(Cobertura cobertura) {
		super(cobertura);
	}
	
	@Override
	protected void fazerMassa() {
		System.out.println("Faz a massa de chocolate");
	}
	
}