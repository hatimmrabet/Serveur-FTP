package sr.serveur.ftp;

import java.io.File;
import java.io.PrintWriter;

/**
 * Test if the user name given is valid.
 * 
 * @author Hatim  Mrabet el khomssi, Nouria Aitkheddache 
 */


public class CommandeUSER extends Commande {

	public CommandeUSER(PrintWriter ps, User user, String commandeStr) {
		super(ps, user, commandeStr);
	}

	/**
	 * print Reponse with status 331 Please specify the password if valid username, 
	 * a Reponse with status 430 if invalid username 
	 */

	public void execute() {
		//récuperer tous les fichiers et les répertoires de répertoire courant
		File file = new File("./serveur/");
		String[] ls = file.list();

		for(int i=0;i<ls.length; i++) {
			//System.out.println(ls[i]);
			//tester si ce répertoire du client existe ou non
			if(commandeArgs[0].toLowerCase().equals(ls[i])) {

				user.setUserok(true);
				user.setNom(ls[i]);
				String pwd = new File("serveur/"+ls[i]).getAbsolutePath();
				user.setCheminCourant(pwd);
				ps.println("331 Please specify the password.");
				return;
			}
		}
		ps.println("430 Invalid username : " + commandeArgs[0] );
		
	}

}
