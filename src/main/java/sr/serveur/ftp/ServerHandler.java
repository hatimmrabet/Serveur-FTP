package sr.serveur.ftp;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

/**
 * @author 
 * Hatim  Mrabet el khomssi 
 * Nouria Aitkheddache, 
 */


public class ServerHandler extends Thread {

	private Socket socket;
	private BufferedReader br;
	private PrintWriter ps;

	public ServerHandler(Socket socket) throws IOException {
		this.socket = socket;
		this.br = new BufferedReader(new InputStreamReader(this.socket.getInputStream()));
		this.ps = new PrintWriter(this.socket.getOutputStream(), true);
	}


	/**
	 * create a user object by identifying it with the current thread ID
	 * call the CommandExecutor class with FTP client requests call the exector command class with FTP client requests forexecute FTP request
	 */
	@Override
	public void run() {

		try {
			// les messages que le client recoit une fois il se connecte
			ps.println("220 Welcome to Our Private FTP server");
			String commande = "";

			/**
			 * une fois le client se connecte, on cree un objet user en l'identifiant
			 * avec l'ID du thread courant et on l'ajoute dans la liste des clients
			*/
			User u = new User(Thread.currentThread().getId());
			Main.users.add(u);

			// message de suivi 
			System.out.println("=>> Nouveau Client : "+u.getIdThread()+" (nbr d'utilisateurs : " + Main.users.size() + ")");

			while ((commande = br.readLine()) != null && !commande.equalsIgnoreCase("quit")) {
				System.out.println(u.getIdThread() + " >> " + commande);
				CommandExecutor.executeCommande(ps, br, commande);
				try {
					Thread.sleep(1);
				} catch (InterruptedException e) {
					throw new RuntimeException("Interruption : "+e.getMessage());
				}
			}
			// commande de deconnection
			(new CommandeQUIT(ps, br, u, "quit")).execute();

		} catch (IOException e) {
			e.printStackTrace();
		}

	}

}
