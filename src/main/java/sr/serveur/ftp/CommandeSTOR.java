package sr.serveur.ftp;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.net.Socket;

/**
 * Upload a file from the local repository onto the distant repository.
 * 
 * @author Hatim  Mrabet el khomssi, Nouria Aitkheddache
 */

public class CommandeSTOR extends Commande {

	protected BufferedReader br;

	public CommandeSTOR(PrintWriter ps, BufferedReader br, User user, String commandeStr) {
		super(ps, user, commandeStr);
		this.br = br;
	}

	/**Print a Reponse with status 226 Transfer complete if success, a Reponse with status 550 Problem while transfering file  if an
	 *         exception is thrown, a Reponse with status 425 use PASV or EPSV first if the data socket 
	 *         is not activated(psv or epsv)
	 */

	public void execute() {

		String filename = String.join(" ", commandeArgs);

		// verification si on a activé socket de data (pasv ou epsv)
		if (this.user.dataSocketIsConnected()) {
			ps.println("150 Ok to send data.");
			try {
				Socket data = this.user.getDataSocket();
				if (data.isClosed()) {
					data = this.user.getDataServerSocket().accept();
				}
				//inputstream
				InputStream in = data.getInputStream();

				//création d'un nouveau fichier
				File file = new File(this.user.getCheminCourant() + "/" + filename);
				FileOutputStream out = new FileOutputStream(file);

				//lecture des octets à copier depuis la socket en les écrivant dans le fichier créé
				byte[] buffer = new byte[1024];
                int nbBytes = 0;
                while ((nbBytes = in.read(buffer)) != -1) {
                    out.write(buffer, 0, nbBytes);
                }
				ps.println("226 Transfer complete.");
				out.close();
				
				this.user.desactivateDataSocket();

			} catch (IOException e) {
				ps.println("550 Problem while transfering file : "+e.getMessage());
			}

		} else {
			ps.println("425 use PASV or EPSV first.");
		}
	}

}
