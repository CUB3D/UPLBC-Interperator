package call.upl.core;
import java.math.BigDecimal;


public class Value
{
	private ValueType type;
	
	private BigDecimal number;
	private String text;
	
	public Value(BigDecimal dec)
	{
		this.type = ValueType.NUMBER;
		this.number = dec;
	}
	
	public Value(String text)
	{
		this.type = ValueType.STRING;
		this.text = text;
	}
	
	public ValueType getType()
	{
		return type;
	}
	
	public BigDecimal getNumber()
	{
		return number;
	}
	
	public String getText()
	{
		return text.replaceAll("/n", "\n");
	}
}
