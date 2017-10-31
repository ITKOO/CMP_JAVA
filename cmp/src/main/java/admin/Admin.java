package admin;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ExecutionException;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.tasks.Task;
import com.google.firebase.tasks.Tasks;

import model.MagicPassUser;
import model.Notice;


public class Admin 
{
	Notice no;
	String noticeContent;
	public void uploadNotice(String notice)
	{
		 final FirebaseDatabase database = FirebaseDatabase.getInstance();
         DatabaseReference ref = database.getReference("CMP/");

         DatabaseReference noticeRef = ref.child("Notice");

         Map<String, Notice> notices = new HashMap<String, Notice>();
         notices.put("Content", new Notice(notice));
       
         noticeRef.setValue(notices);
         
         System.out.println("Log : putData - uploadNotice");
         
         Task<Void> task = noticeRef.setValue(notices);
         
         try 
         {
         	System.out.println("Log : await- uploadNotice");
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
	} // end of uploadNotice
	
	public String getNotice()
	{
		System.out.println("getNotice~~");
		final FirebaseDatabase database = FirebaseDatabase.getInstance();
		DatabaseReference ref = database.getReference("CMP/Notice/Content");
		
		// Attach a listener to read the data at our posts reference
		ref.addValueEventListener(new ValueEventListener() 
		{	 
		    public void onDataChange(DataSnapshot dataSnapshot) 
		    {
		    	System.out.println("onDataChange");
		    	no = dataSnapshot.getValue(Notice.class);
		        //System.out.println("no : " + no);
		        //System.out.println("no.notice : " + no.notice);
		        noticeContent = no.notice;
		        System.out.println("noticeContent : " + noticeContent);
		    }

		    public void onCancelled(DatabaseError databaseError) 
		    {
		        System.out.println("The read failed: " + databaseError.getCode());
		    }
		}); // end of addValueEventListener
		
		try 
		{
			Thread.sleep(2000);
		} 
		catch (InterruptedException e) 
		{
			e.printStackTrace();
		}
		return noticeContent;
	}  // end of getNotice
	
	
} // end of Admin
