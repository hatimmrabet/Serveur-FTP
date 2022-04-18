package sr.serveur.ftp;

import static org.junit.Assert.*;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;

import org.junit.Before;
import org.junit.Test;

public class CommandeDELETest {
	
	 public User client1;
	 public String cmd1= "DELET mystere.py";
	 public String cmd2 = "DELET montext.txt";
	   
	   private PrintWriter ps;
	   MockCommandeDELET cmdmkd,cmdmkd1;
	 
	   
	 @Before    
	 public void intyn() throws IOException {
	 client1 = new User(0);
	 this.client1.setNom("nouria");
	 this.client1.setCheminCourant("/home/dell/Musique/projetTp2/serveurFTP/serveur/nouria");
   MockCommands mock=new MockCommands();
 
	 this.ps= mock.getWriter();
	 cmdmkd =  new MockCommandeDELET(ps, client1, cmd1);
	 cmdmkd1 =  new MockCommandeDELET(ps, client1, cmd2);

  }
	 
	
	
	
      @Test
	public void testWhenFilNotExist() {
		
		cmdmkd1.execute();
	    assertFalse(cmdmkd.getDelet());
		
	}
	

}






class MockCommandeDELET extends CommandeDELE{
	Boolean delet= false;

	public MockCommandeDELET(PrintWriter ps, User user, String commandeStr) {
		super(ps, user, commandeStr);
		
	}
	
	
	
	public void execute() {
		
        String filename = String.join(" ",commandeArgs);
		File file = new File(this.user.getCheminCourant()+"/"+filename);
		System.out.println(file);
        if(file.exists() && file.isFile())
        {
            if(file.delete())
            {
            	
            	delet = true;
                ps.println("250 Delete operation successful.");
              try {
            	  file.createNewFile();
					  
			} catch (IOException e) {
				
				  
				e.printStackTrace();
			}
              
              
            }else
            {
                ps.println("550 Delete operation failed.");
            }
        }
        else
        {
            ps.println("550 Delete operation failed.");
        }

	}
	
	public Boolean getDelet() {
		return this.delet;
	}
	
}
