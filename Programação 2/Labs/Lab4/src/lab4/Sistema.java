package lab4;

import lab4.auxiliares.Controller;

/**
 * Classe principal do Sistema
 * 
 * Lab04 - Laboratório de Programação II
 * 
 * @author Matheus Silva - 117110412
 *
 */
public class Sistema {
	
	static Controller controller = new Controller();
	
	static final String CADASTRAR_ALUNO = "C";
	static final String CONSULTAR = "E";
	static final String CADASTRAR_GRUPO = "N";
	static final String ALOCAR_OU_IMPRIMIR = "A";
	static final String REGISTRAR = "R";
	static final String IMPRIMIR = "I";
	static final String SAIR = "O";

	public static void main(String[] args) {
		do {
			switch (controller.leituraOperacao()) {
			case CADASTRAR_ALUNO:
				controller.cadastrarAluno();
				break;	
			case CONSULTAR:
				controller.consultarAluno();
				break;
			case CADASTRAR_GRUPO:
				controller.cadastrarGrupo();
				break;
			case ALOCAR_OU_IMPRIMIR:
				controller.alocarOuImprimir();
				break;
			case REGISTRAR:
				controller.registrar();
				break;
			case IMPRIMIR:
				controller.imprimir();
				break;
			default:
				if (!controller.leituraOperacao().equals("O")) {
					System.err.println("OPÇÃO INVÁLIDA!");
				}
				break;
			}
		} while (!controller.leituraOperacao().equals("O"));
	}
}