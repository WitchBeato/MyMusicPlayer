package userUI.subFrames;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.BorderLayout;
import java.awt.Dimension;

public class PlayButttons extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					PlayButttons frame = new PlayButttons();
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
	public PlayButttons() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 759, 131);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(new BorderLayout(0, 0));
		
		JPanel pnl_Buttons = new JPanel();
		contentPane.add(pnl_Buttons, BorderLayout.WEST);
		pnl_Buttons.setPreferredSize(new Dimension(this.getSize().width/7,0));
		
		JPanel pnl_SoundBass = new JPanel();
		contentPane.add(pnl_SoundBass, BorderLayout.EAST);
		
		JPanel Pnl_time = new JPanel();
		contentPane.add(Pnl_time, BorderLayout.CENTER);
		pnl_SoundBass.setPreferredSize(new Dimension(this.getSize().width/5,0));
	}

}
