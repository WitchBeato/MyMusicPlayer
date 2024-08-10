package userUI;

import java.awt.EventQueue;
import java.util.ArrayList;

import javax.swing.DefaultListModel;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import userUI.information.Musiclist;
import userUI.information.Playlist;
import userUI.information.Settings;

import javax.swing.JList;
import java.awt.Font;
import javax.swing.event.ListSelectionListener;
import javax.swing.event.ListSelectionEvent;
import javax.swing.JButton;

public class SelectPlaylist extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private Playlist playlist;
	private JList<Playlist> lst_Playlist;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					SelectPlaylist frame = new SelectPlaylist();
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
	public SelectPlaylist(Settings setting) {
		init();
		fillList(setting);
	}
	public SelectPlaylist() {
		init();
	}
	private void init() {
		setResizable(false);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 257, 487);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		lst_Playlist = new JList();
		lst_Playlist.addListSelectionListener(new ListSelectionListener() {
			public void valueChanged(ListSelectionEvent e) {
				int id = lst_Playlist.getSelectedIndex();
				if(id == -1) return;
				playlist = lst_Playlist.getSelectedValue();
				if(Settings.DEBUG_MODE)System.out.println(playlist);
			}
		});
		lst_Playlist.setFont(new Font("Yu Gothic UI", Font.BOLD, 15));
		lst_Playlist.setBounds(10, 10, 223, 369);
		contentPane.add(lst_Playlist);
		
		JButton btn_OK = new JButton("OK");
		btn_OK.setFont(new Font("Tahoma", Font.PLAIN, 16));
		btn_OK.setBounds(37, 385, 178, 55);
		contentPane.add(btn_OK);
	}
	private void fillList(Settings setting) {
		Playlist[] playlistofList = setting.getMusiclist(Musiclist.PLAYLIST).getMusiclist().stream().toArray(Playlist[]::new);
		lst_Playlist.setListData(playlistofList);
		repaint();
	}
}
