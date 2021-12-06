package Controller;
import java.io.IOException;
import java.lang.Object;
import java.net.*;

public class ServSystem extends Thread {

	private ServSystem(String name){
		super(name);
		start();	//runs the thread once instanciated
	}

	public void run(){
		DatagramSocket socket = null;
		try {
			socket = new DatagramSocket(ComSystem.SERVSYST);
		} catch (SocketException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		byte[] buffer = new byte[256];
		DatagramPacket inPacket = new DatagramPacket(buffer, buffer.length);
		try {
			socket.receive(inPacket);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}
