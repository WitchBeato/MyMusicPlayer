package userUI.information;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Iterator;



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
	//this method add musicinfo to our playlist
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
		if(id < 0) return;
		list.remove(info);
		removeSearches(info);
		if(list.size() == 0) return;
	}
	//this method created for all music
	public void removeListAllMusic(Musicinfo info) {
		list.remove(info);
		removeSearches(info);
		if(list.size() == 0) return;
		replaceMusic(info.getId());
	}
	private void removeSearches(Musicinfo info) {
		listSearch.remove(info.getDirectory());
		listIDSearch.remove(info.getId());
	}
	private void replaceMusic(int id) {
		Musicinfo last = list.getLast();
		int lastID = last.getId();
		if(lastID < id) return;
		listIDSearch.remove(lastID);
		last.setId(id);	
		addtoList(last);
	}
	//this method check database errors and fix it for good usage
	public void checkandFixDatabase() {
		if(list.size() == listIDSearch.size() && listIDSearch.size() == listSearch.size()) 
			return;
		for (Musicinfo musicinfo : list) {
			String directory = musicinfo.getDirectory();
			int ID = musicinfo.getId();
			if(!listSearch.containsKey(directory)) {
				listSearch.put(directory, musicinfo);
			}
			if(!listIDSearch.containsKey(ID)) {
				listIDSearch.put(ID, musicinfo);
			}
		}
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
		ArrayList<Musicinfo> musicInfoList = another.getList();
		addOneByOne(musicInfoList);
	}
	public void mergePlaylist(ArrayList<Musicinfo> another) {
		addOneByOne(another);
	}
	private void addOneByOne(ArrayList<Musicinfo> another) {
		checkandFixDatabase();
		for (Musicinfo musicinfo : another) {
			addtoList(musicinfo);
		}
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
