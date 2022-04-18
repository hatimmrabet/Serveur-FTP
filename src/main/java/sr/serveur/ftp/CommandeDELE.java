package sr.serveur.ftp;
import java.io.File;
import java.io.PrintWriter;

/**
 * Used to remove a file.
 * 
 * @author Hatim  Mrabet el khomssi, Nouria Aitkheddache
 */

public class CommandeDELE extends Commande {

	public CommandeDELE(PrintWriter ps, User user, String commandeStr) {
		super(ps, user, commandeStr);
	}
	
	/**
	 * print Reponse with status 250 Delete operation successful,
	 *550 Delete operation failed. if the file not exists and not a File
	 */
	public void execute() {
        String filename = String.join(" ",commandeArgs);
		File file = new File(this.user.getCheminCourant()+"/"+filename);
        if(file.exists() && file.isFile())
        {
            if(file.delete())
            {
                ps.println("250 Delete operation successful.");
            }else
            {
                ps.println("550 Delete operation failed.");
            }
        }
        else
        {
            ps.println("550 Delete operation failed.");
        }

	}

}
