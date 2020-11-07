package com.rimidalv111.invoiceexplorer.nodegui;

import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;

import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.MatteBorder;
import javax.swing.border.TitledBorder;

import com.rimidalv111.invoiceexplorer.TabJPanel;
import com.rimidalv111.util.customclasses.CustomJPanel;

public class FramingGui extends JPanel
{
	private static final long serialVersionUID = 1L;

	private TabJPanel tabJPanel;

	//start invoiceFormat
	private JPanel invoiceFormatPanel;
	private JLabel invoiceFormatLabel;
	private JComboBox invoiceFormatSentence;

	//end invoiceFormat

	public FramingGui(TabJPanel tjp)
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
