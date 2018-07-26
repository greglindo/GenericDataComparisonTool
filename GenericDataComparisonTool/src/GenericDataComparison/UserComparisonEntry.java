package GenericDataComparison;

import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.util.ArrayList;

public class UserComparisonEntry {
	private String name;
	private ArrayList<ComparisonCharacteristic> comparisonCharacteristics;
	
	public UserComparisonEntry() {
		
	}
	
	public UserComparisonEntry getUserComparisonEntry(String name) {
		return new UserComparisonEntry();
	}
	
	public void saveUserEntry()
	{
		
	}
	
	public void deleteUserEntry() {
		
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
