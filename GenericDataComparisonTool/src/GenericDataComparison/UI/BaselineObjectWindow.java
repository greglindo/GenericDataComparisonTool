package GenericDataComparison.UI;

import javax.swing.*;
import GenericDataComparison.Characteristic;
import GenericDataComparison.Main;
import GenericDataComparison.ObjectType;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class BaselineObjectWindow extends JPanel
{
	private static final long serialVersionUID = 1L;
    private JLabel _header;
    private WindowType _windowType;
    private JButton btnAdd;
	private JPanel _subPanel;

    public BaselineObjectWindow(Main mainWin)
    {
    	new ObjectType();
        _windowType = WindowType.CREATE;
        initialize();       
    }

    public BaselineObjectWindow(Main mainWin, ObjectType BaselineObject) throws HeadlessException 
    {
		super();
		this._windowType = WindowType.EDIT;
		this.initialize();
		this.addCharPanel(BaselineObject);
	}

    private void initialize()
    {
        add(addHeader());
        setVisible(true);
        
        JScrollPane scrollPane_1 = new JScrollPane();
        scrollPane_1.setBounds(10, 144, 1172, 484);
        add(scrollPane_1);
        
        _subPanel = new JPanel();
        scrollPane_1.setViewportView(_subPanel);
        GridLayout gl = new GridLayout(0, 1, 0, 0);
        _subPanel.setLayout(gl);
        _subPanel.add(new CharacteristicPanel(), "span");
        
        btnAdd = new JButton("Add");
        btnAdd.addMouseListener(new MouseAdapter() 
        {
        	@Override
        	public void mouseClicked(MouseEvent arg0) 
        	{	
        		addCharPanel();
        	}
        });

        btnAdd.setBounds(21, 107, 89, 23);
        add(btnAdd);
        
        JLabel lblAddNewCharacteristic = new JLabel("Add New Characteristic ");
        lblAddNewCharacteristic.setBounds(122, 111, 190, 14);
        add(lblAddNewCharacteristic);
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
}



