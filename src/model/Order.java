package model;

import java.io.Serializable;
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


	public Order(String code, String ClientIdNum, String restNit) {
//		this.code = new BigInteger(50, random).toString(32);
		this.code = code;
		this.date = getDate();
		this.clientIdNum = ClientIdNum;
		this.restaurantNit = restNit;
		this.orderStat = status.REQUESTED;
		orderList = new ArrayList<Product>() ;
	
	}
	public status getOrderStat() {
		return orderStat;
	}
	public void setOrderStat(status orderStat) {
		this.orderStat = orderStat;
	}
	public int searchProductInList(String code) {
		int position = 0;
		boolean found = !false;
		for(int i=0; i<orderList.size() && found; i++){
			if(orderList.get(i).getCode().equalsIgnoreCase(code)){
				found = true;
				position = i;
			}
		}
		return position;
	}
	public String getProductsListEspecific(int pos) {
		String info = "";
			info += "\nProduct code: "+orderList.get(pos).getCode()+"\nQuantity: "+orderList.get(pos).getQuantity();
		return info;
	}
	public String getProductCodeEspecific(int pos) {
		String info = "";
			info += orderList.get(pos).getCode();
		return info;
	}
	public String getDate() {
		String date = "";
		date += ""+(LocalDate.now().getDayOfMonth())+"/"+LocalDate.now().getMonthValue()+"/"+LocalDate.now().getYear();
		return date;
	}
	public String getRestaurantNit() {
		return restaurantNit;
	}
	public String getClientIdNum() {
		return clientIdNum;
	}
	public void setClientIdNum(String clientIdNum) {
		this.clientIdNum = clientIdNum;
	}
	public void setRestaurantNit(String restaurantNit) {
		this.restaurantNit = restaurantNit;
	}
	public void setIdNum(String code) {
		this.code = code;
	}
	public void updateProductsCode(String oldCode, String newCode) {
		for(int i = 0; i < orderList.size(); i++) {
			orderList.get(i).getCode().equalsIgnoreCase(oldCode);
			orderList.get(i).setCode(newCode);
		}
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
		concat += "Code: "+code+"\nDate: "+date+"\nClient ID: "+clientIdNum+"\nRestaurant NIT: "+restaurantNit+"\nStatus: "+orderStat+getOrdersList();

		return concat;
	}
	public String getInfo() {
		String info = "";
		info += "\nCode: "+code+"\nDate: "+date+"\nClient ID: "+clientIdNum+"\nRestaurant NIT: "+restaurantNit+"\nStatus: "+orderStat+getOrdersList()+"\n";
		return info;
	}
}