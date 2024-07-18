package userUI.subFrames;

import javax.swing.JPanel;

import userUI.information.Playlist;

import java.awt.BorderLayout;
import java.awt.Dimension;

import javax.swing.JButton;
import javax.swing.JLabel;
import java.awt.GridBagLayout;
import java.util.ArrayList;
import java.awt.CardLayout;

public class Musicpanel extends JPanel {

	private static final long serialVersionUID = 1L;
	private String directory = null;
	private Playlist playlist = null;
	private JLabel lbl_Text;
	/**
	 * Create the panel.
	 */
	public Musicpanel(String directory) {
		this.directory = directory;
		init();
		lbl_Text.setText(directory);
	}
	public Musicpanel(Playlist playlist){
		this.playlist = playlist;
		init();
		lbl_Text.setText(playlist.getName());
	}
	public Musicpanel() {
		init();
	}
	private void init() {
		this.setBounds(0, 0, 667, 459);
		setLayout(new BorderLayout(0, 0));
		
		JPanel pnl_North = new JPanel();
		add(pnl_North, BorderLayout.NORTH);
		pnl_North.setPreferredSize(new Dimension(0,100));
		pnl_North.setLayout(new BorderLayout(0, 0));
		
		JPanel pnl_Settings = new JPanel();
		pnl_Settings.setPreferredSize(new Dimension(60,0));
		pnl_North.add(pnl_Settings, BorderLayout.EAST);
		
		lbl_Text = new JLabel("Title");
		lbl_Text.setFont(PlayerError.universal);
		pnl_North.add(lbl_Text, BorderLayout.CENTER);
		
		JPanel pnl_Musics = new JPanel();
		add(pnl_Musics, BorderLayout.CENTER);
		GridBagLayout gbl_pnl_Musics = new GridBagLayout();
		gbl_pnl_Musics.columnWidths = new int[]{0};
		gbl_pnl_Musics.rowHeights = new int[]{0};
		gbl_pnl_Musics.columnWeights = new double[]{Double.MIN_VALUE};
		gbl_pnl_Musics.rowWeights = new double[]{Double.MIN_VALUE};
		pnl_Musics.setLayout(gbl_pnl_Musics);
	}

}
