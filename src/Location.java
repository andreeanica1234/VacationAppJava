import java.util.ArrayList;

public class Location {
	private int id;
	private String name;
	private String city;
	private int hotelPriceDay;
	private float avgPriceDay;
	private ArrayList<Period> periods = new ArrayList<Period>();
	private ArrayList<Activity> activities = new ArrayList<Activity>();
	
	public Location(int id, String name, String city, int hotelPriceDay,
			ArrayList<Period> periods, ArrayList<Activity> activities){
		this.id = id;
		this.name = name;
		this.city = city;
		this.hotelPriceDay = hotelPriceDay;
		this.periods = periods;
		this.activities = activities;
		this.avgPriceDay = calculateAvgPriceDay(activities);
	}
	
	public float calculateAvgPriceDay(ArrayList<Activity> activities){
		float avg = 0;
		for (Activity a : activities){
			avg += a.getPrice();
		}
		avg /= activities.size();
		return avg;
	}
	
	public String getName(){
		return this.name;
	}
	
	public String getCity(){
		return this.city;
	}
	
	public float getAvgPriceDay(){
		return this.avgPriceDay;
	}
	
	public int getHotelPriceDay(){
		return this.hotelPriceDay;
	}
	
	public ArrayList<Activity> getActivities(){
		return this.activities;
	}
	
	public ArrayList<Period> getPeriods(){
		return this.periods;
	}

	public void addPeriod(Period period){
		periods.add(period);
	}
	
	public void addActivity(Activity activity){
		activities.add(activity);
	}
	
}
