package WindowBuilder;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JButton;
import java.awt.Font;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Color;
import javax.swing.ImageIcon;
import javax.swing.JCheckBox;
import java.awt.SystemColor;
import javax.swing.JTabbedPane;

public class Wframe extends JFrame {

	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Wframe frame = new Wframe();
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
	public Wframe() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(0, 0, 700, 380);
		contentPane = new JPanel();
		contentPane.setForeground(Color.LIGHT_GRAY);
		contentPane.setBackground(SystemColor.control);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JButton PoliticsButton = new JButton("\uC815\uCE58");
		PoliticsButton.setBounds(30, 21, 150, 50);
		contentPane.add(PoliticsButton);
		
		JButton FinanButton = new JButton("\uACBD\uC81C");
		FinanButton.setBounds(476, 21, 150, 50);
		contentPane.add(FinanButton);
		
		JButton SocietyButton = new JButton("\uC0AC\uD68C");
		SocietyButton.setBounds(260, 133, 150, 50);
		contentPane.add(SocietyButton);
		
		JButton LifeButton = new JButton("\uC0DD\uD65C/\uBB38\uD654");
		LifeButton.setBounds(30, 256, 150, 50);
		contentPane.add(LifeButton);
		
		JButton ITButton = new JButton("IT/\uACFC\uD559");
		ITButton.addActionListener(new ActionListener() {  // 회원 로그인 버튼 누를시
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				new IT(); // 회원 로그인 프레임 생성
				
			}
		});
		
		ITButton.setBounds(476, 256, 150, 50);
		contentPane.add(ITButton);
		
	
	}
}
