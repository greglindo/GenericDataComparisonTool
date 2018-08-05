package GenericDataComparison;

public class Caller 
{
	public enum UIType
	{
		BaselineObjectWindow,
		EditOrCompareWindow,
		OutputWindow
	}
	
	public enum UIFunction
	{
		Back,
		Save,
		Open
	}
	
	public UIType type;
	public UIFunction function;
	
	public Caller(UIType t, UIFunction f)
	{
		type = t;
		function = f;
	}
}
