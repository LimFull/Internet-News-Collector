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

public class Key1_1 extends JFrame {

	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	/*public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Key1_1 frame = new Key1_1();
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
	public Key1_1() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(50, 50, 550, 340);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel News1 = new JLabel("\uBAA8\uBE44\uD1A1, \uAC24\uB7ED\uC2DCS9?\uAC24\uB7ED\uC2DC\uB178\uD2B88 \uAD6C\uB9E4 \uC0AC\uC740\uD488 '\uACF5\uAE30\uCCAD\uC815\uAE30'?'G\uD328\uB4DC' \uC99D\uC815");
		News1.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				try {
					Desktop.getDesktop().browse(new URI("http://news.naver.com/main/read.nhn?mode=LSD&mid=sec&sid1=105&oid=016&aid=0001377838"));
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				} catch (URISyntaxException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				} 
			}
		});
		News1.setFont(new Font("¸¼Àº °íµñ", Font.BOLD, 14));
		News1.setHorizontalAlignment(SwingConstants.CENTER);
		News1.setBounds(12, 28, 512, 69);
		contentPane.add(News1);
		
		JLabel News3 = new JLabel("\uC560\uB4DC\uBC15\uC2A4, \uC2E0\uC791 \uBAA8\uBC14\uC77C\uAC8C\uC784 \u2018\uB178\uBE14\uB808\uC2A4M with NAVER WEBTOON\u2019 \uCEA0\uD398\uC778 \uCD94\uAC00");
		News3.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				try {
					Desktop.getDesktop().browse(new URI("http://news.naver.com/main/read.nhn?mode=LSD&mid=sec&sid1=105&oid=016&aid=0001377837"));
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				} catch (URISyntaxException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				} 
			}
		});
		News3.setHorizontalAlignment(SwingConstants.CENTER);
		News3.setFont(new Font("¸¼Àº °íµñ", Font.BOLD, 14));
		News3.setBounds(12, 99, 512, 75);
		contentPane.add(News3);
		setVisible(true);
	}

}
