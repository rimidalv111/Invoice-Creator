package com.rimidalv111.util.customclasses;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Polygon;
import java.awt.Shape;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.geom.AffineTransform;

import javax.swing.AbstractButton;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.plaf.basic.BasicButtonUI;

import com.rimidalv111.util.Util;

public class CustomJLabelIncDec extends JPanel
{
	private static final long serialVersionUID = 1L;
	private JPanel thisClass;
	private JLabel label;
	private JLabel incDecVarLabel;
	private int incdecvar;
	private ActionListener actionL;
	DecrementButton decrementButton;
	IncrementButton incrementButton;

	public CustomJLabelIncDec(String text, int startIncDecVar)
	{
		super(new FlowLayout(FlowLayout.RIGHT, 0, 0));
		thisClass = this;
		actionL = null;

		setOpaque(false);
		label = new JLabel(text);
		label.setFont(getFont());
		label.setBounds(getX(), getY(), getWidth(), getHeight());
		add(label);

		decrementButton = new DecrementButton();
		add(decrementButton);

		incdecvar = startIncDecVar;
		incDecVarLabel = new JLabel(" " + incdecvar + " ");
		incDecVarLabel.setFont(getFont());
		add(incDecVarLabel);

		incrementButton = new IncrementButton();
		add(incrementButton);
	}

	public Double getCalculation() //if can
	{
		String removeAlpha = label.getText();
		removeAlpha = removeAlpha.replaceAll("[^\\d.]", "");
		double total = 0;
		try
		{
			total = Double.parseDouble(removeAlpha);
			total = total * Integer.parseInt(incDecVarLabel.getText().replace(" ", ""));
		} catch(Exception io)
		{
			Util.showError(io);
		}
		return total;
	}

	public void setText(String text)
	{
		label.setText(text);
		label.repaint();
	}

	@Override
	public void setFont(Font font)
	{
		if(label != null && incDecVarLabel != null)
		{
			label.setFont(font);
			label.repaint();
			incDecVarLabel.setFont(font);
			incDecVarLabel.repaint();
		}
	}

	@Override
	public void setEnabled(boolean enabled)
	{
		if(!enabled)
		{
			label.setEnabled(false);
			incDecVarLabel.setEnabled(false);
			decrementButton.setEnabled(false);
			incrementButton.setEnabled(false);
			repaint();
		} else
		{
			label.setEnabled(true);
			incDecVarLabel.setEnabled(true);
			decrementButton.setEnabled(true);
			incrementButton.setEnabled(true);
			repaint();
		}
	}

	private class IncrementButton extends JButton implements ActionListener
	{
		private static final long serialVersionUID = 1L;

		public IncrementButton()
		{
			int size = 14;
			setPreferredSize(new Dimension(size, size));
			setToolTipText("Increment");
			setUI(new BasicButtonUI());
			setContentAreaFilled(false);
			setFocusable(false);
			setBorder(BorderFactory.createEmptyBorder());
			setBorderPainted(false);
			addMouseListener(buttonMouseListener);
			setRolloverEnabled(true);
			addActionListener(this);
		}

		public void actionPerformed(ActionEvent e)
		{
			incdecvar++;
			incDecVarLabel.setText(" " + incdecvar + " ");
			if(getActionL() != null)
			{
				getActionL().actionPerformed(new ActionEvent(this, 0, "Increment"));
			}
		}

		//we don't want to update UI for this button
		@Override
		public void updateUI()
		{
		}

		@Override
		protected void paintComponent(Graphics g)
		{
			super.paintComponent(g);
			Graphics2D g2 = (Graphics2D) g.create();
			//if its not enabled just  paint it
			if(!getModel().isEnabled())
			{
				g2.setStroke(new BasicStroke(2));
				g2.setColor(new Color(142, 117, 100));
				paintIncrementTriangle(g2, false);
				g2.dispose();
				return;
			}

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
			paintIncrementTriangle(g2, true);
			g2.dispose();
		}

		private void paintIncrementTriangle(Graphics g, boolean enabled)
		{
			Graphics2D g2 = (Graphics2D) g.create();
			Shape s = new Triangle(10, 2, 0, 8, 10, 13);
			s = AffineTransform.getRotateInstance(Math.toRadians(180), s.getBounds2D().getCenterX(), s.getBounds2D().getCenterY()).createTransformedShape(s);
			g2.fill(s);
			if(enabled)
			{
				g2.setColor(new Color(80, 80, 80));
			} else
			{
				g2.setColor(new Color(114, 94, 80));
			}
			g2.draw(s);
		}
	}

	private class DecrementButton extends JButton implements ActionListener
	{
		private static final long serialVersionUID = 1L;

		public DecrementButton()
		{
			int size = 14;
			setPreferredSize(new Dimension(size, size));
			setToolTipText("Decrement");
			setUI(new BasicButtonUI());
			setContentAreaFilled(false);
			setFocusable(false);
			setBorder(BorderFactory.createEmptyBorder());
			setBorderPainted(false);
			addMouseListener(buttonMouseListener);
			setRolloverEnabled(true);
			addActionListener(this);
		}

		public void actionPerformed(ActionEvent e)
		{
			if(incdecvar > 1)
			{
				incdecvar--;
				incDecVarLabel.setText(" " + incdecvar + " ");
				if(getActionL() != null)
				{
					getActionL().actionPerformed(new ActionEvent(this, 0, "Decrement"));
				}
			}
		}

		//we don't want to update UI for this button
		@Override
		public void updateUI()
		{
		}

		@Override
		protected void paintComponent(Graphics g)
		{
			super.paintComponent(g);
			Graphics2D g2 = (Graphics2D) g.create();
			//if its not enabled just  paint it
			if(!getModel().isEnabled())
			{
				g2.setStroke(new BasicStroke(2));
				g2.setColor(new Color(142, 117, 100));
				paintDecrementTriangle(g2, false);
				g2.dispose();
				return;
			}

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

			paintDecrementTriangle(g2, true);
			g2.dispose();
		}

		private void paintDecrementTriangle(Graphics g, boolean enabled)
		{
			Graphics2D g2 = (Graphics2D) g.create();
			Shape s = new Triangle(14, 2, 4, 8, 14, 13);
			g2.fill(s);
			if(enabled)
			{
				g2.setColor(new Color(80, 80, 80));
			} else
			{
				g2.setColor(new Color(114, 94, 80));
			}
			g2.draw(s);
		}
	}

	private class Triangle extends Polygon
	{
		private static final long serialVersionUID = 1L;

		public Triangle(int x1, int y1, int x2, int y2, int x3, int y3)
		{
			super(new int[]
			{ x1, x2, x3 }, new int[]
			{ y1, y2, y3 }, 3);
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

	/**
	 * @return the label
	 */
	public JLabel getLabel()
	{
		return label;
	}

	/**
	 * @param label
	 *            the label to set
	 */
	public void setLabel(JLabel label)
	{
		this.label = label;
	}

	/**
	 * @return the incDecVarLabel
	 */
	public JLabel getIncDecVarLabel()
	{
		return incDecVarLabel;
	}

	/**
	 * @param incDecVarLabel
	 *            the incDecVarLabel to set
	 */
	public void setIncDecVarLabel(JLabel incDecVarLabel)
	{
		this.incDecVarLabel = incDecVarLabel;
	}

	/**
	 * @return the incdecvar
	 */
	public int getIncdecvar()
	{
		return incdecvar;
	}

	/**
	 * @return the incdecvar
	 */
	public int getVar()
	{
		return incdecvar;
	}

	/**
	 * @param incdecvar
	 *            the incdecvar to set
	 */
	public void setIncdecvar(int incdecvar)
	{
		this.incdecvar = incdecvar;
	}

	public JPanel getThisClass()
	{
		return thisClass;
	}

	public void setThisClass(JPanel thisClass)
	{
		this.thisClass = thisClass;
	}

	public void addActionListener(ActionListener actionListener)
	{
		actionL = actionListener;
	}

	public ActionListener getActionL()
	{
		return actionL;
	}

	public void setActionL(ActionListener actionL)
	{
		this.actionL = actionL;
	}
}
