import java.util.ArrayList;

public class CheapActivities {

	public void findCheapestActivity(ArrayList<Location> locations) {
		
		/* Retain the cheapest activity and its location.
		 * If there are more activities with the minimum price available, 
		 * all are kept and displayed*/
		
		ArrayList<String> cheapestActivityLocation = new ArrayList<String>();
		ArrayList<Activity> activities = new ArrayList<Activity>();
		int minPrice = Integer.MAX_VALUE;
		
		for (Location l : locations){
			for (Activity a : l.getActivities()){
				if (a.getPrice() < minPrice){
					/* If found a cheaper activity, all previous info is ditched. */
					cheapestActivityLocation = new ArrayList<String>();
					activities = new ArrayList<Activity>();
					String s = new String();
					
					minPrice = a.getPrice();
					
					s += "		" + l.getName() + ", " + l.getRegion() +  ", " + l.getCountry() + "\n";
					
					cheapestActivityLocation.add(s);
					activities.add(a);					
				} else if (a.getPrice() == minPrice){
					/* If a new activity with minPrice it's found, it's added to the retained info. */
					String s = new String();
					
					s += "	" + l.getName() + ", " + l.getRegion() +  ", " + l.getCountry() + "\n";
					
					cheapestActivityLocation.add(s);
					activities.add(a);
				}
			}
		}
		
		displayCheapestActivity(cheapestActivityLocation, activities);
	}
	
	/* Display the cheapest activity/activities and its location(s). */
	private void displayCheapestActivity(ArrayList<String> cheapestActivityLocation, ArrayList<Activity> activities){
		
		System.out.println("The cheapest activity(es): ");
		for (int i = 0; i < activities.size(); i++){
			String print = new String();
			
			print += activities.get(i).getName() + " " + activities.get(i).getPrice() + " RON" + "	->";
			
			System.out.println(print  + cheapestActivityLocation.get(i));
		}
	}

}
