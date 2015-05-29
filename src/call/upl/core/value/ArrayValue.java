package call.upl.core.value;

import java.math.BigDecimal;

/**
 * Created by Callum on 29/05/2015.
 */
public class ArrayValue extends Value
{
    private Value[] values;

    public ArrayValue(Value[] values)
    {
        this.type = ValueType.ARRAY;
        this.values = values;
    }

    @Override
    public Value[] getArray()
    {
        return values;
    }

    public void setArray(int pos, Value value)
    {
        values[pos] = value;
    }
}
