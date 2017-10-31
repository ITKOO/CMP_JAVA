package model;

public class User 
{
    public String studentNumber;
    public String password;
    public String name;
    public String pinCode;

    // 기본 생성자
    public User(){}
    
    public User(String studentNumber, String password, String name, String pinCode) 
    {
    	  this.studentNumber = studentNumber;
          this.password = password;
          this.name = name;
          this.pinCode = pinCode;
    } // end of cosntructor
    
} // end of User
