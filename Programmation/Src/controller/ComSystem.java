package controller;
import java.io.IOException;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import model.MsgSystem;
import model.MsgUser;
import model.User;

public class ComSystem {
    
    public final static int SERVSYST = 1200;
    public final static int SERVACCEPT = 1201;
    public final static int UDPMAXSIZE = 256;

    public Controller controller;

    public ServSystem servSystem;
    public ServAccept servAccept;
    public List<ServChat> servChat;
    
    ComSystem(Controller in)
    {
    	controller = in;
    	servSystem = new ServSystem(this);
    	servAccept = new ServAccept(this);
    	servChat = new ArrayList<ServChat>();
    }
    
    public void SendTCP(String ip, int port, MsgUser msg) throws UnknownHostException, IOException {
    	OutputStream outputStream;
    	OutputStreamWriter outputStreamWriter;
    	
    	// etablissemnt de la connexion 
    	Socket sendSocket = new Socket(ip, port);    
    	
    	// Instantiation pour écrire des données 
    	outputStream = sendSocket.getOutputStream();
    	outputStreamWriter = new OutputStreamWriter(outputStream);
    	
    	// Envoi de la donnée 
    	outputStreamWriter.write(msg.toString()); // à modifer plus tard
    	outputStreamWriter.flush();
    	
    	sendSocket.close();
    }
    
    public void SendMsg(User dest, MsgUser msg) {
    	
    }

    
    public void SendUdp(InetAddress ip, int port, MsgSystem msg) throws IOException {
		Scanner sc = new Scanner(System.in);

		// Création de la socket avec un numéro de port aléatoire
		DatagramSocket ds = new DatagramSocket();
		
		// Créer le Datagramme pour envoyer les donénes.
		DatagramPacket DpSend = new DatagramPacket(msg.toString().getBytes(), msg.toString().length(), ip, port);

		// Invoquer le méthode send pour envoyer les données
		ds.send(DpSend);

		// Fermeture des socket
		sc.close();
		ds.close();

	}

    public int EstablishConnexion(String userID) {
        return -1;
    }

}
