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
	private JScrollPane scrollPane_1;
	private JButton btnDelete;
	private Consumer<Caller> listener;


    public BaselineObjectWindow(Consumer<Caller> lstn)
    {
    	new ObjectType();
        _windowType = WindowType.CREATE;
        initialize();
        this.addCharPanel();
        listener = lstn;
    }

    public BaselineObjectWindow(Consumer<Caller> lstn, ObjectType BaselineObject) throws HeadlessException 
    {
		super();
		this._windowType = WindowType.EDIT;
		this.initialize();
		this.bind();
		this.addCharPanel(_baseObj);
		this.repaint();
		this.validate();
		listener = lstn;
	}
    
    private void bind() {
    	this.txBaselineObjectName.setText(_baseObj.getName());
    }

    private void initialize()
    {
        // create the panel that will hold our charts
        add(addHeader());        
        setVisible(true);
        
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
        add(btnAdd);
        
        JLabel lblAddNewCharacteristic = new JLabel("Add New Characteristic ");
        lblAddNewCharacteristic.setBounds(122, 80, 190, 14);
        add(lblAddNewCharacteristic);
        
        JLabel lblBaselineObjectName = new JLabel("Name of New Baseline Object");
        lblBaselineObjectName.setBounds(334, 76, 208, 14);
        add(lblBaselineObjectName);
        
        txBaselineObjectName = new JTextField();
        txBaselineObjectName.setBounds(552, 73, 156, 20);
        add(txBaselineObjectName);
        txBaselineObjectName.setColumns(10);
        
        
        //Save Button
        btnSave = new JButton("Save");
        btnSave.addActionListener(e ->{
        	this.saveObject();
        });
        btnSave.setBounds(619, 656, 89, 23);
        add(btnSave);
        
        
        //Back Button
        btnBack = new JButton("Back");
        btnBack.addActionListener(e ->{
        	listener.accept(new Caller(UIType.BaselineObjectWindow, UIFunction.Back));
        });
        btnBack.setBounds(492, 656, 89, 23);
        add(btnBack);
        
        JButton btnDeleteBaselineObject = new JButton("Delete Baseline Object");
        btnDeleteBaselineObject.addActionListener(e-> {
        	this.deleteBaselineObject();
        });
        btnDeleteBaselineObject.setBounds(297, 656, 172, 23);
        add(btnDeleteBaselineObject);
        
        btnDelete = new JButton("Delete");
        btnDelete.setBounds(21, 110, 89, 23);
        add(btnDelete);
        
        JLabel lblDeleteMarkedCharacteristic = new JLabel("Delete Marked Characteristic");
        lblDeleteMarkedCharacteristic.setBounds(122, 114, 190, 14);
        add(lblDeleteMarkedCharacteristic);
        
        btnDelete.addActionListener(e->{
        	deletePanel();
        });
        
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
            headerText.replace("Add New", "Modify");
        }
        setLayout(null);
        _header = new JLabel(headerText);
        _header.setBounds(418, 26, 309, 39);
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
	
    private void deleteBaselineObject() {
		int dialogResult = JOptionPane.showConfirmDialog (null, "Are you sure you want to delete this Object?","Warning!",2);
		if(dialogResult == JOptionPane.OK_CANCEL_OPTION){
		}
		
		
	}
    
    public ObjectType getObject()
    {
    	return _baseObj;
    }
	
	private void saveObject() {
		
		try {

			// Clear out characteristics
			if (_windowType == WindowType.EDIT || _baseObj.getCharacteristics() != null) {
				_baseObj.deleteCharacteristics();
			}
			// Get Base Object Name
			if (this.txBaselineObjectName.getText().equals("")) {
				throw new Exception("Baseline Object must have a name");
			}
			_baseObj.setName(this.txBaselineObjectName.getText());

			
			// Get all characteristics from panels
			for (Component c : _subPanel.getComponents()) {
				if (c instanceof CharacteristicPanel) {
					_baseObj.addCharacteristic(((CharacteristicPanel) c).getCharacteristic());
				}

			}
			
			listener.accept(new Caller(UIType.BaselineObjectWindow, UIFunction.Save));

		} catch (Exception e) {
			// TODO error handling
		}

	}
}





