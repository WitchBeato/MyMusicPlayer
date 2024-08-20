package backend;

import java.awt.AlphaComposite;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Transparency;
import java.awt.Window.Type;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;

import javax.imageio.ImageIO;
import javax.swing.Icon;
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
    public static Image iconToImage(Icon icon) {
        if (icon instanceof ImageIcon) {
            return ((ImageIcon)icon).getImage();
        } else {
            int w = icon.getIconWidth();
            int h = icon.getIconHeight();
            BufferedImage image = new BufferedImage(w, h, BufferedImage.TYPE_INT_ARGB);
            Graphics2D g = image.createGraphics();
            icon.paintIcon(null, g, 0, 0);
            g.dispose();
            return image;
        }
    }
    public static BufferedImage iconToBufferedImage(Icon icon) {
            int w = icon.getIconWidth();
            int h = icon.getIconHeight();
            BufferedImage image = new BufferedImage(w, h, BufferedImage.TYPE_INT_ARGB);
            Graphics2D g = image.createGraphics();
            icon.paintIcon(null, g, 0, 0);
            g.dispose();
            return image;
        }
	public static byte[] imagetoByte(BufferedImage image) {
		if(image == null) return null;
	    ByteArrayOutputStream baos = new ByteArrayOutputStream();
	    try {
			ImageIO.write(image, "png", baos);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	    return baos.toByteArray();
	}
	public static BufferedImage bytetoImage(byte[] byteArray) {
	    ByteArrayInputStream bais = new ByteArrayInputStream(byteArray);
	    try {
			return ImageIO.read(bais);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	    return null;
	}
    
}
