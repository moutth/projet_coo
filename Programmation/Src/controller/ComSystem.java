package controller;
import java.util.ArrayList;
import java.util.List;

import model.MsgSystem;
import model.MsgUser;

public class ComSystem {
    
    public final static int SERVSYST = 1200;
    public final static int SERVACCEPT = 1201;
    public final static int UDPMAXSIZE = 256;

    public Controller controller;
    
    ComSystem(Controller in)
    {
    	controller = in;
    }
    
    
    public ServSystem servSystem;

    
    public List<ServChat> servChat = new ArrayList<ServChat> ();

    
    //public ServAccept servAccept;

    
    
    public void SendTCP(String port, MsgUser msg) {
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
