package backend;

import java.io.File;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;

import javax.swing.JFileChooser;
import javax.swing.JTree;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.tree.TreeSelectionModel;

import com.privatejgoodies.forms.util.FormUtils;

import staticinfo.SupportedTypes;

public class StringEditor {
	//it found last iteratives of letter according to qualitive
	public static int[] lastCharLocation(String text, char letter, int qualitive) {
		int[] location = new int[2];
		int counter = 0;
		for (int i = text.length()-1; i > 0; i--) {
			char c = text.charAt(i);
			if(c == letter) {
				if(counter == 0) location[0] = i;
				counter++;
				if(counter == qualitive && counter != 1) {
					location[1] = i;
					break;
				}
			}
		}
		return location;	
	}
	public static String giveTimeinSecond(int second) {
		int hour = second / 36000;
		int minute = second / 60  - (hour * 60);
		second = second % 60;
		String textHour = (hour != 0) ? hour + ":" : "";
		String textMinute = turnNumberToText(minute);
		String textSecond = turnNumberToText(second);
		String text = textHour + textMinute + ":" + textSecond;
		return text;
	}
	private static String turnNumberToText(int value) {
		if(value <10) return "0"+value;
		else return String.valueOf(value);
	}
	public static String giveStringforIterative(String text, char letter, int qualitive) {
		int[] location = lastCharLocation(text, letter, qualitive);
		String newText = (qualitive != 1) ?
				text.substring(location[1], location[0]) : 
				text.substring(location[0], text.length());
		return newText;
		
	}
	public static String giveExtension(String text) {
		return giveStringforIterative(text, '.', 1);
	}

	//give me reverse of the string
	public static String reverseString(String input){
        byte[] strAsByteArray = input.getBytes();

        byte[] result = new byte[strAsByteArray.length];

        for (int i = 0; i < strAsByteArray.length; i++)
            result[i] = strAsByteArray[strAsByteArray.length - i - 1];

       return new String(result);
    }	
	public static String selectFolder() {
	    int result;
        
	    JFileChooser chooser = new JFileChooser(); 
	    chooser.setCurrentDirectory(new java.io.File("."));
	    chooser.setDialogTitle("please select a path");
	    chooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
	    //
	    // disable the "All files" option.
	    //
	    chooser.setAcceptAllFileFilterUsed(false);
	    result = chooser.showOpenDialog(chooser);
	    //    
	    String directory = (result == JFileChooser.APPROVE_OPTION) ? 
	    		chooser.getSelectedFile().getAbsolutePath() :
	    		null;	
	    return directory;
	}
	public static String selectFile(SupportedTypes type) {
		int result;
		FileNameExtensionFilter extFilter = new FileNameExtensionFilter(
				type.getExplanationwithExtension(), 
				type.getExtensions());
		JFileChooser chooser = new JFileChooser();
		chooser.setFileFilter(extFilter);
		result = chooser.showOpenDialog(null);
		if(result == JFileChooser.APPROVE_OPTION) {
			return chooser.getSelectedFile().getAbsolutePath();
		}
		return null;
		
	}
	public static String readTextFile(String Directory) {
		Path path = Paths.get(Directory);
	    try {
			return Files.readString(path,  Charset.defaultCharset());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			return null;
		}
	}
}
