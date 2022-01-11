package gui;
import java.awt.EventQueue;

import controller.Controller;
import model.Model;

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