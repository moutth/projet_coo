import java.io.IOException;

import controller.ComSystem;
import controller.Controller;
import model.*;


public class main {

	public static void main(String[] args) throws IOException {
		
		
		Model model = new Model();
		Controller controller = new Controller(model);
		MsgSystem msg = new MsgSystem("newConnexion");
		controller.comSystem.SendUdp("255.255.255.255", ComSystem.SERVSYST, msg);
		
		
		while(true);

	}

}
