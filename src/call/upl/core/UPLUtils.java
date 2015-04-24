package call.upl.core;
import java.math.BigDecimal;
import java.util.Map;


public class UPLUtils
{
	public static Value getValue(String s, UPLParser parser)
	{
		Value value = null;

		if(s.matches("[\\x00-\\x20]*[+-]?(NaN|Infinity|((((\\p{Digit}+)(\\.)?((\\p{Digit}+)?)([eE][+-]?(\\p{Digit}+))?)|(\\.((\\p{Digit}+))([eE][+-]?(\\p{Digit}+))?)|(((0[xX](\\p{XDigit}+)(\\.)?)|(0[xX](\\p{XDigit}+)?(\\.)(\\p{XDigit}+)))[pP][+-]?(\\p{Digit}+)))[fFdD]?))[\\x00-\\x20]*")) // check if int
			value = new Value(new BigDecimal(s));
		else
		{
			Map<String, Value> map = parser.getMap();
			
			if(map.containsKey(s))
				value = map.get(s);
			else
				new Exception("Cannot pharse: " + s).printStackTrace();
		}

		return value;
	}
}
