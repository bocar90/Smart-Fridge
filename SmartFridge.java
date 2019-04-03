package edu.cuny.csi.csc330.lab4;

import java.util.*;
import java.time.*;
import java.text.*;

//import edu.cuny.csi.csc330.radio.Radio;

public class SmartFridge 
{
	   private boolean on = false;
	   private String name;
	   private String brand;
	   private int capacity;
	   private int temperature;
	   private boolean open;
	   private ArrayList<String> items=new ArrayList<String>();
	   private String note;
	   private String startDate;
	   private String endDate;
	   private String startTime;
	   private String endTime;
	   
	   public SmartFridge(String name,String brand,int capacity)
	   {
	       this.name=name;
	       this.brand=brand;
	       this.capacity=capacity;
	   }
	  
	   public void turnOff()
	   {
		   on = false;  
	   }
	  
	   public void turnOn()
	   {
	       on = true;
	   }
	  
	   public void open()
	   {
	       open = true;
	   }
	   
	   public void close()
	   {
		   open = false;
	   }
	  
	   public void selectTemperature(int t)
	   {
	       this.temperature = t;
	   }
	  
	   public void addItem(String item)
	   {
	       if(on == false)
	           System.out.println("pls turn on before you putting anything inside me or your food might get rotten");

	       if(open == false)
	           System.out.println(" Are you blind?? my door is not opened. ");
	       else
	           items.add(item);
	   }
	  
	  
	   public void getItem(String item)
	   {
	       if(open ==false)
	           System.out.println(" Are you blind?? my door is not opened. ");  
	       else
	       {
	           if(items.contains(item))
	           {
	               if(on==false)
	                   System.out.println("you forgot to turn me on and so your "+item+" dried up");
	               else 
	            	   System.out.println("Here is your item: " + item);
	           }
	           else
	               System.out.println("there is no such item in");
	       }
	   }
	   
	   public void touchscreen()
	   {
		   Date sDate = null, eDate=null; LocalTime sTime=null, eTime=null; String item;
		   java.util.Date date = new java.util.Date(); //instance of predefined Date class	
		   Scanner reader = new Scanner(System.in);  // Reading from System.in
		   System.out.println("\t\t*****\tWELCOME TO TOUCH SCREEN\t*****");
		   System.out.println("Fridge Name: "+ name + "\nBrand: "+ brand);
		   System.out.println("\nEnter a note for reminder: ");
		   note = reader.nextLine();
		   System.out.println("Enter the start date in the format dd-MM-yyyy: ");
		   startDate = reader.nextLine();
		   System.out.println("Enter the start time in the format hh:mm:ss: ");
		   startTime = reader.nextLine();
		   System.out.println("Enter the end date in the format dd-MM-yyyy: ");
		   endDate = reader.nextLine();
		   System.out.println("Enter the end time in the format hh:mm:ss: ");
		   endTime = reader.nextLine();
		   
		   SimpleDateFormat format = new SimpleDateFormat("dd-MM-yyyy");
		   try {
			    //Parsing the String
			   sDate = format.parse(this.startDate);
			   eDate = format.parse(this.endDate);
			   sTime=LocalTime.parse(this.startTime);
			   eTime=LocalTime.parse(this.endTime);
			} catch (ParseException e) {
			    // TODO Auto-generated catch block
			    e.printStackTrace();
			}	  
		   
		 //Displaying current date and time in 12 hour format with AM/PM
		   int free = capacity-items.size();
		   System.out.println("\nDate: " + date + "\n\nReminder:\n\tYou have planned this: " + note + "\n\tFrom: "+ 
				   sDate + " To " + eDate + "\n\tFrom: "+ sTime + " To " + eTime + "\n");
		   
		   System.out.println(toString() + "\n\nThere is enough room for " + free + " more items, would you like to order something(Y/N)?");
		   char answer = reader.next().charAt(0);
		   
		   if(answer=='Y' || answer=='y' )
		   {
			   System.out.println("How many items do you want to order?");
			   int nbItems = reader.nextInt();
			   reader.skip(".*");
			   if(nbItems<free)
			   {	for(int i = 0; i < nbItems; i++)
					{
						System.out.println("Add an item to the fridge: ");
						item = reader.next();
						addItem(item); 
					}
			   }
			   else
				   System.out.println("There is only enough room for " + free + " items!\n");
		   }
		   else
			   System.out.println("No problem, next time maybe!\n");
		   
		   System.out.println("Do you need an item from the fridge(Y/N)?");
		   char option = reader.next().charAt(0);
		   if(option=='Y' || option =='y')
		   {
			   System.out.println("which item?");
			   item = reader.next();
			   getItem(item);
		   }
		   reader.close();
		   System.out.println("\n\t\t*****\tEND TOUCH SCREEN\t*****");
	   }
	   
	   
	   public String toString()
	   { 
	       if(on)
	           return String.format("The fridge is on at " + temperature + " degrees " + "it contains:" + items);
	       else
	    	   return String.format("The fridge is off and it contains following:" + items);
	   }


	public static void main(String[] args) 
	{
		// TODO Auto-generated method stub
			
		Scanner reader = new Scanner(System.in);  // Reading from System.in
		System.out.println("\t\t*****\tSmart Fridge Configuration\t*****\n\n");
		System.out.println("Enter the fridge name: ");
		String fridgeName = reader.next(); // Scans the next token of the input as a String.
		System.out.println("Enter the brand: ");
		String fridgeBrand = reader.next(); // Scans the next token of the input as a String.
		System.out.println("Enter the fridge capacity as integer: ");
		int fridgeCapacity = reader.nextInt(); // Scans the next token of the input as a String.
		System.out.println("\n\t\t*****\tEnd of Configuration\t*****\n\n");
		//reader.close();
		// Create instance of - power up & down ... other ops, and 
		//  display the powerState of the Fridge instance 
		SmartFridge myFridge = new SmartFridge(fridgeName, fridgeBrand, fridgeCapacity);  
		System.out.println("New Instance\n" + myFridge + "\n");
		
		// turn it on
		myFridge.turnOn(); 
		System.out.println("Turned On\n" + myFridge + "\n");
		
//		// select temperature for the fridge 
		System.out.println("Type the temperature that works for you: ");
		int temp = reader.nextInt();
		//reader.skip(".*");
		myFridge.selectTemperature(temp); 
		System.out.println("Changed temperature\n" + myFridge + "\n");
		
		
		// open the fridge
		myFridge.open(); 
		System.out.println("Fridge open\n" + myFridge + "\n");
		
		// add item to the fridge
		for(int i=0;i<3;i++)
		{
			System.out.println("Add an item to the fridge: ");
			String item = reader.next();
			myFridge.addItem(item); 
		}
			
		//display the touchscreen
		myFridge.touchscreen();
		
//		// close the fridge
		myFridge.close(); 
		System.out.println("Fridge close\n" + myFridge + "\n");
		
		reader.close();
		// Turn off the fridge 
		myFridge.turnOff(); 
		System.out.println("Turned Off\n" + myFridge + "\n\n");
				
	}

}
