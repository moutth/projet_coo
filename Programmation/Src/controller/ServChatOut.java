package controller;

import java.io.*;
import java.net.*;

import model.*;

public class ServChatOut extends Thread {
	
	private ComSystem comSystem;
	private User currentUser;
	public User distantUser;
	private Model model;
	
	private Socket sendSocket;
	private BufferedReader reader;
	private BufferedWriter writer;
	
	private String toSend;
	private boolean running;

	private boolean sendWaiting;
	
	ServChatOut(ComSystem comSystem, Socket sendSocket){
		this.comSystem = comSystem;
		this.model = comSystem.controller.model;
		this.currentUser = comSystem.controller.model.getCurrentUser();
		sendWaiting = false;
		running = true;
		
		this.sendSocket = sendSocket;
		
		try {
			reader = new BufferedReader(new InputStreamReader(sendSocket.getInputStream()));
			writer = new BufferedWriter(new OutputStreamWriter(sendSocket.getOutputStream()));
		} catch (IOException e) {
			e.printStackTrace();
		}
		start();
	}
	
	ServChatOut(ComSystem comSystem, User distant, Socket sendSocket){
		this.comSystem = comSystem;
		this.model = comSystem.controller.model;
		this.currentUser = comSystem.controller.model.getCurrentUser();
		this.distantUser = distant;
		sendWaiting = false;
		running = true;
		
		this.sendSocket = sendSocket;
		
		try {
			reader = new BufferedReader(new InputStreamReader(sendSocket.getInputStream()));
		} catch (IOException e) {
			e.printStackTrace();
		}
		start();
	}
	
	public void run() {
		while(running) {
			if (sendWaiting) {
				try {
					writer.write(toSend);
					writer.newLine();
					writer.flush();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				sendWaiting = false;
			}
		}
	}
	
	public void send(String msg) {
		toSend = msg;
		sendWaiting = true;
	}
	
	public void end() {
		running = false;
	}
	
	public boolean isRunning() {
		return running;
	}
}