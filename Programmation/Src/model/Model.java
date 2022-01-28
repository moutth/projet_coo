package model;
import java.util.ArrayList;
import java.util.List;

import controller.ComSystem;
import gui.Principal;


public class Model {
	
    public ArrayList<User> connectedUserList;
    public ArrayList<String> pseudoConnectedList;

	private User currentUser ;
    
    private Principal principal; 
    
    public Model()
    {
    	connectedUserList = new ArrayList<User> ();
    	pseudoConnectedList = new ArrayList<String>();
        //Construction du model sans utilisateur pour l'instant. Il faudra instancier un utilsiateur dès sa connexion
    	currentUser = new User();
    }
    
	public void AddUser(User userToAdd) {
		if (!pseudoConnectedList.contains(userToAdd.getPseudo())) {
			connectedUserList.add(userToAdd);
			pseudoConnectedList.add(userToAdd.getPseudo());
			if (!(principal == null)) {
				principal.addConnecteduser(userToAdd);			
			}			
		}
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
    		pseudoConnectedList.remove(index);
    		removed = connectedUserList.remove(index);
    	}
    	principal.removeConnecteduser(removed);
    }
    
    public void ChangePseudo(int userID, String newPseudo) {
    	for (int i = 0; i < connectedUserList.size(); i++) {
			if (connectedUserList.get(i).getUserID() == userID) {
				principal.SomeoneChangedPseudo(userFromID(userID).getPseudo(), newPseudo);
				connectedUserList.get(i).setPseudo(newPseudo);
				pseudoConnectedList.set(i, newPseudo);
				principal.updateMessageHistory();
				break;
			}
    	}
    }

    public boolean IsAvailable(String pseudo) {
        return !pseudoConnectedList.contains(pseudo);
    }
    
    public User userFromID (int id) {
    	User userFound = null;
    	for (int i = 0; i < connectedUserList.size(); i++) {
			if (connectedUserList.get(i).getUserID() == id) {
				userFound = connectedUserList.get(i);
				break;
			}
		}
    	return userFound;
    }
    
    public int IdFromIp (String ip) {
    	int id = -1;
    	if (ip.startsWith("/")) {
    		ip = ip.substring(1);
    	}
    	for (int i = 0; i < connectedUserList.size(); i++) {
			if (connectedUserList.get(i).getIp().equals(ip)) {
				id = connectedUserList.get(i).getUserID();
				break;
			}
		}
    	return id;
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

	public ArrayList<String> getPseudoConnectedList() {
		return pseudoConnectedList;
	}
}
