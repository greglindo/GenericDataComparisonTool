package GenericDataComparison.UI;

import javax.swing.*;
import GenericDataComparison.Characteristic;
import GenericDataComparison.GenericComparisonManager;
import GenericDataComparison.Main;
import GenericDataComparison.ObjectType;
import java.awt.*;
import java.util.ArrayList;
import java.util.UUID;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class BaselineObjectWindow extends JPanel
{
	private static final long serialVersionUID = 1L;
    private JLabel _header;
    private ObjectType _baseObj;
    private WindowType _windowType;
    private JButton btnAdd;
	private JPanel _subPanel;
	private JTextField txBaselineObjectName;
	private JButton btnSave;
	private JButton btnBack;
	private JScrollPane scrollPane_1;
	private JButton btnDelete;
	private GenericComparisonManager _manager;
	private JButton btnDeleteBaselineObject;

	/**
	 * @wbp.parser.constructor
	 */
	private BaselineObjectWindow() {
		_manager = new GenericComparisonManager();
    	_baseObj = new ObjectType();
        _windowType = WindowType.CREATE;
        initialize();
        //this.addCharPanel();
		
	}
	//Create a new object
    public BaselineObjectWindow(GenericComparisonManager Manager)
    {
    	
    	_manager = Manager;
    	_baseObj = new ObjectType();
        _windowType = WindowType.CREATE;
        initialize();
        //this.addCharPanel();
       
    }


    //Edit existing object
    public BaselineObjectWindow(GenericComparisonManager Manager, ObjectType BaselineObject) throws HeadlessException {
		super();
		_manager = Manager;
		this._windowType = WindowType.EDIT;
		this.initialize();
		//this.bind();
		//this.addCharPanel(_baseObj);
		
	}
  //Edit existing object
    public BaselineObjectWindow(GenericComparisonManager Manager, String BaselineObjectName) throws HeadlessException {
		super();
		_manager = Manager;
		this._windowType = WindowType.EDIT;
		this._baseObj = _manager.getObjectTypeByName(BaselineObjectName);
		this.initialize();
		//this.bind();
		//this.addCharPanel(_baseObj);
	}
    
    
    private void bind() {
    	this.txBaselineObjectName.setText(_baseObj.getName());
    }

    private void initialize()
    {

        // set up the main frame of the application
    	
    	
        this.setBounds(100, 100, 1242, 758);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
    	this.setLocation(dim.width/2-this.getSize().width/2, dim.height/2-this.getSize().height/2);


        // create the panel that will hold our charts
        _panel = new JPanel();
        

        

        this.getContentPane().add(_panel);
        
        _panel.setVisible(true);
        
        scrollPane_1 = new JScrollPane();
        scrollPane_1.setBounds(10, 144, 1172, 484);
        add(scrollPane_1);
        
        _subPanel = new JPanel();
        scrollPane_1.setViewportView(_subPanel);
        GridLayout gl = new GridLayout(0, 1, 0, 0);
        _subPanel.setLayout(gl);

        //Mouse click event
        btnAdd = new JButton("Add");
        btnAdd.addActionListener(e ->{
        		addCharPanel();

        	});

        btnAdd.setBounds(21, 76, 89, 23);
        _panel.add(btnAdd);
        
        JLabel lblAddNewCharacteristic = new JLabel("Add New Characteristic ");
        lblAddNewCharacteristic.setBounds(122, 80, 190, 14);
        _panel.add(lblAddNewCharacteristic);
        
        JLabel lblBaselineObjectName = new JLabel("Name of New Baseline Object");
        lblBaselineObjectName.setBounds(411, 72, 172, 20);
        _panel.add(lblBaselineObjectName);
        
        txBaselineObjectName = new JTextField();
        txBaselineObjectName.setBounds(615, 72, 156, 20);
        _panel.add(txBaselineObjectName);
        txBaselineObjectName.setColumns(10);
        
        
        //Save Button
        btnSave = new JButton("Save");
        btnSave.addActionListener(e ->{
        	this.saveObject();
        });
        btnSave.setBounds(619, 656, 89, 23);
        _panel.add(btnSave);
        
        
        //Back Button
        btnBack = new JButton("Back");
        btnBack.addActionListener(e ->{
        	this.closeWindow();
        });
        btnBack.setBounds(492, 656, 89, 23);
        _panel.add(btnBack);
        
        btnDeleteBaselineObject = new JButton("Delete Baseline Object");
        btnDeleteBaselineObject.addActionListener(e-> {
        	this.deleteBaselineObject();
        });
        btnDeleteBaselineObject.setBounds(297, 656, 172, 23);
        _panel.add(btnDeleteBaselineObject);
        btnDeleteBaselineObject.setVisible(false);
        
        _panel.add(addHeader());
        
        btnDelete = new JButton("Delete");
        btnDelete.setBounds(21, 110, 89, 23);
        _panel.add(btnDelete);
        
        JLabel lblDeleteMarkedCharacteristic = new JLabel("Delete Marked Characteristic");
        lblDeleteMarkedCharacteristic.setBounds(122, 114, 190, 14);
        _panel.add(lblDeleteMarkedCharacteristic);
        
        btnDelete.addActionListener(e->{
        	deletePanel();
        });
        
        if(this._windowType == WindowType.EDIT){
        	this.bind();
    		this.addCharPanel(_baseObj);
        }else {
        	this.addCharPanel();
        	
        }
        this.repaint();
        this.validate();
        
        //this.pack();
    }
    
	private void deletePanel() {
		for(Component c: _subPanel.getComponents())
			//if(c instanceof CharacteristicPanel && c.isVisible() == false) {
			if(((CharacteristicPanel) c).DeleteFlag()) {
				_subPanel.remove(c);
				this.repaint();
				this.validate();
			}
		
	}

    //Adds header and specifies if the user is adding a new baseline object or modifying
    private JLabel addHeader()
    {
        String headerText = "Add New Baseline Object";
        if(_windowType == WindowType.EDIT){
            headerText= "Edit Baseline Object";
            btnDeleteBaselineObject.setVisible(true);
        }
        setLayout(null);
        _header = new JLabel(headerText);
        _header.setBounds(441, 22, 309, 39);
        _header.setHorizontalAlignment(JLabel.CENTER);
        _header.setFont(new Font("Serif", Font.PLAIN, 30));
        
        return _header;
    }    
    
	private void addCharPanel() 
	{
		_subPanel.add(new CharacteristicPanel(), "span");
		_subPanel.repaint();
		_subPanel.revalidate();
  }
	
	private void addCharPanel(ObjectType BaselineObject)
	{
		for(Characteristic _char: BaselineObject.getCharacteristics()) 
		{
			_subPanel.add(new CharacteristicPanel(_char), "span");
			_subPanel.repaint();
			_subPanel.revalidate();
    }
	}
	
	
	//Call delete object to clear form and clear out object
    private void deleteBaselineObject() {
		int dialogResult = JOptionPane.showConfirmDialog (null, "Are you sure you want to delete this Object?","Warning!",2);
		if(dialogResult == JOptionPane.OK_OPTION){
			this.deleteObject();
			this.clearForm();
			
		}
		
		
	}
    
    
    //Clear Form all all information
    private void clearForm() {
    	_baseObj = new ObjectType();
    	String baseObjName = "";
		for(Component c: _subPanel.getComponents()) {
			_subPanel.remove(c);
		}
		_baseObj.setName(baseObjName);
		
		this.txBaselineObjectName.setText(baseObjName);
		this.repaint();
		this.validate();
    	
    }
    
    //Delete object
    private void deleteObject() {
		_baseObj.deleteCharacteristics();
		_manager.deleteObjectTypeByName(_baseObj.getName());
		_manager.saveData();
		
		
    	
    }

	//Close form
	private void closeWindow() {
		setVisible(false);
		dispose();
	}
	

	
	//Save object to JSON file
	private void saveObject() {

		if(this.checkPanelsAreValid() == false  || this.validateScore() == false ) {
			return;
		}
		
		if(_manager.getObjectTypeByName(_baseObj.getName()) != null) {
			this.deleteObject();
		}
			

		try {

			// Clear out characteristics
			if (_windowType == WindowType.EDIT || _baseObj.getCharacteristics() != null) {
				_baseObj.deleteCharacteristics();
			}
			// Get Base Object Name
			if (this.txBaselineObjectName.getText().equals("")) {
				JOptionPane.showMessageDialog(null,"Baseline Object must have a name","Error",JOptionPane.WARNING_MESSAGE);
				//throw new Exception("Baseline Object must have a name");
			}
			_baseObj.setName(this.txBaselineObjectName.getText());


			
			// Get all characteristics from panels
			for (Component c : _subPanel.getComponents()) {
				if (c instanceof CharacteristicPanel) {
					_baseObj.addCharacteristic(((CharacteristicPanel) c).getCharacteristic());
				}

			}

			_manager.addObjectType(_baseObj);
			_manager.saveData();
			this.clearForm();

			JOptionPane.showMessageDialog(null, "Your new baseline model has been saved.", "Success!", 1);
			
			this.closeWindow();

		} catch (Exception e) {
			// TODO error handling
		}

	}
	
	//Validate that the score adds up to 100
	private boolean validateScore() {
		double score = 0;
		double maxScore = 100;
		try {
			for(Component c: _subPanel.getComponents())
				//if(c instanceof CharacteristicPanel && c.isVisible() == false) {
				if(c instanceof CharacteristicPanel) {
					score += ((CharacteristicPanel) c).getScoreWeight();
				}
			if(Math.abs(maxScore) != Math.abs(score)) {
				JOptionPane.showMessageDialog(null,"Score must add up to 100", "Error",JOptionPane.WARNING_MESSAGE);
				//throw new Exception("Score must add up to 100");
				
			}
			return true;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			//e.printStackTrace();
		}
		return false;
	}
	
	//Check that all panels have been completely filled out
	private boolean checkPanelsAreValid() {
		for(Component c: _subPanel.getComponents())
			//if(c instanceof CharacteristicPanel && c.isVisible() == false) {
			if(c instanceof CharacteristicPanel) {
				if(((CharacteristicPanel) c).PanelIsValid() == false){
					return false;
				}
			}
		
		return true;
	}
}





