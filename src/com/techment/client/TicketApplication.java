package com.techment.client;

import java.util.*;


import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.sql.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.*;
import com.techment.model.Passenger;
import com.techment.model.Ticket;
import com.techment.model.Train;
import com.techment.utility.ConnectionProperty;
import java.io.*;	

import java.io.*;
import com.techment.*;

class NameSorting implements Comparator<Passenger>
{

	@Override
	public int compare(Passenger o1, Passenger o2) {
		// TODO Auto-generated method stub
		return o1.getName().compareTo(o2.getName());
	}
	
}


public class TicketApplication {

	public static void main(String[] args) throws ParseException, IOException {
		// TODO Auto-generated method stub
		int num =0;
		do 
		{
			if(num==1)
			{
				
				
				String destination=null;
				String source =null;
				 int trainNo =0;
				 String trainName=null;
				 double ticketPrice=0;
				 double sumFare = 0;
				 String PNR=null;
				
				
				
				 
				 double totalTicketPrice=0;
				
				int flag=0;
				Scanner scanner = new Scanner(System.in);
				System.out.println("Enter the Train Number : ");
				int trainNum  = scanner.nextInt();
				
				
				//date
				
		
				
				try {
					//step-1
					Class.forName("com.mysql.cj.jdbc.Driver");
					System.out.println("Driver Class is found");
					
					//step-2
					Connection con = DriverManager.getConnection(ConnectionProperty.URL, ConnectionProperty.ID, ConnectionProperty.PASSWORD);
					
					PreparedStatement ps = con.prepareStatement("select TRAIN_NO  from TRAINS;");
					
					ResultSet rs=ps.executeQuery();
		//			
					
					while(rs.next())
					{
						
						if(trainNum==rs.getInt(1))	
						{					 
//							System.out.println("Trainno = "+rs.getInt(1));
							flag = 1;
						}
					}
					
					
					PreparedStatement ps1 = con.prepareStatement("select * from TRAINS");
				
					ResultSet rs1=ps1.executeQuery();
		//			
					
					while(rs1.next())
					{
						
						
						
						if(trainNum==rs1.getInt(1))	
						{		
							
							trainNo = rs1.getInt(1);
							trainName = rs1.getString(2);
							
							source = rs1.getString(3);	
							destination = rs1.getString(4);
							ticketPrice = rs1.getDouble(5);
							System.out.println("\nTrain No: "+rs1.getInt(1)+"\nTrain name: "+trainName+"\nSource: "+source+"\nDestination: "+destination+"\nTicket Price: "+ticketPrice+"INR");
											
						}
					}
					
						con.close();
						
				}catch(Exception e) {
					
					System.out.println("caught "+e);
				}
				
				
				if(flag==1)
				{				
					System.out.println("\nEnter Travel Date (dd/MM/yyyy) : ");
					String travelDate = scanner.next();
					
					
					//compare date
					
					String[] splitVal = travelDate.split("/");
					int d=Integer.parseInt(splitVal[0]);
					int m=Integer.parseInt(splitVal[1]);
					int y =Integer.parseInt(splitVal[2]);
					//System.out.println(d+" "+m+" "+y);
					Date currentDate = new Date();
					System.out.println(currentDate);
					System.out.println( dateCompare(d,m,y));
					
					boolean checkOldDate = dateCompare(d,m,y);
					
					if(checkOldDate==false)
					{
						
						System.out.println("Travel Date is before current date");
					}
					else
					{
						
						
					
			
				    
					Date date1=new SimpleDateFormat("dd/MM/yyyy").parse(travelDate);  
					
					SimpleDateFormat formatter = new SimpleDateFormat("yyyyMMdd");  
				    String strDate = formatter.format(date1);  
					
					
					
					System.out.println("\nEnter Number of Passengers  : ");
					int totalPassengers = scanner.nextInt();
					ArrayList<Passenger> passengers = new ArrayList<Passenger>(totalPassengers);
					
					for(int i =1;i<=totalPassengers;i++)
					{
						
										
						
						
						System.out.println("\nEnter Passenger Name : ");
						String name = scanner.next();
						
						
						System.out.println("\nEnter Age : ");
						int age = scanner.nextInt();
						
						
						System.out.println("\nEnter Gender(M/F) : ");
						String gender   = scanner.next();
						
						passengers.add(new Passenger(name,age,gender));
						
						
						
					
					}
				
					
				System.out.println("\n train number "+trainNum );
				System.out.println("\n date ="+strDate);
				System.out.println("\n totalPassengers ="+totalPassengers);
				
				Collections.sort(passengers,new NameSorting());
				System.out.println("after sorting ");
				
				for ( Passenger passenger :  passengers)
				{
					
					System.out.println("Name : "+ passenger .getName()+" Age: "+passenger .getAge()+" Gender: "+passenger.getGender());
					
				}
				
				Train train = new Train(trainNo,trainName,source,destination,ticketPrice);
				
				
				
				Ticket ticket1 = new Ticket(date1,train);
				
				PNR = ticket1.generatePNR(source,destination);
				System.out.println("PNR : "+PNR);
				
		
				
				for ( Passenger passenger :  passengers)
				{
					
					double fare = ticket1.calcPassengerFare(passenger);
		//			 totalTicketPrice = ticket1.calculateTotalTicketPrice(fare);
					 sumFare +=fare;
								
		//			System.out.println("Name : "+ passenger .getName()+" Age: "+passenger .getAge()+" Gender: "+passenger.getGender()+"Fare : "+fare );
					
				}
				System.out.println("Totalticket = "+sumFare);
					
				
				// ticket writting 
				
				
				FileWriter fw = new FileWriter(PNR+".txt");
				
		//		
		//	System.out.println(fw.exists());
		//	fw.createNewFile(); 		// crate file
				
				
				BufferedWriter bw = new BufferedWriter(fw);
				
				bw.write("PNR:        : "+PNR);
				bw.newLine();
				bw.write("Train no    : "+trainNo);
				bw.newLine();
				bw.write("Train Name  : "+trainName);
				bw.newLine();
				bw.write("From        : "+source);
				bw.newLine();
				bw.write("To          : "+destination);
				bw.newLine();
				bw.write("Travel Date : "+date1);
				bw.newLine();
				
				bw.write("Passengers  : \n");
				bw.newLine();
				
				
				for ( Passenger passenger :  passengers)
				{
					
					double fare = ticket1.calcPassengerFare(passenger);
		//			 totalTicketPrice = ticket1.calculateTotalTicketPrice(fare);
					 sumFare +=fare;
								
					System.out.println(" name : "+ passenger .getName()+" age: "+passenger .getAge()+" gender: "+passenger.getGender()+" fare : "+fare );
					bw.write("Name : "+ passenger .getName()+" Age: "+passenger .getAge()+" Gender: "+passenger.getGender()+" Fare : "+fare );
					bw.newLine();
					
				}
				System.out.println("\n Totalticket = "+sumFare);
				bw.newLine();
				bw.write(" Total Price : "+sumFare);
				
				
				bw.flush();
				bw.close();
				
				num=0;
				}
				}
				else
					System.out.println("Train with given train number does not exist");
		
			}
			else
			{
				Scanner scanner = new Scanner(System.in);
				System.out.println("Press 1 to continue ");				
				num = scanner.nextInt();
				if(num!=1)
				{
					System.out.println("Thank you");
					break;
				}
			}
	}while(true); //closing of while loop
		
	}

	private static Boolean dateCompare(int d, int m, int y) {
		// TODO Auto-generated method stub
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

}
