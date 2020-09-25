package model;
import java.io.Serializable;

public class Restaurant implements Serializable,  Comparable<Restaurant> {

	//Initialization and constants declaration

	public final static long serialVersionUID = 1;

	private String name;
	private String nit;
	private String manager;

	/**
	 *  This method is the constructor of Restaurant
	 * <b><pre>:<br><br>
	 * 
	 * @param name String of restaurant name
	 * @param nit String of restaurant nit
	 * @param manager String of restaurant manager name
	 *
	 * <b>post:</b><br>
	 */
	public Restaurant(String name, String nit, String manager) {
		this.name = name;
		this.nit = nit;
		this.manager = manager;
	}
	/**
	 * This method gets the name of restaurant
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
	 * This method sets name of restaurant
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
	 * This method gets the nit of restaurant
	 * <b><pre>:<br><br>
	 * 
	 * @return nit
	 * 
	 * <b>post:</b><br>
	 */
	public String getNit() {
		return nit;
	}
	/**
	 * This method sets nit of restaurant
	 * <b><pre>:<br><br>
	 * 
	 * @param nit
	 * 
	 * <b>post:</b><br>
	 */
	public void setNit(String nit) {
		this.nit = nit;
	}
	/**
	 * This method gets the name of restaurant manager
	 * <b><pre>:<br><br>
	 * 
	 * @return manager
	 * 
	 * <b>post:</b><br>
	 */
	public String getManager() {
		return manager;
	}
	/**
	 * This method sets name of restaurant manager
	 * <b><pre>:<br><br>
	 * 
	 * @param manager
	 * 
	 * <b>post:</b><br>
	 */
	public void setManager(String manager) {
		this.manager = manager;
	}
	/**
	 * This method returns the information of the restaurant
	 * <b><pre>:<br>A restaurant must be created as minimum<br>
	 * 
	 * @return info String with the information of restaurant
	 * 
	 * <b>post:</b><br>
	 */
	public String getInfo() {
		String info = "";
		info += "Name: "+name+"\nNIT: "+nit+"\nManager is: "+manager;
		return info;
	}
	/**
	 * This method returns the information of the restaurant for override the method toString
	 * <b><pre>:<br>A product must be created as minimum<br>
	 * 
	 * @return concat String with the information of restaurant
	 * 
	 * <b>post:</b><br>
	 */
	@Override
	public String toString() {
		String concat = "";
		concat += "\nName: "+name+"\nNIT: "+nit+"\nManager is: "+manager+"\n";
		return concat;
	}
	/**
	 * This method returns the operation of compare to restaurants nit
	 * <b><pre>:<br>Two restaurants must be created as minimum<br>
	 * 
	 * @return S integer of the operation
	 * 
	 * <b>post:</b><br>
	 */
	@Override
	public int compareTo(Restaurant rest) {
		int S = nit.compareToIgnoreCase(rest.getNit());
		return S;
	}
}		