package com.rimidalv111.util.print;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.LayoutManager;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.print.PageFormat;
import java.awt.print.Paper;
import java.awt.print.Printable;
import java.awt.print.PrinterException;
import java.awt.print.PrinterJob;

import javax.swing.JButton;
import javax.swing.JEditorPane;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.border.MatteBorder;

import com.rimidalv111.util.Util;
import com.rimidalv111.util.customclasses.CustomJPanel;

public class PrintPreview extends JFrame implements Printable
{
	private static final long serialVersionUID = 1L;
	private Util util;
	private JEditorPane pane;
	private PageFormat pFormat;
	private InvoicePreviewPanel page;
	private JScrollPane scroll;

	public PrintPreview(JEditorPane iep, final String invoiceName, Util u, boolean skipPreview)
	{
		util = u;

		pFormat = new PageFormat();
		Paper paper = new Paper();
		paper.setImageableArea(0, 0, paper.getWidth(), paper.getHeight());
		pFormat.setPaper(paper);

		pane = iep;

		setTitle("Preview - " + invoiceName);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

		setResizable(false);
		setSize(710, 483);
		setLocationRelativeTo(util.getMain().getFrame());
		getContentPane().setLayout(null);

		if(!skipPreview)
		{
			setVisible(true);
		} else
		{
			setVisible(false);
			print();
		}

		CustomJPanel mainPanel = new CustomJPanel();
		mainPanel.setBounds(0, 0, getWidth(), getHeight());
		mainPanel.setBorder(new MatteBorder(0, 1, 1, 1, new Color(180, 180, 180)));
		mainPanel.setLayout(null);
		mainPanel.setVisible(true);
		getContentPane().add(mainPanel);

		CustomJPanel panel = new CustomJPanel();
		panel.setLayout(null);
		panel.setPreferredSize(new Dimension(getWidth() - 20, (int) pFormat.getHeight() + 60));

		page = new InvoicePreviewPanel();
		page.setBounds(0, 0, (int) pFormat.getWidth() - 5, (int) pFormat.getHeight());

		int xmath = page.getWidth() - page.getX();
		int px = ((int) panel.getPreferredSize().getWidth() / 2) - (xmath / 2);

		page.setLocation(px, 30);

		panel.add(page);

		scroll = new JScrollPane(panel);
		scroll.setBounds(0, 0, getWidth() - 5, 400);
		mainPanel.add(scroll);

		JButton btnPrint = new JButton("Print");
		btnPrint.setBounds(268, 420, 89, 23);
		btnPrint.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				dispose();
				new Thread()
				{
					public void run()
					{
						print();
					}
				}.start();
			}
		});
		mainPanel.add(btnPrint);

		JButton btnCancel = new JButton("Cancel");
		btnCancel.setBounds(359, 420, 89, 23);
		btnCancel.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				new Thread()
				{
					public void run()
					{
						dispose();
					}
				}.start();
			}
		});
		mainPanel.add(btnCancel);
	}

	class InvoicePreviewPanel extends JPanel
	{
		private static final long serialVersionUID = 1L;
		WhitePanel innerPage = new WhitePanel()
		{
			private static final long serialVersionUID = 1L;

			public void paintComponent(Graphics g)
			{
				super.paintComponent(g);
				//width width
				pane.setSize(new Dimension((int) pFormat.getWidth() - 18 - 18, (int) pFormat.getHeight()));
				pane.setBackground(Color.WHITE);
				pane.repaint();
				pane.paint(g);
			}
		};

		public InvoicePreviewPanel()
		{
			setSize((int) pFormat.getWidth(), (int) pFormat.getWidth());
			setBackground(Color.white);
			setLayout(null);
			add(innerPage); //width + 1 for border
			innerPage.setBounds(15, 1, (int) pFormat.getWidth() - 3, (int) pFormat.getHeight() - 3);
			setBorder(new MatteBorder(1, 1, 2, 2, Color.BLACK));
		}
	}

	class WhitePanel extends JPanel
	{
		private static final long serialVersionUID = 1L;

		public WhitePanel()
		{
			setOpaque(false);
		}

		public WhitePanel(LayoutManager layout)
		{
			super(layout);
		}

		public WhitePanel(boolean isDoubleBuffered)
		{
			super(isDoubleBuffered);
		}

		public WhitePanel(LayoutManager layout, boolean isDoubleBuffered)
		{
			super(layout, isDoubleBuffered);
		}

		@Override
		protected void paintComponent(Graphics g)
		{
			g.setColor(Color.WHITE);
			super.paintComponent(g);
		}
	}

	public void print()
	{
		PrinterJob printJob = PrinterJob.getPrinterJob();

		printJob.setPrintable(this, pFormat);

		page.setBorder(new MatteBorder(1, 1, 2, 2, Color.WHITE)); //remove any border 

		if(printJob.printDialog())
		{
			try
			{
				printJob.print();
				dispose();
			} catch(PrinterException printerException)
			{
				System.out.println("Error Printing Document");
			}
		}
	}

	@Override
	public int print(Graphics graphics, PageFormat pageFormat, int pageIndex) throws PrinterException
	{
		if(pageIndex > 0)
		{
			return NO_SUCH_PAGE;
		}

		page.paint(graphics);

		return PAGE_EXISTS;
	}
}
