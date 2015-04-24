package call.upl.core;
import cub3d.file.main.FileAPI;
import cub3d.file.reader.Reader;


public class UPL
{
	private UPLPreprocessor preprocessor;
	private UPLParser parser;
	
	public UPL(String file)
	{
		FileAPI api = new FileAPI(file);
		
		Reader r = null;
		
		try
		{
			r = api.getReader();
		}catch(Exception e) {
			e.printStackTrace();
		}
		
		this.preprocessor = new UPLPreprocessor(r);
		this.preprocessor.process();
		
		this.parser = new UPLParser(this.preprocessor);	
		this.parser.beginParse();
	}
	
	public static void main(String[] args) throws Exception
	{
		new UPL("E:\\Development\\Java\\UPL Compiler\\Test.o");
	}
}
