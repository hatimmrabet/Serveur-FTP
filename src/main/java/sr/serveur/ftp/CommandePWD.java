package sr.serveur.ftp;
import java.io.PrintWriter;

/**
 * Send the current repository to the client.
 * 
 * @author Hatim  Mrabet el khomssi, Nouria Aitkheddache
 */

public class CommandePWD extends Commande {

	public CommandePWD(PrintWriter ps, User user, String commandeStr) {
		super(ps, user, commandeStr);
	}

	/**
	 * Print a Reponse with status 257 when success
	 */

	public void execute() {
		
		ps.println("257 \"" + this.user.getCheminCourant() + "\" is the current directory");
		return;
	}

}
