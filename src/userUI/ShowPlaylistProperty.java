package userUI;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import staticinfo.Dtr;
import userUI.information.Musicinfo;
import userUI.information.Playlist;
import userUI.mycomponents.Mybutton;
import userUI.subFrames.Musicpanel;

import java.awt.GridBagLayout;
import javax.swing.JLabel;
import java.awt.GridBagConstraints;
import java.awt.Font;
import javax.swing.JTextField;
import java.awt.Insets;
import java.util.ArrayList;

import javax.swing.SwingConstants;
import java.awt.BorderLayout;
import javax.swing.JButton;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class ShowPlaylistProperty extends JFrame {

	private static final long serialVersionUID = 1L;
	private JFrame me = this;
	private JPanel contentPane;
	private JTextField txt_Name;
	private JLabel lbl_Name;
	private JLabel lbl_Size;
	private Playlist playlist;
	private Musicpanel parent;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ShowPlaylistProperty frame = new ShowPlaylistProperty();
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
	public ShowPlaylistProperty(String directory, ArrayList<Musicinfo> list,Musicpanel parent){
		init();
		this.parent = parent;
		lbl_Name.setText("Directory");
		txt_Name.setText(directory);
		lbl_Size.setText(Integer.toString(list.size()));
		txt_Name.setEnabled(false);
		setBounds(0, 0, this.getWidth(), 160);
	}
	public ShowPlaylistProperty(Playlist playlist,Musicpanel parent){
		init();
		this.parent = parent;
		this.playlist = playlist;
		lbl_Name.setText("Name");
		txt_Name.setText(playlist.getName());
		lbl_Size.setText(Integer.toString(playlist.getList().size()));
	}
	public ShowPlaylistProperty() {
		setResizable(false);
		init();
	}
	private void init() {
		this.setIconImage(Dtr.logo.getImage());
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 450, 239);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(new BorderLayout(0, 0));
		
		JPanel pnl_North = new JPanel();
		contentPane.add(pnl_North);
		pnl_North.setLayout(null);
		
		JLabel lbl_howmany = new JLabel("Size");
		lbl_howmany.setBounds(38, 86, 43, 31);
		pnl_North.add(lbl_howmany);
		lbl_howmany.setHorizontalAlignment(SwingConstants.RIGHT);
		lbl_howmany.setFont(new Font("Tahoma", Font.PLAIN, 25));
		
		lbl_Name = new JLabel("Name");
		lbl_Name.setBounds(38, 32, 63, 31);
		pnl_North.add(lbl_Name);
		lbl_Name.setFont(new Font("Tahoma", Font.PLAIN, 25));
		
		txt_Name = new JTextField();
		txt_Name.setBounds(123, 36, 257, 28);
		pnl_North.add(txt_Name);
		txt_Name.setFont(new Font("Tahoma", Font.PLAIN, 18));
		txt_Name.setColumns(10);
		
		lbl_Size = new JLabel("");
		lbl_Size.setHorizontalAlignment(SwingConstants.CENTER);
		lbl_Size.setBounds(123, 86, 257, 31);
		pnl_North.add(lbl_Size);
		lbl_Size.setFont(new Font("Tahoma", Font.PLAIN, 25));
		
		Mybutton btn_Ok = new Mybutton("Update");
		btn_Ok.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if(playlist != null) {
					mouseOK();
				}
			}
		});
		btn_Ok.setFont(new Font("Tahoma", Font.PLAIN, 18));
		btn_Ok.setBounds(147, 127, 165, 43);
		pnl_North.add(btn_Ok);
		this.setResizable(false);
	}
	public void mouseOK() {
		playlist.setName(txt_Name.getText());
		me.dispose();
		parent.repaint();
	}
}
