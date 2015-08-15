package call.upl.node;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.math.BigDecimal;
import java.util.List;

import call.upl.core.UPLParser;
import call.upl.core.value.*;

public class ParseNodeInt extends ParseNode
{
	public ParseNodeInt()
	{
		super("int");
	}
	
	@Override
	public int execute(UPLParser parser, String[] args, int curLine, String line, List<String> code)
	{
		int id = Integer.parseInt(args[1].replace("0x", ""), 16);


		if(id == 1)
		{
			Value value = parser.getStack().pop();

			System.out.print(value.getText());
		}

		if(id == 2)
			parser.getStack().push(new NumberValue(new BigDecimal(parser.getStack().size())));

		if(id == 3)
		{
			try
			{
				parser.getStack().push(new NumberValue(new BigDecimal(System.in.read())));
			} catch(IOException e)
			{
				e.printStackTrace();
			}
		}

		if(id == 4)
		{
			Value v = parser.getStack().pop();

			BigDecimal bigDecimal = v.getNumber();

            String s = "" + (char) bigDecimal.intValue();

			parser.getStack().push(new StringValue(s));
		}

		if(id == 5)
		{
			Value v = parser.getStack().pop();

			char c = v.getText().charAt(0);

			parser.getStack().push(new NumberValue(new BigDecimal((int) c)));
		}

		if(id == 6)
		{
			Value v = parser.getStack().pop();

			if(v.getType() == ValueType.ARRAY)
			{
				parser.getStack().push(new NumberValue(new BigDecimal(v.getArray().length)));
			}
			else
			{
				// error
			}
		}
		
		return curLine;
	}
}
