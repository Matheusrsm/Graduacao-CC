package lab9;

public class FabricaBoloChocolateComCaramelo extends FabricaBolo {
	
	@Override
	protected void fazerCobertura() {
		System.out.println("Faz cobertura de caramelo");
	}
	
	@Override
	protected void fazerMassa() {
		System.out.println("Faz a massa de chocolate");
	}
	
}