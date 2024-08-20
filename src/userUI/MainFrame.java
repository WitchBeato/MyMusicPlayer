package userUI;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPopupMenu;
import javax.swing.border.EmptyBorder;

import backend.MusicPlayer;
import staticinfo.Mycolors;
import staticinfo.PlayerMessages;
import userUI.information.Musicinfo;
import userUI.information.Musiclist;
import userUI.information.Playlist;
import userUI.information.Settings;
import userUI.subFrames.Musicpanel;
import userUI.subFrames.PlayerError;
import userUI.subFrames.Playerpanel;
import userUI.subFrames.Playlistpanel;
import userUI.subFrames.SortMusics;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Dimension;
import javax.swing.JScrollPane;

import java.awt.event.ComponentEvent;
import java.awt.event.ComponentListener;
import java.awt.event.MouseEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class MainFrame extends JFrame {

	private static final long serialVersionUID = 1L;
	public static final int MINMODE = 0, STDMODE = 1;
	private int mode;
	private MainFrame me = this;
	private JPanel contentPane, pnl_Songs;
	private Playlistpanel pnl_Selection;
	private Settings setting = new Settings();
	private MusicPlayer player;
	private Playerpanel pnl_Player;
	private AddMusic addpanel;
	private Dimension minSize = new Dimension(430,400);
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MainFrame frame = new MainFrame();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public MainFrame() {
		addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				// TODO Auto-generated method stub
				super.windowClosing(e);
				setting.saveAll();
			}
			
		});
		this.setMinimumSize(minSize);
		setPlayer(new MusicPlayer());
		setTitle("MymusicPlayer");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 800, 510);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(new BorderLayout(0, 0));

		pnl_Player = new Playerpanel(this,player);
		pnl_Player.setPreferredSize(new Dimension(0,51));
		contentPane.add(pnl_Player, BorderLayout.SOUTH);
		pnl_Player.setBackground(Mycolors.openGray);
		
		JPanel pnl_Center = new JPanel();
		contentPane.add(pnl_Center, BorderLayout.CENTER);
		pnl_Center.setLayout(new BorderLayout(0, 0));
		

		
		JScrollPane spnl_Songs = new JScrollPane();
		pnl_Center.add(spnl_Songs, BorderLayout.CENTER);
		
		pnl_Selection = new Playlistpanel(this,setting);
		pnl_Selection.setPreferredSize(new Dimension(133,0));
		pnl_Selection.setBackground(Mycolors.openGray);
		pnl_Center.add(pnl_Selection, BorderLayout.WEST);
		pnl_Center.add(pnl_Selection, BorderLayout.WEST);
		
		pnl_Songs =  new JPanel();
		pnl_Center.add(pnl_Songs, BorderLayout.CENTER);
		pnl_Songs.setLayout(new BorderLayout(0, 0));
		PlayerError playererror = new PlayerError(PlayerMessages.noChoice,this);
		panelChange(playererror);
		this.addComponentListener(new ComponentListener() {
			
			@Override
			public void componentShown(ComponentEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void componentResized(ComponentEvent e) {
				// TODO Auto-generated method stub
				int widht = me.getWidth();
				int height = me.getHeight();
				if(pnl_Selection == null) return;
				if(widht<500 && mode != MINMODE) {
					setMinimumMode();
					mode = MINMODE;
				}
				else if(widht>500 && mode != STDMODE){
					setSubFramesVisibility(true);
					mode = STDMODE;
				}
			}
			
			@Override
			public void componentMoved(ComponentEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void componentHidden(ComponentEvent e) {
				// TODO Auto-generated method stub
			}
		});
	}
	public void panelChange(JPanel panel) {
		pnl_Songs.removeAll();
		pnl_Songs.add(panel,BorderLayout.CENTER);
		this.revalidate();
	}

	public MusicPlayer getPlayer() {
		return player;
	}
	public Settings getSettings() {
		return setting;
	}

	public void setPlayer(MusicPlayer player) {
		this.player = player;
	}
	public void setFullTime(int second) {
		pnl_Player.setFulltime(second);
	}
	public void playlistRepaint() {
		pnl_Selection.repaint();
	}
	public Musiclist getMusiclist(int id) {
		if(pnl_Selection == null) return null;
		return pnl_Selection.getMusiclist(id);
	}

	public AddMusic getAddpanel() {
		return this.addpanel;
	}
	public void setAddpanel(AddMusic addmusic) {
		addpanel = addmusic;
	}
	public void musiclistRepaint() {
		pnl_Songs.repaint();
	}
	private void setMinimumMode() {
		setSubFramesVisibility(false);
	}
	private void setSubFramesVisibility(Boolean visible) {
		pnl_Selection.setVisible(visible);
	}
	public int getMinMode() {
		return mode;
	}
	public Musicpanel getMusicList() {
		for (Component componenet : pnl_Songs.getComponents()) {
			if(componenet instanceof Musicpanel) {
				return (Musicpanel) componenet;
			}
		}
		return null;
	}
	public void setSortPanelVisible(Boolean visibility) {
		Musicpanel panel = null;
		try {
			panel = getMusicList();
		} catch (NullPointerException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return;
		}
		panel.setSortVisible(visibility);
	}
	public Musicinfo setNextSong(int skipped) {
		if(player == null) return null;
		int id = player.getID();
		Musicpanel musicpanel = getMusicList();
		if(musicpanel == null) return null;
		int newID = id+skipped;
		if(!isSongExist(newID)) return null;
		Musicinfo info = musicpanel.getInfo(newID);
		player.play(info, 0);
		return info;
	}
	public Boolean isSongExist(int id) {
		Musicpanel musicpanel = getMusicList();
		if(id < 0 || id == musicpanel.getListSize()) return false;
		if(musicpanel.getInfo(id) != null) return true;
		else return false;
	}
	public void setPrevEnabled(Boolean isActive) {
		pnl_Player.setPrevVisibility(isActive);
		
	}
	public void setNextEnabled(Boolean isActive) {
		pnl_Player.setNextVisibility(isActive);
		
	}
	private SortMusics getsortMusic() {
		Musicpanel musicpanel = getMusicList();
		if(musicpanel == null) return null;
		return musicpanel.getSortMusic();
	}
	public void setSelectedValue(int value) {
		SortMusics sortmusic = getsortMusic();
		if(sortmusic == null) return;
		sortmusic.setSelected(value);
		if(!sortmusic.getSelectMode())sortmusic.setSelectMode(true);
	}
	public int getSelectedValue() {
		SortMusics sortmusic = getsortMusic();
		if(sortmusic == null) return 0;
		return sortmusic.getSelected();
	}
}
