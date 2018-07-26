package GenericDataComparison;

//	This class will be used to do everything regarding generating the Box & Whisker JFreeChart
public class ComparisonChart {
	private String title;
	private ObjectType objectType;
	private UserComparisonEntry userEntry;
	private int height;
	private int width;
	private Boolean createLegend;
	private String xAxisName;
	private String yAxisName;
	
	public ComparisonChart(String title, ObjectType baseLineData, UserComparisonEntry userData, Boolean createLegend) {
		
	}
	
	private void createChartDataset() {
		
	}
	
	public void generateChart() {
		this.createChartDataset();
	}
	
	public void saveChartToImage() {
		
	}
	
	//	Declare the getters and setters
}
