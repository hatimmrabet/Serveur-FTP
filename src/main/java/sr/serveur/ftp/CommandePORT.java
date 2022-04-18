package sr.serveur.ftp;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;

public class CommandePORT extends Commande {

    public CommandePORT(PrintWriter ps, User u, String commandeStr) {
        super(ps, u, commandeStr);
        // this.port = (int) Math.floor(Math.random() *(49908-1024+1)+1024);
        // this.port1 = this.port / 256;
        // this.port2 = this.port % 256;
    }

    public void execute() {
        try {
            String[] address = commandeArgs[0].split(",");
            if (address.length == 6) {
                // ip de conncetion
                String ip = address[0] + "." + address[1] + "." + address[2] + "." + address[3];
                // calcul du port du conection
                int port = Integer.parseInt(address[4]) * 256 + Integer.parseInt(address[5]);
                // activer le mode active
                this.user.activateModeActive("PORT");
                // reponse de retour pour le client
                ps.println("200 PORT command successful.");
                // connection avec canal de donnees
                Socket s = new Socket(ip, port);
                System.out.println("Connected to Active Mode");
                // activation du socket data d'utilisateur
                this.user.activateDataSocket(s);
            } else {
                ps.println("550 PORT commande failed.");
            }
        } catch (IOException e) {
            ps.println("550 PORT commande failed, " + e.getMessage());
            throw new RuntimeException("Exception : " + e.getMessage());
        }
    }
}
