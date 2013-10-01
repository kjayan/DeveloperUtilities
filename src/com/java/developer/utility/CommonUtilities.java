package com.java.developer.utility;

import java.util.Calendar;
import java.util.Date;
import java.util.UUID;

public class CommonUtilities{

	private CommonUtilities(){

	}
	
	public static String generateRandomId(){
		return UUID.randomUUID().toString();
	}
	
	public static String getCurrentTimestamp(){
		return Long.toString(new Date().getTime());
	}
	
	public static String getCurrentReverseTimestamp(){
		long currentTimestamp = new Date().getTime();
		long currentReverseTS = Long.MAX_VALUE - currentTimestamp;
		return Long.toString(currentReverseTS);
	}
	
	public static String getCustomTimestamp(int year,int month,int day,int hour,int min,int sec){
		Calendar cal = Calendar.getInstance();
		cal.set(year, month, day,hour, min, sec);
		return Long.toString(cal.getTimeInMillis());
	}
	
	public static String getCustomReverseTimestamp(int year, int month, int day, int hour, int min, int sec){
		Calendar cal = Calendar.getInstance();
		cal.set(year, month, day,hour, min, sec);
		long customReverseRS = Long.MAX_VALUE - cal.getTimeInMillis();
		return Long.toString(customReverseRS);
	}
	
	public static int[] convertStringArrayToIntArray(String[] stringArray){
		int[] intArray = new int[stringArray.length];
		int i =0;
		for(String arrayElement:stringArray){
			try{
				intArray[i] = Integer.parseInt(arrayElement);
			}catch(NumberFormatException e){
				System.out.println("Number Format Exception");
				throw e;
			}
			i++;
		}
		return intArray;
	}

}
