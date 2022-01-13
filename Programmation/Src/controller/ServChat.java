package controller;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

import model.*;

public class ServChat extends Thread {
	
	private ComSystem comSystem;
	private User currentUser;
	public User distantUser;
	private Model model;
	
	private Socket receiveSocket;
	private BufferedReader reader;
	private PrintWriter writer;
	
	private String toSend;
	public boolean running = true;
	
	ServChat(ComSystem comSystem, Socket receiveSocket){
		this.comSystem = comSystem;
		this.model = comSystem.controller.model;
		this.currentUser = comSystem.controller.model.getCurrentUser();
		
		this.receiveSocket = receiveSocket;
		
		try {
			reader = new BufferedReader(new InputStreamReader(receiveSocket.getInputStream()));
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		try {
			writer = new PrintWriter(receiveSocket.getOutputStream(), true);
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		while(running) {
			try {
				System.out.println(reader.readLine());
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	
	ServChat(ComSystem comSystem, User distant, Socket receiveSocket){
		this.comSystem = comSystem;
		this.model = comSystem.controller.model;
		this.currentUser = comSystem.controller.model.getCurrentUser();
		this.distantUser = distant;
		
		this.receiveSocket = receiveSocket;
		
		try {
			reader = new BufferedReader(new InputStreamReader(receiveSocket.getInputStream()));
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		try {
			writer = new PrintWriter(receiveSocket.getOutputStream(), true);
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		while(running) {
			try {
				System.out.println(reader.readLine());
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	
	public void run() {
		writer.print(toSend);
	}
	
	public void send(String toSend) {
		start();
	}
	
	public void end() {
		running = false;
	}
}