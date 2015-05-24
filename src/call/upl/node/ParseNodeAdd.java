package call.upl.node;

import java.math.BigDecimal;
import java.util.List;

import call.upl.core.UPLParser;
import call.upl.core.UPLUtils;
import call.upl.core.Value;
import call.upl.core.ValueType;

public class ParseNodeAdd extends ParseNode
{
	public ParseNodeAdd()
	{
		super("add");
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

			BigDecimal result = decimalA.add(decimalB);

			parser.getStack().push(new Value(result));
		}

		if(valueA.getType() == ValueType.STRING && valueB.getType() == ValueType.STRING)
		{
			String stringA = valueA.getText();
			String stringB = valueB.getText();

			String result = stringA + stringB;

			parser.getStack().push(new Value(result));
		}
		
		return curLine;
	}

}
