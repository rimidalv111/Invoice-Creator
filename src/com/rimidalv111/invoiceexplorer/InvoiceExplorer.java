package com.rimidalv111.invoiceexplorer;

import java.awt.Color;
import java.awt.GridLayout;
import java.awt.SystemColor;
import java.net.URL;
import java.util.HashMap;

import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTree;
import javax.swing.UIManager;
import javax.swing.border.MatteBorder;
import javax.swing.event.TreeSelectionEvent;
import javax.swing.event.TreeSelectionListener;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeModel;
import javax.swing.tree.TreeSelectionModel;

import com.rimidalv111.images.ImageHelper;
import com.rimidalv111.util.Util;
import com.rimidalv111.util.customclasses.ButtonTabComponent;
import com.rimidalv111.util.customclasses.CustomJTree;

public class InvoiceExplorer extends JPanel
{
	private static final long serialVersionUID = 1L;
	private Util util;
	private JScrollPane treeViewPane;
	private CustomJTree tree;

	private HashMap<String, TabJPanel> invoices;

	public InvoiceExplorer(Util u)
	{
		super(new GridLayout(1, 0));
		util = u;
		invoices = new HashMap<String, TabJPanel>();

		//set jpanel settings
		setBackground(SystemColor.controlShadow);
		setBounds(7, 0, 157, 399);
		//setPreferredSize(new Dimension(147, 389));
		setVisible(true);
		//add jpanel to main frame
		u.getMain().getWestPanel().add(this);
		//u.getMain().getFrame().getContentPane().add(this, BorderLayout.WEST);

		//innitiaze the tree settings
		URL parent = ImageHelper.class.getResource("folderOpenIcon.png");
		URL child = ImageHelper.class.getResource("treeLeafIcon.png");
		URL expaned = ImageHelper.class.getResource("treeExpandedIcon.png");
		URL collapsed = ImageHelper.class.getResource("treeCollapsedIcon.png");

		Icon parentIcon = new ImageIcon(parent);
		Icon childIcon = new ImageIcon(child);
		Icon expanedIcon = new ImageIcon(expaned);
		Icon collapsedIcon = new ImageIcon(collapsed);
		UIManager.put("Tree.paintLines", Boolean.FALSE);
		UIManager.put("Tree.expandedIcon", expanedIcon);
		UIManager.put("Tree.collapsedIcon", collapsedIcon);
		UIManager.put("Tree.closedIcon", parentIcon);
		UIManager.put("Tree.openIcon", parentIcon);
		UIManager.put("Tree.leafIcon", childIcon);

		tree = new CustomJTree();
		tree.setOpaque(false); //so we can see custom stuff

		tree.setModel(new DefaultTreeModel(new DefaultMutableTreeNode("Invoices " + "[0]")
		{
			private static final long serialVersionUID = 1L;
		}));

		tree.getSelectionModel().setSelectionMode(TreeSelectionModel.SINGLE_TREE_SELECTION);
		tree.addTreeSelectionListener(new TreeSelection());
		tree.setBorder(new MatteBorder(0, 1, 0, 1, new Color(180, 180, 180)));
		//create treeViewPane that scrolls and add it to panel
		treeViewPane = new JScrollPane(tree);
		treeViewPane.setBounds(10, 32, 157, 379);
		add(treeViewPane);

		//load existing invoices

	}

	//what selections you make
	public class TreeSelection implements TreeSelectionListener
	{
		//@Override
		public void valueChanged(TreeSelectionEvent e)
		{
			try
			{
				//now set the individual guis inside the tab
				String invoiceName = e.getPath().getPathComponent(1).toString(); //if its not an invoice it will run an io exception
				TabJPanel invoiceGui = invoices.get(invoiceName);

				String childName;
				try
				{
					childName = e.getPath().getPathComponent(2).toString();
					invoiceGui.activateGui(childName.replace(" ", ""));

				} catch(Exception io)
				{
					invoiceGui.activateGui("Main");
					//no child just the main gui for invoice name
				}

				if(getUtil().getMain().getMainTabPane().indexOfTab(e.getPath().getPathComponent(1).toString()) == -1)
				{
					if(!getUtil().getMain().getMainTabPane().isVisible()) //make the pane visible (was invisible cuz of black line on top)
					{
						getUtil().getMain().getMainTabPane().setVisible(true);
					}
					getUtil().getMain().getMainTabPane().addTab(e.getPath().getPathComponent(1).toString(), invoices.get(invoiceName));
					getUtil().getMain().getMainTabPane().setTabComponentAt(getUtil().getMain().getMainTabPane().indexOfTab(e.getPath().getPathComponent(1).toString()), new ButtonTabComponent(getUtil().getMain().getMainTabPane()));
					getUtil().getMain().getMainTabPane().setSelectedIndex(getUtil().getMain().getMainTabPane().indexOfTab(e.getPath().getPathComponent(1).toString()));
					getUtil().getMain().getMainTabPane().repaint();
				} else
				{
					getUtil().getMain().getMainTabPane().setSelectedIndex(getUtil().getMain().getMainTabPane().indexOfTab(e.getPath().getPathComponent(1).toString()));
				}

			} catch(Exception io)
			{
				if(!io.getMessage().toString().startsWith("Index 1 is out of the specified range"))
				{
					Util.showError(io);
				}
			}
		}
	}

	public void addNewInvoice(String name)
	{
		DefaultTreeModel model = (DefaultTreeModel) tree.getModel();
		DefaultMutableTreeNode root = (DefaultMutableTreeNode) model.getRoot();
		root.add(new DefaultMutableTreeNode(name)
		{
			private static final long serialVersionUID = 1L;
			{
				add(new DefaultMutableTreeNode("Demolition"));
				add(new DefaultMutableTreeNode("Framing"));
				add(new DefaultMutableTreeNode("Plumbing"));
				add(new DefaultMutableTreeNode("Electrical"));
				add(new DefaultMutableTreeNode("Insulation"));
				add(new DefaultMutableTreeNode("Sheet Rock"));
				add(new DefaultMutableTreeNode("Flooring"));
				add(new DefaultMutableTreeNode("Trim"));
				add(new DefaultMutableTreeNode("Finish Work"));
			}
		});

		root.setUserObject("Invoices [" + root.getChildCount() + "]"); //update how many inoices there are
		model.nodeChanged(root);
		model.reload(root);

		TabJPanel newInvoiceGui = new TabJPanel(util.getMain().getMainTabPane(), this, name);
		newInvoiceGui.setBorder(new MatteBorder(0, 1, 1, 1, new Color(180, 180, 180)));
		newInvoiceGui.setLayout(null);
		invoices.put(name, newInvoiceGui);
	}

	public void loadSavedInvoices(String path)
	{

	}

	public void saveInvoice(String name)
	{

	}

	public boolean doesInvoiceExist(String name)
	{
		if(invoices.containsKey(name))
		{
			return true;
		}
		return false;
	}

	public TabJPanel getCurrentInvoiceTabJPanel()
	{
		if(tree != null && tree.getSelectionPath() != null)
		{
			try
			{
				String selected = tree.getSelectionPath().getPathComponent(1).toString();
				System.out.println(selected);
				TabJPanel tjp = invoices.get(selected);
				return tjp;
			} catch(Exception io)
			{
				//invoices tree was selected and cannot be printed
			}
		}
		return null;
	}

	public Util getUtil()
	{
		return util;
	}

	public void setUtil(Util util)
	{
		this.util = util;
	}

	public JTree getTree()
	{
		return tree;
	}

	public void setTree(CustomJTree tree)
	{
		this.tree = tree;
	}

	public JScrollPane getTreeViewPane()
	{
		return treeViewPane;
	}

	public void setTreeViewPane(JScrollPane treeViewPane)
	{
		this.treeViewPane = treeViewPane;
	}

	public HashMap<String, TabJPanel> getInvoices()
	{
		return invoices;
	}

	public void setInvoices(HashMap<String, TabJPanel> invoices)
	{
		this.invoices = invoices;
	}
}
