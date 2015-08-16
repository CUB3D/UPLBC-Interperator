package call.upl.node;

import call.upl.core.UPLParser;
import call.upl.core.UPLUtils;
import call.upl.core.value.*;

import java.lang.reflect.InvocationTargetException;
import java.math.BigDecimal;
import java.util.List;

public class ParseNodeCallNativeMethod extends ParseNode
{
	public ParseNodeCallNativeMethod()
	{
		super("cnm");
	}

	@Override
	public int execute(UPLParser parser, String[] args, int curLine, String line, List<String> code)
	{
		NativeClassValue classValue = (NativeClassValue) UPLUtils.getValue(args[1], parser);
		String methodName = UPLUtils.getValue(args[2], parser).getText();

		Object[] arguments = new Object[args.length - 3];

		for(int i = 3; i < args.length; i++)
		{
			Value v = UPLUtils.getValue(args[i], parser);
			arguments[i - 3] = v.getData();
		}

        Object ret = null;

        try
        {
            ret = classValue.getData().getClass().getDeclaredMethod(methodName).invoke(classValue.getData(), (Object[]) null);
        } catch (IllegalAccessException e)
        {
            e.printStackTrace();
        } catch (InvocationTargetException e)
        {
            e.printStackTrace();
        } catch (NoSuchMethodException e)
        {
            e.printStackTrace();
        }

        Value outputValue = convertOutputToValue(ret);

        if(outputValue != null)
        {
            parser.getStack().push(outputValue);
        }
		
		return curLine;
	}

    public Value convertOutputToValue(Object output)
    {
        Value returnValue = null;

        if(output instanceof String)
        {
            returnValue = new StringValue((String) output);
        }
        else
        {
            if (output.toString().matches(UPLUtils.REGEX_MATCH_NUMBER))
            {
                returnValue = new NumberValue(new BigDecimal(output.toString()));
            }
            else
            {
                if (output instanceof Object[])
                {
                    Object[] objects = (Object[]) output;
                    Value[] values = new Value[objects.length];

                    for (int i = 0; i < objects.length; i++)
                    {
                        Object o = objects[i];

                        values[i] = convertOutputToValue(o);
                    }

                    returnValue = new ArrayValue(values);
                }
            }
        }

        return returnValue;
    }
}
