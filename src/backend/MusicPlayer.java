package backend;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.lang.ProcessHandle.Info;

import javax.sound.sampled.*;

import com.mpatric.mp3agic.InvalidDataException;
import com.mpatric.mp3agic.Mp3File;
import com.mpatric.mp3agic.UnsupportedTagException;

import javazoom.jl.converter.Converter;
import javazoom.jl.decoder.JavaLayerException;
import javazoom.jl.player.Player;
import userUI.information.Musicinfo;
import userUI.subFrames.PlayerError;

public class MusicPlayer {
	//it holds old wav file directory, with that we can remove old files.
	private String oldWav = null;
	private Player player = null;
	//it can play the file free from function play files
	public void play(Musicinfo info) {
		String directory = info.getDirectory();
		/*if(oldWav != null) {
			File f = new File(oldWav);
			f.delete();
		}
		String newDirectory = extensionConverter(directory, ".wav");*/
		File f = new File(directory);
		FileInputStream fis = null;
		try {
			 fis = new FileInputStream(f);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		BufferedInputStream bis = new BufferedInputStream(fis);
		try {
			player = new Player(bis);
		} catch (JavaLayerException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		new Thread(){
			  public void run(){
					try {
						player.play();
					} catch (JavaLayerException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
			  }
			}.start();
	}
	private static String extensionConverter(String directory, String newExtension) {
		int local[] = StringEditor.lastCharLocation(directory, '.', 1);
		//local[1] give us location of . staring point
		String newDirectory = directory.substring(0,local[1]) + newExtension;
		Converter converter = new Converter();
		try {
			converter.convert(directory,newDirectory);
		} catch (JavaLayerException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return newDirectory;
	}
	//show the file info 
	public static void fileRead(String Directory) {
		Mp3File mp3file = null;
		try {
			mp3file = new Mp3File(Directory);
		} catch (UnsupportedTagException | InvalidDataException | IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println("Length of this mp3 is: " + mp3file.getLengthInSeconds() + " seconds");
		System.out.println("Bitrate: " + mp3file.getBitrate() + " kbps " + (mp3file.isVbr() ? "(VBR)" : "(CBR)"));
		System.out.println("Sample rate: " + mp3file.getSampleRate() + " Hz");
		System.out.println("Has ID3v1 tag?: " + (mp3file.hasId3v1Tag() ? "YES" : "NO"));
		System.out.println("Has ID3v2 tag?: " + (mp3file.hasId3v2Tag() ? "YES" : "NO"));
		System.out.println("Has custom tag?: " + (mp3file.hasCustomTag() ? "YES" : "NO"));
	}
}

