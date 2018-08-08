package GenericDataComparison.UI;

import java.awt.Color;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.util.function.Consumer;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

import GenericDataComparison.Caller;
import GenericDataComparison.Caller.UIFunction;
import GenericDataComparison.Caller.UIType;

public class StartPanel extends JPanel 
{
	private static final long serialVersionUID = 1L;
	private JLabel title;
	private JButton editOrCompareBtn;
	private JButton addNewBtn;
	private Consumer<Caller> listener;

	private StartPanel() 
	{
		setBackground(new Color(145, 163, 193));
		
		title = new JLabel("Generic Comparison Tool");
		title.setBounds(30, 20, 405, 50);
		
		editOrCompareBtn = new JButton("Edit or Compare Object");
		editOrCompareBtn.setIcon(new ImageIcon(EditOrCompareExistingObject.class.getResource("/GenericDataComparison/UI/img/Go.png")));
		editOrCompareBtn.setBorder(BorderFactory.createEmptyBorder(1, 1, 1, 1));
		editOrCompareBtn.setBounds(40, 95, 180, 32);
		
		addNewBtn = new JButton("Add New Baseline Object");
		addNewBtn.setIcon(new ImageIcon(EditOrCompareExistingObject.class.getResource("/GenericDataComparison/UI/img/Add.png")));
		addNewBtn.setBorder(BorderFactory.createEmptyBorder(1, 1, 1, 1));
		addNewBtn.setBounds(240, 95, 180, 32);
		
		initialize();
	}
	
	public StartPanel(Consumer<Caller> lstn)
	{
		this();
		listener = lstn;
	}
	
	private void initialize()
	{
		setLayout(null);
		
		title.setHorizontalAlignment(JLabel.CENTER);
		title.setFont(new Font(Font.SANS_SERIF, Font.PLAIN, 36));
		add(title);
		
		editOrCompareBtn.addActionListener(e->{
			listener.accept(new Caller(UIType.StartWindow, UIFunction.EditCompare));
        });
		add(editOrCompareBtn);
		
		addNewBtn.addActionListener(e->{
			listener.accept(new Caller(UIType.StartWindow, UIFunction.New));
        });
		add(addNewBtn);
	}

}