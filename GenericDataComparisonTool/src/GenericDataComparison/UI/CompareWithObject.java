package GenericDataComparison.UI;

import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import GenericDataComparison.Main;

public class CompareWithObject extends JPanel
{		  
	private static final long serialVersionUID = 1L;
	private JLabel headerLabel;
	private JLabel promptLabel;
	private JTextField fields;
	private JButton backButton;
	private JButton saveButton;
	private JButton compareButton;
	   
	public CompareWithObject(Main mainWin) 
	{
		setLayout (new FlowLayout());
		setVisible(true);
		
		setLayout (new FlowLayout());
		headerLabel = new JLabel ("Compare Existing Object", JLabel.CENTER);
		headerLabel.setFont(new Font (Font.SANS_SERIF, Font.PLAIN, 35));
		add (headerLabel);	

		promptLabel = new JLabel ("Please Enter the following Characterstics about your: ");
		promptLabel.setFont(new Font (Font.SANS_SERIF, Font.ITALIC, 15));
		add(promptLabel);
		
		fields = new JTextField (10);
		fields.setHorizontalAlignment(0);
		add (fields);
		
		backButton = new JButton ("Back");
		backButton.setSize(0,0);
		add (backButton);
		
		saveButton = new JButton ("Save");
		add (saveButton);
		
		compareButton = new JButton ("View Comparison Result");
		add (compareButton);
		 
		event e = new event ();	
		saveButton.addActionListener(e);
		backButton.addActionListener(e);
		compareButton.addActionListener(e);		
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
}
