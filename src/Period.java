
public class Period {
	private String firstDay;
	private String lastDay;
	private int noDays;
	
	public Period(String firstDay, String lastDay, int noDays){
		this.firstDay = firstDay;
		this.lastDay = lastDay;
		this.noDays = noDays;
	}
	
	public String getFirstDay(){
		return this.firstDay;
	}
	
	public String getLastDay(){
		return this.lastDay;
	}
	
	public int getNoDays(){
		return this.noDays;
	}
	
	public String getPeriod(){
		return this.firstDay + " - " + this.lastDay;
	}
}
