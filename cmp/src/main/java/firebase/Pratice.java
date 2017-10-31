package firebase;

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

import model.Code;
import model.User;


class ProductCode
{
	Code code;
	String pCode;
	public void savepCode()
	{
		final FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference ref = database.getReference("CMP/Product");

        DatabaseReference usersRef = ref.child("Code");

        Map<String, Code> codes = new HashMap<String, Code>();
        codes.put("Info", new Code("1"));
        

        usersRef.setValue(codes);
        
        System.out.println("Log : putData");
        
        Task<Void> task = usersRef.setValue(codes);
        
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
	} // end of savepCode
	
	public void getpCode()
	{
		final FirebaseDatabase database = FirebaseDatabase.getInstance();
		DatabaseReference ref = database.getReference("CMP/Product/Code/Info");
		System.out.println("getpCode");
		
		ref.addValueEventListener(new ValueEventListener() 
		{	 
		    public void onDataChange(DataSnapshot dataSnapshot) 
		    {
		    	code = dataSnapshot.getValue(Code.class);
		        System.out.println("Code : " + code);
		        System.out.println("code.productCode : " + code.productCode);
		        pCode = code.productCode;
		        System.out.println("pCode ->>>" + pCode);
		        
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
		
	} // end of getpCode
	
	
} // end of ProductCode

public class Pratice
{

	public static void main(String args[])
	{
		DB db = new DB();
		ProductCode pc = new ProductCode();
		
		db.connectDB();
		//pc.savepCode();
		pc.getpCode();

	} // end of main
	
} // end of Pratice