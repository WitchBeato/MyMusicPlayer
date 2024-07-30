package userUI.mycomponents;

import java.awt.Color;
import java.awt.Event;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JLabel;

import backend.Playlistbackend;
import staticinfo.Mycolors;

public class JLabelClickable extends JLabel{
	Color original;
	Color sub;
	public JLabelClickable(String text){
		setText(text);
		init();
	}
	public JLabelClickable(){
		init();
	}
	private void init() {
		setForeground(Mycolors.linkBlue);
		AddEvents();
	}
	@Override
	public void setForeground(Color fg) {
		// TODO Auto-generated method stub
		super.setForeground(fg);
		if(fg != sub && sub != null) original = fg;
		sub = Playlistbackend.getColorSub(fg, 0.8f);
	}
	private void AddEvents(){
		this.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				// TODO Auto-generated method stub
				super.mouseEntered(e);
				setForeground(sub); 
			}
			@Override
			public void mouseExited(MouseEvent e) {
				// TODO Auto-generated method stub
				super.mouseExited(e);
				setForeground(original);
			}
		});
	}

	
}
