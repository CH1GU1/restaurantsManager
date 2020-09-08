package model;
import java.time.LocalDate;

public class Order {

	private String code;
	private String date;
	private String clientIdNum;
	private String restaurantNit;

	/**
	 * 
	 * @param code
	 * @param date
	 */
	public Order(String code, String date) {
		this.code = code;
		this.date = ""+(LocalDate.now().getDayOfMonth())+LocalDate.now().getMonthValue()+LocalDate.now().getYear();
	}
	public void setIdNum(String code) {
		this.code = code;
	}

	public String getCode() {
		return code;
	}
	public String getDate() {
		return date;
	}
	public String getInfo() {
		// TODO Auto-generated method stub
		return null;
	}

}