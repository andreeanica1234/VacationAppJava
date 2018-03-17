import java.util.ArrayList;

public class Country {
	private String name;
	private ArrayList<Region> regions = new ArrayList<Region>();
	
	public Country(String name){
		this.name = name;
	}
	
	public String getName(){
		return this.name;
	}
	
	public ArrayList<Region> getRegions(){
		return this.regions;
	}
	
	public void addRegion(Region region, City city, Integer location){
		for(Region r : regions){
			if (r.getName().equals(region.getName())){
				r.addCity(city, location);
				return;
			}
		}
		regions.add(region);
		this.addRegion(region, city, location);
	}
	
}
