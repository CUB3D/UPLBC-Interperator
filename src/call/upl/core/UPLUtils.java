package call.upl.core;
import call.upl.core.value.ArrayValue;
import call.upl.core.value.NumberValue;
import call.upl.core.value.StringValue;
import call.upl.core.value.Value;
import call.upl.core.value.ValueType;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.math.BigDecimal;
import java.util.Map;
import java.util.Set;


public class UPLUtils
{
    public static final String REGEX_MATCH_ARRAY_ACCESS = "[A-Za-z@0-9]+\\[[A-Za-z@0-9]+\\]";

    public static final String REGEX_MATCH_NUMBER = "[\\x00-\\x20]*[+-]?(NaN|Infinity|((((\\p{Digit}+)(\\.)?((\\p{Digit}+)?)([eE][+-]?(\\p{Digit}+))?)|(\\.((\\p{Digit}+))([eE][+-]?(\\p{Digit}+))?)|(((0[xX](\\p{XDigit}+)(\\.)?)|(0[xX](\\p{XDigit}+)?(\\.)(\\p{XDigit}+)))[pP][+-]?(\\p{Digit}+)))[fFdD]?))[\\x00-\\x20]*";

    public static Value getValue(String s, UPLParser parser)
    {
        if(!s.matches(REGEX_MATCH_NUMBER))
        {
            s = getRealName(s, parser);
        }

        return getValueImpl(s, parser);
    }

    public static Value getValueImpl(String s, UPLParser parser)
    {
        Value value = null;

        if(s.matches(REGEX_MATCH_NUMBER))
        {
            value = new NumberValue(new BigDecimal(s));
        }
        else
        {
            if(s.matches(REGEX_MATCH_ARRAY_ACCESS)) // array access (x[y])
            {
                String[] args = s.split("\\[");
                String name = args[0];

                Value val = getValueImpl(name, parser);

                ValueType valueType = val.getType();

                if(valueType == ValueType.ARRAY)
                {
                    ArrayValue arrayValue = (ArrayValue) val;

                    String valueString = args[1].substring(0, args[1].length() - 1);

                    int pos = getValue(valueString, parser).getNumber().intValue();

                    value = arrayValue.getArray()[pos];
                }
                else
                {
                    if(valueType == ValueType.STRING)
                    {
                        StringValue stringValue = (StringValue) val;

                        String valueString = args[1].substring(0, args[1].length() - 1);

                        int pos = getValue(valueString, parser).getNumber().intValue();

                        value = new StringValue(""+stringValue.getText().toCharArray()[pos]);
                    }
                }
            } else
            {
                Map<String, Value> map = parser.getMap();

                if(map.containsKey(s))
                {
                    value = map.get(s);
                }
                else
                {
                    Exception e = new Exception("Cannot pharse: " + s);

                    e.printStackTrace();

                    System.out.println("Map dump BEGIN");

                    for(Map.Entry<String, Value> x : map.entrySet())
                    {
                        System.out.println(x.getKey() + " : " + x.getValue().toString());
                    }

                    System.out.println("Map dump END");

                    System.exit(0);
                }
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

            ArrayValue arrayValue = (ArrayValue) getValueImpl(args[0], parser);

            String valueString = args[1].substring(0, args[1].length() - 1);

            int pos = getValue(valueString, parser).getNumber().intValue();

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
