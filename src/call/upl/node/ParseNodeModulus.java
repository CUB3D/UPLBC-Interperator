package call.upl.node;

import call.upl.core.UPLParser;
import call.upl.core.UPLUtils;
import call.upl.core.value.NumberValue;
import call.upl.core.value.StringValue;
import call.upl.core.value.Value;
import call.upl.core.value.ValueType;

import java.math.BigDecimal;
import java.util.List;

public class ParseNodeModulus extends ParseNode
{
	public ParseNodeModulus()
	{
		super("mod");
	}

	@Override
	public int execute(UPLParser parser, String[] args, int curLine, String line, List<String> code)
	{
		String namea = args[1];
		String nameb = args[2];

		Value valueA = UPLUtils.getValue(namea, parser);
		Value valueB = UPLUtils.getValue(nameb, parser);

		if(valueA.getType() == ValueType.NUMBER && valueB.getType() == ValueType.NUMBER)
		{
			BigDecimal decimalA = valueA.getNumber();
			BigDecimal decimalB = valueB.getNumber();

			BigDecimal result = decimalA.remainder(decimalB);

			parser.getStack().push(new NumberValue(result));
		}
		
		return curLine;
	}

}
