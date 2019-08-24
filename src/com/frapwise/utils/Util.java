package com.frapwise.utils;

import java.text.SimpleDateFormat;
import java.util.Date;

public class Util {

	
	
	public static long getDays(String dateStart,String dateStop) {

		//HH converts hour in 24 hours format (0-23), day calculation
		SimpleDateFormat format = new SimpleDateFormat("yy-M-dd");

		Date d1 = null;
		Date d2 = null;
		long diffDays = 0 ,diffHours,diffMinutes,diffSeconds;
		try {
			d1 = format.parse(dateStart);
			d2 = format.parse(dateStop);

			//in milliseconds
			long diff = d2.getTime() - d1.getTime();

			 diffSeconds = diff / 1000 % 60;
			 diffMinutes = diff / (60 * 1000) % 60;
			 diffHours = diff / (60 * 60 * 1000) % 24;
			 diffDays = diff / (24 * 60 * 60 * 1000);
			 
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return diffDays;
	}
	public static long getDaysNoWeekends(String dateStart,String dateStop) {

		//HH converts hour in 24 hours format (0-23), day calculation
		SimpleDateFormat format = new SimpleDateFormat("yy-M-dd");

		Date d1 = null;
		Date d2 = null;
		long diffDays = 0 ,diffHours,diffMinutes,diffSeconds;
		try {
			d1 = format.parse(dateStart);
			d2 = format.parse(dateStop);
			
			//in milliseconds
			long diff = d2.getTime() - d1.getTime();

			 diffSeconds = diff / 1000 % 60;
			 diffMinutes = diff / (60 * 1000) % 60;
			 diffHours = diff / (60 * 60 * 1000) % 24;
			 diffDays = diff / (24 * 60 * 60 * 1000);
			 
			 long days=0,i=0;
			 Date temp = new Date();
			 for(i=0;i<diffDays;i++) {
				 
				 temp.setDate(d1.getDate());
			 }
			 
			 
			 
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return diffDays;
	}
}
