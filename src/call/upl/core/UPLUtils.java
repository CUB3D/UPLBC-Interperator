package call.upl.core;
import call.upl.core.value.ArrayValue;
import call.upl.core.value.NumberValue;
import call.upl.core.value.Value;

import java.math.BigDecimal;
import java.util.Map;


public class UPLUtils
{
    public static final String REGEX_MATCH_ARRAY_ACCESS = "[A-Za-z@0-9]+\\[[A-Za-z@0-9]+\\]";

    public static Value getValue(String s, UPLParser parser)
    {
        if(!s.matches("[\\x00-\\x20]*[+-]?(NaN|Infinity|((((\\p{Digit}+)(\\.)?((\\p{Digit}+)?)([eE][+-]?(\\p{Digit}+))?)|(\\.((\\p{Digit}+))([eE][+-]?(\\p{Digit}+))?)|(((0[xX](\\p{XDigit}+)(\\.)?)|(0[xX](\\p{XDigit}+)?(\\.)(\\p{XDigit}+)))[pP][+-]?(\\p{Digit}+)))[fFdD]?))[\\x00-\\x20]*")) // check if number
        {
            s = getRealName(s, parser);
        }

        return getValueImpl(s, parser);
    }

    public static Value getValueImpl(String s, UPLParser parser)
    {
        Value value = null;

        if(s.matches("[\\x00-\\x20]*[+-]?(NaN|Infinity|((((\\p{Digit}+)(\\.)?((\\p{Digit}+)?)([eE][+-]?(\\p{Digit}+))?)|(\\.((\\p{Digit}+))([eE][+-]?(\\p{Digit}+))?)|(((0[xX](\\p{XDigit}+)(\\.)?)|(0[xX](\\p{XDigit}+)?(\\.)(\\p{XDigit}+)))[pP][+-]?(\\p{Digit}+)))[fFdD]?))[\\x00-\\x20]*")) // check if number
        {
            value = new NumberValue(new BigDecimal(s));
        }
        else
        {
            if(s.matches(REGEX_MATCH_ARRAY_ACCESS)) // array access (x[y])
            {
                String[] args = s.split("\\[");
                String name = args[0];

                ArrayValue arrayValue = (ArrayValue) getValueImpl(name, parser);

                String valueString = args[1].substring(0, args[1].length() - 1);

                int pos = getValueImpl(valueString, parser).getNumber().intValue();

                value = arrayValue.getArray()[pos];
            } else
            {
                Map<String, Value> map = parser.getMap();

                if(map.containsKey(s))
                    value = map.get(s);
                else
                    new Exception("Cannot pharse: " + s).printStackTrace();
            }
        }

        return value;
    }

    public static void putValue(String name, Value value, UPLParser parser)
    {
        putValueImpl(getRealName(name, parser), value, parser);
    }

    public static void putValueImpl(String name, Value value, UPLParser parser)
    {
        if(name.matches(REGEX_MATCH_ARRAY_ACCESS)) // array access (x[y])
        {
            String[] args = name.split("\\[");
            String arrayName = getRealName(args[0], parser);

            ArrayValue arrayValue = (ArrayValue) getValueImpl(arrayName, parser);

            String valueString = args[1].substring(0, args[1].length() - 1);

            int pos = getValueImpl(valueString, parser).getNumber().intValue();

            arrayValue.setArray(pos, value);
        }
        else
        {
            parser.getMap().put(name, value);
        }
    }

    public static String getRealName(String name, UPLParser parser)
    {
        String realName = "";

        for(Object s : parser.getNamespaceStack().toArray())
        {
            if(s instanceof String)
            {
                String s1 = (String) s;

                realName = s1 + "@";
            }
        }

        realName += name;

        return realName;
    }
}
