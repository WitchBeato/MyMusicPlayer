package userUI.mycomponents;

import javax.swing.JLabel;

public class IntegerLabel extends JLabel{
	/**
	 * 
	 */
	private static final long serialVersionUID = -3978184245000985974L;
	public IntegerLabel(){
		
	}
	public IntegerLabel(String string) {
		// TODO Auto-generated constructor stub
		setText(string);
	}
	@Override
	public void setText(String text) {
		// TODO Auto-generated method stub
		try {
			Integer.parseInt(text);
			
		} catch (NumberFormatException e) {
			return;
		}
		String newString = text;
		if(text.length() < 2) {
			newString = "0"+text;
		}
		super.setText(newString);
	}


	
}
