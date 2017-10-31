package login;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import model.User;

public class Login 
{
	User user;
	boolean isUser;

	
	public void getLogin(String studentNumber, final String password)
	{
		final FirebaseDatabase database = FirebaseDatabase.getInstance();
		DatabaseReference ref = database.getReference("CMP/User/" +  studentNumber + "/Info");
		
		ref.addValueEventListener(new ValueEventListener() 
		{	 
		    public void onDataChange(DataSnapshot dataSnapshot) 
		    {
		    	user = dataSnapshot.getValue(User.class);
		        System.out.println("제발요" + user);        
		        System.out.println("user.password : " + user.password);
		        System.out.println("password : " + password);
		        
		        if(user != null && user.password.equals(password))
		        {
		        	isUser = true;
		        	System.out.println(user.studentNumber);
				    System.out.println(user.password);
				    System.out.println(user.name);
				    System.out.println(user.pinCode);        	
		        }
		        
		        else
		        {
		        	isUser = false;
		        }
	       
		    } // end of onDataChange

		    public void onCancelled(DatabaseError databaseError) 
		    {
		        System.out.println("The read failed: " + databaseError.getCode());
		    }
		});
		
		try 
		{
			Thread.sleep(8000);
		} 
		catch (InterruptedException e) 
		{
			e.printStackTrace();
		}
	} // end of getLogin
	
	public boolean isLoginUser()
	{
		return isUser;
	} // end of isLoginUser
	
	public void checkLogin()
	{
		
	} // end of checkLogin

} // end of Login