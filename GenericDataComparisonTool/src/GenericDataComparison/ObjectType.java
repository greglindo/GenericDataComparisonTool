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
		
	private JsonFileManager jsonFileManager;
	
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
		return ParseJsonObjectToObjectType();		
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
		jsonFileManager = new JsonFileManager();
		jsonFileManager.CreateJsonFileStructure(this.getId(), this.getName());
	}
	
	public void addCharacteristicsToObjectType() {
		jsonFileManager = new JsonFileManager();
		
		try {
			JSONObject jsonObject = jsonFileManager.readFromFile();
						
			JSONArray jsonDataElementList = (JSONArray)jsonObject.get(_dataNode);
			
			for (int x = 0; x < jsonDataElementList.size(); x++) {
				JSONObject jsonObjectTypeNode = (JSONObject)jsonDataElementList.get(x);
				
				JSONObject jsonObjectType = (JSONObject)jsonObjectTypeNode.get(_typeNode);
				JSONArray jsonCharacteristicList = new JSONArray();
				
				if (jsonObjectType.get(_nameNode).toString().equals(this.getName()))
				{
					for (Characteristic item : this.getCharacteristics()) {
						JSONObject jsonCharacteristic = item.convertToJsonObject();						
						jsonCharacteristicList.add(jsonCharacteristic);
					}
					
					jsonObjectType.put(_characteristicsNode, jsonCharacteristicList);
					
					jsonObjectTypeNode.put(_typeNode, jsonObjectType);
					
					jsonDataElementList.add(jsonObjectTypeNode);	
				}
			}
			jsonObject.put(_dataNode, jsonDataElementList);
			
			jsonFileManager.saveJsonDataToFile(jsonObject);
			
		} catch (Exception e) {
			String error = e.getMessage();
		}
	}
	
	public void saveObjectType(ObjectType data) throws Exception {
		
		jsonFileManager = new JsonFileManager();
		
		JSONObject jsonObject = new JSONObject();
		JSONArray jsonObjectTypeList = new JSONArray();
		JSONArray jsonCharacteristicList = new JSONArray();
		
		JSONObject jsonObjectType = new JSONObject();
		JSONObject jsonCharacteristic = null;
		
		jsonObjectType.put(_idNode, data.getId());
		jsonObjectType.put(_nameNode, data.getName());
		
		ArrayList<Characteristic> characteristics = data.getCharacteristics();
		
		if (characteristics.size() > 0) {
			for (int x = 0; x < characteristics.size(); x++) {
				jsonCharacteristic = new JSONObject();
				jsonCharacteristic.put(_attributeNode, characteristics.get(x).getAttribute());
				jsonCharacteristic.put(_valueNode, characteristics.get(x).getValue());
				jsonCharacteristic.put(_minimumValueNode, characteristics.get(x).getMinimumValue());
				jsonCharacteristic.put(_firstQuartileNode, characteristics.get(x).getFirstQuartile());
				jsonCharacteristic.put(_medianValueNode, characteristics.get(x).getMedianValue());
				jsonCharacteristic.put(_thirdQuartileNode, characteristics.get(x).getThirdQuartile());
				jsonCharacteristic.put(_maximumValueNode, characteristics.get(x).getMaximumValue());
				jsonCharacteristic.put(_averageValueNode, characteristics.get(x).getAverageValue());
				jsonCharacteristic.put(_weightValueNode, characteristics.get(x).getScoreWeightValue());
				jsonCharacteristic.put(_betterValueNode, characteristics.get(x).getBetterValue().toString());
				jsonCharacteristicList.add(jsonCharacteristic);
			}
			jsonObjectType.put(_characteristicsNode, jsonCharacteristicList);
		}
		
		jsonObjectTypeList.add(jsonObjectType);
		
		jsonObject.put(_typeNode, jsonObjectTypeList);
		
		jsonFileManager.saveJsonDataToFile(jsonObject);		
	}
	
	public void updateObjectType(ObjectType data) {
		
	}
	
	public void deleteObjectType(String objectTypeName) {
		
	}
	
	private ArrayList<ObjectType> ParseJsonObjectToObjectType() {
		ArrayList<ObjectType> objectTypes = new ArrayList<ObjectType>();
		ArrayList<Characteristic> characteristics = null;
		ObjectType objectType = null;
		Characteristic characteristic = null;
		jsonFileManager = new JsonFileManager();
		
		try {
			
			JSONObject jsonObject = jsonFileManager.readFromFile();
			
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
