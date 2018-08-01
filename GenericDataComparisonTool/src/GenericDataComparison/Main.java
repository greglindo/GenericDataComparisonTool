package GenericDataComparison;

import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import GenericDataComparison.UI.BaselineObjectWindow;
import GenericDataComparison.UI.OutputPane;

public class Main implements ActionListener
{
	private JFrame frame;
	private GenericComparisonManager manager;
	private static OutputPane oPane;
	private static BaselineObjectWindow boWin;

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
					// SET STUFF VISIBLE HERE FOR DEVELOPMENT
					boWin.setVisible(false);
					oPane.setVisible(false);
					// --------------------------------------
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
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() 
	{
		frame = new JFrame();
		frame.setBounds(100, 100, 1280, 1024);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		oPane = new OutputPane(this);
		frame.add(oPane);
		
		boWin = new BaselineObjectWindow(this);
		frame.add(boWin);
	}
	
	public void actionPerformed(ActionEvent ae)
	{
		// switch on which button was clicked
	}
}
