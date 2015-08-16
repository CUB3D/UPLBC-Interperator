package call.upl.core.value;

import org.omg.PortableInterceptor.ObjectReferenceFactory;
import sun.swing.plaf.windows.ClassicSortArrowIcon;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Callum on 29/05/2015.
 */
public class NativeClassValue extends Value
{
    private Object classInstance;
    private Class<?> classType;

    public NativeClassValue(Object classInstance)
    {
        this.classInstance = classInstance;
        this.classType = classInstance.getClass();

        this.type = ValueType.NATIVE_CLASS;
    }

    public NativeClassValue(String className, Object... constructorArguments)
    {
        try
        {
            this.classType = Class.forName(className);
        } catch (ClassNotFoundException e)
        {
            e.printStackTrace();
        }

        //create list of constructor types

        Class<?>[] constructorArgumentTypes = new Class<?>[constructorArguments.length];

        for(int i = 0; i < constructorArguments.length; i++)
        {
            constructorArgumentTypes[i] = constructorArguments[i].getClass();
        }

        try
        {
            this.classInstance = classType.getConstructor(constructorArgumentTypes).newInstance(constructorArguments);
        } catch (InstantiationException e)
        {
            e.printStackTrace();
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

        this.type = ValueType.NATIVE_CLASS;
    }

    @Override
    public String getText()
    {
        return classType.getName();
    }

    @Override
    public Object getData()
    {
        return classInstance;
    }
}
