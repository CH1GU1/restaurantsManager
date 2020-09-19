package model;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.lang.Comparable;
import java.io.FileWriter;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.FileInputStream;
import java.io.File;


public class RestaurantsManager implements Comparable<Client> {
	public final static String SAVE_PATH_FILE_RESTAURANTS = "data/restaurants.ap2";
	public final static String SAVE_PATH_FILE_CLIENTS = "data/clients.ap2";
	public final static String SAVE_PATH_FILE_PRODUCTS = "data/products.ap2";
	public final static String SAVE_PATH_FILE_ORDERS = "data/orders.ap2";

	public List<Restaurant> restaurants;
	public List<Product> products;
	public List<Client> clients;
	public List<Order> orders;


	private final static String SEPARATOR = ";";

	public RestaurantsManager() {
		restaurants = new ArrayList<Restaurant>();
		products = new ArrayList<Product>();
		clients = new ArrayList<Client>();
		orders = new ArrayList<Order>();
	}
	public List<Restaurant> getRestaurants(){
		return restaurants;
	}
	public List<Product> getProducts(){
		return products;
	}
	public List<Client> getClients(){
		return clients;
	}
	public List<Order> getOrders(){
		return orders;
	}
	
	public void copyRestaurants() {
		List<Restaurant> copyRestaurants = new ArrayList<Restaurant>(restaurants);
	}
	public String toString(){
		String msg = "Restaurants List:\n";
		for(Restaurant thisRestaurant:restaurants){
			msg += thisRestaurant.getName()+SEPARATOR+thisRestaurant.getNit()+SEPARATOR+thisRestaurant.getManager()+"\n";
		}
		return msg;
	}
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
	public void exportData() throws IOException {
		File newTextFile = new File("data/restaurants.csv");
		FileWriter fw = new FileWriter(newTextFile);
		fw.write(toString());
		fw.close();
	}
	//**************************************************************//

	//Sorting methods

	public void sortByRestaurantName() {
		RestaurantsNameComparator nc = new RestaurantsNameComparator();
		Collections.sort(restaurants, nc);
	}
	public void sortByClientTelephone() {
		Collections.sort(clients, Collections.reverseOrder());
	}
	public void sortByRestaurantNitInsertion() {
		for (int j = 1; j < restaurants.size(); j++) {
			Restaurant current = restaurants.get(j);
			int i = j-1;
			while ((i > -1) && ((restaurants.get(i).compareTo(current)) == 1)) {
				restaurants.set(i+1, restaurants.get(i));
				i--;
			}
			restaurants.set(i+1, current);
		}
	}
	//**************************************************************//

	//Updating & searching	

	public int searchRestaurantNit(String nit){
		int position = 0;
		boolean found = !false;
		for(int i=0; i<restaurants.size() && found; i++){
			if(restaurants.get(i).getNit().equalsIgnoreCase(nit)){
				found = true;
				position = i;
			}
		}
		return position;
	}
	public int searchProductByCode(String code){
		int position = 0;
		boolean found = !false;
		for(int i=0; i<products.size() && found; i++){
			if(products.get(i).getCode().equalsIgnoreCase(code)){
				found = true;
				position = i;
			}
		}
		return position;
	}
	public int searchClientId(String idNum){
		int position = 0;
		boolean found = !false;
		for(int i=0; i<clients.size() && found; i++){
			if(clients.get(i).getIdNum().equalsIgnoreCase(idNum)){
				found = true;
				position = i;
			}
		}
		return position;
	}
	public int searchOrder(String orderCode){
		int position = 0;
		boolean found = !false;
		for(int i=0; i<orders.size() && found; i++){
			if(orders.get(i).getCode().equalsIgnoreCase(orderCode)){
				found = true;
				position = i;
			}
		}
		return position;
	}

	public String searchProductByRestaurant(String nit) {
		String info = "";
		for (int i = 0; i < products.size(); i++) {
			if(products.get(i).getRestaurantNit().equalsIgnoreCase(nit)) {
				info += products.get(i).getAllInfo();
			}
		}	
		return info;
	}
	public int searchProductInProducts(String productCode){
		int position = 0;
		boolean found = !false;
		for(int i=0; i<products.size() && found; i++){
			if(products.get(i).getCode().equalsIgnoreCase(productCode)){
				found = true;
				position = i;
			}
		}
		return position;
	}
	public void updateNitProducts(String OldNit, String NewNit) {
		for (int i = 0; i < products.size(); i++) {
			if(products.get(i).getRestaurantNit().equalsIgnoreCase(OldNit)) {
				products.get(i).setRestaurantNit(NewNit);
			}
		}
	}
	public void updateNitOrders(String OldNit, String NewNit) {
		for (int i = 0; i < orders.size(); i++) {
			if(orders.get(i).getRestaurantNit().equalsIgnoreCase(OldNit)) {
				orders.get(i).setRestaurantNit(NewNit);
			}
		}
	}
	public void updateClientIdOrders(String OldId, String NewId) {
		for (int i = 0; i < orders.size(); i++) {
			if(orders.get(i).getClientIdNum().equalsIgnoreCase(OldId)) {
				orders.get(i).setClientIdNum(NewId);
			}
		}
	}
	public void updateProductOrderCodeFromProduct(String oldCode, String newCode) {
		for (int i = 0; i < orders.size(); i++) {
			orders.get(i).updateProductsCode(oldCode, newCode);
		}
	}

	//**************************************************************//

	//Methods of restaurants

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
	public boolean uniqueRestaurantNit(String nit){
		boolean unique = true;
		for(int i=0; i<restaurants.size() && unique; i++){
			if(restaurants.get(i).getNit().equalsIgnoreCase(nit)){
				unique = false;
			}
		}
		return unique;
	}
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

	public String addClient(String name, String lastName, String idNum, int choice, String tel, String adress) throws IOException {
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
		Client c = new Client(name, lastName, idNum, idType, tel, adress);
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
	public boolean uniqueClientId(String idNum){
		boolean unique = true;
		for(int i=0; i<clients.size() && unique; i++){
			if(clients.get(i).getIdNum().equalsIgnoreCase(idNum)){
				unique = false;
			}
		}
		return unique;
	}
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

	public String addProduct(String name, String code,String infoP, double cost, String restNit) throws IOException {
		String info = "";
		if(restNitExist(restNit)) {
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
			info += "** Restaurant NIT doesn´t exists, product can´t be added! **";
		}
		return info;
	}
	public boolean restNitExist(String restNit) {
		boolean exist = false;
		for (int i = 0; i < restaurants.size(); i++) {
			if(restNit.equalsIgnoreCase(restaurants.get(i).getNit())) {
				exist = true;
			}
		}
		return exist;
	}
	public boolean uniqueProductCode(String code){
		boolean unique = true;
		for(int i=0; i<products.size() && unique; i++){
			if(products.get(i).getCode().equalsIgnoreCase(code)){
				unique = false;
			}
		}
		return unique;
	}
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
	public boolean uniqueOrderCode(String code){
		boolean unique = true;
		for(int i=0; i<orders.size() && unique; i++){
			if(orders.get(i).getCode().equalsIgnoreCase(code)){
				unique = false;
			}
		}
		return unique;
	}
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