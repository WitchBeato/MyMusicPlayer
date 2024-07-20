package userUI.mycomponents;

import javax.swing.JPanel;

import userUI.information.Musicinfo;

import java.awt.Dimension;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.util.ArrayList;
import java.util.Iterator;
import java.awt.FlowLayout;

public class ListMusicPanel extends JPanel {

	private static final long serialVersionUID = 1L;
	private JPanel me = this;
	private int column, row = 1;
	private ArrayList<Musicinfo> list = null;
	private boolean isDirectory;
	
	//this panel do when panel resized list music again to fit on size
	public ListMusicPanel(ArrayList<Musicinfo> list, boolean isDirectory) {
		this.list = list;
		this.isDirectory = isDirectory;
		init();
		addGlobalListeners();
		adjustColumnRow(SelectTitle.SELECTSIZE);
		addMusics();
	}
	public ListMusicPanel() {
		init();
	}
	private void init() {
		setBounds(0, 0, 635, 369);
		setLayout(new FlowLayout(FlowLayout.LEADING, 5, 5));
		
	}
	private void addGlobalListeners() {
		addComponentListener(new ComponentAdapter() {
			@Override
			public void componentResized(ComponentEvent e) {
				
				Dimension titlesize = SelectTitle.SELECTSIZE;
				if(me.getWidth() % (titlesize.width+20) == 0) { 
				adjustColumnRow(titlesize);
				addMusics();
				}
			}
		});
	}
	private void adjustColumnRow(Dimension titlesize) {
		column = me.getWidth() / titlesize.width ;
		if(list == null) return;
		//row =  (list.size() / column)+1;
		row = (list.size() % column == 0) ? (list.size() / column) : (list.size() / column)+1;
	}
	private void addMusics() {
		me.removeAll();
		int lastindex = list.size()-1;
		for (int i = 0; i < row; i++) {
			MusicColumn myColumn = new MusicColumn();
			myColumn.setSize(new Dimension());
			for (int j = 0; j < column; j++) {
				int id = column*i + j;
				if(id>lastindex) break;
				SelectTitle title = new SelectTitle(list.get(id),isDirectory);
				myColumn.add(title);
			}
			me.add(myColumn);
			
		}
	}

}
