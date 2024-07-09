package userUI.subFrames;

import javax.swing.JPanel;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import javax.swing.BoxLayout;
import java.awt.GridLayout;
import javax.swing.JButton;
import javax.swing.SpringLayout;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.SwingConstants;

import backend.Photoeditor;
import directories.Imagedtr;

import javax.swing.JProgressBar;
import java.awt.Color;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class Playerpanel extends JPanel {

	private static final long serialVersionUID = 1L;
	private JPanel pnl_Buttons;
	private JPanel pnl_Time;
	private JPanel pnl_Soundicon;
	/**
	 * Create the panel.
	 */
	public Playerpanel() {
		setLayout(new BorderLayout(0, 0));
		setSize(510,51);
		pnl_Buttons = new JPanel();
		pnl_Buttons.setPreferredSize(new Dimension(100,0));
		add(pnl_Buttons, BorderLayout.WEST);
		pnl_Buttons.setLayout(new GridLayout(0, 3, 0, 0));
		
		JPanel pnl_Prev = new JPanel();
		pnl_Buttons.add(pnl_Prev);
		SpringLayout sl_pnl_Prev = new SpringLayout();
		pnl_Prev.setLayout(sl_pnl_Prev);
		
		JButton btn_Prev = new JButton("");
		btn_Prev.setIcon(Photoeditor.photoScaleImage(Imagedtr.prev, 10, 50));
		sl_pnl_Prev.putConstraint(SpringLayout.NORTH, btn_Prev, 10, SpringLayout.NORTH, pnl_Prev);
		sl_pnl_Prev.putConstraint(SpringLayout.WEST, btn_Prev, 10, SpringLayout.WEST, pnl_Prev);
		sl_pnl_Prev.putConstraint(SpringLayout.SOUTH, btn_Prev, -10, SpringLayout.SOUTH, pnl_Prev);
		sl_pnl_Prev.putConstraint(SpringLayout.EAST, btn_Prev, 23, SpringLayout.WEST, pnl_Prev);
		pnl_Prev.add(btn_Prev);
		
		JPanel pnl_Stop = new JPanel();
		pnl_Buttons.add(pnl_Stop);
		pnl_Stop.setLayout(new BorderLayout(0, 0));
		
		JButton btn_Stop = new JButton("");
		btn_Stop.setIcon(Photoeditor.photoScaleImage(Imagedtr.stop, 30, 51));
		btn_Stop.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		pnl_Stop.add(btn_Stop, BorderLayout.CENTER);
		
		JPanel pnl_Onward = new JPanel();
		pnl_Buttons.add(pnl_Onward);
		SpringLayout sl_pnl_Onward = new SpringLayout();
		pnl_Onward.setLayout(sl_pnl_Onward);
		
		JButton btn_Onward = new JButton("");
		btn_Onward.setIcon(Photoeditor.photoScaleImage(Imagedtr.onward, 10, 50));
		sl_pnl_Onward.putConstraint(SpringLayout.NORTH, btn_Onward, 10, SpringLayout.NORTH, pnl_Onward);
		sl_pnl_Onward.putConstraint(SpringLayout.WEST, btn_Onward, 10, SpringLayout.WEST, pnl_Onward);
		sl_pnl_Onward.putConstraint(SpringLayout.SOUTH, btn_Onward, -10, SpringLayout.SOUTH, pnl_Onward);
		sl_pnl_Onward.putConstraint(SpringLayout.EAST, btn_Onward, -10, SpringLayout.EAST, pnl_Onward);
		pnl_Onward.add(btn_Onward);
		
		JPanel pnl_East = new JPanel();
		add(pnl_East, BorderLayout.CENTER);
		pnl_East.setLayout(new BorderLayout(0, 0));
		
		pnl_Time = new JPanel();
		pnl_Time.setPreferredSize(new Dimension(50,0));
		pnl_East.add(pnl_Time, BorderLayout.WEST);
		pnl_Time.setLayout(new GridLayout(1, 0, 0, 0));
		
		JLabel lblNewLabel = new JLabel("00:00");
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 6));
		pnl_Time.add(lblNewLabel);
		
		JLabel lbl_Slash = new JLabel("/");
		lbl_Slash.setHorizontalAlignment(SwingConstants.CENTER);
		lbl_Slash.setFont(new Font("Tahoma", Font.PLAIN, 9));
		pnl_Time.add(lbl_Slash);
		
		JLabel lblNewLabel_1 = new JLabel("00:00");
		lblNewLabel_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_1.setFont(new Font("Tahoma", Font.PLAIN, 6));
		pnl_Time.add(lblNewLabel_1);
		
		pnl_Soundicon = new JPanel();
		pnl_Soundicon.setPreferredSize(new Dimension(50,0));
		pnl_East.add(pnl_Soundicon, BorderLayout.EAST);
		pnl_Soundicon.setLayout(new BorderLayout(0, 0));
		
		JButton btn_Soundicon = new JButton("");
		btn_Soundicon.setIcon(Photoeditor.photoScaleImage(Imagedtr.sound, 45, 51));
		
		pnl_Soundicon.add(btn_Soundicon, BorderLayout.CENTER);
		
		JPanel pnl_Timeline = new JPanel();
		pnl_East.add(pnl_Timeline, BorderLayout.CENTER);
		SpringLayout sl_pnl_Timeline = new SpringLayout();
		pnl_Timeline.setLayout(sl_pnl_Timeline);
		
		JProgressBar progressBar_1 = new JProgressBar();
		sl_pnl_Timeline.putConstraint(SpringLayout.NORTH, progressBar_1, 10, SpringLayout.NORTH, pnl_Timeline);
		sl_pnl_Timeline.putConstraint(SpringLayout.WEST, progressBar_1, 20, SpringLayout.WEST, pnl_Timeline);
		sl_pnl_Timeline.putConstraint(SpringLayout.SOUTH, progressBar_1, 41, SpringLayout.NORTH, pnl_Timeline);
		sl_pnl_Timeline.putConstraint(SpringLayout.EAST, progressBar_1, 0, SpringLayout.EAST, pnl_Timeline);
		progressBar_1.setForeground(new Color(102, 0, 153));
		pnl_Timeline.add(progressBar_1);

	}
}
