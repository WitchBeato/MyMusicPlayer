package userUI.mycomponents;

import javax.swing.JPanel;
import java.awt.BorderLayout;
import java.awt.Dimension;
import javax.swing.JLabel;
import javax.swing.SpringLayout;

import backend.Photoeditor;
import directories.Imagedtr;
import userUI.information.Musicinfo;

import javax.swing.JLayeredPane;
import javax.swing.JButton;
import java.awt.Font;
import javax.swing.SwingConstants;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class SelectTitle extends JPanel {

	public static Dimension SELECTSIZE = new Dimension(127,123);
	private static final long serialVersionUID = 1L;
	private Musicinfo title;
	private Boolean isDirectory;
	private Mybutton btn_Play, btn_Property, btn_Delete;
	private JLabel lbl_Title;
	public SelectTitle(Musicinfo info, Boolean isDirectory) {
		this.isDirectory = isDirectory;
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
		
		JLabel lbl_Photo = new JLabel("");
		lbl_Photo.setHorizontalAlignment(SwingConstants.CENTER);
		lbl_Photo.setBounds(0, 0, 133,95);
		lpnl_Photo.add(lbl_Photo, JLayeredPane.DEFAULT_LAYER);
		
		btn_Delete = new Mybutton();
		btn_Delete.setBounds(0, 0, 29, 29);
		lpnl_Photo.add(btn_Delete,JLayeredPane.POPUP_LAYER);
		
		btn_Property = new Mybutton();
		btn_Property.setBounds(98, 0, 29, 29);
		lpnl_Photo.add(btn_Property,JLayeredPane.POPUP_LAYER);
		
		
		JPanel pnl_Title = new JPanel();
		add(pnl_Title,BorderLayout.CENTER);
		pnl_Title.setLayout(new BorderLayout(0, 0));
		lbl_Title = new JLabel("text");
		lbl_Title.setOpaque(false);
		lbl_Title.setFont(new Font("Tahoma", Font.BOLD, 12));
		pnl_Title.add(lbl_Title, BorderLayout.CENTER);
		btn_Property.setIcon(Photoeditor.photoScaleImage(Imagedtr.threeDot,
				btn_Property.getSize().width,
				btn_Property.getSize().height));
		btn_Delete.setIcon(Photoeditor.photoScaleImage(Imagedtr.cancel,
				btn_Delete.getSize().width,
				btn_Delete.getSize().height));
		lbl_Photo.setIcon(Photoeditor.photoScaleImage(Imagedtr.musicIcon,
				lbl_Photo.getSize().width/2,
				lbl_Photo.getSize().height));
		
		btn_Play = new Mybutton();
		btn_Play.setBounds(98, 66, 29, 29);
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
		});
		btn_Delete.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				mouseEnterGlob();
			}
		});
		btn_Delete.setVisible(false);
		btn_Property.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				mouseEnterGlob();
			}
		});
		setButtonsVisible(false);
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
	private void mouseLeaveGlob() {
		setButtonsVisible(false);
	}
	private void mouseEnterGlob() {
		setButtonsVisible(true);
	}
}
