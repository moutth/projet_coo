package controller;

import java.net.*;

public class ChatThread extends Thread {
	Socket clientSock = null;
	
	public ChatThread (Socket clientSock) {
		super();
		this.clientSock = clientSock;
	}
	
	public void run () {
		System.out.println("Handling client from port " + clientSock.getPort());
	}
}
