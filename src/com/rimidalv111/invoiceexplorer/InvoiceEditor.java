package com.rimidalv111.invoiceexplorer;

import java.io.File;
import java.util.HashMap;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import com.rimidalv111.images.ImageHelper;

public class InvoiceEditor
{
	private TabJPanel tabJPanel;
	private String invoiceName;
	private String invoiceTemplatePath;

	private Document htmlDocument;
	private Elements tables;
	private Element descriptionTable;

	private Element openingEntry;
	private Element middleEntry;
	private Element closingEntry;

	private Elements elementColumn;
	private String[] fullRowTemplate;
	private HashMap<String, String[]> allRows = new HashMap<String, String[]>();

	public InvoiceEditor(TabJPanel tjp, String in, String itp)
	{
		tabJPanel = tjp;
		invoiceName = in;
		invoiceTemplatePath = itp;
	}

	public void loadSetUpInvoice()
	{
		//							invoiceTemplatePath
		File input = new File(ImageHelper.class.getResource("basic-invoice.html").getFile());
		if(!input.exists())
		{
			System.out.println("Cannot find the invoice template!");
			return;
		}
		try
		{
			htmlDocument = Jsoup.parse(input, "UTF-8", "");
		} catch(Exception io)
		{
			io.printStackTrace();
		}
		if(htmlDocument != null)
		{
			tables = htmlDocument.select("table"); //find the description table

			descriptionTable = findDescriptionTabel(htmlDocument);

			if(descriptionTable != null)
			{
				//get the opening entry
				openingEntry = descriptionTable.select("tr").first();
				//get middle entry
				middleEntry = descriptionTable.select("tr").get(2);
				//get closing entry
				closingEntry = descriptionTable.select("tr").last();

				//now we want to circle through the middle entry only (there are 3 lines, each line is a column)
				elementColumn = middleEntry.getAllElements();
				elementColumn.remove(0); //remove the first index cuz it just prints full code

				fullRowTemplate = new String[3];

				int columnCounter = 0;
				for(Element r : elementColumn)
				{
					fullRowTemplate[columnCounter] = r.toString();
					columnCounter++;
				}

				//remove all of the empty boxes now in description option
				Elements rows = descriptionTable.select("tr");
				for(Element r : rows)
				{
					if(r != openingEntry && r != closingEntry)
					{
						r.remove();
					}
				}
				//System.out.println(htmlDocument.toString());
			} else
			{
				System.out.println("failed to find description table, invoice not compatible");
			}

		}
	}

	public Element findDescriptionTabel(Document doc)
	{
		Elements tempTables = doc.select("table"); //find the description table

		for(Element ta : tempTables)
		{
			Element foundTable = ta.select("tr").first().select("td").first();
			String foundTableNames = foundTable.text();
			if(foundTableNames.equalsIgnoreCase("description"))
			{
				//successfully found description table
				return ta;
			}
		}
		return null;
	}

	public String parseSubdescription(String description, String[] subDescription)
	{
		for(String s : subDescription)
		{
			description += " <br> &nbsp&nbsp&nbsp&nbsp&nbsp <i>" + s + "</i>";
		}
		return description;
	}

	public void editRow(String gui, String description, String quantity, String total)
	{
		if(allRows.containsKey(gui))
		{
			String[] fullRow = fullRowTemplate.clone();
			if(!description.equalsIgnoreCase(""))
			{
				allRows.get(gui)[0] = fullRow[0].substring(0, fullRow[0].length() - 5) + description + "</td>";
			}
			if(!quantity.equalsIgnoreCase(""))
			{
				allRows.get(gui)[1] = fullRow[1].substring(0, fullRow[1].length() - 5) + quantity + "</td>";
			}
			if(!total.equalsIgnoreCase(""))
			{
				allRows.get(gui)[2] = fullRow[2].substring(0, fullRow[2].length() - 5) + total + "</td>";
			}
		} else
		{
			addRow(gui, description, quantity, total);
		}
	}

	public void addRow(String gui, String description, String quantity, String total)
	{
		String[] fullRow = fullRowTemplate.clone();
		fullRow[0] = fullRow[0].substring(0, fullRow[0].length() - 5) + description + "</td>";
		fullRow[1] = fullRow[1].substring(0, fullRow[1].length() - 5) + quantity + "</td>";
		fullRow[2] = fullRow[2].substring(0, fullRow[2].length() - 5) + total + "</td>";
		allRows.put(gui, fullRow);
	}

	public void removeRow(String gui)
	{
		if(allRows.containsKey(gui))
		{
			allRows.remove(gui);
		}
	}

	public void insertVariable(Document doc, String toReplace, String replaceWith)
	{
		try
		{
			Element replacing = doc.select("div:contains(" + toReplace + ")").first(); //first find the portion the toReplace is
			if(replacing.html().contains(toReplace))
			{
				replacing.html(replacing.html().replace(toReplace, replaceWith)); //then replace the var
			} else
			{
				System.out.println("Cannot find node to replace: " + toReplace);
			}
		} catch(Exception io)
		{
			System.out.println("Cannot find node to replace: " + toReplace);
		}
	}

	//put our created rows into the document (only temp so we can use htmlDocument as a template)
	public Document htmlWork()
	{
		Document doc = htmlDocument.clone(); //this is the temp table
		Element descriptionTabel = findDescriptionTabel(doc);

		String htmlCode = "";

		htmlCode += openingEntry.toString(); //add the opening entry

		for(String g : allRows.keySet())
		{
			String htmlRow = "<tr>";

			String[] row = allRows.get(g);
			for(String s : row)
			{
				htmlRow += " " + s; //add the middle entry's
			}

			htmlCode += " " + htmlRow + "</tr>";

		}
		htmlCode += " " + closingEntry.toString(); //add the closing entry

		descriptionTabel.html(htmlCode);
		return doc;
	}

	//TODO
	public void replaceTag(String tag, String replace)
	{

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
	 * @return the invoiceName
	 */
	public String getInvoiceName()
	{
		return invoiceName;
	}

	/**
	 * @param invoiceName
	 *            the invoiceName to set
	 */
	public void setInvoiceName(String invoiceName)
	{
		this.invoiceName = invoiceName;
	}

	/**
	 * @return the invoiceTemplatePath
	 */
	public String getInvoiceTemplatePath()
	{
		return invoiceTemplatePath;
	}

	/**
	 * @param invoiceTemplatePath
	 *            the invoiceTemplatePath to set
	 */
	public void setInvoiceTemplatePath(String invoiceTemplatePath)
	{
		this.invoiceTemplatePath = invoiceTemplatePath;
	}

	/**
	 * @return the htmlDocument
	 */
	public Document getHtmlDocument()
	{
		return htmlDocument;
	}

	/**
	 * @param htmlDocument
	 *            the htmlDocument to set
	 */
	public void setHtmlDocument(Document htmlDocument)
	{
		this.htmlDocument = htmlDocument;
	}

	/**
	 * @return the tables
	 */
	public Elements getTables()
	{
		return tables;
	}

	/**
	 * @param tables
	 *            the tables to set
	 */
	public void setTables(Elements tables)
	{
		this.tables = tables;
	}

	/**
	 * @return the descriptionTable
	 */
	public Element getDescriptionTable()
	{
		return descriptionTable;
	}

	/**
	 * @param descriptionTable
	 *            the descriptionTable to set
	 */
	public void setDescriptionTable(Element descriptionTable)
	{
		this.descriptionTable = descriptionTable;
	}

	/**
	 * @return the openingEntry
	 */
	public Element getOpeningEntry()
	{
		return openingEntry;
	}

	/**
	 * @param openingEntry
	 *            the openingEntry to set
	 */
	public void setOpeningEntry(Element openingEntry)
	{
		this.openingEntry = openingEntry;
	}

	/**
	 * @return the middleEntry
	 */
	public Element getMiddleEntry()
	{
		return middleEntry;
	}

	/**
	 * @param middleEntry
	 *            the middleEntry to set
	 */
	public void setMiddleEntry(Element middleEntry)
	{
		this.middleEntry = middleEntry;
	}

	/**
	 * @return the closingEntry
	 */
	public Element getClosingEntry()
	{
		return closingEntry;
	}

	/**
	 * @param closingEntry
	 *            the closingEntry to set
	 */
	public void setClosingEntry(Element closingEntry)
	{
		this.closingEntry = closingEntry;
	}

	/**
	 * @return the elementColumn
	 */
	public Elements getElementColumn()
	{
		return elementColumn;
	}

	/**
	 * @param elementColumn
	 *            the elementColumn to set
	 */
	public void setElementColumn(Elements elementColumn)
	{
		this.elementColumn = elementColumn;
	}

	/**
	 * @return the fullRowTemplate
	 */
	public String[] getFullRowTemplate()
	{
		return fullRowTemplate;
	}

	/**
	 * @param fullRowTemplate
	 *            the fullRowTemplate to set
	 */
	public void setFullRowTemplate(String[] fullRowTemplate)
	{
		this.fullRowTemplate = fullRowTemplate;
	}

	/**
	 * @return the allRows
	 */
	public HashMap<String, String[]> getAllRows()
	{
		return allRows;
	}

	/**
	 * @param allRows
	 *            the allRows to set
	 */
	public void setAllRows(HashMap<String, String[]> allRows)
	{
		this.allRows = allRows;
	}
}
