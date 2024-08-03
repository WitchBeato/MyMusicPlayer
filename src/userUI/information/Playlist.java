package userUI.information;

import java.io.Serializable;
import java.util.ArrayList;
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
		list.add(info);
		listSearch.put(directory, info);
	}
	public void removeList(Musicinfo info) {
		int id = info.getId();
		list.remove(id);
		listSearch.remove(info.getDirectory());
		Musicinfo last = list.getLast();
		last.setId(id);
		list.add(last);
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
}
