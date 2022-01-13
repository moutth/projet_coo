
import controller.Controller;
import gui.GUIConnexion;
import model.Model;

public class MainGUI {

		public static void main(String[] args) throws ClassNotFoundException {
			Model model = new Model();
			Controller controller = new Controller(model);
			GUIConnexion Gui = new GUIConnexion(controller, model);
			
		}

	

}
