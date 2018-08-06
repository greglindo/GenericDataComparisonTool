package GenericDataComparison.UI;

import java.awt.Dimension;
import java.awt.Font;
import java.util.ArrayList;
import java.util.function.Consumer;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.ScrollPaneConstants;
import javax.swing.border.BevelBorder;

import GenericDataComparison.Caller;
import GenericDataComparison.Caller.UIFunction;
import GenericDataComparison.Caller.UIType;
import net.miginfocom.layout.LC;
import net.miginfocom.swing.MigLayout;
import GenericDataComparison.ObjectType;


public class EditOrCompareExistingObject extends JPanel 
{		
	private static final long serialVersionUID = 1L;
	private JLabel headerLabel;
	private JLabel headerLabel2;
	private JButton backButton;	
	private JPanel panel;
	private JScrollPane scrollPane;
	private Consumer<Caller> listener;
	private String selectedObjectName;

	
	public EditOrCompareExistingObject(Consumer<Caller> lstn) 
	{		
		listener = lstn;
		
		setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
				
		add(Box.createRigidArea(new Dimension(0, 20)));
		headerLabel = new JLabel ("Edit or Compare");
		headerLabel.setFont(new Font (Font.SANS_SERIF, Font.PLAIN, 30));
		headerLabel.setAlignmentX(CENTER_ALIGNMENT);		
		add (headerLabel);
		headerLabel2 = new JLabel ("Existing Object");
		headerLabel2.setFont(new Font (Font.SANS_SERIF, Font.PLAIN, 30));
		headerLabel2.setAlignmentX(CENTER_ALIGNMENT);		
		add (headerLabel2);
		add(Box.createRigidArea(new Dimension(0, 20)));

		scrollPane = new JScrollPane();
		scrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		scrollPane.setMaximumSize(new Dimension(300, 200));
		add(scrollPane);	
		
		panel = new JPanel();
		scrollPane.setViewportView(panel);
		panel.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		MigLayout ml = new MigLayout("", "[300!]", "[40!]");
		LC lc = new LC();
		lc.wrapAfter(1);
		ml.setLayoutConstraints(lc);
		panel.setLayout(ml);
		
		backButton = new JButton("Back");
		backButton.setAlignmentX(CENTER_ALIGNMENT);
		add(Box.createRigidArea(new Dimension(0, 20)));
		add(backButton);
		add(Box.createRigidArea(new Dimension(0, 20)));
		backButton.addActionListener(e->listener.accept(new Caller(UIType.EditOrCompareWindow, UIFunction.Back)));
	}
	
	public void Initialize(ArrayList<ObjectType> objectTypes)
	{		
		panel.removeAll();
		for(ObjectType o : objectTypes) 
		{
			
			JPanel newJpanel = new JPanel();
			newJpanel.setLayout(new BoxLayout(newJpanel, BoxLayout.X_AXIS));
			
			JButton d = new JButton("");
			d.setToolTipText("Delete");
			d.setIcon(new ImageIcon(EditOrCompareExistingObject.class.getResource("/GenericDataComparison/UI/img/Delete.png")));
			d.setActionCommand("Delete");
			d.setSize(10,10);
			d.addActionListener(e->handleEvent(o.getName(), UIFunction.Delete));
			d.addActionListener(e-> {
				this.repaint();
				this.revalidate();
			});
			newJpanel.add(d);
			newJpanel.add(Box.createRigidArea(new Dimension(5, 0)));
			
			JButton ed = new JButton("");
			ed.setToolTipText("Edit");
			ed.setIcon(new ImageIcon(EditOrCompareExistingObject.class.getResource("/GenericDataComparison/UI/img/Modify.png")));
			ed.setActionCommand("Edit");
			ed.setSize(10,10);
			ed.addActionListener(e->handleEvent(o.getName(), UIFunction.Edit));
			newJpanel.add(ed);
			newJpanel.add(Box.createRigidArea(new Dimension(5, 0)));

			JButton c = new JButton(o.getName());
			c.setActionCommand("Compare");
			c.setToolTipText("Compare " + o.getName());
			c.setIcon(new ImageIcon(EditOrCompareExistingObject.class.getResource("/GenericDataComparison/UI/img/About.png")));
			c.setSize(10,10);
			c.addActionListener(e->handleEvent(o.getName(), UIFunction.Compare));
			newJpanel.add(c);
						
			panel.add(newJpanel);	
		}		
		
		this.repaint();
        this.validate();
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
