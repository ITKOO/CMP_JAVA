package inputItem;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import model.Code;
import model.Product;

public class Menu 
{

	String p_address;
	String p_name;
	String p_price;
	String p_stock;
	String p_explan;
	
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
	

	public void getMenu()
	{
		final FirebaseDatabase database = FirebaseDatabase.getInstance();
		System.out.println("1. getProductCode");
		for(int i = 1; i <= Integer.parseInt(pCode); i++)
		{
			DatabaseReference menuRef = database.getReference("CMP/Menu/" + i + "/Info");
			System.out.println("2. getpCode");
			
			menuRef.addValueEventListener(new ValueEventListener() 
			{	 
			    public void onDataChange(DataSnapshot dataSnapshot) 
			    {
			    	System.out.println("3. onDataChange");
			    	p = dataSnapshot.getValue(Product.class);
			    	System.out.println("Product : " + p);
			        System.out.println("code.pictureAddress : " + p.pictureAddress);
			        System.out.println("code.productName : " + p.productName);
			        System.out.println("code.productPrice : " + p.productPrice);
			        System.out.println("code.productStock : " + p.productStock);
			        System.out.println("code.productExplan : " + p.productExplan);
			        
			        p_address = p.pictureAddress;
			    	p_name = p.productName;
			    	p_price = p.productPrice;
			    	p_stock = p.productStock;
			    	p_explan = p.productExplan;

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
		} // end of for
	
	} // end of getMenu
	
	public String returnpCode()
	{
		return pCode;
	}
	public String returnpAddress()
	{
		return p_address;
	}
	
	public String returnpName()
	{
		return p_name;
	}
	
	public String returnpPrice()
	{
		return p_price;
	}
	
	public String returnpStock()
	{
		return p_stock;
	}
	
	public String returnpExplan()
	{
		return p_explan;
	}

} // end of Menu 
