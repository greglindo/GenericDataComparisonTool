
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
	
	public Characteristic() {
		
	}
	public Characteristic(String attribute, double minValue, double maxValue, double avgValue, 
			double medianValue, double scoreWgtValue, double firstQrtile, double thirdQrtile, String value, BetterValue betterValue)
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
		this.betterValue = betterValue;
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
