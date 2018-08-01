package GenericDataComparison;

import java.util.ArrayList;
import java.util.UUID;

public class UserComparisonEntry 
{
	private UUID objectID;
	private String name;
	private ArrayList<ComparisonCharacteristic> comparisonCharacteristics;
	
	public UserComparisonEntry() 
	{
		
	}
	
	public UserComparisonEntry getUserComparisonEntry(String name) 
	{
		return new UserComparisonEntry();
	}
	
	public void saveUserEntry()
	{
		
	}
	
	public void deleteUserEntry() 
	{
		
	}
	
	public UUID getObjectID()
	{
		return objectID;
	}
	
	public String getName() 
	{
		return this.name;				
	}
	
	public ArrayList<ComparisonCharacteristic> getComparisonCharacteristics()
	{
		return this.comparisonCharacteristics;
	}
	
	public void setObjectID(UUID id)
	{
		objectID = id;
	}
	
	public void setName(String name) 
	{
		this.name = name;
	}
	
	public void setComparisonCharacteristics(ArrayList<ComparisonCharacteristic> comparisonCharacteristicsData) 
	{
		this.comparisonCharacteristics = comparisonCharacteristicsData;
	}
}
