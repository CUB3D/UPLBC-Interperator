package call.upl.node;

import java.math.BigDecimal;

import call.upl.core.UPLParser;
import call.upl.core.Value;
import call.upl.core.ValueType;

public class ParseNodeInt extends ParseNode
{
	public ParseNodeInt()
	{
		super("int");
	}
	
	@Override
	public int execute(UPLParser parser, String[] args, int curLine)
	{
		int id = Integer.parseInt(args[1].replace("0x", ""), 16);


		if(id == 1)
		{
			Value value = parser.getStack().pop();

			if(value.getType() == ValueType.NUMBER)
				System.out.print(value.getNumber().intValue());

			if(value.getType() == ValueType.STRING)
				System.out.print(value.getText());
		}

		if(id == 2)
			parser.getStack().push(new Value(new BigDecimal(parser.getStack().size())));
		
		return curLine;
	}
}
