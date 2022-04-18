package sr.serveur.ftp;

import static org.junit.Assert.*;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.net.SocketAddress;
import org.junit.Before;
import org.junit.Test;


public class CommandeUSERTest{

   public User client1;
   public String cmd = "USER nouria";
 
   private PrintWriter ps;	   
	  



    @Before    
	 public void intyn() throws IOException {
	 client1 = new User(0);
     MockCommands mock=new MockCommands();
	 this.ps= mock.getWriter();
	 CommandeUSER cmduser =  new CommandeUSER(ps, client1, cmd);
	 cmduser.execute();
    }
	

	@Test
	public void testExecutewhenUserExiste() {
    
	assertEquals(this.client1.getNom(),"nouria");
    assertTrue(this.client1.isUserok());
    assertNotSame(this.client1.getCheminCourant(),null);
  
    }
	

	@Test
	public void testExecutewhenUserNotExiste() {
    
    assertNotSame(this.client1.getNom(),"moi");
    
   
    }
	

	
	

}




