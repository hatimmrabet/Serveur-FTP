package sr.serveur.ftp;

import static org.junit.Assert.*;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;

import org.junit.Before;
import org.junit.Test;

public class CommandeMKDTest {
	 public User client1;
	   public String cmd1 = "MKD montext.txt";
	   public String cmd2= "mkd text.txt";
	   private PrintWriter ps;
	   MockCommandeMKD cmdmkd,cmdmkd1;
	  Boolean bool ;
	   
	 @Before    
	 public void intyn() throws IOException {
	 client1 = new User(0);
	 this.client1.setNom("nouria");
	 this.client1.setCheminCourant("/home/dell/Musique/projetTp2/serveurFTP/serveur/nouria");
     MockCommands mock=new MockCommands();
   
	 this.ps= mock.getWriter();
	 cmdmkd =  new MockCommandeMKD(ps, client1, cmd1);
	 cmdmkd1 =  new MockCommandeMKD(ps, client1, cmd2);

    }

	@Test
	public void testWhenfilNotexist() {
		cmdmkd.execute();
	    assertTrue(cmdmkd.getCreated());
	    
		
	}
	
	@Test
	public void testWhenfilexist() {
		cmdmkd1.execute();
	    assertFalse(cmdmkd.getCreated());
	    
		
	}

}




class MockCommandeMKD extends CommandeMKD{

	Boolean created= false;
	
	public MockCommandeMKD(PrintWriter ps, User user, String commandeStr) {

		
		super(ps, user, commandeStr);
		
	}
	public void execute() {
		String filename = String.join(" ",commandeArgs);
		File dir = new File(this.user.getCheminCourant()+"/"+filename);
		if (dir.exists()) {
			ps.println("553 \"" + dir.getName() + "\" File name exist already.");
		} else if (dir.mkdir()) {
			ps.println("257 \"" + dir.getAbsolutePath() + "\" created");
			created= true;
            dir.delete();
		} else {
			ps.println("500 \"" + dir + "\" can't be created");
		}
	}
	Boolean getCreated() {
		return this.created;
	}
	
}

