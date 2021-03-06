package inputItem;

import java.awt.Color;

import java.awt.Cursor;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.border.LineBorder;

import admin.AdminGUI;
import firebase.DB;

public class UploadItemGUI extends JFrame{
	DB db;
	UploadItem upItem;
	
	String fileAdress;
	FileInputStream fis = null;
	FileOutputStream fos = null; 
	JTextArea productName;
	JTextArea productPrice;
	JTextArea productStock;
	JTextArea productDescrption;
	JScrollPane productDescription;
	 File selectedFile;
	 JTextArea ta1;
	 boolean check=true;
	 Image originalImage;
	 Image resizeImage;
	 JFrame frame;
	public UploadItemGUI(){
		super("UploadItemGUI");
		
		
		frame = new JFrame();
		frame.setBounds(0, 0, 1024, 768);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
	
		frame.setContentPane(new JLabel(new ImageIcon("input.jpg")));
	
		
		ta1=new JTextArea("",1,20);
		ta1.setBounds(340, 267, 250, 20);
		frame.getContentPane().add(ta1);
		ta1.setColumns(20);
		ta1.setOpaque(false);
		ta1.setBorder(new LineBorder(Color.white, 1));
		ta1.setForeground(Color.white);
		JButton addImg = new JButton(new ImageIcon("addimg1.png"));
		addImg.setBounds(610, 265, 60, 25);
		addImg.setOpaque(false);
		addImg.setBorderPainted(false);
		addImg.setContentAreaFilled(false);
		addImg.setFocusPainted(false);
		addImg.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		frame.getContentPane().add(addImg);
		addImg.addMouseListener(new java.awt.event.MouseAdapter() { //첨부 버튼을 누른 경우
	            public void mouseClicked(java.awt.event.MouseEvent evt) {
	                addImgMouseClicked(evt);
	            }
	        });
		
		JLabel productN=new JLabel("상품 이름");
		productN.setBounds(340, 310, 70, 20);
		productN.setForeground(Color.WHITE);
		productN.setFont(new Font("맑은 고딕", Font.BOLD, 13));
		frame.getContentPane().add(productN);
		productName=new JTextArea("",1,20);
		productName.setOpaque(false);
		productName.setBorder(new LineBorder(Color.white, 1));
		productName.setForeground(Color.white);
		productName.setBounds(410, 310, 260, 20);
		frame.getContentPane().add(productName);
		
		
		JLabel productP=new JLabel("상품 가격");
		productP.setBounds(340, 345, 70, 20);
		productP.setForeground(Color.WHITE);
		productP.setFont(new Font("맑은 고딕", Font.BOLD, 13));
		frame.getContentPane().add(productP);
		productPrice=new JTextArea("",1,10);
		productPrice.setOpaque(false);
		productPrice.setBorder(new LineBorder(Color.white, 1));
		productPrice.setForeground(Color.white);
		productPrice.setBounds(410, 345, 260, 20);
		frame.getContentPane().add(productPrice);
		
		JLabel productS=new JLabel("상품 재고");
		productS.setBounds(340, 380, 70, 20);
		productS.setForeground(Color.WHITE);
		productS.setFont(new Font("맑은 고딕", Font.BOLD, 13));
		frame.getContentPane().add(productS);
		productStock=new JTextArea("",1,10);
		productStock.setOpaque(false);
		productStock.setBorder(new LineBorder(Color.white, 1));
		productStock.setForeground(Color.white);
		productStock.setBounds(410, 380, 260, 20);
		frame.getContentPane().add(productStock);
		
		
		JLabel productD=new JLabel("상품 설명");
		productD.setBounds(340, 415, 70, 20);
		productD.setForeground(Color.WHITE);
		productD.setFont(new Font("맑은 고딕", Font.BOLD, 13));
		frame.getContentPane().add(productD);
		productDescrption=new JTextArea("",1,10);
		productDescrption.setOpaque(false);
		productDescrption.setBorder(new LineBorder(Color.white, 1));
		productDescrption.setForeground(Color.white);
		productDescrption.setBounds(410, 415, 260, 20);
		frame.getContentPane().add(productDescrption);
		
		JButton save = new JButton(new ImageIcon("save1.png"));
		save.setBounds(340, 460, 160, 25);
		save.setOpaque(false);
		save.setBorderPainted(false);
		save.setContentAreaFilled(false);
		save.setFocusPainted(false);
		save.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		frame.getContentPane().add(save);
		save.addMouseListener(new java.awt.event.MouseAdapter() { //첨부 버튼을 누른 경우
            public void mouseClicked(java.awt.event.MouseEvent evt) {
					saveMouseClicked(evt);
				
            }
        });

		JButton cancle = new JButton(new ImageIcon("cancle2.png"));
		cancle.setBounds(510, 460, 160, 25);
		cancle.setOpaque(false);
		cancle.setBorderPainted(false);
		cancle.setContentAreaFilled(false);
		cancle.setFocusPainted(false);
		frame.getContentPane().add(cancle);
		cancle.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		frame.getContentPane().add(cancle);
		cancle.addMouseListener(new java.awt.event.MouseAdapter() { //취소 버튼을 누른 경우
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                	cancleMouseClicked(evt);
            }
        });
	
		frame.setVisible(true);
		
	}
	
	  private void addImgMouseClicked(java.awt.event.MouseEvent evt){  //첨부 버튼을 클릭할 경우
		  JFileChooser fileChooser = new JFileChooser(); //파일선택창
	        fileChooser.setCurrentDirectory(new File(System.getProperty("user.home")));
	        int result = fileChooser.showOpenDialog(this);
	        if (result == JFileChooser.APPROVE_OPTION) {
	            selectedFile = fileChooser.getSelectedFile();
	            fileAdress=selectedFile.getAbsolutePath();
	            System.out.println("Selected file: " + fileAdress);
	           // File orginalImage = selectedFile;
	           
	            ta1.setText(fileAdress);
	        } 
	    }                      
	  private void saveMouseClicked(java.awt.event.MouseEvent evt) {
 try{
			  
	 if (selectedFile.getName().equals("")||productName.getText().equals("") ||
			  productPrice.getText().equals("")||productStock.getText().equals("")||
			  productDescrption.getText().equals("")){
		  JOptionPane.showMessageDialog(null, "모든 항목을 입력해주세요");
		  check=false;
	     }
   else{
   	
   	String pAdress="menuImg/"+selectedFile.getName().toString();
		  String pName=productName.getText().toString();
		  String pPrice=productPrice.getText().toString();
		  String pStock=productStock.getText().toString();
		  String pDescription=productDescrption.getText().toString();
		  
		  if(pName.length()>20||pDescription.length()>20){
			  JOptionPane.showMessageDialog(null, "20자 이내로 입력해주세요");
			  check=false;
		  }
		  if (isStringDouble(pStock)&&isStringDouble(pPrice)){
			  System.out.println("둘 다 숫자");
			  check=true;
		  }
		  else{
			  JOptionPane.showMessageDialog(null, "가격과 재고는 숫자만 입력해주세요");
			  check=false;
		  }
		  //이제 이것들을 디비에 저장하면 되겠지요...?
		  System.out.println("복사된 위치 :"+pAdress); //이미지가 복사된 위치
		  System.out.println("상품 이름"+pName); //상품 이름
		  System.out.println("상품 가격"+pPrice); //상품 가격
		  System.out.println("상품 재고"+pStock); //상품 재고
		  System.out.println("상품 설명"+pDescription); //상품설명
		  System.out.println("check:"+check);
		  if(check==true){
			  try {
					//이미지 200px 200px로 리사이징
				 
					originalImage = ImageIO.read(new File(fileAdress));
					resizeImage = originalImage.getScaledInstance( 200, 200, Image.SCALE_SMOOTH);	//속도보다 이미지 부드러움 우선
					BufferedImage newImage = new BufferedImage( 200, 200, BufferedImage.TYPE_INT_RGB );
		        	Graphics g = newImage.getGraphics();
		        	g.drawImage(resizeImage, 0, 0, this);
		        	g.dispose();
					ImageIO.write(newImage, "jpg", new File("menuImg/"+selectedFile.getName()));
					
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}     	
	            
			  System.out.println("저장");
			  DB db = new DB();
			  db.connectDB();
			  UploadItem uploadItem = new UploadItem();
			  uploadItem.getProductCode();
			  uploadItem.uploadItemData(pAdress, pName, pPrice, pStock, pDescription);
			  JOptionPane.showMessageDialog(null, "저장이 완료되었습니다");
			  
			  new  AdminGUI();
		  	  frame.setVisible(false);
		  }
}
 }catch(java.lang.NullPointerException nu){
			  JOptionPane.showMessageDialog(null, "모든 항목을 입력해주세요");
		  }catch(Exception n){
			  System.out.println("오류");
			  System.out.println(n.toString());
		  }
	  }
	  private void cancleMouseClicked(java.awt.event.MouseEvent evt) {  
		  	  new  AdminGUI();
		  	  frame.setVisible(false);
			  //System.exit(0); //취소버튼을 누르면 창닫기
	  }
	  public static boolean isStringDouble(String s) {
		    try {
		        Double.parseDouble(s);
		        return true;
		    } catch (NumberFormatException e) {
		        return false;
		    }
		  }
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		new UploadItemGUI();
		
	}
	
}
