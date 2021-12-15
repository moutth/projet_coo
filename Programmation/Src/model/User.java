package model;



public class User {
    
    public String pseudo;

    public String ip;
    
    public int userID;
    
    public User(int userIDin, String pseudoin, String ipin){
    	pseudo = pseudoin;
    	ip = ipin;
    	if (ip.startsWith("/")) {
    		ip = ip.substring(1);
    	}
    	userID = userIDin;
    }

}
