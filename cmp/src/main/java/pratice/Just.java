package pratice;

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

import firebase.DB;
import model.Code;
import model.Product;


public class Just 
{
	String pCode;
	Product p;
	Code code;
	
	DatabaseReference pCodeRef;
	DatabaseReference resetRef;

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
		    	System.out.println("Code : " + code);
		        System.out.println("code.productCode : " + code.productCode);
		        pCode = code.productCode;
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
		catch (InterruptedException e) 
		{
			e.printStackTrace();
		}
	} // end of getProductCode
	public String returnCode()
	{
		return "[pCode] = " + pCode;
	}
	public void uploadItemData(String pictureAddress, String productName, String productPrice, String productStock , String productExplan)
	{
		final FirebaseDatabase database = FirebaseDatabase.getInstance();
		DatabaseReference menuRef = database.getReference("CMP/Menu");
		
		//상품코드로 경로에 저장
		int cpCode = Integer.parseInt(pCode) + 1;
		DatabaseReference  productRef = menuRef.child(String.valueOf(cpCode));
    
		System.out.println("4. uploadItemData");
		
      Map<String, Product> productList = new HashMap<String, Product>();

         productList.put("Info", new Product(pictureAddress, productName, productPrice, productStock , productExplan));
        
         productRef.setValue(productList);
         
         System.out.println("Log : uploadItemData() : putData");
         
         try 
 		{
 			Thread.sleep(8000);
 			
 		} 
 		catch (InterruptedException e) 
 		{
 			e.printStackTrace();
 		}
	} // end of uploadItemData
	public static void main(String args[])
	{
		DB db = new DB();
		db.connectDB();
		Just j = new Just();
		j.getProductCode();
		//j.returnCode();
		j.uploadItemData("야", "야", "dd", "12", "제에발");
		
	} // end of main

} // end of Just
