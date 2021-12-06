package model;



public class MsgSystem extends Msg {
    
    public String msgType;
	
	public MsgSystem (String msgTypein){
		msgType = msgTypein;
	}
	
	public enum MsgType {
		NEW_CONNEXION
	}

    
    public String[] arg;

}
