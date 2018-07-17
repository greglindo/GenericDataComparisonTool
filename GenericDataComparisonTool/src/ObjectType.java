import java.io.*;
import java.util.ArrayList;

public class ObjectType {
	private String name;
	private ArrayList<Characteristic> characteristics;
	
	public ObjectType(String name, ArrayList<Characteristic> characteristics)
	{
		this.name = name;
		this.characteristics = characteristics;
	}
	
	public ObjectType(String fileName)
	{
		
	}
	
	public String convertToString()
	{
		String characteristicLine = "";
		String newLine = "\n";
		
		for (int x = 0; x < this.characteristics.size(); x++)
		{
			characteristicLine += this.characteristics.get(x).convertToString() + newLine; 
		}
		
		return "<data>" +
			   "<type>" + 
			   "<name>" + this.name + "</name>" +
			   "<characteristics>" + 
			   characteristicLine + 
			   "</characteristics>" +
			   "</type>" +
			   "</data>";
	}
	
	public String getName()
	{
		return this.name;
	}
	
	public ArrayList<Characteristic> getCharacteristics()
	{
		return this.characteristics;
	}
	
	public void setName(String name)
	{
		this.name = name;
	}
	
	public void setCharacteristics(ArrayList<Characteristic> characteristics)
	{
		this.characteristics = characteristics;
	}
}
