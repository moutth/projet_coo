import java.util.ArrayList;
import java.util.List;
import com.modeliosoft.modelio.javadesigner.annotations.objid;

@objid ("bd94bbc4-5d11-45cf-8086-829ba3f87650")
public class Model {
    @objid ("7f898d2c-aabe-47eb-b4a4-74aa72e173fa")
    public User[] globalUserList;

    @objid ("ae977cca-fd11-4ef3-83a7-811021506308")
    public User[] connectedUserList;

    @objid ("1065d22a-2ad6-426f-8983-8e2bb02f7481")
    public User currentUser;

    @objid ("26c50451-99f9-4329-b18c-e4aeab9e9c95")
    public List<Msg> msg = new ArrayList<Msg> ();

    @objid ("6a64244c-c5f8-4b64-9191-63cdc2e6692e")
    public List<Chat session> chat session = new ArrayList<Chat session> ();

    @objid ("b22ee90f-d0dc-410f-a058-2ffb8e1e93d7")
    public List<User> user = new ArrayList<User> ();

    @objid ("ee97b168-096e-4881-88f7-070955138529")
    public void AddUser(User userToAdd) {
    }

    @objid ("cf994c7c-528c-4184-93e7-a7142b262759")
    public void RemoveUser(String userToRemove) {
    }

    @objid ("768593d2-c64e-4f7f-a002-e7e8974b6667")
    public boolean IsAvailable(String pseudo) {
    }

}
