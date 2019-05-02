package lab03;

import java.util.Arrays;

public class Agenda {
	
	/*
	 * Classe que cont�m m�todos que ser�o utilizados no programa
	 * e a cria��o do array agenda de contatos do tipo Contato.
	 */
	
	private Contato[] agenda = new Contato[100];
	
	/*
	 * Guarda na posi��o passada na agenda, se poss�vel, o contato criado.
	 * @return uma string que confirma se o cadastro foi realizado ou
	 * informa que a posi��o inserida � inv�lida.
	 */
	public String cadastraContato(int pos, String nome, String sobrenome, String telefone) {
		if (pos < 1 || pos > 100) {
			return ("POSI��O INV�LIDA!");
		} else {
			agenda[pos - 1] = new Contato(nome, sobrenome, telefone);
			return ("CADASTRO REALIZADO!");
		}
	}
	
	/*
	 * Verifica se algum contato existe em determinada posi��o.
	 * @return um boolean que indica se o contato existe ou n�o.
	 */
	public boolean contatoExiste(int pos) {
		if (this.agenda[pos - 1] != null) {
			return true;
		}
		return false;
	}
	
	/*
	 * @return a representa��o em string de um contato na posi��o passada.
	 */
	public String exibeContato(int pos) {
		return "\n" + agenda[pos - 1].toString();
	}
	
	/*
	 * Lista todos os contatos existentes na agenda. 
	 * @return uma string com todos os contatos existentes.
	 */
	public String listaContatos() {
		String listacontatos = "";
		for (int i = 0; i < 100; i++) {
			if (contatoExiste(i + 1)) {
				listacontatos += "\n" + (i + 1) + " - " + agenda[i].getNomeESobrenome();
			}
		}
		return listacontatos;
	}

	/*
	 * M�todo equals que compara o objeto com outro a partir de seu Array de
	 * contatos.
	 * @return um valor booleano que indica se os objetos tem o mesmo Array de
	 *         contatos.
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Agenda outro_obj = (Agenda) obj;
		if (!Arrays.equals(agenda, outro_obj.agenda))
			return false;
		return true;
	}
}