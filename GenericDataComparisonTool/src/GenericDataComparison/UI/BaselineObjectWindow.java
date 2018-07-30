package GenericDataComparison.UI;


import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class BaselineObjectWindow extends JFrame{

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	//private JFrame ;
    private JScrollPane _scrollPane;
    private JPanel _panel;
    private JLabel _header;
    private GenericDataComparison.UI.WindowType _windowType;
    private JPanel _compPanel;


    public BaselineObjectWindow(WindowType WindowType)
    {
        _windowType = WindowType;
        initialize();
        //_panel.removeAll();S
        this.addHeader();
        //this.setVisible(true);

    }



    /**
     * Initialize the contents of the frame.
     */
    private void initialize()
    {
        // set up the main frame of the application
        //_frame = new JFrame();
        this.setBounds(100, 100, 1024, 768);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

       // BorderPane border = new BorderPane();




        // create the panel that will hold our charts
        _panel = new JPanel();


        // add the panel to the scroll pane, and the scroll pane to the frame
        
        AttributePanel cPanel = new AttributePanel();

        _scrollPane = new JScrollPane(cPanel);
       // _panel.add(cPanel);
        //cPanel.setVisible(true);
        this.getContentPane().add(_scrollPane, BorderLayout.CENTER);
        //_panel.setVisible(true);
        _scrollPane.setVisible(true);



    }

    //Adds header and specifies if the user is adding a new baseline object or modifying
    private void addHeader(){
        String headerText = "Add New Baseline Object";
        if(_windowType == WindowType.EDIT){
            headerText.replace("Add New", "Modify");
        }
        _panel.setLayout(null);
        _header = new JLabel(headerText);
        _header.setBounds(339, 52, 309, 39);
        _header.setFont(new Font("Serif", Font.PLAIN, 30));
        _panel.add(_header);
        
        JPanel panel = new JPanel();
        panel.setBounds(178, 133, 708, 263);
        _panel.add(panel);
    }
}



