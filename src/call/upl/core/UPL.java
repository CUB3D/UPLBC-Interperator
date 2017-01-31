package call.upl.core;


import java.io.BufferedReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class UPL
{
	public static boolean DEBUG = false;

	private UPLPreprocessor preprocessor;
	private UPLParser parser;
	
	public UPL(String file)
	{
		Path path = Paths.get(file);

        BufferedReader reader = null;

        try
        {
            reader = Files.newBufferedReader(path);
        } catch (IOException e)
        {
            e.printStackTrace();
        }

        this.preprocessor = new UPLPreprocessor(reader);
		this.preprocessor.process();
		
		this.parser = new UPLParser(this.preprocessor);	
		this.parser.beginParse();
	}
	
	public static void main(String[] args) throws Exception
	{
		if(args[0].equals("-debug"))
		{
			DEBUG = true;
            new UPL(args[1]);
		}
        else
        {
            new UPL(args[0]);
        }
	}
}
