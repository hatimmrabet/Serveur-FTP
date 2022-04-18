package sr.serveur.ftp;

import static org.junit.Assert.*;



import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.assertEquals;




public class UserTest {
	
	User client;
	
	
	@Before
	public void init(){
	client = new User(0);
	client.setNom("nouria");
	client.setPass("pass");
	}
	
	
	

	@Test
	public void testGetNomwhenNomOK() {
		
	assertEquals(this.client.getNom(),"nouria");
	
	}

	@Test
	public void testGetPassWhenPassOK() {
	
	assertEquals(this.client.getPass(),"pass");
	}
	
	@Test
	public void testGetNomwhenNomNotOK() {
		
	assertNotSame(this.client.getNom(),"toto");
	
	}
	@Test
	public void testGetNomwhenPassNotOK() {
		
	assertNotSame(this.client.getPass(),"5678");
	
	}
	

}
