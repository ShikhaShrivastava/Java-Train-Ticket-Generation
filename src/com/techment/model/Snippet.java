package com.techment.model;
import java.time.*;
import java.util.Calendar;
import java.util.Date;
import java.util.*;
  

public class Snippet {
 public static Boolean dateCompare(int d,int m,int y)
 {
	 // creating a Calendar object
     Calendar c = Calendar.getInstance();

     // set Month
     // MONTH starts with 0 i.e. ( 0 - Jan)
     c.set(Calendar.MONTH, m);

     // set Date
     c.set(Calendar.DATE, d);

     // set Year
     c.set(Calendar.YEAR, y);

     // creating a date object with specified time.
     Date dateOne = c.getTime();
     Date currentDate = new Date();
     return currentDate.before(dateOne);
 }
public static void main(String ar[])
{
	Scanner scanner = new Scanner(System.in);
	System.out.println("Enter the date in dd/mm/yyyy format");
	String date = scanner.next();
	String[] splitVal = date.split("/");
	int d=Integer.parseInt(splitVal[0]);
	int m=Integer.parseInt(splitVal[1]);
	int y =Integer.parseInt(splitVal[2]);
	//System.out.println(d+" "+m+" "+y);
	Date currentDate = new Date();
	System.out.println(currentDate);
	System.out.println( dateCompare(d,m,y));
	
	
	
	}



}