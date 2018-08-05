package GenericDataComparison.UI;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.util.function.Consumer;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.CategoryPlot;
import org.jfree.chart.plot.ValueMarker;
import org.jfree.chart.renderer.category.BoxAndWhiskerRenderer;
import org.jfree.data.statistics.BoxAndWhiskerItem;
import org.jfree.data.statistics.DefaultBoxAndWhiskerCategoryDataset;
import org.jfree.ui.TextAnchor;
import GenericDataComparison.Caller;
import GenericDataComparison.Caller.UIType;
import GenericDataComparison.Caller.UIFunction;
import GenericDataComparison.Characteristic;
import GenericDataComparison.ComparisonCharacteristic;
import GenericDataComparison.ObjectType;
import GenericDataComparison.UserComparisonEntry;
import net.miginfocom.layout.LC;
import net.miginfocom.swing.MigLayout;

public class OutputPane extends JPanel 
{
	private static final long serialVersionUID = 1L;
	private JPanel header;
	private JLabel scoreTxt;
	private JScrollPane scrollPane;
	private JPanel outputPanel;
	private Consumer<Caller> listener;
	
	public OutputPane(Consumer<Caller> lstn) 
	{
		listener = lstn;
		
		BoxLayout boxLayout1 = new BoxLayout(this, BoxLayout.Y_AXIS);
		setLayout(boxLayout1);
		
		header = new JPanel();
		BoxLayout boxLayout2 = new BoxLayout(header, BoxLayout.Y_AXIS);
		header.setLayout(boxLayout2);
		
		JLabel title = new JLabel();
		title.setText("Comparison Results");
		title.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 26));
		header.add(title);
		header.add(Box.createRigidArea(new Dimension(0,30)));
		
		scoreTxt = new JLabel();
		scoreTxt.setText(String.format("The score for your thing is 72.4"));
		scoreTxt.setFont(new Font("Arial", Font.PLAIN, 18));
		header.add(scoreTxt);
		
		add(header);
		add(Box.createRigidArea(new Dimension(0,15)));
		
		outputPanel = new JPanel();
		MigLayout ml = new MigLayout("", "[300!]", "[400!]");
		LC lc = new LC();
		lc.gridGapX("20").gridGapY("40").wrapAfter(4);
		ml.setLayoutConstraints(lc);
		outputPanel.setLayout(ml);
		scrollPane = new JScrollPane(outputPanel);
		add(scrollPane);
		add(Box.createRigidArea(new Dimension(0,15)));
		
		JButton backBtn = new JButton("Back");
		backBtn.addActionListener(e->listener.accept(new Caller(UIType.OutputWindow, UIFunction.Back)));
		backBtn.setAlignmentX(CENTER_ALIGNMENT);
		add(backBtn);
		add(Box.createRigidArea(new Dimension(0,5)));
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
		scoreTxt.setText(String.format("The score for your %s is %.2f.", userEntry.getName(), score));
		
		outputPanel.removeAll();
		for(ComparisonCharacteristic cc : userEntry.getComparisonCharacteristics())
		{
			Characteristic item = baseline.getCharacteristicByName(cc.getName());
			addChart(item, cc.getValue());
		}
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
