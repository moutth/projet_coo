package model;

public class MsgSystem extends Msg {

	public String msgType;
	public String[] arg;

	
	
	public enum MsgType {
		NEW_CONNEXION
	}

	public MsgSystem(String msgTypein) {
		msgType = msgTypein;
	}

}
