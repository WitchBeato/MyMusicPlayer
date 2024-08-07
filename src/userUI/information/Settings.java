package userUI.information;

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
	private Playlist lastListened = new Playlist("Last listened", -1) {
		@Override
		public void addtoList(Musicinfo info) {
			if(this.getList().size()>20)return;
			super.addtoList(info);
			
		};
	};
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
	public Playlist getLastListened() {
		return lastListened;
	}
	public void setLastListened(Playlist lastListened) {
		this.lastListened = lastListened;
	}
	public Boolean saveLastlistened() {
		String lastListened = Dtr.getDataDirectory() + StaticNames.lastListenedName;
		try {
			saveObject(lastListened, musiclist);
			return true;
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return false;
	}
	public void openPlaylist() {
		String lastListeneddtr = Dtr.getDataDirectory() + StaticNames.lastListenedName;
		lastListened = (Playlist) openObject(lastListeneddtr);
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
