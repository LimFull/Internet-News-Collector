package societypart;
import javax.swing.*; //프로젝트 실행환경 - JavaSE-1.7 

import main.*;




import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class societycontent extends JFrame {
	private static final String ACTION_COMMAND_BACK= "1";
public societycontent(){
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
	public void paintComponent(Graphics g)
	{
		super.paintComponent(g);
		g.drawImage(img, 0, 0, this.getWidth(),this.getHeight(),this);
	}
}
