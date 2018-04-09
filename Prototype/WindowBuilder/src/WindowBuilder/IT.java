package WindowBuilder;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import Keyword1.Key1_1;

import javax.swing.JLabel;
import javax.swing.ImageIcon;
import java.awt.Font;
import java.awt.Color;
import javax.swing.JScrollBar;
import javax.swing.JList;
import javax.swing.JScrollPane;
import javax.swing.ScrollPaneConstants;
import javax.swing.SwingConstants;
import javax.swing.JButton;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class IT extends JFrame {

	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	
			/*public void run() {
	
					IT frame = new IT();
					frame.setVisible(true);
				 
			}*/

	/**
	 * Create the frame.
	 */
	public IT() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(0, 0, 700, 380);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel label = new JLabel("");
		label.setBounds(189, 10, 296, 321);
		label.setIcon(new ImageIcon("C:\\Users\\lhs\\Desktop\\\uCEA1\uC2A4\uD1A4\\IT2.png"));
		contentPane.add(label);
		setVisible(true);
		
		JLabel Keyword1 = new JLabel("\uAC8C\uC784 - 25\uD68C");
		
		Keyword1.setBounds(12, 64, 165, 56);
		Keyword1.setForeground(Color.BLACK);
		Keyword1.setFont(new Font("∏º¿∫ ∞ÌµÒ", Font.BOLD, 18));
		contentPane.add(Keyword1);
		
		JLabel Keyword2 = new JLabel("\uAC1C\uC778\uC815\uBCF4 - 22\uD68C");
		
		Keyword2.setBounds(12, 130, 165, 51);
		Keyword2.setFont(new Font("∏º¿∫ ∞ÌµÒ", Font.BOLD, 18));
		contentPane.add(Keyword2);
		
		JLabel Keyword3 = new JLabel("\uB300\uD45C - 17\uD68C");
		Keyword3.setBounds(12, 191, 165, 55);
		Keyword3.setFont(new Font("∏º¿∫ ∞ÌµÒ", Font.BOLD, 18));
		contentPane.add(Keyword3);
		
		JLabel Press1 = new JLabel("");
		Press1.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				new Keyword1.Key1_1();
			}
		});
		Press1.setFont(new Font("∏º¿∫ ∞ÌµÒ", Font.BOLD, 15));
		Press1.setBounds(529, 20, 143, 38);
		contentPane.add(Press1);
		
		JLabel Press2 = new JLabel("");
		Press2.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				new Keyword1.Key1_2();
			}
		});
		Press2.setFont(new Font("∏º¿∫ ∞ÌµÒ", Font.BOLD, 15));
		Press2.setBounds(529, 82, 143, 38);
		contentPane.add(Press2);
		
		JLabel Press3 = new JLabel("");
		Press3.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				new Keyword1.Key1_3();
			}
		});
		Press3.setFont(new Font("∏º¿∫ ∞ÌµÒ", Font.BOLD, 15));
		Press3.setBounds(529, 144, 143, 38);
		contentPane.add(Press3);
		
		JLabel Press4 = new JLabel("");
		Press4.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				new Keyword1.Key1_4();
			}
		});
		Press4.setFont(new Font("∏º¿∫ ∞ÌµÒ", Font.BOLD, 15));
		Press4.setBounds(529, 208, 143, 38);
		contentPane.add(Press4);
		
		JLabel Press5 = new JLabel("");
		Press5.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				new Keyword1.Key1_5();
			}
		});
		Press5.setFont(new Font("∏º¿∫ ∞ÌµÒ", Font.BOLD, 15));
		Press5.setBounds(529, 270, 143, 38);
		contentPane.add(Press5);
		
		
		Keyword1.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				Press1.setText("«Ï∑≤µÂ∞Ê¡¶ - 2»∏");
				Press2.setText("µøæ∆¿œ∫∏ - 1»∏");
				Press3.setText("¿Ãµ•¿œ∏Æ - 1»∏");
				Press4.setText("∆ƒ¿Ã≥Ωº»¥∫Ω∫ - 1»∏");
				Press5.setText("∏≈¿œ∞Ê¡¶ - 5»∏");	
			}
		});
		
		
		
		
		Keyword2.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				Press1.setText("ZD net Korea - 1»∏");
				Press2.setText("¿Ãµ•¿œ∏Æ - 1»∏");
				Press3.setText("¿¸¿⁄Ω≈πÆ - 1»∏");
			}
		});
		
		
		Keyword3.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				Press1.setText("¿¸¿⁄Ω≈πÆ - 2»∏");
				Press2.setText("æ∆Ω√æ∆∞Ê¡¶ - 1»∏");
				Press3.setText("∆ƒ¿Ã≥Ωº» ¥∫Ω∫ - 1»∏");
				Press4.setText("∏≈¿œ∞Ê¡¶ - 5»∏");
			}
		});
		
	}
}
