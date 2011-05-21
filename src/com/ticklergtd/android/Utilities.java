package com.ticklergtd.android;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;


public class Utilities {
	static final String FORMAT_BD = "yyyy-MM-dd HH:mm:ss";
	
	public static Date string2Date(String pString) {
		if (pString != null) {
			SimpleDateFormat ft = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			SimpleDateFormat ftAux = new SimpleDateFormat("yyyy-MM-dd");
			try {
				return ft.parse(pString);	
			} catch (ParseException e) {
				try {
					return ftAux.parse(pString);
				} catch (ParseException e2) {
					return null;					
				}
			}
		}
		else {
			return null;
		}
	}
	
	public static Date string2Date(String pString, String format) {
		if (pString != null) {
			SimpleDateFormat ft = new SimpleDateFormat(format);
			try {
				return ft.parse(pString);	
			} catch (ParseException e) {
				return null;					
			}
		}
		else {
			return null;
		}
	}

	public static String date2String(Date pDate, int pFormat) {
		String res = null;
		SimpleDateFormat ft = new SimpleDateFormat("dd/MM/yyyy");
		SimpleDateFormat ftAux = new SimpleDateFormat("dd/MM/yyyy");
		
		if (pDate != null) {
			if (pFormat == 1) {
				ft = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
				ftAux = new SimpleDateFormat("dd/MM/yyyy");
			}
			
			if (pFormat == 2) {
				ft = new SimpleDateFormat("dd/MM/yyyy");
				ftAux = new SimpleDateFormat("dd/MM/yyyy");
			}
			
			if (pFormat == 3) {
				ft = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
				ftAux = new SimpleDateFormat("yyyy-MM-dd");
			}
			
			try {
				return ft.format(pDate);	
			} catch (Exception e) {
				try {
					return ftAux.format(pDate);
				} catch (Exception e2) {
					return "";					
				}
			}
		}
		else {
			return "";
		}
	}
}

