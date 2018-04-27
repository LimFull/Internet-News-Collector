package main;
import javax.swing.*; //프로젝트 실행환경 - JavaSE-1.7 

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import itpart.*;
import economypart.*;
import lifepart.*;

public class mainframe extends JFrame {
	private static final String ACTION_COMMAND_IT= "1";
	private static final String ACTION_COMMAND_ECONOMY= "2";
	private static final String ACTION_COMMAND_LIFE= "3";
 	
	public mainframe(){

	GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
	GraphicsDevice gd = ge.getDefaultScreenDevice();
	this.setTitle("OEnews");
	setUndecorated(true);  //타이틀바 제거
	
	panel a = new panel();
	a.setLayout(null);
	JButton itbtn = new JButton(new ImageIcon("./img/normalit.png"));
	JButton societybtn = new JButton(new ImageIcon("./img/normalsociety.png"));
	JButton lifebtn = new JButton(new ImageIcon("./img/normallife.png"));
	JButton politicsbtn = new JButton(new ImageIcon("./img/normalpolitics.png"));
	JButton economybtn = new JButton(new ImageIcon("./img/normaleconomy.png"));
	//itbtn.setPreferredSize(new Dimension(100,100)); //절대위치
	//itbtn.setBounds(130,150,95,30);  //x,y,width,height
	//itbtn.setText("");  //버튼 텍스트
	itbtn.setPressedIcon(new ImageIcon("./img/pressedit.png"));
	societybtn.setPressedIcon(new ImageIcon("./img/pressedsociety.png"));  //버튼 클릭 이미지
	lifebtn.setPressedIcon(new ImageIcon("./img/pressedlife.png"));
	politicsbtn.setPressedIcon(new ImageIcon("./img/pressedpolitics.png"));
	economybtn.setPressedIcon(new ImageIcon("./img/pressedeconomy.png"));
	itbtn.setSize(234,60);
	societybtn.setSize(234,60);
	lifebtn.setSize(234,60);
	politicsbtn.setSize(234,60);
	economybtn.setSize(234,60);   //버튼 크기
	
	itbtn.setLocation(115,140);
	societybtn.setLocation(451,140);
	lifebtn.setLocation(115,250);
	politicsbtn.setLocation(451,250);
	economybtn.setLocation(115,360);  //버튼위치
	
	itbtn.setVisible(true);
	societybtn.setVisible(true);
	lifebtn.setVisible(true);
	politicsbtn.setVisible(true);
	economybtn.setVisible(true);
	
	itbtn.setBorderPainted(false);
	societybtn.setBorderPainted(false);
	lifebtn.setBorderPainted(false);
	politicsbtn.setBorderPainted(false);
	economybtn.setBorderPainted(false);  //버튼 외곽선
	
	itbtn.setContentAreaFilled(false);
	societybtn.setContentAreaFilled(false);
	lifebtn.setContentAreaFilled(false);
	politicsbtn.setContentAreaFilled(false);
	economybtn.setContentAreaFilled(false);  //버튼 내용영역
	
	itbtn.setFocusPainted(false);
	societybtn.setFocusPainted(false);
	lifebtn.setFocusPainted(false);
	politicsbtn.setFocusPainted(false);
	economybtn.setFocusPainted(false);  //버튼 선택 시 테두리
	
	//액션리스너 생성
	ActionListener listener = new ActionListener(){
		public void actionPerformed(ActionEvent e){
			if (e.getActionCommand().equals(ACTION_COMMAND_IT)){
				itcontent itc = new itcontent();
				dispose();
				}
			if (e.getActionCommand().equals(ACTION_COMMAND_ECONOMY)){
				economycontent economyc = new economycontent();
				dispose();
			}
			if (e.getActionCommand().equals(ACTION_COMMAND_LIFE)){
				lifecontent lifec = new lifecontent();
				dispose();
			}
		}
	};
	
	lifebtn.addActionListener(listener);
	itbtn.addActionListener(listener);
	economybtn.addActionListener(listener);
	a.add(itbtn); a.add(societybtn); a.add(lifebtn); a.add(politicsbtn); a.add(economybtn);
	this.add(a);
	this.setSize(800,480); //라즈베리파이 7인치 터치 스크린 해상도 
	this.setVisible(true);
	gd.setFullScreenWindow(this);  //전체
	
	itbtn.setActionCommand(ACTION_COMMAND_IT);
	economybtn.setActionCommand(ACTION_COMMAND_ECONOMY);
	lifebtn.setActionCommand(ACTION_COMMAND_LIFE);


	}//여기까지 생성자
 
}

class panel extends JPanel
{
	ImageIcon bgimg = new ImageIcon("./img/background.png");
	Image img = bgimg.getImage();
	public void paintComponent(Graphics g)
	{
		super.paintComponent(g);
		g.drawImage(img, 0, 0, this.getWidth(),this.getHeight(),this);
	}
}

 
