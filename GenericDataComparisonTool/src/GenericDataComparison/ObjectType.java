package GenericDataComparison;

import java.util.ArrayList;
import java.util.UUID;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

public class ObjectType
{
	private UUID id;
	private String name;	
	private ArrayList<Characteristic> characteristics;		
	
	static final String _idNode = "id";	
	static final String _nameNode = "name";
	static final String _characteristicsNode = "characteristics";	
	
		
	public ObjectType()
	{
		this.setId(UUID.randomUUID());
		characteristics = new ArrayList<Characteristic>();		
	}
	
	public ObjectType(String name, ArrayList<Characteristic> characteristics)
	{
		this.setId(UUID.randomUUID());
		this.setName(name);
		this.setCharacteristics(characteristics);		
	}
	
	public UUID getId() {
		return this.id;
	}
	
	public void setId(UUID id) {
		this.id = id;		
	}
	
	public String getName()
	{
		return this.name;
	}
	
	public void setName(String name)
	{
		this.name = name;
	}
	
	public ArrayList<Characteristic> getCharacteristics()
	{
		return this.characteristics;
	}
	
	public Characteristic getCharacteristicByName(String name)
	{
		for(Characteristic item : characteristics)
		{
			if(item.getName() == name)
			{
				return item;
			}
		}
		
		return null;
	}
	
	public void setCharacteristics(ArrayList<Characteristic> characteristics)
	{
		this.characteristics = characteristics;
	}
	
	public void addCharacteristic(Characteristic characteristic)
	{
		characteristics.add(characteristic);
	}
	
	public void deleteCharacteristics()
	{
		characteristics.clear();
	}
	
	public void deleteCharacteristicByName(String name)
	{
		for(Characteristic item : characteristics)
		{
			if(item.getName() == name)
			{
				characteristics.remove(item);
				return;
			}
		}
	}
	
	public void loadObjectType(JSONObject data)
	{
		this.setId(UUID.fromString(data.get(_idNode).toString()));
		this.setName(data.get(_nameNode).toString());
		JSONArray jsonCharacteristicList = (JSONArray)data.get(_characteristicsNode);
		for (int idx = 0 ; idx < jsonCharacteristicList.size(); idx++) {
	        JSONObject item = (JSONObject)jsonCharacteristicList.get(idx);
	        Characteristic characteristic = new Characteristic();
	        characteristic.loadCharacteristic(item);
	        this.characteristics.add(characteristic);
		}
	}
	
	public JSONObject saveObjectType() 
	{				
		JSONObject jsonObjectType = new JSONObject();
		jsonObjectType.put(_idNode, this.getId().toString());
		jsonObjectType.put(_nameNode, this.getName());
				
		JSONArray jsonCharacteristicList = new JSONArray();
		if (characteristics.size() > 0) {
			for (Characteristic item : characteristics) {
				jsonCharacteristicList.add(item.saveCharacteristic());
			}
			jsonObjectType.put(_characteristicsNode, jsonCharacteristicList);
		}
		
		return jsonObjectType;		
	}
	
	
}
