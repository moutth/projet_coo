package model;

import java.util.ArrayList;

public class MsgSystem extends Msg {
	
	public Model model;
	
	public ArrayList<String> arg;	// 0 is the name of the message type
	
	public MsgSystem(Model modelIn, String msgType, String optArg) {
		model = modelIn;
		
		arg = new ArrayList<String>();
		arg.add(msgType);
		switch (arg.get(0)) {
		case "Init" :
			break;
		case "InitAnswer" :
			arg.add(Integer.toString(model.getCurrentUser().getUserID())); 	// 1 is the ID of the user
			arg.add(model.getCurrentUser().getPseudo());						// 2 is the pseudo of the user
			break;
		case "Connexion" :
			arg.add(Integer.toString(model.getCurrentUser().getUserID())); 	// 1 is the ID of the user
			arg.add(model.getCurrentUser().getPseudo());						// 2 is the pseudo of the user
			break;
		case "ChangePseudo" :
			arg.add(Integer.toString(model.getCurrentUser().getUserID())); 	// 1 is the ID of the user
			arg.add(model.getCurrentUser().getPseudo());						// 2 is the pseudo of the user
			break;
		case "Deconnexion" :
			arg.add(Integer.toString(model.getCurrentUser().getUserID())); 	// 1 is the ID of the user
			break;
		}
	}
	
	public MsgSystem(Model modelIn, String msgIn) {
		model = modelIn;
		msgIn.strip();
		int size = msgIn.split(" ").length;
		
		arg = new ArrayList<String>();

		for (int i = 0; i < size; i++) {
			arg.add(msgIn.split(" ")[i]);
		}
	}
	
	public String toString() {
		String str = new String();
		for (int i = 0; i < arg.size(); i++) {
			str += " " + arg.get(i);
		}
		return str.strip();
	}
	
	public String getType() {
		return arg.get(0);
	}
	
	public String getArg(int i) {
		return arg.get(i);
	}

}
