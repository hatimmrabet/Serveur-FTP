package sr.serveur.ftp;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
/**
 * QUIT to close the socket of the server for the client.
 * @author Hatim  Mrabet el khomssi, Nouria Aitkheddache
 */
public class CommandeQUIT extends Commande {

    BufferedReader br;

    public CommandeQUIT(PrintWriter ps, BufferedReader br, User user, String commandeStr) {
        super(ps, user, commandeStr);
        this.br = br;
    }
    /**
	 
	 * print a Reponse with status 21 Goodbye,and remove the user in the liste of client
	 */

    public void execute() throws IOException {
        System.out.println("==>> deconnection : " + this.user.getIdThread());
        this.user.userDeconnection();
        Main.users.remove(this.user);
        ps.println("221 Goodbye");
        br.close();
        ps.close();
    }
}