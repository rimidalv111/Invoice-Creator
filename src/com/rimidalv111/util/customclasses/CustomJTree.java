package com.rimidalv111.util.customclasses;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;

import javax.swing.tree.TreeCellRenderer;

import com.rimidalv111.images.ImageHelper;

public class CustomJTree extends javax.swing.JTree
{
	private static final long serialVersionUID = 1L;

	public java.awt.Color rowColors[] = new java.awt.Color[2];

	@Override
	public void setCellRenderer(TreeCellRenderer x)
	{
		super.setCellRenderer(new CustomDefaultTreeCellRenderer()); //set our custom cell renderer
	}

	@Override
	protected void paintComponent(Graphics g)
	{
		// paint background
		g.drawImage(ImageHelper.loadImage("panelBackground.png").getImage(), 0, 0, getWidth(), getHeight(), null);

		//paint the non selected (background) first
		final java.awt.Insets insets = getInsets();
		final int w = getWidth() - insets.left - insets.right;
		final int x = insets.left;
		int y = insets.top;
		int rowHeight = getRowHeight();
		if(!(rowHeight > 0))
		{
			// Paint non-uniform height rows first
			final int nItems = getRowCount();
			rowHeight = 17; // A default for empty trees
			for(int i = 0; i < nItems; i++, y += rowHeight)
			{
				rowHeight = getRowBounds(i).height;
				g.setColor(new Color(241, 248, 255));
				g.fillRect(x, y, w, rowHeight);
			}
		}

		//paint selected
		// paint selected node's background and border
		int fromRow = getRowForPath(getSelectionPath());
		if(fromRow != -1)
		{
			int toRow = fromRow + 1;
			Rectangle fromBounds = getRowBounds(fromRow);
			Rectangle toBounds = getRowBounds(toRow - 1);
			if((fromBounds != null) && (toBounds != null))
			{
				g.drawImage(ImageHelper.loadImage("selectionBackground.png").getImage(), 0, fromBounds.y, getWidth(), (toBounds.y - fromBounds.y) + toBounds.height, null);
			}
		}

		super.paintComponent(g);
	}
}
