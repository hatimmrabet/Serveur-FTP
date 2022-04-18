package sr.serveur.ftp;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * @author 
 * Hatim  Mrabet el khomssi 
 * Nouria Aitkheddache, 
 */
public class User {

	private long idThread;
	private String nom;
	private String pass;
	private boolean userok;
	private boolean passok;
	private String cheminCourant;
	private Socket dataSocket;
	private boolean isDataConnected;
	private ServerSocket dataServerSocket;
	private String dataType;

	User(long idThread){
		this.idThread = idThread;
		this.userok = false;
		this.passok = false;
		this.cheminCourant = ".";
		this.dataSocket = null;
		this.dataServerSocket = null;
		this.isDataConnected = false;
	}

	/**
	 * @return the current thread ID ofUSER
	 */
	public long getIdThread() {
		return idThread;
	}

	/**
	 * @param idThread the thread ID 
	 */
	public void setIdThread(long idThread) {
		this.idThread = idThread;
	}

	/**
	 * @return the name of USER 
	 */
	public String getNom() {
		return nom;
	}

	/**
	 * @param nom the name 
	 */
	public void setNom(String nom) {
		this.nom = nom;
	}

	/**
	 * @return the pass password of USER
	 */
	public String getPass() {
		return pass;
	}

	/**
	 * @param pass the password 
	 */
	public void setPass(String pass) {
		this.pass = pass;
	}

	/**
	 * @return return true when the client name exists and false otherwise
	 */
	public boolean isUserok() {
		return userok;
	}

	/**
	 * @param userok is true when the client name exists and false otherwise
	 */
	public void setUserok(boolean userok) {
		this.userok = userok;
	}

	/**
	 * @return return true if the client password is correct and false otherwise
	 */
	public boolean isPassok() {
		return passok;
	}

	/**
	 * @param passok is true if the client password is correct and false otherwise
	 */
	public void setPassok(boolean passok) {
		this.passok = passok;
	}

	/**
	 * return a current path of user
	 * @return
	 */
	public String getCheminCourant() {
		return cheminCourant;
	}

	/**
	 * add a path of user
	 * @return
	 */
	public void setCheminCourant(String cheminCourant) {
		this.cheminCourant = cheminCourant;
	}

	/**
	 * enable user data socket
	 * @param sdata socket
	 */
	public void activateDataSocket(Socket sdata)
	{
		this.dataSocket = sdata;
	}
	
	/**
	 * when PASV or EPSV stat strated then open a new data canal on passive mode.
	 * @param port number of  Harbor
	 * @param type of mode (PSVorEPSV)
	 * @throws IOException
	 */
	public void activateDataServerSocket(int port,String type) throws IOException
	{
		this.dataServerSocket = new ServerSocket(port);
		this.dataType = type;
		this.isDataConnected = true;
	}
	
	/**
	 * use for activate Active state
	 * @param type state
	 * @throws IOException
	 */
	public void activateModeActive(String type) throws IOException
	{
		this.dataType = type;
		this.isDataConnected = true;
	}

	/**
	 * 
	 * @throws IOException
	 */
	public void desactivateDataSocket() throws IOException
	{
		this.dataSocket.close();
		if(this.dataType.equalsIgnoreCase("PASV"))
		{
			this.desactivateDataServerSocket();
		}
	}

	/**
	 * disconnect from server
	 * @throws IOException
	 */
	public void desactivateDataServerSocket() throws IOException
	{
		if(!this.dataType.equalsIgnoreCase("PORT"))
		{
			this.dataServerSocket.close();
		}
		this.isDataConnected = false;
	}

	/**
	 * return true when  PASV state strated
	 * @return
	 */
	public boolean dataSocketIsConnected()
	{
		return this.isDataConnected;
	}

	public Socket getDataSocket()
	{
		return this.dataSocket;
	}

	public ServerSocket getDataServerSocket()
	{
		return this.dataServerSocket;
	}

	/**
	 * use when a user disconnect from server
	 * @throws IOException
	 */
	public void userDeconnection() throws IOException
	{
		if(isDataConnected)
		{
			this.dataSocket.close();
			this.desactivateDataServerSocket();
		}
	}
}
