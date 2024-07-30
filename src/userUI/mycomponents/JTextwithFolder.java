package userUI.mycomponents;

import java.awt.BorderLayout;
import java.awt.Dimension;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JPanel;
import javax.swing.JTextField;


import backend.Photoeditor;
import backend.StringEditor;
import staticinfo.Imagedtr;
import staticinfo.SupportedTypes;
import userUI.information.Musiclist;

public class JTextwithFolder extends JPanel {

	private static final long serialVersionUID = 1L;
	private Mybutton btn_Directory;
	private JTextField txt_Directory;
	//it decide which button will search folder or file
	public static final int FOLDERMODE = 1, FILEMODE = 2;
	private int Searchmode;
	/**
	 * Create the panel.
	 */
	public JTextwithFolder(int listid, int Searchmode) {
		init();
		this.Searchmode = Searchmode;
		if(listid == Musiclist.PLAYLIST) {
			txt_Directory.setEnabled(true);
			btn_Directory.setVisible(false);
		}
		else {
			txt_Directory.setEnabled(false);
			btn_Directory.setVisible(true);
		}
	}
	public JTextwithFolder() {
		init();
	}
	private void init() {
		setBounds(100, 100, 405, 46);
		

		this.setLayout(new BorderLayout(0, 0));
		
		btn_Directory = new Mybutton("");
		btn_Directory.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String directory = null;
				switch (Searchmode) {
				case JTextwithFolder.FOLDERMODE:
					directory = StringEditor.selectFolder();
					break;
				case JTextwithFolder.FILEMODE:
					directory = StringEditor.selectFile(SupportedTypes.audioExt);
					break;
				default:
					System.out.println("unknown searchmode");
					break;
				}

				setText(directory);
			}
		});
		btn_Directory.setPreferredSize(new Dimension(20,30));
		btn_Directory.setIcon(Photoeditor.photoScaleImage(Imagedtr.folder
				, btn_Directory.getPreferredSize().width
				, btn_Directory.getPreferredSize().height));
		this.add(btn_Directory, BorderLayout.EAST);
		
		txt_Directory = new JTextField();
		this.add(txt_Directory, BorderLayout.CENTER);
		txt_Directory.setColumns(10);

	}
	public String getText() {
		return txt_Directory.getText();
	}
	public JTextField gettxt_Directory() {
		return txt_Directory;
	}
	public void setText(String text) {
		txt_Directory.setText(text);
	}
	

}
