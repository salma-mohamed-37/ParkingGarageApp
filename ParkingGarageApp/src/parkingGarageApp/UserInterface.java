package parkingGarageApp;
import java.util.Formatter;
import java.util.Scanner;

public class UserInterface
{
    public Scanner keyboard = new Scanner(System.in);
	private static ParkingArea Pa;
	private static Vehicles_operations Vo;
	
	public UserInterface()
	{
		keyboard = new Scanner(System.in);
		 Pa =ParkingArea.getInstance();
		 Vo = new Vehicles_operations();
	}
	
	public void displayMainMenu()
	{
		int choice =0;
		chooseConfiguration();
		addSlots();
		while(choice != 8)
		 {
			 System.out.print("\nEnter the number of your choice:" );
			 System.out.print("\n1-Add vehicle and park-in");
			 System.out.print("\n2-Park-out");
			 System.out.print("\n3-Get total income & total number of vehicles");
			 System.out.print("\n4-Display available parking slots");
			 System.out.print("\n5-Exit");
			 System.out.print("\nYour choice: ");
			 choice = keyboard.nextInt();
			 
			 switch(choice)
			 {
			   case 1: {parkin(); break;}
			   
			   case 2: {parkout(); break;}
			   
			   case 3:{displayIncomeandNumberofVehicles(); break;}
			   
			   case 4:{displayAvailableParkingSlots(); break;}
			   
			   case 5:{System.exit(0); break;}
			 }	 
		 }
	}
	
	private void chooseConfiguration()
	{
		int c;
		System.out.println("Choose number of configuration");
		System.out.println("1- First come first served \n2- Best fit approach");
		System.out.print("Your choice: ");
		c = keyboard.nextInt();
		if(c==1)
	    {
	       Pa.setConfig(new First_come());
	    }
	    else if (c==2)
	    {
	  	   Pa.setConfig(new Minimum_dimensions());
	    }
	    else
	    {
	    	chooseConfiguration();
	    }
	}
	
	private void addSlots()
	{
		int num;
		System.out.print("Enter the number of slots: ");
		num = keyboard.nextInt();
		for(int i=0;i<num;i++)
		{
			Slot s = new Slot();
			System.out.print("Enter the ID of the slot ");
			s.setId(keyboard.nextInt());
			System.out.print("Enter the depth of the slot ");
			s.setDepth(keyboard.nextDouble());
			System.out.print("Enter the width of the slot ");
			s.setWidth(keyboard.nextDouble());
			Pa.addParkingSlot(s);
		}
	}
	
	private void parkin()
	{
		Vehicle v = new Vehicle();
		System.out.print("Enter the ID of the vehicle ");
		v.getInfo().setId(keyboard.nextInt());
		System.out.print("Enter the model name of the vehicle ");
		v.getInfo().setModel_name(keyboard.next());
		System.out.print("Enter the model year of the vehicle ");
		v.getInfo().setModel_year(keyboard.nextInt());
		System.out.print("Enter the depth of the vehicle ");
		v.getInfo().setDepth(keyboard.nextDouble());
		System.out.print("Enter the width of the vehicle ");
		v.getInfo().setWidth(keyboard.nextDouble());
		
		Vo.parkin(v, Pa.search(v),Pa.getparking_slots());
	}
	
	private void parkout()
	{
		 System.out.print("Enter the id of the vehicle: ");
		 System.out.print("The fees = "+Vo.parkout(keyboard.nextInt(), Pa.getparking_slots())+" LE");
	}
	
	private void displayIncomeandNumberofVehicles()
	{
		System.out.println("The total income: "+Vo.getF().getTotal_income()+" LE");
		System.out.print("Total number of vehicles: "+Vo.getTotal_number_of_vehicles ()+" vehicles");
	}
	
	private void displayAvailableParkingSlots ()
	{
		 Formatter f;
		   f=new Formatter ();
		
	       f.format("%10s %10s %10s \n","slot id","depth","width");
		
		   for (int i=0;i<Pa.getparking_slots().size();i++)
		    {
			   if (Pa.getparking_slots().get(i).getStatus()=="empty")
			   {
			      f.format("%8s %11s %10s \n", Pa.getparking_slots().get(i).getId(),Pa.getparking_slots().get(i).getDepth(),Pa.getparking_slots().get(i).getWidth());
			   }
			}
		    System.out.println(f);
		    f.close();
	}
	
	
	
	
}
