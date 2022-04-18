package sr.serveur.ftp;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;

/**
 * Used to start a EPSV state and open a new data canal on passive mode.
 * 
 * @author Hatim  Mrabet el khomssi, Nouria Aitkheddache 
 */
public class CommandeEPSV extends Commande{
    private int port;
    
    public CommandeEPSV(PrintWriter ps, User u, String commandeStr)
    {
        super(ps,u,commandeStr);
        this.port = (int) Math.floor(Math.random()*(49908-1024+1)+1024);
    }

    /**
	 * print Reponse with status 227 Entering Passive Mode if success 
	 */
    public void execute(){
        try {
            System.out.println("Commande EPSV");
            this.user.activateDataServerSocket(this.port,"EPSV");
            ps.println("229 Entering Extended Passive Mode (|||"+this.port+"|)");
            System.out.println("waiting for connecting");
            Socket s = this.user.getDataServerSocket().accept();
            System.out.println("Connected to EPSV");
            this.user.activateDataSocket(s);
            return;
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


}
