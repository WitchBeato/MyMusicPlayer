package userUI.subFrames;

import javax.swing.JPanel;
import javax.swing.SpringLayout;

import staticinfo.PlaylistComparators;
import userUI.SelectPlaylist;
import userUI.information.Musicinfo;
import userUI.information.Playlist;
import userUI.information.Settings;
import userUI.mycomponents.ListMusicPanel;
import userUI.mycomponents.Mybutton;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;

import javax.swing.JComboBox;
import java.awt.event.ItemListener;
import java.awt.event.ItemEvent;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.SwingConstants;

import javazoom.jl.player.advanced.jlap;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import javax.swing.JButton;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.UIManager;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class SortMusics extends JPanel {
	private JComboBox cmb_Sort;
	private static final long serialVersionUID = 1L;
	private int selected = 0, max = 0;
	private Boolean isSelect;
	private IntegerLabel lbl_Selected,lbl_Size;
	/**
	 * Create the panel.
	 */
	private SortMusics me = this;
	private Musicpanel musicpanel;
	private Playlist Workedlist;
	private ArrayList<Musicinfo> Chosedlist;
	private JLabel lbl_Slash;
	private JPanel pnl_Center;
	private JPanel pnl_East;
	private Mybutton btn_Close;
	private JPanel pnl_Choices;
	private Mybutton btn_DeleteAll;
	private Mybutton btn_Merge;
	public SortMusics(Musicpanel musicpanel,Boolean isDirectory) {
		this.musicpanel = musicpanel;
		Workedlist = musicpanel.getMusicList().getPlaylist();
	    init();
		setAllItems();
		setMode(isDirectory);
		setSelectMode(false);
	}
	public SortMusics() {
       init();
	}
	private void init() {
		setLayout(new BorderLayout(0, 0));
		
		pnl_Center = new JPanel();
		add(pnl_Center);
		pnl_Center.setLayout(new BorderLayout(0, 0));
		
		cmb_Sort = new JComboBox();
		pnl_Center.add(cmb_Sort);
		cmb_Sort.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent e) {
				if(e.getStateChange() != ItemEvent.SELECTED) return;
				Comparator<Musicinfo> item = (Comparator<Musicinfo>) cmb_Sort.getSelectedItem();
				if(item == null) return;
				doSort(item);
				setSelected(0);
			}
		});
		
		pnl_East = new JPanel();
		pnl_East.setPreferredSize(new Dimension(300,0));
		add(pnl_East, BorderLayout.EAST);
		SpringLayout sl_pnl_East = new SpringLayout();
		pnl_East.setLayout(sl_pnl_East);
		
		lbl_Selected = new IntegerLabel("00");
		sl_pnl_East.putConstraint(SpringLayout.NORTH, lbl_Selected, 10, SpringLayout.NORTH, pnl_East);
		sl_pnl_East.putConstraint(SpringLayout.WEST, lbl_Selected, 10, SpringLayout.WEST, pnl_East);
		sl_pnl_East.putConstraint(SpringLayout.EAST, lbl_Selected, -249, SpringLayout.EAST, pnl_East);
		pnl_East.add(lbl_Selected);
		lbl_Selected.setHorizontalAlignment(SwingConstants.CENTER);
		lbl_Selected.setFont(new Font("Tahoma", Font.PLAIN, 16));
		
		lbl_Slash = new JLabel("/");
		sl_pnl_East.putConstraint(SpringLayout.NORTH, lbl_Slash, 0, SpringLayout.NORTH, lbl_Selected);
		sl_pnl_East.putConstraint(SpringLayout.WEST, lbl_Slash, 6, SpringLayout.EAST, lbl_Selected);
		sl_pnl_East.putConstraint(SpringLayout.EAST, lbl_Slash, -202, SpringLayout.EAST, pnl_East);
		pnl_East.add(lbl_Slash);
		lbl_Slash.setHorizontalAlignment(SwingConstants.CENTER);
		lbl_Slash.setFont(new Font("Tahoma", Font.PLAIN, 16));
		
		lbl_Size = new IntegerLabel("00");
		sl_pnl_East.putConstraint(SpringLayout.NORTH, lbl_Size, 0, SpringLayout.NORTH, lbl_Selected);
		sl_pnl_East.putConstraint(SpringLayout.WEST, lbl_Size, 6, SpringLayout.EAST, lbl_Slash);
		sl_pnl_East.putConstraint(SpringLayout.EAST, lbl_Size, -165, SpringLayout.EAST, pnl_East);
		pnl_East.add(lbl_Size);
		lbl_Size.setHorizontalAlignment(SwingConstants.CENTER);
		lbl_Size.setFont(new Font("Tahoma", Font.PLAIN, 16));
		
		pnl_Choices = new JPanel();
		sl_pnl_East.putConstraint(SpringLayout.NORTH, pnl_Choices, 0, SpringLayout.NORTH, pnl_East);
		sl_pnl_East.putConstraint(SpringLayout.WEST, pnl_Choices, 6, SpringLayout.EAST, lbl_Size);
		sl_pnl_East.putConstraint(SpringLayout.SOUTH, pnl_Choices, 0, SpringLayout.SOUTH, pnl_East);
		sl_pnl_East.putConstraint(SpringLayout.EAST, pnl_Choices, -44, SpringLayout.EAST, pnl_East);
		pnl_East.add(pnl_Choices);
		pnl_Choices.setLayout(new GridLayout(1, 0, 0, 0));
		
		btn_DeleteAll = new Mybutton("");
		btn_DeleteAll.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				deleteAll();
			}
		});
		btn_DeleteAll.setBackground(Color.RED);
		pnl_Choices.add(btn_DeleteAll);
		
		btn_Merge = new Mybutton("");
		btn_Merge.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				setMergePlaylist(musicpanel.getFrame().getSettings());
			}
		});
		btn_Merge.setBackground(UIManager.getColor("Button.disabledShadow"));
		pnl_Choices.add(btn_Merge);
		
		btn_Close = new Mybutton("");
		btn_Close.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				closeSelectMode();
			}
		});
		btn_Close.setBackground(Color.red);
		sl_pnl_East.putConstraint(SpringLayout.NORTH, btn_Close, 10, SpringLayout.NORTH, pnl_East);
		sl_pnl_East.putConstraint(SpringLayout.SOUTH, btn_Close, 37, SpringLayout.NORTH, pnl_East);
		sl_pnl_East.putConstraint(SpringLayout.EAST, btn_Close, -5, SpringLayout.EAST, pnl_East);
		pnl_East.add(btn_Close);
	}
	private void setAllItems() {
	    PlaylistComparators.setCompareTypes();
	    for (int i = 0; i < PlaylistComparators.getSize(); i++) {
			cmb_Sort.addItem(PlaylistComparators.getCompareType(i));
		}
	}
	private void setVariable() {
		try {
			max = Workedlist.getList().size();
			lbl_Size.setText(String.valueOf(max));
			lbl_Selected.setText(String.valueOf(selected));
		} catch (NullPointerException e) {
			// TODO: handle exception
			return;
		}
		
	}
	private void doSort(Comparator<Musicinfo> id) {
		if(musicpanel == null ) return;
		musicpanel.getMusicList().sortIt(id);
	}
	private void setMode(Boolean isDirectory) {
		if (isDirectory) {
			lbl_Selected.setVisible(false);
			lbl_Size.setVisible(false);
		}
		else {
			setVariable();
		}
	}
	private void deleteAll() {
		getSelectedList();
		if(Workedlist == null) return;
		Workedlist.removeAll(Chosedlist);
		ListMusicPanel listmusicpanel = musicpanel.getMusicList();
		listmusicpanel.sortItems();
		listmusicpanel.revalidate();
		listmusicpanel.repaint();
		closeSelectMode();
	}
	private void closeSelectMode() {
		musicpanel.getMusicList().setSelectMode(false);
		this.setVisible(false);
		setSelectMode(false);
		setSelected(0);
	}
	private void getSelectedList() {
		Chosedlist = musicpanel.getMusicList().turnSelectedtoMusic();
	}
	private void setMergePlaylist(Settings setting) {
		SelectPlaylist selectPlaylist = new SelectPlaylist(setting);
		selectPlaylist.setVisible(true);
		closeSelectMode();
	}
	public int getSelected() {
		return selected;
	}
	public void setSelected(int selected) {
		this.selected = selected;
		lbl_Selected.setText(String.valueOf(selected));
	}
	public int getMax() {
		return max;
	}
	public void setMax(int max) {
		this.max = max;
	}
	public void setSelectMode(Boolean istrue) {
		pnl_Choices.setVisible(istrue);
		btn_Close.setVisible(istrue);
		lbl_Selected.setVisible(istrue);
		lbl_Size.setVisible(istrue);
		lbl_Slash.setVisible(istrue);
		this.isSelect = istrue;
	}
	public Boolean getSelectMode() {
		return isSelect;
	}
}
class IntegerLabel extends JLabel{
	/**
	 * 
	 */
	private static final long serialVersionUID = -3978184245000985974L;
	IntegerLabel(){
		
	}
	public IntegerLabel(String string) {
		// TODO Auto-generated constructor stub
		setText(string);
	}
	@Override
	public void setText(String text) {
		// TODO Auto-generated method stub
		try {
			Integer.parseInt(text);
			
		} catch (NumberFormatException e) {
			return;
		}
		String newString = null;
		if(text.length() < 2) {
			newString = "0"+text;
		}
		super.setText(text);
	}


	
}
