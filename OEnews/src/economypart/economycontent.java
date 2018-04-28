package economypart;

import javax.swing.*; //프로젝트 실행환경 - JavaSE-1.7 

import main.mainframe;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class economycontent extends JFrame {
	private static final String ACTION_COMMAND_BACK= "1";
public economycontent(){
	setUndecorated(true);
	GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
	GraphicsDevice gd = ge.getDefaultScreenDevice();
	
	panel a = new panel();
	a.setLayout(null);
	JButton backbtn = new JButton(new ImageIcon("./img/normalit.png"));
	backbtn.setSize(80,30);
	backbtn.setVisible(true);
	
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
