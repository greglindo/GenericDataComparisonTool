import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;

import javax.json.Json;
import javax.json.JsonArray;
import javax.json.JsonObject;
import javax.json.JsonReader;
import javax.json.JsonValue;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

public class ObjectType {	
	private String name;	
	private ArrayList<Characteristic> characteristics;
	private String dataFile;
	private Boolean assignDataFile;
	
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
		
	public ObjectType()
	{
		
	}
	
	public ObjectType(String name, ArrayList<Characteristic> characteristics)
	{
		this.name = name;
		this.characteristics = characteristics;
	}
	
	public ArrayList<ObjectType> GetAllObjectTypes()
	{
		ArrayList<ObjectType> objectTypes;
		
		objectTypes = ReadJsonFile(this.dataFile);
		return objectTypes;
	}
	
	public ObjectType GetObjectTypeByName(String objectTypeName)
	{	
		ArrayList<ObjectType> objectTypes = GetAllObjectTypes();
		
		for (ObjectType item : objectTypes) {
			if (item.name.equals(name))
				return item;
		}
		
		return new ObjectType();
	}
	
	public void saveObjectType(ObjectType data) {
		JSONObject jsonObject = new JSONObject();
		JSONArray jsonObjectTypeList = new JSONArray();
		JSONArray jsonCharacteristicList = new JSONArray();
		
		JSONObject jsonObjectType = new JSONObject();
		JSONObject jsonCharacteristic = null;
		
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
		
		try {
			saveJsonStringToFile(jsonObject.toJSONString());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}	
	}
	
	public void saveJsonStringToFile(String jsonData) throws IOException {
		try (FileWriter file = new FileWriter(this.dataFile)){
			file.write(jsonData);
		}	
	}
	
	public void updateObjectType(ObjectType data) {
		
	}
	
	public void deleteObjectType(String objectTypeName) {
		
	}
	
	private ArrayList<ObjectType> ReadJsonFile(String dataFile) {		
		ArrayList<ObjectType> objectTypes = new ArrayList<ObjectType>();
		ArrayList<Characteristic> characteristics = new ArrayList<Characteristic>();
		ObjectType objectType = new ObjectType();
		Characteristic characteristic = new Characteristic();
		
		try {
			InputStream inputStream = new FileInputStream(new File(dataFile));
			
			JsonReader jsonReader = Json.createReader(inputStream);
			
			JsonObject jsonObject = jsonReader.readObject();
			jsonReader.close();
			
			JsonArray jsonObjectTypeList = jsonObject.getJsonArray(_typeNode);
			
			for (JsonValue jsonObjectType : jsonObjectTypeList) {
				objectType = new ObjectType();
				
				JsonObject jsonObjectTypeItem = jsonObjectType.asJsonObject();
				
				objectType.setName(jsonObjectTypeItem.get(_nameNode).toString());
				
				JsonObject jsonCharacteristics = jsonObjectTypeItem.asJsonObject();
				
				JsonArray jsonCharacteristicList = jsonCharacteristics.getJsonArray(_characteristicsNode);
				
				for (JsonValue jsonCharacteristic : jsonCharacteristicList) {
					characteristic = new Characteristic();
					
					JsonObject jsonCharacteristicItem = jsonCharacteristic.asJsonObject();
					characteristic.setAttribute(jsonCharacteristicItem.get(_attributeNode).toString());
					characteristic.setValue(jsonCharacteristicItem.get(_valueNode).toString());
					characteristic.setMinimumValue(Double.parseDouble(jsonCharacteristicItem.get(_minimumValueNode).toString()));
					characteristic.setFirstQuartile(Double.parseDouble(jsonCharacteristicItem.get(_firstQuartileNode).toString()));
					characteristic.setMedianValue(Double.parseDouble(jsonCharacteristicItem.get(_medianValueNode).toString()));
					characteristic.setThirdQuartile(Double.parseDouble(jsonCharacteristicItem.get(_thirdQuartileNode).toString()));
					characteristic.setMaximumValue(Double.parseDouble(jsonCharacteristicItem.get(_maximumValueNode).toString()));
					characteristic.setAverageValue(Double.parseDouble(jsonCharacteristicItem.get(_averageValueNode).toString()));
					characteristic.setScoreWeightValue(Double.parseDouble(jsonCharacteristicItem.get(_weightValueNode).toString()));
					
					if (jsonCharacteristicItem.get(_betterValueNode).toString().equals("LOWEST")) {
						characteristic.setBetterValue(BetterValue.LOWEST);
					}else {
						characteristic.setBetterValue(BetterValue.HIGHEST);
					}
					
					characteristics.add(characteristic);					
				}
				
				objectType.setCharacteristics(characteristics);
				objectTypes.add(objectType);
			}
			
		} catch (Exception e) {
			String error = e.getMessage();
		}
		return objectTypes;
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
	
	public String getDataFile()
	{
		return this.dataFile;
	}
	
	public void setDataFile(String file)
	{
		this.dataFile = file;
	}
	
	public Boolean getAssignDataFile()
	{
		return this.assignDataFile;
	}
	
	public void setAssignDataFile(Boolean assignDataFile)
	{
		this.assignDataFile = assignDataFile;
	}
}
