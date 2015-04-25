package call.upl.node;

import call.upl.core.UPL;
import call.upl.core.UPLParser;
import call.upl.core.Value;

public class ParseNodeDwd extends ParseNode
{
	public ParseNodeDwd()
	{
		super("dwd");
	}

	@Override
	public int execute(UPLParser parser, String[] args, int curLine)
	{
		String var = args[1];

		String s = "";
		
		for(int i = 2; i < args.length; i++)
			s += args[i] + " ";

        s = s.trim();
		
		parser.getMap().put(var, new Value(s));

        if(UPL.DEBUG)
        {
            System.out.println("DWD, S: " + s + ", Var: " + var);
        }

		return curLine;
	}
}
