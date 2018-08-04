package GenericDataComparison;

import java.awt.CardLayout;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;
import GenericDataComparison.UI.BaselineObjectWindow;
import GenericDataComparison.UI.CompareWithObject;
import GenericDataComparison.UI.EditOrCompareExistingObject;
import GenericDataComparison.UI.OutputPane;
import GenericDataComparison.UI.UIObject;

public class Main implements ActionListener
{
	private JFrame frame;
	private JPanel cardPanel;
	private CardLayout cardLayout;
	private GenericComparisonManager manager;
	private OutputPane oPane;
	private BaselineObjectWindow boWin;
	private CompareWithObject cwoWin;
	private EditOrCompareExistingObject eocWin;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) 
	{
		EventQueue.invokeLater(new Runnable() 
		{
			public void run() 
			{
				try 
				{
					Main window = new Main();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public Main() 
	{
		initialize();
		manager = new GenericComparisonManager();
		// SET STUFF VISIBLE HERE FOR DEVELOPMENT
		cardLayout.show(cardPanel, "outputWin");
		// --------------------------------------
	}
	
	public void TestUi() {
		
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() 
	{
		frame = new JFrame();
		frame.setBounds(100, 100, 1280, 1024);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		
		cardLayout = new CardLayout();
		cardPanel = new JPanel(cardLayout);
		frame.add(cardPanel);
		
		oPane = new OutputPane(this);
		cardPanel.add(oPane, "outputWin");
		
		boWin = new BaselineObjectWindow(this);
		cardPanel.add(boWin, "baselineWin");
		
		cwoWin = new CompareWithObject(this);
		cardPanel.add(cwoWin, "compareWin");
		
		eocWin = new EditOrCompareExistingObject(this, manager);
		cardPanel.add(eocWin, "editWin");
	}
	
	public void actionPerformed(ActionEvent ae)
	{
		// switch on which button was clicked
	}
}
