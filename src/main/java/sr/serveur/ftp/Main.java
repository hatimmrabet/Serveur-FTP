package sr.serveur.ftp;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;

/**
 * @author    
 * Hatim  Mrabet el khomssi 
 * Nouria Ait Kheddache 
 */
public class Main {
	// la liste des utilisateurs du serveur
	public static ArrayList<User> users = new ArrayList<User>();

	/**
	 * Launch the FTP-Server on the port define by PORT attribute.
	 * @param args,conventional parameter - not used here.
	 * @throws ExceptioncreateServerSocket
	 */
	public static void main(String[] args) throws Exception {
		System.out.println("Le Serveur FTP est lancé");
		int port = 8080;
		if(args.length>=1)
			port = Integer.parseInt(args[0]);

		ServerSocket serveurFTP = createServerSocket(port);

		while (true) {
			// attente d'un client
			Socket socket = createClientSocket(serveurFTP);
			ServerHandler th = new ServerHandler(socket);
			// lancer un thread du server handler
			th.start();
		}
		// serveurFTP.close(); 
	}

	/**
	 * @param port le numéro de port
	 * @return Crée une socket serveur
	 * @throws IOException
	 */
	public static ServerSocket createServerSocket(int port) throws IOException {
		return new ServerSocket(port);
	}

	/**
	 * Accept the connection from a FTP client.
	 * @param sFTP the server socket of the server
	 * @return  the socket of the command canal with the client
	 * @throws IOException
	 */
	public static Socket createClientSocket(ServerSocket sFTP) throws IOException {
		return sFTP.accept();
	}
}
