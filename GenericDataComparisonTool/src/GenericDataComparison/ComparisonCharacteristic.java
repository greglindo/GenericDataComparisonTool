package GenericDataComparison;

import org.json.simple.JSONObject;

public class ComparisonCharacteristic  {
	private String name;
	private String value;
	
	static final String _userCharacteristicNameNode = "characteristicName";
	static final String _userCharacteristicValueNode = "value";
	
	public ComparisonCharacteristic() {
		
	}
	
	public ComparisonCharacteristic(String name, String value) {
		this.setName(name);
		this.setValue(value);
	}
	
	public ComparisonCharacteristic(JSONObject data) {
		if (data != null) {
			this.setName(data.get(_userCharacteristicNameNode).toString());
			this.setValue(data.get(_userCharacteristicValueNode).toString());
		}
	}
	
	public JSONObject convertToJSONObject() {
		JSONObject jsonResult = new JSONObject();
		jsonResult.put(_userCharacteristicNameNode, this.getName());
		jsonResult.put(_userCharacteristicValueNode, this.getValue());
		return jsonResult;
	}
	
	public String getName() {
		return this.name;
	}
	
	public String getValue() {
		return this.value;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public void setValue(String value) {
		this.value = value;
	}
}
