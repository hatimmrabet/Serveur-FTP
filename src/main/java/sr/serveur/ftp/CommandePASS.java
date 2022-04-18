package sr.serveur.ftp;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * Used to send the password of the client.
 * 
 * @author Hatim  Mrabet el khomssi, Nouria Aitkheddache 
 */

public class CommandePASS extends Commande {

	public CommandePASS(PrintWriter ps, User user, String commandeStr) {
		super(ps, user, commandeStr);
	}
	
	/**
	 * Print Reponse with status 2230 User logged in successfully,
	 *  a Reponse with status 430 Invalid password if bad password
	 */
      
	public void execute() {

		try {
			//ouvrir le fichier "passwd.txt" en lecture pour récuperer le mot de passe du client
			FileReader f = new FileReader("passwd.txt");
			BufferedReader br = new BufferedReader(f);
			String ligne;

			//tester si le mot de passe est correcte
			while((ligne = br.readLine()) != null)
			{
				//ligne du compte
				if(ligne.split(":")[0].equalsIgnoreCase(user.getNom()))
				{
					//verification du mdp
					if(commandeArgs[0].equals(ligne.split(":")[1]))
					{
						user.setPassok(true);
						user.setPass(commandeArgs[0].toLowerCase());
						ps.println("230 User logged in successfully");
					}
					else
					{
						ps.println("430 Invalid password");
					}
					break;
				}
			}
			br.close();
			f.close();
		} catch (FileNotFoundException e) {
			throw new RuntimeException(e.getMessage());
		} catch (IOException e) {
			e.printStackTrace();
		}
		

	}

}
