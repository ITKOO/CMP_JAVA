package inputItem;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import model.Code;
import model.Product;

public class Upload 
{
	String pCode;
	Product p;
	Code code;
	DatabaseReference productRef;
	DatabaseReference pCodeRef;
	DatabaseReference resetRef;
	DatabaseReference menuRef;
	
	public void getProductCode()
	{
		final FirebaseDatabase database = FirebaseDatabase.getInstance();
		System.out.println("1. getProductCode");
		pCodeRef = database.getReference("CMP/Product/Code/Info");
		System.out.println("2. getpCode");
		
		pCodeRef.addValueEventListener(new ValueEventListener() 
		{	 
		    public void onDataChange(DataSnapshot dataSnapshot) 
		    {
		    	System.out.println("3. onDataChange");
		    	code = dataSnapshot.getValue(Code.class);
		    	pCode = code.productCode;
		    	menuRef = database.getReference("CMP/Menu");
			    productRef = menuRef.child(pCode);
		        System.out.println("Code : " + code);
		        System.out.println("code.productCode : " + code.productCode);
		    }

		    public void onCancelled(DatabaseError databaseError) 
		    {
		        System.out.println("The read failed: " + databaseError.getCode());
		    }
		});

		try 
		{
			Thread.sleep(2000);
			System.out.println("hi");
		} 
		catch (InterruptedException e) 
		{
			e.printStackTrace();
		}
	} // end of getProductCode
} // end of Upload
