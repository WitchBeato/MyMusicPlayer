package userUI;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.filechooser.FileNameExtensionFilter;

import backend.AddtoMusiclist;
import backend.Photoeditor;
import backend.StringEditor;
import staticinfo.Dtr;
import staticinfo.Imagedtr;
import userUI.information.Musiclist;
import userUI.mycomponents.JTextwithFolder;
import userUI.mycomponents.Mybutton;

import javax.swing.SpringLayout;
import javax.swing.JTextField;
import java.awt.BorderLayout;
import java.awt.Dimension;

import javax.swing.JButton;
import java.awt.Font;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class AddDirectory extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextwithFolder txt_Folder;
	private Mybutton btn_OK, btn_Cancel;
	private Musiclist list;
	private JPanel Mother;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					AddDirectory frame = new AddDirectory();
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
	public AddDirectory(Musiclist list,JPanel mother) {
		this.setIconImage(Dtr.logo.getImage());
		txt_Folder = new JTextwithFolder(list.getId(),JTextwithFolder.FOLDERMODE);
		this.list = list;
		this.Mother = mother;
		init();
		String text = (list.getId() == Musiclist.PLAYLIST) ? 
				"please enter Playlist name" :
				"please Choose the Directory";
		setTitle(text);
	}
	public AddDirectory() {
		txt_Folder = new JTextwithFolder(Musiclist.DIRECTORY,JTextwithFolder.FOLDERMODE);
		init();
	}
	private void init() {
		this.setIconImage(Dtr.logo.getImage());
		setResizable(false);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 405, 131);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		
		

		
		btn_OK = new Mybutton("Okay");
		btn_OK.setBounds(264, 61, 122, 27);
		btn_OK.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				addName(txt_Folder.getText());
			}
		});
		contentPane.setLayout(null);
		btn_OK.setFont(new Font("Tahoma", Font.BOLD, 15));
		contentPane.add(btn_OK);
		
		btn_Cancel = new Mybutton("Cancel");
		btn_Cancel.setBounds(5, 59, 133, 30);
		btn_Cancel.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				close();
			}
		});
		btn_Cancel.setPreferredSize(new Dimension(120,30));
		btn_Cancel.setFont(new Font("Tahoma", Font.BOLD, 15));
		contentPane.add(btn_Cancel);
		
		txt_Folder.setBounds(10, 10, 371, 41);
		contentPane.add(txt_Folder);
	}
	private void addName(String text) {
		if(list == null) return;
		AddtoMusiclist.addtoMusiclist(list, text);
		Mother.repaint();
		close();

	}
	private void close() {
		this.dispose();
	}

}
