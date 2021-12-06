package model;



public class User {
    
    public String pseudo;

    public String ip;
    
    public int userID;
    
    User(int userIDin, String pseudoin, String ipin){
    	pseudo = pseudoin;
    	ip = ipin;
    	userID = userIDin;
    }

}
