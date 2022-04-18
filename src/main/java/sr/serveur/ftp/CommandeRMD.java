package sr.serveur.ftp;

import java.io.File;
import java.io.PrintWriter;

/**
 * Used to remove a Directory.
 * 
 * @author Hatim  Mrabet el khomssi, Nouria Aitkheddache
 */
public class CommandeRMD extends Commande {

    public CommandeRMD(PrintWriter ps, User user, String commandeStr) {
        super(ps, user, commandeStr);
    }

    public void execute() {
        String filename = String.join(" ", commandeArgs);
        File dir = new File(this.user.getCheminCourant() + "/" + filename);
        
        this.deleteDirectory(dir);
        return;
    }
    /**
	 * print Reponse with status 250 Delete operation successful,
	 *550 Delete operation failed. if the file not exists and not a Directory
	 */
    private void deleteDirectory(File dir) {
        if (dir.exists() && dir.isDirectory()) {
            File[] files = dir.listFiles();
            for (File f : files) {
                if (f.isFile()) // supprimer les fichier du dossier
                {
                    f.delete();
                } else if (f.isDirectory()) // supprimer le contenu des dossier du dossier parent
                {
                    deleteDirectory(f);
                } else {
                    ps.println("550 Delete repository operation failed.");
                }
            }
            if (dir.delete()) {
                ps.println("250 Delete operation successful.");
            } else {
                ps.println("550 Delete repository operation failed.");
            }
        }
    }
}
