package userUI.mycomponents;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;

import javax.swing.JButton;

public class Mybutton extends JButton{
	public Mybutton(String text) {
		super(text);
		init();
	}
	public Mybutton(){
		init();
	}
	private void init() {
		setContentAreaFilled(false);
		setBorderPainted(false);
		setFocusPainted(false);
	}
}
