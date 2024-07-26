package userUI.information;

import java.util.ArrayList;

public class Playlist {
	private int id;
	private String name;
	private ArrayList<Musicinfo> list;
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
		list.add(info);
	}
	public void removeList(Musicinfo info) {
		list.remove(info);
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
}
