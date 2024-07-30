package userUI.subFrames;

import javax.swing.JPanel;
import javax.swing.JPopupMenu;

import userUI.MainFrame;
import userUI.information.MessageData;
import userUI.information.Musicinfo;
import userUI.information.Playlist;
import userUI.mycomponents.ListMusicPanel;
import userUI.mycomponents.Mybutton;
import userUI.mycomponents.SelectTitle;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Font;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JMenuItem;

import java.awt.GridBagLayout;
import java.util.ArrayList;
import java.awt.CardLayout;
import java.awt.FlowLayout;
import javax.swing.SpringLayout;
import javax.swing.SwingUtilities;

import backend.MusicPlayer;
import backend.Photoeditor;
import backend.Playlistbackend;
import staticinfo.Imagedtr;
import staticinfo.MenuItemlist;
import staticinfo.PlayerMessages;

import java.awt.Color;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class Musicpanel extends JPanel {

	private static final long serialVersionUID = 1L;
	private String directory = null;
	private Playlist playlist = null;
	private JLabel lbl_Text;
	private int column, row;
	private Musicpanel me = this;
	private MainFrame frame;
	private ListMusicPanel pnl_Musics;
	/**
	 * Create the panel.
	 */
	public Musicpanel(String directory,MainFrame player) {
		this.directory = directory;
		this.frame = player;
		init();
		lbl_Text.setText(directory);
	}
	public Musicpanel(Playlist playlist,MainFrame player){
		this.frame = player;
		this.playlist = playlist;
		init();
		lbl_Text.setText(playlist.getName());
	}
	public Musicpanel() {
		init();
	}
	private void init() {
		this.setBounds(0, 0, 667, 469);
		ArrayList<Musicinfo> list = new ArrayList<>();
		MessageData messagedata = null;
		if(directory != null) {
			list = Playlistbackend.Directorytoplaylist(directory).getList(); 
			messagedata = PlayerMessages.directoryNoFile;
		}
		else {
			list = playlist.getList();
			messagedata = PlayerMessages.playlistNoFile;
		}
		
		setLayout(new BorderLayout(0, 0));
		
		JPanel pnl_North = new JPanel();
		add(pnl_North, BorderLayout.NORTH);
		pnl_North.setPreferredSize(new Dimension(0,100));
		pnl_North.setLayout(new BorderLayout(0, 0));
		
		Mybutton btn_Settings = new Mybutton();
		btn_Settings.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				rightClick(e);
			}
		});
		btn_Settings.setPreferredSize(new Dimension(60,0));
		pnl_North.add(btn_Settings, BorderLayout.EAST);
		btn_Settings.setIcon(Photoeditor.photoScaleImage(Imagedtr.threeDot,
				btn_Settings.getPreferredSize().width, 
				pnl_North.getPreferredSize().height/2));
		
		lbl_Text = new JLabel("Title") {
			@Override
			public void setText(String text) {
				super.setText(text);
				if(lbl_Text == null) return;
				Font font = lbl_Text.getFont();
				text = text.replaceAll("\\\\", "/");
				if(text.length() > 15) {
					lbl_Text.setFont(new Font(font.getName(),font.getStyle(),font.getSize()-7));
					text = "<html>"+text+"</html>";
				}
				else {
					lbl_Text.setFont(PlayerError.universal);
				}
				super.setText(text);
			}
		};
		lbl_Text.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				rightClick(e);
			}
		});
		lbl_Text.setFont(PlayerError.universal);
		pnl_North.add(lbl_Text, BorderLayout.CENTER);
		
		JPanel pnl_South = new JPanel();
		add(pnl_South, BorderLayout.CENTER);
		pnl_South.setLayout(new BorderLayout(0, 0));
		
		pnl_Musics = new ListMusicPanel(list,false,frame);
		pnl_Musics.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				rightClick(e);
			}
		});
		pnl_Musics.setBackground(new Color(255, 255, 255));
		pnl_South.add(pnl_Musics);
		
		JPanel panel1 = new JPanel();
		panel1.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				rightClick(e);
			}
		});
		panel1.setPreferredSize(new Dimension(20,0));
		pnl_South.add(panel1, BorderLayout.WEST);
		
		JPanel pnl_Resize = new JPanel();
		pnl_Resize.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				rightClick(e);
			}
		});
		pnl_Resize.setPreferredSize(new Dimension(16,0));
		pnl_South.add(pnl_Resize, BorderLayout.EAST);
		Dimension firstSize = pnl_Resize.getPreferredSize();
		pnl_South.addComponentListener(new ComponentAdapter() {
			@Override
			public void componentResized(ComponentEvent e) {
				int widht = pnl_South.getWidth();
				int pluswidht = widht / 25;
				Dimension newSize = new Dimension(firstSize.width+ pluswidht,0);
				pnl_Resize.setPreferredSize(newSize);
				revalidate();
			}
		});
		if(list.size() == 0) {
			frame.panelChange(new PlayerError(messagedata));
		}
	}
	private void rightClick(MouseEvent e) {
		if(!SwingUtilities.isRightMouseButton(e)) return;
		JPopupMenu menu = new JPopupMenu();
		JMenuItem refresh = MenuItemlist.refresh;
		refresh.addActionListener(ae -> repaintMusiclist());
		menu.add(refresh);
		menu.show(e.getComponent(), e.getX(), e.getY());
	}
	public void repaintMusiclist() {
		pnl_Musics.sortItems();
		pnl_Musics.revalidate();
	}
}
