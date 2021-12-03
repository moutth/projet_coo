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

	public ServSystem servSystem;

	public List<ServChat> servChat = new ArrayList<ServChat>();

	public ServAccept servAccept;
	

	public void SendTCP(String port, MsgUser msg) {
	}

	public void SendTCP(String port, MsgSystem msg) {
	}

	public void SendUdp(String broadcast, int port, MsgSystem msg) throws IOException {
		 Scanner sc = new Scanner(System.in);
		  
	        // Step 1:Create the socket object for
	        // carrying the data.
	        DatagramSocket ds = new DatagramSocket();
	  
	        InetAddress ip = InetAddress.getByName(broadcast);
	        byte buf[] = null;
	  
	        // loop while user not enters "bye"
	        while (true)
	        {
	            String inp = sc.nextLine();
	  
	            // convert the String input into the byte array.
	            buf = inp.getBytes();
	  
	            // Step 2 : Create the datagramPacket for sending
	            // the data.
	            DatagramPacket DpSend =
	                  new DatagramPacket(buf, buf.length, ip, port);
	  
	            // Step 3 : invoke the send call to actually send
	            // the data.
	            ds.send(DpSend);
	  
	            // break the loop if user enters "bye"
	            if (inp.equals("bye"))
	                break;
	        }
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
