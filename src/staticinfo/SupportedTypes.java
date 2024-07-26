
package staticinfo;

import java.util.Iterator;

public class SupportedTypes {
	//it work for checking supported file types and getting them automatically.
	public static String audioExt[] = {".mp3"};
	public static String photoExt[] = {".png",".jpg",".jpeg"};
	public static Boolean checkType(String extSupported[], String extension) {
	    for (String string : extSupported) {
			if(string.equals(extension)) return true;
		}
	    return false;
	}
}
