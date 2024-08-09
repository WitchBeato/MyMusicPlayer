package userUI.information;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;



public class Playlist implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private int id;
	private String name;
	private ArrayList<Musicinfo> list;
	//String:Directory, musicinfo: an element from list 
	private HashMap<String, Musicinfo> listSearch = new HashMap<>();
	//int:ID, Musicinfo an element from list
	private HashMap<Integer, Musicinfo> listIDSearch = new HashMap<>();
	public Playlist(String name, int id) {
		this.id = id;
		this.name = name;
		list = new ArrayList<>();
	}
	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return name;
	}
	public ArrayList<Musicinfo> getList() {
		return list;
	}
	public void setList(ArrayList<Musicinfo> list) {
		this.list = list;
	}
	public void addtoList(Musicinfo info) {
		String directory = info.getDirectory();
		if(listSearch.containsKey(directory)) return;
		try {
			if(listIDSearch.containsKey(info.getId())) info.setId(list.size());
		} catch (NullPointerException e) {
			info.setId(0);
		}

		list.add(info);
		listSearch.put(directory, info);
		try {
			listIDSearch.put(info.getId(), info);
		} catch (NullPointerException e) {
			listIDSearch = new HashMap<>();
			listIDSearch.put(info.getId(), info);
		}

	}
	public void removeList(Musicinfo info) {
		int id = info.getId();
		if(!(list.get(id) == info)) return;
		list.remove(id);
		listSearch.remove(info.getDirectory());
		listIDSearch.remove(info.getId());
		if(list.size() == 0) return;
		Musicinfo last = list.getLast();
		int lastID = last.getId();
		if(lastID < id) return;
		listIDSearch.remove(lastID);
		last.setId(id);	
		addtoList(last);
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getName() {
		return name;
	}
	public int getId() {
		return id;
	}
	public void mergePlaylist(Playlist another) {
		list.addAll(another.getList());
	}
	public void mergePlaylist(ArrayList<Musicinfo> another) {
		list.addAll(another);
	}
	
	public void removeAll(ArrayList<Musicinfo> difList) {
		for (Musicinfo musicinfo : difList) {
			this.removeList(musicinfo);
		}
	}
	//this method take all item from this playlist to newPlaylist
	public void movefromPlaylist(ArrayList<Musicinfo> diflist, Playlist newPlaylist) {
		if(newPlaylist == null) return;
		removeAll(diflist);
		newPlaylist.mergePlaylist(diflist);
	}
	public Boolean isMusicInfoExist(String directory) {
		if(listSearch.get(directory) != null) return true;
		else return false;
	}

}
