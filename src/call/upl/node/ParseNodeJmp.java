package call.upl.node;

import call.upl.core.Function;
import call.upl.core.UPLParser;

public class ParseNodeJmp extends ParseNode
{
	public ParseNodeJmp()
	{
		super("jmp");
	}
	
	@Override
	public int execute(UPLParser parser, String[] args, int curLine, String line)
	{
		String funcName = args[1];

		System.out.println("Jump:called, " + funcName);

		for(Function f : parser.getFunctions())
			if(f.getName().equals(funcName))
			{
				parser.parseCode(f.getCode());
			}
		
		return curLine;
	}
}
