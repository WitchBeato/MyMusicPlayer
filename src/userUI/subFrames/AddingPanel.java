package userUI.subFrames;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.filechooser.FileNameExtensionFilter;

import backend.AddtoMusiclist;
import backend.StringEditor;
import userUI.information.Musiclist;
import userUI.mycomponents.Mybutton;

import javax.swing.SpringLayout;
import javax.swing.JTextField;
import java.awt.BorderLayout;
import java.awt.Dimension;

import javax.swing.JButton;
import java.awt.Font;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class AddingPanel extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField txt_Directory;
	private Mybutton btn_OK, btn_Cancel, btn_Directory;
	private Musiclist list;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					AddingPanel frame = new AddingPanel();
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
	public AddingPanel(Musiclist list) {
		init();
		this.list = list;
		if(list.getId() == Musiclist.PLAYLIST) {
			txt_Directory.setEnabled(true);
			btn_Directory.setVisible(false);
		}
		else {
			txt_Directory.setEnabled(false);
			btn_Directory.setVisible(true);
		}
	}
	public AddingPanel() {
		init();
	}
	private void init() {
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 405, 131);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		SpringLayout sl_contentPane = new SpringLayout();
		contentPane.setLayout(sl_contentPane);
		
		JPanel panel = new JPanel();
		sl_contentPane.putConstraint(SpringLayout.NORTH, panel, 20, SpringLayout.NORTH, contentPane);
		sl_contentPane.putConstraint(SpringLayout.WEST, panel, 10, SpringLayout.WEST, contentPane);
		sl_contentPane.putConstraint(SpringLayout.SOUTH, panel, 50, SpringLayout.NORTH, contentPane);
		sl_contentPane.putConstraint(SpringLayout.EAST, panel, 371, SpringLayout.WEST, contentPane);
		contentPane.add(panel);
		panel.setLayout(new BorderLayout(0, 0));
		
		JButton btn_Directory = new JButton("");
		btn_Directory.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String directory = StringEditor.selectFolder();
				txt_Directory.setText(directory);
			}
		});
		btn_Directory.setPreferredSize(new Dimension(20,30));
		panel.add(btn_Directory, BorderLayout.EAST);
		
		txt_Directory = new JTextField();
		panel.add(txt_Directory, BorderLayout.CENTER);
		txt_Directory.setColumns(10);
		
		btn_OK = new Mybutton("Okay");
		btn_OK.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				addName(txt_Directory.getText());
			}
		});
		sl_contentPane.putConstraint(SpringLayout.WEST, btn_OK, -122, SpringLayout.EAST, contentPane);
		sl_contentPane.putConstraint(SpringLayout.SOUTH, btn_OK, -1, SpringLayout.SOUTH, contentPane);
		sl_contentPane.putConstraint(SpringLayout.EAST, btn_OK, 0, SpringLayout.EAST, panel);
		btn_OK.setFont(new Font("Tahoma", Font.BOLD, 15));
		contentPane.add(btn_OK);
		
		btn_Cancel = new Mybutton("Cancel");
		sl_contentPane.putConstraint(SpringLayout.WEST, btn_Cancel, 0, SpringLayout.WEST, panel);
		sl_contentPane.putConstraint(SpringLayout.EAST, btn_Cancel, 133, SpringLayout.WEST, contentPane);
		sl_contentPane.putConstraint(SpringLayout.SOUTH, btn_Cancel, 0, SpringLayout.SOUTH, contentPane);
		btn_Cancel.setPreferredSize(new Dimension(120,30));
		btn_Cancel.setFont(new Font("Tahoma", Font.BOLD, 15));
		contentPane.add(btn_Cancel);
	}
	private void addName(String text) {
		if(list == null) return;
		AddtoMusiclist.addtoMusiclist(list, text);
		this.getParent().repaint();
		close();
	}
	private void close() {
		this.dispose();
	}
}
