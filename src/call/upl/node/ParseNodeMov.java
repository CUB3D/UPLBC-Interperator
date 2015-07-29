package call.upl.node;

import java.math.BigDecimal;
import java.util.List;

import call.upl.core.UPL;
import call.upl.core.UPLParser;
import call.upl.core.UPLUtils;
import call.upl.core.value.NumberValue;
import call.upl.core.value.Value;

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

		Value value = UPLUtils.getValue(valueStr, parser);

		if(UPL.DEBUG)
		{
			System.out.println("Moved value: " + value.toString() + ", Into: " + name);
		}

        UPLUtils.putValue(name, value, parser);
		
		return curLine;
	}
}
