package controller;
import java.util.List;

import model.MsgUser;



public class Database {
    
    public Controller controller;
    
    Database(Controller incontroller){
    	controller = incontroller;
    }
    
    public List<MsgUser> GetHistory(String pseudo) {
    	return null;
    }

    
    public void SaveMsg(MsgUser msg) {
    }

    
    public boolean Login(String username, String password) {
        return false;
    }

    
    public void NewUser(String username, String password) {
    }

    
    public String GetLastPseudoUsed() {
        return "";
    }

}
