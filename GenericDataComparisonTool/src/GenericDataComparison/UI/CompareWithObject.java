package GenericDataComparison.UI;

import java.awt.Color;
import java.awt.Component;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.function.Consumer;

import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.border.MatteBorder;

import GenericDataComparison.Caller;
import GenericDataComparison.Caller.UIFunction;
import GenericDataComparison.Caller.UIType;
import GenericDataComparison.Characteristic;
import GenericDataComparison.ComparisonCharacteristic;
import GenericDataComparison.ObjectType;
import GenericDataComparison.UserComparisonEntry;
import javax.swing.SwingConstants;

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
	private UserComparisonEntry _userEntry;
	private WindowType _windowType;
	private GridBagLayout gbl_panel;
	private final static int LABELPOSITION = 1;
	private final static int TEXTLOCATION = 3;
	private final static int MINMAXLOCATION = 4;
	private JLabel lblNewLabel;
	private JTextField txBaselineObjectType;
	private Consumer<Caller> listener;
	private ArrayList<UserComparisonEntry> _userComparisonEntries;
	private JList<String> lstUserEntries;
	private DefaultListModel<String> listModel;
	private JLabel lblNewLabel_1;
	private JLabel lblCharacteristic;
	private JLabel lblMinmax;
	
	/**
	 * @wbp.parser.constructor
	 */
	private CompareWithObject() 
	{
		_userEntry = new UserComparisonEntry();
		init();		
	}
	
	public CompareWithObject(Consumer<Caller> consumer) 
	{
		listener = consumer;
		_userEntry = new UserComparisonEntry();		
	}
	
	
	public void Initialize(ObjectType BaseObject)
	{
		_windowType = WindowType.CREATE;
		_baseObj = BaseObject;
		_userEntry = new UserComparisonEntry();
		init();
	}
	
	public void Initialize(ObjectType BaseObject, ArrayList<UserComparisonEntry> userComparisonEntries)
	{		
		_userComparisonEntries = userComparisonEntries;
		_baseObj = BaseObject;
		_windowType = WindowType.CREATE;
		_userEntry = new UserComparisonEntry();
		init();
	}
	
	public void UpdateUserEntries(ArrayList<UserComparisonEntry> UserComparisonEntries)
	{
		_userComparisonEntries = UserComparisonEntries;
		init();
	}
	
	private void init()
	{
		removeAll();		
		setLayout(null);
		
		headerLabel = new JLabel ("Compare Existing Object", JLabel.CENTER);
		headerLabel.setBounds(165, 5, 379, 50);
		headerLabel.setFont(new Font (Font.SANS_SERIF, Font.PLAIN, 30));
		add (headerLabel);	

		promptLabel = new JLabel ("Enter the characterstics for your: ");
		promptLabel.setBounds(210, 72, 347, 22);
		promptLabel.setFont(new Font (Font.SANS_SERIF, Font.ITALIC, 15));
		add(promptLabel);
		
		txEntryName = new JTextField (10);
		txEntryName.setBounds(430, 72, 95, 20);
		//txEntryName.setHorizontalAlignment(0);
		add (txEntryName);		
		
		backButton = new JButton ("Back");
		backButton.setLocation(155, 458);
		backButton.setSize(64,23);
		add (backButton);
		backButton.addActionListener(e->
		{
			listener.accept(new Caller(UIType.CompareWithObject, UIFunction.Back));
		});		
		
		saveButton = new JButton ("Save");
		saveButton.setBounds(229, 458, 64, 23);
		add (saveButton);
		saveButton.addActionListener(e->
		{
			listener.accept(new Caller(UIType.CompareWithObject, UIFunction.Save));
		});
		
		compareButton = new JButton ("View Comparison Result");
		compareButton.setBounds(303, 458, 197, 23);
		add (compareButton);
		compareButton.addActionListener(e->
		{
			listener.accept(new Caller(UIType.CompareWithObject, UIFunction.Compare));
		});
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(145, 97, 451, 350);
		add(scrollPane);
		
		panel = new JPanel();
		panel.setAutoscrolls(true);
		scrollPane.setViewportView(panel);
		gbl_panel = new GridBagLayout();
		gbl_panel.columnWidths = new int[] {24, 108, 30, 180, 73, 20};
		gbl_panel.rowHeights = new int[] {0, 12, 12, 12, 12, 12, 12, 12, 12, 12, 12};
		gbl_panel.columnWeights = new double[]{0.0, 0.0, 0.0, 0.0, 1.0, Double.MIN_VALUE};
		gbl_panel.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0};
		panel.setLayout(gbl_panel);
		
		lblCharacteristic = new JLabel("Characteristic");
		GridBagConstraints gbc_lblCharacteristic = new GridBagConstraints();
		gbc_lblCharacteristic.anchor = GridBagConstraints.EAST;
		gbc_lblCharacteristic.insets = new Insets(0, 0, 5, 5);
		gbc_lblCharacteristic.gridx = 1;
		gbc_lblCharacteristic.gridy = 0;
		panel.add(lblCharacteristic, gbc_lblCharacteristic);
		
		lblNewLabel_1 = new JLabel("Value");
		lblNewLabel_1.setHorizontalAlignment(SwingConstants.CENTER);
		GridBagConstraints gbc_lblNewLabel_1 = new GridBagConstraints();
		gbc_lblNewLabel_1.insets = new Insets(0, 0, 5, 5);
		gbc_lblNewLabel_1.gridx = 3;
		gbc_lblNewLabel_1.gridy = 0;
		panel.add(lblNewLabel_1, gbc_lblNewLabel_1);
		
		lblMinmax = new JLabel("Min/Max");
		GridBagConstraints gbc_lblMinmax = new GridBagConstraints();
		gbc_lblMinmax.insets = new Insets(0, 0, 5, 0);
		gbc_lblMinmax.gridx = 4;
		gbc_lblMinmax.gridy = 0;
		panel.add(lblMinmax, gbc_lblMinmax);
		
		lblNewLabel = new JLabel("Baseline Object Type:");
		lblNewLabel.setBounds(217, 55, 139, 14);
		add(lblNewLabel);
		
		txBaselineObjectType = new JTextField();
		txBaselineObjectType.setBorder(null);
		txBaselineObjectType.setEditable(false);
		txBaselineObjectType.setBounds(354, 52, 86, 20);
		add(txBaselineObjectType);
		txBaselineObjectType.setColumns(10);		
		
		//Add in characteristic fields
		this.addChar();
		txBaselineObjectType.setText(_baseObj.getName());
						
		JButton btnClear = new JButton("Clear");
		btnClear.addActionListener(e->
		{
			this.clearForm();
		});
		btnClear.setBounds(532, 458, 64, 23);
		add(btnClear);
		
		listModel = new DefaultListModel<String>();
		lstUserEntries = new JList<String>(listModel);
		lstUserEntries.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent evt) {
		        	addEntrySelection(evt);
			}
		});
		lstUserEntries.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		lstUserEntries.setBorder(new MatteBorder(1, 1, 1, 1, (Color) new Color(0, 0, 0)));
		lstUserEntries.setBounds(30, 131, 105, 316);
		add(lstUserEntries);
		
		JButton btnDeleteEntry = new JButton("Delete Entry");
		btnDeleteEntry.addActionListener(e->{
			int dialogResult = JOptionPane.showConfirmDialog (null, "Are you sure you want to delete this User Entry?","Warning!",2);
			if(dialogResult == JOptionPane.OK_OPTION){
				listener.accept(new Caller(UIType.CompareWithObject, UIFunction.Delete));
				this.clearForm();			
			}
			
		});
		btnDeleteEntry.setBounds(30, 97, 105, 23);
		add(btnDeleteEntry);
		
		_windowType = WindowType.EDIT;
		bindList();
		this.repaint();
		this.revalidate();
	}
	
	//Insert all 
	private void bindList()
	{
		this.listModel.clear();
		if (this._userComparisonEntries.isEmpty()) return;
		for(UserComparisonEntry e:_userComparisonEntries) 
		{
			this.listModel.addElement(e.getName());
		}
	}
	
	private void addEntrySelection(MouseEvent e) 
	{
		_userEntry = _userComparisonEntries.get(this.lstUserEntries.getSelectedIndex());
		_windowType = WindowType.EDIT;
		init();
	}

	private void clearForm() 
	{
		_userEntry = new UserComparisonEntry();
		init();	
	}

	private void addChar() 
	{
		int gridy = 1;
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
			
			JTextField ed = new JTextField();
			this.bindEntry(ed,c.getName());
			ed.setName(c.getName());
			GridBagConstraints gbc_textField = new GridBagConstraints();
			gbc_textField.insets = new Insets(0, 0, 0, 5);
			gbc_textField.fill = GridBagConstraints.HORIZONTAL;
			gbc_textField.gridx = TEXTLOCATION;
			gbc_textField.gridy = gridy;
			panel.add(ed, gbc_textField);
			ed.setColumns(10);
			
			
			double minValue = _baseObj.getCharacteristicByName(c.getName()).getMinimumValue();
			double maxValue = _baseObj.getCharacteristicByName(c.getName()).getMaximumValue();
			String minmax = Double.toString(minValue) + "/" + Double.toString(maxValue);
			JLabel lblMinMax = new JLabel(minmax);
			d.setName(c.getName());
			d.setToolTipText("Delete");
			GridBagConstraints gbc_lblMinMax = new GridBagConstraints();
			gbc_lblMinMax.insets = new Insets(0, 0, 0, 5);
			gbc_lblMinMax.anchor = GridBagConstraints.CENTER;
			gbc_lblMinMax.gridx = MINMAXLOCATION;
			gbc_lblMinMax.gridy = gridy;
			panel.add(lblMinMax, gbc_lblMinMax);
			
			gridy +=1;
		}
	}
	
	//If editing then load the user entry for the text field
	private void bindEntry(JTextField jtext, String EntryName)
	{
		ComparisonCharacteristic entryChar = new ComparisonCharacteristic();
		if(_windowType == WindowType.CREATE) return;
		//if(_userEntry == null) return;
		entryChar = _userEntry.getComparisonCharacteristicByName(EntryName);
		String value = (entryChar != null) ? entryChar.getValue()+"" :"";
		jtext.setText(value);
		txEntryName.setText(_userEntry.getName());	
	}
	
	private boolean validateFields() {
		
		for(Component c: panel.getComponents()) 
		{
			if(c instanceof JTextField)
			{
			double minValue = Math.abs(_baseObj.getCharacteristicByName(c.getName()).getMinimumValue());
			double maxValue = Math.abs(_baseObj.getCharacteristicByName(c.getName()).getMaximumValue());
			String entryValue = ((JTextField)c).getText();

				if(entryValue.equals(""))
				{
					JOptionPane.showMessageDialog(null, "All fields must be completed","Error",JOptionPane.WARNING_MESSAGE);
					return false;
				}
				else if(Math.abs(Double.parseDouble(entryValue)) > maxValue || Math.abs(Double.parseDouble(entryValue)) < minValue)
				{
					JOptionPane.showMessageDialog(null, "Characteristic '" + c.getName() + "' is outside of the min/max values","Error",JOptionPane.WARNING_MESSAGE);
					return false;
				}
			}
			
		}
		return true;
	}	
	

	
	private void saveComparisonData()
	{
		//Check to ensure all fields have been filled
		if(this.validateFields() == false)  return;
		_userEntry.deleteComparisonCharcteristics();
		
		//Iterate through all fields to get the name and value of each ComparisonCharacteristic
		//Labels are the name and text fields are the value of the characteristic
		boolean addField = false;
		GridBagLayout layout = gbl_panel;
		String field = "";
		double value = 0;
		for (Component comp : panel.getComponents()) 
		{
			addField = false;
			GridBagConstraints gbc = layout.getConstraints(comp);			

		    if (gbc.gridx == LABELPOSITION  && comp instanceof JLabel  && gbc.gridy != 0) 
		    {
		    	field =(comp.getName());
		    	addField = false;
		    	
		    }
		    else if(gbc.gridx == TEXTLOCATION && comp instanceof JTextField) 
		    {
		    	value = (Double.parseDouble(((JTextField) comp).getText()));
		    	addField = true;			    
		    
		    }
		    
		    if(addField) 
		    {
		    	_userEntry.addComparisonCharacteristic(new ComparisonCharacteristic(field,value));
		    }
		}
		
		_userEntry.setName(txEntryName.getText());
		_userEntry.setObjectTypeName(_baseObj.getName());
	}
	
	public UserComparisonEntry getUserEntry()
	{
		if(this.validateFields() == false)  return null;
		saveComparisonData();
		return _userEntry;
	}
}
