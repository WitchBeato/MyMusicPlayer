package backend;

import java.awt.Color;
import java.awt.Image;
import java.awt.Component;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;

import javax.imageio.ImageIO;
import javax.swing.JFrame;

import userUI.information.Musicinfo;
import userUI.information.Playlist;

public class Playlistbackend {
	public static Playlist Directorytoplaylist(String directory) {
		Playlist fakeplaylist = new Playlist(directory, 0);
		File directoryfile = new File(directory);
		File[] filelist = directoryfile.listFiles();
		for (File file : filelist) {
			int size = fakeplaylist.getList().size();
			Musicinfo info = new Musicinfo(size, file.getAbsolutePath());
			if(info.getIdInteger() == null) continue;
			fakeplaylist.addtoList(info);
		}
		return fakeplaylist;
		
		
	}
	public static Color getColorSub(Color color,float CLRRATION) {
		Color color_Sub = new Color(Math.round((float)(color.getRed())*CLRRATION),Math.round((float)(color.getGreen())*CLRRATION)
				,Math.round((float)(color.getBlue())*CLRRATION));
		return color_Sub;
	}
	public static BufferedImage bytestoImage(byte[] data) {
		if(data == null) return null;
		ByteArrayInputStream bais = new ByteArrayInputStream(data);
		BufferedImage img = null;
		try {
			img = ImageIO.read(bais);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return img;
		
	}
	
}	
