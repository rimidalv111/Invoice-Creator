package com.rimidalv111.util;

import java.awt.Font;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.swing.JLabel;
import javax.swing.JSeparator;
import javax.swing.SwingConstants;

import com.rimidalv111.invoiceexplorer.TabJPanel;

public class TotalArray
{
	private String tabName;
	private TabJPanel tabJPanel;
	private int x;
	private int y;
	private int width;
	//private int height; for later use when I add a scroll bar
	private String font;
	private int size;
	private boolean paintDown;
	private int textPadding;
	private int lastLabelPositionX;
	private int lastLabelPositionY;
	private JSeparator totalSeparator;
	private JLabel totalLabel;

	public void setUp(String tabN, TabJPanel comp, int x, int y, int width, int height, String font, int size)
	{
		tabName = tabN;
		tabJPanel = comp;
		this.x = x;
		this.y = y;
		this.width = width;
		//this.height = height;
		this.font = font;
		this.size = size;
		paintDown = false;
		textPadding = 4;
		lastLabelPositionX = 0;
		lastLabelPositionY = 0;

		//line seperator + total label
		totalSeparator = new JSeparator();
		totalSeparator.setBounds(x, y + size + 5, width, 2);

		totalLabel = new JLabel("TOTAL: " + "$" + new DecimalFormat("##.##").format(tallyTotalCost()));
		totalLabel.setHorizontalAlignment(SwingConstants.RIGHT);
		totalLabel.setFont(new Font("Tahoma", Font.PLAIN, 18));
		totalLabel.setBounds(x, y + size + 15, width, 15);
	}

	public void enableTotalLabel()
	{
		tabJPanel.add(totalSeparator);
		tabJPanel.add(totalLabel);
		addArray();
	}

	public void disableTotalLabel()
	{
		tabJPanel.remove(totalSeparator);
		tabJPanel.remove(totalLabel);
		removeArray();
	}

	private HashMap<TotalArrayNode, List<JLabel>> arrayString = new HashMap<TotalArrayNode, List<JLabel>>();

	public void paintArray()
	{
		cleanArray();
		lastLabelPositionX = x;
		lastLabelPositionY = y;
		for(TotalArrayNode tan : arrayString.keySet())
		{
			arrayString.get(tan).clear();

			if(tan.getExtraLines().size() > 0)
			{
				for(String extraLabel : tan.getExtraLines().keySet())
				{
					String theExtraLineTotal = tan.getExtraLines().get(extraLabel);
					//array string label
					JLabel extraLineLabel = new JLabel("- " + extraLabel);
					extraLineLabel.setFont(new Font(font, Font.ITALIC, size - 1));
					extraLineLabel.setBounds(lastLabelPositionX + 25, lastLabelPositionY, width, size + 5);
					extraLineLabel.setVisible(true);
					tabJPanel.add(extraLineLabel);

					//total amount label
					JLabel extraLineTotal = new JLabel(theExtraLineTotal + " ");
					extraLineTotal.setFont(new Font(font, Font.ITALIC, size - 1));
					extraLineTotal.setHorizontalAlignment(SwingConstants.RIGHT);
					extraLineTotal.setBounds(x, lastLabelPositionY, width, size + 5);
					extraLineTotal.setVisible(true);
					tabJPanel.add(extraLineTotal);

					arrayString.get(tan).add(extraLineLabel);
					arrayString.get(tan).add(extraLineTotal);

					if(paintDown)
					{
						lastLabelPositionY += size - 2;
					} else
					{
						lastLabelPositionY -= size - 2;
					}
				}
			}

			String arraySt = tan.getArrayString();
			String totalAmount = tan.getTotalAmount();

			if(arraySt.length() >= 35)
			{
				arraySt = arraySt.substring(0, 32) + "...";
			}
			//array string label
			JLabel label = new JLabel(arraySt);
			label.setFont(new Font(font, Font.PLAIN, size));
			label.setBounds(lastLabelPositionX, lastLabelPositionY, width, size + 5);
			label.setVisible(true);
			tabJPanel.add(label);

			//total amount label
			JLabel totalAmountLabel = new JLabel(totalAmount);
			totalAmountLabel.setFont(new Font(font, Font.PLAIN, size));
			totalAmountLabel.setHorizontalAlignment(SwingConstants.RIGHT);
			totalAmountLabel.setBounds(x, lastLabelPositionY, width, size + 5);
			totalAmountLabel.setVisible(true);
			tabJPanel.add(totalAmountLabel);

			arrayString.get(tan).add(label);
			arrayString.get(tan).add(totalAmountLabel);

			if(paintDown)
			{
				lastLabelPositionY += size + textPadding;
			} else
			{
				lastLabelPositionY -= size + textPadding;
			}
		}
		totalLabel.setText("TOTAL: " + "$" + new DecimalFormat("##.##").format(tallyTotalCost()));
		tabJPanel.repaint();
	}

	public Double tallyTotalCost()
	{
		double total = 0;
		for(TotalArrayNode tan : arrayString.keySet())
		{
			total += Double.parseDouble(tan.getTotalAmount());
			for(String extraLines : tan.getExtraLines().keySet())
			{
				total += Double.parseDouble(tan.getExtraLines().get(extraLines));
			}
		}
		return total;
	}

	public Double tallyTotalCostForGui(String gui)
	{
		double total = 0;
		for(TotalArrayNode tan : arrayString.keySet())
		{
			if(tan.getGuiName().equalsIgnoreCase(gui))
			{
				total += Double.parseDouble(tan.getTotalAmount());
				for(String extraLines : tan.getExtraLines().keySet())
				{
					total += Double.parseDouble(tan.getExtraLines().get(extraLines));
				}
			}
		}
		return total;
	}

	public void addArray()
	{
		for(TotalArrayNode tan : arrayString.keySet())
		{
			for(JLabel label : arrayString.get(tan))
			{
				tabJPanel.add(label);
			}
		}
	}

	public void removeArray()
	{
		for(TotalArrayNode tan : arrayString.keySet())
		{
			for(JLabel label : arrayString.get(tan))
			{
				tabJPanel.remove(label);
			}
		}
	}

	public void addToArray(String gui, String arraySt, String total, HashMap<String, String> extraLines)
	{
		boolean found = false;
		for(TotalArrayNode tan : arrayString.keySet())
		{
			if(tan.getGuiName().equalsIgnoreCase(gui))
			{
				found = true;
				tan.setArrayString(arraySt);
				tan.setTotalAmount(total);
				tan.setExtraLines(extraLines);
			}
		}
		if(!found)
		{
			TotalArrayNode tan = new TotalArrayNode(gui, arraySt, total, extraLines);
			arrayString.put(tan, new ArrayList<JLabel>());
		}
		paintArray();
	}

	public void removeFromArray(String gui, String arraySt, String total, HashMap<String, String> extraLines)
	{
		TotalArrayNode tan = new TotalArrayNode(gui, arraySt, total, extraLines);

		if(arrayString.containsKey(tan))
		{
			for(JLabel lb : arrayString.get(tan))
			{
				tabJPanel.remove(lb);
			}
			tabJPanel.repaint();
			arrayString.remove(tan);

			if(paintDown)
			{
				lastLabelPositionY -= size + textPadding;
			} else
			{
				lastLabelPositionY += size + textPadding;
			}
			paintArray();
		}
	}

	public void cleanArray()
	{
		for(TotalArrayNode tan : arrayString.keySet())
		{
			for(JLabel ln : arrayString.get(tan))
			{
				tabJPanel.remove(ln);
			}
		}
		tabJPanel.repaint();
	}

	public TotalArrayNode getArrayNode(String guiName)
	{
		for(TotalArrayNode tan : arrayString.keySet())
		{
			if(tan.getGuiName().equalsIgnoreCase(guiName))
			{
				return tan;
			}
		}
		return null;
	}

	public boolean updateMainInvoice() //adds to the table from the total array to the invoice table
	{
		boolean shouldRefresh = false;
		for(TotalArrayNode tan : arrayString.keySet())
		{
			//update the invoice visually in main gui
			String[] extraInvoiceLines = new String[tan.getExtraLines().size()];

			int count = 0;
			for(String extraLabel : tan.getExtraLines().keySet())
			{
				extraInvoiceLines[count] = extraLabel;
				count++;
			}
			tabJPanel.getInvoiceEditor().editRow(tan.getGuiName(), tabJPanel.getInvoiceEditor().parseSubdescription(tan.getArrayString(), extraInvoiceLines), "", "$" + new DecimalFormat("##.##").format(tallyTotalCostForGui(tan.getGuiName())));
			shouldRefresh = true;
		}
		return shouldRefresh;
	}

	public String getTabName()
	{
		return tabName;
	}

	public void setTabName(String tabName)
	{
		this.tabName = tabName;
	}

	public int getX()
	{
		return x;
	}

	public void setX(int x)
	{
		this.x = x;
	}

	public int getY()
	{
		return y;
	}

	public void setY(int y)
	{
		this.y = y;
	}

	public String getFont()
	{
		return font;
	}

	public void setFont(String font)
	{
		this.font = font;
	}

	public int getSize()
	{
		return size;
	}

	public void setSize(int size)
	{
		this.size = size;
	}

	public int getLastLabelPositionX()
	{
		return lastLabelPositionX;
	}

	public void setLastLabelPositionX(int lastLabelPositionX)
	{
		this.lastLabelPositionX = lastLabelPositionX;
	}

	public int getLastLabelPositionY()
	{
		return lastLabelPositionY;
	}

	public void setLastLabelPositionY(int lastLabelPositionY)
	{
		this.lastLabelPositionY = lastLabelPositionY;
	}

	public TabJPanel gettabJPanel()
	{
		return tabJPanel;
	}

	public void settabJPanel(TabJPanel tabJPanel)
	{
		this.tabJPanel = tabJPanel;
	}

	public int getTextPadding()
	{
		return textPadding;
	}

	public void setTextPadding(int textPadding)
	{
		this.textPadding = textPadding;
	}

	public boolean isPaintDown()
	{
		return paintDown;
	}

	public void setPaintDown(boolean paintDown)
	{
		this.paintDown = paintDown;
	}

	public int getWidth()
	{
		return width;
	}

	public void setWidth(int width)
	{
		this.width = width;
	}

	public JSeparator getTotalSeparator()
	{
		return totalSeparator;
	}

	public void setTotalSeparator(JSeparator totalSeparator)
	{
		this.totalSeparator = totalSeparator;
	}

	public JLabel getTotalLabel()
	{
		return totalLabel;
	}

	public void setTotalLabel(JLabel totalLabel)
	{
		this.totalLabel = totalLabel;
	}

	public HashMap<TotalArrayNode, List<JLabel>> getArrayString()
	{
		return arrayString;
	}

	public void setArrayString(HashMap<TotalArrayNode, List<JLabel>> arrayString)
	{
		this.arrayString = arrayString;
	}
}
