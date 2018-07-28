package GenericDataComparison;

import java.util.ArrayList;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

public class GenericComparisonManager {
	private ArrayList<ObjectType> objectTypes;
	private UserComparisonEntry userComparisonEntry;
	private ComparisonChart comparisonChart;
	private JsonFileManager jsonFileManager;
	
	static final String _allDataNode = "GdcData";
	static final String _userDataNode = "userData";
	static final String _objectTypesNode = "objectTypes";
	
	public GenericComparisonManager() {
		objectTypes = new ArrayList<ObjectType>();
		userComparisonEntry = new UserComparisonEntry();
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
	        // TODO: same pattern as above but try to get array for user data
		}
	}
	
	public void saveData()
	{
		JSONArray allData = new JSONArray();
		
		JSONObject jsonObjectTypes = new JSONObject();
		jsonObjectTypes.put(_objectTypesNode, saveObjectTypes());
		allData.add(jsonObjectTypes);
		
		// save user data
		
		JSONObject root = new JSONObject();
		root.put(_allDataNode, allData);
		jsonFileManager.saveDataToFile(root);
	}
	
	public void generateChart(String name, ObjectType baseLineData, UserComparisonEntry userData, Boolean createLegend) {
		comparisonChart = new ComparisonChart(name, baseLineData, userData, createLegend);
		comparisonChart.generateChart();
	}
	
	public UserComparisonEntry getUserComparisonEntry() {
		return this.userComparisonEntry;
	}
	
	public void setUserComparisonEntry(UserComparisonEntry userComparisonEntry) {
		this.userComparisonEntry = userComparisonEntry;
	}
}
