package economypart;

import java.awt.Graphics;
import java.awt.Image;

import javax.swing.*;

public class economytitle extends JFrame {
	titlepanel a = new titlepanel();
	JButton[] sb = new JButton[10];
	
	public economytitle(int word, int[] titlenumber){
	 
		this.setAlwaysOnTop(true);
		this.setUndecorated(true);
		
		a.setLayout(null);
		
		for (int i = 0; i<6; i++){
			a.add(sb[i] = new JButton(""));
			sb[i].setSize(300,40);
			sb[i].setLocation((700-300)/2,i*55+20);
			sb[i].setVisible(true);
			}
	 
 
		 this.add(a);
		 
		this.setSize(700,350);
		this.setLocation((800-700)/2,(480-350)/2);
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