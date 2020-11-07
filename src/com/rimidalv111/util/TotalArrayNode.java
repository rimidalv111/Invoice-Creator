package com.rimidalv111.util;

import java.util.HashMap;

public class TotalArrayNode
{
	private String guiName;
	private String arrayString;
	private String totalAmount;
	private HashMap<String, String> extraLines;

	public TotalArrayNode(String gn, String as, String ta, HashMap<String, String> e)
	{
		guiName = gn;
		arrayString = as;
		totalAmount = ta;
		extraLines = e;
	}

	public String getGuiName()
	{
		return guiName;
	}

	public void setGuiName(String guiName)
	{
		this.guiName = guiName;
	}

	public String getArrayString()
	{
		return arrayString;
	}

	public void setArrayString(String arrayString)
	{
		this.arrayString = arrayString;
	}

	public String getTotalAmount()
	{
		return totalAmount;
	}

	public void setTotalAmount(String totalAmount)
	{
		this.totalAmount = totalAmount;
	}

	public HashMap<String, String> getExtraLines()
	{
		return extraLines;
	}

	public void setExtraLines(HashMap<String, String> extraLines)
	{
		this.extraLines = extraLines;
	}
}
