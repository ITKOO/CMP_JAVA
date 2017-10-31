package firebase;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ExecutionException;

import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;
import com.google.firebase.auth.FirebaseCredentials;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.tasks.Task;
import com.google.firebase.tasks.Tasks;

import inputItem.UploadItem;
import model.Code;
import model.MagicPassUser;
import model.Notice;
import model.User;

public class DB
{
	MagicPassUser mpUser;
	Code code;
	Notice noti;
	
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
	        System.out.println("Log : connectDB!");
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
         users.put("구지원", new User("2602", "1234", "구지원", "1111"));
         users.put("이유리", new User("2601", "5678", "이유리", "1111"));

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
	
	
	public void getData()
	{
		final FirebaseDatabase database = FirebaseDatabase.getInstance();
		DatabaseReference ref = database.getReference("CMP/Notice/Content");
		
		// Attach a listener to read the data at our posts reference
		ref.addValueEventListener(new ValueEventListener() 
		{	 
		    public void onDataChange(DataSnapshot dataSnapshot) 
		    {
		    	noti = dataSnapshot.getValue(Notice.class);
		        System.out.println("제발요" + noti);
		        System.out.println(noti.notice);
		    }

		    public void onCancelled(DatabaseError databaseError) 
		    {
		        System.out.println("The read failed: " + databaseError.getCode());
		    }
		});
		
		try 
		{
			Thread.sleep(2000);
		} 
		catch (InterruptedException e) 
		{
			e.printStackTrace();
		}
	
	} // end of getData

	public static void main(String[] args) 
	{
		DB db = new DB();
		db.connectDB();
		//db.saveData();
		db.getData();	
	
		

	} // end of main

} // end of DB
