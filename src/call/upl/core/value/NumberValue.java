package call.upl.core.value;

import java.math.BigDecimal;

/**
 * Created by Callum on 29/05/2015.
 */
public class NumberValue extends Value
{
    private BigDecimal number;

    public NumberValue(BigDecimal dec)
    {
        this.type = ValueType.NUMBER;
        this.number = dec;
    }

    @Override
    public BigDecimal getNumber()
    {
        return number;
    }

    @Override
    public String getText()
    {
        return getNumber().toPlainString();
    }

    @Override
    public Object getData()
    {
        return null; // TODO: add a native number class where there is a object and a type where the object is a Integer or Dobule etc and the type is an enum defining the type
        // the conversion from numberValue would be done by a interrupt and would have a number and a typeID on the stack

        //Or change to fixed type numbers and add casts, they would take about the same amount of effort however I like the way the language does not require deciding the correct type for a value
    }
}
