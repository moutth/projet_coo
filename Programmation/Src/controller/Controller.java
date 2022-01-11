package controller;

import gui.GUIConnexion;
import model.Model;

public class Controller {

	public GUIConnexion guiConnexion;
	public Model model;
	public Database database;
	public ComSystem comSystem;

	public Controller(Model inModel) throws ClassNotFoundException {
		model = inModel;
		this.database = new Database(this);
		comSystem = new ComSystem(this);
	}

	Controller(Model inModel, GUIConnexion inguiConnexion) {
		model = inModel;
		guiConnexion = inguiConnexion;
	}

}