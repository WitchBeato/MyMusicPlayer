package userUI.information;

import java.io.Serializable;
import java.util.ArrayList;

import backend.StringEditor;
import staticinfo.SupportedTypes;

public class Musicinfo implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	//if id is null that will mean i wont added to playlist.
	private Integer id = null;
	private String directory;
	private String name;
	private String singer;
	private String date;
	private int time;
	public Musicinfo(int id, String directory){
		String extension = directory.length() > 4 ? directory.substring(directory.length() - 4) : directory;
		if(!SupportedTypes.checkType(SupportedTypes.audioExt, extension)) return;
		this.id = id;
		setDirectory(directory);
	}
	public String getDirectory() {
		return directory;
	}
	public void setDirectory(String directory) {
		this.directory = directory;
		String preName = StringEditor.giveStringforIterative(directory, '\\', 1);
		setName(preName);
		
	}
	public int getId() {
		return id;
	}
	public Integer getIdInteger() {
		return id;
	}
	public void setId(int id) {
		if(this.id != null) return;
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		String extension =  StringEditor.giveStringforIterative(name, '.', 1);
		int size = extension.length();
		name = name.substring(1, name.length()-size-1);
		this.name = name;
	}
	public String getSinger() {
		return singer;
	}
	public void setSinger(String singer) {
		this.singer = singer;
	}
	public String getDate() {
		return date;
	}
	public void setDate(String date) {
		this.date = date;
	}
	public int getTime() {
		return time;
	}
	public void setTime(int time) {
		this.time = time;
	}
}