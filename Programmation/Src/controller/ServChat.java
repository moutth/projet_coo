package controller;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

import model.Model;
import model.User;

public class ServChat extends Thread {

	private ComSystem comSystem;
	private User currentUser;
	public User distantUser;
	private Model model;
	
	private Socket receiveSocket;
	private BufferedReader buffIn;
	private PrintWriter writerOut;

	ServChat(ComSystem comSystem, Socket receiveSocket){
		this.comSystem = comSystem;
		this.model = comSystem.controller.model;
		this.currentUser = comSystem.controller.model.currentUser;
		
		this.receiveSocket = receiveSocket;
		try {
			buffIn = new BufferedReader(new InputStreamReader(receiveSocket.getInputStream()));
		} catch (IOException e) {
			e.printStackTrace();
		}
		try {
			writerOut = new PrintWriter(receiveSocket.getOutputStream(), true);
		} catch (IOException e) {
			e.printStackTrace();
		}

		start();	//runs the thread once instantiated
	}

	public void run(){
		
	}

}