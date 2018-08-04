package GenericDataComparison;

import java.awt.CardLayout;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.function.Consumer;

import javax.swing.*;
import GenericDataComparison.UI.BaselineObjectWindow;
import GenericDataComparison.UI.CompareWithObject;
import GenericDataComparison.UI.EditOrCompareExistingObject;
import GenericDataComparison.UI.OutputPane;

public class Main
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
		cardLayout.show(cardPanel, "baselineWin");
		// --------------------------------------
	}
	
	public void TestUi() {
		
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() 
	{
		Consumer<Caller> consumer = (arg) -> {
            actionPerformed(arg);
        };
		
		frame = new JFrame();
		frame.setBounds(100, 100, 1280, 1024);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		
		cardLayout = new CardLayout();
		cardPanel = new JPanel(cardLayout);
		frame.add(cardPanel);
		
		oPane = new OutputPane(consumer);
		cardPanel.add(oPane, "outputWin");
		
		boWin = new BaselineObjectWindow(consumer);
		cardPanel.add(boWin, "baselineWin");
		
		//cwoWin = new CompareWithObject(consumer);
		//cardPanel.add(cwoWin, "compareWin");
		
		eocWin = new EditOrCompareExistingObject(consumer);
		cardPanel.add(eocWin, "editWin");
	}
	
	public void actionPerformed(Caller caller)
	{
		System.out.println("Back clicked from main");
		JOptionPane.showMessageDialog(null, "Your new baseline model has been saved.", "Success!", 1);
	}
}
