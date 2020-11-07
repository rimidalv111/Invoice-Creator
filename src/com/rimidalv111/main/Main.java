package com.rimidalv111.main;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.SystemColor;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.Properties;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;
import javax.swing.JToolBar;
import javax.swing.RepaintManager;
import javax.swing.SwingConstants;
import javax.swing.UIManager;
import javax.swing.border.MatteBorder;

import com.jtattoo.plaf.texture.TextureLookAndFeel;
import com.rimidalv111.images.ImageHelper;
import com.rimidalv111.invoiceexplorer.TabJPanel;
import com.rimidalv111.util.Util;
import com.rimidalv111.util.customclasses.CustomJLabelIncDec;
import com.rimidalv111.util.customclasses.CustomJPanel;

import javax.swing.JScrollPane;
import javax.swing.JEditorPane;

public class Main
{
	private Main instance;
	private CompanyDetails companyDetailsFrame;

	private JFrame frame;
	private JLabel lblDateTime;

	private Util util;

	private JTabbedPane mainTabPane;
	//new invoice nav
	private JPanel NewInvoiceNav;

	private JPanel westPanel;
	private JPanel centerPanel;

	public static void main(String[] args)
	{
		try
		{
			// setup the look and feel properties
			Properties props = new Properties();
			props.put("logoString", "\u00A0");
			props.put("macStyleScrollBar", "on");
			props.put("centerWindowTitle", "on");

			TextureLookAndFeel.setCurrentTheme(props);

			//com.jtattoo.plaf.texture.TextureLookAndFeel.setTheme("Rock", "", "");
			UIManager.setLookAndFeel("com.jtattoo.plaf.texture.TextureLookAndFeel");

			//UIManager.setLookAndFeel(SyntheticaAluOxideLookAndFeel.class.getName());

		} catch(Exception io)
		{
			io.printStackTrace();
		}

		RepaintManager.setCurrentManager(new RepaintManager());

		EventQueue.invokeLater(new Runnable()
		{
			//@Override
			public void run()
			{
				try
				{
					Main window = new Main();
					window.frame.setVisible(true);
				} catch(Exception e)
				{
					e.printStackTrace();
				}
			}
		});
	}

	public Main()
	{
		initialize();
	}

	private void initialize()
	{
		instance = this;

		companyDetailsFrame = new CompanyDetails(instance);

		frame = new JFrame();
		frame.setTitle("Invoice Builder");
		frame.setBounds(100, 100, 800, 590);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setResizable(false);
		BorderLayout layout = new BorderLayout();

		frame.getContentPane().setLayout(layout);

		westPanel = new JPanel();
		westPanel.setLayout(null);
		westPanel.setPreferredSize(new Dimension(175, frame.getSize().height));
		westPanel.setVisible(true);

		centerPanel = new JPanel();
		centerPanel.setLayout(null);
		centerPanel.setPreferredSize(new Dimension(407, frame.getSize().height));
		centerPanel.setVisible(true);

		util = new Util(this);

		JMenuBar menuBar = new JMenuBar();
		menuBar.setBounds(0, 0, 794, 21);
		frame.setJMenuBar(menuBar);

		JMenu mnFile = new JMenu("File");
		menuBar.add(mnFile);

		JMenuItem mntmExit = new JMenuItem("Exit");
		mntmExit.addActionListener(new ActionListener()
		{
			//@Override
			public void actionPerformed(ActionEvent e)
			{
				frame.dispose();
				System.exit(0);
			}
		});

		JMenuItem mntmNewInvoice = new JMenuItem("New Invoice");
		mntmNewInvoice.addActionListener(new ActionListener()
		{
			//@Override
			public void actionPerformed(ActionEvent e)
			{
				util.createNewInvoice();
			}
		});
		mnFile.add(mntmNewInvoice);

		JMenuItem mntmLoadInvoice = new JMenuItem("Load Invoice");
		mnFile.add(mntmLoadInvoice);
		mnFile.add(mntmExit);

		JMenu mnSettings = new JMenu("Settings");
		menuBar.add(mnSettings);

		JMenuItem mntmPriceSettings = new JMenuItem("Price Settings");
		mnSettings.add(mntmPriceSettings);

		JMenuItem mntmCompanyDetails = new JMenuItem("Company Details");
		mntmCompanyDetails.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				companyDetailsFrame.setVisible(true);
			}
		});

		mnSettings.add(mntmCompanyDetails);

		JMenu mnHelp = new JMenu("Help");
		menuBar.add(mnHelp);

		JMenuItem mntmHowToCreate = new JMenuItem("How to create template");
		mnHelp.add(mntmHowToCreate);

		JMenuItem mntmHowTo = new JMenuItem("How to change prices");
		mnHelp.add(mntmHowTo);

		JMenuItem mntmHowToCreate_1 = new JMenuItem("How to create invoice");
		mnHelp.add(mntmHowToCreate_1);

		frame.getContentPane().add(westPanel, BorderLayout.WEST);
		frame.getContentPane().add(centerPanel, BorderLayout.CENTER);

		signaturePane();
		mainTabPane();
		createToolBar();

		// exceptions

	}

	private void addWindowListener(WindowAdapter windowAdapter)
	{
		// TODO Auto-generated method stub

	}

	public void signaturePane()
	{
		CustomJPanel panel = new CustomJPanel();
		panel.setBorder(new MatteBorder(1, 1, 1, 1, SystemColor.activeCaptionBorder));
		panel.setBounds(7, 404, 157, 94);
		westPanel.add(panel);

		JLabel lblWebsite = new JLabel("www.invoicebuilder.com");
		lblWebsite.addMouseListener(new MouseAdapter()
		{
			@Override
			public void mouseClicked(MouseEvent e)
			{
				util.openWebpage("http://www.google.com");
			}
		});
		lblWebsite.setToolTipText("Click to visit website");
		panel.add(lblWebsite);

		JLabel lblProgramwebsitecom = new JLabel("admin@invoicebuilder.com");
		panel.add(lblProgramwebsitecom);

		lblDateTime = new JLabel("Date/Time");
		panel.add(lblDateTime);
		util.startClock(); // start clock after label is created to avoid null

		JLabel lblCopyright = new JLabel("\u00A9 Invoice Builder 2015");
		panel.add(lblCopyright);
	}

	public void mainTabPane()
	{
		mainTabPane = new JTabbedPane(SwingConstants.TOP);
		mainTabPane.setBounds(0, -6, 612, 505);
		mainTabPane.setTabLayoutPolicy(JTabbedPane.SCROLL_TAB_LAYOUT);
		mainTabPane.setVisible(false); //make it invisible cuz otherwise black line on top of empty pane
		centerPanel.add(mainTabPane);

		mainTabPane.addMouseListener(new MouseAdapter()
		{
			@Override
			public void mouseClicked(MouseEvent e)
			{
				System.out.println("X: " + e.getX() + "    " + "Y: " + e.getY());
			}
		});
		//	frame.getContentPane().add(mainTabPane, BorderLayout.CENTER);

		JPanel panel = new JPanel();
		//		
		//		panel.setBorder(new MatteBorder(0, 1, 1, 1, (Color) new Color(180, 180, 180)));
	//	mainTabPane.addTab("New tab", null, panel, null);
//
	}

	public void createToolBar()
	{
		//theme for toolbar is Discovery Icon Theme by Hylke Bons
		JToolBar toolBar = new JToolBar("Still draggable");
		toolBar.setFloatable(false);
		//toolBar.setBorder(new MatteBorder(0, 0, 1, 0, (Color) new Color(180, 180, 180)));
		JButton newButton = util.makeNavigationButton("new", "Create new Invoice", "Create");
		newButton.addActionListener(new ActionListener()
		{
			//@Override
			public void actionPerformed(ActionEvent e)
			{
				util.createNewInvoice();
			}
		});
		toolBar.add(newButton);

		JButton openButton = util.makeNavigationButton("open", "Open Invoice", "Open");
		toolBar.add(openButton);

		JButton saveButton = util.makeNavigationButton("save", "Save Invoice", "Save");
		toolBar.add(saveButton);

		JButton printButton = util.makeNavigationButton("print", "Print Invoice", "Print");
		printButton.addActionListener(new ActionListener()
		{
			//@Override
			public void actionPerformed(ActionEvent e)
			{
				TabJPanel tjp = util.getInvoiceExplorer().getCurrentInvoiceTabJPanel();
				if(tjp != null)
				{
					tjp.getInvoiceVarWork().calculateTotal();
					tjp.getInvoiceVarWork().setCompanyDetails();
					tjp.getTotalArray().updateMainInvoice();

					JEditorPane tmpPane = new JEditorPane("text/html", tjp.getInvoiceVarWork().getDocument().toString());
					util.printPreview(tmpPane, tjp.getTabName());

				}
			}
		});
		toolBar.add(printButton);

		frame.getContentPane().add(toolBar, BorderLayout.PAGE_START);
	}

	public Util getUtil()
	{
		return util;
	}

	public void setUtil(Util util)
	{
		this.util = util;
	}

	public JFrame getFrame()
	{
		return frame;
	}

	public void setFrame(JFrame frame)
	{
		this.frame = frame;
	}

	public JLabel getLblDateTime()
	{
		return lblDateTime;
	}

	public void setLblDateTime(JLabel lblDateTime)
	{
		this.lblDateTime = lblDateTime;
	}

	public JTabbedPane getMainTabPane()
	{
		return mainTabPane;
	}

	public void setMainTabPane(JTabbedPane mainTabPane)
	{
		this.mainTabPane = mainTabPane;
	}

	public JPanel getNewInvoiceNav()
	{
		return NewInvoiceNav;
	}

	public void setNewInvoiceNav(JPanel newInvoiceNav)
	{
		NewInvoiceNav = newInvoiceNav;
	}

	public JPanel getWestPanel()
	{
		return westPanel;
	}

	public void setWestPanel(JPanel westPanel)
	{
		this.westPanel = westPanel;
	}

	public JPanel getCenterPanel()
	{
		return centerPanel;
	}

	public void setCenterPanel(JPanel centerPanel)
	{
		this.centerPanel = centerPanel;
	}

	/**
	 * @return the companyDetailsFrame
	 */
	public CompanyDetails getCompanyDetailsFrame()
	{
		return companyDetailsFrame;
	}

	/**
	 * @param companyDetailsFrame
	 *            the companyDetailsFrame to set
	 */
	public void setCompanyDetailsFrame(CompanyDetails companyDetailsFrame)
	{
		this.companyDetailsFrame = companyDetailsFrame;
	}

	/**
	 * @return the instance
	 */
	public Main getInstance()
	{
		return instance;
	}

	/**
	 * @param instance
	 *            the instance to set
	 */
	public void setInstance(Main instance)
	{
		this.instance = instance;
	}
}
