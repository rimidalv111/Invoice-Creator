package com.rimidalv111.invoiceexplorer;

import java.text.DecimalFormat;
import java.util.HashMap;
import java.util.Random;

import org.jsoup.nodes.Document;

public class InvoiceVariableWork
{
	private TabJPanel tabJPanel;

	private HashMap<String, String> variables = new HashMap<String, String>();
	private Document invoiceDocument;

	private int invoiceNumber;
	private String invoiceDate;
	
	public InvoiceVariableWork(TabJPanel tjp)
	{
		tabJPanel = tjp;

		Random r = new Random();
		invoiceNumber = r.nextInt(99999999);
		
		//put defualt variables as empty
		variables.put("[IVT_C_N]", ""); //Company Name
		variables.put("[IVT_C_A]", ""); //Company Address
		variables.put("[IVT_C_CSZ]", ""); //Company City, State, Zip
		variables.put("[IVT_C_P]", ""); //Company Phone
		variables.put("[IVT_C_E]", ""); //Company Email

		variables.put("[IVT_ID_N]", ""); //Invoice Number
		variables.put("[IVT_ID_D]", ""); //Invoice Date

		variables.put("[IVT_BT_CN]", ""); //Customer Name
		//variables.put("[IVT_BT_CPN]", ""); //Customer Company Name
		variables.put("[IVT_BT_A]", ""); //Customer Address
		variables.put("[IVT_BT_CSZ]", ""); //Customer City, State, Zip
		variables.put("[IVT_BT_P]", ""); //Customer Phone
		variables.put("[IVT_BT_E]", ""); //Customer Email

		variables.put("[IVT_TOTAL]", ""); //Invoice Total

		setCompanyDetails(); //load customer details right away (will be left blank if not set)
	}

	public void parseEditCreateDocument()
	{
		invoiceDocument = tabJPanel.getInvoiceEditor().htmlWork(); //fill in the description table
		for(String node : variables.keySet()) //fill in the nodes and their variables
		{
			tabJPanel.getInvoiceEditor().insertVariable(invoiceDocument, node, variables.get(node));
		}
	}

	public Document getDocument()
	{
		parseEditCreateDocument();
		return invoiceDocument;
	}

	public boolean setNode(String node, String value)
	{
		if(variables.containsKey(node))
		{
			variables.put(node, value);
			return true;
		}
		return false;
	}

	public void setCompanyDetails()
	{
		variables.put("[IVT_C_N]", tabJPanel.getInvoiceExplorer().getUtil().getMain().getCompanyDetailsFrame().getNameField().getText()); //Company Name
		variables.put("[IVT_C_A]", tabJPanel.getInvoiceExplorer().getUtil().getMain().getCompanyDetailsFrame().getAddressField().getText()); //Company Address
		if(!tabJPanel.getInvoiceExplorer().getUtil().getMain().getCompanyDetailsFrame().getCityField().getText().equalsIgnoreCase("") && !tabJPanel.getInvoiceExplorer().getUtil().getMain().getCompanyDetailsFrame().getCustomerInfoStateComboBox().getSelectedItem().toString().equalsIgnoreCase("Select State") && !tabJPanel.getInvoiceExplorer().getUtil().getMain().getCompanyDetailsFrame().getZipField().getText().equalsIgnoreCase(""))
		{
			variables.put("[IVT_C_CSZ]", tabJPanel.getInvoiceExplorer().getUtil().getMain().getCompanyDetailsFrame().getCityField().getText() + ", " + tabJPanel.getInvoiceExplorer().getUtil().getMain().getCompanyDetailsFrame().getCustomerInfoStateComboBox().getSelectedItem().toString() + ", " + tabJPanel.getInvoiceExplorer().getUtil().getMain().getCompanyDetailsFrame().getZipField().getText()); //Company City, State, Zip
		}
		variables.put("[IVT_C_P]", tabJPanel.getInvoiceExplorer().getUtil().getMain().getCompanyDetailsFrame().getPhoneField().getText()); //Company Phone
		variables.put("[IVT_C_E]", tabJPanel.getInvoiceExplorer().getUtil().getMain().getCompanyDetailsFrame().getEmailField().getText()); //Company Email
	}

	public void calculateTotal()
	{
		variables.put("[IVT_TOTAL]", new DecimalFormat("##.##").format(tabJPanel.getTotalArray().tallyTotalCost()));
	}
	
	public void setInvoiceIdDate()
	{
		variables.put("[IVT_ID_N]", invoiceNumber + ""); //Invoice Number
		variables.put("[IVT_ID_D]", tabJPanel.getInvoiceExplorer().getUtil().getClock().getDate().toString()); //Invoice Date
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
	 * @return the variables
	 */
	public HashMap<String, String> getVariables()
	{
		return variables;
	}

	/**
	 * @param variables
	 *            the variables to set
	 */
	public void setVariables(HashMap<String, String> variables)
	{
		this.variables = variables;
	}

	/**
	 * @return the invoiceDocument
	 */
	public Document getInvoiceDocument()
	{
		return invoiceDocument;
	}

	/**
	 * @param invoiceDocument
	 *            the invoiceDocument to set
	 */
	public void setInvoiceDocument(Document invoiceDocument)
	{
		this.invoiceDocument = invoiceDocument;
	}

}
