package call.upl.core.value;

/**
 * Created by Callum on 29/05/2015.
 */
public class StringValue extends Value
{
    private String text;

    public StringValue(String text)
    {
        this.type = ValueType.STRING;
        this.text = text;
    }

    @Override
    public String getText()
    {
        return text.replaceAll("/n", "\n");
    }
}
