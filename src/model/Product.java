package model;

import java.io.Serializable;

public class Product implements Serializable {
	public final static long serialVersionUID = 1;

	private String code;
	private String name;
	private String info;
	private double cost;
	private String restaurantNit;
	private int quantity;


	/**
	 * 
	 * @param code
	 * @param name
	 * @param info
	 * @param cost
	 */
	public Product(String name, String code, String info, double cost, String restNit) {
		this.code = code;
		this.name = name;
		this.info = info;
		this.cost = cost;
		this.restaurantNit = restNit;
	}
	public Product(String code, int quantity) {
		this.code = code;
		this.quantity = quantity;
	}

	public String getCode() {
		return code;
	}

	/**
	 * 
	 * @param code
	 */
	public void setCode(String code) {
		this.code = code;
	}

	public String getName() {
		return name;
	}

	/**
	 * 
	 * @param name
	 */
	public void setName(String name) {
		this.name = name;
	}

	public String getInfo() {
		return info;
	}

	/**
	 * 
	 * @param info
	 */
	public void setInfo(String info) {
		this.info = info;
	}

	public double getCost() {
		return cost;
	}

	/**
	 * 
	 * @param cost
	 */
	public void setCost(double cost) {
		this.cost = cost;
	}
	public String getRestaurantNit() {
		return restaurantNit;
	}
	public void setRestaurantNit(String restaurantNit) {
		this.restaurantNit = restaurantNit;
	}
	public int getQuantity() {
		return quantity;
	}
	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}
	public String getAllInfo() {
		String allInfo = "";
		allInfo += "\nName: "+name+"\nCode: "+code+"\nInformation: "+info+"\nCost: "+"$"+cost+"\nRestaurant NIT: "+restaurantNit+"\n";
		return allInfo;
	}
	public String toString() {
		String concat = "";
		concat += "\nName: "+name+"\nCode: "+code+"\nInformation: "+info+"\nCost: "+"$"+cost+"\nRestaurant NIT: "+restaurantNit+"\n";
		return concat;
	}

}