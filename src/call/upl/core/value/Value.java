package call.upl.core.value;

import java.math.BigDecimal;


public class Value
{
	protected ValueType type;
	
	public ValueType getType()
	{
		return type;
	}

	public String getText()
	{
		return null;
	}

	public BigDecimal getNumber()
	{
		return null;
	}

    public Value[] getArray() { return null; }
}
