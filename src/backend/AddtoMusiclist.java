package backend;

import java.io.File;

import userUI.information.Musicinfo;
import userUI.information.Musiclist;
import userUI.information.Playlist;

public class AddtoMusiclist {
	//these method made for add and remove functions more easier and understable
	public static void addtoMusiclist(Musiclist musiclist, String text) {
		if(musiclist.getId() == Musiclist.DIRECTORY) addDirectory(musiclist,text);
		else addPlaylist(musiclist, text);
	}
	public static void removefromMusiclist(Musiclist musiclist, Object playlistorText) {
		if(musiclist.getId() == Musiclist.DIRECTORY && playlistorText instanceof String) {
			removeDirectory(musiclist,(String)playlistorText);
		}
		else if(musiclist.getId() == Musiclist.PLAYLIST && playlistorText instanceof Playlist) {
			removePlaylist(musiclist, (Playlist)playlistorText);
		}
	}
	public static String addMusicfromDirectory(Playlist playlist, String directory) {
		File files[] = readAllFiles(directory);
		int size = 0;
		for (File file : files) {
			Boolean isAdded = addMusicwithDirectory(playlist, directory);
			if(isAdded) size++;
		}
		return Integer.toString(size);
	}
	public static Boolean addMusicwithDirectory(Playlist playlist, String musicdirectory) {
		int size = playlist.getList().size();
		Musicinfo info = new Musicinfo(size, musicdirectory);
		return addMusicwithInfo(playlist, info);

	}
	public static Boolean addMusicwithInfo(Playlist playlist, Musicinfo info) {
		if(info.getIdInteger() == null) return false;
		else {
			playlist.addtoList(info);
			return true;
		}

	}
	public static File[] readAllFiles(String directory) {
		File file = new File(directory);
		File[] files = file.listFiles();
		return files;
	}
	private static void addPlaylist(Musiclist musiclist, String text) {
		musiclist.addPlaylist(text);
	}
	private static void addDirectory(Musiclist musiclist, String directory) {
		musiclist.addDirectorylist(directory);
	}
	private static void removeDirectory(Musiclist musiclist, String directory) {
		musiclist.removeDirectorylist(directory);
	}
	private static void removePlaylist(Musiclist musiclist, Playlist playlist) {
		musiclist.removePlaylist(playlist);
	}
}
