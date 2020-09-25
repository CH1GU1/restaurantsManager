package model;

import java.io.Serializable;

public class Product implements Serializable {
	
	//Initialization and constants declaration
	
	public final static long serialVersionUID = 1;

	private String code;
	private String name;
	private String info;
	private double cost;
	private String restaurantNit;
	private int quantity;

	/**
	 *  This method is the constructor of Product
	 * <b><pre>:<br><br>
	 * 
	 * @param name String of product name
	 * @param code String of product code
	 * @param info String of product information
	 * @param cost double of product cost
	 * @param restNit String of restaurant NIT
	 * <b>post:</b><br>
	 */
	public Product(String name, String code, String info, double cost, String restNit) {
		this.code = code;
		this.name = name;
		this.info = info;
		this.cost = cost;
		this.restaurantNit = restNit;
	}
	/**
	 *  This method is the constructor overloaded of Product to use in orders
	 * <b><pre>:<br><br>
	 * 
	 * @param code String of product code
	 * @param quantity integer of product quantity
	 * <b>post:</b><br>
	 */
	public Product(String code, int quantity) {
		this.code = code;
		this.quantity = quantity;
	}
	/**
	 * This method gets the code of product
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
	 * This method sets code of product
	 * <b><pre>:<br><br>
	 * 
	 * @param code
	 * 
	 * <b>post:</b><br>
	 */
	public void setCode(String code) {
		this.code = code;
	}
	/**
	 * This method gets the name of product
	 * <b><pre>:<br><br>
	 * 
	 * @return name
	 * 
	 * <b>post:</b><br>
	 */
	public String getName() {
		return name;
	}
	/**
	 * This method sets name of product
	 * <b><pre>:<br><br>
	 * 
	 * @param name
	 * 
	 * <b>post:</b><br>
	 */
	public void setName(String name) {
		this.name = name;
	}
	/**
	 * This method gets the information of product
	 * <b><pre>:<br><br>
	 * 
	 * @return info
	 * 
	 * <b>post:</b><br>
	 */
	public String getInfo() {
		return info;
	}
	/**
	 * This method sets information of product
	 * <b><pre>:<br><br>
	 * 
	 * @param info
	 * 
	 * <b>post:</b><br>
	 */
	public void setInfo(String info) {
		this.info = info;
	}
	/**
	 * This method gets the cost of product
	 * <b><pre>:<br><br>
	 * 
	 * @return cost
	 * 
	 * <b>post:</b><br>
	 */
	public double getCost() {
		return cost;
	}
	/**
	 * This method sets cost of product
	 * <b><pre>:<br><br>
	 * 
	 * @param cost
	 * 
	 * <b>post:</b><br>
	 */
	public void setCost(double cost) {
		this.cost = cost;
	}
	/**
	 * This method gets the restaurant nit registered of the product
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
	 * This method sets the restaurant NIT linked of product
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
	 * This method gets the quantity of product
	 * <b><pre>:<br><br>
	 * 
	 * @return quantity
	 * 
	 * <b>post:</b><br>
	 */
	public int getQuantity() {
		return quantity;
	}
	/**
	 * This method sets quantity of product
	 * <b><pre>:<br><br>
	 * 
	 * @param quantity
	 * 
	 * <b>post:</b><br>
	 */
	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}
	/**
	 * This method returns the information of the product
	 * <b><pre>:<br>A product must be created as minimum<br>
	 * 
	 * @return allInfo String with the information of product
	 * 
	 * <b>post:</b><br>
	 */
	public String getAllInfo() {
		String allInfo = "";
		allInfo += "\nName: "+name+"\nCode: "+code+"\nInformation: "+info+"\nCost: "+"$"+cost+"\nRestaurant NIT: "+restaurantNit+"\n";
		return allInfo;
	}
	/**
	 * This method returns the information of the product for override the method toString
	 * <b><pre>:<br>A product must be created as minimum<br>
	 * 
	 * @return concat String with the information of product
	 * 
	 * <b>post:</b><br>
	 */
	public String toString() {
		String concat = "";
		concat += "\nName: "+name+"\nCode: "+code+"\nInformation: "+info+"\nCost: "+"$"+cost+"\nRestaurant NIT: "+restaurantNit+"\n";
		return concat;
	}
}