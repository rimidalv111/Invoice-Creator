package com.rimidalv111.util.customclasses;

import java.awt.Graphics;
import java.awt.LayoutManager;

import javax.swing.JPanel;

import com.rimidalv111.images.ImageHelper;

public class CustomJPanel extends JPanel
{
	private static final long serialVersionUID = 1L;

	public CustomJPanel()
	{
		setOpaque(false);
	}

	public CustomJPanel(LayoutManager layout)
	{
		super(layout);
	}

	public CustomJPanel(boolean isDoubleBuffered)
	{
		super(isDoubleBuffered);
	}

	public CustomJPanel(LayoutManager layout, boolean isDoubleBuffered)
	{
		super(layout, isDoubleBuffered);
	}

	@Override
	protected void paintComponent(Graphics g)
	{
		// paint background
		g.drawImage(ImageHelper.loadImage("panelBackground.png").getImage(), 0, 0, getWidth(), getHeight(), null);
		super.paintComponent(g);
	}
}
