package userUI.information;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Iterator;

public class Musiclist implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1530676910997852633L;
	//this tell us which type of musiclist
	public static int DIRECTORY = 0, PLAYLIST = 1;
	private Integer id;
	private Boolean nullIDExist;
	private String name;
	private String logo;
	private ArrayList<String> directorylist = new ArrayList<>();
	private ArrayList<Playlist> musiclist = new ArrayList<>();
	public Musiclist(int id, String name, String logo){
		setID(id);
		setName(name);
		setLogo(logo);
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getLogo() {
		return logo;
	}
	public void setLogo(String logo) {
		this.logo = logo;
	}
	public void setID(int id) {
		if(this.id != null) return;
		this.id = id;
	}
	public int getId() {
		return id;
	}
	public void addDirectorylist(String directory) {
		directorylist.add(directory);
	}
	public void removeDirectorylist(String directory) {
		directorylist.remove(directory);
	}
	public ArrayList<String> getDirectorylist(){
		if(directorylist.size() == 0) return getStringlist();
		return directorylist;
	}
	public String getDirectory(int id){
		return directorylist.get(id);
	}
	
	public Boolean getNullIDExist() {
		return nullIDExist;
	}
	public void setNullIDExist(Boolean nullIDExist) {
		this.nullIDExist = nullIDExist;
	}
	public ArrayList<Playlist> getMusiclist() {
		return musiclist;
	}
	public void setMusiclist(ArrayList<Playlist> musiclist) {
		this.musiclist = musiclist;
	}
	private ArrayList<String> getStringlist(){
		ArrayList<String> stringlist = new ArrayList<>();
		for (int i = 0; i<musiclist.size(); i++) {
			String string = musiclist.get(i).toString();
			stringlist.add(string);
		}
		return stringlist;
	}
	public void addPlaylist(String name) {
		if(directorylist.size()>0) {
			System.out.println("directorylist var");
			return;
		}
		int newID = (musiclist.size()>0) ? musiclist.getLast().getId()+1 : 0;
		Playlist playlist = new Playlist(name, newID);
		musiclist.add(playlist);
	}
	public void removePlaylist(Playlist playlist) {
		musiclist.remove(playlist);
	}
}
