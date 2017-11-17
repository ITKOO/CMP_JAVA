package manual;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.Font;
import java.awt.Frame;
import java.awt.Window;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.JTextArea;
import javax.swing.border.LineBorder;

import admin.AdminGUI;
import firebase.DB;
import inputItem.FileCopy;
import inputItem.MenuGUI;
import signup.SignUpGUI;

public class ManualGUI extends JFrame
{
	JFrame frame;
	  JButton closeBtn;
	
	public  ManualGUI()
	{
		super("메뉴얼");
	
		 frame = new JFrame();
			frame.setBounds(0, 0, 1024, 768);
			frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			frame.getContentPane().setLayout(null);
			
			frame.setContentPane(new JLabel(new ImageIcon("in.jpg")));
		
			closeBtn = new JButton(new ImageIcon("close.png"));
			closeBtn.setBounds(950, 30, 32, 32);
			closeBtn.setOpaque(false);
			closeBtn.setBorderPainted(false);
			closeBtn.setContentAreaFilled(false);
			closeBtn.setFocusPainted(false);
			closeBtn.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
			frame.getContentPane().add( closeBtn);
			closeBtn.addMouseListener(new java.awt.event.MouseAdapter() { //뒤로가기 버튼을 누른 경우
	            public void mouseClicked(java.awt.event.MouseEvent evt) {
	            	closeBtnMouseClicked(evt);
					
	            }
	        });
				
		
		
		JLabel j1=new JLabel("등록된 상품 보기");
		j1.setBounds(291, 430, 120, 20);
		j1.setForeground(Color.WHITE);
		j1.setFont(new Font("맑은 고딕", Font.BOLD, 13));
		frame.getContentPane().add(j1);
		
		JLabel j2=new JLabel("상품 추가하기");
		j2.setBounds(467, 430, 120, 20);
		j2.setForeground(Color.WHITE);
		j2.setFont(new Font("맑은 고딕", Font.BOLD, 13));
		frame.getContentPane().add(j2);
	
		JLabel j3=new JLabel("한 줄 공지사항 등록하기");
		j3.setBounds(610, 430, 200, 20);
		j3.setForeground(Color.WHITE);
		j3.setFont(new Font("맑은 고딕", Font.BOLD, 13));
		frame.getContentPane().add(j3);
		
		
		frame.setVisible(true);
		
	} // end of constructor


	 private void closeBtnMouseClicked(java.awt.event.MouseEvent evt) { 
		 new AdminGUI();
		 frame.setVisible(false);
	 }
	 private void signUpLinkMouseClicked(java.awt.event.MouseEvent evt) 
	 {
		 System.out.println("회원가입 창으로");
		 new SignUpGUI();
		 frame.setVisible(false);
	 }
	public static void main(String[] args) 
	{
		// TODO Auto-generated method stub
		new  ManualGUI();
		
	} // end of main
} // end of LoginGUI
