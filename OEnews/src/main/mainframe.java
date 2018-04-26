package main;
import javax.swing.*; //프로젝트 실행환경 - JavaSE-1.7 
import java.awt.*;

public class mainframe extends JFrame {
	


 	
	mainframe(){

	//GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
	//GraphicsDevice gd = ge.getDefaultScreenDevice();
	this.setTitle("OEnews");
	setUndecorated(true);  //타이틀바 제거
	
	panel a = new panel();
	a.setLayout(null);
	JButton itbtn = new JButton(new ImageIcon("./img/normalit.png"));
	JButton societybtn = new JButton(new ImageIcon("./img/normalsociety.png"));
	//itbtn.setPreferredSize(new Dimension(100,100)); //절대위치
	//itbtn.setBounds(130,150,95,30);  //x,y,width,height
	//itbtn.setText("");  //버튼 텍스트
	itbtn.setPressedIcon(new ImageIcon("./img/pressedit.png"));societybtn.setPressedIcon(new ImageIcon("./img/pressedsociety.png"));  //버튼 클릭 이미지
	itbtn.setSize(234,60);societybtn.setSize(234,60);   //버튼 크기
	itbtn.setLocation(115,140);societybtn.setLocation(451,140);  //버튼위치
	itbtn.setVisible(true);societybtn.setVisible(true);
	itbtn.setBorderPainted(false);societybtn.setBorderPainted(false);  //버튼 외곽선
	itbtn.setContentAreaFilled(false);societybtn.setContentAreaFilled(false);  //버튼 내용영역
	itbtn.setFocusPainted(false);societybtn.setFocusPainted(false);  //버튼 선택 시 테두리
	
	

	a.add(itbtn); a.add(societybtn);
	this.add(a);
	this.setSize(800,480); //라즈베리파이 7인치 터치 스크린 해상도 
	this.setVisible(true);
	//gd.setFullScreenWindow(this);  //전체화면	
	}
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

 
