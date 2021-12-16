import java.io.IOException;
import java.net.InetAddress;

import controller.ComSystem;
import controller.Controller;
import model.*;

public class main {

	public static void main(String[] args) throws IOException, InterruptedException {
		
		Model model = new Model(new User(1234, "moutthias", "127.0.0.1"));
		Controller controller = new Controller(model);
		
		/* Tests relatifs à la UserList
		InetAddress ip = InetAddress.getByName("127.0.0.255");
		
		MsgSystem msg = new MsgSystem(model, "Init");
		controller.comSystem.SendUdp(ip, ComSystem.SERVSYST, msg);
		System.out.println("sent : " + msg);
		Thread.sleep(1000);

		System.out.println("IsAvailable(potatoe) : " + model.IsAvailable("potatoe"));
		Thread.sleep(1000);
		
		msg = new MsgSystem(model, "ChangePseudo 1234 potatoe");
		controller.comSystem.SendUdp(ip, ComSystem.SERVSYST, msg);
		System.out.println("sent : " + msg);
		Thread.sleep(1000);

		System.out.println("IsAvailable(potatoe) : " + model.IsAvailable("potatoe"));
		Thread.sleep(1000);
		
		msg = new MsgSystem(model, "Deconnexion 1234");
		controller.comSystem.SendUdp(ip, ComSystem.SERVSYST, msg);
		System.out.println("sent : " + msg);
		Thread.sleep(1000);

		System.out.println("IsAvailable(potatoe) : " + model.IsAvailable("potatoe"));
		Thread.sleep(1000);
		*/
		
		while(true);
	}

}
