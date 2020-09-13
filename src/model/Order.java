package model;
import java.io.Serializable;
import java.math.BigInteger;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Random;

public class Order implements Serializable {
	public final static long serialVersionUID = 1;

	private String code;
	private String date;
	private String clientIdNum;
	private String restaurantNit;
	public enum status {REQUESTED, IN_PROCESS, SENT, DELIVERED}
	public status orderStat;
	public ArrayList<OrderList> orderList;
	Random random = new Random();
	/**
	 * 
	 * @param code
	 * @param date
	 */
	public Order(status orderStat, String ClientIdNum, String restNit, String productCode, int quantity) {
		this.code = new BigInteger(50, random).toString(32);
		this.date = getDate();
		this.orderStat = orderStat; 
		this.clientIdNum = ClientIdNum;
		this.restaurantNit = restNit;
		orderList = new ArrayList<>();
		OrderList ordList = new OrderList(productCode,quantity);
		orderList.add(ordList);

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
		String info = "";
		info += "Code: "+code+"\nDate: "+date+"\nClient ID: "+clientIdNum+"\nRestaurant NIT: "+restaurantNit;
		return info;
	}
}