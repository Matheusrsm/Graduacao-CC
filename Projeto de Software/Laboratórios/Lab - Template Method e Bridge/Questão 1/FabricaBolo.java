package lab9;

public abstract class FabricaBolo {
	
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
	
	protected abstract void fazerCobertura();
	
	protected abstract void fazerMassa();

}