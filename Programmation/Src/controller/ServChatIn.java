package controller;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.Socket;

import model.*;

public class ServChatIn extends Thread {
	
	private ComSystem comSystem;
	private User currentUser;
	public User distantUser;
	private Model model;
	
	private Socket receiveSocket;
	private BufferedReader reader;
	private BufferedWriter writer;

	private boolean running;
	
	ServChatIn(ComSystem comSystem, Socket receiveSocket){
		this.comSystem = comSystem;
		this.model = comSystem.controller.model;
		this.currentUser = comSystem.controller.model.getCurrentUser();
		running = true;
		
		this.receiveSocket = receiveSocket;
		
		try {
			reader = new BufferedReader(new InputStreamReader(receiveSocket.getInputStream()));
			writer = new BufferedWriter(new OutputStreamWriter(receiveSocket.getOutputStream()));
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		start();
	}
	
	ServChatIn(ComSystem comSystem, User distant, Socket receiveSocket){
		this.comSystem = comSystem;
		this.model = comSystem.controller.model;
		this.currentUser = comSystem.controller.model.getCurrentUser();
		this.distantUser = distant;
		running = true;
		
		this.receiveSocket = receiveSocket;
		
		try {
			reader = new BufferedReader(new InputStreamReader(receiveSocket.getInputStream()));
			writer = new BufferedWriter(new OutputStreamWriter(receiveSocket.getOutputStream()));
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		start();
	}
	
	public void run() {
		String responseLine;
		while(running) {
			try {
				while ((responseLine = reader.readLine()) != null) {
				    System.out.println(responseLine);
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
	
	public void end() {
		running = false;
	}
	
	public boolean isRunning() {
		return running;
	}
}
