package call.upl.node;

import call.upl.core.UPL;
import call.upl.core.UPLParser;
import call.upl.core.UPLUtils;
import call.upl.core.value.Value;

import java.util.List;

public class ParseNodePsh extends ParseNode
{
	public ParseNodePsh()
	{
		super("psh");
	}
	
	@Override
	public int execute(UPLParser parser, String[] args, int curLine, String line, List<String> code)
	{
		String name = args[1];

		Value value = UPLUtils.getValue(name, parser);

        if(UPL.DEBUG)
        {
            System.out.println("Stack:push, " + name + ", " + value.toString());
        }

		parser.getStack().push(value);
		
		return curLine;
	}
}
