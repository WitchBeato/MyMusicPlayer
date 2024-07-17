package userUI;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import userUI.information.Mycolors;
import userUI.information.PlayerMessages;
import userUI.subFrames.PlayerError;
import userUI.subFrames.Playerpanel;
import userUI.subFrames.Playlistpanel;

import java.awt.BorderLayout;
import java.awt.Dimension;
import javax.swing.JScrollPane;

public class MainFrame extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane, pnl_Songs;
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
		setTitle("MymusicPlayer");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 800, 510);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(new BorderLayout(0, 0));

		JPanel pnl_Player = new Playerpanel(this.contentPane);
		pnl_Player.setPreferredSize(new Dimension(0,51));
		contentPane.add(pnl_Player, BorderLayout.SOUTH);
		pnl_Player.setBackground(Mycolors.openGray);
		
		JPanel pnl_Center = new JPanel();
		contentPane.add(pnl_Center, BorderLayout.CENTER);
		pnl_Center.setLayout(new BorderLayout(0, 0));
		

		
		JScrollPane spnl_Songs = new JScrollPane();
		pnl_Center.add(spnl_Songs, BorderLayout.CENTER);
		
		JPanel pnl_Selection = new Playlistpanel();
		pnl_Selection.setPreferredSize(new Dimension(133,0));
		pnl_Selection.setBackground(Mycolors.openGray);
		pnl_Center.add(pnl_Selection, BorderLayout.WEST);
		pnl_Center.add(pnl_Selection, BorderLayout.WEST);
		
		pnl_Songs =  new JPanel();
		pnl_Center.add(pnl_Songs, BorderLayout.CENTER);
		pnl_Songs.setLayout(new BorderLayout(0, 0));
		PlayerError playererror = new PlayerError(PlayerMessages.noChoice);
		panelChange(playererror);
	}
	private void panelChange(JPanel panel) {
		pnl_Songs.removeAll();
		pnl_Songs.add(panel,BorderLayout.CENTER);
	}

}
