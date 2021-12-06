package controller;
import gui.GUIConnexion;
import gui.GUIPrincipale;
import model.Model;

public class Controller {
    
    public GUIConnexion guiConnexion;
    public Model model;
    public GUIPrincipale guiPrincipale;
    
    Controller(Model inModel){
    	model = inModel;
    }
    
    Controller(Model inModel, GUIConnexion inguiConnexion, GUIPrincipale inguiPrincipale){
    	model = inModel;
    	guiPrincipale = inguiPrincipale;
    	guiConnexion = inguiConnexion;
    }
    
    public Database database = new Database(this);
    
    public ComSystem comSystem = new ComSystem(this);
    
    public void ChangePseudo(String pseudo) {
    }

}
