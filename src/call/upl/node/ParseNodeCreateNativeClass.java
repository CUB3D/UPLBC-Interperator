package call.upl.node;

import call.upl.core.UPLParser;
import call.upl.core.UPLUtils;
import call.upl.core.value.*;

import java.math.BigDecimal;
import java.util.List;

public class ParseNodeCreateNativeClass extends ParseNode
{
	public ParseNodeCreateNativeClass()
	{
		super("cnc");
	}

	@Override
	public int execute(UPLParser parser, String[] args, int curLine, String line, List<String> code)
	{
		String className = UPLUtils.getValue(args[1], parser).getText();

		Object[] arguments = new Object[args.length - 2];

		for(int i = 2; i < args.length; i++)
		{
			Value v = UPLUtils.getValue(args[i], parser);
			arguments[i - 2] = v.getData();
		}

		parser.getStack().push(new NativeClassValue(className, arguments));
		
		return curLine;
	}

}
