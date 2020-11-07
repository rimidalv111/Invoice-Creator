package com.rimidalv111.invoiceexplorer.nodegui;

import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;

import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JSeparator;
import javax.swing.JTextField;
import javax.swing.JToggleButton;
import javax.swing.border.MatteBorder;
import javax.swing.border.TitledBorder;

import com.rimidalv111.invoiceexplorer.TabJPanel;
import com.rimidalv111.util.customclasses.CustomJLabelIncDec;
import com.rimidalv111.util.customclasses.CustomJPanel;

public class SheetRockGui extends JPanel
{
	private static final long serialVersionUID = 1L;

	private TabJPanel tabJPanel;

	//start price button selection panel
		private CustomJPanel choosePriceTypePanel;
		private JToggleButton btnSetPrice;
		private JToggleButton btnTypeOfSheetrock;
		private JToggleButton btnCustom;
		//end price button selection panel

		//start amount panel
		private CustomJPanel amountPanel;
		private JTextField setPriceAmount;
		private JTextField setPriceSquareFeet;
		private JLabel lblSubtotal;
		private JLabel lblAmount;
		private JLabel lblSqFt;
		private JLabel lblDollarSign;
		private JSeparator separator;
		//end amount panel

		//start invoiceFormat
		private JPanel invoiceFormatPanel;
		private Boolean invoiceFormatPanelExpanded;
		private JComboBox<String> invoiceFormatSentence;
		private JTextField invoiceCustomSentence;
		//end invoiceFormat	

		//start selection type of sheetrock
		private JToggleButton btnRegularSheetrock;
		private JToggleButton btnMoistureResistant;
		//end selection type of sheetrock

		//Start sheetrock options panel
		private CustomJPanel insulationOptionsPanel;
		private JLabel lblSheetrockSubtotal;
		//End sheetrock options panel
		
		//Start Moisture Resistant Types
		private CustomJLabelIncDec mrHalfInch1IncDec;
		private JRadioButton mrHalfInch1; //number the sheetrock variables so we can add more types in the future.
		private CustomJLabelIncDec mrHalfInch2IncDec;
		private JRadioButton mrHalfInch2;
		private JSeparator mrHalfInchSeparator;
		//End Moisture Resistant Types

		//Start Regular Sheetrock Types
		private JRadioButton halfInch1; 
		private CustomJLabelIncDec halfInch1IncDec;
		private JRadioButton threeEighths2;
		private CustomJLabelIncDec styroFoam2IncDec;
		private JRadioButton styrofoam3;
		private CustomJLabelIncDec styroFoam3IncDec;
		//End Regular Sheetrock Types

	public SheetRockGui(TabJPanel tjp)
	{
		super(new GridLayout(1, 0));
		tabJPanel = tjp;

		// start invoiceformat	
		invoiceFormatPanel = new CustomJPanel();
		TitledBorder ifpb = new TitledBorder(new MatteBorder(1, 1, 1, 1, Color.GRAY), "Invoice Format");
		invoiceFormatPanel.setBorder(ifpb);
		invoiceFormatPanel.setBounds(310, 10, 290, 53); // over x, down y, width, height
		invoiceFormatPanel.setLayout(null);

		invoiceFormatSentence = new JComboBox();
		invoiceFormatSentence.setBounds(10, 25, 270, 20);
		invoiceFormatSentence.addItem("Total cost of demoltion for job");
		invoiceFormatSentence.addItem("Total cost of demoltion for project");
		invoiceFormatSentence.addItem("Total cost of demoltion for bathroom");
		invoiceFormatSentence.addItem("Total cost of demoltion for kitchen");

		invoiceFormatPanel.add(invoiceFormatSentence);
		// end invoice format
	}

	public void enableGui()
	{
		tabJPanel.add(invoiceFormatPanel);

		tabJPanel.validateRepaint();
	}

	public void disableGui()
	{
		tabJPanel.remove(invoiceFormatPanel);

		tabJPanel.validateRepaint();
	}
}
