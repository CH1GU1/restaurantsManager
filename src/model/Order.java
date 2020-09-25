package model;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Order implements Serializable {
	
	//Initialization and constants declaration
	
	public final static long serialVersionUID = 1;

	private String code;
	private String date;
	private String clientIdNum;
	private String restaurantNit;
	public enum status {REQUESTED, IN_PROCESS, SENT, DELIVERED}
	public status orderStat;
	Random random = new Random();
	private List<Product> orderList;

	/**
	 *  This method is the constructor of Order
	 * <b><pre>:<br><br>
	 * 
	 * @param code String of order code
	 * @param ClientIdNum String of client ID number
	 * @param restNit String of restaurant manager name
	 *
	 * <b>post:</b><br>
	 */
	public Order(String code, String ClientIdNum, String restNit) {
		this.code = code;
		this.date = getDate();
		this.clientIdNum = ClientIdNum;
		this.restaurantNit = restNit;
		this.orderStat = status.REQUESTED;
		orderList = new ArrayList<Product>() ;

	}
	/**
	 * This method gets the order status 
	 * <b><pre>:<br><br>
	 * 
	 * @return orderStat.name();
	 * 
	 * <b>post:</b><br>
	 */
	public String getOrderStat() {
		return orderStat.name();
	}
	/**
	 * This method sets the order status depend of previous status and go only forward
	 * <b><pre>:<br><br>
	 * 
	 * @param status String of the previous status
	 * 
	 * @return info 
	 * 
	 * <b>post:</b><br>
	 */
	@SuppressWarnings({ "static-access" })
	public String setOrderStatByCondition(String status) {
		String info = "";
		if(getOrderStat().equalsIgnoreCase("REQUESTED")) {
			setOrderStat(orderStat.IN_PROCESS);
			info += "Order status setted IN PROCESS";
		} else if (getOrderStat().equalsIgnoreCase("IN_PROCESS")) {
			setOrderStat(orderStat.SENT);
			info += "Order status setted SENT";
		} else if(getOrderStat().equalsIgnoreCase("SENT")){
			setOrderStat(orderStat.DELIVERED);
			info += "Order status setted DELIVERED";
		} else if(getOrderStat().equalsIgnoreCase("DELIVERED")) {
			info += "Order status is DELIVERED, Order closed!";
		}
		return info;
	}
	/**
	 * This method sets the order status
	 * <b><pre>:<br><br>
	 * 
	 * @param orderStat status type of enum class
	 * 
	 * <b>post:</b><br>
	 */
	public void setOrderStat(status orderStat) {
		this.orderStat = orderStat;
	}
	/**
	 * This method search a product in order products list and returns the position in array
	 * <b><pre>:<br><br>
	 * 
	 * @param code String of product code
	 * 
	 * @return position
	 * 
	 * <b>post:</b><br>
	 */
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
	/**
	 * This method gets date of order
	 * <b><pre>:<br><br>
	 * 
	 * @return date
	 * 
	 * <b>post:</b><br>
	 */
	public String getDate() {
		String date = "";
		date += ""+(LocalDate.now().getDayOfMonth())+"/"+LocalDate.now().getMonthValue()+"/"+LocalDate.now().getYear();
		return date;
	}
	/**
	 * This method gets the restaurant nit linked to the order
	 * <b><pre>:<br><br>
	 * 
	 * @return restaurantNit
	 * 
	 * <b>post:</b><br>
	 */
	public String getRestaurantNit() {
		return restaurantNit;
	}
	/**
	 * This method gets the client ID number linked to the order
	 * <b><pre>:<br><br>
	 * 
	 * @return clientIdNum
	 * 
	 * <b>post:</b><br>
	 */
	public String getClientIdNum() {
		return clientIdNum;
	}
	/**
	 * This method sets client ID number 
	 * <b><pre>:<br><br>
	 * 
	 * @param clientIdNum 
	 * 
	 * <b>post:</b><br>
	 */
	public void setClientIdNum(String clientIdNum) {
		this.clientIdNum = clientIdNum;
	}
	/**
	 * This method sets nit of restaurant
	 * <b><pre>:<br><br>
	 * 
	 * @param restaurantNit
	 * 
	 * <b>post:</b><br>
	 */
	public void setRestaurantNit(String restaurantNit) {
		this.restaurantNit = restaurantNit;
	}
	/**
	 * This method update the code of product code in the array of products
	 * <b><pre>:<br>An order must be added as minimum<br>
	 * 
	 * @param oldCode
	 * @param newCode
	 * 
	 * <b>post:</b>Replace the old code and sets the new one<br>
	 */
	public void updateProductsCode(String oldCode, String newCode) {
		for(int i = 0; i < orderList.size(); i++) {
			orderList.get(i).getCode().equalsIgnoreCase(oldCode);
			orderList.get(i).setCode(newCode);
		}
	}
	/**
	 * This method gets the order code
	 * <b><pre>:<br><br>
	 * 
	 * @return code
	 * 
	 * <b>post:</b><br>
	 */
	public String getCode() {
		return code;
	}
	/**
	 * This method gets the products list linked with his own order
	 * <b><pre>:<br><br>
	 * 
	 * @return orderList
	 * 
	 * <b>post:</b><br>
	 */
	public List<Product> getOrdersProductList(){
		return orderList;
	}
	/**
	 * This method add a product into the list of the order
	 * <b><pre>:<br><br>
	 * 
	 * @param code String of product code
	 * @param quantity int of product quantity
	 * 
	 * @return info
	 * 
	 * <b>post:</b><br>
	 */
	public String addProductInOrderList(String code, int quantity) {
		String info = "";
		Product p = new Product(code, quantity);
		orderList.add(p);
		info += "Added!";
		return info;
	}
	/**
	 * This method returns the information of the order list
	 * <b><pre>:<br>An order must be created as minimum<br>
	 * 
	 * @return info String with the information of order list
	 * 
	 * <b>post:</b><br>
	 */
	public String getOrdersList(){
		String info = "";
		for (int i = 0; i < orderList.size(); i++) {
			info += "\nProduct code: "+orderList.get(i).getCode()+"\nQuantity: "+orderList.get(i).getQuantity();
		}
		return info;
	}
	/**
	 * This method returns the information of the order for override the method toString
	 * <b><pre>:<br>An order must be created as minimum<br>
	 * 
	 * @return concat String with the information of order
	 * 
	 * <b>post:</b><br>
	 */
	public String toString() {
		String concat = "";
		concat += "Code: "+code+"\nDate: "+date+"\nClient ID: "+clientIdNum+"\nRestaurant NIT: "+restaurantNit+"\nStatus: "+orderStat+getOrdersList();
		return concat;
	}
	/**
	 * This method returns the complete information of the order
	 * <b><pre>:<br>An order must be created as minimum<br>
	 * 
	 * @return info String with the complete information of order
	 * 
	 * <b>post:</b><br>
	 */
	public String getInfo() {
		String info = "";
		info += "\nCode: "+code+"\nDate: "+date+"\nClient ID: "+clientIdNum+"\nRestaurant NIT: "+restaurantNit+"\nStatus: "+orderStat+getOrdersList()+"\n";
		return info;
	}
}