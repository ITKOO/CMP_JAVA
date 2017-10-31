package signup;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.Font;


import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.JTextArea;
import javax.swing.border.LineBorder;

import firebase.DB;
import login.LoginGUI;

public class SignUpGUI extends JFrame
{
	JTextArea inputStudentN;
	JPasswordField inputPassword;
	JTextArea inputName;
	JTextArea inputCode;
	JButton SignUpBtn;
	JFrame frame;
	
	public  SignUpGUI(){
		super("로그인");
		
		
		frame = new JFrame();
		frame.setBounds(0, 0, 1024, 768);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		frame.setContentPane(new JLabel(new ImageIcon("signup_bg.jpg")));
	
		
		
		JLabel inputSN=new JLabel("학번");
		inputSN.setBounds(410, 295, 70, 20);
		inputSN.setForeground(Color.WHITE);
		inputSN.setFont(new Font("맑은 고딕", Font.BOLD, 13));
		frame.getContentPane().add(inputSN);
		inputStudentN=new JTextArea("",1,20);
		 inputStudentN.setOpaque(false);
		 inputStudentN.setBorder(new LineBorder(Color.white, 1));
		 inputStudentN.setForeground(Color.white);
		 inputStudentN.setBounds(470, 295, 150, 20);
		frame.getContentPane().add(inputStudentN);
		
		
		JLabel  inputP=new JLabel("비밀번호");
		inputP.setBounds(400, 330, 70, 20);
		inputP.setForeground(Color.WHITE);
		inputP.setFont(new Font("맑은 고딕", Font.BOLD, 13));
		frame.getContentPane().add(inputP);
		 inputPassword=new JPasswordField();
		 inputPassword.setOpaque(false);
		inputPassword.setBorder(new LineBorder(Color.white, 1));
		inputPassword.setForeground(Color.white);
		inputPassword.setBounds(470, 330, 150, 20);
		frame.getContentPane().add(inputPassword);
		
		JLabel inputN=new JLabel("이름");
		inputN.setBounds(410, 365, 70, 20);
		inputN.setForeground(Color.WHITE);
		inputN.setFont(new Font("맑은 고딕", Font.BOLD, 13));
		frame.getContentPane().add(inputN);
		inputName=new JTextArea("",1,20);
		inputName.setOpaque(false);
		inputName.setBorder(new LineBorder(Color.white, 1));
		inputName.setForeground(Color.white);
		inputName.setBounds(470, 365, 150, 20);
		frame.getContentPane().add(inputName);
		
		JLabel inputC=new JLabel("인증코드");
		inputC.setBounds(400, 400, 70, 20);
		inputC.setForeground(Color.WHITE);
		inputC.setFont(new Font("맑은 고딕", Font.BOLD, 13));
		frame.getContentPane().add(inputC);
		inputCode=new JTextArea("",1,20);
		inputCode.setOpaque(false);
		inputCode.setBorder(new LineBorder(Color.white, 1));
		inputCode.setForeground(Color.white);
		inputCode.setBounds(470, 400, 150, 20);
		frame.getContentPane().add(inputCode);
		
		SignUpBtn = new JButton(new ImageIcon("signup.png"));
		SignUpBtn.setBounds(460, 440, 120, 25);
		SignUpBtn.setOpaque(false);
		SignUpBtn.setBorderPainted(false);
		SignUpBtn.setContentAreaFilled(false);
		SignUpBtn.setFocusPainted(false);
		SignUpBtn.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		frame.getContentPane().add(SignUpBtn);
		
		SignUpBtn.addMouseListener(new java.awt.event.MouseAdapter() 
		{ //첨부 버튼을 누른 경우
            public void mouseClicked(java.awt.event.MouseEvent evt) 
            {
					SignUpMouseClicked(evt);
				
            } // end of mouseClicked
        });
		
		frame.setVisible(true);
		
	}              
	 private void SignUpMouseClicked(java.awt.event.MouseEvent evt) 
	 {
		 SignUp s;
		 DB db;
		 boolean check=true;
		 
		  try
		  {
				 if (inputStudentN.getText().equals("")||inputPassword.getText().equals("") ||
						  inputName.getText().equals("")||inputCode.getText().equals("") )
				 {
					  JOptionPane.showMessageDialog(null, "모든 항목을 입력해주세요");
				 }
			  
			    else
			    {
				      s = new SignUp();
				     db= new DB();
				     
					  String sStudentN=inputStudentN.getText().toString();
					  String sPassword=inputPassword.getText().toString();
					  String sName=inputName.getText().toString();
					  String scode=inputCode.getText().toString();
					  
					  if (isStringDouble(sStudentN)&&sStudentN.length()==4){ //학번 유효성 체크
					      System.out.println("입력한 학번 4자리 숫자");
					            int first = sStudentN.charAt(0)-48;
					            int second= sStudentN.charAt(1)-48;
					            int tforth= (sStudentN.charAt(2)-48)*10+sStudentN.charAt(3)-48;
					            System.out.println("first:"+first);
					            System.out.println("second:"+second);
					            System.out.println("tforth:"+tforth);
					            	if(first<1||first>3)check=false;
					            	if(second<1||second>6)check=false;
					            	if(tforth<1||tforth>20)check=false;
					            if(check==false)
					            {
					            	 JOptionPane.showMessageDialog(null, "올바른 학번을 입력해주세요");
					            }
					  }
						  else{
							  JOptionPane.showMessageDialog(null, "학번은 4자리 숫자만 가능합니다");
							  check=false;
						  }
					  if(sPassword.length()<=6){ //비밀번호 유효성 체크
			            	for(int i=0;i<sPassword.length();i++){
						            char ch = sPassword.charAt(i);
						            System.out.println("ch : "+ch);
						            // 대문자는 아스키 값으로 65부터 90이고
						            // 소문자는 아스키 값으로 97부터 122까지며 그 범위 들어가 있으면 영문자이다. 
						            if(ch>47 && ch<58||ch >= 65 && ch <= 90||ch >= 97 && ch <= 122){
						            	System.out.println("올바른 비밀번호");
						            }
						            else{
						            	JOptionPane.showMessageDialog(null, "소문자,대문자,숫자의 조합으로 입력해주세요");
						            	check=false;
						            }
					  	}//for
					  }//if
					  else{
						  JOptionPane.showMessageDialog(null, "비밀번호는 최대 6자리입니다");
						  check=false;
					  }
					  for(int i=0;i<sName.length();i++){//이름 유효성체크
				            char ch = sName.charAt(i);
				            if(ch>='가' && ch<='힣'){
				            	System.out.println("한글");
				            }
				            else{
				            	JOptionPane.showMessageDialog(null, "이름은 한글로 입력해주세요");
				            	check=false;
				            }
					  }//for
					  if(Integer.parseInt(scode)==8765){//인증코드 유효성 체크
						  System.out.println("미림여고 학생");
					  }
					  else if(Integer.parseInt(scode)==9876){
						  System.out.println("미림마이스터고 학생");
					  }
					  else{
						  JOptionPane.showMessageDialog(null, "유효하지 않은 인증코드입니다");
						  check=false;
					  }
 
					  if(check==true)
					  { //유효성을 모두 만족하는 경우만 회원가입
						  db.connectDB();
						  s. saveSignUpData(sStudentN, sPassword, sName, scode);
						  JOptionPane.showMessageDialog(null, "회원가입 성공");  
						  System.out.println("학번:"+sStudentN); //학번
						  System.out.println("비밀번호:"+sPassword); //비밀번호
						  System.out.println("이름:"+sName); //이름
						  System.out.println("인증코드:"+scode); //인증코드
						  new LoginGUI();
						  frame.setVisible(false);
					  }
	  
			   } // end of else
			  
		  } // end of try
		  
		  catch(Exception n)
		  {
			  System.out.println("오류");
			  System.out.println(n.toString());
		  }
	  } // end of SignUpMouseClicked
	 
	 public static boolean isStringDouble(String s) {
		    try {
		        Double.parseDouble(s);
		        return true;
		    } catch (NumberFormatException e) {
		        return false;
		    }
		  }
	 
	public static void main(String[] args) 
	{
		new  SignUpGUI();
		
	} // end of main
} // end of SignUpGUI
