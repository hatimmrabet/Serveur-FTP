package sr.serveur.ftp;

import java.io.File;
import java.io.PrintWriter;

public class MockcommandUSER extends CommandeUSER  {

	public MockcommandUSER(PrintWriter ps, User user, String commandeStr) {
		super(ps, user, commandeStr);
		
	}
	public int called=0;  
	
	
	public void execute() {
		        
		        called++;
				File file = new File("./serveur/");
				String[] ls = file.list();
                 for(int i=0;i<ls.length; i++) {
					if(commandeArgs[0].toLowerCase().equals(ls[i])) {
						user.setUserok(true);
						user.setNom(ls[i]);
						String pwd = new File("serveur/"+ls[i]).getAbsolutePath();
						user.setCheminCourant(pwd);
						return;
					}
				}
			
				
	}

}

