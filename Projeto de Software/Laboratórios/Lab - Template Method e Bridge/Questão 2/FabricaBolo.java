package lab9;

public abstract class FabricaBolo {
	
	protected Cobertura cobertura;
	
	public FabricaBolo(Cobertura cobertura) {
		this.cobertura = cobertura;
	}
	
	public void prepararBolo() {
		fazerMassa();
		levarAoForno();
		fazerCobertura();
		decorarBolo();
	}
	
	private void levarAoForno() {
		System.out.println("Leva ao forno");
	}
	
	private void decorarBolo() {
		System.out.println("Decora bolo");
	}
	
	protected void fazerCobertura() {
		System.out.println("Faz cobertura de " + cobertura.sabor);
	}
	
	protected abstract void fazerMassa();

}