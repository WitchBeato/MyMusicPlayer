package userUI.mycomponents;

import javax.swing.JPanel;
import java.awt.BorderLayout;
import java.awt.Dimension;

import javax.swing.JButton;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.SwingConstants;

public class Mainmenu extends JPanel {

	private static final long serialVersionUID = 1L;
	private JLabel lbl_Text;
	private int index; 
	/**
	 * Create the panel.
	 */
	public Mainmenu() {
		init();

	}
	private void init() {
		setLayout(new BorderLayout(0, 0));
		
		JButton btn_Open = new JButton("");
		btn_Open.setPreferredSize(new Dimension(30,65));
		add(btn_Open, BorderLayout.EAST);
		
		lbl_Text = new JLabel("Files");
		lbl_Text.setHorizontalAlignment(SwingConstants.CENTER);
		lbl_Text.setFont(new Font("Yu Gothic UI", Font.PLAIN, 18));
		add(lbl_Text, BorderLayout.CENTER);
	}
	public int getIndex() {
		return index;
	}
	public void setIndex(int index) {
		this.index = index;
	}

}
