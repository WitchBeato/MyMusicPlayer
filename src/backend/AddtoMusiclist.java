package backend;

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
