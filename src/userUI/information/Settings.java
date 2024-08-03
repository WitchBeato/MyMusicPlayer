package userUI.information;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

import staticinfo.Dtr;
import staticinfo.StaticNames;

public class Settings {
	public static Boolean DEBUG_MODE = true;
	private Musiclist musiclist[] = new Musiclist[2];
	public void saveAll() {
		saveMusiclist();
	}
	public Musiclist[] getMusiclistArray() {
		if(musiclist == null) return null;
		return musiclist;
	}
	public Musiclist getMusiclist(int id) {
		if(musiclist == null) return null;
		return musiclist[id];
	}
	public void setMusiclist(Musiclist[] musiclist) {
		this.musiclist = musiclist;
	}
	public Boolean saveMusiclist() {
		String MusicDirectory = Dtr.getDataDirectory() + StaticNames.musiclistName;
		try {
			saveObject(MusicDirectory, musiclist);
			return true;
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return false;
	}
	public void openMusiclist() {
		String MusicDirectory = Dtr.getDataDirectory() + StaticNames.musiclistName;
		musiclist = (Musiclist[]) openObject(MusicDirectory);
	}
	private void saveObject(String directory, Object data) throws FileNotFoundException {
	    ObjectOutputStream oos = null;
	    FileOutputStream fos = null;
		try {
			fos = new FileOutputStream(directory);
			oos = new ObjectOutputStream(fos);
			oos.writeObject(data);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	    try {
			oos.close();
			fos.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	private Object openObject(String directory) {
		FileInputStream fis = null;
		ObjectInputStream ois = null;
		Object object = null;
		try {
			fis = new FileInputStream(directory);
			ois = new ObjectInputStream(fis);
			object = ois.readObject();
		} catch (IOException | ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			fis.close();
			ois.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return object;
	}
	
}
