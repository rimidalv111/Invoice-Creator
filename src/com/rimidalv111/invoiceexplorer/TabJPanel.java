package com.rimidalv111.invoiceexplorer;

import java.awt.Graphics;
import java.awt.GridLayout;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.swing.JPanel;
import javax.swing.JTabbedPane;

import com.rimidalv111.images.ImageHelper;
import com.rimidalv111.invoiceexplorer.nodegui.DemolitionGui;
import com.rimidalv111.invoiceexplorer.nodegui.ElectricalGui;
import com.rimidalv111.invoiceexplorer.nodegui.FinishWorkGui;
import com.rimidalv111.invoiceexplorer.nodegui.FlooringGui;
import com.rimidalv111.invoiceexplorer.nodegui.FramingGui;
import com.rimidalv111.invoiceexplorer.nodegui.InsulationGui;
import com.rimidalv111.invoiceexplorer.nodegui.MainGui;
import com.rimidalv111.invoiceexplorer.nodegui.PlumbingGui;
import com.rimidalv111.invoiceexplorer.nodegui.SheetRockGui;
import com.rimidalv111.invoiceexplorer.nodegui.TrimGui;
import com.rimidalv111.util.TotalArray;

public class TabJPanel extends JPanel
{

	private static final long serialVersionUID = 1L;

	private JTabbedPane tabbedPane;
	private InvoiceExplorer invoiceExplorer;
	private String tabName;
	private TotalArray totalArray;
	private InvoiceEditor invoiceEditor;
	private InvoiceVariableWork invoiceVarWork;
	
	private List<String> allGuis = new ArrayList<String>()
	{
		private static final long serialVersionUID = 1L;

		{
			add("Main");
			add("Demolition");
			add("Framing");
			add("Plumbing");
			add("Electrical");
			add("Insulation");
			add("SheetRock");
			add("Flooring");
			add("Trim");
			add("FinishWork");
		}
	};

	private MainGui mainGui;
	private DemolitionGui demolitionGui;
	private FramingGui framingGui;
	private PlumbingGui plumbingGui;
	private ElectricalGui electricalGui;
	private InsulationGui insulationGui;
	private SheetRockGui sheetRockGui;
	private FlooringGui flooringGui;
	private TrimGui trimGui;
	private FinishWorkGui finishWorkGui;

	public TabJPanel(JTabbedPane jtp, InvoiceExplorer ie, String tn)
	{
		super(new GridLayout(1, 0));
		tabbedPane = jtp;
		invoiceExplorer = ie;
		tabName = tn;

		//set jpanel settings
		setOpaque(false);

		setVisible(true);

		totalArray = new TotalArray();
		totalArray.setUp(tabName, this, 317, 400, 280, 200, "Tahoma", 14);

		invoiceEditor = new InvoiceEditor(this, tabName, "Path to invoice used later");
		invoiceEditor.loadSetUpInvoice();

		invoiceVarWork = new InvoiceVariableWork(this);
		invoiceVarWork.setInvoiceIdDate();
		
		mainGui = new MainGui(this);
		demolitionGui = new DemolitionGui(this);
		framingGui = new FramingGui(this);
		plumbingGui = new PlumbingGui(this);
		electricalGui = new ElectricalGui(this);
		insulationGui = new InsulationGui(this);
		sheetRockGui = new SheetRockGui(this);
		flooringGui = new FlooringGui(this);
		trimGui = new TrimGui(this);
		finishWorkGui = new FinishWorkGui(this);
	}

	@Override
	protected void paintComponent(Graphics g)
	{
		g.drawImage(ImageHelper.loadImage("panelBackground.png").getImage(), 0, 0, getWidth(), getHeight(), null);

		super.paintComponent(g);
	}

	public void activateGui(String name)
	{
		deactivateAllGuiBut(name);
		try
		{
			Class<?> guiClass = Class.forName("com.rimidalv111.invoiceexplorer.nodegui." + name + "Gui");
			if(MainGui.class.isAssignableFrom(guiClass))
			{
				mainGui.enableGui();
			}
			if(DemolitionGui.class.isAssignableFrom(guiClass))
			{
				demolitionGui.enableGui();
			}
			if(FramingGui.class.isAssignableFrom(guiClass))
			{
				framingGui.enableGui();
			}
			if(PlumbingGui.class.isAssignableFrom(guiClass))
			{
				plumbingGui.enableGui();
			}
			if(ElectricalGui.class.isAssignableFrom(guiClass))
			{
				electricalGui.enableGui();
			}
			if(InsulationGui.class.isAssignableFrom(guiClass))
			{
				insulationGui.enableGui();
			}
			if(SheetRockGui.class.isAssignableFrom(guiClass))
			{
				sheetRockGui.enableGui();
			}
			if(FlooringGui.class.isAssignableFrom(guiClass))
			{
				flooringGui.enableGui();
			}
			if(TrimGui.class.isAssignableFrom(guiClass))
			{
				trimGui.enableGui();
			}
			if(FinishWorkGui.class.isAssignableFrom(guiClass))
			{
				finishWorkGui.enableGui();
			}
		} catch(ClassNotFoundException e)
		{
			e.printStackTrace();
		}
	}

	public void deactivateGui(String name)
	{
		try
		{
			Class<?> guiClass = Class.forName("com.rimidalv111.invoiceexplorer.nodegui." + name + "Gui");
			if(MainGui.class.isAssignableFrom(guiClass))
			{
				mainGui.disableGui();
			}
			if(DemolitionGui.class.isAssignableFrom(guiClass))
			{
				demolitionGui.disableGui();
			}
			if(FramingGui.class.isAssignableFrom(guiClass))
			{
				framingGui.disableGui();
			}
			if(PlumbingGui.class.isAssignableFrom(guiClass))
			{
				plumbingGui.disableGui();
			}
			if(ElectricalGui.class.isAssignableFrom(guiClass))
			{
				electricalGui.disableGui();
			}
			if(InsulationGui.class.isAssignableFrom(guiClass))
			{
				insulationGui.disableGui();
			}
			if(SheetRockGui.class.isAssignableFrom(guiClass))
			{
				sheetRockGui.disableGui();
			}
			if(FlooringGui.class.isAssignableFrom(guiClass))
			{
				flooringGui.disableGui();
			}
			if(TrimGui.class.isAssignableFrom(guiClass))
			{
				trimGui.disableGui();
			}
			if(FinishWorkGui.class.isAssignableFrom(guiClass))
			{
				finishWorkGui.disableGui();
			}
		} catch(ClassNotFoundException e)
		{
			e.printStackTrace();
		}
	}

	public void deactivateAllGuiBut(String name)
	{
		Iterator<String> it = allGuis.iterator();
		while (it.hasNext())
		{
			String gui = it.next();
			if(!gui.equalsIgnoreCase(name))
			{
				deactivateGui(gui);
			}
		}
	}

	public void deactivateAllGui()
	{
		Iterator<String> it = allGuis.iterator();
		while (it.hasNext())
		{
			String gui = it.next();
			deactivateGui(gui);
		}
	}

	public void validateRepaint()
	{
		tabbedPane.validate();
		tabbedPane.repaint();
	}

	public void updateCompanyDetails() //when program first starts and when "apply" is hit on company details frame
	{
		invoiceVarWork.setCompanyDetails();
	}
	
	public JTabbedPane getTabbedPane()
	{
		return tabbedPane;
	}

	public void setTabbedPane(JTabbedPane tabbedPane)
	{
		this.tabbedPane = tabbedPane;
	}

	public List<String> getAllGuis()
	{
		return allGuis;
	}

	public void setAllGuis(List<String> allGuis)
	{
		this.allGuis = allGuis;
	}

	public MainGui getMainGui()
	{
		return mainGui;
	}

	public void setMainGui(MainGui mainGui)
	{
		this.mainGui = mainGui;
	}

	public DemolitionGui getDemolitionGui()
	{
		return demolitionGui;
	}

	public void setDemolitionGui(DemolitionGui demolitionGui)
	{
		this.demolitionGui = demolitionGui;
	}

	public FramingGui getFramingGui()
	{
		return framingGui;
	}

	public void setFramingGui(FramingGui framingGui)
	{
		this.framingGui = framingGui;
	}

	public PlumbingGui getPlumbingGui()
	{
		return plumbingGui;
	}

	public void setPlumbingGui(PlumbingGui plumbingGui)
	{
		this.plumbingGui = plumbingGui;
	}

	public ElectricalGui getElectricalGui()
	{
		return electricalGui;
	}

	public void setElectricalGui(ElectricalGui electricalGui)
	{
		this.electricalGui = electricalGui;
	}

	public InsulationGui getInsulationGui()
	{
		return insulationGui;
	}

	public void setInsulationGui(InsulationGui insulationGui)
	{
		this.insulationGui = insulationGui;
	}

	public SheetRockGui getSheetRockGui()
	{
		return sheetRockGui;
	}

	public void setSheetRockGui(SheetRockGui sheetRockGui)
	{
		this.sheetRockGui = sheetRockGui;
	}

	public FlooringGui getFlooringGui()
	{
		return flooringGui;
	}

	public void setFlooringGui(FlooringGui flooringGui)
	{
		this.flooringGui = flooringGui;
	}

	public TrimGui getTrimGui()
	{
		return trimGui;
	}

	public void setTrimGui(TrimGui trimGui)
	{
		this.trimGui = trimGui;
	}

	public FinishWorkGui getFinishWorkGui()
	{
		return finishWorkGui;
	}

	public void setFinishWorkGui(FinishWorkGui finishWorkGui)
	{
		this.finishWorkGui = finishWorkGui;
	}

	public static long getSerialversionuid()
	{
		return serialVersionUID;
	}

	public InvoiceExplorer getInvoiceExplorer()
	{
		return invoiceExplorer;
	}

	public void setInvoiceExplorer(InvoiceExplorer invoiceExplorer)
	{
		this.invoiceExplorer = invoiceExplorer;
	}

	public String getTabName()
	{
		return tabName;
	}

	public void setTabName(String tabName)
	{
		this.tabName = tabName;
	}

	public TotalArray getTotalArray()
	{
		return totalArray;
	}

	public void setTotalArray(TotalArray totalArray)
	{
		this.totalArray = totalArray;
	}

	/**
	 * @return the invoiceEditor
	 */
	public InvoiceEditor getInvoiceEditor()
	{
		return invoiceEditor;
	}

	/**
	 * @param invoiceEditor
	 *            the invoiceEditor to set
	 */
	public void setInvoiceEditor(InvoiceEditor invoiceEditor)
	{
		this.invoiceEditor = invoiceEditor;
	}

	/**
     * @return the invoiceVarWork
     */
    public InvoiceVariableWork getInvoiceVarWork()
    {
	    return invoiceVarWork;
    }

	/**
     * @param invoiceVarWork the invoiceVarWork to set
     */
    public void setInvoiceVarWork(InvoiceVariableWork invoiceVarWork)
    {
	    this.invoiceVarWork = invoiceVarWork;
    }
}
