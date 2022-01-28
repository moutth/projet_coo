package controller;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

import model.Model;
import model.User;

public class ServAccept extends Thread {

	private ComSystem comSystem;
	private User currentUser;
	private Model model;
	public boolean running = true;

	ServAccept(ComSystem in) {
		comSystem = in;
		model = comSystem.controller.model;
		currentUser = comSystem.controller.model.getCurrentUser();
		start(); // runs the thread once instantiated
	}

	public void run(){
		ServerSocket acceptSocket = null;
		try {
			acceptSocket = new ServerSocket(ComSystem.SERVACCEPT);
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		while(running) {
			try {
				Socket chatSocket = acceptSocket.accept();
				int destID = model.IdFromIp(chatSocket.getInetAddress().toString());
				comSystem.chatThreadsList.add(new ChatThread(comSystem, chatSocket, destID));
				comSystem.chatThreadsListID.add(destID);
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

}