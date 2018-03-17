import java.util.ArrayList;

public class Region {
	private String name;
	private ArrayList<City> cities = new ArrayList<City>();
	
	public Region(String name){
		this.name = name;
	}
	
	public String getName(){
		return this.name;
	}
	
	public boolean containsCity(String city){
		for (City c : cities){
			if (c.getName().equals(city))
				return true;
		}
		return false;
	}
	
	public ArrayList<City> getCities(){
		return this.cities;
	}
	
	public void addCity(City city, Integer location){
		for (City c : cities){
			if (c.getName().equals(city.getName())){
				c.addLocation(location);
				return;
			}
		}
		city.addLocation(location);
		cities.add(city);
	}
	
}
