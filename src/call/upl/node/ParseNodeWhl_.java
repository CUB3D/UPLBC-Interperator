package call.upl.node;

import call.upl.core.UPL;
import call.upl.core.UPLParser;
import call.upl.core.UPLUtils;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class ParseNodeWhl_ extends ParseNode
{
	public ParseNodeWhl_()
	{
		super("whl");
	}
	
	@Override
	public int execute(UPLParser parser, String[] args, int curLine, String line)
	{
		String arg1 = args[1];
		String arg2 = args[3];
		String op = args[2];

		BigDecimal val1 = UPLUtils.getValue(arg1, parser).getNumber();
		BigDecimal val2 = UPLUtils.getValue(arg2, parser).getNumber();

		List<String> code = new ArrayList<String>();

		for(int n = curLine + 1; n < parser.getCode().size(); n++)
		{
			if(parser.getCode().get(n).equals("endwhl"))
			{
				curLine = n;

				break;
			}
			else
				code.add(parser.getCode().get(n));
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
			System.out.println("While:called, " + arg1 + ", " + val1.toPlainString() + ", " + arg2 + ", " + val2.toPlainString() + ", " + op + ", " + ans);
		}

		while(ans)
		{
            val1 = UPLUtils.getValue(arg1, parser).getNumber();
            val2 = UPLUtils.getValue(arg2, parser).getNumber();

			if(op.equals("=="))
				ans = val1.compareTo(val2) == 0;

			if(op.equals(">"))
				ans = val1.compareTo(val2) == 1;

			if(op.equals("<"))
				ans = val1.compareTo(val2) == -1;

            if(!ans)
            {
                break;
            }

			parser.parseCode(code);
		}

		return curLine;
	}
}
