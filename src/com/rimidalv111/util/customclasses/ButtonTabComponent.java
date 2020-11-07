package com.rimidalv111.util.customclasses;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.AbstractButton;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;
import javax.swing.plaf.basic.BasicButtonUI;

public class ButtonTabComponent extends JPanel
{
	private static final long serialVersionUID = 1L;
	private final JTabbedPane pane;

	public ButtonTabComponent(final JTabbedPane pane)
	{
		//unset default FlowLayout' gaps
		super(new FlowLayout(FlowLayout.LEFT, 0, 0));
		if(pane == null)
		{
			throw new NullPointerException("TabbedPane is null");
		}
		this.pane = pane;
		setOpaque(false);

		//make JLabel read titles from JTabbedPane
		JLabel label = new JLabel()
		{
			private static final long serialVersionUID = 1L;

			@Override
			public String getText()
			{
				int i = pane.indexOfTabComponent(ButtonTabComponent.this);
				if(i != -1)
				{
					return pane.getTitleAt(i);
				}
				return null;
			}
		};

		label.setForeground(new Color(88, 125, 143));

		label.setFont(new Font("Tahoma", Font.BOLD, 12));

		add(label);
		//add more space between the label and the button
		label.setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 5));
		//tab button
		JButton button = new TabButton();
		add(button);
		//add more space to the top of the component
		setBorder(BorderFactory.createEmptyBorder(2, 0, 0, -1));
	}

	private class TabButton extends JButton implements ActionListener
	{
		private static final long serialVersionUID = 1L;

		public TabButton()
		{
			int size = 17;
			setPreferredSize(new Dimension(size, size));
			setToolTipText("Close");
			//Make the button looks the same for all Laf's
			setUI(new BasicButtonUI());
			//Make it transparent
			setContentAreaFilled(false);
			//No need to be focusable
			setFocusable(false);
			setBorder(BorderFactory.createEmptyBorder());
			setBorderPainted(false);
			//Making nice rollover effect
			//we use the same listener for all buttons
			addMouseListener(buttonMouseListener);
			setRolloverEnabled(true);
			//Close the proper tab by clicking the button
			addActionListener(this);
		}

		//@Override
		public void actionPerformed(ActionEvent e)
		{
			int i = pane.indexOfTabComponent(ButtonTabComponent.this);
			if(i != -1)
			{
				pane.remove(i);
				if(pane.getTabCount() == 0) //if the last tab was removed set the pane to invisible (to remove the line at the top)
				{
					pane.setVisible(false);
				}
			}
		}

		//we don't want to update UI for this button
		@Override
		public void updateUI()
		{
		}

		//paint the cross
		@Override
		protected void paintComponent(Graphics g)
		{
			super.paintComponent(g);
			Graphics2D g2 = (Graphics2D) g.create();
			//shift the image for pressed buttons
			if(getModel().isPressed())
			{
				g2.translate(1, 1);
			}
			g2.setStroke(new BasicStroke(2));
			g2.setColor(Color.GRAY);
			if(getModel().isRollover())
			{
				g2.setColor(Color.MAGENTA);
			}
			int delta = 6;
			g2.drawLine(delta, delta, getWidth() - delta - 1, getHeight() - delta - 1);
			g2.drawLine(getWidth() - delta - 1, delta, delta, getHeight() - delta - 1);
			g2.dispose();
		}
	}

	private final static MouseListener buttonMouseListener = new MouseAdapter()
	{
		@Override
		public void mouseEntered(MouseEvent e)
		{
			Component component = e.getComponent();
			if(component instanceof AbstractButton)
			{
				AbstractButton button = (AbstractButton) component;
				button.setBorderPainted(true);
			}
		}

		@Override
		public void mouseExited(MouseEvent e)
		{
			Component component = e.getComponent();
			if(component instanceof AbstractButton)
			{
				AbstractButton button = (AbstractButton) component;
				button.setBorderPainted(false);
			}
		}
	};
}
