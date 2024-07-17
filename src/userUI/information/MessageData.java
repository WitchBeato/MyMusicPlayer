package userUI.information;

public class MessageData{
	private String title;
	private String text;
	public MessageData(String title,String text) {
		// TODO Auto-generated constructor stub
		this.title = title;
		this.text = text;
	}
	public String getTitle() {
		return title;
	}
	public String getText() {
		return ("<html>"+text+"</html>");
	}
}
