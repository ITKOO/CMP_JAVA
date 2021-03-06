package magicpass;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ExecutionException;

import javax.swing.JOptionPane;

import com.google.firebase.FirebaseApp;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.tasks.Task;
import com.google.firebase.tasks.Tasks;

import model.MagicPassUser;
import model.User;

public class MagicPass 
{
	MagicPassUser mpUser;
	int count;
	int mpCount;
	boolean suMagicPass;
	
	public void saveMagicPassData(int saveCount)
	{
		
		 final FirebaseDatabase database = FirebaseDatabase.getInstance();
         DatabaseReference ref = database.getReference("CMP/");

         DatabaseReference usersRef = ref.child("MagicPass");

         Map<String, MagicPassUser> magicPassUsers = new HashMap<String, MagicPassUser>();

         magicPassUsers.put("Count",new MagicPassUser(saveCount));
        
         usersRef.setValue(magicPassUsers);
         
         System.out.println("Log : saveMagicPassData() : putData");
         
         Task<Void> task = usersRef.setValue(magicPassUsers);
         
         try 
         {
         	System.out.println("Log : saveMagicPassData() :  await");
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
	
	public void getMagicPass()
	{
		final FirebaseDatabase database = FirebaseDatabase.getInstance();
		DatabaseReference ref = database.getReference("CMP/MagicPass/Count");
		
		ref.addValueEventListener(new ValueEventListener() 
		{	 
		    public void onDataChange(DataSnapshot dataSnapshot) 
		    {
		       mpUser = dataSnapshot.getValue(MagicPassUser.class);
		        System.out.println("mpUser : " + mpUser);
		        System.out.println("mpUser.count : " + mpUser.count);
		        mpCount = mpUser.count;
		    }

		    public void onCancelled(DatabaseError databaseError) 
		    {
		        System.out.println("The read failed: " + databaseError.getCode());
		    }
		});
		
		try 
		{
			Thread.sleep(8000);
		} 
		
		catch(IllegalStateException e)
		{
			FirebaseApp.getInstance().delete();
			getMagicPass();
		}
		catch (InterruptedException e) 
		{
			e.printStackTrace();
			 
		}
		
		System.out.println("Log : count : " + mpUser.count);
	} // end of checkMagicPass
	
	public void checkMagicPass()
	{
		switch (mpCount) 
		{
			case 0:
				suMagicPass = true;
				System.out.println("Log : case 0 ->  count : " + mpCount);
				saveMagicPassData(1);
				System.out.println("Log : case 0 ->  count : " + mpCount);
				break;
				
			case 1:
				suMagicPass = true;
				System.out.println("Log : case 1 기존 ->  count : " + mpCount);
				saveMagicPassData(2);
				System.out.println("Log : case 1 ->  count : " + mpCount);
				break;
				
			case 2:
				suMagicPass = true;
				System.out.println("Log : case 2 기존 ->  count : " + mpCount);
				saveMagicPassData(3);
				System.out.println("Log : case 2 ->  count : " + mpCount);
				break;
			
			case 3:
				suMagicPass = true;
				System.out.println("Log : case 3 기존 ->  count : " + mpCount);
				saveMagicPassData(4);
				System.out.println("Log : case 3 ->  count : " + mpCount);
				break;
				
			case 4:
				suMagicPass = true;
				System.out.println("Log : case 4 기존 ->  count : " + mpCount);
				saveMagicPassData(5);
				System.out.println("Log : case 4 ->  count : " + mpCount);
				break;
				
			case 5:
				suMagicPass = false;
				System.out.println("Log : case 5 기존->  count : " + mpCount);
				System.out.println("초과!");
				System.out.println("Log : case 5 ->  count : " + mpCount);
				break;	
				

			default:
				JOptionPane.showMessageDialog(null, "CMP 매점 매직패스 실패하셨습니다.");
				System.out.println("오류!");
				break;
		} // end of switch
	} // end of checkMagicPass
	
	public boolean suceedMagic()
	{
		return suMagicPass;
	} // end of isLoginUser
	
} // end of MagicPass
