package firebase;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ExecutionException;

import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;
import com.google.firebase.auth.FirebaseCredentials;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.tasks.Task;
import com.google.firebase.tasks.Tasks;

import model.User;

public class DB 
{
	public void connectDB()
	{
		try
		{
			FileInputStream serviceAccount = new FileInputStream("CMP-JAVA-PROJECT-d5d296baddd2.json");

	        FirebaseOptions options = new FirebaseOptions.Builder()
	          .setCredential(FirebaseCredentials.fromCertificate(serviceAccount))
	          .setDatabaseUrl("https://cmp-java-project.firebaseio.com/")
	          .build();

	        FirebaseApp.initializeApp(options);
		}
		
		catch(IOException ie)
		{
			ie.printStackTrace();
		}
	} // end of connectDB
	
	public void saveData()
	{
		 final FirebaseDatabase database = FirebaseDatabase.getInstance();
         DatabaseReference ref = database.getReference("cmp/");

         DatabaseReference usersRef = ref.child("users");

         Map<String, User> users = new HashMap<String, User>();
         users.put("구지원", new User(2602, 1234, "구지원"));
         users.put("이유리", new User(2601, 5678, "이유리"));

         usersRef.setValue(users);
         
         System.out.println("Log : putData");
         
         Task<Void> task = usersRef.setValue(users);
         
         try 
         {
         	System.out.println("Log : await");
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
		
	} // end of saveData

	public static void main(String[] args) 
	{
		DB db = new DB();
		
		db.connectDB();
		db.saveData();

	} // end of main
} // end of DB
