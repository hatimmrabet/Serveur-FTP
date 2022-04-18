package sr.serveur.ftp;

import java.io.File;

/**
 * Prepare a directory to be renamed in the distant repository.
 * 
 * @author Hatim  Mrabet el khomssi, Nouria Aitkheddache
 */
import java.io.PrintWriter;

public class CommandeRNTO extends Commande {

    public CommandeRNTO(PrintWriter ps, User user, String commandeStr) {
        super(ps, user, commandeStr);
    }
    /**
	 *Print Reponse with status 350 Ready for RNTO, 
	 *a Reponse with status 550 RNFR command failed if the fine not exists
	 */
    public void execute() {
        String filename = String.join(" ", commandeArgs);
        File file = CommandeRNFR.getFileToRename();
        File dest = new File(this.user.getCheminCourant()+"/"+filename);

        if (file.exists()) {
            if (file.renameTo(dest)) {
                CommandeRNFR.desableFileToRename();
                ps.println("250 Rename successful.");
            } else {
                ps.println("550 RNTO command failed.");
            }
        } else {
            ps.println("503 RNFR required first.");
        }
    }
}
