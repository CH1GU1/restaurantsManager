package model;

import java.io.Serializable;
import java.math.BigInteger;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Order implements Serializable {
	public final static long serialVersionUID = 1;

	private String code;
	private String date;
	private String clientIdNum;
	private String restaurantNit;
	public enum status {REQUESTED, IN_PROCESS, SENT, DELIVERED}
	public status orderStat;
	Random random = new Random();


	private List<Product> orderList;


	public Order(String ClientIdNum, String restNit) {
		this.code = new BigInteger(50, random).toString(32);
		this.date = getDate();
		this.clientIdNum = ClientIdNum;
		this.restaurantNit = restNit;
		orderList = new ArrayList<Product>() ;
	}
	public String getDate() {
		String date = "";
		date += ""+(LocalDate.now().getDayOfMonth())+"/"+LocalDate.now().getMonthValue()+"/"+LocalDate.now().getYear();
		return date;
	}

	public void setIdNum(String code) {
		this.code = code;
	}

	public String getCode() {
		return code;
	}
	public List<Product> getOrdersProductList(){
		return orderList;
	}
	public String addProductInOrderList(String code, int quantity) {
		String info = "";
		Product p = new Product(code, quantity);
		orderList.add(p);
		info += "Added!";
		return info;
	}
	public String getOrdersList(){
		String info = "";
		for (int i = 0; i < orderList.size(); i++) {
			info += "\nProduct code: "+orderList.get(i).getCode()+"\nQuantity: "+orderList.get(i).getQuantity();
		}
		return info;
	}
	public String toString() {
		String concat = "";
		concat += "Code: "+code+"\nDate: "+date+"\nClient ID: "+clientIdNum+"\nRestaurant NIT: "+restaurantNit+getOrdersList();

		return concat;
	}

	public String getInfo() {
		String info = "";
		info += "\nCode: "+code+"\nDate: "+date+"\nClient ID: "+clientIdNum+"\nRestaurant NIT: "+restaurantNit+getOrdersList()+"\n";
		return info;
	}
}