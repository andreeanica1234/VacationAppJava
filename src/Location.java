import java.util.ArrayList;
import java.util.Comparator;

public class Location {
	private int id;
	private String name;
	private String city;
	private String region;
	private String country;
	private int hotelPriceDay;
	private float avgPriceDay;
	private ArrayList<Period> periods = new ArrayList<Period>();
	private ArrayList<Activity> activities = new ArrayList<Activity>();
	
	public Location(int id, String name, String city, String region, String country, 
			int hotelPriceDay, ArrayList<Period> periods, ArrayList<Activity> activities){
		
		this.id = id;
		this.name = name;
		this.city = city;
		this.region = region;
		this.country = country;
		this.hotelPriceDay = hotelPriceDay;
		this.periods = periods;
		this.activities = activities;
		this.avgPriceDay = calculateAvgPriceDay(activities);
		
	}
	
	public int getId(){
		return this.id;
	}
	
	public String getName(){
		return this.name;
	}
	
	public String getCity(){
		return this.city;
	}
	
	public String getRegion(){
		return this.region;
	}
	
	public String getCountry(){
		return this.country;
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
	
	/* Calculates the average sum of money spent in a day, if all the activities avaialble are accomplished. */
	public float calculateAvgPriceDay(ArrayList<Activity> activities){
		float avg = 0;
		for (Activity a : activities){
			avg += a.getPrice();
		}
		avg /= activities.size();
		return avg;
	}
	
	/* Compare two locations by the average of money spent in a day on activities.*/
	public static Comparator<Location> avgPriceDayComparator = new Comparator<Location>() {

		@Override
		public int compare(Location location1, Location location2) {
			if (location1.getAvgPriceDay() > location2.getAvgPriceDay())
				return 1;
			if (location1.getAvgPriceDay() < location2.getAvgPriceDay())
				return -1;
			return 0;
		}};
		
	/* Compare two locations by the hotel price / night. */
	public static Comparator<Location> hotelPriceDayComparator = new Comparator<Location>() {

			@Override
			public int compare(Location location1, Location location2) {
				if (location1.getHotelPriceDay() > location2.getHotelPriceDay())
					return 1;
				if (location1.getHotelPriceDay() < location2.getHotelPriceDay())
					return -1;
				return 0;
			}};
	
	/* Compare two locations by the average of money spent in a day on activities and hotel price / night.*/
	public static Comparator<Location> totalPriceDayComparator = new Comparator<Location>() {

		@Override
		public int compare(Location location1, Location location2) {
			float l1 = location1.getAvgPriceDay() + location1.getHotelPriceDay();
			float l2 = location2.getAvgPriceDay() + location2.getHotelPriceDay();
			
			if (l1 > l2)
				return 1;
			if (l1 < l2)
				return -1;
			return 0;
		}};
	
	/* Display a location (with / without periods available) */
	public String toString(boolean periods){
		String avg2decimals = String.format("%.2f", this.getAvgPriceDay());
		
		String s = new String();
		s += "	Location: " + this.getName() + "\n";
		s += "	City: " + this.getCity() + "\n";
		s += "	Hotel price / night: " + this.getHotelPriceDay() + " RON \n	Average of money spent / day on activities: " + avg2decimals + " RON\n";
		s += "	" + this.getActivities().size() + " Available activitie(s): " + "\n";
		for (Activity a : this.getActivities())
			s += "		" + a.getName() + "\n";
		
		if (periods == true){
			s += "	" + this.getPeriods().size() + " available period(s): " + "\n";
			for (Period p : this.getPeriods())
				s += "		" + p.getNoDays() + " day(s): " + p.getPeriod() + "\n";
		}	
		return s;
	}

}
