package controller;

import java.util.LinkedList;
import java.util.List;
import model.MsgUser;
import model.User;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Database {

	public Controller controller;
	Connection connection;

	Database(Controller controller) throws ClassNotFoundException {
		this.controller = controller;

	}

	public void SaveMsg(User sender, User receiver, MsgUser msg) {
		String url = "jdbc:mysql://srv-bdens.insa-toulouse.fr:3306/tp_servlet_010?autoReconnect=true&useSSL=false&useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC";
		String user_base = "tp_servlet_010";
		String pass_base = "fuu6eePh";

		try {
			Connection connection = DriverManager.getConnection(url, user_base, pass_base);
			System.out.println("Connection To The Database");
			String sql_insertMessage = "INSERT INTO messagehistory (idSender, idReceiver, message) VALUES(? ,?, ?)";
			PreparedStatement statement_insertMessage = connection.prepareStatement(sql_insertMessage);
			statement_insertMessage.setInt(1, sender.getUserID());
			statement_insertMessage.setInt(2, receiver.getUserID());
			statement_insertMessage.setString(3, msg.getContent());
			int rows = statement_insertMessage.executeUpdate();
			if (rows > 0) {
				System.out.println("A row has been inserted");

			}

		} catch (SQLException e) {

			System.out.println("Database Connection Failed !\n" + e);
			e.printStackTrace();
		}

	}

	public List<MsgUser> GetHistory(User sender, User receiver) {
		String url = "jdbc:mysql://srv-bdens.insa-toulouse.fr:3306/tp_servlet_010?autoReconnect=true&useSSL=false&useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC";
		String user_base = "tp_servlet_010";
		String pass_base = "fuu6eePh";
		List<MsgUser> allMessages = new LinkedList<MsgUser>();
		try {
			Connection connection = DriverManager.getConnection(url, user_base, pass_base);
			System.out.println("Connection To The Database");
			String sql_get_messages = "SELECT * from messagehistory WHERE (idReceiver = " + sender.getUserID()
					+ " AND idSender = " + receiver.getUserID()+ " ) OR (idReceiver = "+ receiver.getUserID() +
					" AND idSender = " + sender.getUserID()+") ORDER BY sendDate ASC";
			Statement statment_selec_messages = connection.createStatement();
			ResultSet result_select = statment_selec_messages.executeQuery(sql_get_messages);
			
	        while (result_select.next()) {
	        	int id = result_select.getInt(1);
	    		String contenent = result_select.getString(3);
		        String date = result_select.getString(4); 
		        MsgUser msg = new MsgUser(id, contenent, date);
		        allMessages.add(msg);
			}
	        
		} catch (SQLException e) {

			System.out.println("Database Connection Failed !\n" + e);
			e.printStackTrace();
		}
		return allMessages;
	}

	public boolean Login(String username, String password) {
		String url = "jdbc:mysql://srv-bdens.insa-toulouse.fr:3306/tp_servlet_010?autoReconnect=true&useSSL=false&useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC";
		String user_base = "tp_servlet_010";
		String pass_base = "fuu6eePh";
		try {
			Connection connection = DriverManager.getConnection(url, user_base, pass_base);
			System.out.println("Connection To The Database");
			String sql_select = "SELECT * from userinscris";
			Statement statment_selec = connection.createStatement();
			ResultSet result_select = statment_selec.executeQuery(sql_select);
			boolean connected = false;

			while (result_select.next() && connected == false) {
				String username_inscris = result_select.getString(2);
				String username_password = result_select.getString(3);
				if (username_inscris.equals(username) && username_password.equals(password)) {
					connected = true;
					int username_ID = result_select.getInt(1);
					controller.model.getCurrentUser().setUserID(username_ID);
				}
			}

			if (connected) {
				statment_selec.close();
				connection.close();
				System.out.println("Connected to your account!\n");
				return true;
			} else {
				System.out.println("Username or Password are wrong\n");
				return false;
			}
		} catch (SQLException e) {

			System.out.println("Database Connection Failed !\n" + e);
			e.printStackTrace();
		}
		return false;
	}

	public boolean NewUser(String username, String password) {
		String url = "jdbc:mysql://srv-bdens.insa-toulouse.fr:3306/tp_servlet_010?autoReconnect=true&useSSL=false&useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC";
		String user_base = "tp_servlet_010";
		String pass_base = "fuu6eePh";
		try {
			Connection connection = DriverManager.getConnection(url, user_base, pass_base);
			System.out.println("Connection To The Database");
			String sql_select = "SELECT username from userinscris";
			Statement statment_selec = connection.createStatement();
			ResultSet result_select = statment_selec.executeQuery(sql_select);
			boolean unique = true;
			while (result_select.next() && unique == true) {
				String username_inscris = result_select.getString(1);
				if (username_inscris.equals(username)) {
					unique = false;
				}
			}
			statment_selec.close();
			if (unique) {
				String sql_insert = "INSERT INTO userinscris (username, password) VALUES(? ,?)";
				PreparedStatement statement_insert = connection.prepareStatement(sql_insert);
				statement_insert.setString(1, username);
				statement_insert.setString(2, password);
				int rows = statement_insert.executeUpdate();
				if (rows > 0) {
					System.out.println("A row has been inserted");

				}
				statement_insert.close();
				connection.close();
				return true;

			} else {
				System.out.println("Your username is aleardy used !");
				connection.close();
				return false;
			}

		} catch (SQLException e) {

			System.out.println("Database Connection Failed !\n" + e);
			e.printStackTrace();
		}
		return false;
	}

	public String GetLastPseudoUsed() {
		return "";
	}

}
