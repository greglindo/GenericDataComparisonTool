package GenericDataComparison;

import java.awt.CardLayout;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Toolkit;
import java.util.ArrayList;
import java.util.function.Consumer;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import GenericDataComparison.Caller.UIType;
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

	public Main() 
	{
		manager = new GenericComparisonManager();
		manager.loadData();
		initialize();
		cardLayout.show(cardPanel, "startWin");
		setFrameSize(UIType.StartWindow);
	}

	private void initialize() 
	{
		Consumer<Caller> consumer = (arg) -> {
            actionPerformed(arg);
        };
				
		frame = new JFrame();
		frame.setBounds(100, 100, 1200, 800);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setResizable(false);
				
		cardLayout = new CardLayout();
		cardPanel = new JPanel(cardLayout);
		cardPanel.setBounds(0, 0, 1184, 761);
		frame.getContentPane().add(cardPanel);
		
		startWin = new StartPanel(consumer);
		cardPanel.add(startWin, "startWin");
				
		boWin = new BaselineObjectWindow(consumer);
		cardPanel.add(boWin, "baselineWin");
		
		cwoWin = new CompareWithObject(consumer);
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
				setFrameSize(UIType.EditOrCompareWindow);
				break;
				
			case New:
				boWin.setObject(null);
				cardLayout.show(cardPanel, "baselineWin");
				setFrameSize(UIType.BaselineObjectWindow);
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
				setFrameSize(UIType.StartWindow);
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
		    case Back:
		        cardLayout.show(cardPanel, "startWin");
		        setFrameSize(UIType.StartWindow);
		        break;
 		 
		      case Delete:
		  		int dialogResult = JOptionPane.showConfirmDialog (null, "Are you sure you want to delete this Object?","Warning!",2);
				if(dialogResult == JOptionPane.OK_OPTION)
				{
					manager.deleteObjectTypeByName(eocWin.getSelectedObject());
			        manager.saveData();
			        eocWin.Initialize(manager.getObjectTypes());			
				}	
		        break;		        
		 
		    case Edit:		 
		        boWin.setObject(manager.getObjectTypeByName(eocWin.getSelectedObject()));		 
		        cardLayout.show(cardPanel, "baselineWin");	
		        setFrameSize(UIType.BaselineObjectWindow);
		        break; 
		 
		      case Compare:		 
		    	  ArrayList<UserComparisonEntry> userEntries = manager.getUserComparisonEntries(manager.getObjectTypeByName(eocWin.getSelectedObject()));
		    	  cwoWin.Initialize(manager.getObjectTypeByName(eocWin.getSelectedObject()),userEntries);
		    	  cardLayout.show(cardPanel,"compareWin");
		    	  setFrameSize(UIType.CompareWithObject);
		        break;
		 
		    default:		 
		        break;		 
		    }		 
			break;
			
		case CompareWithObject:
			switch(caller.function) 
			{
			case Compare:
				UserComparisonEntry entry = cwoWin.getUserEntry();
				if(entry == null) break;
				outWin.generateOutput(entry,  manager.getObjectTypeByName(entry.getObjectTypeName()));
				cardLayout.show(cardPanel, "outputWin");
				setFrameSize(UIType.OutputWindow);
				break;
			case Back:
				cardLayout.show(cardPanel, "editWin");
				setFrameSize(UIType.EditOrCompareWindow);
				break;
				
			case Save:
				UserComparisonEntry userEntry = cwoWin.getUserEntry();
				if(userEntry == null) break;
				manager.deleteUserComparisonEntryByName((userEntry.getName()));
				manager.addUserComparisonEntry(userEntry);
				manager.saveData();
				cwoWin.UpdateUserEntries(manager.getUserComparisonEntries(manager.getObjectTypeByName(userEntry.getObjectTypeName())));
				JOptionPane.showMessageDialog(null, "Your new user entry have been saved.", "Success!",
						JOptionPane.INFORMATION_MESSAGE);
				break;
				
			case Delete:
				UserComparisonEntry _entry = cwoWin.getUserEntry();
				manager.deleteUserComparisonEntryByName((_entry.getName()));
				cwoWin.UpdateUserEntries(manager.getUserComparisonEntries(manager.getObjectTypeByName(_entry.getObjectTypeName())));
				
			default:
				break;
			
			}
			break;
			
		case OutputWindow:
			switch(caller.function)
			{
			case Back:
				cardLayout.show(cardPanel, "compareWin");
				setFrameSize(UIType.CompareWithObject);
				break;
				
			default:
				break;
			}
			break;
			
		default:
			break;
		}
		
		cardLayout.invalidateLayout(cardPanel);
	}
	
	private void setFrameSize(UIType winType)
	{
		switch(winType)
		{
		case StartWindow:
			frame.setSize(490, 250);
			break;
			
		case BaselineObjectWindow:
			frame.setSize(1155, 525);
			break;
			
		case EditOrCompareWindow:
			frame.setSize(380, 425);
			break;
			
		case CompareWithObject:
			frame.setSize(650, 350);
			break;
			
		case OutputWindow:
			frame.setSize(1330, 700);
			break;
			
		default:
			break;
		}
		
		Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
		frame.setLocation(dim.width/2-frame.getSize().width/2, dim.height/2-frame.getSize().height/2);
	}
}
