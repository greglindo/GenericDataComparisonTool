
public class Characteristic {
	private String attribute;
	private double minimumValue;
	private double maximumValue;
	private double averageValue;
	private double medianValue;
	private double scoreWeightValue;
	private double firstQuartile;
	private double thirdQuartile;
	private String value;
	private BetterValue betterValue;
	
	public Characteristic(String attribute, double minValue, double maxValue, double avgValue, 
			double medianValue, double scoreWgtValue, double firstQrtile, double thirdQrtile, String value)
	{
		this.attribute = attribute;
		this.value = value;
		this.minimumValue = minValue;
		this.maximumValue = maxValue;
		this.averageValue = avgValue;
		this.medianValue = medianValue;
		this.scoreWeightValue = scoreWgtValue;
		this.firstQuartile = firstQrtile;
		this.thirdQuartile = thirdQrtile;
	}
	
	public Characteristic(String xmlCharacteristicData)
	{
		this.attribute = xmlCharacteristicData.substring(xmlCharacteristicData.indexOf("<attribute>") + 11, xmlCharacteristicData.indexOf("</attribute>"));
		this.value = xmlCharacteristicData.substring(xmlCharacteristicData.indexOf("<value>") + 7, xmlCharacteristicData.indexOf("</value>"));;
		this.minimumValue = convertToDouble(xmlCharacteristicData.substring(xmlCharacteristicData.indexOf("<minimumValue>") + 14, xmlCharacteristicData.indexOf("</minimumValue>")));
		this.maximumValue = convertToDouble(xmlCharacteristicData.substring(xmlCharacteristicData.indexOf("<maximumValue>") + 14, xmlCharacteristicData.indexOf("</maximumValue>")));
		this.averageValue = convertToDouble(xmlCharacteristicData.substring(xmlCharacteristicData.indexOf("<averageValue>") + 14, xmlCharacteristicData.indexOf("</averageValue>")));
		this.medianValue = convertToDouble(xmlCharacteristicData.substring(xmlCharacteristicData.indexOf("<medianValue>") + 13, xmlCharacteristicData.indexOf("</medianValue>")));
		this.scoreWeightValue = convertToDouble(xmlCharacteristicData.substring(xmlCharacteristicData.indexOf("<weightValue>") + 13, xmlCharacteristicData.indexOf("</weightValue>")));
		this.firstQuartile = convertToDouble(xmlCharacteristicData.substring(xmlCharacteristicData.indexOf("<firstQuartile>") + 15, xmlCharacteristicData.indexOf("</firstQuartile>")));
		this.thirdQuartile = convertToDouble(xmlCharacteristicData.substring(xmlCharacteristicData.indexOf("<thirdQuartile>") + 15, xmlCharacteristicData.indexOf("</thirdQuartile>")));
		this.betterValue = convertToBetterValue(xmlCharacteristicData.substring(xmlCharacteristicData.indexOf("<betterValue>") + 15, xmlCharacteristicData.indexOf("</betterValue>")));
		
	}
	
	public String convertToString()
	{
		return "<attribute>" + attribute + "</attribute>" +
				"<value>" + value + "</value>" +
				"<minimumValue>" + minimumValue + "</minimumValue>" +
				"<maximumValue>" + maximumValue + "</maximumValue>" + 
				"<averageValue>" + averageValue + "</averageValue>" +
				"<medianValue>" + medianValue + "</medianValue>" +
				"<weightValue>" + scoreWeightValue + "</weightValue>" +
				"<firstQuartile>" + firstQuartile + "</firstQuartile>" + 
				"<thirdQuartile>" + thirdQuartile + "</thirdQuartile>" +
				"<betterValue>" + betterValue.toString() + "</betterValue>";
	}
	
	private double convertToDouble(String value)
	{	
		if (value != null && !value.isEmpty())
			return Double.parseDouble(value);
		else
			return 0;
	}
	
	private BetterValue convertToBetterValue(String value)
	{
		if (value != null && !value.isEmpty())
		{
			if (value.equals("LOWEST"))
				return BetterValue.LOWEST;
			else
				return BetterValue.HIGHEST;
		}
		else
			return BetterValue.LOWEST;
	}
	
	public String getAttribute()
	{
		return attribute;
	}
	
	public String getValue()
	{
		return this.value;
	}
	
	public double getMinimumValue()
	{
		return minimumValue;
	}
	
	public double getMaximumValue()
	{
		return maximumValue;
	}
	
	public double getAverageValue()
	{
		return averageValue;
	}
	
	public double getMedianValue()
	{
		return medianValue;
	}
	
	public double getScoreWeightValue()
	{
		return scoreWeightValue;
	}
	
	public double getFirstQuartile()
	{
		return firstQuartile;
	}
	
	public double getThirdQuartile()
	{
		return thirdQuartile;
	}
	
	public BetterValue getBetterValue()
	{
		return betterValue;
	}
	
	public void setAttribute(String attribute)
	{
		this.attribute = attribute;
	}
	
	public void setValue(String value)
	{
		this.value = value;
	}
	
	public void setMinimumValue(double minValue)
	{
		this.minimumValue = minValue;
	}
	
	public void setMaximumValue(double maxValue)
	{
		this.maximumValue = maxValue;
	}
	
	public void setAverageValue(double avgValue)
	{
		this.averageValue = avgValue;
	}
	
	public void setMedianValue(double medValue)
	{
		this.medianValue = medValue;
	}
	
	public void setScoreWeightValue(double scoreWgtValue)
	{
		this.scoreWeightValue = scoreWgtValue;
	}
	
	public void setFirstQuartile(double firstQrtile)
	{
		this.firstQuartile = firstQrtile;
	}
	
	public void setThirdQuartile(double thirdQrtile)
	{
		this.thirdQuartile = thirdQrtile;
	}
	
	public void setBetterValue(BetterValue betterValue)
	{
		this.betterValue = betterValue;
	}
}
