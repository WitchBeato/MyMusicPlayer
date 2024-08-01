package staticinfo;

import java.io.File;

public class Dtr {
	/* this method make us reach directories more easily,
	 * maybe you can see legacy version as Imagedtr 
	 */
	private final static String local = System.getProperty("user.dir") + "\\project management";
	private final static String imagedirectory = "\\img\\";
	/*
	 *  this method create directory and contol it is exist,
	 *  if it exist return directory or return another image file as b plan
	 */
	public static String getImage(String imagefile) {
		String directory = local + imagedirectory + imagefile;
		directory = isExist(directory);
		if(directory == null) directory = Imagedtr.question;
		return directory;

	}
	
	private static String isExist(String directory) {
		File file = new File(directory);
		if(file.exists()) return directory;
		else {
			if(Settings.DEBUG_MODE) System.out.println("file doesnt exist babe");
			return null;
		}
	}
}
