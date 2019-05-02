package lab03;

import java.util.Arrays;

public class Agenda {
	
	/*
	 * Classe que contém métodos que serão utilizados no programa
	 * e a criação do array agenda de contatos do tipo Contato.
	 */
	
	private Contato[] agenda = new Contato[100];
	
	/*
	 * Guarda na posição passada na agenda, se possível, o contato criado.
	 * @return uma string que confirma se o cadastro foi realizado ou
	 * informa que a posição inserida é inválida.
	 */
	public String cadastraContato(int pos, String nome, String sobrenome, String telefone) {
		if (pos < 1 || pos > 100) {
			return ("POSIÇÃO INVÁLIDA!");
		} else {
			agenda[pos - 1] = new Contato(nome, sobrenome, telefone);
			return ("CADASTRO REALIZADO!");
		}
	}
	
	/*
	 * Verifica se algum contato existe em determinada posição.
	 * @return um boolean que indica se o contato existe ou não.
	 */
	public boolean contatoExiste(int pos) {
		if (this.agenda[pos - 1] != null) {
			return true;
		}
		return false;
	}
	
	/*
	 * @return a representação em string de um contato na posição passada.
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
	 * Método equals que compara o objeto com outro a partir de seu Array de
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