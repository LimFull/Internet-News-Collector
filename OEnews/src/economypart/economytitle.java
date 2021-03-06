package economypart;

import java.awt.Graphics;
import java.awt.Image;
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

import javax.swing.*;

import main.mainframe;

public class economytitle extends JFrame {
	private static final String ACTION_COMMAND_BACK= "1";
	private static final String ACTION_COMMAND_ONE= "11";
	private static final String ACTION_COMMAND_TWO= "22";
	private static final String ACTION_COMMAND_THREE= "33";
	private static final String ACTION_COMMAND_FOUR= "44";
	private static final String ACTION_COMMAND_FIVE= "55";
	private static final String ACTION_COMMAND_SIX= "66";
	private static final String ACTION_COMMAND_SEVEN= "77";
	private static final String ACTION_COMMAND_EIGHT= "88";
	List<List<String>> ret = new ArrayList<List<String>>(); // 제목을 저장할 문자열 리스트
	titlepanel a = new titlepanel();
	JButton[] sb = new JButton[10];
	economyurl economyu = new economyurl();
	
	public economytitle(final int word,final int[] titlenumber){
		this.setUndecorated(true);
		a.setLayout(null);
		
		BufferedReader br = null;
		try{
			br = Files.newBufferedReader(Paths.get("Rdata/ECONOMYwordtitle.csv")); //csv파일을 읽음  
			Charset.forName("UTF-8");  //UTF-8 형식
			String line = "";
			
			while((line = br.readLine()) != null){
				List<String> tmplist = new ArrayList<String>();
				String array[] = line.split("\",");
				tmplist = Arrays.asList(array);
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
		 
		int tlength=0;
		if (titlenumber[0]==0) tlength++;
		for (int i = 0; i<20; i++){  //titlenumber에 해당하는 제목이 몇개 있는지 센다.
			if (titlenumber[i]!=0) tlength++;
		}
 		List<String> lstr = new ArrayList<String>();
		lstr = ret.get(word+1);
		
		for (int i = 0; i<8; i++){  //버튼 만들기
			a.add(sb[i] = new JButton(new ImageIcon("./img/titlebutton.png")));
			sb[i].setPressedIcon(new ImageIcon("./img/titlebuttonpush.png"));
			sb[i].setSize(551,41);
			sb[i].setLocation((800-550)/2,i*55+40);
			sb[i].setVisible(false);
			sb[i].setBorderPainted(false);
			sb[i].setContentAreaFilled(false);
			sb[i].setFocusPainted(false);
			sb[i].setHorizontalTextPosition(JButton.CENTER);
			sb[i].setVerticalTextPosition(JButton.CENTER);
			}
		
		for (int i = 0 ; i<tlength ; i++){
			sb[i].setText(lstr.get(titlenumber[i])+"\"");  // 제목 수만큼 버튼 텍스트 설정
			sb[i].setVisible(true);
			if (i==7) break;
		}
		
		JButton backbtn = new JButton(new ImageIcon("./img/backbtn.png"));
		backbtn.setPressedIcon(new ImageIcon("./img/pressedbackbtn.png"));
		backbtn.setSize(45,45);
		backbtn.setVisible(true);
		backbtn.setLocation(15,35);
		backbtn.setBorderPainted(false);  //버튼 외곽선
		backbtn.setContentAreaFilled(false);
		backbtn.setFocusPainted(false);
		
		ActionListener listener = new ActionListener(){
			public void actionPerformed(ActionEvent e){
				if (e.getActionCommand().equals(ACTION_COMMAND_BACK)){
					dispose();
				}
				if (e.getActionCommand().equals(ACTION_COMMAND_ONE)){
					economyu.openurl(word, 0, titlenumber);
				}
				if (e.getActionCommand().equals(ACTION_COMMAND_TWO)){
					economyu.openurl(word, 1, titlenumber);
				}
				if (e.getActionCommand().equals(ACTION_COMMAND_THREE)){
					economyu.openurl(word, 2, titlenumber);
				}
				if (e.getActionCommand().equals(ACTION_COMMAND_FOUR)){
					economyu.openurl(word, 3, titlenumber);
				}
				if (e.getActionCommand().equals(ACTION_COMMAND_FIVE)){
					economyu.openurl(word, 4, titlenumber);
				}
				if (e.getActionCommand().equals(ACTION_COMMAND_SIX)){
					economyu.openurl(word, 5, titlenumber);
				}
				if (e.getActionCommand().equals(ACTION_COMMAND_SEVEN)){
					economyu.openurl(word, 6, titlenumber);
				}
				if (e.getActionCommand().equals(ACTION_COMMAND_EIGHT)){
					economyu.openurl(word, 7, titlenumber);
				}
			}
		};
	
		sb[0].setActionCommand(ACTION_COMMAND_ONE);
		sb[0].addActionListener(listener);
		sb[1].setActionCommand(ACTION_COMMAND_TWO);
		sb[1].addActionListener(listener);
		sb[2].setActionCommand(ACTION_COMMAND_THREE);
		sb[2].addActionListener(listener);
		sb[3].setActionCommand(ACTION_COMMAND_FOUR);
		sb[3].addActionListener(listener);
		sb[4].setActionCommand(ACTION_COMMAND_FIVE);
		sb[4].addActionListener(listener);
		sb[5].setActionCommand(ACTION_COMMAND_SIX);
		sb[5].addActionListener(listener);
		sb[6].setActionCommand(ACTION_COMMAND_SEVEN);
		sb[6].addActionListener(listener);
		sb[7].setActionCommand(ACTION_COMMAND_EIGHT);
		sb[7].addActionListener(listener);
		backbtn.setActionCommand(ACTION_COMMAND_BACK);
		backbtn.addActionListener(listener);
		a.add(backbtn);
		this.add(a);
		this.setSize(800,480);
		//this.setLocation((800-700)/2,(480-350)/2);
		this.setVisible(true);

	 
		
	}
}

class titlepanel extends JPanel
{
	ImageIcon bgimg = new ImageIcon("./img/barbackground.png");
	Image img = bgimg.getImage();
	public void paintComponent(Graphics g)
	{
		super.paintComponent(g);
		g.drawImage(img, 0, 0, this.getWidth(),this.getHeight(),this);
	}
}
