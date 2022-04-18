package sr.serveur.ftp;

import static org.junit.Assert.*;
import static org.hamcrest.CoreMatchers.*;


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

import org.junit.Test;

public class CommandePASSTest {

	   public User client1;
	   
	   public String cmd = "PASS souad";
	   private PrintWriter ps;
	   

	    @Before    
		 public void intyn() throws IOException {
		 client1 = new User(0);
		 this.client1.setNom("nouria");
	     MockCommands mock=new MockCommands();
		 this.ps= mock.getWriter();
		 CommandePASS cmduser =  new CommandePASS(ps, client1, cmd);
		 cmduser.execute();
	    }

	@Test
	public void testwhenGoodPass() {
	assertEquals(this.client1.getPass(),"souad");
	 assertTrue(this.client1.isPassok());
	}
	
	
	
	@Test
	public void testwhenBadPass() {
	assertThat(this.client1.getPass(),not("so"));
		
	}
}
