package userUI.subFrames;

import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

import javax.swing.SpringLayout;

import backend.Photoeditor;
import directories.Imagedtr;

import javax.swing.JSeparator;
import javax.swing.JSlider;
import javax.swing.event.ChangeListener;
import javax.swing.event.ChangeEvent;

public class Soundpanel extends JPanel {

	private static final long serialVersionUID = 1L;
	//this panel do change sound when music playing
	private String dtr;
	JLabel lbl_Photo;
	public Soundpanel(JButton btn_Sound) {

		initiliazer();
		lbl_Photo.addPropertyChangeListener(new PropertyChangeListener() {	
			@Override
			public void propertyChange(PropertyChangeEvent evt) {
				if(evt.getPropertyName().equals("icon") && dtr != null) {
					btn_Sound.setIcon(Photoeditor.photoScaleImage(dtr,
							btn_Sound.getSize().width, btn_Sound.getSize().width));
				}
			}
		});
		lbl_Photo.setIcon(Photoeditor.photoScaleImage(Imagedtr.sound,
				btn_Sound.getPreferredSize().width, btn_Sound.getPreferredSize().height));
	}
	public Soundpanel() {
		initiliazer();
	}
	private void initiliazer() {
		setLayout(new BorderLayout(0, 0));
		
		JPanel pnl_Sound = new JPanel();
		pnl_Sound.setPreferredSize(new Dimension(100,0));
		add(pnl_Sound, BorderLayout.WEST);
		pnl_Sound.setLayout(new BorderLayout(0, 0));
		
		lbl_Photo = new JLabel("");
		pnl_Sound.add(lbl_Photo, BorderLayout.CENTER);
		
		JPanel pnl_Soundsetter = new JPanel();
		add(pnl_Soundsetter, BorderLayout.CENTER);
		SpringLayout sl_pnl_Soundsetter = new SpringLayout();
		pnl_Soundsetter.setLayout(sl_pnl_Soundsetter);
		
		JSlider sld_Sound = new JSlider();
		sl_pnl_Soundsetter.putConstraint(SpringLayout.WEST, sld_Sound, -281, SpringLayout.EAST, pnl_Soundsetter);
		sl_pnl_Soundsetter.putConstraint(SpringLayout.EAST, sld_Sound, -10, SpringLayout.EAST, pnl_Soundsetter);
		sld_Sound.setValue(0);
		sl_pnl_Soundsetter.putConstraint(SpringLayout.NORTH, sld_Sound, 10, SpringLayout.NORTH, pnl_Soundsetter);
		sl_pnl_Soundsetter.putConstraint(SpringLayout.SOUTH, sld_Sound, -10, SpringLayout.SOUTH, pnl_Soundsetter);
		pnl_Soundsetter.add(sld_Sound);
		sld_Sound.addChangeListener(new ChangeListener() {
			public void stateChanged(ChangeEvent e) {
				setSoundPhoto(sld_Sound.getValue());
			}
		});
	}
	private void setSoundPhoto(int value) {
		int low = 20, medium = 50, high = 80;
		String dtr = null;
		if(value == 0) {
			dtr = Imagedtr.soundno;
		}
		else if(value <= low) {
			
			dtr = Imagedtr.sound;
		}
		else if(low < value && value <= medium) {
			dtr = Imagedtr.soundlvl1;
		}
		else if(medium < value && value <= high) {
			dtr = Imagedtr.soundlvl2;
		}
		else {
			dtr = Imagedtr.soundlvl3;
		}
		this.dtr = dtr;
		lbl_Photo.setIcon(Photoeditor.photoScaleImage(dtr,
				lbl_Photo.getPreferredSize().width, 
				this.getHeight()));
	}
}
