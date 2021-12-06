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

public class ComSystem {
    
    public final static int SERVSYST = 1200;
    public final static int SERVACCEPT = 1201;
    public final static int UDPMAXSIZE = 256;

    public Controller controller;
    
    ComSystem(Controller in)
    {
    	controller = in;
    }
    
    public ServSystem servSystem = new ServSystem(this);
    
    public List<ServChat> servChat = new ArrayList<ServChat> ();
    
    //public ServAccept servAccept;
    
    public void SendTCP(String ip, int port, MsgUser msg) throws UnknownHostException, IOException {
    	OutputStream outputStream;
    	OutputStreamWriter outputStreamWriter;
    	
    	// etablissemnt de la connexion 
    	Socket socket=new Socket(ip , port);    
    	
    	// Instantiation pour  écrire des données 
    	outputStream=socket.getOutputStream();
    	outputStreamWriter=new OutputStreamWriter(outputStream);
    	
    	// Envoie de la donnée 
    	outputStreamWriter.write(msg.toString()); // à modifer plus tard
    	outputStreamWriter.flush();
    
    	
    	
    	socket.close();
    	
    }

    
    public void SendUdp(String broadcast, int port, MsgSystem msg) throws IOException {
		Scanner sc = new Scanner(System.in);

		// Création de la socket avec un numéro de port aléatoire
		DatagramSocket ds = new DatagramSocket();

		InetAddress ip = InetAddress.getByName(broadcast);
		
		// Créer le Datagramme pour envoyer les donénes.
		DatagramPacket DpSend = new DatagramPacket(msg.msgType.getBytes(), msg.msgType.length(), ip, port);

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
