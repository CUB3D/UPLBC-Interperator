package call.upl.core;

import java.io.BufferedReader;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class UPLPreprocessor
{
	private BufferedReader reader;
	
	private List<String> code = new ArrayList<String>();
	
	private Set<Function> funcs = new HashSet<Function>();
	
	
	public UPLPreprocessor(BufferedReader r)
	{
		this.reader = r;
	}
	
	public void process()
	{
		readCode();
		parseFunctions();
	}
	
	private void readCode()
	{
		try
		{
			String s;

			while((s = reader.readLine()) != null)
			{
				if(!s.isEmpty())
					code.add(s);
			}

		}catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	private void parseFunctions()
	{
		for(int i =0; i < code.size(); i++)
		{
			String s = code.get(i);

			i = parseFunctionLine(s, i);
		}

		if(UPL.DEBUG)
        {
            System.out.println("Found " + funcs.size() + " functions");
        }
	}
	
	private int parseFunctionLine(String s, int i)
	{
		int ret = i;

		if(s.startsWith("."))
		{
			String name = s.substring(1);

			List<String> cod = new ArrayList<String>();

			for(int n = i + 1; n <= code.size(); n++)
			{
				if(code.get(n).equals("end"))
				{
					ret = n;

					break;
				}
				else
					cod.add(code.get(n));
			}

			Function f = new Function(name, cod);

            if(UPL.DEBUG)
            {
                System.out.println("Function added: " + name);
            }

			funcs.add(f);
		}

		return ret;
	}
	
	public Set<Function> getFunctions()
	{
		return funcs;
	}
	
	public List<String> getCode()
	{
		return code;
	}
}
