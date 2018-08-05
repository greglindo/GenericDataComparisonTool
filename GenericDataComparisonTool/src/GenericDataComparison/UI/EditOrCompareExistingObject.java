package GenericDataComparison.UI;

import java.awt.Component;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.function.Consumer;

import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.ScrollPaneConstants;
import javax.swing.border.BevelBorder;

import GenericDataComparison.Caller;
import GenericDataComparison.Caller.UIType;
import GenericDataComparison.Caller.UIFunction;
import GenericDataComparison.GenericComparisonManager;
import GenericDataComparison.ObjectType;

public class EditOrCompareExistingObject extends JPanel {
		
	private static final long serialVersionUID = 1L;
	private JLabel headerLabel;
	private JLabel promptLabel;	
	private JButton backButton;	
	private ArrayList<ObjectType> objectTypes;
	private GenericComparisonManager g;
	private JPanel panel;
	//private Main mainWin;
	private JScrollPane scrollPane;


	private Consumer<Caller> listener;
	
//	protected Main getMain() {
//		return this.mainWin;
//	}
	
	//public EditOrCompareExistingObject(Main mainWin) {
	public EditOrCompareExistingObject(Consumer<Caller> lstn, ArrayList<ObjectType> BaselineObjectTypes) {
		
		//this.mainWin = mainWin;
		//objectTypes = this.mainWin.getManager().getObjectTypes();
		listener = lstn;
					

		objectTypes = BaselineObjectTypes;
		
		setLayout(null);
		setVisible(true);		
				
		headerLabel = new JLabel ("Edit or Compare Existing Object");
		headerLabel.setBounds(68, 11, 488, 45);
		headerLabel.setFont(new Font (Font.SANS_SERIF, Font.PLAIN, 35));
		add (headerLabel);	

		promptLabel = new JLabel ("Select an Object for Comparison. Or, click \"Edit\" to edit a baseline object.");
		promptLabel.setBounds(78, 67, 475, 20);
		promptLabel.setFont(new Font (Font.SANS_SERIF, Font.ITALIC, 15));
		add(promptLabel);
		
		scrollPane = new JScrollPane();
		scrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		scrollPane.setBounds(127, 98, 357, 324);
		add(scrollPane);	
		scrollPane.setVisible(true);
		
		panel = new JPanel(new FlowLayout());
		scrollPane.setViewportView(panel);
		panel.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		panel.setVisible(true);
		panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
		
		for(ObjectType o : objectTypes) 
		{
			GridBagLayout gridBagLayout = new GridBagLayout();
			gridBagLayout.columnWidths = new int[]{0, 60, 60, 69, 60};
			GridBagConstraints c1 = new GridBagConstraints();
			c1.weightx = 1;
			JPanel newJpanel = new JPanel();
			newJpanel.setLayout(gridBagLayout);
			newJpanel.setAlignmentX(Component.LEFT_ALIGNMENT);
			newJpanel.setVisible(true);
			
			JButton d = new JButton("");
			d.setToolTipText("Delete");
			d.setIcon(new ImageIcon(EditOrCompareExistingObject.class.getResource("/GenericDataComparison/UI/img/Delete.png")));
			d.setActionCommand("Delete");
			d.setSize(10,10);
			d.addActionListener(new ObjectTypeListener(o, this));
			newJpanel.add(d);			
			
			JButton ed = new JButton("");
			ed.setToolTipText("Edit");
			ed.setIcon(new ImageIcon(EditOrCompareExistingObject.class.getResource("/GenericDataComparison/UI/img/Modify.png")));
			ed.setActionCommand("Edit");
			ed.setSize(10,10);
			ed.addActionListener(new ObjectTypeListener(o, this));
			newJpanel.add(ed);

			JButton c = new JButton(o.getName());
			c.setActionCommand("Compare");
			c.setToolTipText("Compare " + o.getName());
			c.setIcon(new ImageIcon(EditOrCompareExistingObject.class.getResource("/GenericDataComparison/UI/img/About.png")));
			c.setSize(10,10);
			c.addActionListener(new ObjectTypeListener(o, this));
			newJpanel.add(c);
						
			panel.add(newJpanel);			
		}

		event e = new event ();
		backButton = new JButton("Back");
		backButton.setBounds(265, 433, 89, 23);
		add(backButton);
		backButton.addActionListener(e);		
	}
	
	public class ObjectTypeListener implements ActionListener {
	    private ObjectType objectType;
	    private EditOrCompareExistingObject window;
	    

	    public ObjectTypeListener(ObjectType objectType, EditOrCompareExistingObject window) {
	        this.objectType = objectType;
	        this.window = window;
	    }
	    
	    public void actionPerformed(ActionEvent e) {	    	
	    	// TODO: Need to change this
	    	// IDEALLY: window.getMain().actionPerformed(e, objectType);
	    	System.out.println("Action: " + e.getActionCommand() + ", " + objectType.getName());
	    }
	}
	
	public class event implements ActionListener 
	{
		public void actionPerformed (ActionEvent e) 
		{
			String command = e.getActionCommand();	        
	        if( command.equals( "Back" ) )  
	        {
	        	System.out.println("Back clicked");
		    }
		}
	}
}
