package lab9;

public class Cliente {
	
	public static void main(String[] args) {
		
		FabricaBoloBaunilhaComMorango fBoloBaunilha = new FabricaBoloBaunilhaComMorango();
		FabricaBoloChocolateComCaramelo fBoloChocolate = new FabricaBoloChocolateComCaramelo();
		fBoloBaunilha.prepararBolo();
		fBoloChocolate.prepararBolo();
	}
	
}