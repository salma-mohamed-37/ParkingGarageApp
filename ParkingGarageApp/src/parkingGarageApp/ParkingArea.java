package parkingGarageApp;
import java.util.List;
import java.util.ArrayList;


public class ParkingArea
{
	static private List <Slot> parking_slots ;
	private Iconfiguration config;
    private static ParkingArea instance ;

	private ParkingArea(){}
	
	public List<Slot>  getparking_slots ()
	{
		return parking_slots;
	}
	
	public int search (Vehicle v)
	{
		int res= config.searchForEmptySlot(v, parking_slots);
		return res;
    }
   
	public void setConfig (Iconfiguration c)
	{
		config = c;
	}
	public static ParkingArea getInstance ()
	{
		if(instance ==null)
		{
			instance = new ParkingArea();
			parking_slots = new ArrayList<Slot>();
			return instance;
		}
		else
		{
			return instance;
		}
		
	}

    public void addParkingSlot (Slot s)
    {
    	parking_slots.add(s);
    }

    

}