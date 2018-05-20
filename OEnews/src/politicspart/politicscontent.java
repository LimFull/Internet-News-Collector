package politicspart;

import javax.swing.*; //프로젝트 실행환경 - JavaSE-1.7 

import main.*;




import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class politicscontent extends JFrame {
	private static final String ACTION_COMMAND_BACK= "1";
public politicscontent(){
	setUndecorated(true);
	GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
	GraphicsDevice gd = ge.getDefaultScreenDevice();
	
	panel a = new panel();
	a.setLayout(null);
	JButton backbtn = new JButton(new ImageIcon("./img/backbtn.png"));
	backbtn.setPressedIcon(new ImageIcon("./img/pressedbackbtn.png"));
	backbtn.setSize(45,45);
	backbtn.setVisible(true);
	backbtn.setLocation(15,43);
	backbtn.setBorderPainted(false);  //버튼 외곽선
	backbtn.setContentAreaFilled(false);
	backbtn.setFocusPainted(false);
	
	politicskeywords politicsk = new politicskeywords();  //버튼에 키워드를 불러올 객체
	JButton word1 = new JButton(politicsk.getwords(0)); JButton word2 = new JButton(politicsk.getwords(1)); 
	JButton word3 = new JButton(politicsk.getwords(2)); JButton word4 = new JButton(politicsk.getwords(3)); 
	JButton word5 = new JButton(politicsk.getwords(4));
	word1.setSize(100,45); word2.setSize(100,45); word3.setSize(100,45); word4.setSize(100,45); word5.setSize(100,45);
	word1.setLocation(65,95); word2.setLocation(65,162); word3.setLocation(65,229); word4.setLocation(65,296); word5.setLocation(65,363);
	 
	ActionListener listener = new ActionListener(){
		public void actionPerformed(ActionEvent e){
			if (e.getActionCommand().equals(ACTION_COMMAND_BACK)){
				mainframe mf = new mainframe();
				dispose();
			}
		}
	};
	backbtn.setActionCommand(ACTION_COMMAND_BACK);
	backbtn.addActionListener(listener);
	
	a.add(backbtn);
	a.add(word1); a.add(word2); a.add(word3); a.add(word4); a.add(word5); //키워드 버튼 추가

	this.add(a);
	this.setSize(800,480);
	this.setVisible(true);
	//gd.setFullScreenWindow(this); 
	

}
}

class panel extends JPanel
{
	ImageIcon bgimg = new ImageIcon("./img/barbackground.png");
	Image img = bgimg.getImage();
	ImageIcon cloudimg = new ImageIcon("cloudpolitics.png");
	Image img2 = cloudimg.getImage();
	public void paintComponent(Graphics g)
	{
		super.paintComponent(g);
		g.drawImage(img, 0, 0, this.getWidth(),this.getHeight(),this);
		g.drawImage(img2, 400-(350/2), 80, 350, 350, this);
	}
}
