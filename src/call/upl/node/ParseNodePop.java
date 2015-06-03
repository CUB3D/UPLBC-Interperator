package call.upl.node;

import java.util.List;

import call.upl.core.UPL;
import call.upl.core.UPLParser;
import call.upl.core.UPLUtils;
import call.upl.core.value.Value;

public class ParseNodePop extends ParseNode
{
	public ParseNodePop()
	{
		super("pop");
	}
	
	@Override
	public int execute(UPLParser parser, String[] args, int curLine, String line, List<String> code)
	{
		String name = null;

		if(args.length > 1)
			name = args[1];

		if(!parser.getStack().isEmpty())
		{
			Value value = parser.getStack().pop();

            if(UPL.DEBUG)
            {
				System.out.println("Stack:pop, " + name + ", " + (value == null ? "NULL" : value.toString()));
            }

			if(name != null)
			{
				UPLUtils.putValue(name, value, parser);
			}
		}

		return curLine;
	}
}
