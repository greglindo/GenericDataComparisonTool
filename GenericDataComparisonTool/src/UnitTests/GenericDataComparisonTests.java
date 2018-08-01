package UnitTests;

import GenericDataComparison.*;

import java.io.File;
import java.util.ArrayList;
import org.junit.Assert;
import junit.framework.TestCase;

public class GenericDataComparisonTests extends TestCase {

	protected void setUp() throws Exception {
		super.setUp();
		
		manager = new GenericComparisonManager();
		objectTypeData = new ObjectType();
		userEntry = new UserComparisonEntry();
		comparisonCharacteristics = new ArrayList<ComparisonCharacteristic>();
		fileManager = new JsonFileManager();
	}

	private GenericComparisonManager manager;
	private ObjectType objectTypeData;
	private UserComparisonEntry userEntry;
	private JsonFileManager fileManager;
	private ArrayList<ComparisonCharacteristic> comparisonCharacteristics; 
	
	private ArrayList<Characteristic> CreateCharacteristics()
	{
		ArrayList<Characteristic> newCharacteristics = new ArrayList<Characteristic>();
		
		Characteristic characteristic1 = new Characteristic("No of wheels", 4, 10, 5, 6, 100, 4, 5, BetterValue.HIGHEST);
		Characteristic characteristic2 = new Characteristic("HorsePower", 190, 510, 350, 400, 100, 230, 500, BetterValue.LOWEST);
		newCharacteristics.add(characteristic1);
		newCharacteristics.add(characteristic2);
		
		return newCharacteristics;
	}
	
	private ArrayList<ComparisonCharacteristic> CreateComparisonCharacteristics()
	{
		ArrayList<ComparisonCharacteristic> comparisonCharacteristics = new ArrayList<ComparisonCharacteristic>();
		
		ComparisonCharacteristic item1 = new ComparisonCharacteristic("No of wheels", 4);
		ComparisonCharacteristic item2 = new ComparisonCharacteristic("HorsePower", 200);
		
		comparisonCharacteristics.add(item1);
		comparisonCharacteristics.add(item2);
		
		return comparisonCharacteristics;
	}
	
	private UserComparisonEntry CreateUserComparisonEntry(){		
		String name = "2014 Car"; 
		String objectTypeName = "cars";
		UserComparisonEntry newUserEntry = new UserComparisonEntry(name, objectTypeName, GetComparisonCharacteristcsForLoadonUI());				
		return newUserEntry;
	}
	
	private ObjectType CreateObjectType()
	{
		ObjectType newObjectType = new ObjectType("cars", CreateCharacteristics());
		
		return newObjectType;
	}
	
	private ArrayList<ComparisonCharacteristic> GetComparisonCharacteristcsForLoadonUI() {
		String objectTypeName = "cars";
		comparisonCharacteristics = manager.generateComparisonCharacteristicsFromObjectTypeCharacteristics(objectTypeName);	
		return comparisonCharacteristics;
	}
	
	public void test_CreateCarObject()
	{
		manager.addObjectType(CreateObjectType());
		
		Assert.assertEquals(1, manager.getObjectTypes().size());
	}
	
	public void test_SaveCarObject()
	{
		test_CreateCarObject();
		manager.saveData();
		
		File file = new File(fileManager.getFileName());
		Assert.assertEquals(true, file.exists());
	}
	
	public void test_LoadCarObject()
	{
		test_SaveCarObject();
		manager.loadData();
		
		Assert.assertEquals(1, manager.getObjectTypes().size());
	}
	
	public void test_ReadAllObjectTypes_Get() 
	{
		test_LoadCarObject();
		ArrayList<ObjectType> types = new ArrayList<ObjectType>();		
		types = manager.getObjectTypes();
		
		Assert.assertTrue(types.size() > 0);
	}
	
	public void test_GetObjectTypeByName_Get() {
		test_LoadCarObject();
		objectTypeData = manager.getObjectTypeByName("cars");
		
		Assert.assertEquals("cars", objectTypeData.getName());
	}
	
	public void test_saveUserComparisonEntry()
	{
		this.userEntry = CreateUserComparisonEntry();
		manager.addUserComparisonEntry(this.userEntry);	
		manager.saveData();
		
		File file = new File(fileManager.getFileName());
		Assert.assertEquals(true, file.exists());
	}
}
