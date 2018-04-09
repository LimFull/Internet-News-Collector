package Keyword1;

import java.awt.BorderLayout;
import java.awt.Desktop;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.SwingConstants;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;

public class Key1_4 extends JFrame {

	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	/*public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Key1_4 frame = new Key1_4();
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
	public Key1_4() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(50, 50, 550, 340);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel News5 = new JLabel("\uAC8C\uC784\uC5C5\uACC4, \uBE14\uB85D\uCCB4\uC778 \uC811\uBAA9 \uC131\uACF5\uBAA8\uB378 \uCC3E\uB294\uB2E4");
		News5.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				try {
					Desktop.getDesktop().browse(new URI("http://news.naver.com/main/read.nhn?mode=LSD&mid=sec&sid1=105&oid=014&aid=0003998310"));
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				} catch (URISyntaxException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				} 
			}
		});
		News5.setHorizontalAlignment(SwingConstants.CENTER);
		News5.setFont(new Font("¸¼Àº °íµñ", Font.BOLD, 14));
		News5.setBounds(12, 10, 512, 55);
		contentPane.add(News5);
		setVisible(true);
	}

}
