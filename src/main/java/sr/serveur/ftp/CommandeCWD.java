package sr.serveur.ftp;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Path;
import java.nio.file.Paths;

/**
 * Set the given repository in the command as the current repository. User can
 * not go further than their user root repository.
 * 
 * @author Hatim  Mrabet el khomssi, Nouria Aitkheddache 
 */

public class CommandeCWD extends Commande {

	public CommandeCWD(PrintWriter ps, User user, String commandeStr) {
		super(ps, user, commandeStr);
	}
	
	
	/**
	 * print Reponse with status 250 Directory successfully changed,
	 *550 Failed to change directory if the file not exists and not a directory
	 */
 public void execute() {
		String filename = String.join(" ",commandeArgs);
		Path arg = Paths.get(filename);
		System.out.println(arg);
		//System.out.println(arg.isAbsolute());

		File file = new File(arg.toFile().getAbsolutePath());
		//check if is path
		if(!arg.isAbsolute())
		{
			
			file = new File(this.user.getCheminCourant()+"/"+arg);
			System.out.println(file);
		
		
			
		}
		if(file.exists() && file.isDirectory())
		{
			try {
				
				File racine = new File("./serveur/"+this.user.getNom()+"/");
				
				if(racine.getCanonicalFile().compareTo(file.getCanonicalFile()) > 0)
				{
					
					this.user.setCheminCourant(racine.getCanonicalPath());
				}
				else	
				{
					
					this.user.setCheminCourant(file.getCanonicalPath());
				}

			} catch (IOException e) {
				e.printStackTrace();
			}	
			
			ps.println("250 Directory successfully changed.");
		}
		else
		{
			ps.println("550 Failed to change directory.");
		}
		

	}

}
