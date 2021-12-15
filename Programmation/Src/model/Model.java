package model;
import java.util.ArrayList;
import java.util.List;


public class Model {
    public User[] globalUserList;

    public ArrayList<User> connectedUserList;

    public User currentUser ;

    public List<Msg> msg ;

    public List<ChatSession> chatSession ;
    
    public Model()
    {
    	this.currentUser = new User(12564, "moutthias", "127.0.0.1");
    	this.msg = new ArrayList<Msg> ();
    	this.chatSession = new ArrayList<ChatSession> ();
    	connectedUserList = new ArrayList<User> ();
    }

    public void AddUser(User userToAdd) {
    	connectedUserList.add(userToAdd);
    }

    public void RemoveUser(String userToRemove) {
		int index = -1;
    	for (int i = 0; i < connectedUserList.size(); i++) {
			if (connectedUserList.get(i).pseudo == userToRemove) {
				index = i;
			}
    	}
    	if (index != -1) {
    		connectedUserList.remove(index);
    	}
    }

    public boolean IsAvailable(String pseudo) {
    	boolean available = true;
    	for (int i = 0; i < connectedUserList.size(); i++) {
			if (connectedUserList.get(i).pseudo == pseudo) {
				available = false;
				break;
			}
    	}
        return available;
    }

}
