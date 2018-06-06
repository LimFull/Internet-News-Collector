package main;
import javax.swing.*; //프로젝트 실행환경 - JavaSE-1.7 

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import itpart.*;
import economypart.*;
import lifepart.*;
import politicspart.*;
import societypart.*;

public class mainframe extends JFrame {
	private static final String ACTION_COMMAND_REFRESH= "0";
	private static final String ACTION_COMMAND_IT= "1";
	private static final String ACTION_COMMAND_ECONOMY= "2";
	private static final String ACTION_COMMAND_LIFE= "3";
	private static final String ACTION_COMMAND_POLITICS= "4";
	private static final String ACTION_COMMAND_SOCIETY= "5";
	static int complete;
	public mainframe(){

	GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
	GraphicsDevice gd = ge.getDefaultScreenDevice();
	this.setTitle("OEnews");
	setUndecorated(true);  //타이틀바 제거
	panel a = new panel();
	a.setLayout(null);
	JButton refresh = new JButton(new ImageIcon("./img/refreshbutton.png"));
	JButton itbtn = new JButton(new ImageIcon("./img/normalit.png"));
	JButton societybtn = new JButton(new ImageIcon("./img/normalsociety.png"));
	JButton lifebtn = new JButton(new ImageIcon("./img/normallife.png"));
	JButton politicsbtn = new JButton(new ImageIcon("./img/normalpolitics.png"));
	JButton economybtn = new JButton(new ImageIcon("./img/normaleconomy.png"));
	//itbtn.setPreferredSize(new Dimension(100,100)); //절대위치
	//itbtn.setBounds(130,150,95,30);  //x,y,width,height
	//itbtn.setText("");  //버튼 텍스트
	refresh.setPressedIcon(new ImageIcon("./img/refreshbuttonpush.png"));
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
	refresh.setSize(50,50);
	
	itbtn.setLocation(115,140);
	societybtn.setLocation(451,140);
	lifebtn.setLocation(115,250);
	politicsbtn.setLocation(451,250);
	economybtn.setLocation(115,360);  //버튼위치
	refresh.setLocation(720,400);
	
	itbtn.setVisible(true);
	societybtn.setVisible(true);
	lifebtn.setVisible(true);
	politicsbtn.setVisible(true);
	economybtn.setVisible(true);
	
	refresh.setBorderPainted(false);
	itbtn.setBorderPainted(false);
	societybtn.setBorderPainted(false);
	lifebtn.setBorderPainted(false);
	politicsbtn.setBorderPainted(false);
	economybtn.setBorderPainted(false);  //버튼 외곽선
	
	refresh.setContentAreaFilled(false);
	itbtn.setContentAreaFilled(false);
	societybtn.setContentAreaFilled(false);
	lifebtn.setContentAreaFilled(false);
	politicsbtn.setContentAreaFilled(false);
	economybtn.setContentAreaFilled(false);  //버튼 내용영역
	
	refresh.setFocusPainted(false);
	itbtn.setFocusPainted(false);
	societybtn.setFocusPainted(false);
	lifebtn.setFocusPainted(false);
	politicsbtn.setFocusPainted(false);
	economybtn.setFocusPainted(false);  //버튼 선택 시 테두리
	
	//액션리스너 생성
	ActionListener listener = new ActionListener(){
		public void actionPerformed(ActionEvent e){
			if (e.getActionCommand().equals(ACTION_COMMAND_REFRESH)){
				complete = 0;
				//loading ld = new loading();
				//Rall al = new Rall();
				t1 t11 = new t1();
				t2 t22 = new t2();
				t11.start();
				t22.start();
				
			}
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
			if (e.getActionCommand().equals(ACTION_COMMAND_POLITICS)){
				politicscontent politicsc = new politicscontent();
				dispose();
			}
			if (e.getActionCommand().equals(ACTION_COMMAND_SOCIETY)){
				societycontent societyc = new societycontent();
				dispose();
			}
		}
	};
	
	refresh.addActionListener(listener);
	lifebtn.addActionListener(listener);
	itbtn.addActionListener(listener);
	economybtn.addActionListener(listener);
	politicsbtn.addActionListener(listener);
	societybtn.addActionListener(listener);
	a.add(itbtn); a.add(societybtn); a.add(lifebtn); a.add(politicsbtn); a.add(economybtn);
	a.add(refresh);
	this.add(a);
	this.setSize(800,480); //라즈베리파이 7인치 터치 스크린 해상도 
	this.setVisible(true);
	//gd.setFullScreenWindow(this);  //전체
	
	itbtn.setActionCommand(ACTION_COMMAND_IT); //리스너가 버튼을 구분할 때 사용함
	economybtn.setActionCommand(ACTION_COMMAND_ECONOMY);
	lifebtn.setActionCommand(ACTION_COMMAND_LIFE);
	politicsbtn.setActionCommand(ACTION_COMMAND_POLITICS);
	societybtn.setActionCommand(ACTION_COMMAND_SOCIETY);
	refresh.setActionCommand(ACTION_COMMAND_REFRESH);
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

 
class t1 extends Thread {
	public void run(){
		loading ld = new loading();
	}
}
class t2 extends Thread{
	public void run(){
		Rall ra = new Rall();
		mainframe.complete = 1;
	}
}