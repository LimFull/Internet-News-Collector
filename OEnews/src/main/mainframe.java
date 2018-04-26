package main;
import javax.swing.*; //프로젝트 실행환경 - JavaSE-1.7 
import java.awt.*;

public class mainframe extends JFrame {
	JScrollPane scrollPane;
	ImageIcon bgimg;
	
	mainframe(){
	 
	GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
	GraphicsDevice gd = ge.getDefaultScreenDevice();
	setTitle("OEnews");
	setUndecorated(true);  //타이틀바 제거
	setSize(800,480); //라즈베리파이 7인치 터치 스크린 해상도
	
	//배경이미지
	bgimg = new ImageIcon("background.png");
	JPanel background = new JPanel(){
		public void paintComponent(Graphics g) {
			g.drawImage(bgimg.getImage(),0,0,null);
			setOpaque(false);
			super.paintComponent(g);
		}
	};
	
	 
	 
	scrollPane = new JScrollPane(background);
	setContentPane(scrollPane);
	//gd.setFullScreenWindow(this);  //전체화면	
	setVisible(true);
	}
}
