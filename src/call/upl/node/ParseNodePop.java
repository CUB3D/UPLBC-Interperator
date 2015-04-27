package call.upl.node;

import java.math.BigDecimal;

import call.upl.core.UPL;
import call.upl.core.UPLParser;
import call.upl.core.Value;

public class ParseNodePop extends ParseNode
{
	public ParseNodePop()
	{
		super("pop");
	}
	
	@Override
	public int execute(UPLParser parser, String[] args, int curLine, String line)
	{
		String namea = null;

		if(args.length > 1)
			namea = args[1];

		if(!parser.getStack().isEmpty())
		{
			BigDecimal value = parser.getStack().pop().getNumber();

            if(UPL.DEBUG)
            {
                System.out.println("Stack:pop, " + namea + ", " + value.toPlainString());
            }

			if(namea != null)
				parser.getMap().put(args[1], new Value(value));
		}
		return curLine;
	}
}
