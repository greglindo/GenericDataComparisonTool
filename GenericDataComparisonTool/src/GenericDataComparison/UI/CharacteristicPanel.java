package GenericDataComparison.UI;

import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;
import javax.swing.border.MatteBorder;

import GenericDataComparison.BetterValue;
import GenericDataComparison.Characteristic;

public class CharacteristicPanel extends JPanel 
{
	private static final long serialVersionUID = 1L;
    private final ButtonGroup buttonGroup = new ButtonGroup();
    private final Characteristic  _char;
	private JTextField txCharacteristicName;
	private JTextField txMinimum;
	private JTextField txFirstQuartile;
	private JTextField txMedian;
	private JTextField txThirdQuartile;
	private JTextField txMaximum;
	private JTextField txAvg;
	private JTextField txWeight;
	private JRadioButton rdHighest;
	private JRadioButton rdLowest;
	private JButton btnDelete;
	private JCheckBox chbxDelete;
	private boolean _panelIsValid;
    
    public CharacteristicPanel() 
    {
		super();
		_char = new Characteristic();
		this.initialize();
	}    
   
	public CharacteristicPanel(Characteristic _char) 
	{
		super();
		this._char = _char;
		this.initialize();
		this.bind();
	}

	private void bind() 
	{		
		try 
		{
			txCharacteristicName.setText(_char.getName());
			txMinimum.setText(_char.getMinimumValue()+"");
			txFirstQuartile.setText(_char.getFirstQuartile()+"");
			txMedian.setText(_char.getMedianValue()+"");
			txThirdQuartile.setText(_char.getThirdQuartile()+"");
			txMaximum.setText(_char.getMaximumValue()+"");
			txAvg.setText(_char.getAverageValue()+"");
			txWeight.setText(_char.getScoreWeightValue()+"");
			if(_char.getBetterValue() == BetterValue.LOWEST) 
			{
				this.rdHighest.setSelected(false);
		        this.rdLowest.setSelected(true);
			}			
		} catch(Exception e) {
			e.printStackTrace();
			e.getCause();
		}						
	}
	
	public double getScoreWeight() 
	{
		return (Double.parseDouble(this.txWeight.getText()));
	}
	
	private boolean fieldCheck() 
	{
		try 
		{
			for(Component c: this.getComponents()) 
			{
				if (c instanceof JTextField && ((JTextField) c).getText().equals("")) 
				{
					JOptionPane.showMessageDialog(null, "All fields must be completed","Error",JOptionPane.WARNING_MESSAGE);
					return false;
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return true;
	}
	
	public boolean PanelIsValid() 
	{
		_panelIsValid = fieldCheck();
		return _panelIsValid;
	}

	private void saveCharacteristic() 
	{
		if (this.PanelIsValid()) 
		{
			_char.setAverageValue(Double.parseDouble(this.txAvg.getText()));
			_char.setName(this.txCharacteristicName.getText());
			_char.setMinimumValue(Double.parseDouble(this.txMinimum.getText()));
			_char.setFirstQuartile(Double.parseDouble(this.txFirstQuartile.getText()));
			_char.setMedianValue(Double.parseDouble(this.txMedian.getText()));
			_char.setThirdQuartile(Double.parseDouble(this.txThirdQuartile.getText()));
			_char.setMaximumValue(Double.parseDouble(this.txMaximum.getText()));
			_char.setScoreWeightValue(Double.parseDouble(this.txWeight.getText()));
		}

		BetterValue betterValue = this.rdHighest.isSelected() ? BetterValue.HIGHEST : BetterValue.LOWEST;
		_char.setBetterValue(betterValue);
	}
	
	public Characteristic getCharacteristic() 
	{
		this.saveCharacteristic();
		return _char;		
	}
	
	public boolean DeleteFlag() 
	{
		return chbxDelete.isSelected();
	}
	
	private void initialize()
	{		
		setBorder(new MatteBorder(0, 0, 1, 0, (Color) new Color(0, 0, 0)));
		setPreferredSize(new Dimension(1060, 56));
		
		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWidths = new int[] {0, 170, 60, 85, 60, 85, 0, 64, 55, 0};
		this.setLayout(gridBagLayout);
		GridBagConstraints c = new GridBagConstraints();
		c.weightx = 1;
		
        rdHighest = new JRadioButton("Highest is Best");
        buttonGroup.add(rdHighest);
        
        btnDelete = new JButton("Delete"); //TODO add trash can image
        btnDelete.addActionListener(e->{
        	this.setVisible(false);
        });
        btnDelete.setVisible(false);        	

        GridBagConstraints gbc_btnDelete = new GridBagConstraints();
        gbc_btnDelete.anchor = GridBagConstraints.WEST; 
        gbc_btnDelete.insets = new Insets(0, 0, 5, 5);
        gbc_btnDelete.gridx = 0;
        gbc_btnDelete.gridy = 0;
        this.add(btnDelete, gbc_btnDelete);

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
        
        rdLowest = new JRadioButton("Lowest is Best");
        buttonGroup.add(rdLowest);
        GridBagConstraints gbc_rdLowest_1 = new GridBagConstraints();
        gbc_rdLowest_1.insets = new Insets(0, 0, 5, 0);
        gbc_rdLowest_1.gridx = 9;
        gbc_rdLowest_1.gridy = 0;
        this.add(rdLowest, gbc_rdLowest_1);
        
        chbxDelete = new JCheckBox("");
        GridBagConstraints gbc_chbxDelete = new GridBagConstraints();
        gbc_chbxDelete.insets = new Insets(0, 0, 0, 5);
        gbc_chbxDelete.gridx = 0;
        gbc_chbxDelete.gridy = 1;
        add(chbxDelete, gbc_chbxDelete);
        
        txCharacteristicName = new JTextField();
        txCharacteristicName.setColumns(20);
        GridBagConstraints gbc_txCharacteristicName = new GridBagConstraints();
        gbc_txCharacteristicName.fill = GridBagConstraints.HORIZONTAL;
        gbc_txCharacteristicName.weightx = 0.5;
        gbc_txCharacteristicName.insets = new Insets(0, 0, 0, 5);
        gbc_txCharacteristicName.gridx = 1;
        gbc_txCharacteristicName.gridy = 1;
        this.add(txCharacteristicName, gbc_txCharacteristicName);
        
        txMinimum = new JTextField();
        txMinimum.setColumns(10);
        GridBagConstraints gbc_txMinimum = new GridBagConstraints();
        gbc_txMinimum.fill = GridBagConstraints.HORIZONTAL;
        gbc_txMinimum.weightx = 0.5;
        gbc_txMinimum.insets = new Insets(0, 0, 0, 5);
        gbc_txMinimum.gridx = 2;
        gbc_txMinimum.gridy = 1;
        this.add(txMinimum, gbc_txMinimum);
        
        txFirstQuartile = new JTextField();
        txFirstQuartile.setColumns(10);
        GridBagConstraints gbc_txFirstQuartile = new GridBagConstraints();
        gbc_txFirstQuartile.fill = GridBagConstraints.HORIZONTAL;
        gbc_txFirstQuartile.weightx = 0.5;
        gbc_txFirstQuartile.insets = new Insets(0, 0, 0, 5);
        gbc_txFirstQuartile.gridx = 3;
        gbc_txFirstQuartile.gridy = 1;
        this.add(txFirstQuartile, gbc_txFirstQuartile);
        
        txMedian = new JTextField();
        txMedian.setColumns(10);
        GridBagConstraints gbc_txMedian = new GridBagConstraints();
        gbc_txMedian.fill = GridBagConstraints.HORIZONTAL;
        gbc_txMedian.weightx = 0.5;
        gbc_txMedian.insets = new Insets(0, 0, 0, 5);
        gbc_txMedian.gridx = 4;
        gbc_txMedian.gridy = 1;
        this.add(txMedian, gbc_txMedian);
        
        txThirdQuartile = new JTextField();
        txThirdQuartile.setColumns(10);
        GridBagConstraints gbc_txThirdQuartile = new GridBagConstraints();
        gbc_txThirdQuartile.fill = GridBagConstraints.HORIZONTAL;
        gbc_txThirdQuartile.weightx = 0.5;
        gbc_txThirdQuartile.insets = new Insets(0, 0, 0, 5);
        gbc_txThirdQuartile.gridx = 5;
        gbc_txThirdQuartile.gridy = 1;
        this.add(txThirdQuartile, gbc_txThirdQuartile);
        
        txMaximum = new JTextField();
        txMaximum.setColumns(10);
        GridBagConstraints gbc_txMaximum = new GridBagConstraints();
        gbc_txMaximum.fill = GridBagConstraints.HORIZONTAL;
        gbc_txMaximum.weightx = 0.5;
        gbc_txMaximum.insets = new Insets(0, 0, 0, 5);
        gbc_txMaximum.gridx = 6;
        gbc_txMaximum.gridy = 1;
        this.add(txMaximum, gbc_txMaximum);
        
        txAvg = new JTextField();
        txAvg.setColumns(10);
        GridBagConstraints gbc_txAvg = new GridBagConstraints();
        gbc_txAvg.fill = GridBagConstraints.HORIZONTAL;
        gbc_txAvg.weightx = 0.5;
        gbc_txAvg.insets = new Insets(0, 0, 0, 5);
        gbc_txAvg.gridx = 7;
        gbc_txAvg.gridy = 1;
        this.add(txAvg, gbc_txAvg);
        
        txWeight = new JTextField();
        txWeight.setColumns(10);
        GridBagConstraints gbc_txWeight = new GridBagConstraints();
        gbc_txWeight.fill = GridBagConstraints.HORIZONTAL;
        gbc_txWeight.weightx = 0.5;
        gbc_txWeight.insets = new Insets(0, 0, 0, 5);
        gbc_txWeight.gridx = 8;
        gbc_txWeight.gridy = 1;
        this.add(txWeight, gbc_txWeight);
        GridBagConstraints gbc_rdHighest = new GridBagConstraints();
        gbc_rdHighest.gridx = 9;
        gbc_rdHighest.gridy = 1;
        this.add(rdHighest, gbc_rdHighest);
        
        this.rdHighest.setSelected(true);
        this.rdLowest.setSelected(false);
    }
}
