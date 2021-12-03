import java.util.ArrayList;
import java.util.List;


public class Model {
    public User[] globalUserList;

    public User[] connectedUserList;

    public User currentUser;

    public List<Msg> msg = new ArrayList<Msg> ();

    public List<Chat session> chat session = new ArrayList<Chat session> ();

    public List<User> user = new ArrayList<User> ();

    public void AddUser(User userToAdd) {
    }

    public void RemoveUser(String userToRemove) {
    }

    public boolean IsAvailable(String pseudo) {
    }

}
