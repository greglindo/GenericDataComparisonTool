package GenericDataComparison.UI;


import javax.swing.*;

import GenericDataComparison.Characteristic;
import GenericDataComparison.ObjectType;

import java.awt.*;
import java.util.ArrayList;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class BaselineObjectWindow extends JFrame{

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
    private JPanel _panel;
    private JLabel _header;
    private GenericDataComparison.UI.WindowType _windowType;
    private JPanel _compPanel;
    private final ObjectType _baseObj;
	private JButton btnAdd;
	private JPanel _subPanel;


    public BaselineObjectWindow()
    {
    	_baseObj = new ObjectType();
        _windowType = WindowType.CREATE;
        initialize();
       
    }



    public BaselineObjectWindow( ObjectType BaselineObject) throws HeadlessException {
		super();
		this._windowType = WindowType.EDIT;
		this._baseObj = BaselineObject;
		this.initialize();
		this.addCharPanel(BaselineObject);
	}



	/**
     * Initialize the contents of the frame.
     */
    private void initialize()
    {
        // set up the main frame of the application
        this.setBounds(100, 100, 1242, 758);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);


        // create the panel that will hold our charts
        _panel = new JPanel();
        _panel.add(addHeader());

        

        this.getContentPane().add(_panel);
        
        _panel.setVisible(true);
        
        JScrollPane scrollPane_1 = new JScrollPane();
        scrollPane_1.setBounds(10, 144, 1172, 484);
        _panel.add(scrollPane_1);
        
        _subPanel = new JPanel();
        scrollPane_1.setViewportView(_subPanel);
        GridLayout gl = new GridLayout(0, 1, 0, 0);
        _subPanel.setLayout(gl);
        _subPanel.add(new CharacteristicPanel(), "span");

        
        //Mouse click event
        btnAdd = new JButton("Add");
        btnAdd.addMouseListener(new MouseAdapter() {
        	@Override
        	public void mouseClicked(MouseEvent arg0) {	
        		addCharPanel();

        	}


        });

        btnAdd.setBounds(21, 107, 89, 23);
        _panel.add(btnAdd);
        
        JLabel lblAddNewCharacteristic = new JLabel("Add New Characteristic ");
        lblAddNewCharacteristic.setBounds(122, 111, 190, 14);
        _panel.add(lblAddNewCharacteristic);
        
        //this.pack();



    }

    //Adds header and specifies if the user is adding a new baseline object or modifying
    private JLabel addHeader(){
        String headerText = "Add New Baseline Object";
        if(_windowType == WindowType.EDIT){
            headerText.replace("Add New", "Modify");
        }
        _panel.setLayout(null);
        _header = new JLabel(headerText);
        _header.setBounds(418, 26, 309, 39);
        _header.setFont(new Font("Serif", Font.PLAIN, 30));
        
        return _header;

    }
    
    
	private void addCharPanel() {
		_subPanel.add(new CharacteristicPanel(), "span");
		_subPanel.repaint();
		_subPanel.revalidate();
		// TODO Auto-generated method stub
		
	}
	
	
	private void addCharPanel(ObjectType BaselineObject) {
		for(Characteristic _char: BaselineObject.getCharacteristics()) {
			_subPanel.add(new CharacteristicPanel(_char), "span");
			_subPanel.repaint();
			_subPanel.revalidate();
			// TODO Auto-generated method stub
			
		}
		
		
	}
}



