package userUI.information;

import java.util.ArrayList;

public class Musiclist {
	private Integer id;
	private String name;
	private String logo;
	private ArrayList<String> directorylist = new ArrayList<>();
	private ArrayList<Musicinfo> musiclist = new ArrayList<>();
	Musiclist(int id, String name, String logo){
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
	public ArrayList<Musicinfo> getMusic() {
		return musiclist;
	}
	public void addMusic(Musicinfo music) {
		musiclist.add(music);
	}
}
