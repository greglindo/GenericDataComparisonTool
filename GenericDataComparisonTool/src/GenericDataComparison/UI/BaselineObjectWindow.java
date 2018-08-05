package GenericDataComparison.UI;

import java.awt.Component;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.HeadlessException;
import java.util.function.Consumer;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;

import GenericDataComparison.Caller;
import GenericDataComparison.Caller.UIType;
import GenericDataComparison.Caller.UIFunction;
import GenericDataComparison.Characteristic;
import GenericDataComparison.ObjectType;

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
	private JScrollPane _scrollPane;
	private JButton btnDelete;
	private JButton btnDeleteBaselineObject;
	private Consumer<Caller> listener;

	/**
	 * @wbp.parser.constructor
	 */
	private BaselineObjectWindow() {
		super();
    	_baseObj = new ObjectType();
        _windowType = WindowType.CREATE;
        initialize();
        //this.addCharPanel();
		
	}
  
	//Create a new object
    public BaselineObjectWindow(Consumer<Caller> lstn)
    {
    	super();
        listener = lstn;
        initialize();       
    }    

	private void bind() {
    	this.txBaselineObjectName.setText(_baseObj.getName());
    }

    private void initialize()
    {

        // set up the main frame of the application
    	
    	
        this.setBounds(100, 100, 1242, 758);

        
        _scrollPane = new JScrollPane();
        _scrollPane.setBounds(10, 144, 1172, 484);
        add(_scrollPane);
        
        _subPanel = new JPanel();
        _scrollPane.setViewportView(_subPanel);
        GridLayout gl = new GridLayout(0, 1, 0, 0);
        _subPanel.setLayout(gl);

        //Mouse click event
        btnAdd = new JButton("Add");
        btnAdd.addActionListener(e ->{
        		addCharPanel();

        	});

        btnAdd.setBounds(21, 76, 89, 23);
        this.add(btnAdd);
        
        JLabel lblAddNewCharacteristic = new JLabel("Add New Characteristic ");
        lblAddNewCharacteristic.setBounds(122, 80, 190, 14);
        this.add(lblAddNewCharacteristic);
        
        JLabel lblBaselineObjectName = new JLabel("Name of New Baseline Object");
        lblBaselineObjectName.setBounds(411, 72, 172, 20);
        this.add(lblBaselineObjectName);
        
        txBaselineObjectName = new JTextField();
        txBaselineObjectName.setBounds(615, 72, 156, 20);
        this.add(txBaselineObjectName);
        txBaselineObjectName.setColumns(10);
        
        
        //Save Button
        btnSave = new JButton("Save");
        btnSave.addActionListener(e ->{
        	this.saveObject();
        });
        btnSave.setBounds(619, 656, 89, 23);
        this.add(btnSave);
        
        //Back Button
        btnBack = new JButton("Back");
        btnBack.addActionListener(e ->{
        	listener.accept(new Caller(UIType.BaselineObjectWindow, UIFunction.Back));
        });
        btnBack.setBounds(492, 656, 89, 23);
        this.add(btnBack);
        add(btnBack);
        
        btnDeleteBaselineObject = new JButton("Delete Baseline Object");
        btnDeleteBaselineObject.addActionListener(e-> {
        	this.deleteBaselineObject();
        });
        btnDeleteBaselineObject.setBounds(297, 656, 172, 23);
        this.add(btnDeleteBaselineObject);
        btnDeleteBaselineObject.setVisible(false);
        
        setLayout(null);
        _header = new JLabel();
        _header.setBounds(441, 22, 309, 39);
        _header.setHorizontalAlignment(JLabel.CENTER);
        _header.setFont(new Font("Serif", Font.PLAIN, 30));
        this.add(_header);
        
        btnDelete = new JButton("Delete");
        btnDelete.setBounds(21, 110, 89, 23);
        this.add(btnDelete);
        
        JLabel lblDeleteMarkedCharacteristic = new JLabel("Delete Marked Characteristic");
        lblDeleteMarkedCharacteristic.setBounds(122, 114, 190, 14);
        this.add(lblDeleteMarkedCharacteristic);
        
        btnDelete.addActionListener(e->{
        	deletePanel();
        });
        
        this.repaint();
        this.validate();
        
        //this.pack();
    }
    
	private void deletePanel() {
		for(Component c: _subPanel.getComponents())
			if(((CharacteristicPanel) c).DeleteFlag()) {
				_subPanel.remove(c);
				this.repaint();
				this.validate();
			}
		
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
			listener.accept(new Caller(UIType.BaselineObjectWindow, UIFunction.Delete));			
		}
		
		
	}
    
    //Clear Form all all information
    public void clearForm() {
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
	
    public ObjectType getObject()
    {
    	return _baseObj;
    }
    
    public void setObject(ObjectType objType) 
    {
    	if(objType == null) 
    	{
    		_baseObj = new ObjectType();
    		addCharPanel();
            _windowType = WindowType.CREATE;
            _header.setText("Add New Baseline Object");
	    }
    	else
    	{
    		_baseObj = objType;
	    	addCharPanel(_baseObj);
	    	_windowType = WindowType.EDIT;
	    	_header.setText("Edit Baseline Object");
	    	bind();
    	}
    }
	
	//Save object to JSON file
	private void saveObject() {

		if(this.checkPanelsAreValid() == false  || this.validateScore() == false ) {
			return;
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
			
			listener.accept(new Caller(UIType.BaselineObjectWindow, UIFunction.Save));
			this.clearForm();

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
				if(c instanceof CharacteristicPanel) {
					score += ((CharacteristicPanel) c).getScoreWeight();
				}
			if(Math.abs(maxScore) != Math.abs(score)) {
				JOptionPane.showMessageDialog(null,"Score must add up to 100", "Error",JOptionPane.WARNING_MESSAGE);
				return false;
				
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
			if(c instanceof CharacteristicPanel) {
				if(((CharacteristicPanel) c).PanelIsValid() == false){
					return false;
				}
			}
		
		return true;
	}
}





