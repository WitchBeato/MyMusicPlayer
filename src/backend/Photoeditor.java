package backend;

import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JComponent;

public class Photoeditor {
	//scale photo to wanted resolution
	public BufferedImage photoScale(String directory, int widht,int height) {
		BufferedImage originalImage = null;
		try {
			originalImage = ImageIO.read(new File("original_image.png"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		Image scaledImage = originalImage.getScaledInstance(widht, height, Image.SCALE_SMOOTH);

		// Convert the scaled image to a BufferedImage
		BufferedImage resizedImage = new BufferedImage(widht, height, BufferedImage.TYPE_INT_RGB);
		return resizedImage;	
	}
	//turn photoscale's bufferedimage to imageicon
	public ImageIcon photoScaleImage(String directory, int widht,int height) {
		BufferedImage newImage =  photoScale(directory, widht, height);
		return new ImageIcon(newImage);
	}
}
