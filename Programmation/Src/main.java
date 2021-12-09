import java.io.IOException;
import java.net.InetAddress;

import controller.Controller;
import model.*;

public class main {

	public static void main(String[] args) throws IOException {
		
		Model model = new Model();
		Controller controller = new Controller(model);
		
		MsgSystem msg = new model.MsgSystem("newConnexion");
		InetAddress ip = InetAddress.getByName("10.1.5.74");
		controller.comSystem.SendUdp(ip, controller.comSystem.SERVSYST, msg);
		while(true);
	}

}
