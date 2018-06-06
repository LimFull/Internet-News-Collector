package main;
import javax.swing.*; //프로젝트 실행환경 - JavaSE-1.7 

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

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
	List<List<String>> ret = new ArrayList<List<String>>(); // url을 저장할 문자열 리스트
	public mainframe(){

		BufferedReader br = null;
		try{
			br = Files.newBufferedReader(Paths.get("Rdata/LIFEwordurl.csv")); //csv파일을 읽음  
			Charset.forName("UTF-8");  //UTF-8 형식
			String line = "";
			
			while((line = br.readLine()) != null){
				List<String> tmplist = new ArrayList<String>(); //url 저장할 tmplist 객체생성
				String array[] = line.split(","); // 콤마(,) 단위로 분리
				tmplist = Arrays.asList(array); //tmplist에 저장
				ret.add(tmplist);	// 한 줄씩 ret에 추가
			}
			br = Files.newBufferedReader(Paths.get("Rdata/ITwordurl.csv")); //csv파일을 읽음
			while((line = br.readLine()) != null){
				List<String> tmplist = new ArrayList<String>(); //url 저장할 tmplist 객체생성
				String array[] = line.split(","); // 콤마(,) 단위로 분리
				tmplist = Arrays.asList(array); //tmplist에 저장
				ret.add(tmplist);	// 한 줄씩 ret에 추가
			}
			br = Files.newBufferedReader(Paths.get("Rdata/SOCIETYwordurl.csv")); //csv파일을 읽음
			while((line = br.readLine()) != null){
				List<String> tmplist = new ArrayList<String>(); //url 저장할 tmplist 객체생성
				String array[] = line.split(","); // 콤마(,) 단위로 분리
				tmplist = Arrays.asList(array); //tmplist에 저장
				ret.add(tmplist);	// 한 줄씩 ret에 추가
			}
			br = Files.newBufferedReader(Paths.get("Rdata/ECONOMYwordurl.csv")); //csv파일을 읽음
			while((line = br.readLine()) != null){
				List<String> tmplist = new ArrayList<String>(); //url 저장할 tmplist 객체생성
				String array[] = line.split(","); // 콤마(,) 단위로 분리
				tmplist = Arrays.asList(array); //tmplist에 저장
				ret.add(tmplist);	// 한 줄씩 ret에 추가
			}
			br = Files.newBufferedReader(Paths.get("Rdata/POLITICSwordurl.csv")); //csv파일을 읽음
			while((line = br.readLine()) != null){
				List<String> tmplist = new ArrayList<String>(); //url 저장할 tmplist 객체생성
				String array[] = line.split(","); // 콤마(,) 단위로 분리
				tmplist = Arrays.asList(array); //tmplist에 저장
				ret.add(tmplist);	// 한 줄씩 ret에 추가
			}
		}catch(FileNotFoundException e){
			e.printStackTrace();
		}catch(IOException e){
			e.printStackTrace();
		}finally{
			try{
				if(br != null){
					br.close();  //파일 읽기 종료
				}
			}catch(IOException e){
				e.printStackTrace();
			}
		}
		 
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
				dispose();
				
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
	
	List<String> lstr = new ArrayList<String>();
	
	int hotword[] = new int[25];
	int k = -1;
	for (int i = 0 ; i < 30 ; i++){
		if ((i%6)!=0){
			k++;
			lstr = ret.get(i);
			int j = 0;
			while (!lstr.get(j).equals("NA")){
				 hotword[k] = hotword[k]+1;
				 j++;
			}
		}
	}
	
int bignum=0;
int bigcnt=0;
int cate=0;
for (int i = 0; i<25 ; i++){
	if (hotword[i]>bigcnt) {
		bigcnt=hotword[i];
		bignum = i;
	}
}
	
List<String> ret2 = new ArrayList<String>();
try{
	
	if (bignum <5) {
		br = Files.newBufferedReader(Paths.get("Rdata/LIFEkeywords.csv")); //csv파일을 읽음
		cate = 1;
	}
	else if (bignum <10) {
		br = Files.newBufferedReader(Paths.get("Rdata/ITkeywords.csv"));
		cate = 2;
	}
	else if (bignum <15) {
		br = Files.newBufferedReader(Paths.get("Rdata/SOCIETYkeywords.csv"));
		cate = 3;
	}
	else if (bignum <20) {
		br = Files.newBufferedReader(Paths.get("Rdata/ECONOMYITkeywords.csv"));
		cate = 4;
	}
	else {
		br = Files.newBufferedReader(Paths.get("Rdata/POLITICSITkeywords.csv"));
		cate = 5;
	}

	bignum = bignum%5;
	Charset.forName("UTF-8");  //UTF-8 형식
	String line = "";
	while((line = br.readLine()) != null){
		ret2.add(line);	// 한 줄씩 ret에 추가
	}
}catch(FileNotFoundException e){
	e.printStackTrace();
}catch(IOException e){
	e.printStackTrace();
}finally{
	try{
		if(br != null){
			br.close();  //파일 읽기 종료
		}
	}catch(IOException e){
		e.printStackTrace();
	}
}

String strcate = new String();
if (cate == 1)
	strcate = "생활/문화";
else if (cate == 2)
	strcate = "IT/과학";
else if (cate ==3)
	strcate = "사회";
else if (cate ==4)
	strcate = "경제";
else if (cate ==5)
	strcate = "정치";
	
//System.out.println(ret2.get(bignum));
//ret2.get(bignum)------------------------------------------------------------------------
JLabel hotkeyword = new JLabel();
JLabel hotkeyword2 = new JLabel();
hotkeyword.setText("가장 중요한 키워드는 \"" + strcate + "\" 카테고리의");
//hotkeyword.setText("가장 중요한 키워드는 \"" + "생활/문화" + "\" 카테고리의");
hotkeyword2.setText(ret2.get(bignum) + " 입니다.");
hotkeyword.setSize(400,100);
hotkeyword2.setSize(400,100);
hotkeyword.setLocation(380, 310);
hotkeyword2.setLocation(380, 340);
hotkeyword.setFont(new Font("은 돋움", Font.BOLD, 19));
hotkeyword2.setFont(new Font("은 돋움", Font.BOLD, 19));
hotkeyword.setForeground(Color.WHITE);
hotkeyword2.setForeground(Color.WHITE);
	
	
	refresh.addActionListener(listener);
	lifebtn.addActionListener(listener);
	itbtn.addActionListener(listener);
	economybtn.addActionListener(listener);
	politicsbtn.addActionListener(listener);
	societybtn.addActionListener(listener);
	a.add(itbtn); a.add(societybtn); a.add(lifebtn); a.add(politicsbtn); a.add(economybtn);
	a.add(refresh); 
	a.add(hotkeyword);
	a.add(hotkeyword2);
	this.add(a);
	this.setSize(800,480); //라즈베리파이 7인치 터치 스크린 해상도 
	this.setVisible(true);

	
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