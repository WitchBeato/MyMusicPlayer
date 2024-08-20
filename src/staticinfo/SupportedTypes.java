
package staticinfo;

import java.util.Iterator;

public class SupportedTypes {
	//it work for checking supported file types and getting them automatically.
	private String explanation;
	private String[] extensions;
	public SupportedTypes(String explanation, String[] extension) {
		// TODO Auto-generated constructor stub
		setExplanation(explanation);
		setExtensions(extension);
	}
	public static SupportedTypes audioExt = new SupportedTypes(
			"Audio files",
			new String[] {"mp3"});
	public static SupportedTypes photoExt = new SupportedTypes(
			"Image files", 
			new String[]{"png","jpg","jpeg"});
	public static Boolean checkType(SupportedTypes types, String extension) {
	    for (String string : types.getExtensions()) {
			if(string.equals(extension)) return true;
		}
	    return false;
	}
	public String getExplanation() {
		return explanation;
	}
	public String getExplanationwithExtension() {
		String newexplanation = explanation  + sortExtension();
		return newexplanation;
	}
	public void setExplanation(String explanation) {
		this.explanation = explanation;
	}
	public String[] getExtensions() {
		return extensions;
	}
	public void setExtensions(String[] extensions) {
		this.extensions = extensions;
	}
	public String sortExtension() {
		String text = "{";
		for (String string : extensions) {
			text += string + " ";
		}
		text += "}";
		return text;
	}
}
