package lifepart;
import javax.swing.*; //프로젝트 실행환경 - JavaSE-1.7 
import java.awt.*;

public class lifecontent extends JFrame {
	public lifecontent(){
		setUndecorated(true);
		GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
		GraphicsDevice gd = ge.getDefaultScreenDevice();
	
		panel a = new panel();
		
		this.add(a);
		this.setSize(800,480);
		this.setVisible(true);
		gd.setFullScreenWindow(this); 
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
