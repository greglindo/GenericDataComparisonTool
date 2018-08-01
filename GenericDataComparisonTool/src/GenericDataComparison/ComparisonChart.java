package GenericDataComparison;

import java.awt.BasicStroke;
import java.awt.Color;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.CategoryPlot;
import org.jfree.chart.plot.ValueMarker;
import org.jfree.chart.renderer.category.BoxAndWhiskerRenderer;
import org.jfree.data.statistics.BoxAndWhiskerItem;
import org.jfree.data.statistics.DefaultBoxAndWhiskerCategoryDataset;
import org.jfree.ui.TextAnchor;

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
	
	public ComparisonChart() {
		
	}
	
	public ComparisonChart(String title, ObjectType baseLineData, UserComparisonEntry userData, Boolean createLegend) {
		this.setTitle(title);
		this.setObjectType(baseLineData);
		this.setUserComparisonEntry(userData);
		this.setCreateLegend(createLegend);
	}
	
	private void createChartDataset() {
		
	}
	
	public void generateChart() {
		this.createChartDataset();
	}
	
	public void saveChartToImage() {
		
	}
	
	public String getTitle() {
		return this.title;
	}
	
	public String getXAxisName() {
		return this.xAxisName;
	}
	
	public String getyAxisName() {
		return this.yAxisName;
	}
	
	public ObjectType getObjectType() {
		return this.objectType;
	}
	
	public UserComparisonEntry getUserComparisonEntry() {
		return this.userEntry;
	}
	
	public int getHeight() {
		return this.height;
	}
	
	public int getWidth() {
		return this.width;
	}
	
	public Boolean getCreateLegend() {
		return this.createLegend;
	}
	
	public void setTitle(String title) {
		this.title = title;
	}
	
	public void setXAxisName(String xName) {
		this.xAxisName = xName;
	}
	
	public void setYAxisName(String yName) {
		this.yAxisName = yName;
	}
	
	public void setObjectType(ObjectType objType) {
		this.objectType = objType;
	}
	
	public void setUserComparisonEntry(UserComparisonEntry userEntry) {
		this.userEntry = userEntry;
	}
	
	public void setHeight(int height) {
		this.height = height;
	}
	
	public void setWidth(int width) {
		this.width = width;
	}
	
	public void setCreateLegend(Boolean createLegend) {
		this.createLegend = createLegend;
	}
	
	private DefaultBoxAndWhiskerCategoryDataset createDataset(String characteristicName, double userValue) {
		DefaultBoxAndWhiskerCategoryDataset dataset = new DefaultBoxAndWhiskerCategoryDataset();
		
		if (!characteristicName.isEmpty()) {
			Characteristic characteristic = this.getObjectType().getCharacteristicByName(characteristicName);
			
			if (characteristic != null) {
				BoxAndWhiskerItem item = new BoxAndWhiskerItem(
						characteristic.getAverageValue(),
						characteristic.getMedianValue(), 
						characteristic.getFirstQuartile(), 
						characteristic.getThirdQuartile(),
						characteristic.getMinimumValue(), 
						characteristic.getMaximumValue(), 
						null, null, null);
				
				dataset.add(item, "", "");
			}
		}
		
		return dataset;
	}
	
	private JFreeChart createBoxAndWhiskerChart(String characteristicName, double userValue, Boolean createLegend) {
		JFreeChart chart = ChartFactory.createBoxAndWhiskerChart(
				characteristicName, 
				this.getyAxisName(), 
				this.getXAxisName(), 
				createDataset(characteristicName, userValue), 
				createLegend);
		
		int basicStrokeThickness = 2;
		double renderBarWidthPercent = 0.20;
		String userDataPoint = "User data point: " + userValue;
		CategoryPlot categoryPlot = chart.getCategoryPlot();
		
		ValueMarker valueMarker = new ValueMarker(userValue);
		valueMarker.setStroke(new BasicStroke(basicStrokeThickness));
		valueMarker.setLabel(userDataPoint);
		
		valueMarker.setPaint(Color.BLUE);
		valueMarker.setLabelPaint(Color.BLUE);
		valueMarker.setLabelTextAnchor(TextAnchor.BOTTOM_LEFT);
		valueMarker.setLabelBackgroundColor(Color.LIGHT_GRAY);
		
		categoryPlot.addRangeMarker(valueMarker);
		
		BoxAndWhiskerRenderer renderer = (BoxAndWhiskerRenderer)categoryPlot.getRenderer();
		renderer.setMaximumBarWidth(renderBarWidthPercent);
		
		return chart;
	}
	
	public ChartPanel getChartPanelWithComparisonData(String characteristicName, double userValue, Boolean createLegend) {
		ChartPanel chartPanel = new ChartPanel(createBoxAndWhiskerChart(characteristicName, userValue, createLegend));
		return chartPanel;
	}
}
