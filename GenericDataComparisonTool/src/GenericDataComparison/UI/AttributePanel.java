package GenericDataComparison.UI;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.LayoutManager;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;
import java.awt.Insets;
import java.awt.Dimension;
import javax.swing.border.LineBorder;
import java.awt.Color;
import javax.swing.ButtonGroup;

public class AttributePanel extends JPanel {
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
    private final ButtonGroup buttonGroup = new ButtonGroup();

    

    
    public AttributePanel() {
		super();
		setBorder(new LineBorder(new Color(0, 0, 0)));
		setPreferredSize(new Dimension(1172, 79));
		this.initialize();
		// TODO Auto-generated constructor stub
	}





	private void initialize(){

		
		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWidths = new int[]{0, 129, 60, 85, 60, 85, 0, 64, 55, 0};
		this.setLayout(gridBagLayout);
		GridBagConstraints c = new GridBagConstraints();
        JRadioButton rdHighest = new JRadioButton("Highest is Best");
        buttonGroup.add(rdHighest);
        JPanel _panel = new JPanel();

        JLabel lblNewLabel = new JLabel("Characteristic Name");
        lblNewLabel.setPreferredSize(new Dimension(120, 14));
        GridBagConstraints gbc_lblNewLabel = new GridBagConstraints();
        gbc_lblNewLabel.insets = new Insets(0, 0, 5, 5);
        gbc_lblNewLabel.gridx = 1;
        gbc_lblNewLabel.gridy = 0;
        add(lblNewLabel, gbc_lblNewLabel);
        
        JLabel lblNewLabel_1 = new JLabel("Minimum");
        GridBagConstraints gbc_lblNewLabel_1 = new GridBagConstraints();
        gbc_lblNewLabel_1.insets = new Insets(0, 0, 5, 5);
        gbc_lblNewLabel_1.gridx = 2;
        gbc_lblNewLabel_1.gridy = 0;
        add(lblNewLabel_1, gbc_lblNewLabel_1);
        
        JLabel lblNewLabel_2 = new JLabel("1st Quartile");
        GridBagConstraints gbc_lblNewLabel_2 = new GridBagConstraints();
        gbc_lblNewLabel_2.insets = new Insets(0, 0, 5, 5);
        gbc_lblNewLabel_2.gridx = 3;
        gbc_lblNewLabel_2.gridy = 0;
        add(lblNewLabel_2, gbc_lblNewLabel_2);
        
        JLabel lblNewLabel_3 = new JLabel("Median");
        GridBagConstraints gbc_lblNewLabel_3 = new GridBagConstraints();
        gbc_lblNewLabel_3.insets = new Insets(0, 0, 5, 5);
        gbc_lblNewLabel_3.gridx = 4;
        gbc_lblNewLabel_3.gridy = 0;
        add(lblNewLabel_3, gbc_lblNewLabel_3);
        
        JLabel lblNewLabel_4 = new JLabel("3rd Quartile");
        GridBagConstraints gbc_lblNewLabel_4 = new GridBagConstraints();
        gbc_lblNewLabel_4.insets = new Insets(0, 0, 5, 5);
        gbc_lblNewLabel_4.gridx = 5;
        gbc_lblNewLabel_4.gridy = 0;
        add(lblNewLabel_4, gbc_lblNewLabel_4);
        
        JLabel lblNewLabel_6 = new JLabel("Maximum");
        GridBagConstraints gbc_lblNewLabel_6 = new GridBagConstraints();
        gbc_lblNewLabel_6.insets = new Insets(0, 0, 5, 5);
        gbc_lblNewLabel_6.gridx = 6;
        gbc_lblNewLabel_6.gridy = 0;
        add(lblNewLabel_6, gbc_lblNewLabel_6);
        
        JLabel lblNewLabel_5 = new JLabel("Average");
        GridBagConstraints gbc_lblNewLabel_5 = new GridBagConstraints();
        gbc_lblNewLabel_5.insets = new Insets(0, 0, 5, 5);
        gbc_lblNewLabel_5.gridx = 7;
        gbc_lblNewLabel_5.gridy = 0;
        add(lblNewLabel_5, gbc_lblNewLabel_5);
        
        JLabel lblNewLabel_7 = new JLabel("Weight");
        GridBagConstraints gbc_lblNewLabel_7 = new GridBagConstraints();
        gbc_lblNewLabel_7.insets = new Insets(0, 0, 5, 5);
        gbc_lblNewLabel_7.gridx = 8;
        gbc_lblNewLabel_7.gridy = 0;
        add(lblNewLabel_7, gbc_lblNewLabel_7);
        JRadioButton rdLowest_1 = new JRadioButton("Lowest is Best");
        buttonGroup.add(rdLowest_1);
        GridBagConstraints gbc_rdLowest_1 = new GridBagConstraints();
        gbc_rdLowest_1.insets = new Insets(0, 0, 5, 0);
        gbc_rdLowest_1.gridx = 9;
        gbc_rdLowest_1.gridy = 0;
        this.add(rdLowest_1, gbc_rdLowest_1);
        
        
                JButton btnDelete = new JButton("Delete"); //TODO add trash can image
                
                	

                GridBagConstraints gbc_btnDelete = new GridBagConstraints();
                gbc_btnDelete.anchor = GridBagConstraints.WEST;
                gbc_btnDelete.insets = new Insets(0, 0, 0, 5);
                gbc_btnDelete.gridx = 0;
                gbc_btnDelete.gridy = 1;
                this.add(btnDelete, gbc_btnDelete);
        
        
        JTextField txCharacteristicName = new JTextField();
        txCharacteristicName.setColumns(20);
        
        
        GridBagConstraints gbc_txCharacteristicName = new GridBagConstraints();
        gbc_txCharacteristicName.insets = new Insets(0, 0, 0, 5);
        gbc_txCharacteristicName.gridx = 1;
        gbc_txCharacteristicName.gridy = 1;
        this.add(txCharacteristicName, gbc_txCharacteristicName);
        JTextField txMinimum = new JTextField();
        txMinimum.setColumns(10);
        GridBagConstraints gbc_txMinimum = new GridBagConstraints();
        gbc_txMinimum.insets = new Insets(0, 0, 0, 5);
        gbc_txMinimum.gridx = 2;
        gbc_txMinimum.gridy = 1;
        this.add(txMinimum, gbc_txMinimum);
        JTextField txFirstQuartile = new JTextField();
        txFirstQuartile.setColumns(10);
        GridBagConstraints gbc_txFirstQuartile = new GridBagConstraints();
        gbc_txFirstQuartile.insets = new Insets(0, 0, 0, 5);
        gbc_txFirstQuartile.gridx = 3;
        gbc_txFirstQuartile.gridy = 1;
        this.add(txFirstQuartile, gbc_txFirstQuartile);
        JTextField txMedian = new JTextField();
        txMedian.setColumns(10);
        GridBagConstraints gbc_txMedian = new GridBagConstraints();
        gbc_txMedian.insets = new Insets(0, 0, 0, 5);
        gbc_txMedian.gridx = 4;
        gbc_txMedian.gridy = 1;
        this.add(txMedian, gbc_txMedian);
        JTextField txThirdQuartile = new JTextField();
        txThirdQuartile.setColumns(10);
        GridBagConstraints gbc_txThirdQuartile = new GridBagConstraints();
        gbc_txThirdQuartile.insets = new Insets(0, 0, 0, 5);
        gbc_txThirdQuartile.gridx = 5;
        gbc_txThirdQuartile.gridy = 1;
        this.add(txThirdQuartile, gbc_txThirdQuartile);
        JTextField txMaximum = new JTextField();
        txMaximum.setColumns(10);
        GridBagConstraints gbc_txMaximum = new GridBagConstraints();
        gbc_txMaximum.insets = new Insets(0, 0, 0, 5);
        gbc_txMaximum.gridx = 6;
        gbc_txMaximum.gridy = 1;
        this.add(txMaximum, gbc_txMaximum);
        JTextField txAvg = new JTextField();
        txAvg.setColumns(10);
        GridBagConstraints gbc_txAvg = new GridBagConstraints();
        gbc_txAvg.insets = new Insets(0, 0, 0, 5);
        gbc_txAvg.gridx = 7;
        gbc_txAvg.gridy = 1;
        this.add(txAvg, gbc_txAvg);
        JTextField txWeight = new JTextField();
        txWeight.setColumns(10);
        GridBagConstraints gbc_txWeight = new GridBagConstraints();
        gbc_txWeight.insets = new Insets(0, 0, 0, 5);
        gbc_txWeight.gridx = 8;
        gbc_txWeight.gridy = 1;
        this.add(txWeight, gbc_txWeight);
        GridBagConstraints gbc_rdHighest = new GridBagConstraints();
        gbc_rdHighest.gridx = 9;
        gbc_rdHighest.gridy = 1;
        this.add(rdHighest, gbc_rdHighest);


//
//        for(JComponent obj: _compArray){
//        	this.add(obj);
//        }
        
        
    }
    
//    private void addControls(){
//    	this.add(new JLabel("Name of new baseline object"));
//
//    }



}
