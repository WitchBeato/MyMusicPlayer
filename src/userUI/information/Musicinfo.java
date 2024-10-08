package userUI.information;

import java.awt.CheckboxMenuItem;
import java.awt.Image;
import java.awt.Transparency;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.ArrayList;

import javax.imageio.ImageIO;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;

import com.mpatric.mp3agic.ID3v1;
import com.mpatric.mp3agic.ID3v1Tag;
import com.mpatric.mp3agic.ID3v2;
import com.mpatric.mp3agic.ID3v24Tag;
import com.mpatric.mp3agic.InvalidDataException;
import com.mpatric.mp3agic.Mp3File;
import com.mpatric.mp3agic.UnsupportedTagException;

import backend.Photoeditor;
import backend.Playlistbackend;
import backend.StringEditor;
import staticinfo.SupportedTypes;

public class Musicinfo implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	//if id is null that will mean i wont added to playlist.
	
	private Integer id = null;
	private String directoryFamily;
	private String directory;
	private String name,singer;
	private String date;
	private byte[] cover_Byte;
	private transient BufferedImage cover;
	private int time;

	public Musicinfo(int id, String directory){
		String extension = StringEditor.giveExtension(directory);
		extension = extension.substring(1, extension.length());
		if(!SupportedTypes.checkType(SupportedTypes.audioExt, extension)) return;
		this.id = id;
		setDirectory(directory);
		readFile(directory);
	}
	public String getDirectory() {
		return directory;
	}
	public void setDirectory(String directory) {
		this.directory = directory;
		String preName = StringEditor.giveStringforIterative(directory, '\\', 1);
		setNamewithDirectory(preName);
		setDirectoryFamily(directory);
		
	}
	public int getId() {
		return id;
	}
	public Integer getIdInteger() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return checkIt(name);
	}
	public void setNamewithDirectory(String name) {
		if(name == null) return;
		String extension =  StringEditor.giveStringforIterative(name, '.', 1);
		int size = extension.length();
		name = name.substring(1, name.length()-size);
		this.name = name;
	}
	public void setName(String name) {
		if(name == null) return;
		this.name = name;
	}
	public String getSinger() {
		return checkIt(singer);
	}
	public void setSinger(String singer) {
		this.singer = singer;
	}
	public String getDate() {
		return checkIt(date);
	}
	public void setDate(String date) {
		this.date = date;
	}
	public int getTime() {
		return time;
	}
	public void setTime(int time) {
		this.time = time;
	}
	public void readFile(String directory) {
		Mp3File mp3file = null;
		try {
			mp3file = new Mp3File(directory);
		} catch (UnsupportedTagException | InvalidDataException | IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		setTime((int) mp3file.getLengthInSeconds());
		if (mp3file.hasId3v2Tag()) {
			readID3v2Tag(mp3file.getId3v2Tag());
		}
		else if (mp3file.hasId3v1Tag()) {
			readID3v1Tag(mp3file.getId3v1Tag());
		}
		else {
			noTag();
		}
	}
	private void readID3v2Tag(ID3v2 info) {
		setSinger(info.getArtist());
		setDate(info.getDate());
		setCover(Playlistbackend.bytestoImage(info.getAlbumImage()));
		setName(info.getTitle());
	}
	private void readID3v1Tag(ID3v1 info) {
		setSinger(info.getArtist());
		setDate(info.getYear());
		setCover(null);
		setName(info.getTitle());
	}
	private void noTag() {
		setSinger("N/A");
		setDate("N/A");
		setCover(null);
	}
	public Image getCover() {
		if(cover == null && cover_Byte != null) {
			cover = Photoeditor.bytetoImage(cover_Byte);
		}
		return cover;
	}
	public void setCover(BufferedImage cover) {
		this.cover = cover;
		this.cover_Byte = Photoeditor.imagetoByte(cover);
	}
	public byte[] getCover_Byte() {
		return cover_Byte;
	}
	public String getDirectoryFamily() {
		return directoryFamily;
	}
	private void setDirectoryFamily(String directory) {
		String directoryFamily = StringEditor.giveStringforIterative(directory, '\\', 2).substring(1);;
		this.directoryFamily = directoryFamily;
	}
	private String checkIt(String text) {
		if(text == null) return "N/A";
		return text;
	}

}