package sr.serveur.ftp;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * Handler for redirect, execute and answering FTP request from FTP client.
 * @author 
 * Hatim  Mrabet el khomssi, Nouria Aitkheddache 
 *
 */

public class CommandExecutor {


	public static void executeCommande(PrintWriter ps, BufferedReader br, String commande) throws IOException {

		// execution des taches des utilisateurs
		for (User u : Main.users) {

			if (Thread.currentThread().getId() == u.getIdThread()) {
				// la commande passé
				String cmd = commande.split(" ")[0].toLowerCase();

				// si l'utilisateur est connecté
				if (u.isUserok() && u.isPassok()) {
					switch (cmd) {
						// Revenir au repertoire parent
						case "cdup":
							(new CommandeCDUP(ps, u, commande)).execute();
							break;
						// Changer de repertoire.
						case "cwd":
							(new CommandeCWD(ps, u, commande)).execute();
							break;
						// creer repertoire
						case "mkd":
							(new CommandeMKD(ps, u, commande)).execute();
							break;
						// Telecharger un fichier
						case "retr":
							(new CommandeRETR(ps, u, commande)).execute();
							break;
						// Afficher la liste des fichiers et des dossiers du repertoire courant
						case "list":
							(new CommandeLIST(ps, u, commande)).execute();
							break;
						// Afficher le path du dossier courant
						case "pwd":
							(new CommandePWD(ps, u, commande)).execute();
							break;
						// Envoyer (uploader) un fichier
						case "stor":
							(new CommandeSTOR(ps, br, u, commande)).execute();
							break;
						// activer le mode passive extent
						case "epsv":
							(new CommandeEPSV(ps, u, commande)).execute();
							break;
						// activer le mode passif
						case "pasv":
							(new CommandePASV(ps, u, commande)).execute();
							break;
						// supprimer un fichier
						case "dele":
							(new CommandeDELE(ps, u, commande)).execute();
							break;
						// supprimer dossier
						case "rmd":
							(new CommandeRMD(ps, u, commande)).execute();
							break;
						// renomer fichier
						case "rnfr":
							(new CommandeRNFR(ps, u, commande)).execute();
							break;
						// renomer fichier
						case "rnto":
							(new CommandeRNTO(ps, u, commande)).execute();
							break;
						// commande port
						case "port":
							(new CommandePORT(ps, u, commande)).execute();
							break;
						// commande type non implementer
						case "type":
							ps.println("200 Command not implemented for that parameter");
							break;
						// commande non connues
						default:
							ps.println("504 Command not implemented for that parameter");
							break;
					}
				}
				// si l'utilisateur n'est pas connecté
				else {
					if (cmd.equals("pass") || cmd.equals("user")) {
						// Le login pour l'authentification
						if (cmd.equals("user"))
							(new CommandeUSER(ps, u, commande)).execute();
						// Le mot de passe pour l'authentification
						if (cmd.equals("pass"))
							(new CommandePASS(ps, u, commande)).execute();
					} else {
						ps.println("530 Please log in first.");
					}
				}
			}
		}
	}
}
