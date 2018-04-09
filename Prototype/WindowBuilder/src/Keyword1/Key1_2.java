package Keyword1;
import java.awt.BorderLayout;
import java.awt.Desktop;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import java.awt.Font;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;

public class Key1_2 extends JFrame {

	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	/*public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Key1_2 frame = new Key1_2();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}*/

	/**
	 * Create the frame.
	 */
	public Key1_2() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(50, 50, 550, 340);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		setVisible(true);
		
		JLabel News2 = new JLabel("\uB137\uB9C8\uBE14\uACAC\uD559\uD504\uB85C\uADF8\uB7A8, \uACBD\uC7C1\uB960 4:1 \uAE30\uB85D\uD558\uBA70 \uB9C8\uAC10");
		News2.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				try {
					Desktop.getDesktop().browse(new URI("http://news.naver.com/main/read.nhn?mode=LSD&mid=sec&sid1=105&oid=020&aid=0003139422"));
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				} catch (URISyntaxException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				} 
			}
		});
		News2.setFont(new Font("¸¼Àº °íµñ", Font.BOLD, 14));
		News2.setHorizontalAlignment(SwingConstants.CENTER);
		News2.setBounds(12, 29, 512, 57);
		contentPane.add(News2);
	}

}
