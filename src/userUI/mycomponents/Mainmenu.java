package userUI.mycomponents;

import javax.swing.JPanel;
import java.awt.BorderLayout;
import java.awt.Dimension;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.SwingConstants;

import directories.Imagedtr;
import userUI.information.Musiclist;
import userUI.information.Mycolors;

import javax.swing.JSeparator;
import java.awt.Color;
import java.awt.event.ActionListener;
import java.util.concurrent.atomic.AtomicInteger;
import java.awt.event.ActionEvent;

public class Mainmenu extends JPanel {

	private static final long serialVersionUID = 1L;
	private AtomicInteger ID;
	private JLabel lbl_Text;
	private JPanel pnl_Left;
	private JButton btn_Open;
	private Boolean expand;
	private Musiclist list;
	private JPanel mother;
	/**
	 * Create the panel.
	 */
	public Mainmenu(Musiclist list,AtomicInteger ID,JPanel mother) {
		init();
		lbl_Text.setText(list.getName());
		this.list = list;
		this.mother = mother;
		this.ID = ID;
	}
	public Mainmenu() {
		init();

	}
	private void init() {
		expand = false;
		setLayout(new BorderLayout(0, 0));
		
		btn_Open = new JButton("");
		btn_Open.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				btnClicked();
				
			}
		});
		btn_Open.setPreferredSize(new Dimension(30,65));
		add(btn_Open, BorderLayout.EAST);
		
		lbl_Text = new JLabel("Files");

		lbl_Text.setOpaque(true);
		lbl_Text.setHorizontalAlignment(SwingConstants.LEFT);
		lbl_Text.setFont(new Font("Yu Gothic UI", Font.PLAIN, 18));

		
		JSeparator sprt_name = new JSeparator();
		sprt_name.setForeground(new Color(102, 51, 102));
		add(sprt_name, BorderLayout.SOUTH);
		
		JSeparator sprt_name_1 = new JSeparator();
		sprt_name_1.setForeground(new Color(102, 51, 102));
		add(sprt_name_1, BorderLayout.NORTH);
		
		pnl_Left = new JPanel();
		add(pnl_Left, BorderLayout.CENTER);
		pnl_Left.add(lbl_Text, BorderLayout.CENTER);
		lbl_Text.setBorder(BorderFactory.createEmptyBorder());
	}
	public int getIndex() {
		return list.getId();
	}
	@Override
	public void setBackground(Color bg) {
		// TODO Auto-generated method stub
		if(lbl_Text == null || pnl_Left == null || btn_Open == null) return;
		super.setBackground(bg);
		lbl_Text.setBackground(bg);
		pnl_Left.setBackground(bg);
		btn_Open.setBackground(bg);
	}
	public Musiclist getList() {
		return list;
	}
	public void setList(Musiclist list) {
		this.list = list;
	}
	@Override
	public void repaint() {
		// TODO Auto-generated method stub
		super.repaint();
		if(expand == null) return;
		String dtr;
		dtr = (ID.get() == list.getId()) ? Imagedtr.arrowdown : Imagedtr.arrowup;
		btn_Open.setIcon(new ImageIcon(dtr));
	}
	private void btnClicked() {
		if(ID.get() == list.getId()) ID.set(-1);
		else ID.set(list.getId());
		mother.repaint();
		repaint();
	}

}
