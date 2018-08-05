package GenericDataComparison.UI;

import java.awt.Component;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
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
import GenericDataComparison.ObjectType;

public class EditOrCompareExistingObject extends JPanel 
{		
	private static final long serialVersionUID = 1L;
	private JLabel headerLabel;
	private JLabel promptLabel;	
	private JButton backButton;	
	private JPanel panel;
	private JScrollPane scrollPane;
	private Consumer<Caller> listener;
	private String selectedObjectName;
	
	public EditOrCompareExistingObject(Consumer<Caller> lstn) 
	{		
		listener = lstn;
		
		setLayout(null);
				
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
		
		backButton = new JButton("Back");
		backButton.setBounds(265, 433, 89, 23);
		add(backButton);
		backButton.addActionListener(e->listener.accept(new Caller(UIType.EditOrCompareWindow, UIFunction.Back)));
	}
	
	public void Initialize(ArrayList<ObjectType> objectTypes)
	{		
		panel.removeAll();
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
			d.addActionListener(e->handleEvent(o.getName(), UIFunction.Delete));
			newJpanel.add(d);			
			
			JButton ed = new JButton("");
			ed.setToolTipText("Edit");
			ed.setIcon(new ImageIcon(EditOrCompareExistingObject.class.getResource("/GenericDataComparison/UI/img/Modify.png")));
			ed.setActionCommand("Edit");
			ed.setSize(10,10);
			ed.addActionListener(e->handleEvent(o.getName(), UIFunction.Edit));
			newJpanel.add(ed);

			JButton c = new JButton(o.getName());
			c.setActionCommand("Compare");
			c.setToolTipText("Compare " + o.getName());
			c.setIcon(new ImageIcon(EditOrCompareExistingObject.class.getResource("/GenericDataComparison/UI/img/About.png")));
			c.setSize(10,10);
			c.addActionListener(e->handleEvent(o.getName(), UIFunction.Compare));
			newJpanel.add(c);
						
			panel.add(newJpanel);	
		}		
		
		//this.repaint();
        //this.validate();
	}
	
	private void handleEvent(String objName, UIFunction func)
	{
		selectedObjectName = objName;
		listener.accept(new Caller(UIType.EditOrCompareWindow, func));
	}
	
	public String getSelectedObject()
	{
		return selectedObjectName;
	}
}
