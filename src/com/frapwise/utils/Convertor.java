package com.frapwise.utils;

import org.json.JSONArray;
import org.json.JSONObject;
import java.sql.ResultSet;

/**
 * Utility for converting ResultSets into some Output formats
 * @author sidd5sci@gmail.com
 */
 public class Convertor {
	 
	 /**
	  * Convert JSONArray to JSON string
	  * @param JSONArray
	  * @return a String
	  */
	 public static String convertJSONArrayToString(JSONArray jsonArray) {
		 
		 return jsonArray.toString().replace("},{", " ,");
	 }
	 
	 public static JSONArray convertToJSON1(ResultSet resultSet)
	            throws Exception {
	    	
	        JSONArray jsonArray = new JSONArray();
	        while (resultSet.next()) {
	        	JSONArray jsonArray1 = new JSONArray();
	            int total_rows = resultSet.getMetaData().getColumnCount();
	            for (int i = 0; i < total_rows; i++) {
	                JSONObject obj = new JSONObject();
	                
	                obj.put(resultSet.getMetaData().getColumnLabel(i + 1)
	                        .toLowerCase(), resultSet.getObject(i + 1) != null?resultSet.getObject(i + 1):"");
	                jsonArray1.put(obj);
	                            
	                //System.out.println(obj.toString());
	            }
	            
	            jsonArray.put(jsonArray1);
	        }
	        return jsonArray;
	 }
    /**
     * Convert a result set into a JSON Array
     * @param resultSet
     * @return a JSONArray
     * @throws Exception
     */
    public static JSONArray convertToJSON(ResultSet resultSet)
            throws Exception {
    	
        JSONArray jsonArray = new JSONArray();
        while (resultSet.next()) {
            int total_rows = resultSet.getMetaData().getColumnCount();
            for (int i = 0; i < total_rows; i++) {
                JSONObject obj = new JSONObject();
                
                obj.put(resultSet.getMetaData().getColumnLabel(i + 1)
                        .toLowerCase(), resultSet.getObject(i + 1) != null?resultSet.getObject(i + 1):"");
                jsonArray.put(obj);
                            
                //System.out.println(obj.toString());
            }
        }
        return jsonArray;
    }
    /**
     * Convert a result set into a XML List
     * @param resultSet
     * @return a XML String with list elements
     * @throws Exception if something happens
     */
    public static String convertToXML(ResultSet resultSet)
            throws Exception {
        StringBuffer xmlArray = new StringBuffer("<results>");
        while (resultSet.next()) {
            int total_rows = resultSet.getMetaData().getColumnCount();
            xmlArray.append("<result ");
            for (int i = 0; i < total_rows; i++) {
                xmlArray.append(" " + resultSet.getMetaData().getColumnLabel(i + 1)
                .toLowerCase() + "='" + resultSet.getObject(i + 1) + "'"); }
            xmlArray.append(" />");
        }
        xmlArray.append("</results>");
        return xmlArray.toString();
    }
}