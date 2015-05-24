package call.upl.node;

import java.math.BigDecimal;
import java.util.List;

import call.upl.core.UPL;
import call.upl.core.UPLParser;
import call.upl.core.UPLUtils;
import call.upl.core.Value;

public class ParseNodeMov extends ParseNode
{
	public ParseNodeMov()
	{
		super("mov");
	}

	@Override
	public int execute(UPLParser parser, String[] args, int curLine, String line, List<String> code)
	{
		String name = args[1];

		String valueStr = args[2];

		BigDecimal value = UPLUtils.getValue(valueStr, parser).getNumber();

		if(UPL.DEBUG)
		{
			System.out.println("Moved value: " + value.toPlainString() + ", Into: " + name);
		}

		parser.getMap().put(name, new Value(value));
		
		return curLine;
	}
}
