package main;

import javax.swing.*;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


public class loading extends JFrame {

	public loading(){
	
		 panel a = new panel();
		 a.setLayout(null);
		
		this.setUndecorated(true);
		this.setSize(503,134);
		this.setLocation(148, 173);
		this.setVisible(true);
		this.add(a);
		//Rall rall = new Rall();
		while(true){
			if (mainframe.complete == 1) dispose();
		}
	
	}
	class panel extends JPanel
	{
		ImageIcon bgimg = new ImageIcon("./img/loading.png");
		Image img = bgimg.getImage();
		public void paintComponent(Graphics g)
		{
			super.paintComponent(g);
			g.drawImage(img, 0, 0, this.getWidth(),this.getHeight(),this);
		}
	}


}