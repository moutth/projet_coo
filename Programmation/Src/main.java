import java.io.IOException;
import java.net.InetAddress;

import controller.Controller;
import model.*;

public class main {

	public static void main(String[] args) throws IOException {
		
		Model model = new Model();
		Controller controller = new Controller(model);
		
		MsgSystem msg = new MsgSystem(model, "newConnexion");
		InetAddress ip = InetAddress.getByName("127.0.0.255");
		controller.comSystem.SendUdp(ip, controller.comSystem.SERVSYST, msg);
		System.out.println("sent : " + msg);
		while(true);
	}

}
