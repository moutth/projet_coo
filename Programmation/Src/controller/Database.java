package controller;

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

	public static void createNewDatabase(String fileName) throws ClassNotFoundException {

		Class.forName("org.sqlite.JDBC");
		Connection connection = null;
		try {
			// create a database connection
			connection = DriverManager.getConnection("jdbc:sqlite:sample.db");

			Statement statement = connection.createStatement();
			statement.setQueryTimeout(30); // set timeout to 30 sec.

			statement.executeUpdate("DROP TABLE IF EXISTS users");
			statement.executeUpdate("CREATE TABLE users (name STRING, password STRING)");

			String names[] = { "Peter", "Pallar", "William", "Paul", "James Bond" };

			for (int i = 0; i < names.length; i++) {
				statement.executeUpdate("INSERT INTO users values(' " + names[i] + "', '" + names[i] + "')");
			}

			// statement.executeUpdate("UPDATE person SET name='Peter' WHERE id='1'");
			// statement.executeUpdate("DELETE FROM person WHERE id='1'");

			ResultSet resultSet = statement.executeQuery("SELECT * from users");
			while (resultSet.next()) {
				// iterate & read the result set
				System.out.println("name = " + resultSet.getString("name"));
				System.out.println("Password = " + resultSet.getString("password"));
			}
		}

		catch (SQLException e) {
			System.err.println(e.getMessage());
		} finally {
			try {
				if (connection != null)
					connection.close();
			} catch (SQLException e) { // Use SQLException class instead.
				System.err.println(e);
			}
		}

	}

	public void SaveMsg(User sender, User receiver,MsgUser msg) {
		String url = "jdbc:mysql://srv-bdens.insa-toulouse.fr:3306/tp_servlet_010?autoReconnect=true&useSSL=false&useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC";
		String user_base = "tp_servlet_010";
		String pass_base = "fuu6eePh";
		
		try {
			Connection connection = DriverManager.getConnection(url, user_base, pass_base);
			System.out.println("Connection To The Database");
			String sql_insertMessage= "INSERT INTO messagehistory (idSender, idReceiver, message VALUES(? ,?, ?)";
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

			if (connected)
			{
				statment_selec.close();
				connection.close();
				System.out.println("Connected to your account!\n" );
				return true;
			}
			else
			{
				System.out.println("Username or Password are wrong\n" );
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
				
			}
			else
			{
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

	public List<MsgUser> GetHistory(String pseudo) {
		return null;
	}

}
