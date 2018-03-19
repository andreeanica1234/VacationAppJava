import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Date;
import java.util.Scanner;

public class PrintTopFive {
	
	private void printSortText(){
		System.out.println("Sort (asc) by:" );
		System.out.println("	- total price in a day (hotel + activities) <totalDay>");
		System.out.println("	- the cost of activities in a day <activities>");
		System.out.println("	- the cost of hotel per night <hotel>");
	}
	
	private boolean isYes(String s){
		if (s.equals("y") || s.equals("yes"))
			return true;
		else
			return false;
	}
	
	/* Checks if [d3, d4] is in [d1, d2] */
	private boolean dateInRange(Date d1, Date d2, Date d3, Date d4){
		if (d3.after(d1)){
			if (d3.before(d2)){
				if (d4.before(d2)){
					if (d4.after(d1)){
						if (d3.before(d4)){
							return true;
						}
					}
				}
			}
		}
		
		return false;	
	}
	
	/* Get the given date (firstDay / lastDay) by the user. */
	private Date getPeriodDay(int type){
		Date date = new Date();
		Scanner sc = new Scanner(System.in);
		SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
		
		if (type == 1)
			System.out.println("Type the first day of the desired period: <dd-MM-yyyy>");
		else
			System.out.println("Type the last day of the desired period: <dd-MM-yyyy>");
		
		String day = sc.nextLine();
		try {
			date = sdf.parse(day);
		} catch (ParseException e) {
			System.out.println("Wrong format!");
		}
		
		return date;
	}
	
	/* Detailed display a location considering (or not) a period given by the user. */
	private int displayDetailedLocations(int count, Location l, boolean printPeriods,  Date firstDay, Date lastDay){
		
		if (!printPeriods){
			System.out.println(l.toString(true));
			count++;
		} else {
			for (Period p : l.getPeriods()){
				if (dateInRange(firstDay, lastDay, p.getFirstDay(), p.getLastDay())){
					System.out.println(l.toString(false) + "	Period: " + p.getNoDays() + " days: " +
				p.getStringFirstDay() + " - " + p.getStringLastDay() + "\n");
				}
			}
			count++;
		}
		
		return count;
	}
	
	/* Undetailed display a location considering (or not) a period given by the user and the sorting criteria. */
	private int displayUndetailedLocation(int count, String sortingCrt, int indexLocation, Location l, boolean printPeriods, Date firstDay, Date lastDay){
		
		if (sortingCrt.equals("activities")) {
			if (!printPeriods){
				System.out.println("(" + indexLocation + ") " + l.getName() + "	" +
						String.format("%.2f", l.getAvgPriceDay()) + " RON / day");
				count++;
			}
			else{
				for (Period p : l.getPeriods()){
					if (dateInRange(firstDay, lastDay, p.getFirstDay(), p.getLastDay())){
						System.out.println("(" + (count + 1) + ") " + l.getName() + ": " + p.getStringFirstDay() + " -> " +
								p.getStringLastDay() + " 	Total price: " + p.getTotalPrice() + "RON / day");
					}
				}
				
				count++;
			}
		} else if (sortingCrt.equals("hotel")) {
			if (!printPeriods){
				System.out.println("(" + indexLocation + ") " + l.getName() + "	" + 
						l.getHotelPriceDay() + " RON / day");
				count++;
			}
			else{
				for (Period p : l.getPeriods()){
					if (dateInRange(firstDay, lastDay, p.getFirstDay(), p.getLastDay())){
						System.out.println("(" + (count + 1) + ") " + l.getName() + ": " + p.getStringFirstDay() + " -> " +
								p.getStringLastDay() + " 	Total price: " + p.getTotalPrice() + "RON / day");
					}
				}
				
				count++;
			}
		} else if (sortingCrt.equals("totalday")) {
			if (!printPeriods){
				System.out.println("(" + indexLocation + ") " + l.getName() + "	" + 
						String.format("%.2f", (l.getAvgPriceDay() + l.getHotelPriceDay())) + " RON / day");
				count++;
			}
			else{
				for (Period p : l.getPeriods()){
					if (dateInRange(firstDay, lastDay, p.getFirstDay(), p.getLastDay())){
						System.out.println("(" + (count + 1) + ") " + l.getName() + ": " + p.getStringFirstDay() + " -> " +
								p.getStringLastDay() + " 	Total price: " + p.getTotalPrice() + "RON / day");
					}
				}
				count++;
			}
		} 
		
		return count;
	}

	
	/* Display top5 locations / locations in a city / region / country considering (or not) periods, details and sorting criteria given by the user. */
	private void displayTopFiveLocations(ArrayList<Location> locations, String xName,
			boolean displayDetails, String sortingCrt, int type, boolean printPeriods){
		
		/* type 1 = print top5 locations
		 * type 2 = print top5 locations from a city
		 * type 3 = print top5 locations from a region
		 * type 4 = print top5 locations from a country
		 */
		
		ArrayList<Location> locationsSorted =(ArrayList<Location>)locations.clone();
		Scanner sc = new Scanner(System.in);
		String seeAvailablePeriods = null;
		Date firstDay = null, lastDay = null;
		int count = 0; 		/* Used to display only the first 5 locations. */
		int indexLocation;
		
		if (printPeriods){
			firstDay = getPeriodDay(1);
			lastDay = getPeriodDay(2);
		}
		
		/* Sort ArrayList using the criteria given by the user. */
		if(sortingCrt.equals("activities")){
			Collections.sort(locationsSorted, Location.avgPriceDayComparator);
		} else if (sortingCrt.equals("hotel")){
			Collections.sort(locationsSorted, Location.hotelPriceDayComparator);
		} else if (sortingCrt.equals("totalday")) {
			Collections.sort(locationsSorted, Location.totalPriceDayComparator);
		} else if (sortingCrt.equals("exit")){
			System.out.println("Goodbye!");
			System.exit(0);
		}
		
		/* The user wants the info detailed. */
		if (displayDetails){
			if (type == 1){
				for (Location l : locationsSorted){
						if (count >= 5){
							System.out.println();
							break;
						}
						
						count = displayDetailedLocations(count, l, printPeriods,  firstDay, lastDay);
				}
				
				if (count == 0){
					System.out.println("Locations not found!");
				}
				
			} else if (type == 2){
				for (Location l : locationsSorted){
					if (l.getCity().toLowerCase().equals(xName)){
						
						if (count >= 5){
							System.out.println();
							break;
						}
						
						count = displayDetailedLocations(count, l, printPeriods,  firstDay, lastDay);
					}	
				}
				
				if (count == 0){
					System.out.println("Locations not found!");
				}
	
			} else if (type == 3){
				for (Location l : locationsSorted){
					if (l.getRegion().toLowerCase().equals(xName)){
						if (count >= 5){
							System.out.println();
							break;
						}
						
						count = displayDetailedLocations(count, l, printPeriods,  firstDay, lastDay);
					}	
				}
				
				if (count == 0){
					System.out.println("Locations not found!");
				}
	
			} else if (type == 4){
				for (Location l : locationsSorted){
					if (l.getCountry().toLowerCase().equals(xName)){
						if (count >= 5){
							System.out.println();
							break;
						}
						
						count = displayDetailedLocations(count, l, printPeriods,  firstDay, lastDay);
					}	
				}
				
				if (count == 0){
					System.out.println("Locations not found!");
				}		
			}
			
			System.out.println();
		
		/* The user wants brief info. */
		} else {
			if (type == 1){
				for (Location l : locationsSorted) {
					
					if (count >= 5){
						System.out.println();
						break;
					}
					
					 count = displayUndetailedLocation(count, sortingCrt, locationsSorted.indexOf(l), l, printPeriods, firstDay, lastDay);
					
				}
				
				if (count == 0){
					System.out.println("Locations not found!");
				}
					
			} else if (type == 2){
				for (Location l : locationsSorted){
					if (l.getCity().toLowerCase().equals(xName)){
						
						if (count >= 5){
							System.out.println();
							break;
						}
						
						count = displayUndetailedLocation(count, sortingCrt, locationsSorted.indexOf(l), l, printPeriods, firstDay, lastDay);
					}
				}

				if (count == 0){
					System.out.println("Locations not found!");
				}
				
			} else if (type == 3){
				for (Location l : locationsSorted){
					if (l.getRegion().toLowerCase().equals(xName)){
						
						if (count >= 5){
							System.out.println();
							break;
						}
						
						count = displayUndetailedLocation(count, sortingCrt, locationsSorted.indexOf(l), l, printPeriods, firstDay, lastDay);	
					}	
				}
				
				if (count == 0){
					System.out.println("Locations not found!");
				}
				
			} else if (type == 4){
				for (Location l : locationsSorted){
					if (l.getCountry().toLowerCase().equals(xName)) {
						
						if (count >= 5){
							System.out.println();
							break;
						}
						
						count = displayUndetailedLocation(count, sortingCrt, locationsSorted.indexOf(l), l, printPeriods, firstDay, lastDay);
					}
				}

				if (count == 0){
					System.out.println("Locations not found!");
				}
			}
			
			System.out.println();
		}
		
		/* If the user did not searched top5 locations in a specific period of time, 
		 * he/she can now see the available periods of a location displayed above.*/
		if (!printPeriods){
			System.out.println("Do you want to see available periods for a specific location? <yes/no>");
			seeAvailablePeriods = sc.next().toLowerCase();
			
			if (isYes(seeAvailablePeriods)){
				System.out.println("For which location do you want to see the available periods? Type the index of the location (listed above)!");
				
				String s = sc.next();
				if (s.equals("exit"))
					return;
				indexLocation = Integer.parseInt(s);
				
				displayPeriods(locationsSorted, indexLocation);
				
			} else if (seeAvailablePeriods.equals("exit")){
				sc.close();
				System.out.println("Goodbye!");
				System.exit(0);
			}
		}
		
		return;
	}
	
	/* Display periods available for a location. */
	private void displayPeriods(ArrayList<Location> locations, int indexLocation) {
		for (Period p : locations.get(indexLocation).getPeriods()){
			System.out.println(" -> " + p.toString());
		}
	}
	
	/* Main menu for top5 commands. */
	public void displayTopFive(ArrayList<Location> locations, String identifier, int type){
		Scanner sc = new Scanner(System.in);
		String sortingCrt, printDetails, printPeriods;
		int indexLocation;
		
		printSortText();
		
		sortingCrt = sc.next().toLowerCase();
		
		System.out.println("Do you want full details for every location? <yes/no>");
		
		printDetails = sc.next().toLowerCase();
		
		System.out.println("Do you want to search a specific period? <yes/no>");
		
		printPeriods = sc.next().toLowerCase();
		
		displayTopFiveLocations(locations, identifier.toLowerCase(), isYes(printDetails), sortingCrt, type, isYes(printPeriods));
		
		return;
	}
	
}
