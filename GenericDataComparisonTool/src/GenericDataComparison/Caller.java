package GenericDataComparison;

public class Caller 
{
	public enum UIType
	{
		StartWindow,
		BaselineObjectWindow,
		EditOrCompareWindow,
		OutputWindow
	}
	
	public enum UIFunction
	{
		Back,
		Save,
		EditCompare,
		New,
		Delete,
		Edit,
		Compare
	}
	
	public UIType type;
	public UIFunction function;
	
	public Caller(UIType t, UIFunction f)
	{
		type = t;
		function = f;
	}
}
