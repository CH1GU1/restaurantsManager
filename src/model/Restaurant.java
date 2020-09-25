package model;
import java.io.Serializable;

public class Restaurant implements Serializable,  Comparable<Restaurant> {
	public final static long serialVersionUID = 1;

	private String name;
	private String nit;
	private String manager;

	/**
	 * 
	 * @param name
	 * @param nit
	 * @param manager
	 */
	public Restaurant(String name, String nit, String manager) {
		this.name = name;
		this.nit = nit;
		this.manager = manager;
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

	public String getNit() {
		return nit;
	}

	/**
	 * 
	 * @param nit
	 */
	public void setNit(String nit) {
		this.nit = nit;
	}

	public String getManager() {
		return manager;
	}

	/**
	 * 
	 * @param manager
	 */
	public void setManager(String manager) {
		this.manager = manager;
	}

	public String getInfo() {
		String info = "";
		info += "Name: "+name+"\nNIT: "+nit+"\nManager is: "+manager;
		return info;
	}
	@Override
	public String toString() {
		String concat = "";
		concat += "\nName: "+name+"\nNIT: "+nit+"\nManager is: "+manager+"\n";
		return concat;
	}


	@Override
	public int compareTo(Restaurant rest) {
		int S = nit.compareToIgnoreCase(rest.getNit());
		return S;
	}
}		