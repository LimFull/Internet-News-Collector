package itpart;
import javax.swing.*; //프로젝트 실행환경 - JavaSE-1.7 

import main.*;




import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class itcontent extends JFrame {
	private static final String ACTION_COMMAND_BACK= "1";
	private static final String ACTION_COMMAND_ONE= "2";
	private static final String ACTION_COMMAND_TWO= "3";
	private static final String ACTION_COMMAND_THREE= "4";
	private static final String ACTION_COMMAND_FOUR= "5";
	private static final String ACTION_COMMAND_FIVE= "6";
	itspeech its = new itspeech(); //언론사 수 셈
	panel a = new panel();
	JButton[] sb = new JButton[7]; 
	
public itcontent(){
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
	
	itkeywords itk = new itkeywords();  //버튼에 키워드를 불러올 객체
	
	JButton word1 = new JButton(); JButton word2 = new JButton(); 
	JButton word3 = new JButton(); JButton word4 = new JButton(); 
	JButton word5 = new JButton();
	word1.setText(itk.getwords(0)); word2.setText(itk.getwords(1));
	word3.setText(itk.getwords(2)); word4.setText(itk.getwords(3));
	word5.setText(itk.getwords(4));
	word1.setSize(100,45); word2.setSize(100,45); word3.setSize(100,45); word4.setSize(100,45); word5.setSize(100,45);
	word1.setLocation(65,95); word2.setLocation(65,162); word3.setLocation(65,229); word4.setLocation(65,296); word5.setLocation(65,363);
	for (int i = 0; i<7; i++){
	a.add(sb[i] = new JButton(""));
	sb[i].setSize(120,45);
	sb[i].setLocation(625,i*50+95);
	sb[i].setVisible(false);
	}
	
	ActionListener listener = new ActionListener(){
		public void actionPerformed(ActionEvent e){
			if (e.getActionCommand().equals(ACTION_COMMAND_BACK)){
				mainframe mf = new mainframe();
				dispose();
			}
			if (e.getActionCommand().equals(ACTION_COMMAND_ONE)){
				int i=0; 
				while(i<its.sclength[0]){
					sb[i].setVisible(true);
					sb[i].setText(its.getname(0, i));
					i++;
					if (i==7) break;
				}
				while(i<7){
					sb[i].setVisible(false);
					i++;
				}
			}
			if (e.getActionCommand().equals(ACTION_COMMAND_TWO)){
				int i=0; 
				while(i<its.sclength[1]){
					sb[i].setVisible(true);
					sb[i].setText(its.getname(1, i));
					i++;
					if (i==7) break;
				}
				while(i<7){
					sb[i].setVisible(false);
					i++;
				}
			}
			if (e.getActionCommand().equals(ACTION_COMMAND_THREE)){
				int i=0; 
				while(i<its.sclength[2]){
					sb[i].setVisible(true);
					sb[i].setText(its.getname(2, i));
					i++;
					if (i==7) break;
				}
				while(i<7){
					sb[i].setVisible(false);
					i++;
				}
			}
			if (e.getActionCommand().equals(ACTION_COMMAND_FOUR)){
				int i=0; 
				while(i<its.sclength[3]){
					sb[i].setVisible(true);
					sb[i].setText(its.getname(3, i));
					i++;
					if (i==7) break;
				}
				while(i<7){
					sb[i].setVisible(false);
					i++;
				}
			}
			if (e.getActionCommand().equals(ACTION_COMMAND_FIVE)){
				int i=0; 
				while(i<its.sclength[4]){
					sb[i].setVisible(true);
					sb[i].setText(its.getname(4, i));
					i++;
					if (i==7) break;
				}
				while(i<7){
					sb[i].setVisible(false);
					i++;
				}
			}
			}
	};
	
	backbtn.setActionCommand(ACTION_COMMAND_BACK);
	backbtn.addActionListener(listener);
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
	a.add(backbtn);
	a.add(word1); a.add(word2); a.add(word3); a.add(word4); a.add(word5); //키워드 버튼 추가
	this.add(a);
	this.setSize(800,480);
	this.setVisible(true);
	 
	//gd.setFullScreenWindow(this); 
 /*
	for (int i =0; i<its.sclength[0]; i++){
		JButton buttons = new JButton(its.getname(0, i));
		buttons.setSize(120,45);
		buttons.setLocation(625,i*50+95);
		this.add(buttons);
	}
	*/
}
}

class panel extends JPanel
{
	ImageIcon bgimg = new ImageIcon("./img/barbackground.png");
	Image img = bgimg.getImage();
	ImageIcon cloudimg = new ImageIcon("cloudit.png");
	Image img2 = cloudimg.getImage();
	public void paintComponent(Graphics g)
	{
		super.paintComponent(g);
		g.drawImage(img, 0, 0, this.getWidth(),this.getHeight(),this);
		g.drawImage(img2, 400-(350/2), 80, 350, 350, this);
	}
}
