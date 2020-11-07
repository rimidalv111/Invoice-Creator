package com.rimidalv111.util;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class Clock extends Thread
{
	private Util util;

	public Clock(Util u)
	{
		util = u;
	}

	@Override
	public void run()
	{
		while (true)
		{
			try
			{
				util.getMain().getLblDateTime().setText(getTime() + " " + getDate());
				util.getMain().getLblDateTime().repaint();
				Thread.sleep(1000);
			} catch(InterruptedException iex)
			{
				System.out.println("Exception in clock thread: " + iex.getMessage());
			}
		}
	}

	public String getDate()
	{
		Date date = new Date();
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		int year = cal.get(Calendar.YEAR);
		int month = cal.get(Calendar.MONTH);
		int day = cal.get(Calendar.DAY_OF_MONTH);
		return month + "/" + day + "/" + year;
	}

	public String getTime()
	{
		DateFormat dateFormat = new SimpleDateFormat("HH:mm:ss a");
		Date date = new Date();
		return dateFormat.format(date);
	}

	public Util getUtil()
	{
		return util;
	}

	public void setUtil(Util util)
	{
		this.util = util;
	}
}
