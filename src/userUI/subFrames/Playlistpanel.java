package userUI.subFrames;

import javax.swing.JPanel;

import userUI.MainFrame;
import userUI.information.Musicinfo;
import userUI.information.Musiclist;
import userUI.mycomponents.Mainmenu;
import userUI.mycomponents.Submenu;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import javax.swing.JLabel;
import javax.swing.SwingConstants;

import backend.Photoeditor;
import staticinfo.Imagedtr;
import staticinfo.Mycolors;

import java.awt.Font;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.concurrent.atomic.AtomicInteger;

import javax.swing.JSeparator;
import javax.swing.JButton;
import javax.swing.JScrollPane;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JSplitPane;
import javax.swing.JTabbedPane;
import java.awt.FlowLayout;
import javax.swing.ScrollPaneConstants;

public class Playlistpanel extends JPanel {

	private static final long serialVersionUID = 1L;
	private MainFrame parent = null;
	private Musiclist[] musiclist = new Musiclist[2];
	private AtomicInteger  chosedID = new AtomicInteger();
	private JPanel pnl_List;
	private JButton btn_Last;
	/**
	 * Create the panel.
	 */
	public Playlistpanel(MainFrame parent) {
		this.parent = parent;
		init();
	}
	public Playlistpanel() {
		init();
	}
	private void init() {
		setLayout(new BorderLayout(0, 0));
		initiliazeList();
		chosedID.set(-1);
		
		JPanel pnl_Logo = new JPanel();
		pnl_Logo.setPreferredSize(new Dimension(this.getWidth(),80));
		pnl_Logo.setBackground(Mycolors.openGray);
		add(pnl_Logo, BorderLayout.NORTH);
		pnl_Logo.setLayout(new BorderLayout(0, 0));
		
		JLabel lbl_Logo = new JLabel("");
		lbl_Logo.setHorizontalAlignment(SwingConstants.CENTER);
		lbl_Logo.setPreferredSize(new Dimension(50,pnl_Logo.getPreferredSize().height));
		pnl_Logo.add(lbl_Logo, BorderLayout.CENTER);
		lbl_Logo.setIcon(Photoeditor.photoScaleImage(Imagedtr.logo, lbl_Logo.getPreferredSize().height, lbl_Logo.getPreferredSize().height));
		
		JPanel panel = new JPanel();
		add(panel, BorderLayout.CENTER);
		panel.setLayout(new BorderLayout(0, 0));
		
		JSeparator spt_1 = new JSeparator();
		spt_1.setForeground(new Color(153, 0, 255));
		panel.add(spt_1, BorderLayout.NORTH);
		
		JPanel pnl_South = new JPanel();
		panel.add(pnl_South, BorderLayout.CENTER);
		pnl_South.setLayout(new BorderLayout(0, 0));
		
		JPanel pnl_Lastlisten = new JPanel();
		pnl_Lastlisten.setPreferredSize(new Dimension(133,60));
		pnl_South.add(pnl_Lastlisten, BorderLayout.NORTH);
		pnl_Lastlisten.setLayout(new BorderLayout(0, 0));
		
		JSeparator spt_1_1 = new JSeparator();
		spt_1_1.setForeground(new Color(153, 0, 255));
		pnl_Lastlisten.add(spt_1_1, BorderLayout.SOUTH);
		
		btn_Last = new JButton("Last listened");
		btn_Last.setFont(new Font("Tahoma", Font.PLAIN, 15));
		pnl_Lastlisten.add(btn_Last, BorderLayout.CENTER);
		btn_Last.setBorderPainted(false);
		btn_Last.setFocusPainted(false);
		
		JScrollPane spn_List = new JScrollPane();
		spn_List.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_NEVER);
		pnl_South.add(spn_List, BorderLayout.CENTER);
		
		pnl_List = new JPanel();
		pnl_List.setPreferredSize(new Dimension(100,1000)); 
		
		spn_List.add(pnl_List);
		spn_List.setViewportView(pnl_List);
		pnl_List.setLayout(new FlowLayout(FlowLayout.TRAILING, 0,0));
		setBackground(Mycolors.openGray);
	}
	//this method initiliaze menu list of Directories and Playlist
	private void initiliazeList() {
		musiclist[0] = new Musiclist(0, "Directories", null); 
		musiclist[0].addDirectorylist("C:\\Users\\aliko\\OneDrive\\Belgeler\\çalışılacak");
		musiclist[0].addDirectorylist("C:\\Users\\aliko\\eclipse-workspace\\myMusicPlayer\\project management\\musics");
		musiclist[1] = new Musiclist(1, "Playlist", null); 
		musiclist[1].addPlaylist("deneme");
	}
	//it add Mainitems for drop down menu
	private void addMainItems(JPanel panel) {
		Dimension sizeMain = new Dimension(130,70);
		Dimension sizeSub = new Dimension(130,50);
		for (int i = 0; i < musiclist.length; i++) {
			Mainmenu menu = new Mainmenu(musiclist[i],chosedID,this);
			menu.setPreferredSize(sizeMain); 
			menu.setBackground(Mycolors.openGray);
			panel.add(menu);
			if(i == chosedID.get()) addSubItems(panel, musiclist,sizeSub);
		}
	}
	//it add subitems for drop down menu
	private void addSubItems(JPanel panel, Musiclist[] list, Dimension size) {
		ArrayList<String> stringlist = list[chosedID.get()].getDirectorylist();
		for (int i = 0; i < stringlist.size(); i++) {
			Submenu menu = null;
			if(chosedID.get() == Musiclist.DIRECTORY) {
				 menu = new Submenu(list[chosedID.get()].getDirectory(i),null,parent);
			}
			else {
				 menu = new Submenu(null,list[chosedID.get()].getMusiclist().get(i),parent);
			}
			menu.setPreferredSize(size);
			menu.setBackground(Mycolors.openGray);
			panel.add(menu);
		}
		
	}
	@Override
	public void repaint() {
		// TODO Auto-generated method stub
		super.repaint();
		if(pnl_List == null || chosedID == null) return;
		pnl_List.removeAll();
		addMainItems(pnl_List);
		pnl_List.validate();
		
	}
	@Override
	public void setBackground(Color bg) {
		// TODO Auto-generated method stub
		super.setBackground(bg);
		if(btn_Last == null) return;
		btn_Last.setBackground(bg);
		pnl_List.setBackground(bg);
	}
	public MainFrame getParent() {
		return parent;
	}
	public Musiclist getMusiclist(int id) {
		if(musiclist == null) return null;
		return musiclist[id];
	}

}
