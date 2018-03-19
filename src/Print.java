import java.util.ArrayList;
import java.util.Scanner;

public class Print {
	
	private void exit(){
		System.out.println("Goodbye!");
		System.exit(0);
	}
	
	/* Parse the top5 command. */
	public void printTopFive(String cmdName, ArrayList<Country> countries, ArrayList<Location> locations){
		PrintTopFive display = new PrintTopFive();
		
		if (cmdName.equals("locations") || cmdName.equals("location")){ 
			
			display.displayTopFive(locations, "", 1);
			
		} else if(cmdName.equals("city") || cmdName.equals("cities")){
			Scanner sc = new Scanner(System.in);
			
			System.out.println("Type the name of the city: ");
			String city = sc.nextLine().toLowerCase().replace(" ", "");
			if (city.equals("exit"))
				exit();
			
			display.displayTopFive(locations, city, 2);
			
		} else if (cmdName.equals("region") || cmdName.equals("regions") ){
			Scanner sc = new Scanner(System.in);
			
			System.out.println("Type the name of the region: ");
			String region = sc.nextLine().toLowerCase().replace(" ", "");
			if (region.equals("exit"))
				exit();
			
			display.displayTopFive(locations, region, 3);
			
		} else if (cmdName.equals("country") || cmdName.equals("countries")){
			Scanner sc = new Scanner(System.in);
			
			System.out.println("Type the name of the country: ");
			String country = sc.nextLine().toLowerCase().replace(" ", "");
			if (country.equals("exit"))
				exit();
			
			display.displayTopFive(locations, country, 4);
			
		} else if (cmdName.equals("exit")){
			exit();
		} else {
			System.out.println("Option unavailable!\n");
			return;
		}
		
		return;
	}
	
	/* Print all the information, in hierarchical mode. */
	public void printAll(ArrayList<Country> countries, ArrayList<Location> locations){
		System.out.println("Print All Available Locations: ");
		for (Country c : countries){
			System.out.println( "Country: " + c.getName());
			for (Region r : c.getRegions()){
				System.out.println("   Region: " + r.getName());
				for (City city: r.getCities()){
					System.out.println("       City: " + city.getName());
					for (Integer i : city.getLocations()){
						System.out.println("            " + locations.get(i).getName());
					}
				}
			}
		}
	}
	
	/* Print all the info available for a location. */
	public void printLocationInfo(String locationName, ArrayList<Location> locations){
		Scanner sc = new Scanner(System.in);
		String in;
		
		for (Location l : locations){
			if (l.getName().replaceAll(" ", "").toLowerCase().equals(locationName.replace(" ", "").toLowerCase())){	
				System.out.println(l.toString(true));
				
				System.out.println("To see the price of available periods type P. To return type R.");
				in = sc.next().toLowerCase();
				
				if (in.equals("r")){
					return;
				} else if (in.equals("p")){
					System.out.println("Do you want to see the prices in RON or EURO (1€ = 4.5RON)? <ron / euro>");
					in = sc.next().toLowerCase();
					
					/* Print all the periods available for the location and the total hotel price (ron/ euro).*/
					if (in.equals("ron")){
						for (Period p : l.getPeriods()){
							System.out.println(p.getNoDays() + " days: " + p.getStringFirstDay() + " -> " +
									p.getStringLastDay() + "	Total price: " + p.getTotalPrice() + "RON");
						}
					} else if (in.equals("euro")){
						for (Period p : l.getPeriods()){
							String priceEuro = String.format("%.2f", (p.getTotalPrice()/4.5));
							System.out.println(p.getNoDays() + " days: " + p.getStringFirstDay() + " -> " +
									p.getStringLastDay() + "	Total price: " + priceEuro + "€");
						}
					} else if (in.equals("exit")){
						sc.close();
						exit();
						}
				} else if (in.equals("exit")){
					sc.close();
					exit();
				}
			}
			
			return;
		}
		
		System.out.println("Location unavailable! Try again:");
		return;
	}
	
	/* Print the locations available in a city. */
	public void printCityInfo(String cityName, ArrayList<Country> countries, ArrayList<Location> locations){
		for (Country c : countries){
			for (Region r : c.getRegions()){
				for (City city: r.getCities()){
					if (city.getName().toLowerCase().equals(cityName.toLowerCase())){
						System.out.println(city.getLocations().size() + " Locations: ");
						for (Integer i : city.getLocations()){
							System.out.println("	-> " + locations.get(i).getName());
						}
						System.out.println();
						return;
					}
				}
			}
		}
		
		System.out.println("City unavailable!");
		return;
	}
	
	/* Print the cities available in a region. */
	public void printRegionInfo(String regionName, ArrayList<Country> countries){
		for (Country c : countries){
			for (Region r : c.getRegions()){
				if (r.getName().toLowerCase().equals(regionName.toLowerCase())){
					System.out.println("Available cities: ");
					for (City city : r.getCities()){
						System.out.println("	-> " + city.getName());
					}
					return;
				}
			}
		}
		
		System.out.println("Region unavailable!");
		return;
	}
	
	/* Print the regions available in a country. */
	public void printCountryInfo(String countryName, ArrayList<Country> countries){
		for (Country c : countries){
			if (c.getName().toLowerCase().equals(countryName.toLowerCase())){
				System.out.println("Available regions: ");
				for (Region r : c.getRegions()){
					System.out.println("	-> " + r.getName());
				}
				return;
			}
		}
		
		System.out.println("Country unavailable!");
		return;
	}
	
	/* Main meniu between user and app. The 'origin' of all commands. */
	public void waitInput(ArrayList<Country> countries, ArrayList<Location> locations){
		Scanner sc = new Scanner(System.in);
	
		/* Parse command. */
		System.out.println("What type of info do you need? <allLocations / top5 / location / city / regions / country / cheap activity>");
		String info = sc.nextLine().toLowerCase();
		
		if (info.equals("alllocations")){
			printAll(countries, locations);
		} else if (info.equals("location") || info.equals("locations")){
			System.out.println("Type the name of the location: ");
			
			String location = sc.nextLine();
			if (location.equals("exit")){
				sc.close();
				exit();
			}
			
			printLocationInfo(location, locations);	
		} else if (info.equals("city") || info.equals("cities")){
			System.out.println("Type the name of the city: ");
			
			String city =  sc.nextLine();
			if (city.equals("exit")){
				sc.close();
				exit();
			}
			
			printCityInfo(city, countries, locations);
		} else if (info.equals("region") || info.equals("regions")){
			System.out.println("Type the name of the region: ");
			
			String region =  sc.nextLine();
			if (region.equals("exit")){
				sc.close();
				exit();
			}
			
			printRegionInfo(region, countries);
		} else if (info.equals("country") || info.equals("countries")){
			System.out.println("Type the name of the country: ");
			
			String country = sc.nextLine();
			if (country.equals("exit")){
				sc.close();
				exit();
			}
			
			printCountryInfo(country, countries);
		} else if (info.equals("top5")){
			System.out.println("Top5: locations / city / region / country / exit");
			
			String cmdName = sc.nextLine().toLowerCase();
			if (cmdName.equals("exit")){
				sc.close();
				exit();
			}
			
			printTopFive(cmdName, countries, locations);
		} else if (info.replace(" ", "").equals("cheapactivity")){
			CheapActivities cheapActivities = new CheapActivities();
			cheapActivities.findCheapestActivity(locations);
		} else if (info.equals("exit")){
			sc.close();
			exit();
		}
		
		/* Until exit, keep communicating with the user. */
		waitInput(countries, locations);
	}
}
