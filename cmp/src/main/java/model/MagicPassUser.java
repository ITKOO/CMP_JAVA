package model;

public class MagicPassUser
{
	public int count;

	public MagicPassUser(){}
	
	public MagicPassUser(int count)
	{
		this.count = count;
	} // end of constructor

	@Override
	public String toString() 
	{
		return "MagicPassUser [count=" + count + "]";
	}

	public int getCount() 
	{
		return count;
	}

	public void setCount(int count) 
	{
		this.count = count;
	}

} // end of MagicPassUser
