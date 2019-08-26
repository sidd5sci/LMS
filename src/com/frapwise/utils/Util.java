package com.frapwise.utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.StringWriter;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.FormulaEvaluator;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.frapwise.entities.Leave;
import com.frapwise.vo.ReportVO;

public class Util {

	
	/**
	 * 
	 * @param dateStart
	 * @param dateStop
	 * @return
	 */
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
	
	/**
	 * 
	 * @param dateStart
	 * @param dateStop
	 * @return
	 */
	public static long getDaysNoWeekends(String dateStart,String dateStop) {

		//HH converts hour in 24 hours format (0-23), day calculation
		SimpleDateFormat format = new SimpleDateFormat("yy-M-dd");
		Calendar c = Calendar.getInstance();
		Date d1 = null;
		Date d2 = null;
		long diffDays = 0 ,days=0,diffHours,diffMinutes,diffSeconds;
		try {
			d1 = format.parse(dateStart);
			d2 = format.parse(dateStop);
			
			//in milliseconds
			long diff = d2.getTime() - d1.getTime();

			 diffSeconds = diff / 1000 % 60;
			 diffMinutes = diff / (60 * 1000) % 60;
			 diffHours = diff / (60 * 60 * 1000) % 24;
			 diffDays = diff / (24 * 60 * 60 * 1000) + 1;
			 
			 days=diffDays;
			 int i=0;
			 Date temp = new Date();
			 for(i=0;i<diffDays;i++) {
				 c.setTime(format.parse(dateStart));
				 c.add(Calendar.DATE,  i);
				 
				 int d = c.get(Calendar.DAY_OF_WEEK);
				 if(d == 1 || d == 7) {
					 days--;
					 System.out.println("CHECK:--"+dateStart+"|"+days+"|"+diffDays+"|"+d);
				 }
				 
			 }
			     
			 System.out.println("CHECK:"+dateStart+"|"+days+"|"+diffDays);
			 
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return days;
	}
	
	/**
	 * 
	 * @param dateStart
	 * @param dateStop
	 * @return
	 */
	public static long getDaysNoWeekendsJquery(String dateStart,String dateStop) {

		//HH converts hour `in 24 hours format (0-23), day calculation
		SimpleDateFormat format = new SimpleDateFormat("dd-M-yy");
		Calendar c = Calendar.getInstance();
		Date d1 = null;
		Date d2 = null;
		long diffDays = 0 ,days=0,diffHours,diffMinutes,diffSeconds;
		try {
			d1 = format.parse(dateStart);
			d2 = format.parse(dateStop);
			
			//in milliseconds
			long diff = d2.getTime() - d1.getTime();

			 diffSeconds = diff / 1000 % 60;
			 diffMinutes = diff / (60 * 1000) % 60;
			 diffHours = diff / (60 * 60 * 1000) % 24;
			 diffDays = diff / (24 * 60 * 60 * 1000) + 1;
			 
			 days=diffDays;
			 int i=0;
			 Date temp = new Date();
			 for(i=0;i<diffDays;i++) {
				 c.setTime(format.parse(dateStart));
				 c.add(Calendar.DATE,  i);
				 
				 int d = c.get(Calendar.DAY_OF_WEEK);
				 if(d == 1 || d == 7) {
					 days--;
					 System.out.println("CHECK:--"+dateStart+"|"+days+"|"+diffDays+"|"+d);
				 }
				 
			 }
			     
			 System.out.println("CHECK:"+dateStart+"|"+days+"|"+diffDays);
			 
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return days;
	}
	
	/**
	 * 
	 * @param l
	 * @param date1
	 * @param date2
	 * @return
	 * @throws ParseException
	 */
	public static int getDatesCollision(Leave l,String date1,String date2) throws ParseException {
		
		/**
		 * 1 - > no collision
		 * 0 - > start collision
		 * 2 - > end collision
		 * 3 - > between collision
		 */
		/**
		 * types of collisions
		 * start - start
		 * end - end
		 * start in between
		 * end in between
		 */
		int collisionType = 1; 
		SimpleDateFormat format = new SimpleDateFormat("yy-M-dd");
		Date d1 = format.parse(date1);
		Date d2 = format.parse(date2);
		
		Date ld1 = format.parse(l.getLeaveFrom());
		Date ld2 = format.parse(l.getLeaveTo());
		
		if (l.getLeaveFrom().equals(date1)) {
			collisionType = 0; // start - start 
			System.out.println(l.getLeaveFrom() + "|" + date1 + ">>" + collisionType+"START-START");
		}
		if (l.getLeaveTo().equals(date2)) {
			collisionType = 2; // end - end 
			System.out.println(l.getLeaveTo() + "|" + date2 + ">>" + collisionType+"END-END");
		}
		if(d1.compareTo(ld1) > 0 && d1.compareTo(ld2) < 0) {
			collisionType = 3;
			System.out.println(l.getLeaveFrom() + "|" + date1 + ">>" + collisionType+"START-BETWEEN");	
		}
		if(d2.compareTo(ld1) > 0 && d2.compareTo(ld2) < 0) {
			collisionType = 3;
			System.out.println(l.getLeaveTo() + "|" + date2 + ">>" + collisionType+"END-BETWEEN");	
		}
		return collisionType;
	}
	
	/**
	 * Convert sqlDate to jquery
	 * @param sqlDate
	 * @return
	 */
	public static String sqlToJquery(String sqlDate) {
		
		SimpleDateFormat sqlFormat = new SimpleDateFormat("yy-M-dd");
		SimpleDateFormat jQueryFormat = new SimpleDateFormat("dd-MM-yyyy");
		Date date = null;
		try {
			 date = sqlFormat.parse(sqlDate);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return jQueryFormat.format(date.getTime());
	}
	    
	/**
	 * Convert jquery Date to SQL
	 * @param jqueryDate
	 * @return
	 */
	public static java.sql.Date jqueryToSql(String jqueryDate) {
		
		SimpleDateFormat sqlFormat = new SimpleDateFormat("yy-M-dd");
		SimpleDateFormat jQueryFormat = new SimpleDateFormat("dd-M-yy");
		java.util.Date date = null;
		try {
			 date = jQueryFormat.parse(jqueryDate);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		java.sql.Date sqlDate = new java.sql.Date( date.getTime() );
		
		
		return sqlDate;
		
	}
	/**
	 * 
	 * @param o
	 * @return
	 */
	public static StringWriter encodeJson(Object o) {
		ObjectMapper objectMapper = new ObjectMapper();
        StringWriter stringEmp = new StringWriter();
        try {
			objectMapper.writeValue(stringEmp, o);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        
        return stringEmp;
	}
	/**
	 * 
	 * @param json
	 * @return
	 */
	public static Object decodeJson(String json) {
		return null;
		
	}
	
	/**
	 * 
	 * @param str
	 * @param index
	 * @param replace
	 * @return
	 */
	public String replace(String str, int index, char replace){     
	    if(str==null){
	        return str;
	    }else if(index<0 || index>=str.length()){
	        return str;
	    }
	    char[] chars = str.toCharArray();
	    chars[index] = replace;
	    return String.valueOf(chars);       
	}
	/**
	 * read Excel header
	 * @String filename
	 * @throws IOException 
	 */
	@SuppressWarnings("deprecation")
	public static int readerExcelHeader(String filename) throws IOException {
		
		try {
			FileInputStream fin = new FileInputStream(new File("D:\\siddhartha\\Siddhartha\\My_works\\java_web\\LeaveManagmentSystem\\WebContent\\resources\\uploads\\"+filename));
		
			// workbook instance
			XSSFWorkbook wb = new XSSFWorkbook(fin);
			//create sheet
			XSSFSheet sheet = wb.getSheetAt(0);
			
			// 
			FormulaEvaluator formulaEvaluator = wb.getCreationHelper().createFormulaEvaluator();
		
			for(Row row : sheet) {
				for(Cell cell: row) {
					
					switch(formulaEvaluator.evaluateInCell(cell).getCellType()) {
						case Cell.CELL_TYPE_BLANK:
							System.out.print("empty"+"\t");
							break;
						
						case Cell.CELL_TYPE_STRING:
							System.out.print(cell.getStringCellValue()+"\t");
							
							break;
					}
				}
				System.out.println(" ");
			}
		
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		
		
		
		return 0;
		
	}
	
}
