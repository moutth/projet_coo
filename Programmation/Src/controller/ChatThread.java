package controller;

import java.io.*;
import java.net.*;

public class ChatThread extends Thread {
	Socket clientSock = null;
	private DataInputStream reader;
	
	private boolean running;
	
	public ChatThread (Socket receiveSocket) {
		this.clientSock = receiveSocket;
		try {
			reader = new DataInputStream(new BufferedInputStream(receiveSocket.getInputStream()));
		} catch (IOException e) {
			e.printStackTrace();
		}
		start();
	}
	
	public void run() {
		String line = "";
		while(running) {
			try {
				line = reader.readUTF();
			} catch (IOException e) {
				e.printStackTrace();
			}
			System.out.println(line);
		}
	}
	
	public void end() {
		running = false;
	}
	
	public boolean isRunning() {
		return running;
	}
}
