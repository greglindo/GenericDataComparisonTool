package GenericDataComparison;

import java.awt.CardLayout;
import java.awt.EventQueue;
import java.awt.TrayIcon.MessageType;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.function.Consumer;
import java.util.ArrayList;

import javax.swing.*;
import GenericDataComparison.Caller;
import GenericDataComparison.Caller.UIType;
import GenericDataComparison.Caller.UIFunction;
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
		manager = new GenericComparisonManager();
		initialize();
		
		
		// SET STUFF VISIBLE HERE FOR DEVELOPMENT
		cardLayout.show(cardPanel, "baselineWin");
		// --------------------------------------
	}
	
	public void TestUi() {
	    ArrayList<Characteristic> newCharacteristics = new ArrayList<Characteristic>();

	    Characteristic characteristic1 = new Characteristic("No of wheels", 4, 10, 5, 6, 100, 4, 5, BetterValue.HIGHEST);
	 
	    Characteristic characteristic2 = new Characteristic("HorsePower", 190, 510, 350, 400, 100, 230, 500, BetterValue.LOWEST);
	 
	    newCharacteristics.add(characteristic1);
	 
	    newCharacteristics.add(characteristic2);
	    ObjectType obj = new ObjectType("test",newCharacteristics);
	 
		manager.addObjectType(obj);
		
	}
	
	public GenericComparisonManager getManager() {
		return manager;
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() 
	{
		Consumer<Caller> consumer = (arg) -> {
            actionPerformed(arg);
        };
		
		TestUi();
		
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
		eocWin = new EditOrCompareExistingObject(this);
		cardPanel.add(eocWin, "editWin");
		eocWin.setVisible(true);
	}
	
	public void actionPerformed(Caller caller)
	{
		switch(caller.type) 
		{
		case BaselineObjectWindow:
			switch(caller.function)
			{
			case Back:
				//cardLayout.show(cardPanel, "MainWin");
				break;
				
			case Save:
				ObjectType oType = boWin.getObject();
				manager.deleteObjectTypeByName(oType.getName());
				manager.addObjectType(oType);
				manager.saveData();
				JOptionPane.showMessageDialog(null, "Your new baseline model has been saved.", "Success!",
						JOptionPane.INFORMATION_MESSAGE);
				//cardLayout.show(cardPanel, "MainWin");
				break;
				
			default:
				break;
			}
			break;
			
		case EditOrCompareWindow:
			break;
			
		case OutputWindow:
			break;
			
		default:
			break;
		}		
	}
}
