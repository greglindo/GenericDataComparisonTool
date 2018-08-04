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
import javax.swing.JPanel;
import javax.swing.JTextField;

import GenericDataComparison.BetterValue;
import GenericDataComparison.Caller;
import GenericDataComparison.Characteristic;
import GenericDataComparison.Main;
import GenericDataComparison.ObjectType;
import GenericDataComparison.UI.EditOrCompareExistingObject.ObjectTypeListener;

public class CompareWithObject extends JPanel
{		  
	private static final long serialVersionUID = 1L;
	private JLabel headerLabel;
	private JLabel promptLabel;
	private JTextField fields;
	private JButton backButton;
	private JButton saveButton;
	private JButton compareButton;
	private JPanel panel;
	private ObjectType _baseObj;
	   
	public CompareWithObject(Consumer<Caller> consumer, ObjectType BaseObject) 
	{
		//setLayout (new FlowLayout());
		setVisible(true);
		setLayout(null);
		_baseObj = BaseObject;
		
		//setLayout (new FlowLayout());
		headerLabel = new JLabel ("Compare Existing Object", JLabel.CENTER);
		headerLabel.setBounds(35, 5, 379, 45);
		headerLabel.setFont(new Font (Font.SANS_SERIF, Font.PLAIN, 35));
		add (headerLabel);	

		promptLabel = new JLabel ("Please Enter the following Characterstics about your: ");
		promptLabel.setBounds(6, 55, 347, 20);
		promptLabel.setFont(new Font (Font.SANS_SERIF, Font.ITALIC, 15));
		add(promptLabel);
		
		fields = new JTextField (10);
		fields.setBounds(358, 55, 86, 20);
		fields.setHorizontalAlignment(0);
		add (fields);
		
		panel = new JPanel();
		panel.setBounds(64, 86, 298, 158);
		add(panel);
		
		backButton = new JButton ("Back");
		backButton.setLocation(83, 255);
		backButton.setSize(55,23);
		add (backButton);
		
		saveButton = new JButton ("Save");
		saveButton.setBounds(143, 255, 57, 23);
		add (saveButton);
		
		compareButton = new JButton ("View Comparison Result");
		compareButton.setBounds(205, 255, 147, 23);
		add (compareButton);
		 
		event e = new event ();	
		saveButton.addActionListener(e);
		backButton.addActionListener(e);
		compareButton.addActionListener(e);		
		
		for(Characteristic c : _baseObj.getCharacteristics()) 
		{
			JPanel newJpanel = new JPanel();
			newJpanel.setLayout(new FlowLayout());
			newJpanel.setAlignmentX(Component.LEFT_ALIGNMENT);
			newJpanel.setVisible(true);
			
			JLabel d = new JLabel(c.getName());
			d.setToolTipText("Delete");
			//d.setIcon(new ImageIcon(EditOrCompareExistingObject.class.getResource("/GenericDataComparison/UI/img/Delete.png")));
			//d.setActionCommand("Delete");
			d.setSize(10,10);
			//d.addActionListener(new ObjectTypeListener(c, this));
			newJpanel.add(d);			
			
			JTextField ed = new JTextField();
			ed.setToolTipText("Edit");
			//ed.setIcon(new ImageIcon(EditOrCompareExistingObject.class.getResource("/GenericDataComparison/UI/img/Modify.png")));
			//ed.setActionCommand("Edit");
			ed.setSize(10,10);
			//ed.addActionListener(new ObjectTypeListener(c, this));
			newJpanel.add(ed);

						
			panel.add(newJpanel);			
		}
	}
	
	public class event implements ActionListener 
	{
		public void actionPerformed (ActionEvent e) 
		{
			String command = e.getActionCommand();	        
	        if  (command.equals( "Save" )) 
	        {
	        	//call save stuff
	        }
	        else if( command.equals( "Back" ) )  
	        {
	        	//EditorCompareExistingObject Object = new EditorCompareExistingObject(); 
		    } 
		    else if( command.equals( "View Comparison Result" ) )  
		    {
		    	//ComparisonResult Object = new ComparisonResult();  
		    }
		}
	}
	
	
	public void TestUi() {
	    ArrayList<Characteristic> newCharacteristics = new ArrayList<Characteristic>();

	    Characteristic characteristic1 = new Characteristic("No of wheels", 4, 10, 5, 6, 100, 4, 5, BetterValue.HIGHEST);
	 
	    Characteristic characteristic2 = new Characteristic("HorsePower", 190, 510, 350, 400, 100, 230, 500, BetterValue.LOWEST);
	 
	    newCharacteristics.add(characteristic1);
	 
	    newCharacteristics.add(characteristic2);
	    _baseObj = new ObjectType("test",newCharacteristics);
	 
		
	}
}
