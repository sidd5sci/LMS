package com.frapwise.controllers;

import java.io.IOException;

import org.apache.poi.EncryptedDocumentException;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;

import com.frapwise.utils.Util;

public class ConsoleController {

	
	public static void main(String[] args) throws IOException, EncryptedDocumentException, InvalidFormatException {
		
		String filename = "report1.xlsx";
		Util.readerExcelFile(filename);
	}
}
