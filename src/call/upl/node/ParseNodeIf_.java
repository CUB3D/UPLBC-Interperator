package call.upl.node;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import call.upl.core.UPL;
import call.upl.core.UPLParser;
import call.upl.core.UPLUtils;

public class ParseNodeIf_ extends ParseNode
{
	public ParseNodeIf_()
	{
		super("if");
	}
	
	@Override
	public int execute(UPLParser parser, String[] args, int curLine, String line, List<String> code)
	{
		String arg1 = args[1];
		String arg2 = args[3];
		String op = args[2];

		BigDecimal val1 = UPLUtils.getValue(arg1, parser).getNumber();
		BigDecimal val2 = UPLUtils.getValue(arg2, parser).getNumber();

		List<String> ifCode = new ArrayList<String>();

		for(int n = curLine + 1; n < code.size(); n++)
		{
			if(code.get(n).equals("endif"))
			{
				curLine = n;

				break;
			}
			else
				ifCode.add(code.get(n));
		}

		boolean ans = false;

		if(op.equals("=="))
			ans = val1.compareTo(val2) == 0;

		if(op.equals(">"))
			ans = val1.compareTo(val2) == 1;

		if(op.equals("<"))
			ans = val1.compareTo(val2) == -1;

		if(UPL.DEBUG)
		{
			System.out.println("If:called, " + arg1 + ", " + val1.toPlainString() + ", " + arg2 + ", " + val2.toPlainString() + ", " + op + ", " + ans);
		}

		if(ans)
			parser.parseCode(ifCode);
		return curLine;
	}
}
