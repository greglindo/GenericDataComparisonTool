import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;

import org.junit.jupiter.api.Test;

import junit.framework.Assert;

class GenericDataComparisonToolUnitTests {

	private ObjectType sut;
	
	private ArrayList<Characteristic> CreateCharacteristics()
	{
		ArrayList<Characteristic> newCharacteristics = new ArrayList<Characteristic>();
		
		Characteristic characteristic1 = new Characteristic("No of wheels", 4, 10, 5, 6, 100, 4, 5, "");
		newCharacteristics.add(characteristic1);
		
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
		
		sut.setDataFile("C:\\GDC\\data.xml");
		
		sut.saveDataFile();
		
		Assert.assertEquals("cars", CreateObjectType().getName());
	}

}
