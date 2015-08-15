package call.upl.node;

import call.upl.core.UPLParser;
import call.upl.core.UPLUtils;
import call.upl.core.value.ArrayValue;
import call.upl.core.value.NativeClassValue;
import call.upl.core.value.StringValue;
import call.upl.core.value.Value;

import java.lang.reflect.InvocationTargetException;
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

        Value retValue = null;

        if(ret instanceof String)
        {
            retValue = new StringValue((String) ret);
        }

        if(ret instanceof Object[])
        {
            Object[] arry = (Object[]) ret;
            Value[] valueArry = new Value[arry.length];

            for(int i = 0; i < arry.length; i++)
            {
                Object o = arry[i];

                if(o instanceof String)
                {
                    valueArry[i] = new StringValue((String) o);
                }
            }
        }

        if(retValue != null)
        {
            parser.getStack().push(retValue);
        }
		
		return curLine;
	}

}
