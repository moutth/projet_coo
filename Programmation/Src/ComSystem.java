import java.util.ArrayList;
import java.util.List;
import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;
import java.net.UnknownHostException;
import java.util.Scanner;


public class ComSystem {

	// Socket de récéption broadcast de connexion
	public ServSystem servSystem;

	public List<ServChat> servChat = new ArrayList<ServChat>();

	// Socket de récéption de message
	public ServAccept servAccept;

	public void SendTCP(String port, MsgUser msg) {
	}

	public void SendTCP(String port, MsgSystem msg) {
	}

	public void SendUdp(String broadcast, int port, MsgSystem msg) throws IOException {
		Scanner sc = new Scanner(System.in);

		// Création de la socket avec un numéro de port aléatoire
		DatagramSocket ds = new DatagramSocket();

		InetAddress ip = InetAddress.getByName(broadcast);
		byte buf[] = null;
		String inp = sc.nextLine();

		// Convertir l'input en un tableau d'octets
		buf = inp.getBytes();

		// Créer le Datagramme pour envoyer les donénes.
		DatagramPacket DpSend = new DatagramPacket(buf, buf.length, ip, port);

		// Invoquer le méthode send pour envoyer les données
		ds.send(DpSend);

		// Fermeture des socket
		sc.close();
		ds.close();

	}

	public void InitSystemServ() {
	}

	public int EstablishConnexion(String userID) {
		return -1;
	}

	public void InitAcceptServ() {
	}

}
