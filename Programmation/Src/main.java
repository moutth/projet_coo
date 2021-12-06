import java.io.IOException;

import Controller.ComSystem;
import Controller.ServSystem;

public class main {

	public static void main(String[] args) throws IOException {
		
		ServSystem serveur = new ServSystem("test");
		ComSystem  communication = new ComSystem();
		communication.SendUdp("255.255.255.255", ComSystem.SERVSYST, null); 
		
		
		
		

	}

}
