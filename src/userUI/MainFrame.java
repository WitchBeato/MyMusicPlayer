package userUI;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.BorderLayout;
import java.awt.Dimension;
import javax.swing.JScrollPane;

public class MainFrame extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MainFrame frame = new MainFrame();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public MainFrame() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 800, 510);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(new BorderLayout(0, 0));
		
		JPanel pnl_Player = new JPanel();
		pnl_Player.setPreferredSize(new Dimension(0,this.getSize().height/10));
		contentPane.add(pnl_Player, BorderLayout.SOUTH);
		
		JPanel pnl_Center = new JPanel();
		contentPane.add(pnl_Center, BorderLayout.CENTER);
		pnl_Center.setLayout(new BorderLayout(0, 0));
		
		JScrollPane spnl_Selection = new JScrollPane();
		spnl_Selection.setPreferredSize(new Dimension(this.getSize().width/8,0));
		pnl_Center.add(spnl_Selection, BorderLayout.WEST);
		
		JScrollPane spnl_Songs = new JScrollPane();
		pnl_Center.add(spnl_Songs, BorderLayout.CENTER);
		
		JPanel pnl_Selection = new JPanel();
		pnl_Selection.setPreferredSize(new Dimension(this.getSize().width/6,0));
		spnl_Selection.add(pnl_Selection);
		//pnl_Center.add(pnl_Selection, BorderLayout.WEST);
		
		JPanel pnl_Songs = new JPanel();
		spnl_Songs.add(pnl_Songs);
		//pnl_Center.add(pnl_Songs, BorderLayout.CENTER);
	}

}
