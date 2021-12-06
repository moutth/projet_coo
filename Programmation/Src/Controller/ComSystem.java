package Controller;
import java.util.ArrayList;
import java.util.List;

import Model.MsgSystem;
import Model.MsgUser;

public class ComSystem {
    
    public final static int SERVSYST = 1200;
    public final static int SERVACCEPT = 1201;
    public final static int UDPMAXSIZE = 256;

    //public Controller ;

    
    public ServSystem servSystem;

    
    public List<ServChat> servChat = new ArrayList<ServChat> ();

    
    //public ServAccept servAccept;

    
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
