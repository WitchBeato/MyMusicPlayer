package userUI.subFrames;
import userUI.information.MessageData;
import javax.swing.JPanel;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import javax.swing.JLabel;
import javax.swing.SwingConstants;

import userUI.information.Mycolors;

import java.awt.Font;
import javax.swing.SpringLayout;

public class PlayerError extends JPanel {

	private static final long serialVersionUID = 1L;
	public static Font universal = new Font("Yu Gothic UI", Font.PLAIN, 25);
	private JLabel lbl_Entry, lbl_Text;
	private JPanel pnl_North;
	/**
	 * Create the panel.
	 */
	public PlayerError(MessageData data) {
		init();
		readMessage(data);
	}
	public PlayerError() {
		init();
	}
	private void init() {

		this.setBounds(0, 0, 667, 459);
		setLayout(new BorderLayout(0, 0));
		
		pnl_North = new JPanel();
		add(pnl_North, BorderLayout.NORTH);
		pnl_North.setPreferredSize(new Dimension(0,100));
		SpringLayout sl_pnl_North = new SpringLayout();
		pnl_North.setLayout(sl_pnl_North);
		
		lbl_Entry = new JLabel("There are no file on directory");
		sl_pnl_North.putConstraint(SpringLayout.NORTH, lbl_Entry, 0, SpringLayout.NORTH, pnl_North);
		sl_pnl_North.putConstraint(SpringLayout.WEST, lbl_Entry, 75, SpringLayout.WEST, pnl_North);
		sl_pnl_North.putConstraint(SpringLayout.SOUTH, lbl_Entry, 100, SpringLayout.NORTH, pnl_North);
		sl_pnl_North.putConstraint(SpringLayout.EAST, lbl_Entry, 571, SpringLayout.WEST, pnl_North);
		lbl_Entry.setFont(new Font("Yu Gothic UI", Font.PLAIN, 25));
		lbl_Entry.setHorizontalAlignment(SwingConstants.CENTER);
		pnl_North.add(lbl_Entry);
		lbl_Entry.setForeground(Mycolors.notifyRed);
		
		lbl_Text = new JLabel("blah blah blah error message");
		lbl_Text.setVerticalAlignment(SwingConstants.TOP);
		lbl_Text.setFont(new Font("Tahoma", Font.PLAIN, 18));
		add(lbl_Text, BorderLayout.CENTER);
	}
	public void readMessage(MessageData data) {
		lbl_Entry.setText(data.getTitle());
		lbl_Text.setText(data.getText());
	}
	@Override
	public void setBackground(Color bg) {
		// TODO Auto-generated method stub
		super.setBackground(bg);
		if(lbl_Text == null) return;
		lbl_Text.setBackground(bg);
		lbl_Entry.setBackground(bg);
		pnl_North.setBackground(bg);
	}

}
