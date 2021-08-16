package com.techment.model;

import java.text.SimpleDateFormat;
import java.util.*;



public class Ticket {

	static int counter=100;
	String pnr;
	Date travelDate;
	Train train;
	
	
	Passenger passenger;

	public Ticket() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Ticket(Date travelDate, Train train) {
		super();
		this.travelDate = travelDate;
		this.train = train;
	}
	
//	TreeMap<Passenger, Integer> passengers  = new  TreeMap<Passenger, Integer>();
	
	TreeMap<Passenger ,Integer > passengers = new TreeMap<Passenger ,Integer >();
	
	public String generatePNR(String src,String des)
	{ 
		String pnr;
	
	String travelDate;
	
	System.out.println("src ="+src+" des="+des+"src.charAt(0) "+src.charAt(0)+"des.charAt(0) "+des.charAt(0));
	  Date date = new Date();  
	    SimpleDateFormat formatter = new SimpleDateFormat("yyyyMMdd");  
	    String strDate = formatter.format(date);  
	  
	   
	   pnr = Character.toString(src.charAt(0)); 
	  String strDes = Character.toString(des.charAt(0));
	   
	 pnr = pnr.concat(strDes);
	 pnr = pnr.concat("_");
	 pnr = pnr.concat(strDate);
	 pnr = pnr.concat("_")+counter;
	 
	 
	 
//		System.out.println(" pnr sample "+src.charAt(0)+des.charAt(0)+"_"+strDate+"_"+counter);
		
		
		System.out.println("PNR :"+pnr);
		counter++;
		
		return pnr;
				
	}
	
	public double calcPassengerFare(Passenger passenger2)
	{
		double fare = 0;
		
		String gender;
//		double sum= 0;
		 
		 
				
				
					int age =  passenger2.getAge();
				  gender = passenger2.getGender();
				
//				  System.out.println("inside calPassengerfare block age "+age+" gender  "+ gender  );
				if (age<=12)
				{
					System.out.println("age is less then 12");
					fare = ((train.getTicketPrice())*0.5);
				System.out.println("price = "+fare);
				
				}
				else if(age>=60)
				{
					System.out.println("age is greater than 60");
					fare = ((train.getTicketPrice())*0.6);
					System.out.println("fare = "+fare);
					
				}
				else if(gender.equalsIgnoreCase("f"))
				{
					System.out.println("25% extra discount for females");
					fare = (train.getTicketPrice())*0.25;
					
									 
				}
				
				else
				{
					fare = train.getTicketPrice();
					 System.out.println("fare="+fare);
				}
				
				
				
		return fare;
	}
	
	void addPassenger(String name ,int age ,char gender)
	{
		
		
	}
	
	public double calculateTotalTicketPrice(Double fare)
	{
		
		 
		 
		return counter;
		
		
	}
	
	
	public StringBuilder generateTicket()
	{
		return null;
		
		
	}
	
	public void writeTicket()
	{
		
		
	}

	public int getCounter() {
		return counter;
	}

	public void setCounter(int counter) {
		this.counter = counter;
	}

	public String getPnr() {
		return pnr;
	}

	public void setPnr(String pnr) {
		this.pnr = pnr;
	}

	public Date getTravelDate() {
		return travelDate;
	}

	public void setTravelDate(Date travelDate) {
		this.travelDate = travelDate;
	}

	public Train getTrain() {
		return train;
	}

	public void setTrain(Train train) {
		this.train = train;
	}

	public Passenger getPassenger() {
		return passenger;
	}

	public void setPassenger(Passenger passenger) {
		this.passenger = passenger;
	}
	
	
}

