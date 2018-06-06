package economypart;


import javax.swing.*; //프로젝트 실행환경 - JavaSE-1.7 

import main.mainframe;




import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class economycontent extends JFrame {
	private static final String ACTION_COMMAND_BACK= "1";
	private static final String ACTION_COMMAND_ONE= "2";
	private static final String ACTION_COMMAND_TWO= "3";
	private static final String ACTION_COMMAND_THREE= "4";
	private static final String ACTION_COMMAND_FOUR= "5";
	private static final String ACTION_COMMAND_FIVE= "6";
	private static final String ACTION_SPEECH_ONE= "11";
	private static final String ACTION_SPEECH_TWO= "22";
	private static final String ACTION_SPEECH_THREE= "33";
	private static final String ACTION_SPEECH_FOUR= "44";
	private static final String ACTION_SPEECH_FIVE= "55";
	private static final String ACTION_SPEECH_SIX= "66";
	private static final String ACTION_SPEECH_SEVEN= "77";
	economyspeech economys = new economyspeech(); //언론사 수 셈
	panel a = new panel();
	JButton[] sb = new JButton[7]; 
	int word;
	JButton word1 = new JButton(new ImageIcon("./img/simplebutton.png"));
	JButton word2 = new JButton(new ImageIcon("./img/simplebutton.png"));
	JButton word3 = new JButton(new ImageIcon("./img/simplebutton.png")); 
	JButton word4 = new JButton(new ImageIcon("./img/simplebutton.png")); 
	JButton word5 = new JButton(new ImageIcon("./img/simplebutton.png"));
public economycontent(){
	setUndecorated(true);
	GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
	GraphicsDevice gd = ge.getDefaultScreenDevice();
	
	a.setLayout(null);
	JButton backbtn = new JButton(new ImageIcon("./img/backbtn.png"));
	backbtn.setPressedIcon(new ImageIcon("./img/pressedbackbtn.png"));
	backbtn.setSize(45,45);
	backbtn.setVisible(true);
	backbtn.setLocation(15,43);
	backbtn.setBorderPainted(false);  //버튼 외곽선
	backbtn.setContentAreaFilled(false);
	backbtn.setFocusPainted(false);
	
	economykeywords economyk = new economykeywords();  //버튼에 키워드를 불러올 객체
	
	   
	word1.setBorderPainted(false);
	word1.setContentAreaFilled(false);
	word1.setFocusPainted(false);
	word1.setHorizontalTextPosition(JButton.CENTER);
	word1.setVerticalTextPosition(JButton.CENTER);
	word1.setForeground(Color.BLACK);
	word2.setBorderPainted(false);
	word2.setContentAreaFilled(false);
	word2.setFocusPainted(false);
	word2.setHorizontalTextPosition(JButton.CENTER);
	word2.setVerticalTextPosition(JButton.CENTER);
	word2.setForeground(Color.BLACK);
	word3.setBorderPainted(false);
	word3.setContentAreaFilled(false);
	word3.setFocusPainted(false);
	word3.setHorizontalTextPosition(JButton.CENTER);
	word3.setVerticalTextPosition(JButton.CENTER);
	word3.setForeground(Color.BLACK);
	word4.setBorderPainted(false);
	word4.setContentAreaFilled(false);
	word4.setFocusPainted(false);
	word4.setHorizontalTextPosition(JButton.CENTER);
	word4.setVerticalTextPosition(JButton.CENTER);
	word4.setForeground(Color.BLACK);
	word5.setBorderPainted(false);
	word5.setContentAreaFilled(false);
	word5.setFocusPainted(false);
	word5.setHorizontalTextPosition(JButton.CENTER);
	word5.setVerticalTextPosition(JButton.CENTER);
	word5.setForeground(Color.BLACK);
	word1.setText(economyk.getwords(0)); word2.setText(economyk.getwords(1));
	word3.setText(economyk.getwords(2)); word4.setText(economyk.getwords(3));
	word5.setText(economyk.getwords(4));
	word1.setSize(100,45); word2.setSize(100,45); word3.setSize(100,45); word4.setSize(100,45); word5.setSize(100,45);
	word1.setLocation(65,95); word2.setLocation(65,162); word3.setLocation(65,229); word4.setLocation(65,296); word5.setLocation(65,363);
	for (int i = 0; i<7; i++){
		a.add(sb[i] = new JButton(new ImageIcon("./img/speechbutton.png")));
		sb[i].setPressedIcon(new ImageIcon("./img/speechbuttonpush.png"));
		sb[i].setSize(120,45);
		sb[i].setLocation(625,i*50+95);
		sb[i].setVisible(false);
		sb[i].setBorderPainted(false);
		sb[i].setContentAreaFilled(false);
		sb[i].setFocusPainted(false);
		sb[i].setHorizontalTextPosition(JButton.CENTER);
		sb[i].setVerticalTextPosition(JButton.CENTER);
	}
	
	ActionListener listener = new ActionListener(){
		public void actionPerformed(ActionEvent e){
			if (e.getActionCommand().equals(ACTION_COMMAND_BACK)){
				mainframe mf = new mainframe();
				dispose();
			}
			if (e.getActionCommand().equals(ACTION_COMMAND_ONE)){
				word1.setIcon(new ImageIcon("./img/simplebuttonpush.png"));
				word2.setIcon(new ImageIcon("./img/simplebutton.png"));
				word3.setIcon(new ImageIcon("./img/simplebutton.png"));
				word4.setIcon(new ImageIcon("./img/simplebutton.png"));
				word5.setIcon(new ImageIcon("./img/simplebutton.png"));
				word1.setForeground(Color.WHITE);
				word2.setForeground(Color.BLACK);
				word3.setForeground(Color.BLACK);
				word4.setForeground(Color.BLACK);
				word5.setForeground(Color.BLACK);
				int i=0; 
				word = 0;
				while(i<economys.sclength[0]){
					sb[i].setVisible(true);
					sb[i].setText(economys.getname(0, i));
					i++;
					if (i==7) break;
				}
				while(i<7){
					sb[i].setVisible(false);
					i++;
				}
			}
			if (e.getActionCommand().equals(ACTION_COMMAND_TWO)){
				word1.setIcon(new ImageIcon("./img/simplebutton.png"));
				word2.setIcon(new ImageIcon("./img/simplebuttonpush.png"));
				word3.setIcon(new ImageIcon("./img/simplebutton.png"));
				word4.setIcon(new ImageIcon("./img/simplebutton.png"));
				word5.setIcon(new ImageIcon("./img/simplebutton.png"));
				word1.setForeground(Color.BLACK);
				word2.setForeground(Color.WHITE);
				word3.setForeground(Color.BLACK);
				word4.setForeground(Color.BLACK);
				word5.setForeground(Color.BLACK);
				int i=0; 
				word = 1;
				while(i<economys.sclength[1]){
					sb[i].setVisible(true);
					sb[i].setText(economys.getname(1, i));
					i++;
					if (i==7) break;
				}
				while(i<7){
					sb[i].setVisible(false);
					i++;
				}
			}
			if (e.getActionCommand().equals(ACTION_COMMAND_THREE)){
				word1.setIcon(new ImageIcon("./img/simplebutton.png"));
				word2.setIcon(new ImageIcon("./img/simplebutton.png"));
				word3.setIcon(new ImageIcon("./img/simplebuttonpush.png"));
				word4.setIcon(new ImageIcon("./img/simplebutton.png"));
				word5.setIcon(new ImageIcon("./img/simplebutton.png"));
				word1.setForeground(Color.BLACK);
				word2.setForeground(Color.BLACK);
				word3.setForeground(Color.WHITE);
				word4.setForeground(Color.BLACK);
				word5.setForeground(Color.BLACK);
				int i=0; 
				word = 2;
				while(i<economys.sclength[2]){
					sb[i].setVisible(true);
					sb[i].setText(economys.getname(2, i));
					i++;
					if (i==7) break;
				}
				while(i<7){
					sb[i].setVisible(false);
					i++;
				}
			}
			if (e.getActionCommand().equals(ACTION_COMMAND_FOUR)){
				word1.setIcon(new ImageIcon("./img/simplebutton.png"));
				word2.setIcon(new ImageIcon("./img/simplebutton.png"));
				word3.setIcon(new ImageIcon("./img/simplebutton.png"));
				word4.setIcon(new ImageIcon("./img/simplebuttonpush.png"));
				word5.setIcon(new ImageIcon("./img/simplebutton.png"));
				word1.setForeground(Color.BLACK);
				word2.setForeground(Color.BLACK);
				word3.setForeground(Color.BLACK);
				word4.setForeground(Color.WHITE);
				word5.setForeground(Color.BLACK);
				int i=0; 
				word = 3;
				while(i<economys.sclength[3]){
					sb[i].setVisible(true);
					sb[i].setText(economys.getname(3, i));
					i++;
					if (i==7) break;
				}
				while(i<7){
					sb[i].setVisible(false);
					i++;
				}
			}
			if (e.getActionCommand().equals(ACTION_COMMAND_FIVE)){
				word1.setIcon(new ImageIcon("./img/simplebutton.png"));
				word2.setIcon(new ImageIcon("./img/simplebutton.png"));
				word3.setIcon(new ImageIcon("./img/simplebutton.png"));
				word4.setIcon(new ImageIcon("./img/simplebutton.png"));
				word5.setIcon(new ImageIcon("./img/simplebuttonpush.png"));
				word1.setForeground(Color.BLACK);
				word2.setForeground(Color.BLACK);
				word3.setForeground(Color.BLACK);
				word4.setForeground(Color.BLACK);
				word5.setForeground(Color.WHITE);
				int i=0; 
				word = 4;
				while(i<economys.sclength[4]){
					sb[i].setVisible(true);
					sb[i].setText(economys.getname(4, i));
					i++;
					if (i==7) break;
				}
				while(i<7){
					sb[i].setVisible(false);
					i++;
				}
			}
			if (e.getActionCommand().equals(ACTION_SPEECH_ONE)){
				int a[] = new int[20];
				economytitle economyt = new economytitle(word, economys.gettitlenumber(word, 0)); // 특정 키워드의 특정 번호의 언론사의 배열 위치를 넘겨줌
			}
			if (e.getActionCommand().equals(ACTION_SPEECH_TWO)){
				int a[] = new int[20];
				economytitle economyt = new economytitle(word, economys.gettitlenumber(word, 1));
			}
			if (e.getActionCommand().equals(ACTION_SPEECH_THREE)){
				int a[] = new int[20];
				economytitle economyt = new economytitle(word, economys.gettitlenumber(word, 2));
			}
			if (e.getActionCommand().equals(ACTION_SPEECH_FOUR)){
				int a[] = new int[20];
				economytitle economyt = new economytitle(word, economys.gettitlenumber(word, 3));
			}
			if (e.getActionCommand().equals(ACTION_SPEECH_FIVE)){
				int a[] = new int[20];
				economytitle economyt = new economytitle(word, economys.gettitlenumber(word, 4));
			}
			if (e.getActionCommand().equals(ACTION_SPEECH_SIX)){
				int a[] = new int[20];
				economytitle economyt = new economytitle(word, economys.gettitlenumber(word, 5));
			}
			if (e.getActionCommand().equals(ACTION_SPEECH_SEVEN)){
				int a[] = new int[20];
				economytitle economyt = new economytitle(word, economys.gettitlenumber(word, 6));
			}
			}
	};
	
	backbtn.setActionCommand(ACTION_COMMAND_BACK);
	backbtn.addActionListener(listener);
	word1.setActionCommand(ACTION_COMMAND_ONE);
	word1.addActionListener(listener);
	word2.setActionCommand(ACTION_COMMAND_TWO);
	word2.addActionListener(listener);
	word3.setActionCommand(ACTION_COMMAND_THREE);
	word3.addActionListener(listener);
	word4.setActionCommand(ACTION_COMMAND_FOUR);
	word4.addActionListener(listener);
	word5.setActionCommand(ACTION_COMMAND_FIVE);
	word5.addActionListener(listener);
	sb[0].setActionCommand(ACTION_SPEECH_ONE);
	sb[0].addActionListener(listener);
	sb[1].setActionCommand(ACTION_SPEECH_TWO);
	sb[1].addActionListener(listener);
	sb[2].setActionCommand(ACTION_SPEECH_THREE);
	sb[2].addActionListener(listener);
	sb[3].setActionCommand(ACTION_SPEECH_FOUR);
	sb[3].addActionListener(listener);
	sb[4].setActionCommand(ACTION_SPEECH_FIVE);
	sb[4].addActionListener(listener);
	sb[5].setActionCommand(ACTION_SPEECH_SIX);
	sb[5].addActionListener(listener);
	sb[6].setActionCommand(ACTION_SPEECH_SEVEN);
	sb[6].addActionListener(listener);
	a.add(backbtn);
	a.add(word1); a.add(word2); a.add(word3); a.add(word4); a.add(word5); //키워드 버튼 추가
	this.add(a);
	this.setSize(800,480);
	this.setVisible(true);
	 
}
}

class panel extends JPanel
{
	ImageIcon bgimg = new ImageIcon("./img/barbackground.png");
	Image img = bgimg.getImage();
	ImageIcon cloudimg = new ImageIcon("cloudeconomy.png");
	Image img2 = cloudimg.getImage();
	public void paintComponent(Graphics g)
	{
		super.paintComponent(g);
		g.drawImage(img, 0, 0, this.getWidth(),this.getHeight(),this);
		g.drawImage(img2, 400-(350/2), 80, 350, 350, this);
	}
}
