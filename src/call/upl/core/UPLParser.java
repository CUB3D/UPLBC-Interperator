package call.upl.core;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.Stack;

import call.upl.core.value.Value;
import call.upl.node.ParseNode;


public class UPLParser
{
	private UPLPreprocessor preprocessor;
	
	private Map<String, Value> map = new HashMap<String, Value>();

	private Stack<Value> stack = new Stack<Value>();

	public UPLParser(UPLPreprocessor preprocessor)
	{
		this.preprocessor = preprocessor;
	}

	public void beginParse()
	{
		parseCode(getCode());
	}

	public void parseCode(List<String> code)
	{
		for(int i = 0; i < code.size(); i++)
		{
			String s = code.get(i);

			if(s.startsWith(".")) // line is function
			{
				while(true)
				{
					i++;

					if(code.get(i).equals("end"))
					{
						break;
					}
				}
			}

			i = parseLine(s, i, code);
		}
	}

	public int parseLine(String s, int i, List<String> text)
	{
		int ret = i;

		String[] args = s.split(" ");

		String opCode = args[0];

		ParseNode pn = ParseNode.getNode(opCode);

		if(pn != null)
			ret = pn.execute(this, args, i, s, text);

		return ret;
	}

	public Map<String, Value> getMap()
	{
		return map;
	}

	public Stack<Value> getStack()
	{
		return stack;
	}
	
	public Set<Function> getFunctions()
	{
		return this.preprocessor.getFunctions();
	}
	
	public List<String> getCode()
	{
		return this.preprocessor.getCode();
	}
}
