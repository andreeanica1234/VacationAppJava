import java.io.File;
import java.io.FileNotFoundException;
import java.text.DateFormat;
import java.util.ArrayList;
import java.util.Scanner;

public class Vacation{

	private ArrayList<Country> countries = new ArrayList<Country>();
	private ArrayList<Location> locations = new ArrayList<Location>();
	
	public boolean containsCountry(String countryName){
		for (Country c : countries){
			if (c.getName().equals(countryName))
				return true;
		}
		return false;
	}
	
	public Country getCountry(String countryName){
		for (Country c : countries){
			if (c.getName().equals(countryName))
				return c;
		}
		return null;
	}
	
	public void printAll(){
		System.out.println("Printare locatii ierarhic: ");
		for (Country c : countries){
			System.out.println( "Tara: " + c.getName());
			for (Region r : c.getRegions()){
				System.out.println("   Regiunea: " + r.getName());
				for (City city: r.getCities()){
					System.out.println("       Orasul: " + city.getName());
					for (Integer i : city.getLocations()){
						System.out.println("            " + locations.get(i).getName());
					}
				}
			}
		}
	}
	
	public void printLocationInfo(String locationName){
		for (Location l : locations){
			if (l.getName().equals(locationName)){	
				System.out.println("Locatie :" + locationName);
				System.out.println("	Oras : " + l.getCity());
				System.out.println("	Pret hotel / noapte: " + l.getHotelPriceDay() 
						+ " Pret mediu / zi :" + l.getAvgPriceDay());
				System.out.println("	" + l.getActivities().size() + " activitati disponibile: ");
				for (Activity a : l.getActivities())
					System.out.println("		" + a.getName());
				System.out.println("	" + l.getPeriods().size() + " perioade disponibile: ");
				for (Period p : l.getPeriods())
					System.out.println("		" + p.getNoDays() + " zile: " + p.getPeriod());
				return;
			}
		}
		
		System.out.println("Locatie inexistenta! Mai incercati!");
		waitInput();
	}
	
	public void printCityInfo(String cityName){
		for (Country c : countries){
			for (Region r : c.getRegions()){
				for (City city: r.getCities()){
					if (city.getName().equals(cityName)){
						System.out.println(city.getLocations().size() + " locatii: ");
						for (Integer i : city.getLocations()){
							System.out.println("	-> " + locations.get(i).getName());
						}
						System.out.println();
						return;
					}
				}
			}
		}
		
		System.out.println("Orasul introdus nu exista!");
		waitInput();
	}
	
	public void printRegionInfo(String regionName){
		for (Country c : countries){
			for (Region r : c.getRegions()){
				if (r.getName().equals(regionName)){
					System.out.println("Orase disponibile: ");
					for (City city : r.getCities()){
						System.out.println("	-> " + city.getName());
					}
					return;
				}
			}
		}
		
		System.out.println("Regiunea introdusa nu exista!");
		waitInput();
	}
	
	public void printCountryInfo(String countryName){
		for (Country c : countries){
			if (c.getName().equals(countryName)){
				System.out.println("Regiuni disponibile: ");
				for (Region r : c.getRegions()){
					System.out.println("	-> " + r.getName());
				}
				return;
			}
		}
		
		System.out.println("Regiunea introdusa nu exista!");
		waitInput();
	}
	
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
				periods.add(new Period(firstDay, lastDay, noDays));
			}
			
			Location location = new Location(id, name, cityName, hotelPriceDay, periods, activities);
			locations.add(location);
			City city = new City(cityName);
			Region region = new Region(regionName);
			Country country = new Country(countryName);
			
			if (this.containsCountry(countryName)){
				this.getCountry(countryName).addRegion(region, city, locations.size()-1);
			}
			else{
				country.addRegion(region, city, locations.size()-1);
				countries.add(country);
			}
				
			
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	private void readData(String fileLocations) {
		int noOfFiles;
		File inputFile = new File(fileLocations);
		
		try {
			Scanner sc = new Scanner(inputFile);
			noOfFiles = sc.nextInt();
			for (int i = 0; i < noOfFiles; i++){
				this.readFileLocation(sc.next());
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}	
	}
	
	public void waitInput(){
		Scanner in = new Scanner(System.in);
	
		System.out.println("Despre ce doriti informatii? <tara/regiune/oras/locatie/exit>");
		String info = in.nextLine();
		
		if (info.equals("locatie")){
			System.out.println("Tastati numele locatiei despre care doriti informatii:");
			String location = in.nextLine();
			printLocationInfo(location);
			waitInput();
		} 
		
		if (info.equals("oras")){
			System.out.println("Tastati numele orasului despre care doriti informatii:");
			String city =  in.nextLine();
			printCityInfo(city);
			waitInput();
		}
		
		if (info.equals("regiune")){
			System.out.println("Tastati numele regiunii despre care doriti informatii:");
			String region =  in.nextLine();
			printRegionInfo(region);
			waitInput();
		}
		
		if (info.equals("tara")){
			System.out.println("Tastati numele tarii despre care doriti informatii:");
			String country = in.nextLine();
			printCountryInfo(country);
			waitInput();
		}
		
		if (info.equals("exit")){
			System.out.println("La revedere!");
			return;
		}
		
		
		
	}
	
	public static void main(String []args){
		Vacation v = new Vacation();
		
		v.readData(args[0]);
		
		//v.printAll();
		
		v.waitInput();
	}
}
