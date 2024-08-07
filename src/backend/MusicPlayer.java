package backend;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;

import com.mpatric.mp3agic.InvalidDataException;
import com.mpatric.mp3agic.Mp3File;
import com.mpatric.mp3agic.UnsupportedTagException;

import javazoom.jl.decoder.JavaLayerException;
import javazoom.jl.player.Player;
import userUI.information.Musicinfo;
import userUI.information.Playlist;

public class MusicPlayer {
	//main Instance of app
	private Player player = null;
	private boolean isPlay = false;
	private MusicplayerRunnable runnable;
	private ArrayList<Musicinfo> list;
	private Musicinfo info;
	private static Object lock = new  Object();
	//it can play the file free from function play files
	public void play(Musicinfo info,int second) {
		String directory = info.getDirectory();
		File file = new File(directory);
		setInfo(info);
		checkPrevious();
		reset();
		getInputStream(file,second);
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
public boolean getisPlay() {
	return isPlay;
}
public void reverseisPlay() {
	this.isPlay = !isPlay;
}
	//show the file info 
	public static void fileRead(String Directory) {
		Mp3File mp3file = createMP3FILE(Directory);
		System.out.println("Length of this mp3 is: " + mp3file.getLengthInSeconds() + " seconds");
		System.out.println("Bitrate: " + mp3file.getBitrate() + " kbps " + (mp3file.isVbr() ? "(VBR)" : "(CBR)"));
		System.out.println("Sample rate: " + mp3file.getSampleRate() + " Hz");
		System.out.println("Has ID3v1 tag?: " + (mp3file.hasId3v1Tag() ? "YES" : "NO"));
		System.out.println("Has ID3v2 tag?: " + (mp3file.hasId3v2Tag() ? "YES" : "NO"));
		System.out.println("Has custom tag?: " + (mp3file.hasCustomTag() ? "YES" : "NO"));
	}
	public static int giveLenght(String directory) {
		Mp3File mp3file = createMP3FILE(directory);
		return (int) mp3file.getLengthInSeconds();
	}
	//give us how many byte mp3 file take on every second, important for time skip method
	private int getByteRate(String directory) {
		Mp3File mp3file = createMP3FILE(directory);
		int bitrate = mp3file.getBitrate();
		int bytePerSecond = bitrate * 1000 / 8;
		return bytePerSecond;
	}
	private void getInputStream(File file, int second) {
		FileInputStream fis = null;
		try {
			 fis = new FileInputStream(file);
		} catch (IOException  e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		BufferedInputStream bis = new BufferedInputStream(fis);
		try {
			if(second != 0) {
				int byterate = getByteRate(file.getAbsolutePath());
				//setCurrentTimeSecond(second);
				int skippedByte = second * byterate;
				bis.skip(skippedByte);
			}
			player = new Player(bis);
		} catch (JavaLayerException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
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
		//setCurrentTimeSecond(0);
	}
	private static Mp3File createMP3FILE(String directory) {
		Mp3File mp3File = null;
		try {
			mp3File = new Mp3File(directory);
		} catch (UnsupportedTagException | InvalidDataException | IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	    return mp3File;
	}
	public Musicinfo getInfo() {
		return info;
	}
	public void setInfo(Musicinfo info) {
		this.info = info;
		
	}
	public static Object getLock() {
		return lock;
	}
	public static void setLock(Object lock) {
		MusicPlayer.lock = lock;
	}
	public int getID() {
		return info.getId();
	}
}

