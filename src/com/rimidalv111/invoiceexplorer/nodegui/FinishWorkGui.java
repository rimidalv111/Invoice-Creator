package com.rimidalv111.invoiceexplorer.nodegui;

import java.awt.GridLayout;

import javax.swing.JLabel;
import javax.swing.JPanel;

import com.rimidalv111.invoiceexplorer.TabJPanel;

public class FinishWorkGui extends JPanel
{
	private static final long serialVersionUID = 1L;

	private TabJPanel tabJPanel;

	JLabel lblFinishWorkGui;

	public FinishWorkGui(TabJPanel tjp)
	{
		super(new GridLayout(1, 0));
		tabJPanel = tjp;

		lblFinishWorkGui = new JLabel("Finish Work GUI"); //make component
		lblFinishWorkGui.setBounds(10, 10, 66, 66);
		lblFinishWorkGui.setVisible(true);

	}

	public void enableGui()
	{
		tabJPanel.add(lblFinishWorkGui); //add it to the panel cuz otherwise it will take up space

		tabJPanel.validateRepaint();
	}

	public void disableGui()
	{
		tabJPanel.remove(lblFinishWorkGui); //remove it cuz otherwise it will take up space

		tabJPanel.validateRepaint();
	}
}
