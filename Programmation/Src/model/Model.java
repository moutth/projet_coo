package model;
import java.util.ArrayList;
import java.util.List;


public class Model {
    public User[] globalUserList;

    public User[] connectedUserList;

    public User currentUser ;

    public List<Msg> msg ;

    public List<ChatSession> chatSession ;

    public List<User> user;
    
    public Model()
    {
    	this.currentUser = new User(12564, "moutthias", "10.1.5.71");
    	this.msg = new ArrayList<Msg> ();
    	this.chatSession = new ArrayList<ChatSession> ();
    	user = new ArrayList<User> ();
    }

    public void AddUser(User userToAdd) {
    }

    public void RemoveUser(String userToRemove) {
    }

    public boolean IsAvailable(String pseudo) {
        return false;
    }

}
