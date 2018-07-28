package GenericDataComparison;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

public class JsonFileManager {
	static final String _fileName = ".\\GdcData.json";
	
	public String getFileName()
	{
		return _fileName;
	}
	
	public JSONObject loadDataFromFile() {
		JSONObject result = null;
		
		File file = new File(_fileName);
		if(file.exists())
		{
			try {
				JSONParser parser = new JSONParser();				
				result = (JSONObject)parser.parse(new FileReader(_fileName));				
			}
			catch(Exception e)
			{
				// TODO: Feedback that data load failed
				result = null;
			}
		}
		
		return result;
	}
	
	public void saveDataToFile(JSONObject data) {
		try
		{
			if(data == null)
			{
				throw new Exception("Cannot save empty object");
			}
				
			String jsonDataString = data.toJSONString();
			FileWriter file = new FileWriter(_fileName);
			file.write(jsonDataString);
			file.close();
		}
		catch (Exception e)
		{
			// TODO: Feedback that save failed
		}
	}
	
}
