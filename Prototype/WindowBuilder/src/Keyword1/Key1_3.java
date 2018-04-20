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
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class Key1_3 extends JFrame {

	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	/*public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Key1_3 frame = new Key1_3();
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
	public Key1_3() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(50, 50, 550, 340);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel News4 = new JLabel("\uB125\uC2A8, \uC885\uB85C \uC138\uC6B4\uC0C1\uAC00\uC11C \uCF58\uD150\uCE20 \uCD95\uC81C \u2018\uB124\uCF54\uC81C\u2019 \uAC1C\uCD5C");
		News4.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				try {
					Desktop.getDesktop().browse(new URI("http://news.naver.com/main/read.nhn?mode=LSD&mid=sec&sid1=105&oid=018&aid=0004075229"));
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				} catch (URISyntaxException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				} 
			}
		});
		News4.setHorizontalAlignment(SwingConstants.CENTER);
		News4.setFont(new Font("¸¼Àº °íµñ", Font.BOLD, 14));
		News4.setBounds(12, 10, 512, 66);
		contentPane.add(News4);
		
		JButton DButton3 = new JButton("\uCC3D\uB2EB\uAE30");
		DButton3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		DButton3.setFont(new Font("¸¼Àº °íµñ", Font.BOLD, 14));
		DButton3.setBounds(397, 242, 127, 49);
		contentPane.add(DButton3);
		setVisible(true);
	}

}
