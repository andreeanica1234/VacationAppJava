import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Period {
	private Date firstDay;
	private Date lastDay;
	private int noDays;
	private float totalPrice;
	
	public Period(String firstDay, String lastDay, int noDays){

		SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
		
		try {
			this.firstDay = sdf.parse(firstDay);
			this.lastDay = sdf.parse(lastDay);
		} catch (ParseException e) {
			System.out.println("Wrong format for date in input file!");
		}
		
		this.noDays = noDays;
	}
	
	public Date getFirstDay(){
		return this.firstDay;
	}
	
	public Date getLastDay(){
		return this.lastDay;
	}
	
	/* Return first day of period in string format. */
	public String getStringFirstDay(){
		SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
		String s = sdf.format(this.firstDay);
		return s;
	}
	
	/* Return first day of period in string format. */
	public String getStringLastDay(){
		SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
		String s = sdf.format(this.lastDay);
		return s;
	}
	
	public int getNoDays(){
		return this.noDays;
	}
	
	public String getPeriod(){
		return this.getStringFirstDay() + " -> " + this.getStringLastDay();
	}
	
	/* Total price of stay at the hotel. */
	public void setTotalPrice(float dayPrice){
		this.totalPrice = (this.noDays * dayPrice);
	}
	
	public float getTotalPrice(){
		return this.totalPrice;
	}

	public String toString(){
		String s = new String();
		s += this.getNoDays() + " days: ";
		s += this.getStringFirstDay();
		s += " -> ";
		s += this.getStringLastDay();
		s += "		Total price: ";
		s += this.getTotalPrice() + " RON";
		return s;
	}
}
