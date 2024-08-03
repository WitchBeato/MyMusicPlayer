package userUI.mycomponents;

import javax.swing.JPanel;
import javax.swing.JPopupMenu;

import java.awt.BorderLayout;
import java.awt.Dimension;
import javax.swing.JLabel;
import javax.swing.SpringLayout;

import backend.MusicPlayer;
import backend.Photoeditor;
import backend.StringEditor;
import staticinfo.Imagedtr;
import staticinfo.MenuItemlist;
import userUI.AddMusic;
import userUI.MainFrame;
import userUI.information.Musicinfo;
import userUI.information.Playlist;
import userUI.information.Settings;

import javax.swing.JLayeredPane;
import javax.swing.JMenuItem;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import java.awt.Font;
import java.awt.Image;

import javax.swing.SwingConstants;
import javax.swing.SwingUtilities;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;

public class SelectTitle extends JPanel {

	public static Dimension SELECTSIZE = new Dimension(127,123);
	private static final long serialVersionUID = 1L;
	private SelectTitle me = this;
	private Musicinfo title;
	private Boolean isDirectory;
	private Mybutton btn_Play, btn_Property, btn_Delete;
	private JLabel lbl_Title, lbl_Photo;
	private MainFrame mainframe;
	public SelectTitle(Musicinfo info, Boolean isDirectory, MainFrame mainframe) {
		this.isDirectory = isDirectory;
		this.mainframe = mainframe;
		init();
		setTitle(info);
	}
	public SelectTitle() {
		init();
	}
	private void init() {
		setBounds(0,0,SELECTSIZE.width,SELECTSIZE.height);
		setLayout(new BorderLayout(0, 0));
		
		JPanel pnl_Photo = new JPanel();
		pnl_Photo.setPreferredSize(new Dimension(133,95));
		add(pnl_Photo, BorderLayout.NORTH);
		pnl_Photo.setLayout(new BorderLayout(0, 0));
		
		JLayeredPane lpnl_Photo = new JLayeredPane();
		pnl_Photo.add(lpnl_Photo, BorderLayout.CENTER);
		lpnl_Photo.setLayout(null);
		
		lbl_Photo = new JLabel("");
		lbl_Photo.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				ClickedRight(e);
				if(e.getClickCount() == 2) {
					playMusic(title.getDirectory());
				}
			}
			@Override
			public void mouseEntered(MouseEvent e) {
				mouseEnterGlob();
			}
			@Override
			public void mouseExited(MouseEvent e) {
				// TODO Auto-generated method stub
				super.mouseExited(e);
				mouseLeaveGlob();
			}
		});
		lbl_Photo.setHorizontalAlignment(SwingConstants.CENTER);
		lbl_Photo.setBounds(0, 0, 133,95);
		lpnl_Photo.add(lbl_Photo, JLayeredPane.DEFAULT_LAYER);
		
		btn_Delete = new Mybutton();
		btn_Delete.setBounds(0, 0, 29, 29);
		lpnl_Photo.add(btn_Delete,JLayeredPane.POPUP_LAYER);
		
		btn_Property = new Mybutton();
		btn_Property.setBounds(98, 0, 29, 29);
		lpnl_Photo.add(btn_Property,JLayeredPane.POPUP_LAYER);
		
		
		JPanel pnl_Title = new JPanel();
		add(pnl_Title,BorderLayout.CENTER);
		pnl_Title.setLayout(new BorderLayout(0, 0));
		lbl_Title = new JLabel("text");
		lbl_Title.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				ClickedRight(e);
			}
		});
		lbl_Title.setOpaque(false);
		lbl_Title.setFont(new Font("Tahoma", Font.BOLD, 12));
		pnl_Title.add(lbl_Title, BorderLayout.CENTER);
		btn_Property.setIcon(Photoeditor.photoScaleImage(Imagedtr.threeDot,
				btn_Property.getSize().width,
				btn_Property.getSize().height));
		btn_Delete.setIcon(Photoeditor.photoScaleImage(Imagedtr.cancel,
				btn_Delete.getSize().width,
				btn_Delete.getSize().height));
		lbl_Photo.setIcon(Photoeditor.photoScaleImage(Imagedtr.musicIcon,
				lbl_Photo.getSize().width/2,
				lbl_Photo.getSize().height));
		
		btn_Play = new Mybutton();
		btn_Play.setBounds(98, 66, 29, 29);
		btn_Play.setIcon(Photoeditor.photoScaleImage(Imagedtr.playbutton,
				btn_Play.getSize().width, 
				btn_Play.getSize().height));
		lpnl_Photo.add(btn_Play,JLayeredPane.POPUP_LAYER);
		btn_Play.setVisible(true);
		this.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				mouseEnterGlob();
			}
			@Override
			public void mouseExited(MouseEvent e) {
				mouseLeaveGlob();
			}
		});
		btn_Play.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				mouseEnterGlob();
			}
			@Override
			public void mouseClicked(MouseEvent e) {
				playMusic(title.getDirectory());
			}
		});
		btn_Delete.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				mouseEnterGlob();
			}
		});
		btn_Delete.setVisible(false);
		btn_Property.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				mouseEnterGlob();
			}
		});
		setButtonsVisible(false);
	}
	@Override
	public void repaint() {
		// TODO Auto-generated method stub
		super.repaint();
		if(lbl_Photo == null) return;
		Image image = title.getCover();
		if(image != null) lbl_Photo.setIcon(new ImageIcon(title.getCover()));
	}
	public Musicinfo getTitle() {
		return title;
	}
	public void setTitle(Musicinfo title) {
		this.title = title;
		lbl_Title.setText(title.getName());
	}
	public void setButtonsVisible(Boolean buttonsVisible) {
		if(isDirectory == null) return;
		if(!isDirectory) btn_Delete.setVisible(buttonsVisible);
		btn_Play.setVisible(buttonsVisible);
		btn_Property.setVisible(buttonsVisible);
	}
	private void mouseLeaveGlob() {
		setButtonsVisible(false);
	}
	private void mouseEnterGlob() {
		setButtonsVisible(true);
	}
	private void playMusic(String directory) {
		MusicPlayer player = mainframe.getPlayer();
		player.play(title,0);
		mainframe.setFullTime(MusicPlayer.giveLenght(directory));
		addLastListened();
		if(Settings.DEBUG_MODE) MusicPlayer.fileRead(directory);
	}
	private void ClickedRight(MouseEvent e) {
	if(!SwingUtilities.isRightMouseButton(e)) return;
	JMenuItem detail = MenuItemlist.detail;
	detail.addActionListener(ae -> openFeature());
	JPopupMenu menu = new JPopupMenu();
	if(!isDirectory) menu.add(detail);
	menu.show(e.getComponent(), e.getX(), e.getY());
   }
    private void openFeature() {
		AddMusic addmusic = mainframe.getAddpanel();
		if(addmusic != null) addmusic.dispose();
		mainframe.setAddpanel(new AddMusic(AddMusic.UPDATEMODE));
		addmusic = mainframe.getAddpanel();
		addmusic.setInfo(title);
		addmusic.setVisible(true);
	}
    private void addLastListened() {
    	Playlist lastlistened = mainframe.getSettings().getLastListened();
    	lastlistened.addtoList(title);
    }
}
