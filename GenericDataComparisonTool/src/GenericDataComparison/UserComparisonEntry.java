package GenericDataComparison;

import java.util.ArrayList;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

public class UserComparisonEntry {
	private String name;
	private ArrayList<ComparisonCharacteristic> comparisonCharacteristics;
	private JsonFileManager fileManager;
	private ObjectType objectType;
	
	static final String _dataNode = "data";	
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
	static final String _userDataNode = "userData";
	static final String _userEntryNameNode = "name";
	static final String _userCharacteristicNameNode = "characteristicName";
	static final String _userCharacteristicValueNode = "value";
	
	
	public UserComparisonEntry() {
		
	}
	
	public UserComparisonEntry(String name, ArrayList<ComparisonCharacteristic> userCharacteristics) {
		this.setName(name);
		this.setComparisonCharacteristics(userCharacteristics);
	}
	
	public ArrayList<UserComparisonEntry> getAll(String objectTypeName){
		return this.parseJSONToUserComparisonEntries(objectTypeName);
	}
	
	public UserComparisonEntry getUserComparisonEntryByName(String objectTypeName, String name) {
		
		for (UserComparisonEntry item : this.getAll(objectTypeName)) {
			if (item.getName().equals(name)) {
				return item;
			}
		}
		return new UserComparisonEntry();
	}
	
	public void save(String objectTypeName)
	{
		fileManager = new JsonFileManager(this.getName());
		objectType = new ObjectType();
		
		try {
			JSONObject jsonObject = fileManager.getJSONObjectFromFile(objectTypeName);
			
			JSONArray jsonDataElementList = (JSONArray)jsonObject.get(_dataNode);
			
			JSONObject jsonObjectTypeNode = null;
			JSONObject jsonObjectType = null;
			JSONObject jsonUserEntry = null;
			JSONObject jsonUserData = null;
			
			JSONArray jsonCharacteristicList = new JSONArray();
			JSONArray jsonUserCharacteristicList = new JSONArray();
			
			
			for (int x = 0; x < jsonDataElementList.size(); x++) {
				jsonObjectTypeNode = (JSONObject)jsonDataElementList.get(x);
				
				jsonObjectType = (JSONObject)jsonObjectTypeNode.get(_typeNode);
				
				if (jsonObjectType.get(_nameNode).toString().equals(objectTypeName)) {
					jsonCharacteristicList = (JSONArray)jsonObjectType.get(_characteristicsNode);
					
					//jsonObjectType = objectType.addObjectType(jsonCharacteristicList);
					
					jsonUserEntry = new JSONObject();
					jsonUserEntry.put(_userEntryNameNode, this.getName());
					
					for (int v = 0; v < jsonCharacteristicList.size(); v++) {
						JSONObject jsonUserCharacteristic = (JSONObject)jsonCharacteristicList.get(v);
						
						jsonUserData = new JSONObject();
						jsonUserData.put(_userCharacteristicNameNode, jsonUserCharacteristic.get(_attributeNode).toString());
						jsonUserData.put(_userCharacteristicValueNode, "");
						
						jsonUserCharacteristicList.add(jsonUserData);
					}
					
					jsonUserEntry.put(_userDataNode, jsonUserCharacteristicList);
					
					jsonObjectType.put(_nameNode, objectTypeName);
					jsonObjectType.put(_characteristicsNode, jsonCharacteristicList);
					jsonObjectType.put(_userComparisonEntry, jsonUserEntry);
				}
			}
			jsonObjectTypeNode.put(_typeNode, jsonObjectType);
			
			jsonDataElementList.add(jsonObjectTypeNode);	
			
			jsonObject.put(_dataNode, jsonDataElementList);			
			
			fileManager.clearFile();
			fileManager.saveJsonDataToFile(jsonObject);
			
		} catch (Exception e) {
			
		}
		
	}
	
	public void deleteUserEntry(String name) {
		
	}
	
	private ArrayList<UserComparisonEntry> parseJSONToUserComparisonEntries(String objectTypeName){
		ArrayList<UserComparisonEntry> result = new ArrayList<UserComparisonEntry>();		
		ArrayList<ComparisonCharacteristic> characteristics = null;
		
		UserComparisonEntry userEntry = null;
		
		fileManager = new JsonFileManager();
		
		try {
			JSONObject jsonResult = fileManager.getJSONObjectFromFile(objectTypeName);
			
			JSONArray jsonObjects = (JSONArray)jsonResult.get(_dataNode);
			
			for (int y = 0; y < jsonObjects.size(); y++) {
				
				JSONObject jsonObject = (JSONObject)jsonObjects.get(y);				
				JSONObject jsonObjectType = (JSONObject)jsonObject.get(_typeNode);
										
				JSONObject jsonUserEntry = (JSONObject)jsonObjectType.get(_userComparisonEntry);
				userEntry = new UserComparisonEntry();
				
				userEntry.setName(jsonUserEntry.get(_userEntryNameNode).toString());
				JSONArray jsonUserData = (JSONArray)jsonUserEntry.get(_userDataNode);
				
				for (int z = 0; z < jsonUserData.size(); z++) {
					characteristics = new ArrayList<ComparisonCharacteristic>();
					
					JSONObject jsonCharacteristic = (JSONObject)jsonUserData.get(z);
					ComparisonCharacteristic characteristic = new ComparisonCharacteristic(jsonCharacteristic);
					
					characteristics.add(characteristic);
					
					userEntry.setComparisonCharacteristics(characteristics);
				}
				
				result.add(userEntry);				
			}
		} catch (Exception e) {
			String error = e.getMessage();
		}		
		return result;
	}
	
	public String getName() {
		return this.name;				
	}
	
	public ArrayList<ComparisonCharacteristic> getComparisonCharacteristics()
	{
		return this.comparisonCharacteristics;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public void setComparisonCharacteristics(ArrayList<ComparisonCharacteristic> comparisonCharacteristicsData) {
		this.comparisonCharacteristics = comparisonCharacteristicsData;
	}
}
