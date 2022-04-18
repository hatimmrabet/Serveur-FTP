package sr.serveur.ftp;

import java.io.File;
import java.nio.file.Files;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Locale;


/**
 * Used to list the content of the distant current repository.
 * 
 * @author Hatim  Mrabet el khomssi, Nouria Aitkheddache 
 */

public class CommandeLIST extends Commande {

	public CommandeLIST(PrintWriter ps, User u, String commandeStr) {
		super(ps, u, commandeStr);
	}

	/**
	 * 
	 * Print Reponse with status 226 Directory send OK.
	 * a Reponse with status 425 use PASV or EPSV first when if the data socket is not activated(psv or epsv)
	 */

	public void execute() throws IOException {
		// récuperer tous les fichiers et les répertoires de répertoire courant
		File file = new File(user.getCheminCourant());
		// System.out.println(file.getAbsolutePath());
		String[] ls = file.list();
		File file2;
		String s;

		// verification si on a activé socket de data (pasv ou epsv)
		if (this.user.dataSocketIsConnected()) {
			ps.println("150 Here comes the directory listing");
			Socket data = this.user.getDataSocket();
			if (data.isClosed()) {
				data = this.user.getDataServerSocket().accept();
			}
			PrintWriter dataps = new PrintWriter(data.getOutputStream(), true);
			DateFormat dateFormater = new SimpleDateFormat("MMM dd YYYY", Locale.ENGLISH);

			for (int i = 0; i < ls.length; i++) {
				s = "";
				file2 = new File(user.getCheminCourant() + "/" + ls[i]);
				// permissions du fichier
				s = file2.isDirectory() ? "d" : "-";
				s += file2.canRead() ? "r" : "-";
				s += file2.canWrite() ? "w" : "-";
				s += file2.canExecute() ? "x" : "-";
				s += "------";
				// owner du fichier
				String owner = Files.getOwner(file2.toPath()).getName();
				s += "\t" + owner + "\t" + owner + "\t";
				// taille du fichier
				s += file2.length() + "\t";
				// date derniere modif
				s += dateFormater.format(file2.lastModified()) + "\t";
				// nom du fichier
				s += file2.getName();
				// toutes les donnees du fichier
				dataps.println(s);
				System.out.println(s);
			}
			this.user.desactivateDataSocket();

			ps.println("226 Directory send OK.");
		} else {
			ps.println("425 use PASV or EPSV first.");
		}

	}
}
