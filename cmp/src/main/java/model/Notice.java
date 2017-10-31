package model;

public class Notice
{
	public String notice;
	
	// 기본생성자
	public Notice(){}
	
	public Notice(String notice)
	{
		this.notice = notice;
	} // end of constructor

	public String getNotice() 
	{
		return notice;
	} // end of getter

	public void setNotice(String notice)
	{
		this.notice = notice;
	} // end of setter

	@Override
	public String toString() 
	{
		return "Notice [notice=" + notice + "]";
	} // end of toString

} // end of Notice
