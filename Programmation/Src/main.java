import java.io.IOException;
import controller.Controller;
import model.*;

public class main {

	public static void main(String[] args) throws IOException {
		
		Model model = new Model();
		Controller controller = new Controller(model);
		
		MsgSystem msg = new model.MsgSystem("newConnexion");
		
		//controller.comSystem.SendUdp("127.0.0.255", controller.comSystem.SERVSYST, msg);
		while(true);
	}

}
