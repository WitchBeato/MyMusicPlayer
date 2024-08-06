package userUI.subFrames;

import javax.swing.JPanel;
import javax.swing.SpringLayout;

import staticinfo.PlaylistComparators;
import userUI.information.Musicinfo;
import userUI.information.Playlist;
import userUI.mycomponents.ListMusicPanel;

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

public class SortMusics extends JPanel {
	private JComboBox cmb_Sort;
	private static final long serialVersionUID = 1L;
	private int Selected = 0, max = 0;
	private IntegerLabel lbl_Selected,lbl_Size;
	/**
	 * Create the panel.
	 */
	private ListMusicPanel musicpanel;
	private Playlist Workedlist;
	private ArrayList<Musicinfo> Chosedlist;
	private JLabel lbl_Slash;
	private JLabel lbl_Message;
	public SortMusics(ListMusicPanel musicpanel,Boolean isDirectory) {
		this.musicpanel = musicpanel;
		Workedlist = musicpanel.getPlayList();
	    init();
		setAllItems();
		setMode(isDirectory);

	}
	public SortMusics() {
       init();
	}
	private void init() {
		SpringLayout springLayout = new SpringLayout();
		setLayout(springLayout);
		
		cmb_Sort = new JComboBox();
		cmb_Sort.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent e) {
				if(e.getStateChange() != ItemEvent.SELECTED) return;
				Comparator<Musicinfo> item = (Comparator<Musicinfo>) cmb_Sort.getSelectedItem();
				if(item == null) return;
				doSort(item);
			}
		});
		springLayout.putConstraint(SpringLayout.NORTH, cmb_Sort, 10, SpringLayout.NORTH, this);
		springLayout.putConstraint(SpringLayout.WEST, cmb_Sort, 10, SpringLayout.WEST, this);
		springLayout.putConstraint(SpringLayout.SOUTH, cmb_Sort, 36, SpringLayout.NORTH, this);
		springLayout.putConstraint(SpringLayout.EAST, cmb_Sort, 311, SpringLayout.WEST, this);
		add(cmb_Sort);
		
		lbl_Selected = new IntegerLabel("00");
		springLayout.putConstraint(SpringLayout.NORTH, lbl_Selected, 1, SpringLayout.NORTH, cmb_Sort);
		springLayout.putConstraint(SpringLayout.WEST, lbl_Selected, 6, SpringLayout.EAST, cmb_Sort);
		springLayout.putConstraint(SpringLayout.EAST, lbl_Selected, 55, SpringLayout.EAST, cmb_Sort);
		lbl_Selected.setHorizontalAlignment(SwingConstants.CENTER);
		lbl_Selected.setFont(new Font("Tahoma", Font.PLAIN, 16));
		add(lbl_Selected);
		
		lbl_Slash = new JLabel("/");
		springLayout.putConstraint(SpringLayout.NORTH, lbl_Slash, -2, SpringLayout.NORTH, cmb_Sort);
		springLayout.putConstraint(SpringLayout.WEST, lbl_Slash, 6, SpringLayout.EAST, lbl_Selected);
		springLayout.putConstraint(SpringLayout.SOUTH, lbl_Slash, -2, SpringLayout.SOUTH, cmb_Sort);
		springLayout.putConstraint(SpringLayout.EAST, lbl_Slash, -237, SpringLayout.EAST, this);
		lbl_Slash.setHorizontalAlignment(SwingConstants.CENTER);
		lbl_Slash.setFont(new Font("Tahoma", Font.PLAIN, 16));
		add(lbl_Slash);
		
		lbl_Size = new IntegerLabel("00");
		springLayout.putConstraint(SpringLayout.NORTH, lbl_Size, 1, SpringLayout.NORTH, cmb_Sort);
		springLayout.putConstraint(SpringLayout.WEST, lbl_Size, 6, SpringLayout.EAST, lbl_Slash);
		springLayout.putConstraint(SpringLayout.EAST, lbl_Size, -182, SpringLayout.EAST, this);
		lbl_Size.setHorizontalAlignment(SwingConstants.CENTER);
		lbl_Size.setFont(new Font("Tahoma", Font.PLAIN, 16));
		add(lbl_Size);
		
		lbl_Message = new JLabel("Selected");
		springLayout.putConstraint(SpringLayout.NORTH, lbl_Message, 1, SpringLayout.NORTH, cmb_Sort);
		springLayout.putConstraint(SpringLayout.WEST, lbl_Message, 6, SpringLayout.EAST, lbl_Size);
		springLayout.putConstraint(SpringLayout.EAST, lbl_Message, -100, SpringLayout.EAST, this);
		lbl_Message.setHorizontalAlignment(SwingConstants.CENTER);
		lbl_Message.setFont(new Font("Tahoma", Font.PLAIN, 16));
		add(lbl_Message);
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
			lbl_Selected.setText(String.valueOf(Selected));
		} catch (NullPointerException e) {
			// TODO: handle exception
			return;
		}
		
	}
	private void doSort(Comparator<Musicinfo> id) {
		if(musicpanel == null ) return;
		musicpanel.sortIt(id);
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
	public int getSelected() {
		return Selected;
	}
	public void setSelected(int selected) {
		Selected = selected;
		lbl_Selected.setText(String.valueOf(selected));
	}
	public int getMax() {
		return max;
	}
	public void setMax(int max) {
		this.max = max;
	}
}
class IntegerLabel extends JLabel{
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
