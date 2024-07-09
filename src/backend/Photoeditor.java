package backend;

import java.awt.AlphaComposite;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Transparency;
import java.awt.Window.Type;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JComponent;

public class Photoeditor {
	//scale photo to wanted resolution
	public static Image photoScale(String directory, int widht,int height) {
		BufferedImage originalImage = null;
		try {
			originalImage = ImageIO.read(new File(directory));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		Image scaledImage = originalImage.getScaledInstance(widht, height, Image.SCALE_SMOOTH);
		return scaledImage;	
	}
	//turn photoscale's bufferedimage to imageicon
	public static ImageIcon photoScaleImage(String directory, int widht,int height) {
		Image newImage =  photoScale(directory, widht, height);
		return new ImageIcon(newImage);
	}
}
