package userUI.mycomponents;

import javax.swing.JPanel;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.SpringLayout;
import javax.swing.SwingConstants;

import userUI.information.Musicinfo;
import userUI.information.Mycolors;
import userUI.information.Playlist;

import java.awt.Font;

public class Submenu extends JPanel {

	private static final long serialVersionUID = 1L;
	private JPanel pnl_Image;
	private JLabel lbl_Text, lbl_Image;
	private Playlist list;
	private String directory;
	
	/**
	 * Create the panel.
	 */
	public Submenu(String directory, Playlist list, ImageIcon icon) {
		init();
		this.list = list;
		this.directory = directory;
		String name = (directory == null) ? list.toString() : directory;
		lbl_Image.setIcon(icon);
		lbl_Text.setText(name);
	}
	public Submenu(String text) {
		init();
		lbl_Text.setText(text);
	}
	public Submenu() {
		init();
	}
	private void init() {
		setLayout(new BorderLayout(0, 0));
		
		pnl_Image = new JPanel();
		pnl_Image.setPreferredSize(new Dimension(60,40));
		add(pnl_Image, BorderLayout.WEST);
		pnl_Image.setLayout(new BorderLayout(0, 0));
		
		lbl_Image = new JLabel();
		lbl_Image.setOpaque(true);
		lbl_Image.setHorizontalAlignment(SwingConstants.CENTER);
		pnl_Image.add(lbl_Image, BorderLayout.CENTER);
		
		lbl_Text = new JLabel();
		lbl_Text.setOpaque(true);
		lbl_Text.setFont(new Font("Yu Gothic UI Semilight", Font.PLAIN, 9));
		add(lbl_Text, BorderLayout.CENTER);
		setBackground(Mycolors.openPurple);
	}
	@Override
	public void setBackground(Color bg) {
		// TODO Auto-generated method stub
		if(lbl_Text == null || pnl_Image == null || lbl_Image == null) return;
		super.setBackground(bg);
		lbl_Text.setBackground(bg);
		pnl_Image.setBackground(bg);
		lbl_Image.setBackground(bg);
	}
}
