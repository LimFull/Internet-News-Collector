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

public class Key1_5 extends JFrame {

	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	/*public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Key1_5 frame = new Key1_5();
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
	public Key1_5() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(50, 50, 550, 340);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel News6 = new JLabel("\uB137\uB9C8\uBE14\uBB38\uD654\uC7AC\uB2E8, `2018 \uB137\uB9C8\uBE14\uACAC\uD559\uD504\uB85C\uADF8\uB7A8` \uBAA8\uC9D1\uC5D0 4\uB3001 \uACBD\uC7C1\uB960");
		News6.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				try {
					Desktop.getDesktop().browse(new URI("http://news.naver.com/main/read.nhn?mode=LSD&mid=sec&sid1=105&oid=009&aid=0004130244"));
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				} catch (URISyntaxException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				} 
			}
		});
		News6.setHorizontalAlignment(SwingConstants.CENTER);
		News6.setFont(new Font("¸¼Àº °íµñ", Font.BOLD, 14));
		News6.setBounds(12, 10, 512, 43);
		contentPane.add(News6);
		
		JLabel News7 = new JLabel("\uD55C\uBE5B\uC18C\uD504\uD2B8, \uAC8C\uC784\uAC1C\uBC1C \uC811\uBAA9\uD55C \uCF54\uB529\uC218\uC5C5 \uD560\uC778 \uC624\uD508");
		News7.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				try {
					Desktop.getDesktop().browse(new URI("http://news.naver.com/main/read.nhn?mode=LSD&mid=sec&sid1=105&oid=009&aid=0004130242"));
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				} catch (URISyntaxException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				} 
			}
		});
		News7.setHorizontalAlignment(SwingConstants.CENTER);
		News7.setFont(new Font("¸¼Àº °íµñ", Font.BOLD, 14));
		News7.setBounds(22, 63, 502, 43);
		contentPane.add(News7);
		
		JLabel News8 = new JLabel("\uB137\uB9C8\uBE14\uBB38\uD654\uC7AC\uB2E8, `2018 \uB137\uB9C8\uBE14\uACAC\uD559\uD504\uB85C\uADF8\uB7A8` \uBAA8\uC9D1\uC5D0 4\uB3001 \uACBD\uC7C1\uB960");
		News8.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				try {
					Desktop.getDesktop().browse(new URI("http://news.naver.com/main/read.nhn?mode=LSD&mid=sec&sid1=105&oid=009&aid=0004130244"));
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				} catch (URISyntaxException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				} 
			}
		});
		News8.setHorizontalAlignment(SwingConstants.CENTER);
		News8.setFont(new Font("¸¼Àº °íµñ", Font.BOLD, 14));
		News8.setBounds(32, 116, 492, 43);
		contentPane.add(News8);
		
		JLabel News9 = new JLabel("\uB4DC\uB798\uACE4\uD50C\uB77C\uC774, \uD504\uB791\uC2A4 \uCE78 \u2018MIPTV\u2019\uC5D0 \u2018\uC2A4\uD398\uC15C\uD3EC\uC2A4VR\u2019 \uC804\uC2DC");
		News9.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				try {
					Desktop.getDesktop().browse(new URI("http://news.naver.com/main/read.nhn?mode=LSD&mid=sec&sid1=105&oid=009&aid=0004130241"));
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				} catch (URISyntaxException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				} 
			}
		});
		News9.setHorizontalAlignment(SwingConstants.CENTER);
		News9.setFont(new Font("¸¼Àº °íµñ", Font.BOLD, 14));
		News9.setBounds(12, 169, 512, 43);
		contentPane.add(News9);
		
		JLabel News10 = new JLabel("2030 \uC0AC\uB85C\uC7A1\uC740 \u2018\uB77C\uADF8\uB098\uB85C\uD06CM\u2019, \uD55C \uB2EC\uC9F8 \uC21C\uD56D");
		News10.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				try {
					Desktop.getDesktop().browse(new URI("http://news.naver.com/main/read.nhn?mode=LSD&mid=sec&sid1=105&oid=009&aid=0004130239"));
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				} catch (URISyntaxException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				} 
			}
		});
		News10.setFont(new Font("¸¼Àº °íµñ", Font.BOLD, 14));
		News10.setHorizontalAlignment(SwingConstants.CENTER);
		News10.setBounds(12, 222, 512, 43);
		contentPane.add(News10);
		
		JButton btnNewButton = new JButton("\uCC3D\uB2EB\uAE30");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		btnNewButton.setFont(new Font("¸¼Àº °íµñ", Font.BOLD, 14));
		btnNewButton.setBounds(396, 268, 128, 33);
		contentPane.add(btnNewButton);
		setVisible(true);
	}

}
