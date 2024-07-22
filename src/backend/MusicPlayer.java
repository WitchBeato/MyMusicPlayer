package backend;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

import com.mpatric.mp3agic.InvalidDataException;
import com.mpatric.mp3agic.Mp3File;
import com.mpatric.mp3agic.UnsupportedTagException;

import javazoom.jl.decoder.JavaLayerException;
import javazoom.jl.player.Player;
import userUI.information.Musicinfo;

public class MusicPlayer {
	//main Instance of app
	private Player player = null;
	private boolean isPlay = false;
	private MusicplayerRunnable runnable;
	//it can play the file free from function play files
	public void play(Musicinfo info) {
		String directory = info.getDirectory();
		File file = new File(directory);
		checkPrevious();
		reset();
		getInputStream(file);
		start();
		reverseisPlay();
	}
	public void playerclose() {
		if(player == null) return;
		player.close();
	}
	public void playerwait() {
	    if (player == null) return;
	    if(isPlay)runnable.stopPlayer();
	    else runnable.continuePlayer();
	    
        reverseisPlay();
	}
	public void startAt(int time) {
		try {
			player.play(time);
		} catch (JavaLayerException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
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
	private void getInputStream(File file) {
		FileInputStream fis = null;
		try {
			 fis = new FileInputStream(file);
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
	}
	private void checkPrevious() {
		if(player != null) playerclose();
	}
	private void start() {
		runnable = new MusicplayerRunnable(player);
		Thread thread = new Thread(runnable);
		thread.start();
	}

	private void reset() {
		isPlay = false;
		//thread = null;
	}
	public boolean getisPlay() {
		return isPlay;
	}
	public void reverseisPlay() {
		this.isPlay = !isPlay;
	}

}

