package model;
import java.time.LocalDate;

public class Order {

	private String code;
	private String date;
	private String clientIdNum;
	private String restaurantNit;
	public enum status {REQUESTED, IN_PROCESS, SENT, DELIVERED}
	public status orderStat;
	
	/**
	 * 
	 * @param code
	 * @param date
	 */
	public Order(String code, String date, status orderStat, String ClntIdNum, String restNit) {
		this.code = code;
		this.date = getDate();
		this.orderStat = status.REQUESTED; 
		this.clientIdNum = ClntIdNum;
		this.restaurantNit = restNit;
		
	}
	public String getDate() {
		String date = "";
		date = ""+(LocalDate.now().getDayOfMonth())+LocalDate.now().getMonthValue()+LocalDate.now().getYear();
		return date;
	}
	
	public void setIdNum(String code) {
		this.code = code;
	}

	public String getCode() {
		return code;
	}
	public String getInfo() {
		// TODO Auto-generated method stub
		return null;
	}
}