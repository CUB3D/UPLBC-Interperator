package call.upl.node;

import call.upl.core.UPL;
import call.upl.core.UPLParser;
import call.upl.core.UPLUtils;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class ParseNodeNsp extends ParseNode
{
	public ParseNodeNsp()
	{
		super("nsp");
	}
	
	@Override
	public int execute(UPLParser parser, String[] args, int curLine, String line, List<String> code)
	{
		String name = args[1];

        parser.getNamespaceStack().add(name);

		return curLine;
	}
}
