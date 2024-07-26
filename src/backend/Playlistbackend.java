package backend;

import java.io.File;

import userUI.information.Musicinfo;
import userUI.information.Playlist;

public class Playlistbackend {
	public static Playlist Directorytoplaylist(String directory) {
		Playlist fakeplaylist = new Playlist(directory, 0);
		File directoryfile = new File(directory);
		File[] filelist = directoryfile.listFiles();
		for (File file : filelist) {
			int size = fakeplaylist.getList().size();
			Musicinfo info = new Musicinfo(size, file.getAbsolutePath());
			if(info.getIdInteger() == null) continue;
			fakeplaylist.addtoList(info);
		}
		return fakeplaylist;
		
		
	}
}
