package lab9;

public class FabricaBoloBaunilhaComMorango extends FabricaBolo {

	@Override
	protected void fazerCobertura() {
		System.out.println("Faz cobertura de morango");
	}
	
	@Override
	protected void fazerMassa() {
		System.out.println("Faz a massa de baunilha");
	}
	
}