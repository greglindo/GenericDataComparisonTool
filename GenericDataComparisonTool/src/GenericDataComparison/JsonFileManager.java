package GenericDataComparison;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;

import javax.json.Json;
import javax.json.JsonObject;
import javax.json.JsonReader;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

public class JsonFileManager {
	private String fileName;
	
	static final String _idNode = "id";
	static final String _typeNode = "type";	
	static final String _nameNode = "name";	
	static final String _characteristicsNode = "characteristics";
	static final String _attributeNode = "attribute";
	static final String _valueNode = "value";
	static final String _minimumValueNode = "minimumValue";
	static final String _firstQuartileNode = "firstQuartile";
	static final String _medianValueNode = "medianValue";
	static final String _thirdQuartileNode = "thirdQuartile";
	static final String _maximumValueNode = "maximumValue";
	static final String _averageValueNode = "averageValue";
	static final String _weightValueNode = "weightValue";
	static final String _betterValueNode = "betterValue";
	static final String _userComparisonEntry = "userEntry";
	static final String _dataNode = "data";

	static final String _fileDirectory = "C:\\GDC\\";
    static final String _fileExtension = ".txt";
	
    public JsonFileManager() {
		
	}
	
//	public JsonFileManager(String fileName) {
//		this.setFileName(fileName);
//	}
	
	public JsonFileManager(String objectTypeName) {
		this.setFileName(objectTypeName);
	}
	
	//	TODO: This method really belongs in the Object Type class
	public void CreateJsonFileStructure(long id, String objectTypeName) throws Exception {
		JSONObject jsonDataObject = new JSONObject();
		JSONObject jsonWrapperObject = new JSONObject();
		JSONObject jsonObjectType = new JSONObject();		
		
		JSONArray jsonDataObjectTypeList = new JSONArray();
		JSONArray jsonCharacteristicList = new JSONArray();
		JSONArray jsonUserEntries = new JSONArray();
		
		Boolean fileExists = this.fileCreated();
		
		//if (!fileExists) {
			//	Add the Object type name to JSON
			jsonObjectType.put(_idNode, id);
			jsonObjectType.put(_nameNode, objectTypeName);
			
			//	Add an empty characteristics array element to object type JSON
			jsonObjectType.put(_characteristicsNode, jsonCharacteristicList);
			
			//	Add an empty user comparison entry array element to object type JSON
			jsonObjectType.put(_userComparisonEntry, jsonUserEntries);
			
			//	Add object type to data
			jsonDataObject.put(_typeNode, jsonObjectType);
			
			//	Add the data object type to a JSON Object type array
			jsonDataObjectTypeList.add(jsonDataObject);
			
			//	Add the Object type array to main JSON
			jsonWrapperObject.put(_dataNode, jsonDataObjectTypeList);
			
			//	Save to file
			saveJsonDataToFile(jsonWrapperObject);
		//}
	}
	
	public JSONArray readFromFile() {		
		JSONArray items = new JSONArray();		
		try {			
			for (String file : getAllFiles()) {
				JSONParser parser = new JSONParser();				
				Object jsonObject = parser.parse(new FileReader(_fileDirectory + file));				
				items.add((JSONObject)jsonObject);
			}			
		}catch (Exception e) {
			
		}		
		return items;
	}
	
	public JSONObject getJSONObjectFromFile(String name) {		
		JSONObject item = new JSONObject();		
		try {
				this.setFileName(name);
				
				JSONParser parser = new JSONParser();
				
				Object jsonObject = parser.parse(new FileReader(this.getFileName()));
				
				item = (JSONObject)jsonObject;
			
			
		}catch (Exception e) {
			
		}		
		return item;
	}
	
	private ArrayList<String> getAllFiles(){
		ArrayList<String> fileNames = new ArrayList<String>();
		File folder = new File(_fileDirectory);
        
        String[] files = folder.list();
         
        for (String file : files) 
        {
        	fileNames.add(file);
        }
        
        return fileNames;
	}
	
	public void saveJsonDataToFile(JSONObject data) throws IOException {
		if (data != null) {
			String jsonDataString = data.toJSONString();
			
			Boolean fileExists = fileCreated();
			
			if (!fileExists) {
				try (FileWriter file = new FileWriter(this.getFileName())){
					file.write(jsonDataString);
				}	
			}
			else {
				try (FileWriter fileWriter = new FileWriter(this.getFileName(), true)){
					fileWriter.write(jsonDataString);
				}		
			}				
		}
	}
	
			
	public void addJSONArray(String nodeName, String objectTypeName, JSONObject objectData, JSONArray jsonArrayData)
	{
		JSONObject newJsonObject = new JSONObject();
		if (jsonArrayData != null) {
			newJsonObject.put(nodeName, jsonArrayData);
		}
	}
	
	public Boolean fileCreated() {
		//	This method will be used to determine if the JSON file has been created
		File file = new File(this.getFileName());
		
		if (file.exists())
			return true;
		else
			return false;
	}
	
	public void clearFile() throws FileNotFoundException {
		PrintWriter writer = new PrintWriter(this.getFileName());
		writer.print("");
		writer.close();
	}
	
	public void deleteFile() {
		File file = new File(this.getFileName());
		
		if (file.exists()) {
			file.delete();
		}
	}
	
	public String getFileName() {
		return this.fileName;
	}
	
	public void setFileName(String name) {
		
        this.fileName = _fileDirectory + name + _fileExtension;
	}
	
	
}
