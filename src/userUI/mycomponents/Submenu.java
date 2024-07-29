package userUI.mycomponents;

import javax.swing.JPanel;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JLayeredPane;
import javax.swing.JOptionPane;
import javax.swing.SpringLayout;
import javax.swing.SwingConstants;

import backend.AddtoMusiclist;
import backend.MusicPlayer;
import backend.Photoeditor;
import backend.StringEditor;
import staticinfo.Imagedtr;
import staticinfo.Mycolors;
import userUI.MainFrame;
import userUI.information.Musicinfo;
import userUI.information.Musiclist;
import userUI.information.Playlist;
import userUI.subFrames.Musicpanel;

import java.awt.Font;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionAdapter;
import java.awt.event.MouseAdapter;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class Submenu extends JPanel {

	private static final long serialVersionUID = 1L;
	private JPanel pnl_Image;
	private JLabel lbl_Text, lbl_Image;
	private Playlist list = null;
	private String directory = null;
	private MainFrame greatparent;
	private MusicPlayer player;
	private Mybutton btn_Delete;
	/**
	 * Create the panel.
	 */
	public Submenu(String directory, Playlist list, MainFrame greatparent) {
		init();
		this.list = list;
		this.directory = directory;
		this.greatparent = greatparent;
		player = greatparent.getPlayer();
		String name = null;
		ImageIcon icon = null;
		if(directory == null) {
			name = list.toString();
			icon = new ImageIcon(Imagedtr.playlist);
		}
		else {
			name = directory;
			icon = new ImageIcon(Imagedtr.folder);
		}
		lbl_Image.setIcon(icon);
		lbl_Text.setText(name);
	}
	public Submenu(String text) {
		init();
		lbl_Text.setText(text);
	}
	public Submenu() {
		setBorder(null);
		setPreferredSize(new Dimension(131, 72));
		init();
	}
	private void init() {
		setLayout(new BorderLayout(0, 0));
		
		pnl_Image = new JPanel();
		pnl_Image.setPreferredSize(new Dimension(40,40));
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
				if(!text.equals("") && directory != null) {
					text = text.replaceAll("\\\\", "/");
					text = StringEditor.giveStringforIterative(text, '/', 1);
				}
				super.setText(text);
			}
		};
		lbl_Text.setBounds(0, 0, 91, 72);
		lbl_Text.setHorizontalAlignment(SwingConstants.CENTER);
		lbl_Text.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				menuClicked();
			}
		});
		lbl_Text.setOpaque(true);
		lbl_Text.setFont(new Font("Yu Gothic UI Semilight", Font.PLAIN, 9));
		setBackground(Mycolors.openGray);
		
		JLayeredPane pnl_Center = new JLayeredPane();
		add(pnl_Center, BorderLayout.CENTER);
		pnl_Center.setLayout(null);
		pnl_Center.add(lbl_Text,JLayeredPane.DEFAULT_LAYER);
		
		btn_Delete = new Mybutton();
		btn_Delete.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				removeClicked();
			}
		});
		btn_Delete.setBounds(66, 0, 25, 27);
		btn_Delete.setIcon(Photoeditor.photoScaleImage(Imagedtr.remove
				, btn_Delete.getPreferredSize().width
				, btn_Delete.getPreferredSize().height+15));
		pnl_Center.add(btn_Delete,JLayeredPane.DRAG_LAYER);
		
		Mybutton btn_Add = new Mybutton();
		btn_Add.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		btn_Add.setBounds(40, 0, 25, 27);
		pnl_Center.add(btn_Add, JLayeredPane.DRAG_LAYER);
		btn_Add.setIcon(Photoeditor.photoScaleImage(Imagedtr.add
				, btn_Add.getPreferredSize().width
				, btn_Add.getPreferredSize().height+15));
	}
	@Override
	public void setBackground(Color bg) {
		// TODO Auto-generated method stub
		super.setBackground(bg);
		if(lbl_Text == null || pnl_Image == null || lbl_Image == null || btn_Delete == null) return;
		lbl_Text.setBackground(bg);
		pnl_Image.setBackground(bg);
		lbl_Image.setBackground(bg);
		btn_Delete.setBackground(bg);
	}
	private void menuClicked() {
		Musicpanel mscPanel = (directory == null) ? 
				new Musicpanel(list,greatparent) : 
				new Musicpanel(directory,greatparent);
			greatparent.panelChange(mscPanel);
			mscPanel.revalidate();
	}
	private void removeClicked() {
		if(greatparent == null) return;
		if(!removeisSure()) return;
		int ID = getID();
		Musiclist musiclist = greatparent.getMusiclist(ID);
		AddtoMusiclist.removefromMusiclist(musiclist, list);
		removeInside();
	}
	private int getID() {
		if(directory != null) return Musiclist.DIRECTORY;
		else return Musiclist.PLAYLIST;
	}
	private void removeInside() {
	     greatparent.playlistRepaint();
	}
	private Boolean removeisSure() {
		int choice = JOptionPane.showConfirmDialog(null, "Do you want to Delete it?", "Confirm", JOptionPane.YES_NO_OPTION);
		switch (choice) {
		case JOptionPane.OK_OPTION: {
			return true;
		}
		default:
			return false;
		}
	}
}

