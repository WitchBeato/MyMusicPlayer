package userUI.mycomponents;

import javax.swing.JPanel;
import java.awt.BorderLayout;
import javax.swing.JScrollBar;
import javax.swing.JScrollPane;
import javax.swing.JSlider;
import javax.swing.JLayeredPane;
import javax.swing.SpringLayout;
import javax.swing.JProgressBar;
import javax.swing.SwingConstants;
import javax.swing.SwingUtilities;

import java.awt.Color;
import javax.swing.border.TitledBorder;
import javax.swing.event.ChangeListener;

import backend.MusicPlayer;
import userUI.subFrames.Playerpanel;

import javax.swing.event.ChangeEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Timer;

public class PlayerTime extends JPanel {
	private static final long serialVersionUID = 1L;

	/**
	 * Create the panel.
	 */
	private Boolean isTimeChanging = false;
	private JProgressBar pb_Time;
	private JSlider sb_Time;
	private Boolean isPlay;
	private int prevValue = 0;
	private Playerpanel mother;

	private JLayeredPane lpnl_Content;
	public PlayerTime(Playerpanel mother) {
		this.mother = mother;
		init();
	}
	private void init() {
		setLayout(new BorderLayout(0, 0));
		
		lpnl_Content = new JLayeredPane();
		add(lpnl_Content, BorderLayout.CENTER);
		SpringLayout sl_lpnl_Content = new SpringLayout();
		lpnl_Content.setLayout(sl_lpnl_Content);
		
		sb_Time = new JSlider();
		sb_Time.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseReleased(MouseEvent e) {
				JSlider bar = (JSlider)e.getSource();
				int oldvalue = sb_Time.getValue();
				if(!bar.getValueIsAdjusting()) {
					SwingUtilities.invokeLater(new Runnable() {
						
						@Override
						public void run() {
							// TODO Auto-generated method stub
							valueChangedbyHand(bar.getValue());
						}
					});

					mother.setisplay(true);
					setIsTimeChanging(false);
				}
				

			}
			@Override
			public void mouseClicked(MouseEvent e) {
				SwingUtilities.invokeLater(new Runnable() {
					
					@Override
					public void run() {
						// TODO Auto-generated method stub
						
						
					}
				});
			}
			@Override
			public void mouseDragged(MouseEvent e) {
				// TODO Auto-generated method stub
				sb_Time.setValueIsAdjusting(true);
				super.mouseDragged(e);
			}
		});
		sb_Time.setBorder(null);
		sb_Time.setOpaque(false);
		sb_Time.setPaintTicks(true);
		sb_Time.setPaintLabels(true);
		sb_Time.setOrientation(JScrollBar.HORIZONTAL);
		sb_Time.setForeground(new Color(153, 102, 204));
		sl_lpnl_Content.putConstraint(SpringLayout.WEST, sb_Time, 10, SpringLayout.WEST, lpnl_Content);
		sl_lpnl_Content.putConstraint(SpringLayout.EAST, sb_Time, -10, SpringLayout.EAST, lpnl_Content);
		sl_lpnl_Content.putConstraint(SpringLayout.NORTH, sb_Time, 0, SpringLayout.NORTH, lpnl_Content);
		sl_lpnl_Content.putConstraint(SpringLayout.SOUTH, sb_Time, 51, SpringLayout.NORTH, lpnl_Content);
		lpnl_Content.add(sb_Time,JLayeredPane.DRAG_LAYER);
		

		pb_Time = new JProgressBar();
		pb_Time.setValue(0);
		sb_Time.setValue(0);
		sl_lpnl_Content.putConstraint(SpringLayout.WEST, pb_Time, 10, SpringLayout.WEST, lpnl_Content);
		sl_lpnl_Content.putConstraint(SpringLayout.EAST, pb_Time, -10, SpringLayout.EAST, lpnl_Content);
		sl_lpnl_Content.putConstraint(SpringLayout.NORTH, pb_Time, 5, SpringLayout.NORTH, lpnl_Content);
		sl_lpnl_Content.putConstraint(SpringLayout.SOUTH, pb_Time, 28, SpringLayout.NORTH, lpnl_Content);
		pb_Time.setForeground(new Color(75, 0, 130));
		pb_Time.setOrientation(SwingConstants.HORIZONTAL);
		lpnl_Content.add(pb_Time,JLayeredPane.DEFAULT_LAYER);
		enableComponents(false);
	}
	public void SetTime(int second) {
		enableComponents(true);
		if(sb_Time == null) return;
		sb_Time.setValue(second);
		pb_Time.setValue(second);
		prevValue = second;
	}
	public int getTime() {
		if(sb_Time == null) return 0;
	     return sb_Time.getValue();
	}
	public void setMaximus(int fulltime) {
		if(pb_Time == null) return;
		sb_Time.setMaximum(fulltime);
		pb_Time.setMaximum(fulltime);
	}
	public void enableComponents(Boolean isPlay) {
		if(pb_Time == null) return;
		pb_Time.setEnabled(isPlay);
		sb_Time.setEnabled(isPlay);
		this.isPlay = isPlay;
	}
	private void valueChangedbyHand(int value) {
		if(mother == null) return;
		MusicPlayer player = mother.getPlayer();
		if(player == null) return;
		Timer timer = mother.getTimer();
		mother.closeMusic();
		player.play(player.getInfo(), value);
		mother.setCurrent(value);
		mother.setisplay(false);
		//mother.setTimer();
	}
	@Override
	public void setBackground(Color bg) {
		// TODO Auto-generated method stub
		super.setBackground(bg);
		if(pb_Time == null) return;
		pb_Time.setBackground(bg);
		sb_Time.setBackground(bg);
		lpnl_Content.setBackground(bg);
	}
	public Boolean getIsTimeChanging() {
		return isTimeChanging;
	}
	public void setIsTimeChanging(Boolean isTimeChanging) {
		this.isTimeChanging = isTimeChanging;
	}
}
