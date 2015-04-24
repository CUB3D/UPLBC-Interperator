package call.upl.core;
import java.util.List;


public class Function
{
	private String name;
	
	private List<String> code;
	
	public Function(String name, List<String> code)
	{
		this.name = name;
		
		this.code = code;
	}
	
	public String getName()
	{
		return name;
	}
	
	public List<String> getCode()
	{
		return code;
	}
}
