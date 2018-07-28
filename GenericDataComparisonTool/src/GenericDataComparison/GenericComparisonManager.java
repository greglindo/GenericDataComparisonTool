package GenericDataComparison;
import java.util.ArrayList;

public class GenericComparisonManager {
	private ObjectType objectType;
	private UserComparisonEntry userComparisonEntry;
	private ComparisonChart comparisonChart;
	
	public GenericComparisonManager() {
		objectType = new ObjectType();
		userComparisonEntry = new UserComparisonEntry();
	}
	
	//	Get all Object Types
	public ArrayList<ObjectType> getObjectTypes(){
		return objectType.getAllObjectTypes();
	}
	
	//	Get Object Type by name
	public ObjectType getObjectTypeByName(String name) {
		return objectType.getObjectTypeByName(name);
	}
	
	//	Save an Object Type
	public void saveObjectType(String name) {
		objectType.setName(name);
		try {
			objectType.saveObjectTypeName();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			//	Add message of failure to save object Type
		}
	}
	
	//	Delete an Object Type
	public void deleteObjectType(String name) {
		objectType.deleteObjectType(name);
	}
	
	//	Update an Object Type
	
	
	// 	Add Characteristics to Object Type
	public void addCharacteristics(String objectTypeName, ArrayList<Characteristic> characteristics)
	{
		objectType.setName(objectTypeName);
		objectType.setCharacteristics(characteristics);
		objectType.addCharacteristicsToObjectType();
	}
	
	//	Delete Characteristic from Object Type
	
	//	Add User Comparison Entry to Object Type
	public void addUserComparisonEntry(String objectTypeName, String userEntryName) {
		userComparisonEntry.setName(userEntryName);
		userComparisonEntry.save(objectTypeName);
	}
	
	//	Delete User comparison entry from object Type
	
	//	Update User Comparison entry to Object type
	
	//	Generate JFreeChart
	public void generateChart(String name, ObjectType baseLineData, UserComparisonEntry userData, Boolean createLegend) {
		comparisonChart = new ComparisonChart(name, baseLineData, userData, createLegend);
		comparisonChart.generateChart();
	}
	
	public ObjectType getObjectType() {
		return this.objectType;
	}
	
	public UserComparisonEntry getUserComparisonEntry() {
		return this.userComparisonEntry;
	}
	
	public void setObjectType(ObjectType objectType) {
		this.objectType = objectType;
	}
	
	public void setUserComparisonEntry(UserComparisonEntry userComparisonEntry) {
		this.userComparisonEntry = userComparisonEntry;
	}
}
