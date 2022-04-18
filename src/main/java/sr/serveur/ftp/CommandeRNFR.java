package sr.serveur.ftp;

import java.io.File;
import java.io.PrintWriter;

/**
 * Prepare a file to be renamed in the distant repository.
 * 
 * @authorHatim  Mrabet el khomssi, Nouria Aitkheddache
 */

public class CommandeRNFR extends Commande {

    private static File fileToRename = null;

    public CommandeRNFR(PrintWriter ps, User user, String commandeStr) {
        super(ps, user, commandeStr);
    }

	/**
	 *Print Reponse with status 350 Ready for RNTO, 
	 *a Reponse with status 550 RNFR command failed if the fine not exists
	 */

    public void execute() {
        String filename = String.join(" ", commandeArgs);
        fileToRename = new File(this.user.getCheminCourant()+"/"+filename);

        //check if file exist
        if (fileToRename.exists()) {
        	ps.println("350 Ready for RNTO.");
        } else {
        	desableFileToRename();
            ps.println("550 RNFR command failed.");
        }
    }

    public static File getFileToRename() {
        return fileToRename;
    }

    public static void desableFileToRename() {
        fileToRename = null;
    }
}
