package GenericDataComparison.UI;

import java.awt.Component;
import java.awt.Dimension;
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
import java.awt.GridLayout;
import java.awt.Insets;
import java.awt.ComponentOrientation;

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
	private final static int DELETEPOSITION = 1;
	private final static int EDITPOSITION = 2;
	private final static int COMPAREPOSITION = 3;
	
	public EditOrCompareExistingObject(Consumer<Caller> lstn) 
	{		
		listener = lstn;
		
		setLayout(null);
				
		headerLabel = new JLabel ("Edit or Compare Existing Object");
		headerLabel.setBounds(282, -1, 488, 45);
		headerLabel.setFont(new Font (Font.SANS_SERIF, Font.PLAIN, 35));
		add (headerLabel);	

		promptLabel = new JLabel ("Select an Object for Comparison. Or, click \"Edit\" to edit a baseline object.");
		promptLabel.setBounds(292, 55, 475, 20);
		promptLabel.setFont(new Font (Font.SANS_SERIF, Font.ITALIC, 15));
		add(promptLabel);
		
		scrollPane = new JScrollPane();
		scrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		scrollPane.setBounds(305, 86, 451, 350);
		add(scrollPane);	
		
		panel = new JPanel();
		scrollPane.setViewportView(panel);
		panel.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		GridBagLayout gbl_panel = new GridBagLayout();
		gbl_panel.columnWidths = new int[] {27, 72, 72, 126, 30};
		gbl_panel.rowHeights = new int[] {10, 30, 30, 30, 30, 30, 30, 30, 30, 30};
		gbl_panel.columnWeights = new double[]{};
		gbl_panel.rowWeights = new double[]{};
		panel.setLayout(gbl_panel);
		
		backButton = new JButton("Back");
		backButton.setBounds(492, 447, 89, 23);
		add(backButton);
		backButton.addActionListener(e->listener.accept(new Caller(UIType.EditOrCompareWindow, UIFunction.Back)));
	}
	
	public void Initialize(ArrayList<ObjectType> objectTypes)
	{		
		panel.removeAll();
		int gridy = 1;
		for(ObjectType o : objectTypes) 
		{
			GridBagLayout gridBagLayout = new GridBagLayout();
			gridBagLayout.columnWidths = new int[]{0, 60, 60, 69, 60};
			GridBagConstraints c1 = new GridBagConstraints();
			c1.weightx = 1;


			
			JButton d = new JButton("");
			d.setToolTipText("Delete");
			d.setIcon(new ImageIcon(EditOrCompareExistingObject.class.getResource("/GenericDataComparison/UI/img/Delete.png")));
			d.setActionCommand("Delete");
			d.setPreferredSize(new Dimension(75,30));
			d.addActionListener(e->handleEvent(o.getName(), UIFunction.Delete));
			d.addActionListener(e-> {
				this.repaint();
				this.revalidate();
			});
			GridBagConstraints gbc_delete = new GridBagConstraints();
			gbc_delete.insets = new Insets(0, 0, 5, 5);
			gbc_delete.anchor = GridBagConstraints.WEST;
			gbc_delete.gridx = DELETEPOSITION;
			gbc_delete.gridy = gridy;
			panel.add(d, gbc_delete);
	
			
			JButton ed = new JButton("");
			ed.setToolTipText("Edit");
			ed.setIcon(new ImageIcon(EditOrCompareExistingObject.class.getResource("/GenericDataComparison/UI/img/Modify.png")));
			ed.setActionCommand("Edit");
			ed.setPreferredSize(new Dimension(75,30));
			ed.addActionListener(e->handleEvent(o.getName(), UIFunction.Edit));
			GridBagConstraints gbc_Edit = new GridBagConstraints();
			gbc_Edit.insets = new Insets(0, 0, 5, 5);
			gbc_Edit.anchor = GridBagConstraints.WEST;
			gbc_Edit.gridx = EDITPOSITION;
			gbc_Edit.gridy = gridy;
			panel.add(ed, gbc_Edit);

			JButton c = new JButton(o.getName());
			c.setActionCommand("Compare");
			c.setToolTipText("Compare " + o.getName());
			c.setIcon(new ImageIcon(EditOrCompareExistingObject.class.getResource("/GenericDataComparison/UI/img/About.png")));
			c.setPreferredSize(new Dimension(120,30));
			c.addActionListener(e->handleEvent(o.getName(), UIFunction.Compare));
			GridBagConstraints gbc_Compare = new GridBagConstraints();
			gbc_Compare.insets = new Insets(0, 0, 5, 5);
			gbc_Compare.anchor = GridBagConstraints.WEST;
			gbc_Compare.gridx = COMPAREPOSITION;
			gbc_Compare.gridy = gridy;
			panel.add(c, gbc_Compare);
						
			
			gridy +=1;
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
