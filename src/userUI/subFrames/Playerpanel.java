package userUI.subFrames;

import javax.swing.JPanel;
import javax.swing.JPopupMenu;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import javax.swing.BoxLayout;
import java.awt.GridLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.SpringLayout;
import javax.swing.JLabel;
import javax.swing.JMenuItem;

import java.awt.Font;
import javax.swing.SwingConstants;
import javax.swing.SwingUtilities;

import backend.MusicPlayer;
import backend.Photoeditor;
import backend.StringEditor;
import staticinfo.Imagedtr;
import staticinfo.MenuItemlist;
import userUI.MainFrame;
import userUI.information.Musicinfo;
import userUI.mycomponents.Mybutton;
import userUI.mycomponents.PlayerTime;

import javax.swing.JProgressBar;
import javax.swing.JSlider;

import java.awt.Color;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Collection;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;
import java.awt.event.ActionEvent;
import javax.swing.UIManager;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

public class Playerpanel extends JPanel {
	private static final long serialVersionUID = 1L;
	private Playerpanel me = this;
	private MainFrame father;
	private JPanel pnl_Buttons, pnl_Time, pnl_Soundicon;
	private PlayerTime pb_Time;
	private JButton btn_Soundicon;
	private JLabel lbl_Fulltime , lbl_Currenttime;
	private int fulltime;
	private Timer timer;
	private MusicPlayer player;
	private Boolean isStop = false;
	private final Object lock = new Object();
	private int current = 0;
	private JButton btn_Prev, btn_Stop,btn_Onward;
	private JPanel pnl_Timeline, pnl_Onward,pnl_Stop,pnl_Prev;
	/**
	 * Create the panel.
	 */
	public Playerpanel() {
		initiliaze();
	}
	public Playerpanel(MainFrame father, MusicPlayer player) {
		this.father = father;
		initiliaze();
		globalListeners(father);
		this.player = player;

	}
	private void initiliaze() {
		setLayout(new BorderLayout(0, 0));
		setSize(510,51);
		pnl_Buttons = new JPanel();
		pnl_Buttons.setPreferredSize(new Dimension(100,0));
		add(pnl_Buttons, BorderLayout.WEST);
		pnl_Buttons.setLayout(new GridLayout(0, 3, 0, 0));
		
		pnl_Prev = new JPanel();
		pnl_Buttons.add(pnl_Prev);
		SpringLayout sl_pnl_Prev = new SpringLayout();
		pnl_Prev.setLayout(sl_pnl_Prev);
		
		btn_Prev = new Mybutton("");
		btn_Prev.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				prevSong();
			}
		});
		btn_Prev.setIcon(Photoeditor.photoScaleImage(Imagedtr.prev, 10, 50));
		sl_pnl_Prev.putConstraint(SpringLayout.NORTH, btn_Prev, 10, SpringLayout.NORTH, pnl_Prev);
		sl_pnl_Prev.putConstraint(SpringLayout.WEST, btn_Prev, 10, SpringLayout.WEST, pnl_Prev);
		sl_pnl_Prev.putConstraint(SpringLayout.SOUTH, btn_Prev, -10, SpringLayout.SOUTH, pnl_Prev);
		sl_pnl_Prev.putConstraint(SpringLayout.EAST, btn_Prev, 23, SpringLayout.WEST, pnl_Prev);
		pnl_Prev.add(btn_Prev);
		
		pnl_Stop = new JPanel();
		pnl_Buttons.add(pnl_Stop);
		pnl_Stop.setLayout(new BorderLayout(0, 0));
		
		btn_Stop = new Mybutton("");
		btn_Stop.setIcon(Photoeditor.photoScaleImage(Imagedtr.stop, 30, 51));
		btn_Stop.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				stop();
			}
		});
		pnl_Stop.add(btn_Stop, BorderLayout.CENTER);
		
		pnl_Onward = new JPanel();
		pnl_Buttons.add(pnl_Onward);
		SpringLayout sl_pnl_Onward = new SpringLayout();
		pnl_Onward.setLayout(sl_pnl_Onward);
		
		btn_Onward = new Mybutton("");
		btn_Onward.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				nextSong();
			}
		});
		btn_Onward.setIcon(Photoeditor.photoScaleImage(Imagedtr.onward, 10, 50));
		sl_pnl_Onward.putConstraint(SpringLayout.NORTH, btn_Onward, 10, SpringLayout.NORTH, pnl_Onward);
		sl_pnl_Onward.putConstraint(SpringLayout.WEST, btn_Onward, 10, SpringLayout.WEST, pnl_Onward);
		sl_pnl_Onward.putConstraint(SpringLayout.SOUTH, btn_Onward, -10, SpringLayout.SOUTH, pnl_Onward);
		sl_pnl_Onward.putConstraint(SpringLayout.EAST, btn_Onward, -10, SpringLayout.EAST, pnl_Onward);
		pnl_Onward.add(btn_Onward);
		
		JPanel pnl_East = new JPanel();
		add(pnl_East, BorderLayout.CENTER);
		pnl_East.setLayout(new BorderLayout(0, 0));
		
		pnl_Time = new JPanel();
		pnl_Time.setPreferredSize(new Dimension(60,0));
		pnl_East.add(pnl_Time, BorderLayout.WEST);
		pnl_Time.setLayout(new GridLayout(1, 0, 0, 0));
		
		lbl_Currenttime = new JLabel("00:00");
		lbl_Currenttime.setHorizontalAlignment(SwingConstants.CENTER);
		lbl_Currenttime.setFont(new Font("Tahoma", Font.PLAIN, 6));
		pnl_Time.add(lbl_Currenttime);
		
		JLabel lbl_Slash = new JLabel("/");
		lbl_Slash.setHorizontalAlignment(SwingConstants.CENTER);
		lbl_Slash.setFont(new Font("Tahoma", Font.PLAIN, 9));
		pnl_Time.add(lbl_Slash);
		
		lbl_Fulltime = new JLabel("00:00");
		lbl_Fulltime.setHorizontalAlignment(SwingConstants.CENTER);
		lbl_Fulltime.setFont(new Font("Tahoma", Font.PLAIN, 6));
		pnl_Time.add(lbl_Fulltime);
		
		pnl_Soundicon = new JPanel();
		pnl_Soundicon.setPreferredSize(new Dimension(50,0));
		pnl_East.add(pnl_Soundicon, BorderLayout.EAST);
		pnl_Soundicon.setLayout(new BorderLayout(0, 0));
		
		btn_Soundicon = new JButton("");
		btn_Soundicon.setIcon(Photoeditor.photoScaleImage(Imagedtr.sound, 45, 51));
		btn_Soundicon.setVisible(false);
		pnl_Soundicon.add(btn_Soundicon, BorderLayout.CENTER);
		
		pnl_Timeline = new JPanel();
		pnl_East.add(pnl_Timeline, BorderLayout.CENTER);
		SpringLayout sl_pnl_Timeline = new SpringLayout();
		pnl_Timeline.setLayout(sl_pnl_Timeline);
		
		pb_Time = new PlayerTime(this);
		sl_pnl_Timeline.putConstraint(SpringLayout.EAST, pb_Time, -10, SpringLayout.EAST, pnl_Timeline);
		pb_Time.setBorder(UIManager.getBorder("EditorPane.border"));
		sl_pnl_Timeline.putConstraint(SpringLayout.WEST, pb_Time, 10, SpringLayout.WEST, pnl_Timeline);
		pb_Time.setSize(new Dimension(20,31));
		sl_pnl_Timeline.putConstraint(SpringLayout.NORTH, pb_Time, 10, SpringLayout.NORTH, pnl_Timeline);
		sl_pnl_Timeline.putConstraint(SpringLayout.SOUTH, pb_Time, 41, SpringLayout.NORTH, pnl_Timeline);
		pb_Time.setForeground(new Color(102, 0, 153));
		pnl_Timeline.add(pb_Time);
	}

	public int getFulltime() {
		return fulltime;
	}
	public MusicPlayer getPlayer() {
		return player;
	}
	public void setFulltime(int fulltime) {
		timerreset();
		pb_Time.setMaximus(fulltime);
		this.fulltime = fulltime;
		lbl_Fulltime.setText(StringEditor.giveTimeinSecond(fulltime));
		setTimer();
	}
	public void setCurrent(int second) {
		current = second;
		lbl_Currenttime.setText(StringEditor.giveTimeinSecond(current));
	    SwingUtilities.invokeLater(() -> {
	    	pb_Time.SetTime(second);
	    });
		if(fulltime <= current) {
			player.playerclose();
			
		}
	}
	public void closeMusic() {
		if(player == null) return;
		player.playerclose();
	}
	public void setTimer() {
		float speed = 1;
		timer = new Timer();
		TimerTask task = new TimerTask() {		
			@Override
			public void run() {
				if(!isStop) setlbl_Current(speed);
				if(current >= fulltime) {
					timerreset();
				}
			}
		};
		timer.scheduleAtFixedRate(task, 0,(int)speed*1000);
	}
	public Timer getTimer() {
		return timer;
	}
	public Object getLock() {
		return lock;
	}
	private void stop() {
		isStop = !isStop;
		player.playerwait();
		pb_Time.enableComponents(false);
	}
	public void setisplay(Boolean isstop) {
		if(!isstop) {
			synchronized (lock) {
				lock.notify();
			}
		}
		this.isStop = isstop;
	}
	private void globalListeners(MainFrame father) {
		btn_Soundicon.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Soundpanel soundpanel = new Soundpanel(btn_Soundicon);
				JSlider slider = soundpanel.getSlider();
				slider.setValue(20);
				slider.addChangeListener(new ChangeListener() {
					
					@Override
					public void stateChanged(ChangeEvent e) {
						// TODO Auto-generated method stub
						float volume = (float)(slider.getValue())/100f;
						player.setVolume(volume);
					}
				});
				soundpanel.setSize(new Dimension(393,85));
				JPopupMenu menu = new JPopupMenu();
				menu.setPopupSize(soundpanel.getSize());
				menu.add(soundpanel);
				menu.setSize(new Dimension(soundpanel.getWidth(),soundpanel.getHeight()));
				menu.show(father, 
						father.getWidth()-soundpanel.getWidth()/2,
						father.getHeight()-btn_Soundicon.getPreferredSize().height-soundpanel.getHeight());
			}
		});
	}
	private void setlbl_Current(float speed) {
		if (isStop) return;
		setCurrent((int)(current+speed));

	}
	private void timerreset() {
		current = 0;
		fulltime = 0;
		if(timer != null) timer.cancel();
	}
	private void nextSong() {
		setInfo(1);
		Boolean nextExist = father.isSongExist(player.getID()+1);
		btn_Onward.setEnabled(nextExist);
		btn_Prev.setEnabled(!nextExist);
	}
	private void prevSong() {
		setInfo(-1);
		Boolean prevExist = father.isSongExist(player.getID()-1);
		btn_Prev.setEnabled(prevExist);
		btn_Onward.setEnabled(!prevExist);
	}
	private void setInfo(int skipped) {
		Musicinfo info = father.setNextSong(skipped);
		if(info == null) return;
		setFulltime(info.getTime());
	}
	public void setPrevVisibility(Boolean isActive) {
		btn_Prev.setEnabled(isActive);
	}
	public void setNextVisibility(Boolean isActive) {
		btn_Onward.setEnabled(isActive);
	}
	@Override
	public void setBackground(Color bg) {
		// TODO Auto-generated method stub
		super.setBackground(bg);
		if(btn_Onward == null) return;
		btn_Onward.setBackground(bg);
		btn_Prev.setBackground(bg);
		btn_Stop.setBackground(bg);
		pb_Time.setBackground(bg);
		pnl_Time.setBackground(bg);
		pnl_Timeline.setBackground(bg);
		pnl_Buttons.setBackground(bg);
		pnl_Soundicon.setBackground(bg);
		pnl_Prev.setBackground(bg);
		pnl_Onward.setBackground(bg);
		pnl_Stop.setBackground(bg);
	}
}
