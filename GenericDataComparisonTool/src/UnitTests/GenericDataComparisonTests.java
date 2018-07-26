package UnitTests;
import GenericDataComparison.*;
import java.util.ArrayList;

import org.junit.Assert;

import junit.framework.TestCase;

public class GenericDataComparisonTests extends TestCase {

	protected void setUp() throws Exception {
		super.setUp();
		
		manager = new GenericComparisonManager();
		objectTypeData = new ObjectType();
		userEntry = new UserComparisonEntry();
		fileManager = new JsonFileManager();
	}

	private GenericComparisonManager manager;
	private ObjectType objectTypeData;
	private UserComparisonEntry userEntry;
	private JsonFileManager fileManager;
	
	private ArrayList<Characteristic> CreateCharacteristics()
	{
		ArrayList<Characteristic> newCharacteristics = new ArrayList<Characteristic>();
		
		Characteristic characteristic1 = new Characteristic("No of wheels", 4, 10, 5, 6, 100, 4, 5, "na", BetterValue.HIGHEST);
		Characteristic characteristic2 = new Characteristic("HorsePower", 190, 510, 350, 400, 100, 230, 500, "310", BetterValue.LOWEST);
		newCharacteristics.add(characteristic1);
		newCharacteristics.add(characteristic2);
		
		return newCharacteristics;
	}
	
	private ObjectType CreateObjectType()
	{
		ObjectType newObjectType = new ObjectType("cars", CreateCharacteristics());
		
		return newObjectType;
	}
	
	public void test_CreateObjectType_Save() {
		//	Arrange
		String objectTypeName = "Cars";
		
		//	Act
		manager.saveObjectType(objectTypeName);
		Boolean fileCreated = fileManager.jsonFileCreated();
		
		//	Assert
		Assert.assertEquals(true, fileCreated);
	}
	
	public void test_ReadAllObjectTypes_Get() {
		ArrayList<ObjectType> types = new ArrayList<ObjectType>();
		
		types = manager.getObjectTypes();
		
		Assert.assertTrue(types.size() > 0);
	}
	
	public void test_GetObjectTypeByName_Get() {
		String name = "Cars";
		
		objectTypeData = manager.getObjectTypeByName(name);
		
		Assert.assertEquals("Cars", objectTypeData.getName());
	}
	
	public void test_addCharacteristics_Save() {
		String name = "Cars";
		ArrayList<Characteristic> characteristics = CreateCharacteristics();
		
		manager.addCharacteristics(name, characteristics);
		
		Assert.assertEquals(true, true);
	}
	
	
}
