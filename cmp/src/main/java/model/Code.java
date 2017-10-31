package model;

public class Code 
{
	public String productCode;
	
	// 기본 생성자
	public Code(){}
	
	public Code(String productCode)
	{
		this.productCode = productCode;
	} // end of constructor

	public String getProductCode() 
	{
		return productCode;
	}

	public void setProductCode(String productCode) 
	{
		this.productCode = productCode;
	}
	
	@Override
	public String toString() 
	{
		return "Code [productCode=" + productCode + "]";
	}

} // end of Code
