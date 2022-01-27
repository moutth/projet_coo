package model;



public class User {
    
    private String pseudo;

    private String ip;
    
    private int userID;
    
    public User(){
    	//valeur par défaut
    	pseudo = "!";
    }
    
    public User(int userIDin, String pseudoin, String ipin){
    	pseudo = pseudoin;
    	ip = ipin;
    	// InetAdress.toString() returns an address beginning with / which isn't convenient
    	if (ip.startsWith("/")) {
    		ip = ip.substring(1);
    	}
    	userID = userIDin;
    }

    
    
    // Getters & Setters
    
	public String getPseudo() {
		return pseudo;
	}

	public void setPseudo(String pseudo) {
		this.pseudo = pseudo;
	}

	public String getIp() {
		return ip;
	}

	public void setIp(String ip) {
		this.ip = ip;
	}

	public int getUserID() {
		return userID;
	}

	public void setUserID(int userID) {
		this.userID = userID;
	}
    
    

}
