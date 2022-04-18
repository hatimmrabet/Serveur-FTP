package sr.serveur.ftp;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;

/**
 * Used to start a PASV state and open a new data canal on passive mode.
 * 
 * @author Hatim  Mrabet el khomssi, Nouria Aitkheddache 
 */

public class CommandePASV extends Commande{
    private int port;
    private int port1, port2;
    
    public CommandePASV(PrintWriter ps, User u, String commandeStr)
    {
        super(ps,u,commandeStr);
        this.port = (int) Math.floor(Math.random() *(49908-1024+1)+1024);
        this.port1 = this.port / 256;
        this.port2 = this.port % 256;
    }
    
   

	/**
	 * print Reponse with status 227 Entering Passive Mode if success 
	 * and a message containing the address of the new data canal
	 */
 public void execute(){
        try {
            System.out.println("Commande PASV");
            this.user.activateDataServerSocket(this.port,"PASV");
            ps.println("227 Entering Passive Mode (127,0,0,1,"+this.port1+","+this.port2+")");
            System.out.println("waiting for connecting");
            Socket s = this.user.getDataServerSocket().accept();
            System.out.println("Connected to passive Mode");
            //activation du socket data d'utilisateur
            this.user.activateDataSocket(s);
            return;
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
