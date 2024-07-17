package directories;

import java.io.File;

public class Imagedtr {
	private static String local = System.getProperty("user.dir") + "\\project management\\img\\";
	static public String sound = local+ "sound.png", soundlvl1 = local+"sound+.png", 
			soundlvl2 = local+"sound++.png",
			soundlvl3 = local+"sound+++.png",soundno = local+"nosound.png";
	static public String play = local+"continue.png", stop = local+"stop.png",  
			prev = local+"prev.png", onward = local+"onward.png";
	static public String arrowup = local+"arrowup.png",arrowdown = local+"arrowdown.png";
	static public String folder = local+"folder.png", playlist = local+"playlist.png";
}
