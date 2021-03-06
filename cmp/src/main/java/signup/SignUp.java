package signup;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ExecutionException;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.tasks.Task;
import com.google.firebase.tasks.Tasks;

import model.User;

public class SignUp 
{	
	public void saveSignUpData(String studentNumber, String password, String name, String pinCode)
	{
		
		 final FirebaseDatabase database = FirebaseDatabase.getInstance();
         DatabaseReference ref = database.getReference("CMP/User");

         DatabaseReference usersRef = ref.child(studentNumber);

         Map<String, User> signUpUsers = new HashMap<String, User>();

         signUpUsers.put("Info", new User(studentNumber, password, name, pinCode));
        
         usersRef.setValue(signUpUsers);
         
         System.out.println("Log : saveSignUpData() : putData");
         
         Task<Void> task = usersRef.setValue(signUpUsers);
         
         try 
         {
         	System.out.println("Log : saveSignUpData() :  await");
            Tasks.await(task);
         } 
         
         catch (ExecutionException e) 
         {
         	e.printStackTrace();
         }
         
         catch(InterruptedException e)
         {
         	e.printStackTrace();
         }
		
	} // end of saveSignUpData

} // end of SignUp
