package model;
import java.util.ArrayList;
import java.util.List;

import gui.Principal;


public class Model {
    public User[] globalUserList;

    public ArrayList<User> connectedUserList;

	private User currentUser ;

    public List<Msg> msg ;

    public List<ChatSession> chatSession ;
    
    private Principal principal; 
    
    //Construction du model sans utilisteur pour l'instant. Il faut instancier un utilsiateur dès sa connexion 
    public Model()
    {
    	msg = new ArrayList<Msg> ();
    	chatSession = new ArrayList<ChatSession> ();
    	connectedUserList = new ArrayList<User> ();
    	currentUser = new User();
    }
    
    public void AddUser(User userToAdd) {
    	connectedUserList.add(userToAdd);
    	principal.addConnecteduser(userToAdd);
    }

    public void RemoveUser(int userID) {
		int index = -1;
		User removed = null;
    	for (int i = 0; i < connectedUserList.size(); i++) {
			if (connectedUserList.get(i).getUserID() == userID) {
				index = i;
			}
    	}
    	if (index != -1) {
    		
    		removed = connectedUserList.remove(index);
    	}
    	principal.removeConnecteduser(removed);
    }
    
    public void ChangePseudo(int userID, String newPseudo) {
    	for (int i = 0; i < connectedUserList.size(); i++) {
			if (connectedUserList.get(i).getUserID() == userID) {
				connectedUserList.get(i).setPseudo(newPseudo);
				break;
			}
    	}
    }

    public boolean IsAvailable(String pseudo) {
    	boolean available = true;
    	for (int i = 0; i < connectedUserList.size(); i++) {
			if (connectedUserList.get(i).getPseudo().equalsIgnoreCase(pseudo)) {
				available = false;
				break;
			}
    	}
        return available;
    }

    
    // Getters & Setters
    
	public User getCurrentUser() {
		return currentUser;
	}

	public void setCurrentUser(User currentUser) {
		this.currentUser = currentUser;
	}

	public Principal getPrincipal() {
		return principal;
	}

	public void setPrincipal(Principal principal) {
		this.principal = principal;
	}

    public ArrayList<User> getConnectedUserList() {
		return connectedUserList;
	}
	
}
