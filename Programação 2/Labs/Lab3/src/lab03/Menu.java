package lab03;

public class Menu {
	
	/*
	 * Classe de intera��o do usu�rio com o programa.
	 */
	
	static Inputs input = new Inputs();
	static Agenda agenda = new Agenda();
	
	final static String CADASTRAR = "C";
	final static String LISTAR = "L";
	final static String EXIBIR = "E";
	
	public static void main(String[] args) {

		String operacao;
		
		/*
		 * Enquanto o usu�rio n�o digitar a op��o "S", o programa
		 * executar� os comandos abaixo, escolhendo o que executar
		 * de acordo com a op��o passada pelo usu�rio.
		 */
		
		do {
			switch (operacao = input.leOperacao()) {
			
			case CADASTRAR:
				int posicao = input.leInteiro("\nPosi��o: ");
				String nome = input.leString("Nome: ");
				String sobrenome = input.leString("Sobrenome: ");
				String telefone = input.leString("Telefone: ");
				try {
					System.out.println(agenda.cadastraContato(posicao, nome, sobrenome, telefone));
				} catch (Exception e) {
					System.out.println(e.getMessage());
				}
				break;

			case LISTAR:
				System.out.println(agenda.listaContatos());
				break;

			case EXIBIR:
				int pos = input.leInteiro("Contato> ");
				if (pos >= 1 || pos <= 100) {
					if (agenda.contatoExiste(pos)) {
						System.out.println(agenda.exibeContato(pos));
					} else {
						System.out.println("CONTATO N�O EXISTE!");
					}
				} else {
					System.out.println("POSI��O INV�LIDA!");
				}
				break;

			default:
				if (!operacao.equals("S")) {
					System.out.println("OP��O INV�LIDA!");
				}
				break;
			}
		} while (!operacao.equals("S"));
	}
}