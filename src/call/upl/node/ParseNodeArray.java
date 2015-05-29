package call.upl.node;

import call.upl.core.UPL;
import call.upl.core.UPLParser;
import call.upl.core.UPLUtils;
import call.upl.core.value.ArrayValue;
import call.upl.core.value.NumberValue;
import call.upl.core.value.Value;

import java.math.BigDecimal;
import java.util.InputMismatchException;
import java.util.List;

public class ParseNodeArray extends ParseNode
{
	public ParseNodeArray()
	{
		super("ary");
	}

	@Override
	public int execute(UPLParser parser, String[] args, int curLine, String line, List<String> code)
	{
		String name = args[1];
		BigDecimal size = UPLUtils.getValue(args[2], parser).getNumber();

        Value[] values = new Value[size.intValue()];

		if(UPL.DEBUG)
		{
            System.out.println("Created array: " + values + ", Size: " + size.intValue() + ", Name: " + name);
        }

		parser.getMap().put(name, new ArrayValue(values));
		
		return curLine;
	}
}
