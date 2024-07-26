package backend;

import userUI.information.Musiclist;

public class AddtoMusiclist {
	public static void addtoMusiclist(Musiclist musiclist, String text) {
		if(musiclist.getId() == Musiclist.DIRECTORY) addDirectory(musiclist,text);
		else addPlaylist(musiclist, text);
	}
	private static void addPlaylist(Musiclist musiclist, String text) {
		musiclist.addPlaylist(text);
	}
	private static void addDirectory(Musiclist musiclist, String directory) {
		musiclist.addDirectorylist(directory);
	}
}
