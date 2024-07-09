package userUI.mycomponents;

import javax.swing.JPanel;
import java.awt.BorderLayout;
import java.awt.Dimension;

import javax.swing.JLabel;
import javax.swing.SpringLayout;
import javax.swing.SwingConstants;
import java.awt.Font;

public class Submenu extends JPanel {

	private static final long serialVersionUID = 1L;

	/**
	 * Create the panel.
	 */
	public Submenu() {
		setLayout(new BorderLayout(0, 0));
		
		JPanel pnl_Image = new JPanel();
		pnl_Image.setPreferredSize(new Dimension(60,40));
		add(pnl_Image, BorderLayout.WEST);
		SpringLayout sl_pnl_Image = new SpringLayout();
		pnl_Image.setLayout(sl_pnl_Image);
		
		JLabel lbl_Image = new JLabel("image");
		lbl_Image.setHorizontalAlignment(SwingConstants.CENTER);
		sl_pnl_Image.putConstraint(SpringLayout.NORTH, lbl_Image, 0, SpringLayout.NORTH, pnl_Image);
		sl_pnl_Image.putConstraint(SpringLayout.WEST, lbl_Image, 10, SpringLayout.WEST, pnl_Image);
		sl_pnl_Image.putConstraint(SpringLayout.SOUTH, lbl_Image, -6, SpringLayout.SOUTH, pnl_Image);
		sl_pnl_Image.putConstraint(SpringLayout.EAST, lbl_Image, -10, SpringLayout.EAST, pnl_Image);
		pnl_Image.add(lbl_Image);
		
		JLabel lbl_Text = new JLabel("text");
		lbl_Text.setFont(new Font("Yu Gothic UI Semilight", Font.PLAIN, 9));
		add(lbl_Text, BorderLayout.CENTER);

	}
}
