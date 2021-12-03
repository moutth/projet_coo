import java.util.List;
import com.modeliosoft.modelio.javadesigner.annotations.objid;

@objid ("8c2ac3e1-b03d-4328-bfdf-5fffdf22db02")
public class Database {
    @objid ("0b1fff9e-c930-41ba-a2ca-94a395626dd6")
    public Controller ;

    @objid ("1852be51-b1cf-4333-b207-8c65d7515306")
    public List<MsgUser> GetHistory(String pseudo) {
    }

    @objid ("b8b18973-7771-4f69-ae74-416134a16201")
    public void SaveMsg(MsgUser msg) {
    }

    @objid ("7cee4702-3852-45f1-9de3-286795c0a424")
    public boolean Login(String username, String password) {
    }

    @objid ("d65191b1-d78a-48f4-9b9d-4f16674824d4")
    public void NewUser(String username, String password) {
    }

    @objid ("3bcaffd6-b5ca-4157-95ee-9fca28de05fa")
    public String GetLastPseudoUsed() {
    }

}
