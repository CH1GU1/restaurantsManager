package model;
import java.io.Serializable;

public class Client implements Serializable, Comparable<Client>{
	
	//Initialization and constants declaration
	
	private static final long serialVersionUID = 1;
	private String idType;
	private String idNum;
	private String name;
	private String lastName;
	private String telephone;
	private String address;


	/**
	 *  This method is the constructor of Client
	 * <b><pre>:<br><br>
	 * 
	 * @param idType String of type of document
	 * @param idNum String of ID number
	 * @param name String of first name
	 * @param lastName String of last name
	 * @param telephone String of telephone
	 * @param address String of address
	 * 
	 * <b>post:</b><br>
	 */
	public Client(String name, String lastName, String idNum, String idType, String telephone, String address) {
		this.idType = idType;
		this.idNum = idNum;
		this.name = name;
		this.lastName = lastName;
		this.telephone = telephone;
		this.address = address;

	}
	/**
	 * This method gets the last name of client
	 * <b><pre>:<br><br>
	 * 
	 * @return lastName
	 * 
	 * <b>post:</b><br>
	 */
	public String getLastName() {
		return lastName;
	}
	/**
	 * This method sets the last name of client
	 * <b><pre>:<br><br>
	 * 
	 * @param lastName
	 * 
	 * <b>post:</b><br>
	 */
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	/**
	 * This method gets the idType of client
	 * <b><pre>:<br><br>
	 * 
	 * @return idType
	 * 
	 * <b>post:</b><br>
	 */
	public String getIdType() {
		return idType;
	}
	/**
	 * This method sets id type of client
	 * <b><pre>:<br><br>
	 * 
	 * @param idType
	 * 
	 * <b>post:</b><br>
	 */
	public void setIdType(String idType) {
		this.idType = idType;
	}
	/**
	 * This method gets the ID number of client
	 * <b><pre>:<br><br>
	 * 
	 * @return idNum
	 * 
	 * <b>post:</b><br>
	 */
	public String getIdNum() {
		return idNum;
	}
	/**
	 * This method sets the ID num of client
	 * <b><pre>:<br><br>
	 * 
	 * @param idNum
	 * 
	 * <b>post:</b><br>
	 */
	public void setIdNum(String idNum) {
		this.idNum = idNum;
	}
	/**
	 * This method gets the name of client
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
	 * This method sets the first name of client
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
	 * This method gets the telephone of client
	 * <b><pre>:<br><br>
	 * 
	 * @return telephone
	 * 
	 * <b>post:</b><br>
	 */
	public String getTelephone() {
		return telephone;
	}
	/**
	 * This method sets the telephone of client
	 * <b><pre>:<br><br>
	 * 
	 * @param telephone
	 * 
	 * <b>post:</b><br>
	 */
	public void setTelephone(String telephone) {
		this.telephone = telephone;
	}
	/**
	 * This method gets the address of client
	 * <b><pre>:<br><br>
	 * 
	 * @return address
	 * 
	 * <b>post:</b><br>
	 */
	public String getAddress() {
		return address;
	}
	/**
	 * This method sets the address of client
	 * <b><pre>:<br><br>
	 * 
	 * @param address
	 * 
	 * <b>post:</b><br>
	 */
	public void setAddress(String address) {
		this.address = address;
	}
	/**
	 * This method gets the name of client
	 * <b><pre>:<br>Name and last name must be already assigned<br>
	 * 
	 * @return fullName
	 * 
	 * <b>post:</b>Link the name and last name<br>
	 */
	public String getFullName() {
		String fullName = "";
		fullName = name+" "+lastName;
		return fullName;
	}
	/**
	 * This method returns the information of the client
	 * <b><pre>:<br>A client must be created as minimum<br>
	 * 
	 * @return info String with the information of client
	 * 
	 * <b>post:</b><br>
	 */
	public String getInfo() {
		String info = "";
		info += "Name: "+getName()+"\nLast name: "+lastName+"\nID number: "+getIdNum()+"\nID type:"+getIdType()+"\nTelephone: "+getTelephone()+"\nAdress: "+getAddress()+"\n";
		return info;
	}
	/**
	 * This method returns the information of the client for override the method toString
	 * <b><pre>:<br>A client must be created as minimum<br>
	 * 
	 * @return concat String with the information of client
	 * 
	 * <b>post:</b><br>
	 */
	@Override
	public String toString() {
		String concat = "";
		concat += "\n"+"Name: "+getName()+"\nLast name: "+lastName+"\nID number: "+getIdNum()+"\nID type:"+getIdType()+"\nTelephone: "+getTelephone()+"\nAdress: "+getAddress()+"\n";
		return concat;
	}
	/**
	 * This method returns the operation of compare to clients telephone
	 * <b><pre>:<br>Two clients must be created as minimum<br>
	 * 
	 * @return x-y integer of the operation
	 * 
	 * <b>post:</b><br>
	 */
	@Override
	public int compareTo(Client clt) {
		int x = Integer.parseInt(telephone);
		int y = (Integer.parseInt(clt.getTelephone()));
		return x-y;
	}
}