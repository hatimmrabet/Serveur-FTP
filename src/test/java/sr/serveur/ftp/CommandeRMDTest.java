package sr.serveur.ftp;

import static org.junit.Assert.*;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;

import org.junit.Before;
import org.junit.Test;

public class CommandeRMDTest {
	 public User client1;
	   public String cmd1 = "RMD test.txt";
	   private PrintWriter ps;
	   MockCommandeRMD cmdrmd,cmdrmd1;
	  
	   
	   @Before    
		 public void intyn() throws IOException {
		 client1 = new User(0);
		 this.client1.setNom("nouria");
		 this.client1.setCheminCourant("/home/dell/Musique/projetTp2/serveurFTP/serveur/nouria");
	     MockCommands mock=new MockCommands();
	   
		 this.ps= mock.getWriter();
		  cmdrmd =  new MockCommandeRMD(ps, client1, cmd1);
		 
		
	    }

	@Test
	public void testwehnFilIsNotdirectory() {
		
		cmdrmd.execute();
		assertFalse(cmdrmd.getdelet());
		
		 
	}

}

class MockCommandeRMD extends CommandeRMD {
 
	
	Boolean delet= false;
	public MockCommandeRMD(PrintWriter ps, User user, String commandeStr) {
		super(ps, user, commandeStr);
		
	}
	  public void execute() {
	        String filename = String.join(" ", commandeArgs);
	        File dir = new File(this.user.getCheminCourant() + "/" + filename);
	        this.deleteDirectory(dir);
	        return;
	    }

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
	            	delet= true;
	            	dir.mkdir();
	                ps.println("250 Delete operation successful.");
	            } else {
	                ps.println("550 Delete repository operation failed.");
	            }
	        }
	    }
	public Boolean getdelet() {
		return this.delet;
		}
	
}
