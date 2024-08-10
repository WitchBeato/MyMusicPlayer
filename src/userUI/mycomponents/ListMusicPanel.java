package userUI.mycomponents;

import javax.swing.JLayeredPane;
import javax.swing.JPanel;
import javax.swing.JPopupMenu;

import staticinfo.PlaylistComparators;
import userUI.MainFrame;
import userUI.information.Musicinfo;
import userUI.information.Playlist;

import java.awt.Component;
import java.awt.Dimension;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.awt.FlowLayout;

public class ListMusicPanel extends JLayeredPane {

	private static final long serialVersionUID = 1L;
	private JLayeredPane me = this;
	private int column, row = 1;
	private Playlist playlist;
	private ArrayList<Musicinfo> list = null;
	private ArrayList<Musicinfo> selectedMusic = new ArrayList<>();
	private boolean isDirectory, isSelectMode;
	private MainFrame frame;
	
	//this panel do when panel resized list music again to fit on size
	public ListMusicPanel(Playlist playlist, boolean isDirectory,MainFrame mainFrame) {
		this.playlist = playlist;
		this.list = playlist.getList();
		playlistinit(isDirectory, mainFrame);
		//setSelectMode(true);
	}
	public ListMusicPanel(ArrayList<Musicinfo> list, boolean isDirectory,MainFrame mainFrame) {
		this.list = list;
		playlistinit(isDirectory, mainFrame);
	}
	private void playlistinit(boolean isDirectory,MainFrame mainFrame) {
		Collections.sort(list, PlaylistComparators.compareNames);
		this.isDirectory = isDirectory;
		this.frame = mainFrame;
		init();
		addGlobalListeners();
		sortItems();
	}
	public ListMusicPanel() {
		init();
	}
	private void init() {
		setBounds(0, 0, 635, 369);
		setLayout(new FlowLayout(FlowLayout.LEADING, 0, 0));
		
	}
	private void addGlobalListeners() {
		addComponentListener(new ComponentAdapter() {
			@Override
			public void componentResized(ComponentEvent e) {
				Dimension titlesize = SelectTitle.SELECTSIZE;
				if(me.getWidth() % (titlesize.width+20) == 0) { 
					sortItems();
				}
			}
		});
	}
	public void sortItems() {
		Dimension titlesize = SelectTitle.SELECTSIZE;
		adjustColumnRow(titlesize);
		addMusics();
		setSelectTitleMode(isSelectMode);
	}
	private void adjustColumnRow(Dimension titlesize) {
		if(titlesize.width == 0) return;
		column = me.getWidth() / titlesize.width ;
		if(list == null) return;
		row = (list.size() % column == 0) ? (list.size() / column) : (list.size() / column)+1;
	}
	private void addMusics() {
		me.removeAll();
		int lastindex = list.size()-1;
		for (int i = 0; i < row; i++) {
			int local = 129 * i;
			for (int j = 0; j < column; j++) {
				int id = column*i + j;
				if(id>lastindex) break;
				SelectTitle title = new SelectTitle(list.get(id),isDirectory,this);
				title.setLocation(0, local);
				me.add(title);
			}
			
		}
	}
	public void setlist(ArrayList<Musicinfo> list) {
		this.list = list;
	}
	public void setIsDirectory(Boolean isDirectory) {
		this.isDirectory = isDirectory;
		sortItems();
	}
	public MainFrame getMainFrame() {
		return frame;
	}
	public Playlist getPlaylist() {
		return playlist;
	}
	public void sortIt(Comparator<Musicinfo> id) {
		Collections.sort(list, id);
		sortItems();
	}
	public Playlist getPlayList(){
		if(playlist == null) return null;
		return playlist;
	}
	public boolean getSelectMode() {
		return isSelectMode;
	}
	public void setSelectMode(boolean isSelectMode) {
		this.isSelectMode = isSelectMode;
		frame.setSortPanelVisible(isSelectMode);
		setSelectTitleMode(isSelectMode);
	}
	public MainFrame getFrame() {
		return frame;
	}
	private void setSelectTitleMode(Boolean isSelectMode) {
		Component componentList[] = this.getComponents();
		for (int i = 0; i < componentList.length; i++) {
			Component component = componentList[i];
			if(component instanceof SelectTitle) {
				SelectTitle title = (SelectTitle)component;
				//title.debugMessage();
				title.setSelectMode(isSelectMode);
				title.repaint();
				System.out.println(title.getSelectMode());
			}
		}
	}
	public ArrayList<Musicinfo> turnSelectedtoMusic() {
		ArrayList<Musicinfo> selectedMusic = new ArrayList<>();
		for (Component component : this.getComponents()) {
			if(component instanceof SelectTitle) {
				SelectTitle title = (SelectTitle) component;
				if(title.getisSelected())selectedMusic.add(title.getMusic());
			}
		}
		return selectedMusic;
	}
	

}
