package GenericDataComparison.UI;

import java.awt.Component;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.function.Consumer;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import GenericDataComparison.BetterValue;
import GenericDataComparison.Caller;
import GenericDataComparison.Characteristic;
import GenericDataComparison.ComparisonCharacteristic;
import GenericDataComparison.GenericComparisonManager;
import GenericDataComparison.Main;
import GenericDataComparison.ObjectType;
import GenericDataComparison.UserComparisonEntry;
import GenericDataComparison.Caller.UIFunction;
import GenericDataComparison.Caller.UIType;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import javax.swing.JScrollPane;

public class CompareWithObject extends JPanel
{		  
	private static final long serialVersionUID = 1L;
	private JLabel headerLabel;
	private JLabel promptLabel;
	private JTextField txEntryName;
	private JButton backButton;
	private JButton saveButton;
	private JButton compareButton;
	private JPanel panel;
	private ObjectType _baseObj;
	private JTextField textField;
	private UserComparisonEntry _userEntry;
	private WindowType _windowType;
	private GridBagLayout gbl_panel;
	private final static int LABELPOSITION = 1;
	private final static int TEXTLOCATION = 3;
	private JLabel lblNewLabel;
	private JTextField txBaselineObjectType;
	private Consumer<Caller> listener;
	
	

	/**
	 * @wbp.parser.constructor
	 */
//	public CompareWithObject(Consumer<Caller> consumer, ObjectType BaseObject) 
//	{
//		_windowType = WindowType.CREATE;
//		_baseObj = BaseObject;
//		_userEntry = new UserComparisonEntry();
//		listener = consumer;
//		initialize();
//		
//	}
	
	public CompareWithObject(Consumer<Caller> consumer) 
	{
		
		
		_userEntry = new UserComparisonEntry();
		listener = consumer;
		
	}
	
//	public CompareWithObject(Consumer<Caller> consumer, ObjectType BaseObject, UserComparisonEntry userComparisonEntry) 
//	{
//		_windowType = WindowType.EDIT;
//		_baseObj = BaseObject;
//		_userEntry = userComparisonEntry;
//		listener = consumer;
//		initialize();
//	}
//	
	
	public void Initialize(ObjectType BaseObject)
	{
		
		_baseObj = BaseObject;
		_windowType = WindowType.CREATE;
		//setLayout (new FlowLayout());
		setVisible(true);
		setLayout(null);
		
		
		//setLayout (new FlowLayout());
		headerLabel = new JLabel ("Compare Existing Object", JLabel.CENTER);
		headerLabel.setBounds(160, 37, 379, 45);
		headerLabel.setFont(new Font (Font.SANS_SERIF, Font.PLAIN, 35));
		add (headerLabel);	

		promptLabel = new JLabel ("Please Enter the following Characterstics about your: ");
		promptLabel.setBounds(101, 121, 347, 20);
		promptLabel.setFont(new Font (Font.SANS_SERIF, Font.ITALIC, 15));
		add(promptLabel);
		
		txEntryName = new JTextField (10);
		txEntryName.setBounds(453, 121, 86, 20);
		txEntryName.setHorizontalAlignment(0);
		add (txEntryName);
		
		
		backButton = new JButton ("Back");
		backButton.setLocation(188, 412);
		backButton.setSize(64,23);
		add (backButton);
		backButton.addActionListener(e->
		{
			listener.accept(new Caller(UIType.CompareWithObject, UIFunction.Back));
		});
		
		
		saveButton = new JButton ("Save");
		saveButton.setBounds(255, 412, 64, 23);
		add (saveButton);
		saveButton.addActionListener(e->
		{
			this.saveComparisonData();
		});
		
		compareButton = new JButton ("View Comparison Result");
		compareButton.setBounds(324, 412, 197, 23);
		add (compareButton);
		compareButton.addActionListener(e->
		{
			listener.accept(new Caller(UIType.CompareWithObject, UIFunction.Compare));
		});
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(71, 151, 488, 250);
		add(scrollPane);
		
		panel = new JPanel();
		scrollPane.setViewportView(panel);
		gbl_panel = new GridBagLayout();
		gbl_panel.columnWidths = new int[] {30, 108, 30, 180, 73, 20};
		gbl_panel.rowHeights = new int[] {0, 0, 0, 35, 20};
		gbl_panel.columnWeights = new double[]{0.0, 0.0, 0.0, 0.0, 1.0, Double.MIN_VALUE};
		gbl_panel.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		panel.setLayout(gbl_panel);
		
		lblNewLabel = new JLabel("Baseline Object Type:");
		lblNewLabel.setBounds(170, 96, 139, 14);
		add(lblNewLabel);
		
		txBaselineObjectType = new JTextField();
		txBaselineObjectType.setEditable(false);
		txBaselineObjectType.setBounds(304, 93, 86, 20);
		add(txBaselineObjectType);
		txBaselineObjectType.setColumns(10);
		
		
		//Add in characteristic fields
		this.addChar();
		txBaselineObjectType.setText(_baseObj.getName());
		 
	
		
		this.repaint();
		this.revalidate();
		

	}
	
	private void addChar() 
	{

		int gridy = 0;
		for(Characteristic c : _baseObj.getCharacteristics()) 
		{
			

			
			JLabel d = new JLabel(c.getName());
			d.setName(c.getName());
			d.setToolTipText("Delete");
			GridBagConstraints gbc_lblNewLabel = new GridBagConstraints();
			gbc_lblNewLabel.insets = new Insets(0, 0, 0, 5);
			gbc_lblNewLabel.anchor = GridBagConstraints.EAST;
			gbc_lblNewLabel.gridx = LABELPOSITION;
			gbc_lblNewLabel.gridy = gridy;
			panel.add(d, gbc_lblNewLabel);
			//d.addActionListener(new ObjectTypeListener(c, this));
	
			
			JTextField ed = new JTextField();
			this.bindEntry(ed,c.getName());
			GridBagConstraints gbc_textField = new GridBagConstraints();
			gbc_textField.insets = new Insets(0, 0, 0, 5);
			gbc_textField.fill = GridBagConstraints.HORIZONTAL;
			gbc_textField.gridx = TEXTLOCATION;
			gbc_textField.gridy = gridy;
			panel.add(ed, gbc_textField);
			ed.setColumns(10);
			
			gridy +=1;
	

		}
	}
	
	//If editing then load the user entry for the text field
	private void bindEntry(JTextField jtext, String EntryName)
	{
		ComparisonCharacteristic entryChar = new ComparisonCharacteristic();
		if(_windowType == WindowType.CREATE) return;
		entryChar = _userEntry.getComparisonCharacteristicByName(EntryName);
		jtext.setText(entryChar.getValue()+"");
		txEntryName.setText(_userEntry.getName());

		
	}
	
	private boolean validateFields() {
		for(Component c: panel.getComponents()) 
		{
			if(c instanceof JTextField && ((JTextField) c).getText().equals(""))
			{
				JOptionPane.showMessageDialog(null, "All fields must be completed","Error",JOptionPane.WARNING_MESSAGE);
				return false;
			}
			
		}
		return true;

	}
	
	
	
	private void saveComparisonData()
	{
		//Check to ensure all fields have been filled
		if(this.validateFields() == false)  return;
		
		
		//Iterate through all fields to get the name and value of each ComparisonCharacteristic
		//Labels are the name and textfields are the value of the characteristic
		boolean addField = false;
		GridBagLayout layout = gbl_panel;
		String field = "";
		double value = 0;
		for (Component comp : panel.getComponents()) {
			addField = false;
			GridBagConstraints gbc = layout.getConstraints(comp);
			
				
			   
			    if (gbc.gridx == LABELPOSITION  && comp instanceof JLabel) 
			    {
			    	field =(comp.getName());
			    	addField = false;
			    	
			    }else if(gbc.gridx == TEXTLOCATION && comp instanceof JTextField) {
			    	value = (Double.parseDouble(((JTextField) comp).getText()));
			    	addField = true;			    
			    
			    }
			    if(addField) _userEntry.addComparisonCharacteristic(new ComparisonCharacteristic(field,value));
		}
		
		_userEntry.setName(txEntryName.getText());
		_userEntry.setObjectTypeName(_baseObj.getName());
		listener.accept(new Caller(UIType.CompareWithObject, UIFunction.Save));
	

	}
	
	public UserComparisonEntry getUserEntry()
	{
		return _userEntry;
	}
	
	


		

}
