package GenericDataComparison.UI;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import java.awt.Font;
import java.awt.GridLayout;
import java.util.ArrayList;

import javax.swing.SwingConstants;
import java.awt.Color;

import javax.swing.ImageIcon;
import javax.swing.JButton;

public class ManageObjectTypes extends JFrame {

	private JPanel contentPane;
	private JPanel objectTypesPanel;
	private JButton btnObjectType;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ManageObjectTypes frame = new ManageObjectTypes();
					frame.setSize(750, 500);
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public ManageObjectTypes() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 713, 500);
		contentPane = new JPanel();
		contentPane.setBackground(Color.WHITE);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(null);
		setContentPane(contentPane);
		
		JLabel lblTitle = new JLabel("Edit or Compare Existing Objects");
		lblTitle.setHorizontalAlignment(SwingConstants.CENTER);
		lblTitle.setFont(new Font("Tahoma", Font.BOLD, 25));
		lblTitle.setBounds(0, 13, 688, 38);
		contentPane.add(lblTitle);
		
		JLabel lblInstruction = new JLabel("Select an object for comparison or click on an icon to edit");
		lblInstruction.setHorizontalAlignment(SwingConstants.CENTER);
		lblInstruction.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblInstruction.setBounds(10, 64, 678, 26);
		contentPane.add(lblInstruction);
		
		objectTypesPanel = new JPanel();
		objectTypesPanel.setLayout(new GridLayout(getObjectTypes().size(), 1, 1, 10));
		objectTypesPanel.setBackground(Color.WHITE);
		objectTypesPanel.setBounds(200, 103, 350, 150);
		try {
			loadObjectTypes();
		} catch (Exception e) {
			e.printStackTrace();
		}
		contentPane.add(objectTypesPanel);
		
		JButton btnBack = new JButton("Back");
		btnBack.setBackground(Color.WHITE);
		btnBack.setBounds(283, 397, 97, 25);
		contentPane.add(btnBack);
	}
	
	private ArrayList<String> getObjectTypes(){
		ArrayList<String> objectTypes = new ArrayList<String>();
		
		objectTypes.add("Cars");
		objectTypes.add("Cellphone");
		objectTypes.add("Desktop");
		objectTypes.add("Laptop");
		objectTypes.add("Motorcycle");
		
		return objectTypes;
	}
	
	private void loadObjectTypes() {
		ArrayList<String> objectTypes = getObjectTypes();
		
		String imagePath = "C:\\Users\\Shaunavon Blackmore\\git\\GenericDataComparisonTool\\dev_shaunavon\\GenericDataComparisonTool\\GenericDataComparisonTool\\images\\";
		
		if (objectTypes != null) {
			for (String item: objectTypes) {
				
				JLabel imgEditLabel = new JLabel(new ImageIcon(imagePath + "Modify.png"));
				imgEditLabel.setName("imgEdit" + item);
				imgEditLabel.setBackground(Color.WHITE);
				imgEditLabel.setToolTipText("Edit " + item);
				objectTypesPanel.add(imgEditLabel);
				
				JLabel imgDeleteLabel = new JLabel(new ImageIcon(imagePath + "Delete.png"));
				imgDeleteLabel.setName("imgDelete" + item);
				imgDeleteLabel.setBackground(Color.WHITE);
				imgDeleteLabel.setToolTipText("Delete " + item);
				objectTypesPanel.add(imgDeleteLabel);
				
				btnObjectType = new JButton(item);
				btnObjectType.setName("btn" + item);
				btnObjectType.setBackground(Color.WHITE);
				btnObjectType.setForeground(new Color(0, 0, 0));
				btnObjectType.setBounds(283, 397, 100, 50);
				objectTypesPanel.add(btnObjectType);
				
			}
		}
	}
}
