
/**
 * Code is taken from Computer Networking: A Top-Down Approach Featuring 
 * the Internet, second edition, copyright 1996-2002 J.F Kurose and K.W. Ross, 
 * All Rights Reserved.
 **/

package Lab1;

import java.io.*;
import java.net.*;

class TCPServer {

	private static ServerSocket welcomeSocket;

	public static void main(String argv[]) throws Exception {
		String clientSentence;
		String capitalizedSentence;

		welcomeSocket = new ServerSocket(8082);

		while (true) {

			Socket connectionSocket = welcomeSocket.accept();

			BufferedReader inFromClient = new BufferedReader(new InputStreamReader(connectionSocket.getInputStream()));

			DataOutputStream outToClient = new DataOutputStream(connectionSocket.getOutputStream());

			clientSentence = inFromClient.readLine();

			capitalizedSentence = verificaOperacao(clientSentence).toUpperCase() + '\n';

			outToClient.writeBytes(capitalizedSentence);
		}
	}

	private static String verificaOperacao(String clientSentence) {
		String[] operacao = clientSentence.split(" ");
		switch (operacao[0].toUpperCase()) {
		case "CONCATENAR":
			return operacao[1] + operacao[2];
		case "COMPARAR":
			return "" + operacao[1].equals(operacao[2]);
		case "SUBSTRING":
			return operacao[1].substring(Integer.parseInt(operacao[2]), Integer.parseInt(operacao[3]));
		case "CONTEM":
			return "" + operacao[1].contains(operacao[2]);
		case "SUBSTITUIR":
			return operacao[1].replace(operacao[2], operacao[3]);
		default:
			return clientSentence;
		}
	}
}
