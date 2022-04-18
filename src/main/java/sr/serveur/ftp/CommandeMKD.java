package sr.serveur.ftp;

import java.io.File;
import java.io.PrintWriter;

/**
 * Used to create a new repository in the distant current repository.
 * 
 * @author Hatim  Mrabet el khomssi, Nouria Aitkheddache
 */

public class CommandeMKD extends Commande {

	public CommandeMKD(PrintWriter ps, User user, String commandeStr) {
		super(ps, user, commandeStr);
	}
	
	/**
	 * print a Reponse with status 553, when the repository already exists,
	 *  a Reponse with status 500 if the repository wasn't created, a
	 *  a Reponse with status "257 if the repository created
	 */

	public void execute() {
		String filename = String.join(" ",commandeArgs);
		File dir = new File(this.user.getCheminCourant()+"/"+filename);
		if (dir.exists()) {
			ps.println("553 \"" + dir.getName() + "\" File name exist already.");
		} else if (dir.mkdir()) {
			ps.println("257 \"" + dir.getAbsolutePath() + "\" created");
		} else {
			ps.println("500 \"" + dir + "\" can't be created");
		}
	}
}
