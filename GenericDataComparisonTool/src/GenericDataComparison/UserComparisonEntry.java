package GenericDataComparison;

import java.util.ArrayList;
import java.util.UUID;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

public class UserComparisonEntry {
	private String name;
	private String objectTypeName;
	private ArrayList<ComparisonCharacteristic> comparisonCharacteristics;
	private UUID userComparisonEntryID;
		
	static final String _userDataNode = "userData";
	static final String _userDataIdNode = "id";	//	use this to map to the object type item
	static final String _userDataNameNode = "name";		
	static final String _userCharacteristicNameNode = "characteristicName";
	static final String _userCharacteristicValueNode = "characteristicValue";
	static UUID objectID;
	
	
	
	public UserComparisonEntry() {
		comparisonCharacteristics = new ArrayList<ComparisonCharacteristic>();
		userComparisonEntryID = (UUID.randomUUID());
	}
	
	public UserComparisonEntry(String name, String objectTypeName, ArrayList<ComparisonCharacteristic> comparisonCharacteristics) {
		userComparisonEntryID = (UUID.randomUUID());
		this.setName(name);
		this.setObjectTypeName(objectTypeName);
		this.setComparisonCharacteristics(comparisonCharacteristics);		
	}
	
	public UserComparisonEntry getUserComparisonEntry(String name) {
		return new UserComparisonEntry();
	}
	
	
	public String getName() {
		return this.name;				
	}
	
	public String getObjectTypeName() {
		return this.objectTypeName;
	}
	
	public ArrayList<ComparisonCharacteristic> getComparisonCharacteristics()
	{
		return this.comparisonCharacteristics;
	}
	
	public void setObjectID(UUID id)
	{
		objectID = id;
	}
	
	public UUID getUserComparisonEntryID() 
	{
		return this.userComparisonEntryID;
	}
	
	public void setName(String name) 
	{
		this.name = name;
	}
	
	public void setObjectTypeName(String objectTypeName) {
		this.objectTypeName = objectTypeName;
	}
	
	public void setComparisonCharacteristics(ArrayList<ComparisonCharacteristic> comparisonCharacteristicsData) {
		this.comparisonCharacteristics = comparisonCharacteristicsData;
	}
	
	public void addComparisonCharacteristic(ComparisonCharacteristic comparisonCharacteristic) 
	{
		comparisonCharacteristics.add(comparisonCharacteristic);
	}
	
	public void deleteComparisonCharcteristics()
	{
		this.comparisonCharacteristics.clear();
	}
	
	public ComparisonCharacteristic getComparisonCharacteristicByName(String name)
	{
		for(ComparisonCharacteristic item : this.comparisonCharacteristics)
		{
			if(item.getName().equals(name))
			{
				return item;
			}
		}
		
		return null;
	}
	
	public ComparisonCharacteristic getComparisonCharacteristicByID(UUID ComparisonCharacteristicByID)
	{
		for(ComparisonCharacteristic item : this.comparisonCharacteristics)
		{
			if(item.getComparisonCharacteristicID() == ComparisonCharacteristicByID)
			{
				return item;
			}
		}
		
		return null;
	}
	
	public void loadUserComparisonEntry(JSONObject data) {
		
		this.setName(data.get(_userDataNameNode).toString());
		this.setObjectTypeName(data.get(_userDataIdNode).toString());
		JSONArray jsonComparisonCharacteristicList = (JSONArray)data.get(_userDataNode);
		for (int idx = 0 ; idx < jsonComparisonCharacteristicList.size(); idx++) {
	        JSONObject item = (JSONObject)jsonComparisonCharacteristicList.get(idx);
	        ComparisonCharacteristic comparisonCharacteristic = new ComparisonCharacteristic();
	        comparisonCharacteristic.loadComparisonCharacteristic(item);
	        this.comparisonCharacteristics.add(comparisonCharacteristic);
		}
	}
	
	public JSONObject saveUserComparisonEntry() 
	{				
		JSONObject jsonComparisonCharacteristic = new JSONObject();		
		jsonComparisonCharacteristic.put(_userDataNameNode, this.getName());
		jsonComparisonCharacteristic.put(_userDataIdNode, this.getObjectTypeName());
				
		JSONArray jsonComparisonCharacteristicList = new JSONArray();
		if (this.comparisonCharacteristics.size() > 0) {
			for (ComparisonCharacteristic item : this.comparisonCharacteristics) {
				jsonComparisonCharacteristicList.add(item.saveComparisonCharacteristic());
			}
			jsonComparisonCharacteristic.put(_userDataNode, jsonComparisonCharacteristicList);
		}
		
		return jsonComparisonCharacteristic;		
	}
}
