package model;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import exceptions.NullCodeException;
import exceptions.WrongIdException;
import exceptions.WrongNitException;
import java.lang.Comparable;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.PrintWriter;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.ObjectInputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.BufferedReader;
import java.io.File;


public class RestaurantsManager implements Comparable<Client> {
	
	//Initialization and constants declaration
	
	public final static String SAVE_PATH_FILE_RESTAURANTS = "data/restaurants.ap2";
	public final static String SAVE_PATH_FILE_CLIENTS = "data/clients.ap2";
	public final static String SAVE_PATH_FILE_PRODUCTS = "data/products.ap2";
	public final static String SAVE_PATH_FILE_ORDERS = "data/orders.ap2";

	public List<Restaurant> restaurants;
	public List<Product> products;
	public List<Client> clients;
	public List<Order> orders;

	private final static String SEPARATOR = ",";

	
	/**
	 *  This method is the constructor of RestaurantsManager
	 * <b><pre>:<br><br>
	 * 
	 * <b>post:</b>ArrayList are created<br>
	 */
	public RestaurantsManager() {
		restaurants = new ArrayList<Restaurant>();
		products = new ArrayList<Product>();
		clients = new ArrayList<Client>();
		orders = new ArrayList<Order>();
	}
	/**
	 * This method gets the restaurants list
	 * <b><pre>:<br><br>
	 * 
	 * @return restaurants
	 * 
	 * <b>post:</b><br>
	 */
	public List<Restaurant> getRestaurants(){
		return restaurants;
	}
	/**
	 * This method gets the products list
	 * <b><pre>:<br><br>
	 * 
	 * @return products
	 * 
	 * <b>post:</b><br>
	 */
	public List<Product> getProducts(){
		return products;
	}
	/**
	 * This method gets the clients list
	 * <b><pre>:<br><br>
	 * 
	 * @return clients
	 * 
	 * <b>post:</b><br>
	 */
	public List<Client> getClients(){
		return clients;
	}
	/**
	 * This method gets the orders list
	 * <b><pre>:<br><br>
	 * 
	 * @return orders
	 * 
	 * <b>post:</b><br>
	 */
	public List<Order> getOrders(){
		return orders;
	}


//	public String toString(){
//		String msg = "Restaurants List:\n";
//		for(Restaurant thisRestaurant:restaurants){
//			msg += thisRestaurant.getName()+SEPARATOR+thisRestaurant.getNit()+SEPARATOR+thisRestaurant.getManager()+"\n";
//		}
//		return msg;
//	}
	
	/**
	 * This method serialize the program data
	 * <b><pre>:<br><br>
	 * 
	 * @param type String of the object type to serialize
	 * @throws IOException
	 * 
	 * <b>post:</b>Serialize the data requested for each path file<br>
	 */
	public void saveData(String type) throws IOException{
		if(type.equalsIgnoreCase("rest")) {
			ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(SAVE_PATH_FILE_RESTAURANTS));
			oos.writeObject(restaurants);
			oos.close();
		} 
		if(type.equalsIgnoreCase("client")) {
			ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(SAVE_PATH_FILE_CLIENTS));
			oos.writeObject(clients);
			oos.close();
		}
		if(type.equalsIgnoreCase("products")) {
			ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(SAVE_PATH_FILE_PRODUCTS));
			oos.writeObject(products);
			oos.close();
		}	
		if(type.equalsIgnoreCase("orders")) {
			ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(SAVE_PATH_FILE_ORDERS));
			oos.writeObject(orders);
			oos.close();
		}
	}
	/**
	 * This method deserialize the program data
	 * <b><pre>:<br>Serialized files must be created to be deserialize<br>
	 * 
	 * @param type String of the object type to deserialize
	 * 
	 * @return loaded boolean that notify if was added or not
	 * 
	 * @throws IOException
	 * @throws ClassNotFoundException
	 * 
	 * <b>post:</b>Deserialize the data requested for each path file<br>
	 */
	@SuppressWarnings("unchecked")
	public boolean loadData(String type) throws IOException, ClassNotFoundException{
		File r = new File(SAVE_PATH_FILE_RESTAURANTS);
		File c = new File(SAVE_PATH_FILE_CLIENTS);
		File p = new File(SAVE_PATH_FILE_PRODUCTS);
		File o = new File(SAVE_PATH_FILE_ORDERS);
		boolean loaded = false;
		if(r.exists()){
			ObjectInputStream ois = new ObjectInputStream(new FileInputStream(r));
			if(type.equalsIgnoreCase("rest")) {
				restaurants = (List<Restaurant>)ois.readObject();
				loaded = true;
			}
			ois.close();	
		}
		if(c.exists()){
			ObjectInputStream ois = new ObjectInputStream(new FileInputStream(c));
			if(type.equalsIgnoreCase("client")) {
				clients = (List<Client>)ois.readObject();
				loaded = true;
			}
			ois.close();		
		}
		if(p.exists()){
			ObjectInputStream ois = new ObjectInputStream(new FileInputStream(p));
			if(type.equalsIgnoreCase("products")) {
				products = (List<Product>)ois.readObject();
				loaded = true;
			}
			ois.close();
		}
		if(o.exists()){
			ObjectInputStream ois = new ObjectInputStream(new FileInputStream(o));
			if(type.equalsIgnoreCase("orders")) {
				orders = (List<Order>)ois.readUnshared();
				loaded = true;
			}
			ois.close();	
		}
		else {
			loaded = false;
		}
		return loaded;
	}
	
	// Exporting Data...
	
	/**
	 * This method export the orders information
	 * <b><pre>:<br>An order as minimum must be added<br>
	 * 
	 * @param separ String of the separator to use between columns
	 * @throws FileNotFoundException
	 * 
	 * <b>post:</b>Orders was exported<br>
	 */
	public void exportData(String separ) throws FileNotFoundException {
				{
					PrintWriter pw = new PrintWriter("data/orders.csv");
					for(Order elem:orders) {
						pw.println(elem.getCode()+SEPARATOR+elem.getDate()+SEPARATOR+elem.getClientIdNum()+SEPARATOR+elem.getOrderStat()+SEPARATOR+elem.getRestaurantNit()+SEPARATOR+elem.getOrdersList());
					}
					pw.close();
				}
	}
	/**
	 * This method import the restaurants information
	 * <b><pre>:<br>The file must be exist and with information to load<br>
	 * 
	 * @param fileName String of the file path to import
	 * 
	 * @throws IOException
	 * 
	 * <b>post:</b>Restaurants information was imported<br>
	 */
	public void importRestaurants(String fileName) throws IOException {
		BufferedReader br = new BufferedReader(new FileReader(fileName));
		br.readLine();
		String line = br.readLine();
		while(line!=null) {
			String[] parts = line.split(SEPARATOR);
			String name = parts[0];
			String nit = parts[1];
			String manager = parts[2];
			addRestaurant(name,nit,manager);
			line = br.readLine();
		}
		br.close();
	}
	/**
	 * This method import the clients information
	 * <b><pre>:<br>The file must be exist and with information to load<br>
	 * 
	 * @param fileName String of the file path to import
	 * 
	 * @throws IOException
	 * 
	 * <b>post:</b>Clients information was imported<br>
	 */
	public void importClients(String fileName) throws IOException {
		BufferedReader br = new BufferedReader(new FileReader(fileName));
		br.readLine();
		String line = br.readLine();
		while(line!=null) {
			String[] parts = line.split(SEPARATOR);
			String name = parts[0];
			String lastName = parts[1];
			String idNum = parts[2];
			int idType = Integer.parseInt(parts[3]);
			String telephone = parts[4];
			String address= parts[5];
			addClient(name, lastName, idNum, idType, telephone, address);
			line = br.readLine();
		}
		br.close();
	}
	/**
	 * This method import the products information
	 * <b><pre>:<br>The file must be exist and with information to load<br>
	 * 
	 * @param fileName String of the file path to import
	 * 
	 * @throws IOException
	 * 
	 * <b>post:</b>Products information was imported<br>
	 */
	public void importProducts(String fileName) throws IOException {
		BufferedReader br = new BufferedReader(new FileReader(fileName));
		br.readLine();
		String line = br.readLine();
		while(line!=null) {
			String[] parts = line.split(SEPARATOR);
			String name = parts[0];
			String code = parts[1];
			String info = parts[2];
			double cost = Double.parseDouble(parts[3]);
			String restNit = parts[4];
			addProduct(name, code, info, cost, restNit);
			line = br.readLine();
		}
		br.close();
	}

	//**************************************************************//

	//Sorting methods

	public void sortByRestaurantName() {
		RestaurantsNameComparator nc = new RestaurantsNameComparator();
		Collections.sort(restaurants, nc);
	}
	public void sortByClientTelephone() {
		Collections.sort(clients, new SortbyClientTelephone());
	}
	public void sortByClientIdNumber() {
		Collections.sort(clients, new SortbyClientId());
	}
	public void sortByRestaurantNitInsertion() {
		Collections.sort(restaurants);
	}

	//	sorting anonymus class

	class SortbyClientTelephone implements Comparator<Client> { 
		@Override
		public int compare(Client c1, Client c2) {
			return c2.getTelephone().compareTo(c1.getTelephone()); 
		} 
	}
	class SortbyClientId implements Comparator<Client> { 
		@Override
		public int compare(Client c1, Client c2) {
			return c2.getIdNum().compareTo(c1.getIdNum()); 
		} 
	}

	//**************************************************************//

	//Updating & searching	

	/**
	 * This method search a restaurant by his nit
	 * <b><pre>:<br>A restaurant must be added<br>
	 * 
	 * @param nit String of the restaurant NIT
	 * 
	 * @throws WrongNitException
	 * 
	 * @return position
	 * 
	 * <b>post:</b>Returns the position in the array<br>
	 */
	public int searchRestaurantNit(String nit) throws WrongNitException{
		int position = 0;
		boolean found = !false;
		for(int i=0; i<restaurants.size() && found; i++){
			if(restaurants.get(i).getNit().equalsIgnoreCase(nit)){
				found = true;
				position = i;
			}
		} if(found == false) {
			throw new WrongNitException();
		}
		return position;
	}
	/**
	 * This method search a product by his code and returns the position
	 * <b><pre>:<br>A product must be added<br>
	 * 
	 * @param code String of the product code
	 * 
	 * @throws NullCodeException
	 * 
	 * @return position
	 * 
	 * <b>post:</b>Returns the position in the array<br>
	 */
	public int searchProductByCode(String code) throws NullCodeException{
		int position = 0;
		boolean found = !false;
		for(int i=0; i<products.size() && found; i++){
			if(products.get(i).getCode().equalsIgnoreCase(code)){
				found = true;
				position = i;
			}
		} if(found == false) {
			throw new NullCodeException();
		}
		return position;
	}
	/**
	 * This method search a client by ID number and returns the position
	 * <b><pre>:<br>A client must be added<br>
	 * 
	 * @param idNum String of the client ID number
	 * 
	 * @throws WrongIdException
	 * 
	 * @return position
	 * 
	 * <b>post:</b>Returns the position in the array<br>
	 */
	public int searchClientId(String idNum) throws WrongIdException{
		int position = 0;
		boolean found = !false;
		for(int i=0; i<clients.size() && found; i++){
			if(clients.get(i).getIdNum().equalsIgnoreCase(idNum)){
				found = true;
				position = i;
			}
		}
		if(found = false) {
			throw new WrongIdException();
		}
		return position;
	}
	/**
	 * This method search a client by his name and last name executing a binary search
	 * <b><pre>:<br>A client must be added<br>
	 * 
	 * @param name String of client name
	 * @param lastName String od client last name
	 * 
	 * @return found
	 * 
	 * <b>post:</b>Returns the search condition<br>
	 */
	public boolean searchClientName(String name, String lastName) {
		String fullName = "";
		fullName = name+" "+lastName;
		boolean found = false;
		int start = 0;
		int end = clients.size()-1;
		while (start <= end && !found) {
			int middle = (start + end)/2;
			if (clients.get(middle).getFullName().equalsIgnoreCase(fullName)) {
				found = true;
			} else if(clients.get(middle).getFullName().compareToIgnoreCase(fullName) < 1){
				end = middle -1;
			} else {
				start = middle +1;
			}			
		}
		return found;
	}
	/**
	 * This method search an order by his code and returns the position
	 * <b><pre>:<br>An order must be added<br>
	 * 
	 * @param orderCode String of order code
	 * 
	 * @throws NullCodeException
	 * 
	 * @return position
	 * 
	 * <b>post:</b>Returns the position in the array<br>
	 */
	public int searchOrder(String orderCode) throws NullCodeException{
		int position = 0;
		boolean found = !false;
		for(int i=0; i<orders.size() && found; i++){
			if(orders.get(i).getCode().equalsIgnoreCase(orderCode)){
				found = true;
				position = i;
			}
		} if(found == false) {
			throw new NullCodeException();
		}
		return position;
	}
	/**
	 * This method search the products of a requested restaurant and returns their information
	 * <b><pre>:<br>The restaurant to search products must have products added<br>
	 * 
	 * @param nit String of restaurant NIT
	 * 
	 * @return info
	 * 
	 * <b>post:</b>Returns the products information linked with the restaurant requested<br>
	 */
	public String searchProductByRestaurant(String nit) {
		String info = "";
		for (int i = 0; i < products.size(); i++) {
			if(products.get(i).getRestaurantNit().equalsIgnoreCase(nit)) {
				info += products.get(i).getAllInfo();
			}
		}
		return info;
	}
	/**
	 * This method update the restuarant NIT linked to their products
	 * <b><pre>:<br>A product must be added and linked with a restaurant<br>
	 * 
	 * @param OldNit String of the restaurant old NIT
	 * @param NewNit String of the restaurant new NIT
	 * 
	 * 
	 * <b>post:</b>New NIT is setted for each restaurant<br>
	 */
	public void updateNitProducts(String OldNit, String NewNit) {
		for (int i = 0; i < products.size(); i++) {
			if(products.get(i).getRestaurantNit().equalsIgnoreCase(OldNit)) {
				products.get(i).setRestaurantNit(NewNit);
			}
		}
	}
	/**
	 * This method update the restuarant NIT linked to their orders
	 * <b><pre>:<br>An order must be added and linked with a restaurant<br>
	 * 
	 * @param OldNit String of the restaurant old NIT
	 * @param NewNit String of the restaurant new NIT
	 * 
	 * 
	 * <b>post:</b>New NIT is setted for each orders<br>
	 */
	public void updateNitOrders(String OldNit, String NewNit) {
		for (int i = 0; i < orders.size(); i++) {
			if(orders.get(i).getRestaurantNit().equalsIgnoreCase(OldNit)) {
				orders.get(i).setRestaurantNit(NewNit);
			}
		}
	}
	/**
	 * This method update the client ID number linked to their orders
	 * <b><pre>:<br>An order must be added and linked with a client<br>
	 * 
	 * @param OldId String of the client old ID number
	 * @param NewId String of the client new ID number
	 * 
	 * <b>post:</b>New ID number is setted for each orders<br>
	 */
	public void updateClientIdOrders(String OldId, String NewId) {
		for (int i = 0; i < orders.size(); i++) {
			if(orders.get(i).getClientIdNum().equalsIgnoreCase(OldId)) {
				orders.get(i).setClientIdNum(NewId);
			}
		}
	}
	/**
	 * This method update the product code linked to their orders
	 * <b><pre>:<br>An order must be added and linked with a product<br>
	 * 
	 * @param OldCode String of the product old code
	 * @param NewCode String of the product new code
	 * 
	 * <b>post:</b>New product code is setted for each orders<br>
	 */
	public void updateProductOrderCodeFromProduct(String oldCode, String newCode) {
		for (int i = 0; i < orders.size(); i++) {
			orders.get(i).updateProductsCode(oldCode, newCode);
		}
	}

	//**************************************************************//

	//Methods of restaurants

	/**
	 * This method add a restaurant, then serialize and returns an information about the adding process
	 * <b><pre>:<br>The restaurant to add must have a unique NIT<br>
	 * 
	 * @param name String of restaurant name
	 * @param nit String nit of restaurant NIT
	 * @param manager String manager of restaurant manager
	 * 
	 * @throws IOException
	 * 
	 * @return info
	 * 
	 * <b>post:</b>Returns information of adding process<br>
	 */
	public String addRestaurant(String name, String nit, String manager) throws IOException {
		Restaurant R = new Restaurant(name, nit, manager);
		String info = "";
		boolean added = false;
		if(restaurants.isEmpty()) {
			restaurants.add(R);
			added = true;
			info += "\n**Added!**\n";
			saveData("rest");
		}
		else if(!added){
			boolean unique = uniqueRestaurantNit(R.getNit());
			if(unique) {
				restaurants.add(R);
				info += "\n**Added!**\n";
				saveData("rest");
			}
			else
				info += "**\nRestaurant alredy exists\n**";
		}	
		return info;
	}
	/**
	 * This method search if a restaurant NIT is unique and returns a boolean
	 * <b><pre>:<br>A restaurant as minimum must be added<br>
	 * 
	 * @param nit String nit of restaurant NIT to search
	 * 
	 * @return unique
	 * 
	 * <b>post:</b>Returns a boolean notifying if Nit is unique or no<br>
	 */
	public boolean uniqueRestaurantNit(String nit){
		boolean unique = true;
		for(int i=0; i<restaurants.size() && unique; i++){
			if(restaurants.get(i).getNit().equalsIgnoreCase(nit)){
				unique = false;
			}
		}
		return unique;
	}
	/**
	 * This method deploy the restaurants and sort it by his names
	 * <b><pre>:<br>A restaurant as minimum must be added<br>
	 * 
	 * @return info
	 * 
	 * <b>post:</b>Restaurants list is deployed<br>
	 */
	public String showRestaurants() {
		String info = "";
		if (restaurants.isEmpty()) {
			info = "**\nThere no restaurants in list***\n";
		}
		else {
			sortByRestaurantName();
			info += getRestaurants()+"\n";		
		}
		return info;
	}
	//**************************************************************//

	//Methods of clients

	/**
	 * This method add a client, then serialize and returns an information about the adding process
	 * <b><pre>:<br>The restaurant to add must have a unique NIT<br>
	 * 
	 * @param name String of client name
	 * @param lastName String of client last name
	 * @param idNum String of client ID number
	 * @param choice Integer of the choice of ID type (CC, PP, CE or TI)
	 * @param tel String of client telephone
	 * @param address String of client address
	 * 
	 * @throws IOException
	 * 
	 * @return info
	 * 
	 * <b>post:</b>Returns information of adding process<br>
	 */
	public String addClient(String name, String lastName, String idNum, int choice, String tel, String address) throws IOException {
		String info = "";
		String idType = ""; 
		switch (choice) {
		case 1:
			idType = "CC";
			break;

		case 2:
			idType = "PP";
			break;

		case 3:
			idType = "CE";
			break;

		case 4:
			idType = "TI";
			break;

		default:
			info += "Choice not valid";
			break;					
		}
		Client c = new Client(name, lastName, idNum, idType, tel, address);
		boolean unique = uniqueClientId(c.getIdNum());
		if(clients.isEmpty()) {
			clients.add(c);
			info += "**Added!**";
			saveData("client");

		} else if(!unique) {
			info += "**Client alredy exists **";
		} else {
			//method sorting called
			clients.add(compareTo(c),c);
			info += "**Added!**";
			saveData("client");
		} 
		return info;
	}
	/**
	 * This method sort the clients by his names and last name to add clients sorted and returns the position to add the new client 
	 * <b><pre>:<br><br>
	 * 
	 * @param c Client Object
	 * 
	 * @return r
	 * 
	 * <b>post:</b>Returns position to set in array<br>
	 */
	@Override	
	public int compareTo(Client c) {
		int r = 0;
		boolean added = false;
		for (int i = 0; i < clients.size() && !added; i++) {
			int S = c.getFullName().compareToIgnoreCase(clients.get(i).getFullName());
			if (S > 0) {
				r = i;
				added = true;
			} else if(!added && i == clients.size()-1) {
				i++;
				r = i;
				added = true;
			}
		}
		return r;			
	}  
	/**
	 * This method search if a client ID number is unique and returns a boolean
	 * <b><pre>:<br>A client as minimum must be added<br>
	 * 
	 * @param idNum String ID number of client to search
	 * 
	 * @return unique
	 * 
	 * <b>post:</b>Returns a boolean notifying if ID number is unique or no<br>
	 */
	public boolean uniqueClientId(String idNum){
		boolean unique = true;
		for(int i=0; i<clients.size() && unique; i++){
			if(clients.get(i).getIdNum().equalsIgnoreCase(idNum)){
				unique = false;
			}
		}
		return unique;
	}
	/**
	 * This method deploy the clients 
	 * <b><pre>:<br>A client as minimum must be added<br>
	 * 
	 * @return info
	 * 
	 * <b>post:</b>Clients list is deployed<br>
	 */
	public String showClients() {
		String info = "";
		if (clients.isEmpty()) {
			info = "There no clients in list\n";
		}
		else {
			for (int i = 0; i < clients.size(); i++) {
				info += clients.get(i).getInfo()+"\n";
				info += i+"\n";
			}	
		}
		return info;
	}

	//**************************************************************//

	//Methods of products

	/**
	 * This method add a product, then serialize and returns an information about the adding process
	 * <b><pre>:<br>The product to add must have a unique product code<br>
	 * 
	 * @param name String of product name
	 * @param code String of product code
	 * @param infoP String of product information
	 * @param cost Double of product cost
	 * @param restNit String of restaurant NIT  
	 * 
	 * @throws IOException
	 * 
	 * @return info
	 * 
	 * <b>post:</b>Returns information of adding process<br>
	 */
	public String addProduct(String name, String code,String infoP, double cost, String restNit) throws IOException {
		String info = "";
		if(!uniqueRestaurantNit(restNit)) {
			Product p = new Product(name, code, infoP, cost, restNit);
			boolean unique = uniqueProductCode(p.getCode());
			if(unique) {
				products.add(p);
				info += "Added!";
				saveData("products");
			}
			else
				info += "** Product alredy exists **";	
		} else {
			info += "** Restaurant NIT doesn�t exists, product can�t be added! **";
		}
		return info;
	}
	/**
	 * This method search if a product code is unique and returns a boolean
	 * <b><pre>:<br>A product as minimum must be added<br>
	 * 
	 * @param code String of product code
	 * 
	 * @return unique
	 * 
	 * <b>post:</b>Returns a boolean notifying if code is unique or no<br>
	 */
	public boolean uniqueProductCode(String code){
		boolean unique = true;
		for(int i=0; i<products.size() && unique; i++){
			if(products.get(i).getCode().equalsIgnoreCase(code)){
				unique = false;
			}
		} 
		return unique;
	}
	/**
	 * This method deploy the products
	 * <b><pre>:<br>A product as minimum must be added<br>
	 * 
	 * @return info
	 * 
	 * <b>post:</b>Products list is deployed<br>
	 */
	public String showProducts() {
		String info = "";
		if (products.isEmpty()) {
			info = "There no products in list\n";
		}
		else {
			for (int i = 0; i < products.size(); i++) {
				info += products.get(i).getAllInfo()+"\n";
			}	
		}
		return info;
	}
	//**************************************************************//

	//Methods of orders

	/**
	 * This method add an order, then serialize and returns an information about the adding process
	 * <b><pre>:<br>A restaurant must be added as minimum<br>
	 * <b><pre>:<br>A client must be added as minimum<br>
	 * <b><pre>:<br>A product must be added as minimum linked to a restaurant<br>
	 * 
	 * @param code String of order code
	 * @param idNum String of client ID number
	 * @param Nit String of restaurant NIT  
	 * 
	 * @throws IOException
	 * 
	 * @return info
	 * 
	 * <b>post:</b>Returns information of adding process<br>
	 */
	public String addOrder(String code, String idNum, String Nit) throws IOException {
		String info = "";
		Order order = new Order(code, idNum, Nit); 
		boolean unique = uniqueOrderCode(order.getCode());
		if(unique) {
			orders.add(order);
			saveData("orders");
			info += "Added!";
		}
		else
			info += "** Order alredy exists **";

		return info;
	}
	/**
	 * This method search if an order is unique and returns a boolean
	 * <b><pre>:<br>An order as minimum must be added<br>
	 * 
	 * @param code String order code
	 * 
	 * @return unique
	 * 
	 * <b>post:</b>Returns a boolean notifying if order code is unique or no<br>
	 */
	public boolean uniqueOrderCode(String code){
		boolean unique = true;
		for(int i=0; i<orders.size() && unique; i++){
			if(orders.get(i).getCode().equalsIgnoreCase(code)){
				unique = false;
			}
		}
		return unique;
	}
	/**
	 * This method deploy the orders
	 * <b><pre>:<br>An order as minimum must be added<br>
	 * 
	 * @return info
	 * 
	 * <b>post:</b>Orders list is deployed<br>
	 */
	public String showOrders() {
		String info = "";
		if (orders.isEmpty()) {
			info = "There no orders in list\n";
		}
		else {
			for (int i = 0; i < orders.size(); i++) {
				info += orders.get(i).getInfo()+"\n";
			}	
		}
		return info;
	}

}