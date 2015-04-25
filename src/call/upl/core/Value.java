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

	@Override
	public String toString()
	{
		String ret = "";

		if(text != null)
		{
			ret += "Text: " + text;
		}

		if(number != null)
		{
			ret += "Number: " + number.toPlainString();
		}

        if(text != null && number != null)
        {
            ret = "Test: " + text + ", Number: " + number.toPlainString();
        }

        return ret;
	}
}
