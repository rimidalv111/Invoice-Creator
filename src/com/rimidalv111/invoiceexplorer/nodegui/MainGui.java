package com.rimidalv111.invoiceexplorer.nodegui;

import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;

import javax.swing.JComboBox;
import javax.swing.JEditorPane;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.MatteBorder;
import javax.swing.border.TitledBorder;

import com.rimidalv111.invoiceexplorer.TabJPanel;
import com.rimidalv111.util.customclasses.CustomJPanel;

public class MainGui extends JPanel
{
	private static final long serialVersionUID = 1L;
	private TabJPanel tabJPanel;

	//start customer info
	private CustomJPanel customerInfoPanel;
	private JLabel customerInfoName;
	private JTextField customerInfoNameField;
	private JLabel customerInfoAddress;
	private JTextField customerInfoAddressField;
	private JLabel customerInfoPhone;
	private JTextField customerInfoPhoneField;
	private JLabel customerInfoEmail;
	private JTextField customerInfoEmailField;
	private JTextField customerInfoCityField;
	private JComboBox<String> customerInfoStateComboBox;
	private JTextField customerInfoZipcodeField;
	//end customer info

	//start invoice loader
	private JScrollPane invoiceViewerScrollPane;
	private JEditorPane invoiceViewerEditorPane;

	//end invoice loader

	public MainGui(TabJPanel tjp)
	{
		super(new GridLayout(1, 0));
		tabJPanel = tjp;

		//start customer info
		customerInfoPanel = new CustomJPanel();
		customerInfoPanel.setBounds(10, 10, 590, 70);
		customerInfoPanel.setLayout(null);
		TitledBorder ctb = new TitledBorder(new MatteBorder(1, 1, 1, 1, Color.GRAY), "Customer Info");
		customerInfoPanel.setBorder(ctb);

		customerInfoName = new JLabel("Name:");
		customerInfoName.setFont(new Font("Tahoma", Font.PLAIN, 14));
		customerInfoName.setBounds(10, 15, 70, 23);
		customerInfoNameField = new JTextField();
		customerInfoNameField.setBounds(84, 17, 120, 20);
		customerInfoNameField.addFocusListener(new FocusAdapter()
		{
			@Override
			public void focusLost(FocusEvent e)
			{
				updateInvoiceNode("[IVT_BT_CN]", customerInfoNameField.getText());
			}
		});
		customerInfoPanel.add(customerInfoName);
		customerInfoPanel.add(customerInfoNameField);

		customerInfoPhone = new JLabel("Phone:");
		customerInfoPhone.setFont(new Font("Tahoma", Font.PLAIN, 14));
		customerInfoPhone.setBounds(224, 15, 70, 23);
		customerInfoPhoneField = new JTextField();
		customerInfoPhoneField.setBounds(274, 17, 120, 20);
		customerInfoPhoneField.addFocusListener(new FocusAdapter()
		{
			@Override
			public void focusLost(FocusEvent e)
			{
				updateInvoiceNode("[IVT_BT_P]", customerInfoPhoneField.getText());
			}
		});
		customerInfoPanel.add(customerInfoPhone);
		customerInfoPanel.add(customerInfoPhoneField);

		customerInfoEmail = new JLabel("Email:");
		customerInfoEmail.setFont(new Font("Tahoma", Font.PLAIN, 14));
		customerInfoEmail.setBounds(415, 15, 100, 23);
		customerInfoEmailField = new JTextField();
		customerInfoEmailField.setBounds(458, 17, 120, 20);
		customerInfoEmailField.addFocusListener(new FocusAdapter()
		{
			@Override
			public void focusLost(FocusEvent e)
			{
				updateInvoiceNode("[IVT_BT_E]", customerInfoEmailField.getText());
			}
		});
		customerInfoPanel.add(customerInfoEmail);
		customerInfoPanel.add(customerInfoEmailField);

		customerInfoAddress = new JLabel("Address:");
		customerInfoAddress.setFont(new Font("Tahoma", Font.PLAIN, 14));
		customerInfoAddress.setBounds(10, 38, 70, 23);
		customerInfoAddressField = new JTextField();
		customerInfoAddressField.setBounds(84, 40, 120, 20);
		customerInfoAddressField.addFocusListener(new FocusAdapter()
		{
			@Override
			public void focusLost(FocusEvent e)
			{
				updateInvoiceNode("[IVT_BT_A]", customerInfoAddressField.getText());
			}
		});
		customerInfoPanel.add(customerInfoAddress);
		customerInfoPanel.add(customerInfoAddressField);

		customerInfoCityField = new JTextField("City");
		customerInfoCityField.setBounds(224, 40, 100, 20);
		customerInfoCityField.addFocusListener(new FocusAdapter()
		{
			@Override
			public void focusGained(FocusEvent e)
			{
				if(customerInfoCityField.getText().equalsIgnoreCase("City"))
				{
					customerInfoCityField.setText("");
				}
			}

			@Override
			public void focusLost(FocusEvent e)
			{
				if(customerInfoCityField.getText().equalsIgnoreCase(""))
				{
					customerInfoCityField.setText("City");
				} else
				{
					updateInvoiceNode("[IVT_BT_CSZ]", customerInfoCityField.getText() + ", " + customerInfoStateComboBox.getSelectedItem().toString() +", " + customerInfoZipcodeField.getText());
				}
			}
		});

		customerInfoPanel.add(customerInfoCityField);

		customerInfoStateComboBox = new JComboBox<String>();
		customerInfoStateComboBox.setEditable(false);
		customerInfoStateComboBox.setBounds(344, 40, 124, 20);
		customerInfoStateComboBox.addItem("Select State");
		addAllStates();
		customerInfoPanel.add(customerInfoStateComboBox);

		customerInfoZipcodeField = new JTextField("Zipcode");
		customerInfoZipcodeField.setBounds(488, 40, 90, 20);
		customerInfoZipcodeField.addFocusListener(new FocusAdapter()
		{
			@Override
			public void focusGained(FocusEvent e)
			{
				if(customerInfoZipcodeField.getText().equalsIgnoreCase("Zipcode"))
				{
					customerInfoZipcodeField.setText("");
				}
			}

			@Override
			public void focusLost(FocusEvent e)
			{
				if(customerInfoZipcodeField.getText().equalsIgnoreCase(""))
				{
					customerInfoZipcodeField.setText("Zipcode");
				} else
				{
					updateInvoiceNode("[IVT_BT_CSZ]", customerInfoCityField.getText() + ", " + customerInfoStateComboBox.getSelectedItem().toString() +", " + customerInfoZipcodeField.getText());
				}
			}
		});

		customerInfoPanel.add(customerInfoZipcodeField);
		//end customer info

		//start invoice loader
		invoiceViewerScrollPane = new JScrollPane();
		invoiceViewerScrollPane.setBounds(10, 90, 590, 370);

		invoiceViewerEditorPane = new JEditorPane("text/html", tabJPanel.getInvoiceVarWork().getDocument().toString());
		invoiceViewerEditorPane.setFocusable(false);
		invoiceViewerEditorPane.setEditable(false);
		invoiceViewerScrollPane.setViewportView(invoiceViewerEditorPane);
		//end invoice loader
	}

	public void enableGui()
	{
		tabJPanel.add(customerInfoPanel);
		tabJPanel.add(invoiceViewerScrollPane);

		new Thread()
		{
			public void run()
			{
				tabJPanel.getInvoiceVarWork().calculateTotal();
				tabJPanel.getInvoiceVarWork().setCompanyDetails();
				if(tabJPanel.getTotalArray().updateMainInvoice()) //add the new stuff to the table only when main gui is selected cuz you obv cant change the price while in main gui
				{
					invoiceViewerEditorPane.setText(tabJPanel.getInvoiceVarWork().getDocument().toString());
				}
			}
		}.start();

		tabJPanel.validateRepaint();
	}

	public void disableGui()
	{
		tabJPanel.remove(customerInfoPanel);
		tabJPanel.remove(invoiceViewerScrollPane);

		tabJPanel.validateRepaint();
	}

	public void updateInvoiceNode(final String node, final String var)
	{
		new Thread()
		{
			public void run()
			{
				tabJPanel.getInvoiceVarWork().setNode(node, var);
				invoiceViewerEditorPane.setText(tabJPanel.getInvoiceVarWork().getDocument().toString());
			}
		}.start();
	}
	
	public void addAllStates()
	{
		customerInfoStateComboBox.addItem("Alabama");
		customerInfoStateComboBox.addItem("Alaska");
		customerInfoStateComboBox.addItem("Arizona");
		customerInfoStateComboBox.addItem("Arkansas");
		customerInfoStateComboBox.addItem("California");
		customerInfoStateComboBox.addItem("Colorado");
		customerInfoStateComboBox.addItem("Connecticut");
		customerInfoStateComboBox.addItem("Delaware");
		customerInfoStateComboBox.addItem("Florida");
		customerInfoStateComboBox.addItem("Georgia");
		customerInfoStateComboBox.addItem("Hawaii");
		customerInfoStateComboBox.addItem("Idaho");
		customerInfoStateComboBox.addItem("Illinois");
		customerInfoStateComboBox.addItem("Indiana");
		customerInfoStateComboBox.addItem("Iowa");
		customerInfoStateComboBox.addItem("Kansas");
		customerInfoStateComboBox.addItem("Kentucky");
		customerInfoStateComboBox.addItem("Louisiana");
		customerInfoStateComboBox.addItem("Maine");
		customerInfoStateComboBox.addItem("Maryland");
		customerInfoStateComboBox.addItem("Massachusetts");
		customerInfoStateComboBox.addItem("Michigan");
		customerInfoStateComboBox.addItem("Minnesota");
		customerInfoStateComboBox.addItem("Mississippi");
		customerInfoStateComboBox.addItem("Missouri");
		customerInfoStateComboBox.addItem("Montana");
		customerInfoStateComboBox.addItem("Nebraska");
		customerInfoStateComboBox.addItem("Nevada");
		customerInfoStateComboBox.addItem("New Hampshire");
		customerInfoStateComboBox.addItem("New Jersey");
		customerInfoStateComboBox.addItem("New Mexico");
		customerInfoStateComboBox.addItem("New York");
		customerInfoStateComboBox.addItem("North Carolina");
		customerInfoStateComboBox.addItem("North Dakota");
		customerInfoStateComboBox.addItem("Ohio");
		customerInfoStateComboBox.addItem("Oklahoma");
		customerInfoStateComboBox.addItem("Oregon");
		customerInfoStateComboBox.addItem("Pennsylvania");
		customerInfoStateComboBox.addItem("Rhode Island");
		customerInfoStateComboBox.addItem("South Carolina");
		customerInfoStateComboBox.addItem("South Dakota");
		customerInfoStateComboBox.addItem("Tennessee");
		customerInfoStateComboBox.addItem("Texas");
		customerInfoStateComboBox.addItem("Utah");
		customerInfoStateComboBox.addItem("Vermont");
		customerInfoStateComboBox.addItem("Virginia");
		customerInfoStateComboBox.addItem("Washington");
		customerInfoStateComboBox.addItem("West Virginia");
		customerInfoStateComboBox.addItem("Wisconsin");
		customerInfoStateComboBox.addItem("Wyoming");
	}

	/**
	 * @return the tabJPanel
	 */
	public TabJPanel getTabJPanel()
	{
		return tabJPanel;
	}

	/**
	 * @param tabJPanel
	 *            the tabJPanel to set
	 */
	public void setTabJPanel(TabJPanel tabJPanel)
	{
		this.tabJPanel = tabJPanel;
	}

	/**
	 * @return the customerInfoPanel
	 */
	public CustomJPanel getCustomerInfoPanel()
	{
		return customerInfoPanel;
	}

	/**
	 * @param customerInfoPanel
	 *            the customerInfoPanel to set
	 */
	public void setCustomerInfoPanel(CustomJPanel customerInfoPanel)
	{
		this.customerInfoPanel = customerInfoPanel;
	}

	/**
	 * @return the customerInfoName
	 */
	public JLabel getCustomerInfoName()
	{
		return customerInfoName;
	}

	/**
	 * @param customerInfoName
	 *            the customerInfoName to set
	 */
	public void setCustomerInfoName(JLabel customerInfoName)
	{
		this.customerInfoName = customerInfoName;
	}

	/**
	 * @return the customerInfoNameField
	 */
	public JTextField getCustomerInfoNameField()
	{
		return customerInfoNameField;
	}

	/**
	 * @param customerInfoNameField
	 *            the customerInfoNameField to set
	 */
	public void setCustomerInfoNameField(JTextField customerInfoNameField)
	{
		this.customerInfoNameField = customerInfoNameField;
	}

	/**
	 * @return the customerInfoAddress
	 */
	public JLabel getCustomerInfoAddress()
	{
		return customerInfoAddress;
	}

	/**
	 * @param customerInfoAddress
	 *            the customerInfoAddress to set
	 */
	public void setCustomerInfoAddress(JLabel customerInfoAddress)
	{
		this.customerInfoAddress = customerInfoAddress;
	}

	/**
	 * @return the customerInfoAddressField
	 */
	public JTextField getCustomerInfoAddressField()
	{
		return customerInfoAddressField;
	}

	/**
	 * @param customerInfoAddressField
	 *            the customerInfoAddressField to set
	 */
	public void setCustomerInfoAddressField(JTextField customerInfoAddressField)
	{
		this.customerInfoAddressField = customerInfoAddressField;
	}

	/**
	 * @return the customerInfoPhone
	 */
	public JLabel getCustomerInfoPhone()
	{
		return customerInfoPhone;
	}

	/**
	 * @param customerInfoPhone
	 *            the customerInfoPhone to set
	 */
	public void setCustomerInfoPhone(JLabel customerInfoPhone)
	{
		this.customerInfoPhone = customerInfoPhone;
	}

	/**
	 * @return the customerInfoPhoneField
	 */
	public JTextField getCustomerInfoPhoneField()
	{
		return customerInfoPhoneField;
	}

	/**
	 * @param customerInfoPhoneField
	 *            the customerInfoPhoneField to set
	 */
	public void setCustomerInfoPhoneField(JTextField customerInfoPhoneField)
	{
		this.customerInfoPhoneField = customerInfoPhoneField;
	}

	/**
	 * @return the customerInfoEmail
	 */
	public JLabel getCustomerInfoEmail()
	{
		return customerInfoEmail;
	}

	/**
	 * @param customerInfoEmail
	 *            the customerInfoEmail to set
	 */
	public void setCustomerInfoEmail(JLabel customerInfoEmail)
	{
		this.customerInfoEmail = customerInfoEmail;
	}

	/**
	 * @return the customerInfoEmailField
	 */
	public JTextField getCustomerInfoEmailField()
	{
		return customerInfoEmailField;
	}

	/**
	 * @param customerInfoEmailField
	 *            the customerInfoEmailField to set
	 */
	public void setCustomerInfoEmailField(JTextField customerInfoEmailField)
	{
		this.customerInfoEmailField = customerInfoEmailField;
	}

	/**
	 * @return the customerInfoCityField
	 */
	public JTextField getCustomerInfoCityField()
	{
		return customerInfoCityField;
	}

	/**
	 * @param customerInfoCityField
	 *            the customerInfoCityField to set
	 */
	public void setCustomerInfoCityField(JTextField customerInfoCityField)
	{
		this.customerInfoCityField = customerInfoCityField;
	}

	/**
	 * @return the customerInfoStateComboBox
	 */
	public JComboBox<String> getCustomerInfoStateComboBox()
	{
		return customerInfoStateComboBox;
	}

	/**
	 * @param customerInfoStateComboBox
	 *            the customerInfoStateComboBox to set
	 */
	public void setCustomerInfoStateComboBox(JComboBox<String> customerInfoStateComboBox)
	{
		this.customerInfoStateComboBox = customerInfoStateComboBox;
	}

	/**
	 * @return the customerInfoZipcodeField
	 */
	public JTextField getCustomerInfoZipcodeField()
	{
		return customerInfoZipcodeField;
	}

	/**
	 * @param customerInfoZipcodeField
	 *            the customerInfoZipcodeField to set
	 */
	public void setCustomerInfoZipcodeField(JTextField customerInfoZipcodeField)
	{
		this.customerInfoZipcodeField = customerInfoZipcodeField;
	}

	/**
	 * @return the invoiceViewerScrollPane
	 */
	public JScrollPane getInvoiceViewerScrollPane()
	{
		return invoiceViewerScrollPane;
	}

	/**
	 * @param invoiceViewerScrollPane
	 *            the invoiceViewerScrollPane to set
	 */
	public void setInvoiceViewerScrollPane(JScrollPane invoiceViewerScrollPane)
	{
		this.invoiceViewerScrollPane = invoiceViewerScrollPane;
	}

	/**
	 * @return the invoiceViewerEditorPane
	 */
	public JEditorPane getInvoiceViewerEditorPane()
	{
		return invoiceViewerEditorPane;
	}

	/**
	 * @param invoiceViewerEditorPane
	 *            the invoiceViewerEditorPane to set
	 */
	public void setInvoiceViewerEditorPane(JEditorPane invoiceViewerEditorPane)
	{
		this.invoiceViewerEditorPane = invoiceViewerEditorPane;
	}
}
