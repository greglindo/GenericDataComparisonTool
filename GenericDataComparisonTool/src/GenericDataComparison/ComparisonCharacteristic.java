package GenericDataComparison;

import java.util.UUID;

import org.json.simple.JSONObject;

public class ComparisonCharacteristic {
	private String name;
	private double value;
	private UUID comparisonCharacteristicID;
	
	static final String _comparisonName = "characteristicName";
	static final String _comparisonValue = "characteristicValue";

	
	public ComparisonCharacteristic() {
		comparisonCharacteristicID = (UUID.randomUUID());
		
	}
	
	public ComparisonCharacteristic(String name, double val) {
		this();
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
	
	public UUID getComparisonCharacteristicID()
	{
		return comparisonCharacteristicID;
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
