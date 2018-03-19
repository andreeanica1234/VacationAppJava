import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class Vacation implements Cloneable{

	private ArrayList<Country> countries = new ArrayList<Country>();
	private ArrayList<Location> locations = new ArrayList<Location>();
	
	/* Check if the countries ArrayList contains a certain country. */
	private boolean containsCountry(String countryName){
		for (Country c : countries){
			if (c.getName().equals(countryName))
				return true;
		}
		return false;
	}
	
	/* Search and return a certain country.*/
	private Country getCountry(String countryName){
		for (Country c : countries){
			if (c.getName().equals(countryName))
				return c;
		}
		return null;
	}
	
	/* Parse input for a location. */
	public void readFileLocation(String fileName) {
		
		try {
			Scanner sc = new Scanner(new File(fileName));
			
			String name = sc.nextLine();
			int id = sc.nextInt();
			String countryName = sc.next();
			String regionName = sc.next();
			String cityName = sc.next();
			int hotelPriceDay = sc.nextInt();
			
			int noActivities = sc.nextInt();
			ArrayList<Activity> activities = new ArrayList<Activity>();
			for (int i = 0; i < noActivities; i++){
				sc.nextLine();
				String activityName = sc.nextLine();
				int activityPrice = sc.nextInt();
				activities.add(new Activity(activityName, activityPrice));
			}
			
			int noPeriods = sc.nextInt();
			ArrayList<Period> periods = new ArrayList<Period>();
			for (int i = 0; i < noPeriods; i++){
				sc.nextLine();
				String firstDay = sc.next();
				String lastDay = sc.next();
				int noDays = Integer.parseInt(sc.next());
				Period p = new Period(firstDay, lastDay, noDays);
				p.setTotalPrice(hotelPriceDay);
				periods.add(p);
			}
			
			/* Create location, city, region and country objects and add them to the arrays. */
			Location location = new Location(id, name, cityName, regionName, countryName, hotelPriceDay, periods, activities);
			locations.add(location);
			
			City city = new City(cityName);
			Region region = new Region(regionName);
			Country country = new Country(countryName);
			
			if (this.containsCountry(countryName)){
				/* If the country already exists, add the other objects in the correct country. */
				this.getCountry(countryName).addRegion(region, city, locations.size()-1);
			}
			else{
				/* New country. */
				country.addRegion(region, city, locations.size()-1);
				countries.add(country);
			}
				
			sc.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}
	
	/* Read the file that contains the files with the locations. */
	private void readData(String fileLocations) {
		int noOfFiles;
		File inputFile = new File(fileLocations);
		
		try {
			Scanner sc = new Scanner(inputFile);
			noOfFiles = sc.nextInt();
			
			for (int i = 0; i < noOfFiles; i++){
				/* Read every location details. */
				this.readFileLocation(sc.next());
			}
			
			sc.close();
		} catch (FileNotFoundException e) {
			System.out.println("File not found! File ignored");
		}	
	}
	
	/* Starts communicating with the user, waiting for commands. */
	public void startCommunication(){
		Print print = new Print();
		print.waitInput(countries, locations);
	}
	
	public Object clone() throws CloneNotSupportedException{
		return super.clone();
	}
	
	public static void main(String []args){
		Vacation v = new Vacation();
		
		v.readData(args[0]);
		
		System.out.println("For leaving type exit at any moment!");
		v.startCommunication();
	}
}
