package sr.serveur.ftp;

import static org.junit.Assert.*;

import java.io.IOException;
import java.io.PrintWriter;

import org.junit.Before;
import org.junit.Test;

public class CommandeCWDTest {

	   public User client1;
	   public String cmd1 = "CWD tests";
	 
	   private PrintWriter ps;
	   CommandeCWD cmdcwd;
	  
	   
	  
	 
	   @Before    
		 public void intyn() throws IOException {
		 client1 = new User(0);
		 this.client1.setNom("nouria");
		 this.client1.setCheminCourant("/home/dell/Musique/projetTp2/serveurFTP/serveur/nouria");
	     MockCommands mock=new MockCommands();
	     this.ps= mock.getWriter();
		  cmdcwd =  new CommandeCWD(ps, client1, cmd1);
		  
		
	    }

	@Test
	public void testExecutewhenfolderNotExist() {
		 cmdcwd.execute();
		assertSame(client1.getCheminCourant(),"/home/dell/Musique/projetTp2/serveurFTP/serveur/nouria");
		
	}
	

}
