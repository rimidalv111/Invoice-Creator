package com.rimidalv111.invoiceexplorer.nodegui;

import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.text.DecimalFormat;
import java.util.HashMap;

import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JSeparator;
import javax.swing.JTextField;
import javax.swing.JToggleButton;
import javax.swing.SwingConstants;
import javax.swing.border.MatteBorder;
import javax.swing.border.TitledBorder;

import com.rimidalv111.invoiceexplorer.TabJPanel;
import com.rimidalv111.util.TotalArrayNode;
import com.rimidalv111.util.customclasses.CustomJPanel;

public class DemolitionGui extends JPanel
{
	private static final long serialVersionUID = 1L;

	private TabJPanel tabJPanel;

	//start price button selection panel
	private CustomJPanel choosePriceTypePanel;
	private JToggleButton tglbtnSetPrice;
	private JToggleButton tglbtnPerSquareFeet;
	private JToggleButton tglbtnCustom;
	//end price button selection panel

	//start amount/sq.ft. panel
	private CustomJPanel perSquarePanel;
	private JTextField setPriceAmount;
	private JTextField setPriceSquareFeet;
	private JLabel lblSubtotal;
	private JLabel lblAmount;
	private JLabel lblSquareFeet;
	private JLabel labelMoneySign;
	private JLabel lblSqFt;
	private JSeparator separator;
	//end amount/sq.ft. panel

	//start invoiceFormat
	private JPanel invoiceFormatPanel;
	private Boolean invoiceFormatPanelExpanded;
	private JComboBox<String> invoiceFormatSentence;
	private JTextField invoiceCustomSentence;
	//end invoiceFormat

	//start extra selections (concrete, hazardous)
	private JPanel extrasPanel;
	private JRadioButton rdbtnConcrete;
	private Double concretePrice;
	private JRadioButton rdbtnHazardous;
	private Double hazardousPrice;
	private JSeparator extraPanelSeparator;
	private JLabel lblExtraSubtotal;

	//end extra selections

	//-------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------//	
	public DemolitionGui(TabJPanel tjp)
	{
		super(new GridLayout(1, 0));
		tabJPanel = tjp;

		choosePriceTypePanel = new CustomJPanel();
		choosePriceTypePanel.setBounds(10, 10, 296, 87);
		choosePriceTypePanel.setLayout(null);
		TitledBorder ctb = new TitledBorder(new MatteBorder(1, 1, 1, 1, Color.GRAY), "Pricing");
		choosePriceTypePanel.setBorder(ctb);

		tglbtnSetPrice = new JToggleButton("Set Price");
		tglbtnPerSquareFeet = new JToggleButton("Per Square Feet");

		tglbtnSetPrice.addActionListener(new ActionListener()
		{
			@Override
			public void actionPerformed(ActionEvent e)
			{
				setSelected(0);
			}
		});

		tglbtnSetPrice.setFont(new Font("Tahoma", Font.PLAIN, 14));
		tglbtnSetPrice.setBounds(10, 23, 132, 23);
		choosePriceTypePanel.add(tglbtnSetPrice);

		tglbtnPerSquareFeet.addActionListener(new ActionListener()
		{
			@Override
			public void actionPerformed(ActionEvent e)
			{
				setSelected(1);
			}
		});
		tglbtnPerSquareFeet.setFont(new Font("Tahoma", Font.PLAIN, 14));
		tglbtnPerSquareFeet.setBounds(147, 23, 139, 23);
		choosePriceTypePanel.add(tglbtnPerSquareFeet);

		tglbtnCustom = new JToggleButton("Custom");
		tglbtnCustom.setFont(new Font("Tahoma", Font.PLAIN, 14));
		tglbtnCustom.setBounds(10, 51, 276, 23);
		choosePriceTypePanel.add(tglbtnCustom);

		tglbtnCustom.addActionListener(new ActionListener()
		{
			@Override
			public void actionPerformed(ActionEvent e)
			{
				setSelected(2);
			}
		});
		//-------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------//		

		perSquarePanel = new CustomJPanel();
		perSquarePanel.setBorder(new MatteBorder(1, 1, 1, 1, Color.GRAY));
		perSquarePanel.setBounds(10, 107, 296, 113);
		perSquarePanel.setLayout(null);

		setPriceAmount = new JTextField(12);
		setPriceAmount.addKeyListener(new KeyAdapter()
		{
			@Override
			public void keyTyped(KeyEvent e)
			{

				if(Character.isDigit(e.getKeyChar()) || Character.toString(e.getKeyChar()).equalsIgnoreCase(".") || (e.getKeyChar() == KeyEvent.VK_BACK_SPACE) || (e.getKeyChar() == KeyEvent.VK_DELETE))
				{
					if((setPriceAmount.getText().length() >= 9) || (setPriceAmount.getText().contains(".") && Character.toString(e.getKeyChar()).equalsIgnoreCase(".")))
					{
						Toolkit.getDefaultToolkit().beep();
						e.consume();
					}
				} else
				{
					Toolkit.getDefaultToolkit().beep();
					e.consume();
				}
			}

			@Override
			public void keyReleased(KeyEvent e)
			{
				if((e.getKeyChar() != KeyEvent.VK_BACK_SPACE) || (e.getKeyChar() != KeyEvent.VK_DELETE)) // dont need to capture spaces
				{
					if(tabJPanel.getInvoiceExplorer().getUtil().isDouble(setPriceAmount.getText()))
					{
						calculateShowSubtotal(setPriceAmount.getText(), setPriceSquareFeet.isVisible() ? setPriceSquareFeet.getText() : "1");
					}
				}
			}
		});
		setPriceAmount.setHorizontalAlignment(SwingConstants.RIGHT);
		setPriceAmount.setFont(new Font("Tahoma", Font.PLAIN, 14));
		setPriceAmount.setBounds(143, 11, 86, 23);
		perSquarePanel.add(setPriceAmount);
		setPriceAmount.setColumns(10);

		setPriceSquareFeet = new JTextField(12);
		setPriceSquareFeet.addKeyListener(new KeyAdapter()
		{
			@Override
			public void keyTyped(KeyEvent e)
			{
				if(Character.isDigit(e.getKeyChar()) || Character.toString(e.getKeyChar()).equalsIgnoreCase(".") || (e.getKeyChar() == KeyEvent.VK_BACK_SPACE) || (e.getKeyChar() == KeyEvent.VK_DELETE))
				{
					if((setPriceSquareFeet.getText().length() >= 9) || (setPriceSquareFeet.getText().contains(".") && Character.toString(e.getKeyChar()).equalsIgnoreCase(".")))
					{
						Toolkit.getDefaultToolkit().beep();
						e.consume();
					}
				} else
				{
					Toolkit.getDefaultToolkit().beep();
					e.consume();
				}
			}

			@Override
			public void keyReleased(KeyEvent e)
			{
				if((e.getKeyChar() != KeyEvent.VK_BACK_SPACE) || (e.getKeyChar() != KeyEvent.VK_DELETE)) // dont need to capture spaces
				{
					if(tabJPanel.getInvoiceExplorer().getUtil().isDouble(setPriceSquareFeet.getText()))
					{
						calculateShowSubtotal(setPriceAmount.getText(), setPriceSquareFeet.getText());
					}
				}
			}
		});
		setPriceSquareFeet.setHorizontalAlignment(SwingConstants.RIGHT);
		setPriceSquareFeet.setFont(new Font("Tahoma", Font.PLAIN, 14));
		setPriceSquareFeet.setBounds(143, 45, 86, 23);
		perSquarePanel.add(setPriceSquareFeet);
		setPriceSquareFeet.setColumns(10);

		lblAmount = new JLabel("Amount");
		lblAmount.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblAmount.setBounds(58, 14, 75, 17);
		perSquarePanel.add(lblAmount);

		lblSquareFeet = new JLabel("Square Feet");
		lblSquareFeet.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblSquareFeet.setBounds(58, 46, 75, 20);
		perSquarePanel.add(lblSquareFeet);

		labelMoneySign = new JLabel("$");
		labelMoneySign.setBounds(132, 10, 27, 25);
		perSquarePanel.add(labelMoneySign);
		labelMoneySign.setFont(new Font("Tahoma", Font.PLAIN, 14));

		lblSqFt = new JLabel("sq. ft");
		lblSqFt.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblSqFt.setBounds(229, 47, 46, 23);
		perSquarePanel.add(lblSqFt);

		separator = new JSeparator();
		separator.setBounds(132, 77, 107, 2);
		perSquarePanel.add(separator);

		lblSubtotal = new JLabel("$0");
		lblSubtotal.setHorizontalAlignment(SwingConstants.RIGHT);
		lblSubtotal.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblSubtotal.setBounds(58, 83, 171, 28);
		lblSubtotal.setVisible(false);
		perSquarePanel.add(lblSubtotal);
		//end setting default buttons

		//-------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------//

		// start invoiceformat	
		invoiceFormatPanel = new CustomJPanel();
		TitledBorder ifpb = new TitledBorder(new MatteBorder(1, 1, 1, 1, Color.GRAY), "Invoice Format");
		invoiceFormatPanel.setBorder(ifpb);
		invoiceFormatPanel.setBounds(310, 10, 290, 53); // over x, down y, width, height
		invoiceFormatPanel.setLayout(null);

		invoiceFormatPanelExpanded = false;

		invoiceFormatSentence = new JComboBox<String>();
		invoiceFormatSentence.setEditable(false);

		invoiceFormatSentence.setBounds(10, 25, 270, 20);
		invoiceFormatSentence.addItem("Total cost for Demolition");
		invoiceFormatSentence.addItem("Demolition cost for Bathroom");
		invoiceFormatSentence.addItem("Demolition cost for Kitchen");
		invoiceFormatSentence.addItem("Demolition cost for Living Room");
		invoiceFormatSentence.addItem("Demolition cost for Dining Room");
		invoiceFormatSentence.addItem("Demolition cost for Bedroom");
		invoiceFormatSentence.addItem("Demolition cost for Basement");
		
		invoiceFormatSentence.addItem("Custom");

		invoiceFormatSentence.addActionListener(new ActionListener()
		{
			@Override
			public void actionPerformed(ActionEvent e)
			{
				if(e.getActionCommand().toString().equals("comboBoxChanged"))
				{
					if(invoiceFormatSentence.getItemAt(invoiceFormatSentence.getSelectedIndex()).toString().equals("Custom"))
					{
						if(!invoiceFormatPanelExpanded)
						{
							expandInvoiceFormatPanel();
						}
					} else
					{
						if(invoiceFormatPanelExpanded)
						{
							contractInvoiceFormatPanel();
						}
					}
					TotalArrayNode tan = tabJPanel.getTotalArray().getArrayNode("Demolition");
					if(tan != null)
					{
						String la = invoiceFormatSentence.getItemAt(invoiceFormatSentence.getSelectedIndex()).toString();
						if(la.equalsIgnoreCase("Custom"))
						{
							la = invoiceCustomSentence.getText();
						}
						tan.setArrayString(la);
						tan.setTotalAmount(lblSubtotal.getText().replace("$", ""));
						tabJPanel.getTotalArray().paintArray();
					} else
					{
						String la = invoiceFormatSentence.getItemAt(invoiceFormatSentence.getSelectedIndex()).toString();
						if(la.equalsIgnoreCase("Custom"))
						{
							la = invoiceCustomSentence.getText();
						}
						tabJPanel.getTotalArray().addToArray("Demolition", la, lblSubtotal.getText().replace("$", ""), new HashMap<String, String>());
					}
				}
			}
		});

		invoiceCustomSentence = new JTextField("Demolition");
		invoiceCustomSentence.setBounds(10, 50, 250, 20);
		invoiceCustomSentence.setVisible(false);
		invoiceCustomSentence.addKeyListener(new KeyAdapter()
		{
			@Override
			public void keyReleased(KeyEvent e)
			{
				TotalArrayNode tan = tabJPanel.getTotalArray().getArrayNode("Demolition");
				if(tan != null)
				{
					tan.setArrayString(invoiceCustomSentence.getText().toString());
					tabJPanel.getTotalArray().paintArray();
				}
			}
		});

		invoiceFormatPanel.add(invoiceCustomSentence);
		invoiceFormatPanel.add(invoiceFormatSentence);
		// end invoice format

		//-------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------//

		//start extras panel (concrete and hazardous)
		extrasPanel = new CustomJPanel();
		extrasPanel.setBorder(new MatteBorder(1, 1, 1, 1, Color.GRAY));
		extrasPanel.setBounds(10, 230, 296, 113);
		extrasPanel.setLayout(null);

		//Concrete Radio Button	
		concretePrice = 4.52;
		rdbtnConcrete = new JRadioButton("Concrete:         + $" + new DecimalFormat("##.##").format(concretePrice) + " a sq. ft.");
		rdbtnConcrete.setBounds(10, 10, 250, 18);
		rdbtnConcrete.setFont(new Font("Tahoma", Font.PLAIN, 14));
		rdbtnConcrete.setOpaque(false);
		rdbtnConcrete.setFocusPainted(false);
		rdbtnConcrete.addActionListener(new ActionListener()
		{
			@Override
			public void actionPerformed(ActionEvent e)
			{
				calculateExtraSubtotal();
			}
		});
		extrasPanel.add(rdbtnConcrete);

		//Hazardous Radio Button
		hazardousPrice = 5.54;
		rdbtnHazardous = new JRadioButton("Hazardous:       + $" + new DecimalFormat("##.##").format(hazardousPrice) + " a sq. ft.");
		rdbtnHazardous.setBounds(10, 38, 250, 18);
		rdbtnHazardous.setFont(new Font("Tahoma", Font.PLAIN, 14));
		rdbtnHazardous.setOpaque(false);
		rdbtnHazardous.setFocusPainted(false);
		rdbtnHazardous.addActionListener(new ActionListener()
		{
			@Override
			public void actionPerformed(ActionEvent e)
			{
				calculateExtraSubtotal();
			}
		});
		extrasPanel.add(rdbtnHazardous);

		extraPanelSeparator = new JSeparator();
		extraPanelSeparator.setBounds(132, 77, 107, 2);
		extrasPanel.add(extraPanelSeparator);

		lblExtraSubtotal = new JLabel("$0");
		lblExtraSubtotal.setHorizontalAlignment(SwingConstants.RIGHT);
		lblExtraSubtotal.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblExtraSubtotal.setBounds(58, 83, 171, 28);
		lblExtraSubtotal.setVisible(false);
		extrasPanel.add(lblExtraSubtotal);
		//end extras panel

		//-------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------//	

		//Start setting the default selected button and variables set it last so everything is loaded first
		setSelected(0);
	}

	public void enableGui()
	{
		tabJPanel.add(choosePriceTypePanel);
		tabJPanel.add(perSquarePanel);
		tabJPanel.add(invoiceFormatPanel);
		tabJPanel.add(extrasPanel);
		tabJPanel.getTotalArray().enableTotalLabel();
		tabJPanel.validateRepaint();
	}

	//-------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------//
	public void disableGui()
	{
		tabJPanel.remove(choosePriceTypePanel);
		tabJPanel.remove(perSquarePanel);
		tabJPanel.remove(invoiceFormatPanel);
		tabJPanel.remove(extrasPanel);
		tabJPanel.getTotalArray().disableTotalLabel();
		tabJPanel.validateRepaint();
	}

	//-------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------//
	public void setSelected(int i)
	{
		if(i == 0)
		{
			setPriceAmount.setText("");
			setPriceAmount.setFocusable(true);
			setPriceAmount.setBackground(Color.WHITE);
			setPriceSquareFeet.setText("");
			tglbtnSetPrice.setSelected(true);
			tglbtnPerSquareFeet.setSelected(false);
			tglbtnCustom.setSelected(false);
			setPriceSquareFeet.setVisible(false);
			lblSquareFeet.setVisible(false);
			lblSqFt.setVisible(false);
			rdbtnConcrete.setSelected(false);
			rdbtnConcrete.setEnabled(false);
			rdbtnHazardous.setSelected(false);
			rdbtnHazardous.setEnabled(false);
			if((setPriceAmount != null) && (lblSubtotal != null))
			{
				calculateShowSubtotal(setPriceAmount.getText(), "1");
			}
			if(lblExtraSubtotal != null)
			{
				calculateExtraSubtotal();
			}
		}
		if(i == 1)
		{
			setPriceAmount.setText("2.34");
			setPriceAmount.setFocusable(false);
			setPriceAmount.setBackground(Color.LIGHT_GRAY);
			setPriceSquareFeet.setText("");
			tglbtnSetPrice.setSelected(false);
			tglbtnPerSquareFeet.setSelected(true);
			tglbtnCustom.setSelected(false);
			setPriceSquareFeet.setVisible(true);
			lblSquareFeet.setVisible(true);
			lblSqFt.setVisible(true);
			rdbtnConcrete.setEnabled(true);
			rdbtnHazardous.setEnabled(true);
			if((setPriceAmount != null) && (setPriceSquareFeet != null) && (lblSubtotal != null))
			{
				calculateShowSubtotal(setPriceAmount.getText(), setPriceSquareFeet.getText());
			}
			if(lblExtraSubtotal != null)
			{
				calculateExtraSubtotal();
			}
		}
		if(i == 2)
		{
			setPriceAmount.setText("");
			setPriceAmount.setFocusable(true);
			setPriceAmount.setBackground(Color.WHITE);
			setPriceSquareFeet.setText("");
			tglbtnSetPrice.setSelected(false);
			tglbtnPerSquareFeet.setSelected(false);
			tglbtnCustom.setSelected(true);
			setPriceSquareFeet.setVisible(true);
			lblSquareFeet.setVisible(true);
			lblSqFt.setVisible(true);
			rdbtnConcrete.setEnabled(true);
			rdbtnHazardous.setEnabled(true);
			if((setPriceAmount != null) && (setPriceSquareFeet != null) && (lblSubtotal != null))
			{
				calculateShowSubtotal(setPriceAmount.getText(), setPriceSquareFeet.getText());
			}
			if(lblExtraSubtotal != null)
			{
				calculateExtraSubtotal();
			}
		}
	}

	//-------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------//
	public void calculateShowSubtotal(String spa, String spqf)
	{
		if((!spa.isEmpty() && !spqf.isEmpty()))
		{
			double price = Double.parseDouble(spa);
			double squareFt = Double.parseDouble(spqf);
			double subTotal = price * squareFt;
			lblSubtotal.setText("$ " + new DecimalFormat("##.##").format(subTotal));
			lblSubtotal.setVisible(true);
			calculateExtraSubtotal();
		} else
		{
			lblSubtotal.setVisible(false);
		}
	}

	public void calculateExtraSubtotal()
	{
		if(lblSubtotal.isVisible())
		{
			double currentPrice = Double.parseDouble(lblSubtotal.getText().replace("$", ""));
			double newPrice = 0;

			double extraForConcrete = 0;
			double extraForHazardous = 0;

			if(currentPrice > 0)
			{
				double squareFeet = 1;
				if(tabJPanel.getInvoiceExplorer().getUtil().isDouble(setPriceSquareFeet.getText()))
				{
					squareFeet = Double.parseDouble(setPriceSquareFeet.getText());
				}
				if(rdbtnConcrete.isSelected())
				{
					extraForConcrete = concretePrice * squareFeet;
				}
				if(rdbtnHazardous.isSelected())
				{
					extraForHazardous = hazardousPrice * squareFeet;
				}
				newPrice = currentPrice + extraForConcrete + extraForHazardous;
				String newPriceString = "$ " + new DecimalFormat("##.##").format(newPrice);
				lblExtraSubtotal.setText(newPriceString);
				lblExtraSubtotal.setVisible(true);
			}

			TotalArrayNode tan = tabJPanel.getTotalArray().getArrayNode("Demolition");
			if(tan != null)
			{
				String la = invoiceFormatSentence.getItemAt(invoiceFormatSentence.getSelectedIndex()).toString();
				if(la.equalsIgnoreCase("Custom"))
				{
					la = invoiceCustomSentence.getText();
				}
				tan.setArrayString(la);

				if(rdbtnConcrete.isSelected() || rdbtnHazardous.isSelected())
				{
					tan.setTotalAmount(new DecimalFormat("##.##").format(currentPrice));
				} else
				{
					tan.setTotalAmount(new DecimalFormat("##.##").format(newPrice));
				}

				if(rdbtnConcrete.isSelected())
				{
					tan.getExtraLines().put("Concrete", new DecimalFormat("##.##").format(extraForConcrete));
				} else
				{
					tan.getExtraLines().remove("Concrete");
				}
				if(rdbtnHazardous.isSelected())
				{
					tan.getExtraLines().put("Hazardous", new DecimalFormat("##.##").format(extraForHazardous));
				} else
				{
					tan.getExtraLines().remove("Hazardous");
				}

				tabJPanel.getTotalArray().paintArray();
			} else
			{
				String la = invoiceFormatSentence.getItemAt(invoiceFormatSentence.getSelectedIndex()).toString();
				if(la.equalsIgnoreCase("Custom"))
				{
					la = invoiceCustomSentence.getText();
				}
				tabJPanel.getTotalArray().addToArray("Demolition", la, new DecimalFormat("##.##").format(newPrice), new HashMap<String, String>());
			}
		} else
		{
			lblExtraSubtotal.setVisible(false);
		}
	}

	//-------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------//
	public void expandInvoiceFormatPanel()
	{
		invoiceFormatPanelExpanded = true;
		invoiceFormatPanel.setBounds(310, 10, 290, 80);
		invoiceFormatPanel.repaint();
		invoiceCustomSentence.setVisible(true);
		invoiceCustomSentence.grabFocus();
		invoiceCustomSentence.repaint();
	}

	//-------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------//
	public void contractInvoiceFormatPanel()
	{
		invoiceFormatPanelExpanded = false;
		invoiceFormatPanel.setBounds(310, 10, 290, 53);
		invoiceFormatPanel.repaint();
		invoiceCustomSentence.setVisible(false);
		invoiceCustomSentence.repaint();
	}

	/**
     * @return the tabJPanel
     */
    public TabJPanel getTabJPanel()
    {
    	return tabJPanel;
    }

	/**
     * @param tabJPanel the tabJPanel to set
     */
    public void setTabJPanel(TabJPanel tabJPanel)
    {
    	this.tabJPanel = tabJPanel;
    }

	/**
     * @return the choosePriceTypePanel
     */
    public CustomJPanel getChoosePriceTypePanel()
    {
    	return choosePriceTypePanel;
    }

	/**
     * @param choosePriceTypePanel the choosePriceTypePanel to set
     */
    public void setChoosePriceTypePanel(CustomJPanel choosePriceTypePanel)
    {
    	this.choosePriceTypePanel = choosePriceTypePanel;
    }

	/**
     * @return the tglbtnSetPrice
     */
    public JToggleButton getTglbtnSetPrice()
    {
    	return tglbtnSetPrice;
    }

	/**
     * @param tglbtnSetPrice the tglbtnSetPrice to set
     */
    public void setTglbtnSetPrice(JToggleButton tglbtnSetPrice)
    {
    	this.tglbtnSetPrice = tglbtnSetPrice;
    }

	/**
     * @return the tglbtnPerSquareFeet
     */
    public JToggleButton getTglbtnPerSquareFeet()
    {
    	return tglbtnPerSquareFeet;
    }

	/**
     * @param tglbtnPerSquareFeet the tglbtnPerSquareFeet to set
     */
    public void setTglbtnPerSquareFeet(JToggleButton tglbtnPerSquareFeet)
    {
    	this.tglbtnPerSquareFeet = tglbtnPerSquareFeet;
    }

	/**
     * @return the tglbtnCustom
     */
    public JToggleButton getTglbtnCustom()
    {
    	return tglbtnCustom;
    }

	/**
     * @param tglbtnCustom the tglbtnCustom to set
     */
    public void setTglbtnCustom(JToggleButton tglbtnCustom)
    {
    	this.tglbtnCustom = tglbtnCustom;
    }

	/**
     * @return the perSquarePanel
     */
    public CustomJPanel getPerSquarePanel()
    {
    	return perSquarePanel;
    }

	/**
     * @param perSquarePanel the perSquarePanel to set
     */
    public void setPerSquarePanel(CustomJPanel perSquarePanel)
    {
    	this.perSquarePanel = perSquarePanel;
    }

	/**
     * @return the setPriceAmount
     */
    public JTextField getSetPriceAmount()
    {
    	return setPriceAmount;
    }

	/**
     * @param setPriceAmount the setPriceAmount to set
     */
    public void setSetPriceAmount(JTextField setPriceAmount)
    {
    	this.setPriceAmount = setPriceAmount;
    }

	/**
     * @return the setPriceSquareFeet
     */
    public JTextField getSetPriceSquareFeet()
    {
    	return setPriceSquareFeet;
    }

	/**
     * @param setPriceSquareFeet the setPriceSquareFeet to set
     */
    public void setSetPriceSquareFeet(JTextField setPriceSquareFeet)
    {
    	this.setPriceSquareFeet = setPriceSquareFeet;
    }

	/**
     * @return the lblSubtotal
     */
    public JLabel getLblSubtotal()
    {
    	return lblSubtotal;
    }

	/**
     * @param lblSubtotal the lblSubtotal to set
     */
    public void setLblSubtotal(JLabel lblSubtotal)
    {
    	this.lblSubtotal = lblSubtotal;
    }

	/**
     * @return the lblAmount
     */
    public JLabel getLblAmount()
    {
    	return lblAmount;
    }

	/**
     * @param lblAmount the lblAmount to set
     */
    public void setLblAmount(JLabel lblAmount)
    {
    	this.lblAmount = lblAmount;
    }

	/**
     * @return the lblSquareFeet
     */
    public JLabel getLblSquareFeet()
    {
    	return lblSquareFeet;
    }

	/**
     * @param lblSquareFeet the lblSquareFeet to set
     */
    public void setLblSquareFeet(JLabel lblSquareFeet)
    {
    	this.lblSquareFeet = lblSquareFeet;
    }

	/**
     * @return the labelMoneySign
     */
    public JLabel getLabelMoneySign()
    {
    	return labelMoneySign;
    }

	/**
     * @param labelMoneySign the labelMoneySign to set
     */
    public void setLabelMoneySign(JLabel labelMoneySign)
    {
    	this.labelMoneySign = labelMoneySign;
    }

	/**
     * @return the lblSqFt
     */
    public JLabel getLblSqFt()
    {
    	return lblSqFt;
    }

	/**
     * @param lblSqFt the lblSqFt to set
     */
    public void setLblSqFt(JLabel lblSqFt)
    {
    	this.lblSqFt = lblSqFt;
    }

	/**
     * @return the separator
     */
    public JSeparator getSeparator()
    {
    	return separator;
    }

	/**
     * @param separator the separator to set
     */
    public void setSeparator(JSeparator separator)
    {
    	this.separator = separator;
    }

	/**
     * @return the invoiceFormatPanel
     */
    public JPanel getInvoiceFormatPanel()
    {
    	return invoiceFormatPanel;
    }

	/**
     * @param invoiceFormatPanel the invoiceFormatPanel to set
     */
    public void setInvoiceFormatPanel(JPanel invoiceFormatPanel)
    {
    	this.invoiceFormatPanel = invoiceFormatPanel;
    }

	/**
     * @return the invoiceFormatPanelExpanded
     */
    public Boolean getInvoiceFormatPanelExpanded()
    {
    	return invoiceFormatPanelExpanded;
    }

	/**
     * @param invoiceFormatPanelExpanded the invoiceFormatPanelExpanded to set
     */
    public void setInvoiceFormatPanelExpanded(Boolean invoiceFormatPanelExpanded)
    {
    	this.invoiceFormatPanelExpanded = invoiceFormatPanelExpanded;
    }

	/**
     * @return the invoiceFormatSentence
     */
    public JComboBox<String> getInvoiceFormatSentence()
    {
    	return invoiceFormatSentence;
    }

	/**
     * @param invoiceFormatSentence the invoiceFormatSentence to set
     */
    public void setInvoiceFormatSentence(JComboBox<String> invoiceFormatSentence)
    {
    	this.invoiceFormatSentence = invoiceFormatSentence;
    }

	/**
     * @return the invoiceCustomSentence
     */
    public JTextField getInvoiceCustomSentence()
    {
    	return invoiceCustomSentence;
    }

	/**
     * @param invoiceCustomSentence the invoiceCustomSentence to set
     */
    public void setInvoiceCustomSentence(JTextField invoiceCustomSentence)
    {
    	this.invoiceCustomSentence = invoiceCustomSentence;
    }

	/**
     * @return the extrasPanel
     */
    public JPanel getExtrasPanel()
    {
    	return extrasPanel;
    }

	/**
     * @param extrasPanel the extrasPanel to set
     */
    public void setExtrasPanel(JPanel extrasPanel)
    {
    	this.extrasPanel = extrasPanel;
    }

	/**
     * @return the rdbtnConcrete
     */
    public JRadioButton getRdbtnConcrete()
    {
    	return rdbtnConcrete;
    }

	/**
     * @param rdbtnConcrete the rdbtnConcrete to set
     */
    public void setRdbtnConcrete(JRadioButton rdbtnConcrete)
    {
    	this.rdbtnConcrete = rdbtnConcrete;
    }

	/**
     * @return the concretePrice
     */
    public Double getConcretePrice()
    {
    	return concretePrice;
    }

	/**
     * @param concretePrice the concretePrice to set
     */
    public void setConcretePrice(Double concretePrice)
    {
    	this.concretePrice = concretePrice;
    }

	/**
     * @return the rdbtnHazardous
     */
    public JRadioButton getRdbtnHazardous()
    {
    	return rdbtnHazardous;
    }

	/**
     * @param rdbtnHazardous the rdbtnHazardous to set
     */
    public void setRdbtnHazardous(JRadioButton rdbtnHazardous)
    {
    	this.rdbtnHazardous = rdbtnHazardous;
    }

	/**
     * @return the hazardousPrice
     */
    public Double getHazardousPrice()
    {
    	return hazardousPrice;
    }

	/**
     * @param hazardousPrice the hazardousPrice to set
     */
    public void setHazardousPrice(Double hazardousPrice)
    {
    	this.hazardousPrice = hazardousPrice;
    }

	/**
     * @return the extraPanelSeparator
     */
    public JSeparator getExtraPanelSeparator()
    {
    	return extraPanelSeparator;
    }

	/**
     * @param extraPanelSeparator the extraPanelSeparator to set
     */
    public void setExtraPanelSeparator(JSeparator extraPanelSeparator)
    {
    	this.extraPanelSeparator = extraPanelSeparator;
    }

	/**
     * @return the lblExtraSubtotal
     */
    public JLabel getLblExtraSubtotal()
    {
    	return lblExtraSubtotal;
    }

	/**
     * @param lblExtraSubtotal the lblExtraSubtotal to set
     */
    public void setLblExtraSubtotal(JLabel lblExtraSubtotal)
    {
    	this.lblExtraSubtotal = lblExtraSubtotal;
    }
}
