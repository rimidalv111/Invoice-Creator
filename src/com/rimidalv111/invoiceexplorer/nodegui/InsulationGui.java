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
import com.rimidalv111.util.customclasses.CustomJLabelIncDec;
import com.rimidalv111.util.customclasses.CustomJPanel;

public class InsulationGui extends JPanel
{
	private static final long serialVersionUID = 1L;

	private TabJPanel tabJPanel;

	//start price button selection panel
	private CustomJPanel choosePriceTypePanel;
	private JToggleButton btnSetPrice;
	private JToggleButton btnTypeOfInsulation;
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

	//start selection type of insulation
	private JToggleButton btnSprayFoam;
	private JToggleButton btnStyrofoam;
	private JToggleButton btnBlowIn;
	private JToggleButton btnRolls;
	//end selection tye of insulation

	//Start insulation options panel
	private CustomJPanel insulationOptionsPanel;
	//End insulation options panel

	//Spray Foam Types
	private CustomJLabelIncDec sprayFoam1IncDec;
	private JRadioButton sprayFoam1; //number the spray foam variables so we can add more types in the future.
	private CustomJLabelIncDec sprayFoam2IncDec;
	private JRadioButton sprayFoam2;
	private JSeparator sprayFoamSeparator;
	//end Foam Types

	//Styrofoam Types
	private JRadioButton styrofoam1; //Expanded Polystryene Foam Board Insulation 
	private CustomJLabelIncDec styroFoam1IncDec;
	private JRadioButton styrofoam2;
	private CustomJLabelIncDec styroFoam2IncDec;
	private JRadioButton styrofoam3;
	private CustomJLabelIncDec styroFoam3IncDec;
	private JRadioButton styrofoam4;
	private CustomJLabelIncDec styroFoam4IncDec;
	private JRadioButton styrofoam5;
	private CustomJLabelIncDec styroFoam5IncDec;
	private JRadioButton styrofoam6;
	private CustomJLabelIncDec styroFoam6IncDec;
	private JSeparator styroFoamSeparator;

	//Blow In Types
	private JRadioButton blowInFoam1;
	private CustomJLabelIncDec blowInFoam1IncDec;
	private JRadioButton blowInFoam2;
	private CustomJLabelIncDec blowInFoam2IncDec;
	private JSeparator blowInFoamSeparator;

	//Roll Types
	private JRadioButton rollType1;
	private CustomJLabelIncDec rollType1IncDec;
	private JRadioButton rollType2;
	private CustomJLabelIncDec rollType2IncDec;
	private JRadioButton rollType3;
	private CustomJLabelIncDec rollType3IncDec;
	private JRadioButton rollType4;
	private CustomJLabelIncDec rollType4IncDec;
	private JSeparator rollFoamSeparator;

	//end selection type of insulation

	public InsulationGui(TabJPanel tjp)
	{
		super(new GridLayout(1, 0));
		tabJPanel = tjp;

		//start price selection panel
		choosePriceTypePanel = new CustomJPanel();
		choosePriceTypePanel.setBounds(10, 10, 296, 87);
		choosePriceTypePanel.setLayout(null);
		TitledBorder ctb = new TitledBorder(new MatteBorder(1, 1, 1, 1, Color.GRAY), "Pricing");
		choosePriceTypePanel.setBorder(ctb);

		btnSetPrice = new JToggleButton("Set Price");
		btnTypeOfInsulation = new JToggleButton("Type Of Insulation");
		btnCustom = new JToggleButton("Custom");

		btnSetPrice.setFont(new Font("Tahoma", Font.PLAIN, 14));
		btnTypeOfInsulation.setFont(new Font("Tahoma", Font.PLAIN, 14));
		btnCustom.setFont(new Font("Tahoma", Font.PLAIN, 14));

		btnSetPrice.setBounds(10, 23, 132, 23);
		btnTypeOfInsulation.setBounds(147, 23, 139, 23);
		btnCustom.setBounds(10, 51, 276, 23);

		btnSetPrice.addActionListener(new ActionListener()
		{
			@Override
			public void actionPerformed(ActionEvent e)
			{
				setSelected(0);
			}
		});

		btnTypeOfInsulation.addActionListener(new ActionListener()
		{
			@Override
			public void actionPerformed(ActionEvent e)
			{
				setSelected(1);
			}
		});

		btnCustom.addActionListener(new ActionListener()
		{
			@Override
			public void actionPerformed(ActionEvent e)
			{
				setSelected(2);
			}
		});

		choosePriceTypePanel.add(btnSetPrice);
		choosePriceTypePanel.add(btnTypeOfInsulation);
		choosePriceTypePanel.add(btnCustom);

		amountPanel = new CustomJPanel();
		amountPanel.setBorder(new MatteBorder(1, 1, 1, 1, Color.GRAY));
		amountPanel.setBounds(10, 107, 296, 113);
		amountPanel.setLayout(null);

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
				if((e.getKeyChar() != KeyEvent.VK_BACK_SPACE) || (e.getKeyChar() != KeyEvent.VK_DELETE))
				{
					if(tabJPanel.getInvoiceExplorer().getUtil().isDouble(setPriceAmount.getText()))
					{
						if(setPriceSquareFeet.isVisible())
						{
							calculateCustom(setPriceAmount.getText(), setPriceSquareFeet.getText());
						} else
						{
							calculateSetPrice(setPriceAmount.getText());
						}
					}
				}
			}
		});

		setPriceAmount.setHorizontalAlignment(SwingConstants.RIGHT);
		setPriceAmount.setFont(new Font("Tahoma", Font.PLAIN, 14));
		setPriceAmount.setBounds(143, 11, 86, 23);
		setPriceAmount.setColumns(10);
		setPriceAmount.setVisible(false);
		amountPanel.add(setPriceAmount);
		//end price selection panel

		//sq ft text field
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
						calculateCustom(setPriceAmount.getText(), setPriceSquareFeet.getText());
					}
				}
			}
		});
		setPriceSquareFeet.setHorizontalAlignment(SwingConstants.RIGHT);
		setPriceSquareFeet.setFont(new Font("Tahoma", Font.PLAIN, 14));
		setPriceSquareFeet.setBounds(143, 45, 86, 23);
		setPriceSquareFeet.setVisible(false);
		amountPanel.add(setPriceSquareFeet);
		setPriceSquareFeet.setColumns(10);

		//Start setting the default selected button and variables
		lblAmount = new JLabel("Amount");
		lblAmount.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblAmount.setBounds(58, 14, 75, 17);
		lblAmount.setVisible(false);
		amountPanel.add(lblAmount);

		lblSqFt = new JLabel("Sq. Ft.");
		lblSqFt.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblSqFt.setBounds(58, 46, 75, 20);
		lblSqFt.setVisible(false);
		amountPanel.add(lblSqFt);

		lblDollarSign = new JLabel("$");
		lblDollarSign.setBounds(132, 10, 27, 25);
		lblDollarSign.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblDollarSign.setVisible(false);
		amountPanel.add(lblDollarSign);

		separator = new JSeparator();
		separator.setBounds(132, 77, 107, 2);
		separator.setVisible(false);
		amountPanel.add(separator);

		lblSubtotal = new JLabel("$ 0");
		lblSubtotal.setHorizontalAlignment(SwingConstants.RIGHT);
		lblSubtotal.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblSubtotal.setBounds(58, 83, 171, 28);
		lblSubtotal.setVisible(false);
		amountPanel.add(lblSubtotal);
		//end setting default buttons		

		// start invoiceformat	
		invoiceFormatPanel = new CustomJPanel();
		TitledBorder ifpb = new TitledBorder(new MatteBorder(1, 1, 1, 1, Color.GRAY), "Invoice Format");
		invoiceFormatPanel.setBorder(ifpb);
		invoiceFormatPanel.setBounds(310, 10, 290, 53);
		invoiceFormatPanel.setLayout(null);

		invoiceFormatPanelExpanded = false;

		invoiceFormatSentence = new JComboBox<String>();
		invoiceFormatSentence.setEditable(false);

		invoiceFormatSentence.setBounds(10, 25, 270, 20);
		invoiceFormatSentence.addItem("Total cost for insulation material and labor");
		invoiceFormatSentence.addItem("Insulation cost for Bathroom");
		invoiceFormatSentence.addItem("Insulation cost for Kitchen");
		invoiceFormatSentence.addItem("Insulation cost for Living Room");
		invoiceFormatSentence.addItem("Insulation cost for Dining Room");
		invoiceFormatSentence.addItem("Insulation cost for Bedroom");
		invoiceFormatSentence.addItem("Insulation cost for Basement");
		invoiceFormatSentence.addItem("Custom");

		invoiceFormatSentence.addActionListener(new ActionListener()
		{
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
					TotalArrayNode tan = tabJPanel.getTotalArray().getArrayNode("Insulation");
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
						tabJPanel.getTotalArray().addToArray("Insulation", la, lblSubtotal.getText().replace("$", ""), new HashMap<String, String>());
					}
				}
			}
		});

		invoiceCustomSentence = new JTextField("Insulation");
		invoiceCustomSentence.setBounds(10, 50, 250, 20);
		invoiceCustomSentence.setVisible(false);
		invoiceCustomSentence.addKeyListener(new KeyAdapter()
		{
			@Override
			public void keyReleased(KeyEvent e)
			{
				TotalArrayNode tan = tabJPanel.getTotalArray().getArrayNode("Insulation");
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

		//start type of insulation selection panel
		btnSprayFoam = new JToggleButton("Spray Foam");
		btnSprayFoam.setBounds(10, 10, 132, 23);
		btnSprayFoam.setVisible(false);
		amountPanel.add(btnSprayFoam);

		btnSprayFoam.addActionListener(new ActionListener()
		{
			@Override
			public void actionPerformed(ActionEvent e)
			{
				setSelectedInsulationType(0);
			}
		});

		btnStyrofoam = new JToggleButton("Styrofoam");
		btnStyrofoam.setBounds(150, 10, 137, 23);
		btnStyrofoam.setVisible(false);
		amountPanel.add(btnStyrofoam);

		btnStyrofoam.addActionListener(new ActionListener()
		{
			@Override
			public void actionPerformed(ActionEvent e)
			{
				setSelectedInsulationType(1);
			}
		});

		btnBlowIn = new JToggleButton("Blow In");
		btnBlowIn.setBounds(10, 38, 132, 23);
		btnBlowIn.setVisible(false);
		amountPanel.add(btnBlowIn);

		btnBlowIn.addActionListener(new ActionListener()
		{
			@Override
			public void actionPerformed(ActionEvent e)
			{
				setSelectedInsulationType(2);
			}
		});

		btnRolls = new JToggleButton("Rolls");
		btnRolls.setBounds(150, 38, 137, 23);
		btnRolls.setVisible(false);
		amountPanel.add(btnRolls);

		btnRolls.addActionListener(new ActionListener()
		{
			@Override
			public void actionPerformed(ActionEvent e)
			{
				setSelectedInsulationType(3);
			}
		});
		//end type of insulation selection panel

		//start insulation options panel
		insulationOptionsPanel = new CustomJPanel();
		insulationOptionsPanel.setBorder(new MatteBorder(1, 1, 1, 1, Color.GRAY));
		insulationOptionsPanel.setBounds(10, 187, 296, 200);
		insulationOptionsPanel.setLayout(null);
		insulationOptionsPanel.setVisible(false);

		//spray foam
		sprayFoam1IncDec = new CustomJLabelIncDec("$333.44 X", 1);
		sprayFoam1IncDec.setFont(new Font("Tahoma", Font.PLAIN, 14));
		sprayFoam1IncDec.setBounds(160, 11, 120, 20);
		sprayFoam1IncDec.setEnabled(false);
		sprayFoam1IncDec.addActionListener(new ActionListener()
		{
			@Override
			public void actionPerformed(ActionEvent e)
			{
				calculateTypeOfInsulation();
			}
		});
		insulationOptionsPanel.add(sprayFoam1IncDec);

		sprayFoam1 = new JRadioButton("Touch n' Foam");
		sprayFoam1.setBounds(10, 10, 120, 18);
		sprayFoam1.setFont(new Font("Tahoma", Font.PLAIN, 14));
		sprayFoam1.setVisible(false);
		sprayFoam1.setOpaque(false);
		sprayFoam1.setFocusPainted(false);
		sprayFoam1.addActionListener(new ActionListener()
		{
			@Override
			public void actionPerformed(ActionEvent e)
			{
				sprayFoam1IncDec.setEnabled(sprayFoam1.isSelected());
				calculateTypeOfInsulation();
			}
		});
		insulationOptionsPanel.add(sprayFoam1);

		sprayFoam2IncDec = new CustomJLabelIncDec("$5.48 X", 1);
		sprayFoam2IncDec.setFont(new Font("Tahoma", Font.PLAIN, 14));
		sprayFoam2IncDec.setBounds(160, 34, 120, 20);
		sprayFoam2IncDec.setEnabled(false);
		sprayFoam2IncDec.addActionListener(new ActionListener()
		{
			@Override
			public void actionPerformed(ActionEvent e)
			{
				calculateTypeOfInsulation();
			}
		});
		insulationOptionsPanel.add(sprayFoam2IncDec);

		sprayFoam2 = new JRadioButton("12-fl oz can");
		sprayFoam2.setBounds(10, 33, 120, 18);
		sprayFoam2.setFont(new Font("Tahoma", Font.PLAIN, 14));
		sprayFoam2.setVisible(false);
		sprayFoam2.setOpaque(false);
		sprayFoam2.setFocusPainted(false);
		sprayFoam2.addActionListener(new ActionListener()
		{
			@Override
			public void actionPerformed(ActionEvent e)
			{
				sprayFoam2IncDec.setEnabled(sprayFoam2.isSelected());
				calculateTypeOfInsulation();
			}
		});
		insulationOptionsPanel.add(sprayFoam2);

		sprayFoamSeparator = new JSeparator();
		sprayFoamSeparator.setBounds(132, 77, 107, 2);
		insulationOptionsPanel.add(sprayFoamSeparator);

		/*---------------------------------------------------------------------------------------------------*/

		//styrofoam
		styroFoam1IncDec = new CustomJLabelIncDec("$48.50 X", 1);
		styroFoam1IncDec.setFont(new Font("Tahoma", Font.PLAIN, 14));
		styroFoam1IncDec.setBounds(160, 11, 120, 20);
		styroFoam1IncDec.setEnabled(false);
		styroFoam1IncDec.addActionListener(new ActionListener()
		{
			@Override
			public void actionPerformed(ActionEvent e)
			{
				calculateTypeOfInsulation();
			}
		});
		insulationOptionsPanel.add(styroFoam1IncDec);

		styrofoam1 = new JRadioButton("0.25 x 4 ft x 50 ft");
		styrofoam1.setBounds(10, 10, 140, 18);
		styrofoam1.setFont(new Font("Tahoma", Font.PLAIN, 14));
		styrofoam1.setVisible(false);
		styrofoam1.setOpaque(false);
		styrofoam1.setFocusPainted(false);
		styrofoam1.addActionListener(new ActionListener()
		{
			@Override
			public void actionPerformed(ActionEvent e)
			{
				styroFoam1IncDec.setEnabled(styrofoam1.isSelected());
				calculateTypeOfInsulation();
			}
		});
		insulationOptionsPanel.add(styrofoam1);

		styroFoam2IncDec = new CustomJLabelIncDec("$11.68 X", 1);
		styroFoam2IncDec.setFont(new Font("Tahoma", Font.PLAIN, 14));
		styroFoam2IncDec.setBounds(160, 34, 120, 20);
		styroFoam2IncDec.setEnabled(false);
		styroFoam2IncDec.addActionListener(new ActionListener()
		{
			@Override
			public void actionPerformed(ActionEvent e)
			{
				calculateTypeOfInsulation();
			}
		});
		insulationOptionsPanel.add(styroFoam2IncDec);

		styrofoam2 = new JRadioButton("0.56in x 4ft x 8ft");
		styrofoam2.setBounds(10, 33, 140, 18);
		styrofoam2.setFont(new Font("Tahoma", Font.PLAIN, 14));
		styrofoam2.setVisible(false);
		styrofoam2.setOpaque(false);
		styrofoam2.setFocusPainted(false);
		styrofoam2.addActionListener(new ActionListener()
		{
			@Override
			public void actionPerformed(ActionEvent e)
			{
				styroFoam2IncDec.setEnabled(styrofoam2.isSelected());
				calculateTypeOfInsulation();
			}
		});
		insulationOptionsPanel.add(styrofoam2);

		styroFoam3IncDec = new CustomJLabelIncDec("$4.98 X", 1);
		styroFoam3IncDec.setFont(new Font("Tahoma", Font.PLAIN, 14));
		styroFoam3IncDec.setBounds(160, 57, 120, 20);
		styroFoam3IncDec.setEnabled(false);
		styroFoam3IncDec.addActionListener(new ActionListener()
		{
			@Override
			public void actionPerformed(ActionEvent e)
			{
				calculateTypeOfInsulation();
			}
		});
		insulationOptionsPanel.add(styroFoam3IncDec);

		styrofoam3 = new JRadioButton("1in 2ft 2ft");
		styrofoam3.setBounds(10, 56, 140, 18);
		styrofoam3.setFont(new Font("Tahoma", Font.PLAIN, 14));
		styrofoam3.setVisible(false);
		styrofoam3.setOpaque(false);
		styrofoam3.setFocusPainted(false);
		styrofoam3.addActionListener(new ActionListener()
		{
			@Override
			public void actionPerformed(ActionEvent e)
			{
				styroFoam3IncDec.setEnabled(styrofoam3.isSelected());
				calculateTypeOfInsulation();
			}
		});
		insulationOptionsPanel.add(styrofoam3);

		styroFoam4IncDec = new CustomJLabelIncDec("$12.98 X", 1);
		styroFoam4IncDec.setFont(new Font("Tahoma", Font.PLAIN, 14));
		styroFoam4IncDec.setBounds(160, 80, 120, 20);
		styroFoam4IncDec.setEnabled(false);
		styroFoam4IncDec.addActionListener(new ActionListener()
		{
			@Override
			public void actionPerformed(ActionEvent e)
			{
				calculateTypeOfInsulation();
			}
		});
		insulationOptionsPanel.add(styroFoam4IncDec);

		styrofoam4 = new JRadioButton("1in x 4ft x 8ft");
		styrofoam4.setBounds(10, 79, 140, 18);
		styrofoam4.setFont(new Font("Tahoma", Font.PLAIN, 14));
		styrofoam4.setVisible(false);
		styrofoam4.setOpaque(false);
		styrofoam4.setFocusPainted(false);
		styrofoam4.addActionListener(new ActionListener()
		{
			@Override
			public void actionPerformed(ActionEvent e)
			{
				styroFoam4IncDec.setEnabled(styrofoam4.isSelected());
				calculateTypeOfInsulation();
			}
		});
		insulationOptionsPanel.add(styrofoam4);

		styroFoam5IncDec = new CustomJLabelIncDec("$19.38 X", 1);
		styroFoam5IncDec.setFont(new Font("Tahoma", Font.PLAIN, 14));
		styroFoam5IncDec.setBounds(160, 103, 120, 20);
		styroFoam5IncDec.setEnabled(false);
		styroFoam5IncDec.addActionListener(new ActionListener()
		{
			@Override
			public void actionPerformed(ActionEvent e)
			{
				calculateTypeOfInsulation();
			}
		});
		insulationOptionsPanel.add(styroFoam5IncDec);

		styrofoam5 = new JRadioButton("2in x 2ft x 8ft");
		styrofoam5.setBounds(10, 102, 140, 18);
		styrofoam5.setFont(new Font("Tahoma", Font.PLAIN, 14));
		styrofoam5.setVisible(false);
		styrofoam5.setOpaque(false);
		styrofoam5.setFocusPainted(false);
		styrofoam5.addActionListener(new ActionListener()
		{
			@Override
			public void actionPerformed(ActionEvent e)
			{
				styroFoam5IncDec.setEnabled(styrofoam5.isSelected());
				calculateTypeOfInsulation();
			}
		});
		insulationOptionsPanel.add(styrofoam5);

		styroFoam6IncDec = new CustomJLabelIncDec("$26.98 X", 1);
		styroFoam6IncDec.setFont(new Font("Tahoma", Font.PLAIN, 14));
		styroFoam6IncDec.setBounds(160, 126, 120, 20);
		styroFoam6IncDec.setEnabled(false);
		styroFoam6IncDec.addActionListener(new ActionListener()
		{
			@Override
			public void actionPerformed(ActionEvent e)
			{
				calculateTypeOfInsulation();
			}
		});
		insulationOptionsPanel.add(styroFoam6IncDec);

		styrofoam6 = new JRadioButton("2in x 4ft x 8ft");
		styrofoam6.setBounds(10, 125, 140, 18);
		styrofoam6.setFont(new Font("Tahoma", Font.PLAIN, 14));
		styrofoam6.setVisible(false);
		styrofoam6.setOpaque(false);
		styrofoam6.setFocusPainted(false);
		styrofoam6.addActionListener(new ActionListener()
		{
			@Override
			public void actionPerformed(ActionEvent e)
			{
				styroFoam6IncDec.setEnabled(styrofoam6.isSelected());
				calculateTypeOfInsulation();
			}
		});
		insulationOptionsPanel.add(styrofoam6);

		styroFoamSeparator = new JSeparator();
		styroFoamSeparator.setBounds(132, 169, 107, 2);
		insulationOptionsPanel.add(styroFoamSeparator);

		/*---------------------------------------------------------------------------------------------------*/

		//blow in
		blowInFoam1IncDec = new CustomJLabelIncDec("$12.15 X", 1);
		blowInFoam1IncDec.setFont(new Font("Tahoma", Font.PLAIN, 14));
		blowInFoam1IncDec.setBounds(160, 11, 120, 20);
		blowInFoam1IncDec.setEnabled(false);
		blowInFoam1IncDec.addActionListener(new ActionListener()
		{
			@Override
			public void actionPerformed(ActionEvent e)
			{
				calculateTypeOfInsulation();
			}
		});
		insulationOptionsPanel.add(blowInFoam1IncDec);

		blowInFoam1 = new JRadioButton("Cellulose");
		blowInFoam1.setBounds(10, 10, 120, 18);
		blowInFoam1.setFont(new Font("Tahoma", Font.PLAIN, 14));
		blowInFoam1.setVisible(false);
		blowInFoam1.setOpaque(false);
		blowInFoam1.setFocusPainted(false);
		blowInFoam1.addActionListener(new ActionListener()
		{
			@Override
			public void actionPerformed(ActionEvent e)
			{
				blowInFoam1IncDec.setEnabled(blowInFoam1.isSelected());
				calculateTypeOfInsulation();
			}
		});
		insulationOptionsPanel.add(blowInFoam1);

		blowInFoam2IncDec = new CustomJLabelIncDec("$38.38 X", 1);
		blowInFoam2IncDec.setFont(new Font("Tahoma", Font.PLAIN, 14));
		blowInFoam2IncDec.setBounds(160, 34, 120, 20);
		blowInFoam2IncDec.setEnabled(false);
		blowInFoam2IncDec.addActionListener(new ActionListener()
		{
			@Override
			public void actionPerformed(ActionEvent e)
			{
				calculateTypeOfInsulation();
			}
		});
		insulationOptionsPanel.add(blowInFoam2IncDec);

		blowInFoam2 = new JRadioButton("Fiberglass");
		blowInFoam2.setBounds(10, 33, 120, 18);
		blowInFoam2.setFont(new Font("Tahoma", Font.PLAIN, 14));
		blowInFoam2.setVisible(false);
		blowInFoam2.setOpaque(false);
		blowInFoam2.setFocusPainted(false);
		blowInFoam2.addActionListener(new ActionListener()
		{
			@Override
			public void actionPerformed(ActionEvent e)
			{
				blowInFoam2IncDec.setEnabled(blowInFoam2.isSelected());
				calculateTypeOfInsulation();
			}
		});
		insulationOptionsPanel.add(blowInFoam2);

		blowInFoamSeparator = new JSeparator();
		blowInFoamSeparator.setBounds(132, 77, 107, 2);
		insulationOptionsPanel.add(blowInFoamSeparator);

		/*---------------------------------------------------------------------------------------------------*/

		//rolls
		rollType1IncDec = new CustomJLabelIncDec("$11.00 X", 1);
		rollType1IncDec.setFont(new Font("Tahoma", Font.PLAIN, 14));
		rollType1IncDec.setBounds(160, 11, 120, 20);
		rollType1IncDec.setEnabled(false);
		rollType1IncDec.addActionListener(new ActionListener()
		{
			@Override
			public void actionPerformed(ActionEvent e)
			{
				calculateTypeOfInsulation();
			}
		});
		insulationOptionsPanel.add(rollType1IncDec);

		rollType1 = new JRadioButton("R13 (15in x 32ft)");
		rollType1.setBounds(10, 10, 140, 18);
		rollType1.setFont(new Font("Tahoma", Font.PLAIN, 14));
		rollType1.setVisible(false);
		rollType1.setOpaque(false);
		rollType1.setFocusPainted(false);
		rollType1.addActionListener(new ActionListener()
		{
			@Override
			public void actionPerformed(ActionEvent e)
			{
				rollType1IncDec.setEnabled(rollType1.isSelected());
				calculateTypeOfInsulation();
			}
		});
		insulationOptionsPanel.add(rollType1);

		rollType2IncDec = new CustomJLabelIncDec("$26.45 X", 1);
		rollType2IncDec.setFont(new Font("Tahoma", Font.PLAIN, 14));
		rollType2IncDec.setBounds(160, 34, 120, 20);
		rollType2IncDec.setEnabled(false);
		rollType2IncDec.addActionListener(new ActionListener()
		{
			@Override
			public void actionPerformed(ActionEvent e)
			{
				calculateTypeOfInsulation();
			}
		});
		insulationOptionsPanel.add(rollType2IncDec);

		rollType2 = new JRadioButton("R19 (15in x 39ft)");
		rollType2.setBounds(10, 33, 140, 18);
		rollType2.setFont(new Font("Tahoma", Font.PLAIN, 14));
		rollType2.setVisible(false);
		rollType2.setOpaque(false);
		rollType2.setFocusPainted(false);
		rollType2.addActionListener(new ActionListener()
		{
			@Override
			public void actionPerformed(ActionEvent e)
			{
				rollType2IncDec.setEnabled(rollType2.isSelected());
				calculateTypeOfInsulation();
			}
		});
		insulationOptionsPanel.add(rollType2);

		rollType3IncDec = new CustomJLabelIncDec("$16.25 X", 1);
		rollType3IncDec.setFont(new Font("Tahoma", Font.PLAIN, 14));
		rollType3IncDec.setBounds(160, 57, 120, 20);
		rollType3IncDec.setEnabled(false);
		rollType3IncDec.addActionListener(new ActionListener()
		{
			@Override
			public void actionPerformed(ActionEvent e)
			{
				calculateTypeOfInsulation();
			}
		});
		insulationOptionsPanel.add(rollType3IncDec);

		rollType3 = new JRadioButton("R21 (16in x 25ft)");
		rollType3.setBounds(10, 56, 140, 18);
		rollType3.setFont(new Font("Tahoma", Font.PLAIN, 14));
		rollType3.setVisible(false);
		rollType3.setOpaque(false);
		rollType3.setFocusPainted(false);
		rollType3.addActionListener(new ActionListener()
		{
			@Override
			public void actionPerformed(ActionEvent e)
			{
				rollType3IncDec.setEnabled(rollType3.isSelected());
				calculateTypeOfInsulation();
			}
		});
		insulationOptionsPanel.add(rollType3);

		rollType4IncDec = new CustomJLabelIncDec("$18.67 X", 1);
		rollType4IncDec.setFont(new Font("Tahoma", Font.PLAIN, 14));
		rollType4IncDec.setBounds(160, 81, 120, 20);
		rollType4IncDec.setEnabled(false);
		rollType4IncDec.addActionListener(new ActionListener()
		{
			@Override
			public void actionPerformed(ActionEvent e)
			{
				calculateTypeOfInsulation();
			}
		});

		insulationOptionsPanel.add(rollType4IncDec);

		rollType4 = new JRadioButton("R30 (15in x 25ft)");
		rollType4.setBounds(10, 79, 140, 18);
		rollType4.setFont(new Font("Tahoma", Font.PLAIN, 14));
		rollType4.setVisible(false);
		rollType4.setOpaque(false);
		rollType4.setFocusPainted(false);
		rollType4.addActionListener(new ActionListener()
		{
			@Override
			public void actionPerformed(ActionEvent e)
			{
				rollType4IncDec.setEnabled(rollType4.isSelected());
				calculateTypeOfInsulation();
			}
		});
		insulationOptionsPanel.add(rollType4);

		rollFoamSeparator = new JSeparator();
		rollFoamSeparator.setBounds(132, 123, 107, 2);
		insulationOptionsPanel.add(rollFoamSeparator);

		//		lblInsulationSubtotal = new JLabel("$ 0");
		//		lblInsulationSubtotal.setHorizontalAlignment(SwingConstants.RIGHT);
		//		lblInsulationSubtotal.setFont(new Font("Tahoma", Font.PLAIN, 14));
		//		lblInsulationSubtotal.setVisible(false);
		//		insulationOptionsPanel.add(lblInsulationSubtotal);

		/*---------------------------------------------------------------------------------------------------*/

		//end insulation options panel

		//Start setting the default selected button and variables set it last so everything is loaded first
		setSelected(0);
	}

	public void enableGui()
	{
		tabJPanel.add(choosePriceTypePanel);
		tabJPanel.add(amountPanel);
		tabJPanel.add(invoiceFormatPanel);
		tabJPanel.add(insulationOptionsPanel);
		tabJPanel.getTotalArray().enableTotalLabel();
		tabJPanel.validateRepaint();
	}

	public void disableGui()
	{
		tabJPanel.remove(choosePriceTypePanel);
		tabJPanel.remove(amountPanel);
		tabJPanel.remove(invoiceFormatPanel);
		tabJPanel.remove(insulationOptionsPanel);
		tabJPanel.getTotalArray().disableTotalLabel();
		tabJPanel.validateRepaint();
	}

	//0 - setPrice, 1 - Type of Insulation, 2 - Custom
	public void setSelected(int i)
	{
		if(i == 0)
		{
			btnSetPrice.setSelected(true);
			btnTypeOfInsulation.setSelected(false);
			btnCustom.setSelected(false);
			amountPanel.setBounds(10, 107, 296, 113);
			//set bounds for subtotal back to normal
			lblSubtotal.setBounds(58, 83, 171, 28);
			amountPanel.add(lblSubtotal);
			//text field options
			setPriceAmount.setText("");
			setPriceAmount.setFocusable(true);
			setPriceAmount.setBackground(Color.WHITE);
			//Visible
			setPriceAmount.setVisible(true);
			separator.setVisible(true);
			lblAmount.setVisible(true);
			lblDollarSign.setVisible(true);
			//Invisible
			lblSqFt.setVisible(false);
			setPriceSquareFeet.setVisible(false);
			//			lblInsulationSubtotal.setVisible(false);

			btnSprayFoam.setVisible(false);
			btnStyrofoam.setVisible(false);
			btnBlowIn.setVisible(false);
			btnRolls.setVisible(false);
			insulationOptionsPanel.setVisible(false);

			if(setPriceAmount != null && lblSubtotal != null)
			{
				calculateSetPrice(setPriceAmount.getText());
			}

		}
		if(i == 1)
		{
			btnTypeOfInsulation.setSelected(true);
			btnCustom.setSelected(false);
			btnSetPrice.setSelected(false);
			amountPanel.setBounds(10, 107, 296, 70);
			//Visible
			btnSprayFoam.setVisible(true);
			btnStyrofoam.setVisible(true);
			btnBlowIn.setVisible(true);
			btnRolls.setVisible(true);
			//Invisible
			setPriceAmount.setVisible(false);
			separator.setVisible(false);
			lblAmount.setVisible(false);
			lblDollarSign.setVisible(false);
			// sdf			lblSubtotal.setVisible(false);
			lblSqFt.setVisible(false);
			setPriceSquareFeet.setVisible(false);
			sprayFoam1.setVisible(false);
			insulationOptionsPanel.setVisible(false);

			//open up the foam selection type panel when this button is selected (after the buttons are selected already, otherwise just ignore)
			if(btnSprayFoam.isSelected())
			{
				setSelectedInsulationType(0);
			}
			if(btnStyrofoam.isSelected())
			{
				setSelectedInsulationType(1);
			}
			if(btnBlowIn.isSelected())
			{
				setSelectedInsulationType(2);
			}
			if(btnRolls.isSelected())
			{
				setSelectedInsulationType(3);
			}
		}

		if(i == 2)
		{
			btnCustom.setSelected(true);
			btnSetPrice.setSelected(false);
			btnTypeOfInsulation.setSelected(false);
			amountPanel.setBounds(10, 107, 296, 113);
			//set bounds for subtotal back to normal
			lblSubtotal.setBounds(58, 83, 171, 28);
			amountPanel.add(lblSubtotal);

			//Amount JTextField
			setPriceAmount.setText("");
			setPriceAmount.setFocusable(true);
			setPriceAmount.setBackground(Color.WHITE);
			//Visible
			setPriceAmount.setVisible(true);
			separator.setVisible(true);
			lblAmount.setVisible(true);
			lblDollarSign.setVisible(true);
			lblSqFt.setVisible(true);
			setPriceSquareFeet.setVisible(true);
			//Invisible
			btnSprayFoam.setVisible(false);
			btnStyrofoam.setVisible(false);
			btnBlowIn.setVisible(false);
			btnRolls.setVisible(false);
			sprayFoam1.setVisible(false);
			insulationOptionsPanel.setVisible(false);
			//			lblInsulationSubtotal.setVisible(false);

			if(setPriceAmount != null && lblSubtotal != null)
			{
				calculateCustom(setPriceAmount.getText(), "1");
			}
		}

	}

	//------------------------------------------------//
	// 0 - Spray Foam, 1- Styrofoam, 2 - Blow In, 3 - Rolls
	public void setSelectedInsulationType(int i)
	{
		insulationOptionsPanel.setVisible(true);
		insulationOptionsPanel.add(lblSubtotal);
		//		lblInsulationSubtotal.setVisible(true);
		if(i == 0)
		{
			btnSprayFoam.setSelected(true);
			btnStyrofoam.setSelected(false);
			btnBlowIn.setSelected(false);
			btnRolls.setSelected(false);
			//adjust width of panel
			insulationOptionsPanel.setBounds(10, 187, 296, 113);
			//adjust bounds of total 
			lblSubtotal.setBounds(58, 83, 171, 28);
			//			lblInsulationSubtotal.setBounds(58, 83, 171, 28);

			//Visible
			sprayFoam1.setVisible(true);
			sprayFoam2.setVisible(true);
			sprayFoam1IncDec.setVisible(true);
			sprayFoam2IncDec.setVisible(true);
			sprayFoamSeparator.setVisible(true);

			//Invisible
			blowInFoam1.setVisible(false);
			blowInFoam2.setVisible(false);
			blowInFoam1IncDec.setVisible(false);
			blowInFoam2IncDec.setVisible(false);
			blowInFoamSeparator.setVisible(false);
			styrofoam1.setVisible(false);
			styrofoam2.setVisible(false);
			styrofoam3.setVisible(false);
			styrofoam4.setVisible(false);
			styrofoam5.setVisible(false);
			styrofoam6.setVisible(false);
			styroFoam1IncDec.setVisible(false);
			styroFoam2IncDec.setVisible(false);
			styroFoam3IncDec.setVisible(false);
			styroFoam4IncDec.setVisible(false);
			styroFoam5IncDec.setVisible(false);
			styroFoam6IncDec.setVisible(false);
			styroFoamSeparator.setVisible(false);
			rollType1.setVisible(false);
			rollType2.setVisible(false);
			rollType3.setVisible(false);
			rollType4.setVisible(false);
			rollType1IncDec.setVisible(false);
			rollType2IncDec.setVisible(false);
			rollType3IncDec.setVisible(false);
			rollType4IncDec.setVisible(false);
			rollFoamSeparator.setVisible(false);
		}

		if(i == 1)
		{
			btnSprayFoam.setSelected(false);
			btnStyrofoam.setSelected(true);
			btnBlowIn.setSelected(false);
			btnRolls.setSelected(false);
			//adjust width of panel
			insulationOptionsPanel.setBounds(10, 187, 296, 204);
			//adjust bounds of total 
			lblSubtotal.setBounds(58, 174, 171, 28);
			//			lblInsulationSubtotal.setBounds(58, 174, 171, 28);

			//Visible
			styrofoam1.setVisible(true);
			styrofoam2.setVisible(true);
			styrofoam3.setVisible(true);
			styrofoam4.setVisible(true);
			styrofoam5.setVisible(true);
			styrofoam6.setVisible(true);
			styroFoam1IncDec.setVisible(true);
			styroFoam2IncDec.setVisible(true);
			styroFoam3IncDec.setVisible(true);
			styroFoam4IncDec.setVisible(true);
			styroFoam5IncDec.setVisible(true);
			styroFoam6IncDec.setVisible(true);
			styroFoamSeparator.setVisible(true);
			//Invisible
			blowInFoam1.setVisible(false);
			blowInFoam2.setVisible(false);
			blowInFoam1IncDec.setVisible(false);
			blowInFoam2IncDec.setVisible(false);
			blowInFoamSeparator.setVisible(false);
			sprayFoam1.setVisible(false);
			sprayFoam2.setVisible(false);
			sprayFoam1IncDec.setVisible(false);
			sprayFoam2IncDec.setVisible(false);
			sprayFoamSeparator.setVisible(false);
			rollType1.setVisible(false);
			rollType2.setVisible(false);
			rollType3.setVisible(false);
			rollType4.setVisible(false);
			rollType1IncDec.setVisible(false);
			rollType2IncDec.setVisible(false);
			rollType3IncDec.setVisible(false);
			rollType4IncDec.setVisible(false);
			rollFoamSeparator.setVisible(false);
		}

		if(i == 2)
		{
			btnSprayFoam.setSelected(false);
			btnStyrofoam.setSelected(false);
			btnBlowIn.setSelected(true);
			btnRolls.setSelected(false);
			//adjust width of panel
			insulationOptionsPanel.setBounds(10, 187, 296, 113);
			//adjust bounds of total 
			lblSubtotal.setBounds(58, 83, 171, 28);
			//			lblInsulationSubtotal.setBounds(58, 83, 171, 28);

			//Visible
			blowInFoam1.setVisible(true);
			blowInFoam2.setVisible(true);
			blowInFoam1IncDec.setVisible(true);
			blowInFoam2IncDec.setVisible(true);
			blowInFoamSeparator.setVisible(true);

			//Invisible
			sprayFoam1.setVisible(false);
			sprayFoam2.setVisible(false);
			sprayFoam1IncDec.setVisible(false);
			sprayFoam2IncDec.setVisible(false);
			sprayFoamSeparator.setVisible(false);
			styrofoam1.setVisible(false);
			styrofoam2.setVisible(false);
			styrofoam3.setVisible(false);
			styrofoam4.setVisible(false);
			styrofoam5.setVisible(false);
			styrofoam6.setVisible(false);
			styroFoam1IncDec.setVisible(false);
			styroFoam2IncDec.setVisible(false);
			styroFoam3IncDec.setVisible(false);
			styroFoam4IncDec.setVisible(false);
			styroFoam5IncDec.setVisible(false);
			styroFoam6IncDec.setVisible(false);
			styroFoamSeparator.setVisible(false);
			rollType1.setVisible(false);
			rollType2.setVisible(false);
			rollType3.setVisible(false);
			rollType4.setVisible(false);
			rollType1IncDec.setVisible(false);
			rollType2IncDec.setVisible(false);
			rollType3IncDec.setVisible(false);
			rollType4IncDec.setVisible(false);
			rollFoamSeparator.setVisible(false);
		}

		if(i == 3)
		{
			btnSprayFoam.setSelected(false);
			btnStyrofoam.setSelected(false);
			btnBlowIn.setSelected(false);
			btnRolls.setSelected(true);
			//adjust width of panel
			insulationOptionsPanel.setBounds(10, 187, 296, 160);
			//adjust bounds of total 
			lblSubtotal.setBounds(58, 130, 171, 28);
			//			lblInsulationSubtotal.setBounds(58, 130, 171, 28);

			//Visible
			rollType1.setVisible(true);
			rollType2.setVisible(true);
			rollType3.setVisible(true);
			rollType4.setVisible(true);
			rollType1IncDec.setVisible(true);
			rollType2IncDec.setVisible(true);
			rollType3IncDec.setVisible(true);
			rollType4IncDec.setVisible(true);
			rollFoamSeparator.setVisible(true);

			//Invisible
			blowInFoam1.setVisible(false);
			blowInFoam2.setVisible(false);
			blowInFoam1IncDec.setVisible(false);
			blowInFoam2IncDec.setVisible(false);
			blowInFoamSeparator.setVisible(false);
			sprayFoam1.setVisible(false);
			sprayFoam2.setVisible(false);
			sprayFoam1IncDec.setVisible(false);
			sprayFoam2IncDec.setVisible(false);
			styroFoam1IncDec.setVisible(false);
			sprayFoamSeparator.setVisible(false);
			styrofoam1.setVisible(false);
			styrofoam2.setVisible(false);
			styrofoam3.setVisible(false);
			styrofoam4.setVisible(false);
			styrofoam5.setVisible(false);
			styrofoam6.setVisible(false);
			styroFoam1IncDec.setVisible(false);
			styroFoam2IncDec.setVisible(false);
			styroFoam3IncDec.setVisible(false);
			styroFoam4IncDec.setVisible(false);
			styroFoam5IncDec.setVisible(false);
			styroFoam6IncDec.setVisible(false);
			styroFoamSeparator.setVisible(false);
		}

	}

	//------------------------------------------------//
	public void calculateSetPrice(String setPrice)
	{
		if((!setPrice.isEmpty()))
		{
			double price = Double.parseDouble(setPrice);
			double subTotal = price;
			lblSubtotal.setText("$ " + new DecimalFormat("##.##").format(subTotal));
			lblSubtotal.setVisible(true);
			updateTotal();
		} else
		{
			lblSubtotal.setVisible(false);
		}
	}

	//-----calculate insulation code-------------------------------------------//
	public Double calculateInsulationSpray()
	{
		double spray1 = 0;
		double spray2 = 0;

		if(sprayFoam1.isSelected())
		{
			spray1 = sprayFoam1IncDec.getCalculation();
		}
		if(sprayFoam2.isSelected())
		{
			spray2 = sprayFoam2IncDec.getCalculation();
		}

		return spray1 + spray2;
	}

	public Double calculateInsulationBlow()
	{
		double blow1 = 0;
		double blow2 = 0;

		if(blowInFoam1.isSelected())
		{
			blow1 = blowInFoam1IncDec.getCalculation();
		}
		if(blowInFoam2.isSelected())
		{
			blow2 = blowInFoam2IncDec.getCalculation();
		}
		return blow1 + blow2;
	}

	public Double calculateInsulationStyro()
	{
		double styro1 = 0;
		double styro2 = 0;
		double styro3 = 0;
		double styro4 = 0;
		double styro5 = 0;
		double styro6 = 0;

		if(styrofoam1.isSelected())
		{
			styro1 = styroFoam1IncDec.getCalculation();
		}
		if(styrofoam2.isSelected())
		{
			styro2 = styroFoam2IncDec.getCalculation();
		}
		if(styrofoam3.isSelected())
		{
			styro3 = styroFoam3IncDec.getCalculation();
		}
		if(styrofoam4.isSelected())
		{
			styro4 = styroFoam4IncDec.getCalculation();
		}
		if(styrofoam5.isSelected())
		{
			styro5 = styroFoam5IncDec.getCalculation();
		}
		if(styrofoam6.isSelected())
		{
			styro6 = styroFoam6IncDec.getCalculation();
		}
		return styro1 + styro2 + styro3 + styro4 + styro5 + styro6;
	}

	public Double calculateInsulationRolls()
	{
		double roll1 = 0;
		double roll2 = 0;
		double roll3 = 0;
		double roll4 = 0;

		if(rollType1.isSelected())
		{
			roll1 = rollType1IncDec.getCalculation();
		}
		if(rollType2.isSelected())
		{
			roll2 = rollType2IncDec.getCalculation();
		}
		if(rollType3.isSelected())
		{
			roll3 = rollType3IncDec.getCalculation();
		}
		if(rollType4.isSelected())
		{
			roll4 = rollType4IncDec.getCalculation();
		}
		return roll1 + roll2 + roll3 + roll4;
	}

	public void calculateTypeOfInsulation()
	{
		if(lblSubtotal != null)
		{
			Double totalMath = calculateInsulationSpray() + calculateInsulationBlow() + calculateInsulationStyro() + calculateInsulationRolls();
			lblSubtotal.setText("$ " + new DecimalFormat("##.##").format(totalMath));
			lblSubtotal.setVisible(true);
			updateTotal();
		}
	}

	//------------------------------------------------//
	public void calculateCustom(String amount, String spqf)
	{
		if((!amount.isEmpty() && !spqf.isEmpty()))
		{
			double price = Double.parseDouble(amount);
			double squareFt = Double.parseDouble(spqf);
			double subTotal = price * squareFt;
			lblSubtotal.setText("$ " + new DecimalFormat("##.##").format(subTotal));
			lblSubtotal.setVisible(true);
			updateTotal();
		} else
		{
			lblSubtotal.setVisible(false);
		}
	}

	//----------------------total array---------------------//

	public void updateTotal()
	{
		double currentPrice = Double.parseDouble(lblSubtotal.getText().replace("$", ""));

		TotalArrayNode tan = tabJPanel.getTotalArray().getArrayNode("Insulation");
		if(tan != null)
		{
			String la = invoiceFormatSentence.getItemAt(invoiceFormatSentence.getSelectedIndex()).toString();
			if(la.equalsIgnoreCase("Custom"))
			{
				la = invoiceCustomSentence.getText();
			}
			tan.setArrayString(la);

			if(sprayFoam1.isSelected() || sprayFoam2.isSelected())
			{
				tan.getExtraLines().put("Spray Foam", new DecimalFormat("##.##").format(calculateInsulationSpray()));
			} else
			{
				tan.getExtraLines().remove("Spray Foam");
			}

			if(blowInFoam1.isSelected() || blowInFoam2.isSelected())
			{
				tan.getExtraLines().put("Blow-In Foam", new DecimalFormat("##.##").format(calculateInsulationBlow()));
			} else
			{
				tan.getExtraLines().remove("Blow-In Foam");
			}

			if(styrofoam1.isSelected() || styrofoam2.isSelected() || styrofoam3.isSelected() || styrofoam4.isSelected() || styrofoam5.isSelected() || styrofoam6.isSelected())
			{
				tan.getExtraLines().put("Styrofoam", new DecimalFormat("##.##").format(calculateInsulationStyro()));
			} else
			{
				tan.getExtraLines().remove("Styrofoam");
			}

			if(rollType1.isSelected() || rollType2.isSelected() || rollType3.isSelected() || rollType4.isSelected())
			{
				tan.getExtraLines().put("Roll Foam", new DecimalFormat("##.##").format(calculateInsulationRolls()));
			} else
			{
				tan.getExtraLines().remove("Roll Foam");
			}

			tan.setTotalAmount(new DecimalFormat("##.##").format(currentPrice));

			tabJPanel.getTotalArray().paintArray();
		} else
		{
			String la = invoiceFormatSentence.getItemAt(invoiceFormatSentence.getSelectedIndex()).toString();
			if(la.equalsIgnoreCase("Custom"))
			{
				la = invoiceCustomSentence.getText();
			}
			tabJPanel.getTotalArray().addToArray("Insulation", la, new DecimalFormat("##.##").format(currentPrice), new HashMap<String, String>());
		}
	}

	//------------------------------------------------//
	public void expandInvoiceFormatPanel()
	{
		invoiceFormatPanelExpanded = true;
		invoiceFormatPanel.setBounds(310, 10, 290, 80);
		invoiceFormatPanel.repaint();
		invoiceCustomSentence.setVisible(true);
		invoiceCustomSentence.grabFocus();
		invoiceCustomSentence.repaint();
	}

	//----------------------------------------------------------------------------------------------//	
	public void contractInvoiceFormatPanel()
	{
		invoiceFormatPanelExpanded = false;
		invoiceFormatPanel.setBounds(310, 10, 290, 53);
		invoiceFormatPanel.repaint();
		invoiceCustomSentence.setVisible(false);
		invoiceCustomSentence.repaint();
	}
}
