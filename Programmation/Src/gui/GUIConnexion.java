package gui;
import java.awt.EventQueue;
import java.util.ArrayList;

import controller.Controller;
import model.ChatSession;
import model.Model;
import model.Msg;
import model.User;

public class GUIConnexion {
    
    public Controller controller;

    public Model model;

    public GUIConnexion(Controller controller, Model model)
    {
    	this.controller = controller;
    	this.model= model;
    	EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Connexion frame = new Connexion(controller, model);
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});	
    }
}