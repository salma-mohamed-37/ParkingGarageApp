package parkingGarageApp;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class Vehicles_operations 
{
	private List<Vehicle> parked_vehicles;
	private Financial f;
	
	Vehicles_operations ()
	{
		parked_vehicles = new ArrayList<Vehicle>();
		f = new Financial();
	}
	public Financial getF()
	{
		return f;
	}
	void parkin (Vehicle ve, int emptySlot, List <Slot> p)
	{
		if(emptySlot !=-1)
		{	
			ve.setArrivalTime(LocalDateTime.now());
			parked_vehicles.add(ve);
			p.get(emptySlot).setStatus("busy");
			p.get(emptySlot).setVehicle_id(ve.getInfo().getId());
			System.out.print("the car parked");
		}
		else
		{
			System.out.print("No suitable place");
		}
	}
	
	double parkout(int vid, List <Slot> p)
	{
		double fees =5;
		for(int j= 0;j< parked_vehicles.size();j ++)
		{
			if(parked_vehicles.get(j).getInfo().getId() == vid) 
			{
				parked_vehicles.get(j).setDepartureTime(LocalDateTime.now());
				parked_vehicles.get(j).calculateTotalTime();
				
				fees = parked_vehicles.get(j).calculatingParkingFees ();
				f.add_to_total_income(fees);
			}
		}
		
		for (int i=0;i<p.size();i++)
		{
			if(p.get(i).getVehicle_id()==vid)
			{
				p.get(i).setStatus("empty");
				p.get(i).setVehicle_id(0);	
			}
		}
		return fees;
	}
	
	int getTotal_number_of_vehicles ()
	{
		return parked_vehicles.size();
	}
}
