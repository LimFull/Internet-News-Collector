


import javax.swing.*; //프로젝트 실행환경 - JavaSE-1.7 


import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;


@SuppressWarnings("serial")
public class Polrecord extends JFrame {
	private static final String ACTION_COMMAND_BACK= "1";
	private static final String ACTION_COMMAND_ONE= "2";
	private static final String ACTION_COMMAND_TWO= "3";
	private static final String ACTION_COMMAND_THREE= "4";
	private static final String ACTION_COMMAND_FOUR= "5";
	private static final String ACTION_COMMAND_FIVE= "6";
	panel a = new panel();
	JButton[] sb = new JButton[7]; 
	private JTextField strdate;
	private JTextField enddate;
	String strnum, endnum;
	private PopUpKeyboard1 keyboard1;
	private PopUpKeyboard2 keyboard2;
	
	
	
	
	
	
	public Polrecord(){
		setUndecorated(true);
		GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
		GraphicsDevice gd = ge.getDefaultScreenDevice();
	
		a.setLayout(null);
		JButton backbtn = new JButton(new ImageIcon("./img/backbtn.png"));
		backbtn.setPressedIcon(new ImageIcon("./img/pressedbackbtn.png"));
		backbtn.setSize(45,45);
		backbtn.setVisible(true);
		backbtn.setLocation(15,43);
		backbtn.setBorderPainted(false);  //버튼 외곽선
		backbtn.setContentAreaFilled(false);
		backbtn.setFocusPainted(false);
		
		polkeywords politicsk = new polkeywords();  //버튼에 키워드를 불러올 객체
		JButton word1 = new JButton(); JButton word2 = new JButton(); 
		JButton word3 = new JButton(); JButton word4 = new JButton(); 
		JButton word5 = new JButton();
		
		strdate = new JTextField(8); // 시작날짜 텍스트 필
		keyboard1 = new PopUpKeyboard1(strdate);		
		strdate.setBounds(625,162,100,45);
		strdate.addMouseListener(new MouseAdapter()
	        {
	            @Override
	            public void mouseClicked(MouseEvent e)
	            {
	                Point p = strdate.getLocationOnScreen();
	                p.y += 30;
	                keyboard1.setLocation(p);
	                keyboard1.setVisible(true);
	            }
	        });	
		a.add(strdate);
		 pack();
		 setLocationByPlatform(true);
		 

		 enddate = new JTextField(8); //날짜 끝 텍스트 필
			keyboard2 = new PopUpKeyboard2(enddate);		
			enddate.setBounds(625,296,100,45);
			enddate.addMouseListener(new MouseAdapter()
		        {
		            @Override
		            public void mouseClicked(MouseEvent e)
		            {
		                Point p = enddate.getLocationOnScreen();
		                p.y += 30;
		                keyboard2.setLocation(p);
		                keyboard2.setVisible(true);
		            }
		        });	
			a.add(enddate);
			 pack();
			 setLocationByPlatform(true);
		
		
		JLabel str = new JLabel("시작 일");
		str.setBounds(650,110,100,45);
		a.add(str);
		
		JLabel end = new JLabel("마지막 일");
		end.setBounds(650,235,100,45);
		a.add(end);
		
		
		JButton search = new JButton("검색");
		search.setBounds(625,363,100,45);
		a.add(search);
		
		word1.setText(politicsk.getwords(0)); word2.setText(politicsk.getwords(1));
		word3.setText(politicsk.getwords(2)); word4.setText(politicsk.getwords(3));
		word5.setText(politicsk.getwords(4));
		word1.setSize(100,45); word2.setSize(100,45); word3.setSize(100,45); word4.setSize(100,45); word5.setSize(100,45);
		word1.setLocation(65,95); word2.setLocation(65,162); word3.setLocation(65,229); word4.setLocation(65,296); word5.setLocation(65,363);
		for (int i = 0; i<7; i++){
			a.add(sb[i] = new JButton(""));
			sb[i].setSize(120,45);
			sb[i].setLocation(625,i*50+95);
			sb[i].setVisible(false);
			}
		
		ActionListener listener = new ActionListener(){
			public void actionPerformed(ActionEvent e){
				if (e.getActionCommand().equals(ACTION_COMMAND_BACK)){
					//mainframe mf = new mainframe();
					dispose();
				}
			}
		};
		backbtn.setActionCommand(ACTION_COMMAND_BACK);
		backbtn.addActionListener(listener);
		word1.setActionCommand(ACTION_COMMAND_ONE);
		word1.addActionListener(listener);
		word2.setActionCommand(ACTION_COMMAND_TWO);
		word2.addActionListener(listener);
		word3.setActionCommand(ACTION_COMMAND_THREE);
		word3.addActionListener(listener);
		word4.setActionCommand(ACTION_COMMAND_FOUR);
		word4.addActionListener(listener);
		word5.setActionCommand(ACTION_COMMAND_FIVE);
		word5.addActionListener(listener);
		
		a.add(backbtn);
		a.add(word1); a.add(word2); a.add(word3); a.add(word4); a.add(word5); //키워드 버튼 추가
		
		this.add(a);
		this.setSize(800,480);
		this.setVisible(true);
		//gd.setFullScreenWindow(this); 
	}
	
	private class PopUpKeyboard1 extends JDialog implements ActionListener
    {
        private JTextField strdate;

        public PopUpKeyboard1(JTextField strdate)
        {
            this.strdate = strdate;
            setLayout(new GridLayout(3, 3));
            for(int i = 1; i <= 9; i++) createButton(Integer.toString(i));
            pack();
        }

        private void createButton(String label)
        {
            JButton btn = new JButton(label);
            btn.addActionListener(this);
            btn.setFocusPainted(false);
            btn.setPreferredSize(new Dimension(100, 100));
            Font font = btn.getFont();
            float size = font.getSize() + 15.0f;
            btn.setFont(font.deriveFont(size));
            add(btn);
        }

        @Override
        public void actionPerformed(ActionEvent e)
        {
            String actionCommand = e.getActionCommand();
            strdate.setText(strdate.getText() + actionCommand);
        }
    }
	
	
	
	
	private class PopUpKeyboard2 extends JDialog implements ActionListener
    {
        private JTextField enddate;

        public PopUpKeyboard2(JTextField enddate)
        {
            this.enddate = enddate;
            setLayout(new GridLayout(3, 3));
            for(int i = 1; i <= 9; i++) createButton(Integer.toString(i));
            pack();
        }

        private void createButton(String label)
        {
            JButton btn = new JButton(label);
            btn.addActionListener(this);
            btn.setFocusPainted(false);
            btn.setPreferredSize(new Dimension(100, 100));
            Font font = btn.getFont();
            float size = font.getSize() + 15.0f;
            btn.setFont(font.deriveFont(size));
            add(btn);
        }

        @Override
        public void actionPerformed(ActionEvent e2)
        {
            String actionCommand = e2.getActionCommand();
            enddate.setText(enddate.getText() + actionCommand);
        }
    }
	
	
	
	
	
	
	
	
}






class panel extends JPanel
{
	ImageIcon bgimg = new ImageIcon("./img/barbackground.png");
	Image img = bgimg.getImage();
	ImageIcon cloudimg = new ImageIcon("./polrecord/polwordcloud.png");
	Image img2 = cloudimg.getImage();
	public void paintComponent(Graphics g)
	{
		super.paintComponent(g);
		g.drawImage(img, 0, 0, this.getWidth(),this.getHeight(),this);
		g.drawImage(img2, 400-(350/2), 80, 350, 350, this);
	}
}
