package staticinfo;

import userUI.information.Settings;

public class StaticNames {
	
	private static String extUniversal = ".dat";
	public static String local;
	static{
		if(Settings.DEBUG_MODE) local = System.getProperty("user.dir") + "\\project management";
		else local = System.getProperty("user.dir");
	}
	public static String musiclistName = "musiclist"+extUniversal;
	public static String lastListenedName = "lastlisten"+extUniversal;
}
