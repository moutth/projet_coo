package model;

public class MsgUser extends Msg {

	private String content;
	private String date;
	private int idSender;

	public MsgUser(String content) {
		this.content = content;
	}

	public MsgUser(int idSender, String content, String date) {
		this.content = content;
		this.date = date;
		this.idSender = idSender;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public int getIdSender() {
		return idSender;
	}

	public void setIdSender(int idSender) {
		this.idSender = idSender;
	}

}
