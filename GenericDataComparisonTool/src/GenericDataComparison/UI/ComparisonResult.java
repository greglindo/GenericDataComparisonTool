package GenericDataComparison.UI;

import GenericDataComparison.*;
import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.Color;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.SwingConstants;

public class ComparisonResult extends JFrame {
	
	private JPanel chartWrapperPanel;
	private GenericComparisonManager manager;
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ComparisonResult frame = new ComparisonResult("cars", "2014 Car");
					frame.setVisible(true);
					frame.setSize(1020, 860);
					
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public ComparisonResult(String objectTypeName, String userEntryName) {
		manager = new GenericComparisonManager();
		
		getContentPane().setBackground(Color.WHITE);
		getContentPane().setLayout(null);
		
		chartWrapperPanel = new JPanel();
		chartWrapperPanel.setBackground(Color.WHITE);
		chartWrapperPanel.setBounds(12, 52, 794, 473);
		renderChart(objectTypeName, userEntryName);
		getContentPane().add(chartWrapperPanel);
		
		JLabel lblTitle = new JLabel("Comparison Results");
		lblTitle.setHorizontalAlignment(SwingConstants.CENTER);
		lblTitle.setFont(new Font("Tahoma", Font.BOLD, 25));
		lblTitle.setBounds(12, 13, 794, 42);
		getContentPane().add(lblTitle);
		
		setTitle("Comparison Result");
		setBackground(Color.WHITE);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 836, 585);
	}
	
	private void renderChart(String objectTypeName, String userEntryName) {
		Boolean createLegend = true;
		
		manager.loadData();
		
		ObjectType objectType = manager.getObjectTypeByName(objectTypeName);
		UserComparisonEntry userEntry = manager.getUserComparisonEntryByName(objectTypeName, userEntryName);
		
		if (objectType != null) {
			for (Characteristic item: objectType.getCharacteristics()) {
				for (ComparisonCharacteristic compareItem: userEntry.getComparisonCharacteristics()) {
					chartWrapperPanel.add(manager.generateBoxAndWhiskerChart(objectType, item.getName(), compareItem.getValue(), createLegend));
				}
			}
		}
		
		
	}

}
