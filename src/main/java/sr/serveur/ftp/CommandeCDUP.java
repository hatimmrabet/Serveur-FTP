package sr.serveur.ftp;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;

/**
 *  change the client's current working directory to the immediate parent directory 
 *  of the current working directory
 * 
 * @author Hatim  Mrabet el khomssi, Nouria Aitkheddache
 */

public class CommandeCDUP extends Commande {

	public CommandeCDUP(PrintWriter ps, User user, String commandeStr) {
		super(ps, user, commandeStr);
	}

	/**
	 * Print Reponse with status 250 Directory successfully changed if success.
	 */
  public void execute() {

        File file = new File(this.user.getCheminCourant());
        File parent = file.getParentFile();
        File racine = new File("./serveur/"+this.user.getNom()+"/");
        
        try {
            if(racine.getCanonicalFile().compareTo(parent.getCanonicalFile()) >= 0)
            {
                this.user.setCheminCourant(racine.getCanonicalPath());
            }
            else	//in his repository
            {
                this.user.setCheminCourant(parent.getCanonicalPath());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        
        // System.out.println(this.user.getCheminCourant());
        ps.println("250 Directory successfully changed.");
            

	}

}
