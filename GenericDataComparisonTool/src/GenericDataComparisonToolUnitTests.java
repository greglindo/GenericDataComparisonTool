import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;

import org.junit.jupiter.api.Test;

import junit.framework.Assert;

class GenericDataComparisonToolUnitTests {

	private ObjectType sut;
	
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
	
	@SuppressWarnings("deprecation")
	@Test
	void testSaving() throws Exception {
		sut = new ObjectType(CreateObjectType().getName(), CreateObjectType().getCharacteristics());
		
		sut.setDataFile("C:\\GDC\\data.txt");
		
		sut.saveObjectType(CreateObjectType());
		
		Assert.assertEquals("cars", CreateObjectType().getName());
	}
	
	@Test
	void testReading() throws Exception {
		sut = new ObjectType();
		sut.setDataFile("C:\\GDC\\data.txt");
		
		ArrayList<ObjectType> result = sut.GetAllObjectTypes();
				
		Assert.assertTrue(result.size() > 0);
	}

}
