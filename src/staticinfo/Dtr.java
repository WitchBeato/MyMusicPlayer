package staticinfo;

import java.io.File;

import javax.swing.ImageIcon;

import net.coobird.thumbnailator.util.exif.IfdStructure;
import userUI.information.Settings;

public class Dtr {
	/* this method make us reach directories more easily,
	 * maybe you can see legacy version as Imagedtr 
	 */
	
	private final static String local = StaticNames.local;
	public final static ImageIcon logo = new ImageIcon(Dtr.getImage("Musicicon.png"));
	private final static String imageDirectory = "\\img\\";
	private final static String dataDirectory = "\\data\\";
	/*
	 *  this method create directory and contol it is exist,
	 *  if it exist return directory or return another image file as b plan
	 */
	public static String getImage(String imagefile) {
		String directory = local + imageDirectory + imagefile;
		directory = isExist(directory);
		if(directory == null) directory = Imagedtr.question;
		return directory;

	}
	public static File getData(String dataName) {
		String directory = getDataDirectory() + dataName;
		directory = isExist(directory);
		if(directory == null) return null;
		else return new File(directory);

	}
	public static String getDataDirectory() {
		return local + dataDirectory;
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
