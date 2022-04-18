package sr.serveur.ftp;

import java.io.BufferedReader;
import java.io.PrintWriter;
import java.net.Socket;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketAddress;
import org.junit.Before;
import org.junit.Test;
public class MockCommands {

	public  MockCommands() throws IOException{
		
			 
		this.init();
		 
	}

	   private static final String SERVER_ADDRESS = "localhost";
	   public Socket socket;
	   private BufferedReader reader;
	   private PrintWriter ps;	   
		  
	public void init()throws IOException {
		 try {
		        SocketAddress socketAddress = new InetSocketAddress(InetAddress.getByName(SERVER_ADDRESS),21);  
				socket = new Socket(SERVER_ADDRESS,8080);
			    InputStream in = socket.getInputStream();
				InputStreamReader isr = new InputStreamReader(in);
				this.reader = new BufferedReader(isr);
				OutputStream out = socket.getOutputStream();
				this.ps = new PrintWriter(out, true);
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	
	public PrintWriter getWriter() {
		return this.ps;
	}
		 
				
			
	}
	


