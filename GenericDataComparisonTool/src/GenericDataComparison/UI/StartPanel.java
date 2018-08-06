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
import java.awt.BorderLayout;
import javax.swing.BoxLayout;
import java.awt.Component;

public class StartPanel extends JPanel 
{
	private static final long serialVersionUID = 1L;
	private JLabel title;
	private JButton editOrCompareBtn;
	private JButton addNewBtn;
	private Consumer<Caller> listener;

	private StartPanel() 
	{
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
		
		JPanel panel = new JPanel();
		panel.setBounds(305, 86, 451, 350);
		panel.setAlignmentX(Component.RIGHT_ALIGNMENT);
		add(panel);
		panel.setLayout(null);
		editOrCompareBtn = new JButton("Edit or Compare Object");
		editOrCompareBtn.setBounds(10, 135, 190, 23);
		panel.add(editOrCompareBtn);
		addNewBtn = new JButton("Add New Baseline Object");
		addNewBtn.setBounds(233, 135, 195, 23);
		panel.add(addNewBtn);
		title = new JLabel("Generic Comparison Tool");
		title.setBounds(10, 11, 405, 47);
		panel.add(title);
		//GridBagLayout gbLayout = new GridBagLayout();
		//setLayout(new Layout(a));
		//GridBagConstraints gbc = new GridBagConstraints();
		
		title.setHorizontalAlignment(JLabel.CENTER);
		title.setFont(new Font(Font.SANS_SERIF, Font.PLAIN, 36));
		
		addNewBtn.addActionListener(e->{
			listener.accept(new Caller(UIType.StartWindow, UIFunction.New));
        });
		
		editOrCompareBtn.addActionListener(e->{
			listener.accept(new Caller(UIType.StartWindow, UIFunction.EditCompare));
        });
	}
}
