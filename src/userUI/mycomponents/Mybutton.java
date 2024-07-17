package userUI.mycomponents;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;

import javax.swing.JButton;

public class Mybutton extends JButton{
	Mybutton(){
		setContentAreaFilled(false);
	}
	private Boolean over;
	private Color color;
	private Color colorOver;
	private Color colorClick;
	private Color colorBorder;
	private int radius = 0;
	@Override
	protected void paintComponent(Graphics g) {
		// TODO Auto-generated method stub
		super.paintComponent(g);
		Graphics2D g2d = (Graphics2D)g;
		g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
		
	}
	public int getRadius() {
		return radius;
	}
	public void setRadius(int radius) {
		this.radius = radius;
	}
	public Color getColorBorder() {
		return colorBorder;
	}
	public void setColorBorder(Color colorBorder) {
		this.colorBorder = colorBorder;
	}
	public Color getColorClick() {
		return colorClick;
	}
	public void setColorClick(Color colorClick) {
		this.colorClick = colorClick;
	}
	public Color getColorOver() {
		return colorOver;
	}
	public void setColorOver(Color colorOver) {
		this.colorOver = colorOver;
	}
	public Color getColor() {
		return color;
	}
	public void setColor(Color color) {
		this.color = color;
	}
	public Boolean getOver() {
		return over;
	}
	public void setOver(Boolean over) {
		this.over = over;
	}
}
