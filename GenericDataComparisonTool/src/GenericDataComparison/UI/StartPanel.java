package GenericDataComparison.UI;

import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.util.function.Consumer;

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
		title = new JLabel("Generic Comparison Tool");
		editOrCompareBtn = new JButton("Edit or Compare Object");
		addNewBtn = new JButton("Add New Baseline Object");
		initialize();
	}
	
	public StartPanel(Consumer<Caller> lstn)
	{
		this();
		listener = lstn;
	}
	
	private void initialize()
	{
		GridBagLayout gbLayout = new GridBagLayout();
		setLayout(gbLayout);
		GridBagConstraints gbc = new GridBagConstraints();
		
		title.setHorizontalAlignment(JLabel.CENTER);
		title.setFont(new Font(Font.SANS_SERIF, Font.PLAIN, 36));
		gbc.gridx = 0;
		gbc.gridy = 0;
		add(title, gbc);
		
		editOrCompareBtn.addActionListener(e->{
			listener.accept(new Caller(UIType.StartWindow, UIFunction.EditCompare));
        });
		gbc.gridx = 0;
		gbc.gridy = 1;
		add(editOrCompareBtn, gbc);
		
		addNewBtn.addActionListener(e->{
			listener.accept(new Caller(UIType.StartWindow, UIFunction.New));
        });
		gbc.gridx = 1;
		gbc.gridy = 1;
		add(addNewBtn, gbc);
	}

}
