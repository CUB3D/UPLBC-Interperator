package call.upl.node;

import java.math.BigDecimal;
import java.util.List;

import call.upl.core.UPLParser;
import call.upl.core.UPLUtils;
import call.upl.core.value.NumberValue;
import call.upl.core.value.Value;

public class ParseNodeDiv extends ParseNode
{
	public ParseNodeDiv()
	{
		super("div");
	}

	@Override
	public int execute(UPLParser parser, String[] args, int curLine, String line, List<String> code)
	{
		String namea = args[1];
		String nameb = args[2];

		BigDecimal valuea = UPLUtils.getValue(namea, parser).getNumber();
		BigDecimal valueb = UPLUtils.getValue(nameb, parser).getNumber();

		BigDecimal result = valuea.divide(valueb);

		parser.getStack().push(new NumberValue(result));
		
		return curLine;
	}

}
