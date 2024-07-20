package userUI.mycomponents;

import javax.swing.JPanel;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.SpringLayout;
import javax.swing.SwingConstants;

import backend.StringEditor;
import directories.Imagedtr;
import userUI.MainFrame;
import userUI.information.Musicinfo;
import userUI.information.Mycolors;
import userUI.information.Playlist;
import userUI.subFrames.Musicpanel;

import java.awt.Font;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionAdapter;
import java.awt.event.MouseAdapter;

public class Submenu extends JPanel {

	private static final long serialVersionUID = 1L;
	private JPanel pnl_Image;
	private JLabel lbl_Text, lbl_Image;
	private Playlist list = null;
	private String directory = null;
	private MainFrame greatparent;
	/**
	 * Create the panel.
	 */
	public Submenu(String directory, Playlist list, MainFrame greatparent) {
		init();
		this.list = list;
		this.directory = directory;
		this.greatparent = greatparent;
		String name = null;
		ImageIcon icon = null;
		if(directory == null) {
			name = list.toString();
			icon = new ImageIcon(Imagedtr.folder);
		}
		else {
			name = directory;
			icon = new ImageIcon(Imagedtr.playlist);
		}
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
		lbl_Image.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				menuClicked();
			}
		});
		lbl_Image.setOpaque(true);
		lbl_Image.setHorizontalAlignment(SwingConstants.CENTER);
		pnl_Image.add(lbl_Image, BorderLayout.CENTER);
		
		lbl_Text = new JLabel() {
			@Override
			public void setText(String text) {
				// TODO Auto-generated method stub
				if(!text.equals("")) {
					text = text.replaceAll("\\\\", "/");
					text = StringEditor.giveStringforIterative(text, '/', 1);
				}
				super.setText(text);
			}
		};
		lbl_Text.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				menuClicked();
			}
		});
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
	private void menuClicked() {
		Musicpanel mscPanel = (directory == null) ? 
				new Musicpanel(list) : 
				new Musicpanel(directory);
			greatparent.panelChange(mscPanel);
			mscPanel.revalidate();
	}
}

