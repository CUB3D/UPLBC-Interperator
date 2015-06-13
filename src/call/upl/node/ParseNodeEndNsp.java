package call.upl.node;

import call.upl.core.UPLParser;

import java.util.List;

public class ParseNodeEndNsp extends ParseNode
{
	public ParseNodeEndNsp()
	{
		super("endnsp");
	}
	
	@Override
	public int execute(UPLParser parser, String[] args, int curLine, String line, List<String> code)
	{
        parser.getNamespaceStack().pop();

		return curLine;
	}
}
