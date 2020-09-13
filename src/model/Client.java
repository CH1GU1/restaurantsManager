package model;

public class Client {

	private String idType;
	private String idNum;
	private String name;
	private String lastName;
	private String telephone;
	private String adress;


	/**
	 * 
	 * @param idType
	 * @param idNum
	 * @param name
	 * @param telephone
	 * @param adress
	 */
	public Client(String name, String lastName, String idNum, String idType, String telephone, String adress) {
		this.idType = idType;
		this.idNum = idNum;
		this.name = name;
		this.lastName = lastName;
		this.telephone = telephone;
		this.adress = adress;

	}
	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getIdType() {
		return idType;
	}

	/**
	 * 
	 * @param idType
	 */
	public void setIdType(String idType) {
		this.idType = idType;
	}

	public String getIdNum() {
		return idNum;
	}

	/**
	 * 
	 * @param idNum
	 */
	public void setIdNum(String idNum) {
		this.idNum = idNum;
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

	public String getTelephone() {
		return telephone;
	}

	/**
	 * 
	 * @param telephone
	 */
	public void setTelephone(String telephone) {
		this.telephone = telephone;
	}

	public String getAdress() {
		return adress;
	}

	/**
	 * 
	 * @param adress
	 */
	public void setAdress(String adress) {
		this.adress = adress;
	}
	public String getFullName() {
		String fullName = "";
		fullName = name+" "+lastName;
		return fullName;
	}

	public String getInfo() {
		String info = "";
		info += "Name: "+getName()+"\nLast name: "+lastName+"\nID number: "+getIdNum()+"\nID type:"+getIdType()+"\nTelephone: "+getTelephone()+"\nAdress: "+getAdress()+"\n";
		return info;
	}
}