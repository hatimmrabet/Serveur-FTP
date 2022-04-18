package sr.serveur.ftp;

import java.io.IOException;
import java.io.PrintWriter;

public abstract class Commande {

	protected PrintWriter ps;
	protected String commandeNom = "";
	protected String [] commandeArgs ;
	protected User user;

	public Commande(PrintWriter ps, User user, String commandeStr) {

		this.ps = ps ;
		this.user = user;
		String [] args = commandeStr.split(" ");
		commandeNom = args[0];
		commandeArgs = new String[args.length-1];

		for(int i=0; i<commandeArgs.length; i++) {
			commandeArgs[i] = args[i+1];
		}

	}

	public abstract void execute() throws IOException;

}