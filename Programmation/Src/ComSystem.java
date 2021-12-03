import java.util.ArrayList;
import java.util.List;
import com.modeliosoft.modelio.javadesigner.annotations.objid;


public class ComSystem {
    
    public Controller ;

    
    public ServSystem servSystem;

    
    public List<ServChat> servChat = new ArrayList<ServChat> ();

    
    public ServAccept servAccept;

    
    public void SendTCP(String port, MsgUser msg) {
    }

    
    public void SendTCP(String port, MsgSystem msg) {
    }

    
    public void InitSystemServ() {
    }

    
    public int EstablishConnexion(String userID) {
        return -1;
    }

    
    public void InitAcceptServ() {
    }

}
