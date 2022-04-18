package sr.serveur.ftp;

import static org.hamcrest.CoreMatchers.not;
import static org.junit.Assert.*;
import static org.hamcrest.CoreMatchers.*;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;

import org.junit.Before;
import org.junit.Test;

public class CommandeRNFRTest {
	  
	   public User client1;
	   public String cmd1 = "RNFR fichier.js";
	   public String cmd2 = "RNFR monfichier";
	   private PrintWriter ps;
	   CommandeRNFR cmdrenf,cmdrenf1;
	  
	   
	  
	 
	   @Before    
		 public void intyn() throws IOException {
		 client1 = new User(0);
		 this.client1.setNom("nouria");
		 this.client1.setCheminCourant("/home/dell/Musique/projetTp2/serveurFTP/serveur/hatim");
	     MockCommands mock=new MockCommands();
	   
		 this.ps= mock.getWriter();
		  cmdrenf =  new CommandeRNFR(ps, client1, cmd1);
		  cmdrenf1 =  new CommandeRNFR(ps, client1, cmd2);
		
	    }

	@Test
	public void testExecutewhenfilExist() {
		 cmdrenf.execute();
		 
     assertNotSame(this.cmdrenf.getFileToRename(),null);
		
	}
	@Test
	public void testExecutewhenfilNotExist() {
		
		   
		 cmdrenf1.execute();
     assertSame(this.cmdrenf.getFileToRename(),null);
		
	}

}
