package sr.serveur.ftp;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.Socket;

/**
 * Used to download data.
 * 
 * @author Hatim  Mrabet el khomssi, Nouria Aitkheddache 
 */
public class CommandeRETR extends Commande {

	public CommandeRETR(PrintWriter ps, User user, String commandeStr) {
		super(ps, user, commandeStr);
	}
	/**
	 * print Reponse with 226 Transfer complete when success 
	 */
	public void execute() {

		String filename = String.join(" ", commandeArgs);

		if (this.user.dataSocketIsConnected()) {
			ps.println("150 Ok to send data.");
			try {
				Socket data = this.user.getDataSocket();
				if (data.isClosed()) {
					data = this.user.getDataServerSocket().accept();
				}
				// ouvrir le fichier à copier en lecture
				FileInputStream in = new FileInputStream(user.getCheminCourant() + "/" + filename);
				// data writer
				OutputStream dataps = data.getOutputStream();

				//lecture des octets à copier depuis la socket en les écrivant dans le fichier créé
				byte[] buffer = new byte[1024];
                while (in.read(buffer) != -1) {
                    dataps.write(buffer);
                }
				ps.println("226 Transfer complete.");
				in.close();
				this.user.desactivateDataSocket();
			} catch (FileNotFoundException e) {
				ps.println("404 Problem while transfering file : " + e.getMessage());
			} catch (IOException e) {
				ps.println("550 Problem while transfering file : " + e.getMessage());
			}

		} else {
			ps.println("425 use PASV or EPSV first.");
		}
	}
}
