package GenericDataComparison;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Map;

import javax.json.Json;
import javax.json.JsonArray;
import javax.json.JsonObject;
import javax.json.JsonReader;
import javax.json.JsonValue;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

public class ObjectType {
	private long id;
	private String name;	
	private ArrayList<Characteristic> characteristics;	
		
	private JsonFileManager fileManager;
	
	//	TODO: Extract these properties to its own class so it can be used elsewhere
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
	static final String _userCharacteristicNameNode = "name";
	static final String _userCharacteristicValueNode = "value";
	static final String _dataNode = "data";
		
	public ObjectType()
	{
		this.setId(generatedUniqueId());
	}
	
	public ObjectType(String name, ArrayList<Characteristic> characteristics)
	{
		this.setId(generatedUniqueId());
		this.setName(name);
		this.setCharacteristics(characteristics);
	}
	
	public ArrayList<ObjectType> getAllObjectTypes()
	{	
		return this.parseJsonObjectToObjectType();		
	}
	
	public ObjectType getObjectTypeByName(String objectTypeName)
	{	
		ArrayList<ObjectType> objectTypes = getAllObjectTypes();
		
		for (ObjectType item : objectTypes) {
			if (item.name.equals(objectTypeName))
				return item;
		}
		
		return new ObjectType();
	}
	
	public void saveObjectTypeName() throws Exception {
		fileManager = new JsonFileManager(this.getName());
		fileManager.CreateJsonFileStructure(this.getId(), this.getName());
	}
	
	public void addCharacteristicsToObjectType() {
		fileManager = new JsonFileManager(this.getName());
		
		try {
			JSONObject jsonObject = fileManager.getJSONObjectFromFile(this.getName());
						
			JSONArray jsonDataElementList = (JSONArray)jsonObject.get(_dataNode);
			
			JSONObject jsonObjectTypeNode = null;
			JSONArray jsonCharacteristicList = new JSONArray();
			JSONObject jsonObjectType = null;
			
			for (int x = 0; x < jsonDataElementList.size(); x++) {
				jsonObjectTypeNode = (JSONObject)jsonDataElementList.get(x);
				
				jsonObjectType = (JSONObject)jsonObjectTypeNode.get(_typeNode);
				
				if (jsonObjectType.get(_nameNode).toString().equals(this.getName()))
				{
					jsonCharacteristicList = (JSONArray)jsonObjectType.get(_characteristicsNode);
					jsonObjectType = this.addObjectType(jsonCharacteristicList);
				}				
			}
			jsonObjectTypeNode.put(_typeNode, jsonObjectType);
			
			jsonDataElementList.add(jsonObjectTypeNode);	
			
			jsonObject.put(_dataNode, jsonDataElementList);			
			
			fileManager.clearFile();
			fileManager.saveJsonDataToFile(jsonObject);
			
		} catch (Exception e) {
			String error = e.getMessage();
		}
	}
	
	public JSONObject addObjectType(JSONArray existingCharacteristics) throws Exception {
		
		JSONObject jsonObject = new JSONObject();		
		JSONArray jsonCharacteristicList = new JSONArray();
		
		if (this != null) {
			jsonObject.put(_idNode, this.getId());
			jsonObject.put(_nameNode, this.getName());
			
			jsonCharacteristicList = mergeCharacteristics(existingCharacteristics);
			jsonObject.put(_characteristicsNode, jsonCharacteristicList);	
		}
		
		return jsonObject;	
	}
	
	private JSONArray mergeCharacteristics(JSONArray existingCharacteristics) {
		JSONArray characteristics = new JSONArray();
		
		if (existingCharacteristics != null) {
			characteristics = existingCharacteristics;
		}
		
		for (Characteristic item : this.getCharacteristics()) {
			JSONObject jsonCharacteristic = item.convertToJsonObject();						
			characteristics.add(jsonCharacteristic);
		}
		
		return characteristics;
	}
	
	public void updateObjectType(ObjectType data) {
		
	}
	
	public void deleteObjectType(String objectTypeName) {
		
	}
	
	private ArrayList<ObjectType> parseJsonObjectToObjectType() {
		ArrayList<ObjectType> objectTypes = new ArrayList<ObjectType>();
		ArrayList<Characteristic> characteristics = null;
		ObjectType objectType = null;
		Characteristic characteristic = null;
		fileManager = new JsonFileManager();
		
		try {
			
			JSONArray jsonObjects = fileManager.readFromFile();
			
			for (int y = 0; y < jsonObjects.size(); y++) {
			
				JSONObject jsonObject = (JSONObject)jsonObjects.get(y);
				
				JSONArray jsonDataElementList = (JSONArray)jsonObject.get(_dataNode);
				
				for (int x = 0; x < jsonDataElementList.size(); x++) {
					JSONObject jsonObjectTypeNode = (JSONObject)jsonDataElementList.get(x);
					
					JSONObject jsonObjectType = (JSONObject)jsonObjectTypeNode.get(_typeNode);
					JSONArray jsonCharacteristicList = new JSONArray();
					
					objectType = new ObjectType();
					
					objectType.setId(Long.parseLong(jsonObjectType.get(_idNode).toString()));
					objectType.setName(jsonObjectType.get(_nameNode).toString());
					
					jsonCharacteristicList = (JSONArray)jsonObjectType.get(_characteristicsNode);
					
					characteristics = new ArrayList<Characteristic>();
					
					for (int i = 0; i < jsonCharacteristicList.size(); i++) {
						JSONObject jsonCharacteristic = (JSONObject)jsonCharacteristicList.get(i);
						characteristic = new Characteristic(jsonCharacteristic);
						
						characteristics.add(characteristic);
					}
					
					objectType.setCharacteristics(characteristics);
				}
				
				objectTypes.add(objectType);
			}
			
		} catch (Exception e) {
			String error = e.getMessage();
		}
		return objectTypes;
	}
	
	public long getId() {
		return this.id;
	}
	
	public void setId(long id) {
		this.id = id;		
	}
	
	public String getName()
	{
		return this.name;
	}
	
	public ArrayList<Characteristic> getCharacteristics()
	{
		return this.characteristics;
	}
	
	public void setName(String name)
	{
		this.name = name;
	}
	
	public void setCharacteristics(ArrayList<Characteristic> characteristics)
	{
		this.characteristics = characteristics;
	}
	
	
	private long generatedUniqueId()
	{
		return System.currentTimeMillis();
	}
}
