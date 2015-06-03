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
}
