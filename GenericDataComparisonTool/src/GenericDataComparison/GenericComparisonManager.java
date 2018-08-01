package GenericDataComparison;

import org.jfree.chart.ChartPanel;
import java.util.ArrayList;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

public class GenericComparisonManager {
	private ArrayList<ObjectType> objectTypes;
	private ArrayList<UserComparisonEntry> userComparisonEntries;
	private ComparisonChart comparisonChart;
	private JsonFileManager jsonFileManager;
	
	static final String _allDataNode = "GdcData";
	static final String _userEntryNode = "userEntry";
	static final String _objectTypesNode = "objectTypes";
	
	public GenericComparisonManager() {
		objectTypes = new ArrayList<ObjectType>();
		userComparisonEntries = new ArrayList<UserComparisonEntry>();
		jsonFileManager = new JsonFileManager();
	}
	
	public ArrayList<ObjectType> getObjectTypes(){
		return objectTypes;
	}
	
	public ObjectType getObjectTypeByName(String name)
	{
		for(ObjectType item : objectTypes)
		{
			if(item.getName().equals(name));
			{
				return item;
			}
		}
		
		return null;
	}
	
	public void addObjectType(ObjectType objType)
	{
		objectTypes.add(objType);
	}
	
	public void deleteObjectTypes()
	{
		objectTypes.clear();
	}
	
	public void deleteObjectTypeByName(String name)
	{
		for(ObjectType item : objectTypes)
		{
			if(item.getName() == name)
			{
				objectTypes.remove(item);
				return;
			}
		}
	}
	
	public void loadObjectTypes(JSONArray data)
	{
		for (int idx = 0 ; idx < data.size(); idx++) {
	        JSONObject item = (JSONObject)data.get(idx);
	        ObjectType objType = new ObjectType();
	        objType.loadObjectType(item);
	        this.objectTypes.add(objType);
		}
	}
	
	public JSONArray saveObjectTypes()
	{
		JSONArray jsonObjectTypeList = new JSONArray();
		for(ObjectType item : objectTypes)
		{
			jsonObjectTypeList.add(item.saveObjectType());
		}
		
		return jsonObjectTypeList;
	}
	
	public ArrayList<UserComparisonEntry> getUserComparisonEntries(){
		return this.userComparisonEntries;
	}
	
	public UserComparisonEntry getUserComparisonEntryByName(String objectTypeName, String name) {
		
		for(UserComparisonEntry item : this.userComparisonEntries)
		{
			if(item.getObjectTypeName().equals(objectTypeName) && item.getName().equals(name));
			{
				return item;
			}
		}
		
		return null;
	}
	
	public void addUserComparisonEntry(UserComparisonEntry userEntry)
	{
		this.userComparisonEntries.add(userEntry);
	}
	
	public void deleteUserComparisonEntries()
	{
		this.userComparisonEntries.clear();
	}
	
	public void deleteUserComparisonEntryByName(String name)
	{
		for(UserComparisonEntry item : this.userComparisonEntries)
		{
			if(item.getName() == name)
			{
				userComparisonEntries.remove(item);
				return;
			}
		}
	}
	
	public ArrayList<ComparisonCharacteristic> generateComparisonCharacteristicsFromObjectTypeCharacteristics(String objectTypeName)
	{
		ArrayList<ComparisonCharacteristic> items = new ArrayList<ComparisonCharacteristic>();
		
		this.loadData();
		
		ObjectType objectType = getObjectTypeByName(objectTypeName);
		
		if (objectType != null) {
			for (Characteristic characteristic: objectType.getCharacteristics()) {
				ComparisonCharacteristic item = new ComparisonCharacteristic(characteristic.getName(), 0);
				items.add(item);
			}
		}
		
		return items;
	}
	
	public void loadUserComparisonEntries(JSONArray data)
	{
		for (int idx = 0 ; idx < data.size(); idx++) {
	        JSONObject item = (JSONObject)data.get(idx);
	        UserComparisonEntry userEntry = new UserComparisonEntry();
	        userEntry.loadUserComparisonEntry(item);
	        this.userComparisonEntries.add(userEntry);
		}
	}
	
	public JSONArray saveUserComparisonEntries()
	{
		JSONArray jsonUserComparisonEntryList = new JSONArray();
		for(UserComparisonEntry item : this.userComparisonEntries)
		{
			jsonUserComparisonEntryList.add(item.saveUserComparisonEntry());
		}
		
		return jsonUserComparisonEntryList;
	}
	
	public void loadData()
	{
		JSONObject root = jsonFileManager.loadDataFromFile();
		if(root == null)
		{
			return;
		}
		
		JSONArray jsonAllData = (JSONArray)root.get(_allDataNode);
		for (int idx = 0 ; idx < jsonAllData.size(); idx++) {
	        JSONObject item = (JSONObject)jsonAllData.get(idx);
	        
	        // is this the object type array
	        JSONArray objs = (JSONArray)item.get(_objectTypesNode);
	        if(objs != null)
	        {
	        	this.deleteObjectTypes();
	        	this.loadObjectTypes(objs);
	        	continue;
	        }
	        
	        // is this the user data array
	        JSONArray userData = (JSONArray)item.get(_userEntryNode);
	        if (userData != null) {
	        	this.deleteUserComparisonEntries();
	        	this.loadUserComparisonEntries(userData);
	        }
		}
	}
	
	public void saveData()
	{
		JSONArray allData = new JSONArray();
		
		JSONObject jsonObjectTypes = new JSONObject();
		jsonObjectTypes.put(_objectTypesNode, saveObjectTypes());
		allData.add(jsonObjectTypes);
		
		// save user data
		JSONObject jsonUserComparisonEntries = new JSONObject();
		jsonUserComparisonEntries.put(_userEntryNode, this.saveUserComparisonEntries());
		
		allData.add(jsonUserComparisonEntries);
		
		JSONObject root = new JSONObject();
		root.put(_allDataNode, allData);
		jsonFileManager.saveDataToFile(root);
	}
	
	public ChartPanel generateBoxAndWhiskerChart(ObjectType objectType, String characteristicName, double userValue, Boolean createLegend) {
		comparisonChart = new ComparisonChart();
		
		return comparisonChart.getChartPanelWithComparisonData(objectType, characteristicName, userValue, createLegend);
	}
	
	public ArrayList<UserComparisonEntry> getUserComparisonEntry() {
		return this.userComparisonEntries;
	}
	
	public void setUserComparisonEntry(ArrayList<UserComparisonEntry> userComparisonEntries) {
		this.userComparisonEntries = userComparisonEntries;
	}
}
