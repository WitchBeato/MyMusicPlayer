package userUI;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JPopupMenu;
import javax.swing.border.EmptyBorder;

import staticinfo.Dtr;
import staticinfo.Mycolors;
import staticinfo.SupportedTypes;
import userUI.information.Musicinfo;
import userUI.information.Musiclist;
import userUI.information.Playlist;
import userUI.mycomponents.ChangeableImage;
import userUI.mycomponents.JLabelClickable;
import userUI.mycomponents.JTextwithFolder;
import userUI.mycomponents.Mybutton;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridBagLayout;
import java.awt.Image;

import javax.swing.JLabel;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import java.awt.MouseInfo;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.InputMethodEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.awt.image.ImageObserver;
import java.awt.image.ImageProducer;
import java.io.File;
import java.io.IOException;
import java.lang.reflect.AccessFlag.Location;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.Locale;
import java.awt.Font;
import java.awt.Graphics;

import javax.swing.JTextField;
import javax.swing.border.TitledBorder;

import com.github.lgooddatepicker.components.CalendarPanel;
import com.github.lgooddatepicker.optionalusertools.CalendarListener;
import com.github.lgooddatepicker.zinternaltools.CalendarSelectionEvent;
import com.github.lgooddatepicker.zinternaltools.YearMonthChangeEvent;

import backend.AddtoMusiclist;
import backend.Photoeditor;
import backend.Playlistbackend;
import backend.StringEditor;

import javax.swing.SwingConstants;
import javax.imageio.ImageIO;
import javax.swing.JButton;
import java.awt.Color;
import java.awt.Component;
import javax.swing.UIManager;

public class AddMusic extends JFrame {
	//these value will show which mode are we in
	public static final int ADDMODE = 0, UPDATEMODE = 1;

	private static final Boolean designmode = false;
	private static final long serialVersionUID = 1L;
	private MainFrame mainframe;
	private Boolean isActiveInput;
	private JPanel contentPane;
	private Mybutton btn_Ok;
	private JLabel lbl_addFolder;
	private JTextField txt_Singer,txt_Name,txt_Date, txt_Time;
	private JButton btn_SetDate;
	private Playlist playlist;
	private Musicinfo info;
	private int mode;
	private JTextwithFolder txt_Directory;
	private ChangeableImage lbl_Image;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					AddMusic frame = new AddMusic();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 * @param mainframe 
	 */
	public AddMusic(Playlist playlist, MainFrame mainframe, int mode) {
		this.playlist = playlist;
		this.mainframe = mainframe;
		init();
		setMode(mode);
	}
	public AddMusic(int mode) {
		this.mode = mode;
		init();
		setMode(mode);
	}
	public AddMusic() {
		init();
	}
	private void init() {
		setResizable(false);
		Font font = new Font("Verdana", Font.BOLD, 16);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 480, 600);
		setPreferredSize(new Dimension(410, 600));
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(new BorderLayout(0, 0));
		
		JPanel pnl_Directory = new JPanel();
		contentPane.add(pnl_Directory, BorderLayout.NORTH);
		pnl_Directory.setPreferredSize(new Dimension(this.getWidth(),120));
		pnl_Directory.setBackground(Mycolors.openGray);
		pnl_Directory.setLayout(null);
		 
		if(mode != ADDMODE) txt_Directory = new JTextwithFolder(Musiclist.DIRECTORY,JTextwithFolder.FILEMODE);
		else {
			txt_Directory = new JTextwithFolder(Musiclist.DIRECTORY,JTextwithFolder.FILEMODE) {
				/**
				 * 
				 */

				@Override
				public void setText(String text) {
					// TODO Auto-generated method stub
					super.setText(text);
					if(text == null) return;
					getInfoDirectory(txt_Directory);
				}
			};
		}
		

		txt_Directory.setBounds(10, 29, 352, 39);
		pnl_Directory.add(txt_Directory);
		
		lbl_addFolder = new JLabelClickable("I want to add whole folder");
		lbl_addFolder.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				String directory = StringEditor.selectFolder();
				if(directory == null) return;
				Playlist tempplaylist = Playlistbackend.Directorytoplaylist(directory);
				playlist.mergePlaylist(tempplaylist);
				close();
			}
		});
		lbl_addFolder.setForeground(new Color(0, 0, 255));
		lbl_addFolder.setFont(new Font("Tahoma", Font.BOLD, 15));
		lbl_addFolder.setBounds(136, 67, 214, 24);
		pnl_Directory.add(lbl_addFolder);
		
		JPanel pnl_Info = new JPanel();
		contentPane.add(pnl_Info, BorderLayout.CENTER);
		GridBagLayout gbl_pnl_Info = new GridBagLayout();
		gbl_pnl_Info.columnWidths = new int[] {0, 0, 0, 10, 30, 0};
		gbl_pnl_Info.rowHeights = new int[]{0, 0, 0, 0, 0, 0};
		gbl_pnl_Info.columnWeights = new double[]{0.5, 0.0, 0.0, 0.0, 0.0};
		gbl_pnl_Info.rowWeights = new double[]{1.0, 1.0, 1.0, 1.0, 0.0, Double.MIN_VALUE};
		pnl_Info.setLayout(gbl_pnl_Info);
		
		JLabel lbl_Name = new JLabel("Name");
		lbl_Name.setFont(new Font("Verdana", Font.BOLD, 16));
		GridBagConstraints gbc_lbl_Name = new GridBagConstraints();
		gbc_lbl_Name.insets = new Insets(0, 0, 5, 5);
		gbc_lbl_Name.gridx = 0;
		gbc_lbl_Name.gridy = 0;
		pnl_Info.add(lbl_Name, gbc_lbl_Name);
		
		txt_Name = new JTextField();
		txt_Name.setFont(new Font("Verdana", Font.BOLD, 16));
		txt_Name.setColumns(10);
		txt_Name.setBorder(new TitledBorder(null, "", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		GridBagConstraints gbc_txt_Name = new GridBagConstraints();
		gbc_txt_Name.insets = new Insets(0, 0, 5, 5);
		gbc_txt_Name.fill = GridBagConstraints.HORIZONTAL;
		gbc_txt_Name.gridx = 1;
		gbc_txt_Name.gridy = 0;
		pnl_Info.add(txt_Name, gbc_txt_Name);
		
		JLabel lbl_Singer = new JLabel("Singer");
		lbl_Singer.setFont(font);
		GridBagConstraints gbc_lbl_Singer = new GridBagConstraints();
		gbc_lbl_Singer.insets = new Insets(0, 0, 5, 5);
		gbc_lbl_Singer.anchor = GridBagConstraints.NORTH;
		gbc_lbl_Singer.fill = GridBagConstraints.VERTICAL;
		gbc_lbl_Singer.gridx = 0;
		gbc_lbl_Singer.gridy = 1;
		pnl_Info.add(lbl_Singer, gbc_lbl_Singer);
		
		txt_Singer = new JTextField();
		txt_Singer.setFont(font);
		txt_Singer.setBorder(new TitledBorder(null, "", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		GridBagConstraints gbc_txt_Singer = new GridBagConstraints();
		gbc_txt_Singer.insets = new Insets(0, 0, 5, 5);
		gbc_txt_Singer.fill = GridBagConstraints.HORIZONTAL;
		gbc_txt_Singer.gridx = 1;
		gbc_txt_Singer.gridy = 1;
		pnl_Info.add(txt_Singer, gbc_txt_Singer);
		txt_Singer.setColumns(10);
		if(mode != ADDMODE) lbl_Image = new ChangeableImage();
		else {
			lbl_Image = new ChangeableImage() {
				@Override
				public void setImageCurrent(String directory) {
					// TODO Auto-generated method stub
					super.setImageCurrent(directory);
					if(info == null || designmode) return;
					else {
						File file = new File(directory);
						BufferedImage image = null;
						try {
							image = ImageIO.read(file);
						} catch (IOException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
						info.setCover(image);
					}
					
				}
			};
		}
		if(info != null)lbl_Image.setImageCurrentwithImage(info.getCover());
		lbl_Image.setPreferredSize(new Dimension(100,120));
		GridBagConstraints gbc_lbl_Image = new GridBagConstraints();
		gbc_lbl_Image.weighty = 1.0;
		gbc_lbl_Image.weightx = 1.0;
		gbc_lbl_Image.fill = GridBagConstraints.BOTH;
		gbc_lbl_Image.gridwidth = 4;
		gbc_lbl_Image.insets = new Insets(0, 0, 5, 5);
		gbc_lbl_Image.gridx = 2;
		gbc_lbl_Image.gridy = 1;
		pnl_Info.add(lbl_Image, gbc_lbl_Image);
		lbl_Image.setVisible(true);
		
		JLabel lbl_Time = new JLabel("Time");
		lbl_Time.setFont(new Font("Verdana", Font.BOLD, 16));
		GridBagConstraints gbc_lbl_Time = new GridBagConstraints();
		gbc_lbl_Time.insets = new Insets(0, 0, 5, 5);
		gbc_lbl_Time.gridx = 0;
		gbc_lbl_Time.gridy = 2;
		pnl_Info.add(lbl_Time, gbc_lbl_Time);
		
		txt_Time = new JTextField();
		txt_Time.setFont(new Font("Verdana", Font.BOLD, 16));
		txt_Time.setColumns(10);
		txt_Time.setBorder(new TitledBorder(null, "", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		txt_Time.setEnabled(false);
		GridBagConstraints gbc_txt_Time = new GridBagConstraints();
		gbc_txt_Time.insets = new Insets(0, 0, 5, 5);
		gbc_txt_Time.fill = GridBagConstraints.HORIZONTAL;
		gbc_txt_Time.gridx = 1;
		gbc_txt_Time.gridy = 2;
		pnl_Info.add(txt_Time, gbc_txt_Time);
		
		JLabel lbl_Date = new JLabel("Date");
		lbl_Date.setVerticalAlignment(SwingConstants.BOTTOM);
		lbl_Date.setFont(new Font("Verdana", Font.BOLD, 16));
		GridBagConstraints gbc_lbl_Date = new GridBagConstraints();
		gbc_lbl_Date.insets = new Insets(0, 0, 5, 5);
		gbc_lbl_Date.gridx = 0;
		gbc_lbl_Date.gridy = 3;
		pnl_Info.add(lbl_Date, gbc_lbl_Date);
		
		JPanel pnl_Date = new JPanel();
		GridBagConstraints gbc_pnl_Date = new GridBagConstraints();
		gbc_pnl_Date.fill = GridBagConstraints.HORIZONTAL;
		gbc_pnl_Date.insets = new Insets(0, 0, 5, 5);
		gbc_pnl_Date.gridx = 1;
		gbc_pnl_Date.gridy = 3;
		pnl_Info.add(pnl_Date, gbc_pnl_Date);
		pnl_Date.setLayout(new BorderLayout(0, 0));
		
		txt_Date = new JTextField();
		txt_Date.setFont(new Font("Verdana", Font.BOLD, 16));
		txt_Date.setColumns(10);
		txt_Date.setBorder(new TitledBorder(null, "", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		pnl_Date.add(txt_Date, BorderLayout.CENTER);
		
		btn_SetDate = new JButton("");
		btn_SetDate.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				showCalendar(btn_SetDate,txt_Date);
			}
		});
		btn_SetDate.setPreferredSize(new Dimension(20,20));
		pnl_Date.add(btn_SetDate, BorderLayout.EAST);
		btn_SetDate.setIcon(Photoeditor.photoScaleImage(
				Dtr.getImage("calendarico.png"),
				btn_SetDate.getPreferredSize().width,
				btn_SetDate.getPreferredSize().height)
				);
		
		btn_Ok = new Mybutton("Add");
		btn_Ok.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				
				switch (mode) {
				case ADDMODE:
					if(playlist == null) return;
					clickedAdd();
					break;
		        case UPDATEMODE:
					clickedUpdate();
					break;

				default:
					break;
				}
			}
		});

		GridBagConstraints gbc_btn_Add = new GridBagConstraints();
		gbc_btn_Add.ipady = 20;
		gbc_btn_Add.weighty = 0.2;
		gbc_btn_Add.ipadx = 60;
		gbc_btn_Add.insets = new Insets(0, 0, 0, 5);
		gbc_btn_Add.gridx = 1;
		gbc_btn_Add.gridy = 4;
		pnl_Info.add(btn_Ok, gbc_btn_Add);
		setIsActiveInput(false);
	}
	private void close() {
		this.dispose();
	}
	private void resetInput() {
		txt_Name.setText(null);
		txt_Singer.setText(null);
		txt_Date.setText(null);
		txt_Time.setText(null);
	}
	private void setMusicInfo(Musicinfo info){
		if(info == null) return;
		if(mode == UPDATEMODE)txt_Directory.setText(info.getDirectory());
		txt_Name.setText(info.getName());
		txt_Singer.setText(info.getSinger());
		txt_Date.setText(info.getDate());
		int time = info.getTime();
		txt_Time.setText(Integer.toString(time));
		lbl_Image.setImageCurrentwithImage(info.getCover());
	}
	private void getInfoDirectory(JTextwithFolder txt_Directory) {
		if(txt_Directory == null) return;
		String directory = txt_Directory.getText();
		File file = new File(directory);
		if(file.exists()) {
			int size = (playlist != null) ? playlist.getList().size() : 0;
			Musicinfo info = new Musicinfo(size, directory);
			if(info.getIdInteger() == null) return;
			setInfo(info);
			setIsActiveInput(true);
		}
		else {
			resetInput();
		}
	}

	public Boolean getIsActiveInput() {
		return isActiveInput;
	}

	public void setIsActiveInput(Boolean isActiveInput) {
		this.isActiveInput = isActiveInput;
		txt_Name.setEnabled(isActiveInput);
		txt_Singer.setEnabled(isActiveInput);
		txt_Date.setEnabled(false);
		btn_SetDate.setEnabled(isActiveInput);
	}

	public Musicinfo getInfo() {
		return info;
	}

	public void setInfo(Musicinfo info) {
		this.info = info;
		setMusicInfo(info);
	}
	public void setPlaylist(Playlist playlist) {
		this.playlist = playlist;
	}
	public void setMode(int mode) {
		this.mode = mode;
		Boolean isAdd = null;
		if(mode == ADDMODE) {
			btn_Ok.setText("Add");
			txt_Directory.setText(null);
			isAdd = true; 
			
		}
		else if(mode == UPDATEMODE) {
			btn_Ok.setText("Update");
			isAdd = false;
			setIsActiveInput(true);
		}
		isAddEnabled(isAdd);
		txt_Directory.isTextInputLegal(false);
	}
    public int getMode() {
		return mode;
	}
	private void clickedAdd() {
		getMusicInfo();
		AddtoMusiclist.addMusicwithInfo(playlist, info);
		close();
		if (mainframe != null) {
			mainframe.musiclistRepaint();
		}
	}
	private void clickedUpdate() {
		if(info == null) return;
		getMusicInfo();
		close();
		
	}
	private void getMusicInfo() {
		info.setDate(txt_Date.getText());
		info.setName(txt_Name.getText());
		info.setSinger(txt_Singer.getText());
		info.setTime(Integer.parseInt(txt_Time.getText()));
		BufferedImage image = lbl_Image.getCurrentImage();
		info.setCover(image);

	}
	//true mean we are on add mode false mean musicinfo will be updated
	private void isAddEnabled(Boolean isAdd){
		txt_Directory.setEnabled(isAdd);
		lbl_addFolder.setVisible(isAdd);
	}
	private void showCalendar(Component boss,JTextField txt_Changed) {
		JPopupMenu popup = new JPopupMenu();
		JPanel panel = new JPanel();
		CalendarPanel calendarpanel = new CalendarPanel();
		calendarpanel.setSize(new Dimension(200,200));
		panel.setSize(calendarpanel.getSize());
		popup.setSize(calendarpanel.getSize());
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
		calendarpanel.addCalendarListener(new CalendarListener() {
			
			@Override
			public void yearMonthChanged(YearMonthChangeEvent event) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void selectedDateChanged(CalendarSelectionEvent event) {
				// TODO Auto-generated method stub
				LocalDate date = calendarpanel.getSelectedDate();
				String dateString = date.format(formatter);
				txt_Changed.setText(dateString);
			}
		});
		panel.add(calendarpanel);	
		popup.add(panel);
		popup.show(boss,0,-calendarpanel.getHeight());
	}
}
