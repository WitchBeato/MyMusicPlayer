package userUI.mycomponents;

import javax.swing.JPanel;
import java.awt.BorderLayout;
import java.awt.Dimension;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JLayeredPane;

import java.awt.Font;
import javax.swing.SwingConstants;

import backend.Photoeditor;
import directories.Imagedtr;
import userUI.information.Musiclist;
import userUI.information.Mycolors;
import userUI.subFrames.AddingPanel;
import userUI.subFrames.Playlistpanel;

import javax.swing.JSeparator;
import java.awt.Color;
import java.awt.event.ActionListener;
import java.util.concurrent.atomic.AtomicInteger;
import java.awt.event.ActionEvent;
import net.miginfocom.swing.MigLayout;
import javax.swing.SpringLayout;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class Mainmenu extends JPanel {

	private static final long serialVersionUID = 1L;
	private AtomicInteger ID;
	private JLabel lbl_Text;
	private JLayeredPane pnl_Left;
	private JButton btn_Open;
	private Mybutton btn_Add;
	private Boolean expand;
	private Musiclist list;
	private Playlistpanel mother;
	/**
	 * Create the panel.
	 */
	public Mainmenu(Musiclist list,AtomicInteger ID,Playlistpanel mother) {
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
		this.setPreferredSize(new Dimension(130,70));
		btn_Open = new JButton("");
		btn_Open.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				btnClicked();
			}
		});
		btn_Open.setPreferredSize(new Dimension(30,65));
		add(btn_Open, BorderLayout.EAST);
		
		lbl_Text = new JLabel("Files");
		lbl_Text.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if(e.getClickCount() == 2)btnClicked();
			}
		});
		lbl_Text.setPreferredSize(new Dimension(250, 71));

		lbl_Text.setOpaque(true);
		lbl_Text.setHorizontalAlignment(SwingConstants.LEFT);
		lbl_Text.setFont(new Font("Yu Gothic UI", Font.PLAIN, 18));
		lbl_Text.setVisible(true);
		
		JSeparator sprt_name = new JSeparator();
		sprt_name.setForeground(new Color(102, 51, 102));
		add(sprt_name, BorderLayout.SOUTH);
		
		JSeparator sprt_name_1 = new JSeparator();
		sprt_name_1.setForeground(new Color(102, 51, 102));
		add(sprt_name_1, BorderLayout.NORTH);
		
		pnl_Left = new JLayeredPane();
		add(pnl_Left, BorderLayout.CENTER);
		SpringLayout sl_pnl_Left = new SpringLayout();
		sl_pnl_Left.putConstraint(SpringLayout.NORTH, lbl_Text, 0, SpringLayout.NORTH, pnl_Left);
		sl_pnl_Left.putConstraint(SpringLayout.WEST, lbl_Text, 0, SpringLayout.WEST, pnl_Left);
		sl_pnl_Left.putConstraint(SpringLayout.SOUTH, lbl_Text, 0, SpringLayout.SOUTH, pnl_Left);
		sl_pnl_Left.putConstraint(SpringLayout.EAST, lbl_Text, 122, SpringLayout.WEST, pnl_Left);
		pnl_Left.setLayout(sl_pnl_Left);
		pnl_Left.add(lbl_Text);
		lbl_Text.setBorder(BorderFactory.createEmptyBorder());
		
		btn_Add = new Mybutton("New button");
		btn_Add.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				addToMenu();
			}
		});
		btn_Add.setText("");
		sl_pnl_Left.putConstraint(SpringLayout.NORTH, btn_Add, 0, SpringLayout.NORTH, pnl_Left);
		sl_pnl_Left.putConstraint(SpringLayout.WEST, btn_Add, 80, SpringLayout.WEST, pnl_Left);
		sl_pnl_Left.putConstraint(SpringLayout.SOUTH, btn_Add, 25, SpringLayout.NORTH, pnl_Left);
		sl_pnl_Left.putConstraint(SpringLayout.EAST, btn_Add, 100, SpringLayout.WEST, pnl_Left);
		pnl_Left.add(btn_Add,JLayeredPane.DRAG_LAYER);
		btn_Add.setIcon(Photoeditor.photoScaleImage(Imagedtr.add
				,btn_Add.getPreferredSize().width
			    ,btn_Add.getPreferredSize().height+10));
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
		btn_Add.setBackground(bg);
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
		else ID.set(list.getId());;
		mother.repaint();
	}
	private void addToMenu(){
		AddingPanel frame = new AddingPanel(list,mother);
		frame.setAlwaysOnTop(true);
		frame.setVisible(true);
	}
}
