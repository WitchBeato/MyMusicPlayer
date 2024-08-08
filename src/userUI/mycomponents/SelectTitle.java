package userUI.mycomponents;

import javax.swing.JPanel;
import javax.swing.JPopupMenu;

import java.awt.BorderLayout;
import java.awt.Dimension;
import javax.swing.JLabel;


import backend.MusicPlayer;
import backend.Photoeditor;
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
import java.awt.Font;
import java.awt.Image;

import javax.swing.SwingConstants;
import javax.swing.SwingUtilities;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JCheckBox;

public class SelectTitle extends JPanel {

	public static Dimension SELECTSIZE = new Dimension(127,123);
	private static final long serialVersionUID = 1L;
	private SelectTitle me = this;
	private Musicinfo title;
	private Boolean isDirectory;
	private Mybutton btn_Play, btn_Property, btn_Delete;
	private JLabel lbl_Title, lbl_Photo;
	private ListMusicPanel parent;
	private JLayeredPane pnl_Delete;
	private Boolean selectmode = false;
	private JCheckBox chb_Select;
	public SelectTitle(Musicinfo info, Boolean isDirectory, ListMusicPanel parent) {
		this.isDirectory = isDirectory;
		this.parent = parent;
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
				if(e.getClickCount() == 2 && SwingUtilities.isLeftMouseButton(e)) {
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
		lbl_Photo.setBounds(30, 0, 76,95);
		lpnl_Photo.add(lbl_Photo, JLayeredPane.DEFAULT_LAYER);
		
		pnl_Delete = new JLayeredPane();
		pnl_Delete.setBounds(0, 0, 20, 20);
		lpnl_Photo.add(pnl_Delete);
		pnl_Delete.setLayout(null);
		
		btn_Delete = new Mybutton();
		btn_Delete.setBounds(0, 0, 20, 20);
		pnl_Delete.add(btn_Delete);
		btn_Delete.setIcon(Photoeditor.photoScaleImage(Imagedtr.cancel,
				btn_Delete.getSize().width,
				btn_Delete.getSize().height));
		
		chb_Select = new JCheckBox("New check box");
		chb_Select.setBounds(0, 0, 20, 20);
		pnl_Delete.add(chb_Select,JLayeredPane.DRAG_LAYER);
		chb_Select.setVisible(false);
		btn_Delete.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				mouseEnterGlob();
			}
			@Override
			public void mouseExited(MouseEvent e) {
				// TODO Auto-generated method stub
				mouseLeaveGlob();
			}
			@Override
			public void mouseClicked(MouseEvent e) {
				// TODO Auto-generated method stub
				super.mouseClicked(e);
				removeItem();
			}
		});
		btn_Delete.setVisible(false);
		
		btn_Property = new Mybutton();
		btn_Property.setBounds(107, 0, 20, 20);
		lpnl_Photo.add(btn_Property,JLayeredPane.POPUP_LAYER);
		
		
		JPanel pnl_Title = new JPanel();
		add(pnl_Title,BorderLayout.CENTER);
		pnl_Title.setLayout(new BorderLayout(0, 0));
		lbl_Title = new JLabel("text") {
			@Override
			public void setText(String text) {
				// TODO Auto-generated method stub
				if(text.length()>17) {
					text = text.substring(0,17)+"...";
				}
				super.setText(text);
			}
		};
		lbl_Title.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if(e.getClickCount() == 2 && SwingUtilities.isLeftMouseButton(e)) {
					playMusic(title.getDirectory());
				}
				ClickedRight(e);
			}
		});
		lbl_Title.setOpaque(false);
		lbl_Title.setFont(new Font("Tahoma", Font.BOLD, 12));
		pnl_Title.add(lbl_Title, BorderLayout.CENTER);
		btn_Property.setIcon(Photoeditor.photoScaleImage(Imagedtr.threeDot,
				btn_Property.getSize().width,
				btn_Property.getSize().height));
		lbl_Photo.setIcon(Photoeditor.photoScaleImage(Imagedtr.musicIcon,
				lbl_Photo.getSize().width/2,
				lbl_Photo.getSize().height));
		
		btn_Play = new Mybutton();
		btn_Play.setBounds(107, 75, 20, 20);
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
				if(SwingUtilities.isLeftMouseButton(e))
				playMusic(title.getDirectory());
			}
			@Override
			public void mouseExited(MouseEvent e) {
				// TODO Auto-generated method stub
				mouseLeaveGlob();
			}
		});
		btn_Property.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				mouseEnterGlob();
			}
			@Override
			public void mouseExited(MouseEvent e) {
				// TODO Auto-generated method stub
				mouseLeaveGlob();
			}
			@Override
			public void mouseClicked(MouseEvent e) {
				// TODO Auto-generated method stub
				openFeature();
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
	public void setSelectMode(Boolean newSelectmode) {
		selectmode = newSelectmode;
		if(selectmode) {
			chb_Select.setVisible(true);
			btn_Delete.setVisible(false);
			pnl_Delete.setSize(chb_Select.getPreferredSize());
		}
		else {
			chb_Select.setVisible(false);
			btn_Delete.setVisible(true);
			pnl_Delete.setSize(btn_Delete.getPreferredSize());
		}
	}
    public void debugMessage() {
    	if(Settings.DEBUG_MODE) System.out.println(title.getName());
    }
    public Boolean getSelectMode() {
    	return selectmode;
    }
	private void mouseLeaveGlob() {
		setButtonsVisible(false);
	}
	private void mouseEnterGlob() {
		setButtonsVisible(true);
	}
	private void playMusic(String directory) {
		MusicPlayer player = parent.getMainFrame().getPlayer();
		player.play(title,0);
		parent.getMainFrame().setFullTime(MusicPlayer.giveLenght(directory));
		addLastListened();
		setBtnVisibility();
		if(Settings.DEBUG_MODE) MusicPlayer.fileRead(directory);
	}
	public void setBtnVisibility() {
		MainFrame frame = getMainFrame();
		int id = frame.getPlayer().getID();
		Boolean isNextExist = frame.isSongExist(id+1);
		Boolean isPrevExist = frame.isSongExist(id-1);
		frame.setPrevEnabled(isPrevExist);
		frame.setNextEnabled(isNextExist);
	}
	private MainFrame getMainFrame() {
		return parent.getFrame();
	}
	private void ClickedRight(MouseEvent e) {
	if(!SwingUtilities.isRightMouseButton(e)) return;
	JPopupMenu menu = new JPopupMenu();
	JMenuItem detail = MenuItemlist.detail;
	menu.add(detail);
	detail.addActionListener(ae -> openFeature());
	if(!isDirectory) {
		JMenuItem remove = new JMenuItem("Remove");
		remove.addActionListener(ae -> removeItem());
		menu.add(remove);
	}

	menu.show(e.getComponent(), e.getX(), e.getY());
   }
    private void openFeature() {
		AddMusic addmusic = parent.getMainFrame().getAddpanel();
		if(addmusic != null) {
			addmusic.dispose();

		}
		addmusic = new AddMusic(AddMusic.UPDATEMODE);
		if(isDirectory) addmusic.thisDirectoryfile();
		parent.getMainFrame().setAddpanel(addmusic);
		addmusic.setInfo(title);
		addmusic.setFocusable(true);
		addmusic.setVisible(true);
	}
    private void addLastListened() {
    	Playlist lastlistened = parent.getMainFrame().getSettings().getLastListened();
    	lastlistened.addtoList(title);
    }

    private void removeItem() {
    	Playlist playlist = parent.getPlaylist();
    	playlist.removeList(title);
    	parent.sortItems();
    	parent.repaint();
    }
}
