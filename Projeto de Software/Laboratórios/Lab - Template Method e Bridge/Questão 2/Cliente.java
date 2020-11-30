package lab9;

public class Cliente {
	
	public static void main(String[] args) {
		
		FabricaBoloBaunilha fBoloBaunilhaComMorango = new FabricaBoloBaunilha(new Morango());
		FabricaBoloChocolate fBoloChocolateComCaramelo = new FabricaBoloChocolate(new Caramelo());
		fBoloBaunilhaComMorango.prepararBolo();
		fBoloChocolateComCaramelo.prepararBolo();
		
		FabricaBoloBaunilha fBoloBaunilhaComCaramelo = new FabricaBoloBaunilha(new Caramelo());
		FabricaBoloChocolate fBoloChocolateComMorango = new FabricaBoloChocolate(new Morango());
		fBoloBaunilhaComCaramelo.prepararBolo();
		fBoloChocolateComMorango.prepararBolo();
		
	}
	
}