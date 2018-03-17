import java.util.ArrayList;

public class City {
	private String name;
	private ArrayList<Integer> locationIndex = new ArrayList<Integer>();
	
	public City(String name){
		this.name = name;
	}
	
	public String getName(){
		return this.name;
	}
	
	public void addLocation(Integer location){
		locationIndex.add(location);
	}
	
	
	public ArrayList<Integer> getLocations(){
		return this.locationIndex;
	}
}
