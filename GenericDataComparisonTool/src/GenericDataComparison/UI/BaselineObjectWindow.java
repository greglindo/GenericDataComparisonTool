package GenericDataComparison.UI;

import java.awt.Color;
import java.awt.Component;
import java.awt.Font;
import java.util.function.Consumer;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;

import GenericDataComparison.Caller;
import GenericDataComparison.Caller.UIFunction;
import GenericDataComparison.Caller.UIType;
import net.miginfocom.layout.LC;
import net.miginfocom.swing.MigLayout;
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
	private BaselineObjectWindow() 
	{
		super();
    	_baseObj = new ObjectType();
        _windowType = WindowType.CREATE;
        initialize();
	}
  
	//Create a new object
    public BaselineObjectWindow(Consumer<Caller> lstn)
    {
    	super();
        listener = lstn;
        initialize();       
    }    

	private void bind() 
	{
    	this.txBaselineObjectName.setText(_baseObj.getName());

    }

    private void initialize()
    {
    	
    	setBackground(new Color(145, 163, 193));
    	
    	_scrollPane = new JScrollPane();
        _scrollPane.setBounds(20, 139, 1100, 270);
        add(_scrollPane);
        
        _subPanel = new JPanel();
        _scrollPane.setViewportView(_subPanel);
        MigLayout ml = new MigLayout("", "[1060!]", "[56!]");
		LC lc = new LC();
		lc.wrapAfter(1);
		ml.setLayoutConstraints(lc);
        _subPanel.setLayout(ml);
        _subPanel.setBackground(new Color(145, 163, 193));

        btnAdd = new JButton("Add");
        btnAdd.setIcon(new ImageIcon(this.getClass().getResource("/GenericDataComparison/UI/img/Add.png")));
        btnAdd.setBorder(BorderFactory.createEmptyBorder(1, 1, 1, 1));
        btnAdd.addActionListener(e ->{
        	if(!this.checkPanelsAreValid()) 
        	{
        		
        		return;
        	}
        	
        	addCharPanel();
    	});

        btnAdd.setBounds(20, 61, 89, 32);
        this.add(btnAdd);
        
        JLabel lblAddNewCharacteristic = new JLabel("Add New Characteristic ");
        lblAddNewCharacteristic.setBounds(122, 70, 190, 14);
        this.add(lblAddNewCharacteristic);
        
        JLabel lblBaselineObjectName = new JLabel("Name of New Baseline Object");
        lblBaselineObjectName.setBounds(411, 72, 172, 20);
        this.add(lblBaselineObjectName);
        
        txBaselineObjectName = new JTextField();
        txBaselineObjectName.setBounds(585, 72, 190, 20);
        this.add(txBaselineObjectName);
        txBaselineObjectName.setColumns(10);

        btnSave = new JButton("Save");
        btnSave.setIcon(new ImageIcon(this.getClass().getResource("/GenericDataComparison/UI/img/Save.png")));
        btnSave.setBorder(BorderFactory.createEmptyBorder(1, 1, 1, 1));
        btnSave.addActionListener(e ->{
        	this.saveObject();
        });
        btnSave.setBounds(600, 435, 89, 32);
        this.add(btnSave);
        
        btnBack = new JButton("Back");
        btnBack.setIcon(new ImageIcon(this.getClass().getResource("/GenericDataComparison/UI/img/Back.png")));
        btnBack.setBorder(BorderFactory.createEmptyBorder(1, 1, 1, 1));
        btnBack.addActionListener(e ->{
        	if(hasChandedData())
			{
				int dialogResult = JOptionPane.showConfirmDialog (null, "Changes have not been saved.  Do you want to continue?","Warning!",2);
				if(dialogResult == JOptionPane.CANCEL_OPTION)
				{
					return;
				}
			}
        	listener.accept(new Caller(UIType.BaselineObjectWindow, UIFunction.Back));
        });
        btnBack.setBounds(480, 435, 89, 32);
        this.add(btnBack);
        add(btnBack);
        
        btnDeleteBaselineObject = new JButton("Delete Baseline Object");
        btnDeleteBaselineObject.setIcon(new ImageIcon(this.getClass().getResource("/GenericDataComparison/UI/img/Delete.png")));
        btnDeleteBaselineObject.setBorder(BorderFactory.createEmptyBorder(1, 1, 1, 1));
        btnDeleteBaselineObject.addActionListener(e-> {
        	this.deleteBaselineObject();

        });
        btnDeleteBaselineObject.setBounds(297, 651, 172, 32);
        this.add(btnDeleteBaselineObject);
        btnDeleteBaselineObject.setVisible(false);
        
        setLayout(null);
        _header = new JLabel();
        _header.setBounds(415, 22, 350, 39);
        _header.setHorizontalAlignment(JLabel.CENTER);
        _header.setFont(new Font(Font.SANS_SERIF, Font.PLAIN, 30));
        this.add(_header);
        
        btnDelete = new JButton("Delete");
        btnDelete.setIcon(new ImageIcon(this.getClass().getResource("/GenericDataComparison/UI/img/Delete.png")));
        btnDelete.setBorder(BorderFactory.createEmptyBorder(1, 1, 1, 1));
        btnDelete.setBounds(20, 100, 89, 32);
        this.add(btnDelete);
        
        JLabel lblDeleteMarkedCharacteristic = new JLabel("Delete Marked Characteristic");
        lblDeleteMarkedCharacteristic.setBounds(122, 109, 190, 14);
        this.add(lblDeleteMarkedCharacteristic);
        
        btnDelete.addActionListener(e->{
        	int dialogResult = JOptionPane.showConfirmDialog (null, "Are you sure you want to delete this characteristic?","Warning!",2);
			if(dialogResult == JOptionPane.OK_OPTION)
			{
				deletePanel();
			}
        });
        
        this.repaint();
        this.validate();

    }
    
	private void deletePanel() 
	{
		for(Component c: _subPanel.getComponents())
		{
			if(((CharacteristicPanel) c).DeleteFlag()) 
			{
				_subPanel.remove(c);
				this.repaint();
				this.validate();
			}
		}		
	}   
	
	private boolean hasChandedData()
	{
		return false; //TODO make work
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
	
    private void deleteBaselineObject() 
    {
		int dialogResult = JOptionPane.showConfirmDialog (null, "Are you sure you want to delete this Object?","Warning!",2);
		if(dialogResult == JOptionPane.OK_OPTION)
		{
			listener.accept(new Caller(UIType.BaselineObjectWindow, UIFunction.Delete));			
		}		
	}
    
    public void clearForm() 
    {
    	_baseObj = new ObjectType();
    	String baseObjName = "";
		for(Component c: _subPanel.getComponents()) 
		{
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
	
	private void saveObject() 
	{
		if (this.checkPanelsAreValid() == false  || this.validateScore() == false ) 
		{
			return;
		}
		
		// Clear out characteristics
		if (_windowType == WindowType.EDIT || _baseObj.getCharacteristics() != null) 
		{
			_baseObj.deleteCharacteristics();
		}
		// Get Base Object Name
		if (this.txBaselineObjectName.getText().equals("")) 
		{
			JOptionPane.showMessageDialog(null,"Baseline Object must have a name","Error",JOptionPane.WARNING_MESSAGE);
		}
		_baseObj.setName(this.txBaselineObjectName.getText());
		
		// Get all characteristics from panels
		for (Component c : _subPanel.getComponents()) 
		{
			if (c instanceof CharacteristicPanel) 
			{
				_baseObj.addCharacteristic(((CharacteristicPanel) c).getCharacteristic());
			}
		}
		
		listener.accept(new Caller(UIType.BaselineObjectWindow, UIFunction.Save));
		this.clearForm();

	}
	
	//Validate that the score adds up to 100
	private boolean validateScore() 
	{
		double score = 0;
		double maxScore = 100;

		for(Component c: _subPanel.getComponents())
		{
			if(c instanceof CharacteristicPanel) 
			{
				score += ((CharacteristicPanel) c).getScoreWeight();
			}
		}
		if(Math.abs(maxScore) != Math.abs(score)) 
		{
			JOptionPane.showMessageDialog(null,"Score must add up to 100", "Error",JOptionPane.WARNING_MESSAGE);
			return false;				
		}
		
		return true;
	}
	
	//Check that all panels have been completely filled out
	private boolean checkPanelsAreValid() 
	{
		for(Component c: _subPanel.getComponents())
		{
			if(c instanceof CharacteristicPanel) 
			{
				if(((CharacteristicPanel) c).PanelIsValid() == false)
				{
					return false;
				}
			}
		}
		
		return true;
	}
}





