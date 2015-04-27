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
	public int execute(UPLParser parser, String[] args, int curLine, String line)
	{
		String var = args[1];

        // dwd Answer: .

        line = line.replaceFirst(getOpcode() + " " + var + " ", "");

        // Answer: .

        parser.getMap().put(var, new Value(line));

        if(UPL.DEBUG)
        {
            System.out.println("DWD, S: " + line + ", Var: " + var);
        }

		return curLine;
	}
}
