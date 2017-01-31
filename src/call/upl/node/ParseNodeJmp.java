package call.upl.node;

import call.upl.core.Function;
import call.upl.core.UPL;
import call.upl.core.UPLParser;
import call.upl.core.value.Value;

import java.util.List;
import java.util.Map;

public class ParseNodeJmp extends ParseNode
{
	public ParseNodeJmp()
	{
		super("jmp");
	}
	
	@Override
	public int execute(UPLParser parser, String[] args, int curLine, String line, List<String> code)
	{
		String funcName = args[1];

		if(UPL.DEBUG)
		{
			System.out.println("Jump:called, " + funcName);
		}

		boolean functionFound = false;

		for(Function f : parser.getFunctions())
			if(f.getName().equals(funcName))
			{
				functionFound = true;
				parser.parseCode(f.getCode());
			}

		if(!functionFound)
		{
			System.out.println("Map dump BEGIN");

			for(Map.Entry<String, Value> x : parser.getMap().entrySet())
			{
				System.out.println(x.getKey() + " : " + x.getValue().toString());
			}

			System.out.println("Map dump END");

			Exception e = new Exception("Cannot jump to: " + funcName);
			e.printStackTrace();

			System.exit(0);
		}

		return curLine;
	}
}
