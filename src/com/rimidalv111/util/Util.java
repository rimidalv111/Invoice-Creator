package com.rimidalv111.util;

import java.awt.Desktop;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URISyntaxException;
import java.net.URL;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JEditorPane;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

import com.rimidalv111.images.ImageHelper;
import com.rimidalv111.invoiceexplorer.InvoiceExplorer;
import com.rimidalv111.main.Main;
import com.rimidalv111.util.print.PrintPreview;

public class Util
{
	private static Main main;
	private static Clock clock;
	private InvoiceExplorer invoiceExplorer;

	private Double javaVersion;
	private Double osVersion;

	public Util(Main m)
	{
		main = m;
		clock = new Clock(this);
		invoiceExplorer = new InvoiceExplorer(this);
	}

	public void openWebpage(String url)
	{
		try
		{
			Desktop.getDesktop().browse(new URL(url).toURI());
		} catch(URISyntaxException e)
		{
			e.printStackTrace();
		} catch(MalformedURLException e)
		{
			e.printStackTrace();
		} catch(IOException e)
		{
			e.printStackTrace();
		}
	}

	//add to all try/catch statements
	public static void showError(Exception e)
	{
		String report = "Please report this error immediately. \n_____________________________________________________\n" + "Error: " + e.getClass() + "\n" + "Reason: " + e.getMessage() + "\n" + "Time: " + getClock().getTime() + "\n\n";
		for(int i = 0; i < e.getStackTrace().length; i++)
		{
			report = report + e.getStackTrace()[i] + "\n";
		}
		JTextArea textArea = new JTextArea(20, 45);
		textArea.setText(report);
		textArea.setEditable(false);
		JScrollPane scrollPane = new JScrollPane(textArea);
		JOptionPane.showMessageDialog(main.getFrame(), scrollPane, "Error: " + e.getClass(), JOptionPane.WARNING_MESSAGE);
	}

	public void printPreview(final JEditorPane je, final String invoiceID)
	{
		new PrintPreview(je, invoiceID, this, false);
	}

	public void startClock()
	{
		clock.start();
	}

	public boolean isInteger(String i)
	{
		try
		{
			Integer.parseInt(i);
			return true;
		} catch(Exception io)
		{
			return false;
		}
	}

	public boolean isDouble(String i)
	{
		try
		{
			Double.parseDouble(i);
			return true;
		} catch(Exception io)
		{
			return false;
		}
	}

	public double getJavaVersion()
	{
		if(javaVersion == null)
		{
			try
			{
				String ver = System.getProperty("java.version");
				String version = "";
				boolean firstPoint = true;
				for(int i = 0; i < ver.length(); i++)
				{
					if(ver.charAt(i) == '.')
					{
						if(firstPoint)
						{
							version = version + ver.charAt(i);
						}
						firstPoint = false;
					} else
						if(Character.isDigit(ver.charAt(i)))
						{
							version = version + ver.charAt(i);
						}
				}
				javaVersion = new Double(version);
			} catch(Exception ex)
			{
				javaVersion = new Double(1.3D);
			}
		}
		return javaVersion.doubleValue();
	}

	public double getOSVersion()
	{
		if(osVersion == null)
		{
			try
			{
				String ver = System.getProperties().getProperty("os.version");
				String version = "";
				boolean firstPoint = true;
				for(int i = 0; i < ver.length(); i++)
				{
					if(ver.charAt(i) == '.')
					{
						if(firstPoint)
						{
							version = version + ver.charAt(i);
						}
						firstPoint = false;
					} else
						if(Character.isDigit(ver.charAt(i)))
						{
							version = version + ver.charAt(i);
						}
				}
				osVersion = new Double(version);
			} catch(Exception ex)
			{
				osVersion = new Double(1.0D);
			}
		}
		return osVersion.doubleValue();
	}

	public void createNewInvoice()
	{
		String newProjectName = JOptionPane.showInputDialog(main.getFrame(), "What would you like to call your next project?", "New Invoice", JOptionPane.QUESTION_MESSAGE);
		if(newProjectName != null)
		{
			if(!newProjectName.equals(""))
			{
				if(getInvoiceExplorer().doesInvoiceExist(newProjectName))
				{
					JOptionPane.showMessageDialog(main.getFrame(), "That project already exists!", "Error", JOptionPane.WARNING_MESSAGE);
					return;
				}
				getInvoiceExplorer().addNewInvoice(newProjectName);
			}
		}
	}

	public JButton makeNavigationButton(String imageName, String toolTipText, String altText)
	{
		//Look for the image.
		String imgLocation = imageName + ".png";
		URL imageURL = ImageHelper.class.getResource(imgLocation);

		//Create and initialize the button.
		JButton button = new JButton();
		//button.setActionCommand(actionCommand);
		button.setToolTipText(toolTipText);
		//button.addActionListener(this);

		if(imageURL != null)
		{ //image found
			button.setIcon(new ImageIcon(imageURL, altText));
		} else
		{ //no image found
			button.setText(altText);
			System.err.println("Resource not found: " + imgLocation);
		}

		return button;
	}

	public Main getMain()
	{
		return main;
	}

	public void setMain(Main main)
	{
		this.main = main;
	}

	public static Clock getClock()
	{
		return clock;
	}

	public void setClock(Clock clock)
	{
		this.clock = clock;
	}

	public InvoiceExplorer getInvoiceExplorer()
	{
		return invoiceExplorer;
	}

	public void setInvoiceExplorer(InvoiceExplorer invoiceExplorer)
	{
		this.invoiceExplorer = invoiceExplorer;
	}
}
