package GenericDataComparison;

import java.awt.CardLayout;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Toolkit;
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
import GenericDataComparison.UI.StartPanel;

public class Main
{
	private JFrame frame;
	private JPanel cardPanel;
	private CardLayout cardLayout;
	private GenericComparisonManager manager;
	private OutputPane outWin;
	private BaselineObjectWindow boWin;
	private CompareWithObject cwoWin;
	private EditOrCompareExistingObject eocWin;
	private StartPanel startWin;

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
		cardLayout.show(cardPanel, "startWin");
	}
	
	public void TestUi() {
	    ArrayList<Characteristic> newCharacteristics = new ArrayList<Characteristic>();

	    Characteristic characteristic1 = new Characteristic("No of wheels", 4, 10, 5, 6, 100, 4, 5, BetterValue.HIGHEST);
	 
	    Characteristic characteristic2 = new Characteristic("HorsePower", 190, 510, 350, 400, 100, 230, 500, BetterValue.LOWEST);
	    Characteristic characteristic3 = new Characteristic("HorsePower", 190, 510, 350, 400, 100, 230, 500, BetterValue.LOWEST);
	    Characteristic characteristic4 = new Characteristic("HorsePower", 190, 510, 350, 400, 100, 230, 500, BetterValue.LOWEST);
	 
	    newCharacteristics.add(characteristic1);
	 
	    newCharacteristics.add(characteristic2);
	    
	    newCharacteristics.add(characteristic3);
	    newCharacteristics.add(characteristic4);
	    
	    ObjectType obj1 = new ObjectType("test1",newCharacteristics);
	    ObjectType obj2 = new ObjectType("test2",newCharacteristics);
	    ObjectType obj3 = new ObjectType("test3",newCharacteristics);
	 
		manager.addObjectType(obj1);
		manager.addObjectType(obj2);
		manager.addObjectType(obj3);
		manager.addObjectType(obj1);
		manager.addObjectType(obj2);
		manager.addObjectType(obj3);
		manager.addObjectType(obj1);
		manager.addObjectType(obj2);
		manager.addObjectType(obj3);
		
		manager.loadData();
		
		
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
		Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
		frame.setLocation(dim.width/2-frame.getSize().width/2, dim.height/2-frame.getSize().height/2);
				
		cardLayout = new CardLayout();
		cardPanel = new JPanel(cardLayout);
		frame.add(cardPanel);
		
		startWin = new StartPanel(consumer);
		cardPanel.add(startWin, "startWin");
				
		boWin = new BaselineObjectWindow(consumer);
		cardPanel.add(boWin, "baselineWin");
		
		cwoWin = new CompareWithObject(consumer, manager.getObjectTypeByName("test1"));
		cardPanel.add(cwoWin, "compareWin");
		
		eocWin = new EditOrCompareExistingObject(consumer);
		cardPanel.add(eocWin, "editWin");
		
		outWin = new OutputPane(consumer);
		cardPanel.add(outWin, "outputWin");

	}
	
	public void actionPerformed(Caller caller)
	{
		switch(caller.type) 
		{
		case StartWindow:
			switch(caller.function)
			{
			case EditCompare:
				eocWin.Initialize(manager.getObjectTypes());
				cardLayout.show(cardPanel, "editWin");
				break;
				
			case New:
				cardLayout.show(cardPanel, "baselineWin");
				break;
				
			default:
				break;
			}
			break;
		
		case BaselineObjectWindow:
			ObjectType oType;
			switch(caller.function)
			{
			case Back:
				boWin.clearForm();
				cardLayout.show(cardPanel, "startWin");
				break;
				
			case Save:
				oType = boWin.getObject();
				manager.deleteObjectTypeByName(oType.getName());
				manager.addObjectType(oType);
				manager.saveData();
				JOptionPane.showMessageDialog(null, "Your new baseline model has been saved.", "Success!",
						JOptionPane.INFORMATION_MESSAGE);
				boWin.clearForm();
				break;
				
			case Delete:
				oType = boWin.getObject();
				manager.deleteObjectTypeByName(oType.getName());
				manager.saveData();
				boWin.clearForm();
				break;
				
			default:
				break;
			}
			break;
			
		case EditOrCompareWindow:
			switch(caller.function) 
			{
			case Open:
				cardLayout.show(cardPanel, "outputWin");
				break;
			case Back:
				cardLayout.show(cardPanel, "MainWin");
				break;
				
			case Save:
				UserComparisonEntry userEntry = cwoWin.getUserEntry();
				manager.deleteUserComparisonEntryByName((userEntry.getName()));
				manager.addUserComparisonEntry(userEntry);
				manager.saveData();
				JOptionPane.showMessageDialog(null, "Your new user entry have been saved.", "Success!",
						JOptionPane.INFORMATION_MESSAGE);
				//cardLayout.show(cardPanel, "MainWin");
				break;
				
			default:
				break;
			
			}
			break;
			
		case OutputWindow:
			break;
			
		default:
			break;
		}		
	}
}
