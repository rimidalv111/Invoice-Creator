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
import javax.swing.JSeparator;
import javax.swing.JTextField;
import javax.swing.JToggleButton;
import javax.swing.SwingConstants;
import javax.swing.border.MatteBorder;
import javax.swing.border.TitledBorder;

import com.rimidalv111.invoiceexplorer.TabJPanel;
import com.rimidalv111.util.TotalArrayNode;
import com.rimidalv111.util.customclasses.CustomJPanel;

public class ElectricalGui extends JPanel
{
	private static final long serialVersionUID = 1L;

	private TabJPanel tabJPanel;

	//start price button selection panel
	private CustomJPanel choosePriceTypePanel;
	private JToggleButton btnSetPrice;
	private JToggleButton btnFinishFixture;
	private JToggleButton btnWholeFixture;
	//end price button selection panel

	//start amount/fixtures panel
	private CustomJPanel amountFixturePanel;
	private JTextField setPriceAmount;
	private JTextField numberOfFixtures;
	private JLabel lblSubtotal;
	private JLabel lblAmount;
	private JLabel lblFixtures;
	private JLabel lblNumberSign;
	private JLabel lblDollarSign;
	private JSeparator separator;
	private String priceForFinish;
	private String priceForWhole;
	//end amount/fixtures panel

	//start invoiceFormat
	private JPanel invoiceFormatPanel;
	private Boolean invoiceFormatPanelExpanded;
	private JComboBox<String> invoiceFormatSentence;
	private JTextField invoiceCustomSentence;

	//end invoiceFormat	

	public ElectricalGui(TabJPanel tjp)
	{
		super(new GridLayout(1, 0));
		tabJPanel = tjp;

		//Price Setting Tab Variables
		priceForFinish = "25.00";
		priceForWhole = "50.00";

		//start price selection panel
		choosePriceTypePanel = new CustomJPanel();
		choosePriceTypePanel.setBounds(10, 10, 296, 87);
		choosePriceTypePanel.setLayout(null);
		TitledBorder ctb = new TitledBorder(new MatteBorder(1, 1, 1, 1, Color.GRAY), "Pricing");
		choosePriceTypePanel.setBorder(ctb);

		btnSetPrice = new JToggleButton("Set Price");
		btnFinishFixture = new JToggleButton("Finish Fixture");
		btnWholeFixture = new JToggleButton("Whole Fixture");

		btnSetPrice.setFont(new Font("Tahoma", Font.PLAIN, 14));
		btnFinishFixture.setFont(new Font("Tahoma", Font.PLAIN, 14));
		btnWholeFixture.setFont(new Font("Tahoma", Font.PLAIN, 14));

		btnSetPrice.setBounds(10, 23, 132, 23);
		btnFinishFixture.setBounds(147, 23, 139, 23);
		btnWholeFixture.setBounds(10, 51, 276, 23);

		btnSetPrice.addActionListener(new ActionListener()
		{
			@Override
			public void actionPerformed(ActionEvent e)
			{
				setSelected(0);
			}
		});

		btnFinishFixture.addActionListener(new ActionListener()
		{
			@Override
			public void actionPerformed(ActionEvent e)
			{
				setSelected(1);
			}
		});

		btnWholeFixture.addActionListener(new ActionListener()
		{
			@Override
			public void actionPerformed(ActionEvent e)
			{
				setSelected(2);
			}
		});

		choosePriceTypePanel.add(btnSetPrice);
		choosePriceTypePanel.add(btnFinishFixture);
		choosePriceTypePanel.add(btnWholeFixture);

		amountFixturePanel = new CustomJPanel();
		amountFixturePanel.setBorder(new MatteBorder(1, 1, 1, 1, Color.GRAY));
		amountFixturePanel.setBounds(10, 107, 296, 113);
		amountFixturePanel.setLayout(null);

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
						calculateSetPrice(setPriceAmount.getText());
					}
				}
			}
		});

		setPriceAmount.setHorizontalAlignment(SwingConstants.RIGHT);
		setPriceAmount.setFont(new Font("Tahoma", Font.PLAIN, 14));
		setPriceAmount.setBounds(143, 11, 86, 23);
		setPriceAmount.setColumns(10);
		setPriceAmount.setVisible(false); // set false until corresponding button is selected
		amountFixturePanel.add(setPriceAmount);

		numberOfFixtures = new JTextField(12);
		numberOfFixtures.addKeyListener(new KeyAdapter()
		{
			@Override
			public void keyTyped(KeyEvent e)
			{
				if(Character.isDigit(e.getKeyChar()) || Character.toString(e.getKeyChar()).equalsIgnoreCase(".") || (e.getKeyChar() == KeyEvent.VK_BACK_SPACE) || (e.getKeyChar() == KeyEvent.VK_DELETE))
				{
					if((numberOfFixtures.getText().length() >= 9) || (numberOfFixtures.getText().contains(".") && Character.toString(e.getKeyChar()).equalsIgnoreCase(".")))
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
					if(tabJPanel.getInvoiceExplorer().getUtil().isDouble(numberOfFixtures.getText()))
					{
						calculateFinish(setPriceAmount.getText(), numberOfFixtures.getText());
					}
				}
			}
		});

		numberOfFixtures.setHorizontalAlignment(SwingConstants.RIGHT);
		numberOfFixtures.setFont(new Font("Tahoma", Font.PLAIN, 14));
		numberOfFixtures.setBounds(143, 45, 86, 23);
		numberOfFixtures.setColumns(10);
		numberOfFixtures.setVisible(false);
		amountFixturePanel.add(numberOfFixtures);
		//end price selection panel

		//Start setting the default selected button and variables
		lblAmount = new JLabel("Amount");
		lblAmount.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblAmount.setBounds(58, 14, 75, 17);
		lblAmount.setVisible(false);
		amountFixturePanel.add(lblAmount);

		lblFixtures = new JLabel("Fixtures");
		lblFixtures.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblFixtures.setBounds(58, 46, 75, 20);
		lblFixtures.setVisible(false);
		amountFixturePanel.add(lblFixtures);

		lblNumberSign = new JLabel("#");
		lblNumberSign.setBounds(132, 42, 27, 25);
		lblNumberSign.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblNumberSign.setVisible(false);
		amountFixturePanel.add(lblNumberSign);

		lblDollarSign = new JLabel("$");
		lblDollarSign.setBounds(132, 10, 27, 25);
		lblDollarSign.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblDollarSign.setVisible(false);
		amountFixturePanel.add(lblDollarSign);

		separator = new JSeparator();
		separator.setBounds(132, 77, 107, 2);
		separator.setVisible(false);
		amountFixturePanel.add(separator);

		lblSubtotal = new JLabel("$0");
		lblSubtotal.setHorizontalAlignment(SwingConstants.RIGHT);
		lblSubtotal.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblSubtotal.setBounds(58, 83, 171, 28);
		lblSubtotal.setVisible(false);
		amountFixturePanel.add(lblSubtotal);
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
		invoiceFormatSentence.addItem("Total cost for Electrical work");
		invoiceFormatSentence.addItem("Electrical cost for Bathroom");
		invoiceFormatSentence.addItem("Electrical cost for Kitchen");
		invoiceFormatSentence.addItem("Electrical cost for Living Room");
		invoiceFormatSentence.addItem("Electrical cost for Dining Room");
		invoiceFormatSentence.addItem("Electrical cost for Bedroom");
		invoiceFormatSentence.addItem("Electrical cost for Basement");
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
				}
				updateTotalArray();
			}
		});

		invoiceCustomSentence = new JTextField("Electrical");
		invoiceCustomSentence.setBounds(10, 50, 250, 20);
		invoiceCustomSentence.setVisible(false);
		invoiceCustomSentence.addKeyListener(new KeyAdapter()
		{
			@Override
			public void keyReleased(KeyEvent e)
			{
				TotalArrayNode tan = tabJPanel.getTotalArray().getArrayNode("Electrical");
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

		//Start setting the default selected button and variables set it last so everything is loaded first
		setSelected(0);
	}

	public void enableGui()
	{
		tabJPanel.add(choosePriceTypePanel);
		tabJPanel.add(amountFixturePanel);
		tabJPanel.add(invoiceFormatPanel);

		tabJPanel.getTotalArray().enableTotalLabel();
		tabJPanel.validateRepaint();
	}

	public void disableGui()
	{
		tabJPanel.remove(choosePriceTypePanel);
		tabJPanel.remove(amountFixturePanel);
		tabJPanel.remove(invoiceFormatPanel);
		tabJPanel.getTotalArray().disableTotalLabel();
		tabJPanel.validateRepaint();
	}

	public void setSelected(int i)
	{
		if(i == 0)
		{
			btnFinishFixture.setSelected(false);
			btnWholeFixture.setSelected(false);
			setPriceAmount.setText("");
			setPriceAmount.setFocusable(true);
			setPriceAmount.setBackground(Color.WHITE);
			btnSetPrice.setSelected(true);
			setPriceAmount.setVisible(true);
			separator.setVisible(true);
			lblNumberSign.setVisible(false);
			lblAmount.setVisible(true);
			lblDollarSign.setVisible(true);
			lblFixtures.setVisible(false);
			numberOfFixtures.setVisible(false);
			if(setPriceAmount != null && lblSubtotal != null)
			{
				calculateSetPrice(setPriceAmount.getText());
			}
		}
		if(i == 1)
		{
			numberOfFixtures.setText("");
			setPriceAmount.setText(priceForFinish);
			setPriceAmount.setFocusable(false);
			setPriceAmount.setBackground(Color.LIGHT_GRAY);
			btnSetPrice.setSelected(false);
			btnWholeFixture.setSelected(false);
			numberOfFixtures.setFocusable(true);
			numberOfFixtures.setBackground(Color.WHITE);
			setPriceAmount.setVisible(true);
			separator.setVisible(true);
			lblNumberSign.setVisible(true);
			lblAmount.setVisible(true);
			lblDollarSign.setVisible(true);
			lblFixtures.setVisible(true);
			numberOfFixtures.setVisible(true);
			if(setPriceAmount != null && lblSubtotal != null)
			{
				calculateFinish(priceForFinish, numberOfFixtures.getText());
			}
		}

		if(i == 2)
		{
			numberOfFixtures.setText("");
			setPriceAmount.setText(priceForWhole);
			setPriceAmount.setFocusable(false);
			setPriceAmount.setBackground(Color.LIGHT_GRAY);
			btnSetPrice.setSelected(false);
			btnFinishFixture.setSelected(false);
			setPriceAmount.setVisible(true);
			separator.setVisible(true);
			lblNumberSign.setVisible(true);
			lblAmount.setVisible(true);
			lblDollarSign.setVisible(true);
			lblFixtures.setVisible(true);
			numberOfFixtures.setVisible(true);
			if(setPriceAmount != null && lblSubtotal != null)
			{
				calculateWhole(priceForWhole, numberOfFixtures.getText());
			}
		}
	}

	public void numberOfFixtures(String amount)
	{
		if((!amount.isEmpty()))
		{
			String quantity = amount;
			calculateFinish(null, quantity);
			calculateWhole(null, quantity);
		}

	}

	public void calculateSetPrice(String setPrice)
	{
		if((!setPrice.isEmpty()))
		{
			double price = Double.parseDouble(setPrice);
			double subTotal = price;
			lblSubtotal.setText("$ " + new DecimalFormat("##.##").format(subTotal));
			lblSubtotal.setVisible(true);
			updateTotalArray();
		} else
		{
			lblSubtotal.setVisible(false);
		}
	}

	public void calculateFinish(String priceForFinish, String quantity)
	{
		if((!quantity.isEmpty()))
		{
			double price = Double.parseDouble(priceForFinish);
			double numberOfFixtures = Double.parseDouble(quantity);
			double subTotal = price * numberOfFixtures;
			lblSubtotal.setText("$ " + new DecimalFormat("##.##").format(subTotal));
			lblSubtotal.setVisible(true);
			updateTotalArray();
		} else
		{
			lblSubtotal.setVisible(false);
		}
	}

	public void calculateWhole(String priceForWhole, String quantity)
	{
		if((!quantity.isEmpty()))
		{
			double price = Double.parseDouble(priceForWhole);
			double numberOfFixtures = Double.parseDouble(quantity);
			double subTotal = price * numberOfFixtures;
			lblSubtotal.setText("$ " + new DecimalFormat("##.##").format(subTotal));
			lblSubtotal.setVisible(true);
			updateTotalArray();
		} else
		{
			lblSubtotal.setVisible(false);
		}
	}

	public void updateTotalArray()
	{
		TotalArrayNode tan = tabJPanel.getTotalArray().getArrayNode("Electrical");
		if(tan != null)
		{
			String la = invoiceFormatSentence.getItemAt(invoiceFormatSentence.getSelectedIndex()).toString();
			if(la.equalsIgnoreCase("Custom"))
			{
				la = invoiceCustomSentence.getText();
			}
			tan.setArrayString(la);
			tan.setTotalAmount(new DecimalFormat("##.##").format(Double.parseDouble(lblSubtotal.getText().replace("$", ""))));
			tabJPanel.getTotalArray().paintArray();
		} else
		{
			String la = invoiceFormatSentence.getItemAt(invoiceFormatSentence.getSelectedIndex()).toString();
			if(la.equalsIgnoreCase("Custom"))
			{
				la = invoiceCustomSentence.getText();
			}
			tabJPanel.getTotalArray().addToArray("Electrical", la, new DecimalFormat("##.##").format(Double.parseDouble(lblSubtotal.getText().replace("$", ""))), new HashMap<String, String>());
		}
	}
	
	public void expandInvoiceFormatPanel()
	{
		invoiceFormatPanelExpanded = true;
		invoiceFormatPanel.setBounds(310, 10, 290, 80);
		invoiceFormatPanel.repaint();
		invoiceCustomSentence.setVisible(true);
		invoiceCustomSentence.grabFocus();
		invoiceCustomSentence.repaint();
	}

	public void contractInvoiceFormatPanel()
	{
		invoiceFormatPanelExpanded = false;
		invoiceFormatPanel.setBounds(310, 10, 290, 53);
		invoiceFormatPanel.repaint();
		invoiceCustomSentence.setVisible(false);
		invoiceCustomSentence.repaint();
	}
}
