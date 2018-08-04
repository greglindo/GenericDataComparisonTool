package GenericDataComparison;

import org.json.simple.JSONObject;

public class ComparisonCharacteristic {
	private String name;
	private double value;
	
	static final String _comparisonName = "characteristicName";
	static final String _comparisonValue = "characteristicValue";
	
	public ComparisonCharacteristic() {
		
	}
	
	public ComparisonCharacteristic(String name, double val) {
		this.name = name;
		this.value = val;
	}
	
	public void loadComparisonCharacteristic(JSONObject data) {
		this.setName(data.get(_comparisonName).toString());
		this.setValue(Double.parseDouble(data.get(_comparisonValue).toString()));
	}
	
	public JSONObject saveComparisonCharacteristic() {
		JSONObject ComparisonCharacteristic = new JSONObject();
		ComparisonCharacteristic.put(_comparisonName, this.getName());
		ComparisonCharacteristic.put(_comparisonValue, this.getValue());
		return ComparisonCharacteristic;
	}
	
	public String getName() {
		return this.name;
	}
	
	public double getValue() {
		return this.value;
	}
	
	public void setName(String name) 
	{
		this.name = name;
	}
	
	public void setValue(double value) {
		this.value = value;
	}
}
