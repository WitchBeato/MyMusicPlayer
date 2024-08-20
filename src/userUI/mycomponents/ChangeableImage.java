package userUI.mycomponents;

import java.awt.Image;

import javax.swing.JPanel;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;

import javax.swing.JLayeredPane;
import javax.swing.SpringLayout;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.SwingConstants;

import backend.Photoeditor;
import backend.StringEditor;
import staticinfo.Dtr;
import staticinfo.Imagedtr;
import staticinfo.SupportedTypes;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;

public class ChangeableImage extends JPanel {

	private static final long serialVersionUID = 1L;
	private JLabel lbl_AddLogo, lbl_Image;
	private Boolean enabled;
	private BufferedImage currentImage = null;
	public ChangeableImage() {
		init();
	}
	private void init() {
		setBounds(0,0,300,300);
		setLayout(new BorderLayout(0, 0));
		
		JLayeredPane lpnl_Content = new JLayeredPane();
		lpnl_Content.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				mouseOn();
			}
		});
		add(lpnl_Content, BorderLayout.CENTER);
		SpringLayout sl_lpnl_Content = new SpringLayout();
		lpnl_Content.setLayout(sl_lpnl_Content);
		
		lbl_Image = new JLabel("");
		lbl_Image.setPreferredSize(this.getSize());
		lbl_Image.setOpaque(false);
		lbl_Image.setHorizontalAlignment(SwingConstants.CENTER);
		sl_lpnl_Content.putConstraint(SpringLayout.NORTH, lbl_Image, 0, SpringLayout.NORTH, lpnl_Content);
		sl_lpnl_Content.putConstraint(SpringLayout.WEST, lbl_Image, 0, SpringLayout.WEST, lpnl_Content);
		sl_lpnl_Content.putConstraint(SpringLayout.SOUTH, lbl_Image, 300, SpringLayout.NORTH, lpnl_Content);
		sl_lpnl_Content.putConstraint(SpringLayout.EAST, lbl_Image, 0, SpringLayout.EAST, lpnl_Content);
		lpnl_Content.add(lbl_Image,JLayeredPane.DEFAULT_LAYER);
		
		lbl_AddLogo = new JLabel("");
		lbl_AddLogo.setVerticalAlignment(SwingConstants.TOP);
		lbl_AddLogo.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				mouseOn();
			}
			@Override
			public void mouseClicked(MouseEvent e) {
				mouseClick();
			}
			@Override
			public void mouseExited(MouseEvent e) {
				mouseExit();
			}
		});
		lbl_AddLogo.setOpaque(false);
		lbl_AddLogo.setHorizontalAlignment(SwingConstants.CENTER);
		sl_lpnl_Content.putConstraint(SpringLayout.NORTH, lbl_AddLogo, 0, SpringLayout.NORTH, lpnl_Content);
		sl_lpnl_Content.putConstraint(SpringLayout.WEST, lbl_AddLogo, 0, SpringLayout.WEST, lpnl_Content);
		sl_lpnl_Content.putConstraint(SpringLayout.SOUTH, lbl_AddLogo, 300, SpringLayout.NORTH, lpnl_Content);
		sl_lpnl_Content.putConstraint(SpringLayout.EAST, lbl_AddLogo, 0, SpringLayout.EAST, lpnl_Content);
		lbl_AddLogo.setPreferredSize(this.getSize());
		lpnl_Content.add(lbl_AddLogo,JLayeredPane.DRAG_LAYER);
		lbl_AddLogo.setBackground(new Color(0.0f, 0.0f, 0.0f, 0.5f));
		lbl_AddLogo.setIcon(Photoeditor.photoScaleImage(
				Dtr.getImage("add.png")
				, lbl_AddLogo.getPreferredSize().width
				, lbl_AddLogo.getPreferredSize().height));
		lbl_AddLogo.setVisible(false);
	}
	public void setImageCurrent(String directory) {
		if(directory == null) {
			directory = Imagedtr.question;
		}
		ImageIcon icon = Photoeditor.photoScaleImage(
				directory, 
				lbl_Image.getPreferredSize().height, 
				lbl_Image.getPreferredSize().width);
		lbl_Image.setIcon(icon);
		if (directory == Imagedtr.question) currentImage = null;
		else currentImage = Photoeditor.iconToBufferedImage(icon);
	}
	public void setImageCurrentwithImage(Image image) {
		if(image == null) return;
		ImageIcon icon = new ImageIcon(image);
		lbl_Image.setIcon(icon);
		currentImage = Photoeditor.iconToBufferedImage(icon);
	}
	private void mouseOn() {
	if(!enabled) return;
	lbl_AddLogo.setVisible(true);
	}
	private void mouseExit() {
		lbl_AddLogo.setVisible(false);
	}
	private void mouseClick(){
		if(!enabled) return;
		String imagedtr = StringEditor.selectFile(SupportedTypes.photoExt);
		if(imagedtr != null) {
			setImageCurrent(imagedtr);
		}
	}
	@Override
	public void setPreferredSize(Dimension preferredSize) {
		// TODO Auto-generated method stub
		super.setPreferredSize(preferredSize);
		if(lbl_AddLogo == null) return;
		lbl_AddLogo.setPreferredSize(preferredSize);
		lbl_AddLogo.setIcon(Photoeditor.photoScaleImage(
				Dtr.getImage("add.png")
				, preferredSize.width
				, preferredSize.height));
		
	}
	@Override
	public void setEnabled(boolean enabled) {
		// TODO Auto-generated method stub
		this.enabled = enabled;
		super.setEnabled(enabled);
		if(lbl_AddLogo == null) return;
		lbl_AddLogo.setEnabled(enabled);
	}
	public BufferedImage getCurrentImage() {
		return currentImage;
	}
	public void setCurrentImage(BufferedImage currentImage) {
		this.currentImage = currentImage;
	}
}
