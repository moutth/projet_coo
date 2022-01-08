package model;
import java.util.ArrayList;
import java.util.List;


public class Model {
    public User[] globalUserList;

    public ArrayList<User> connectedUserList;

    public User currentUser ;

    public List<Msg> msg ;

    public List<ChatSession> chatSession ;
    
    
    //Construction du model sans utilistaure pour l'instant. Il faut instancier un utilsiateur dés sa connexion! 
    public Model()
    {
    	msg = new ArrayList<Msg> ();
    	chatSession = new ArrayList<ChatSession> ();
    	connectedUserList = new ArrayList<User> ();
    }

    public void SetCurrentUser(User current) {
    	currentUser = current;
    }
    
    public void AddUser(User userToAdd) {
    	connectedUserList.add(userToAdd);
    }

    public void RemoveUser(int userID) {
		int index = -1;
    	for (int i = 0; i < connectedUserList.size(); i++) {
			if (connectedUserList.get(i).userID == userID) {
				index = i;
			}
    	}
    	if (index != -1) {
    		connectedUserList.remove(index);
    	}
    }
    
    public void ChangePseudo(int userID, String newPseudo) {
    	for (int i = 0; i < connectedUserList.size(); i++) {
			if (connectedUserList.get(i).userID == userID) {
				connectedUserList.get(i).pseudo = newPseudo;
				break;
			}
    	}
    }

    public boolean IsAvailable(String pseudo) {
    	boolean available = true;
    	for (int i = 0; i < connectedUserList.size(); i++) {
			if (connectedUserList.get(i).pseudo.equalsIgnoreCase(pseudo)) {
				available = false;
				break;
			}
    	}
        return available;
    }

}
