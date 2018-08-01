package GenericDataComparison.UI;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Font;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextPane;
import javax.swing.text.SimpleAttributeSet;
import javax.swing.text.StyleConstants;
import javax.swing.text.StyledDocument;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.CategoryPlot;
import org.jfree.chart.plot.ValueMarker;
import org.jfree.chart.renderer.category.BoxAndWhiskerRenderer;
import org.jfree.data.statistics.BoxAndWhiskerItem;
import org.jfree.data.statistics.DefaultBoxAndWhiskerCategoryDataset;
import org.jfree.ui.TextAnchor;
import GenericDataComparison.Characteristic;
import GenericDataComparison.ComparisonCharacteristic;
import GenericDataComparison.Main;
import GenericDataComparison.ObjectType;
import GenericDataComparison.UserComparisonEntry;
import net.miginfocom.layout.LC;
import net.miginfocom.swing.MigLayout;

public class OutputPane extends JScrollPane 
{
	private static final long serialVersionUID = 1L;
	private JPanel outputPanel;
	
	public OutputPane(Main mainWin) 
	{
		outputPanel = new JPanel();
		MigLayout ml = new MigLayout("", "[300!]", "[400!]");
		LC lc = new LC();
		lc.gridGapX("20").gridGapY("40").wrapAfter(4);
		ml.setLayoutConstraints(lc);
		outputPanel.setLayout(ml);		
		add(outputPanel);
		setVisible(false);
	}
	
	public void Reset()
	{
		outputPanel.removeAll();
	}
	
	public void generateOutput(UserComparisonEntry userEntry, ObjectType baseline)
	{
		double score = 0.0;
		for(ComparisonCharacteristic cc : userEntry.getComparisonCharacteristics())
		{
			Characteristic item = baseline.getCharacteristicByName(cc.getName());
			score += item.calculateScore(cc.getValue());
		}		
		addScore(userEntry.getName(), score);		
		for(ComparisonCharacteristic cc : userEntry.getComparisonCharacteristics())
		{
			Characteristic item = baseline.getCharacteristicByName(cc.getName());
			addChart(item, cc.getValue());
		}
	}
	
	private void addScore(String name, double val)
	{
		JTextPane scorePane = new JTextPane();
		scorePane.setEditable(false);
		scorePane.setFont(new Font("Arial", Font.PLAIN, 20));
		scorePane.setText(String.format("The score for your %s is %.2f.", name, val));
		StyledDocument doc = scorePane.getStyledDocument();
		SimpleAttributeSet center = new SimpleAttributeSet();
		StyleConstants.setAlignment(center, StyleConstants.ALIGN_CENTER);
		doc.setParagraphAttributes(0, doc.getLength(), center, false);
		outputPanel.add(scorePane, "span 4");
	}
	
	private void addChart(Characteristic item, double userVal)
	{
		DefaultBoxAndWhiskerCategoryDataset ds = new DefaultBoxAndWhiskerCategoryDataset();
		ds.add(new BoxAndWhiskerItem(item.getAverageValue(), item.getMedianValue(), item.getFirstQuartile(), item.getThirdQuartile(), 
				item.getMinimumValue(), item.getMaximumValue(), null, null, null), "", "");
		JFreeChart chart = ChartFactory.createBoxAndWhiskerChart(item.getName(), null, null, ds, false);
		CategoryPlot plot = chart.getCategoryPlot();
		ValueMarker mrk = new ValueMarker(userVal);
		mrk.setStroke(new BasicStroke(2));
		mrk.setPaint(Color.BLUE);
		mrk.setLabel("User: " + userVal);
		mrk.setLabelPaint(Color.BLUE);
		mrk.setLabelTextAnchor(TextAnchor.BOTTOM_LEFT);
		mrk.setLabelBackgroundColor(Color.LIGHT_GRAY);
		plot.addRangeMarker(mrk);
		BoxAndWhiskerRenderer ren = (BoxAndWhiskerRenderer)plot.getRenderer();
		ren.setMaximumBarWidth(0.20);
		ChartPanel chartPanel = new ChartPanel(chart);
		outputPanel.add(chartPanel);
	}
}
