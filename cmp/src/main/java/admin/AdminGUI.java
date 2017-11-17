package admin;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.Font;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextArea;
import javax.swing.border.LineBorder;
import firebase.DB;

import inputItem.MenuGUI;
import inputItem.UploadItemGUI;

public class AdminGUI extends JFrame
{
	JButton addNotice;
	JLabel noticetxt2;
	JTextArea notice;
	JFrame frame;
	DB db;
	Admin admin;

	public  AdminGUI()
	{
		super("AdminGUI");

		db = new DB();
	    admin = new Admin();
	    //Firebase DB와 연결
	    db.connectDB();
	     
	    frame = new JFrame();
		frame.setBounds(0, 0, 1024, 768);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		frame.setContentPane(new JLabel(new ImageIcon("main77.jpg")));
		
		JLabel noticetxt1=new JLabel("NOTICE  | ");
		noticetxt1.setBounds(30, 308,70, 20);
		noticetxt1.setForeground(Color.WHITE);
		noticetxt1.setFont(new Font("맑은 고딕", Font.BOLD, 13));
		frame.getContentPane().add(noticetxt1);
		
		noticetxt2=new JLabel("");
		noticetxt2.setBounds(100, 308, 490, 20);
		noticetxt2.setForeground(Color.WHITE);
		noticetxt2.setFont(new Font("맑은 고딕", Font.BOLD, 13));
		frame.getContentPane().add(noticetxt2);
		

		notice=new JTextArea("",1,20);
		notice.setBounds(240, 385, 490, 20);
		notice.setFont(new Font("맑은 고딕", Font.BOLD, 13));
		notice.setBorder(new LineBorder(Color.yellow, 2));
		frame.getContentPane().add(notice);
		
		addNotice = new JButton(new ImageIcon("addnotice.png"));
		addNotice.setBounds(742, 383, 60, 25);
		addNotice.setOpaque(false);
		addNotice.setBorderPainted(false);
		addNotice.setContentAreaFilled(false);
		addNotice.setFocusPainted(false);
		addNotice.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		frame.getContentPane().add(addNotice);
		addNotice.addMouseListener(new java.awt.event.MouseAdapter() { //첨부 버튼을 누른 경우
	            public void mouseClicked(java.awt.event.MouseEvent evt) {
	            	addNoticeMouseClicked(evt);
	            }
	        });
		
		
		JButton menu = new JButton(new ImageIcon("btn1.jpg")); //114/ 168
		menu.setBackground(Color.white);
		menu.setOpaque(false);
		menu.setBorderPainted(false);
		menu.setContentAreaFilled(false);
		menu.setFocusPainted(false);
		menu.setBounds(210, 434, 140, 180);
		menu.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		frame.getContentPane().add(menu);
		menu.addMouseListener(new java.awt.event.MouseAdapter() { //예약 버튼을 누른 경우
            public void mouseClicked(java.awt.event.MouseEvent evt) {
				menuMouseClicked(evt);
            }
        });
		
		JButton reservation = new JButton(new ImageIcon("addBtn.jpg"));
		reservation.setBackground(Color.white);
		reservation.setOpaque(false);
		reservation.setBorderPainted(false);
		reservation.setContentAreaFilled(false);
		reservation.setFocusPainted(false);
		reservation.setBounds(450, 440, 130, 170);
		reservation.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		frame.getContentPane().add(reservation);
		reservation.addMouseListener(new java.awt.event.MouseAdapter() { //예약 버튼을 누른 경우
            public void mouseClicked(java.awt.event.MouseEvent evt) {
				reservationMouseClicked(evt);
            }
        });
		frame.setVisible(true);
		

	JButton manual = new JButton(new ImageIcon("manual.jpg"));
	manual.setBackground(Color.white);
	manual.setOpaque(false);
	manual.setBorderPainted(false);
	manual.setContentAreaFilled(false);
	manual.setFocusPainted(false);
	manual.setBounds(680, 438, 140, 180);
	manual.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
	frame.getContentPane().add(manual);
	manual.addMouseListener(new java.awt.event.MouseAdapter() { //예약 버튼을 누른 경우
        public void mouseClicked(java.awt.event.MouseEvent evt) {
        	manualMouseClicked(evt);
        }
    });
	frame.setVisible(true);
	
}    
	
	
	private void menuMouseClicked(java.awt.event.MouseEvent evt) {
		 System.out.println("메뉴 누름");
		 new MenuGUI();
		 frame.setVisible(false);
	  }
	  private void reservationMouseClicked(java.awt.event.MouseEvent evt) {
		 System.out.println("예약 누름");
		 new UploadItemGUI();
		 frame.setVisible(false);
	  }
	  private void manualMouseClicked(java.awt.event.MouseEvent evt) {
			 System.out.println("메뉴얼 누름");
			 new manual.ManualGUI();
			 frame.setVisible(false);
		  }
	  
	  private void addNoticeMouseClicked(java.awt.event.MouseEvent evt) 
	  {
		  System.out.println("공지사항 추가 버튼 누름");
			  
		  if(notice.getText().length()<35)
		  {
			//DB에 새로운 공지사항을 올려줌
			admin.uploadNotice(notice.getText());
			//DB에서 공지사항을 가져와서 띄어줌
			noticetxt2.setText(admin.getNotice());	
			notice.setText("");
		  }
		  
		  else 
			  JOptionPane.showMessageDialog(null, "공지사항은 35자내로 등록해주세요");
	   }
	 
	public static void main(String[] args) 
	{
		// TODO Auto-generated method stub
		new  AdminGUI();
		
	} // end of main
} // end of AdminGUI
