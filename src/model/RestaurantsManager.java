package model;
import java.util.ArrayList;
import java.lang.Comparable;


public class RestaurantsManager implements Comparable<Client> {


	public ArrayList<Restaurant> restaurants;
	public ArrayList<Product> products;
	public ArrayList<Client> clients;
	public ArrayList<Order> orders;


	public RestaurantsManager() {
		restaurants = new ArrayList<>();
		products = new ArrayList<>();
		clients = new ArrayList<>();
		orders = new ArrayList<>();
	}


	//**************************************************************//

	//Methods of restaurants

	public String addRestaurant(String name, String nit, String manager) {
		Restaurant R = new Restaurant(name, nit, manager);
		String info = "";
		boolean added = false;
		if(restaurants.isEmpty()) {
			restaurants.add(R);
			added = true;
			info += "\n**Added!**\n";
		}
		else if(!added){
			boolean unique = uniqueRestaurantNit(R.getNit());
			if(unique) {
				restaurants.add(R);
				info += "\n**Added!**\n";
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
			for (int i = 0; i < restaurants.size(); i++) {
				info += restaurants.get(i).getInfo()+"\n";
			}	
		}
		return info;
	}
	//**************************************************************//

	//Methods of clients

	public String addClient(String name, String lastName, String idNum, int choice, String tel, String adress) {
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

		} else if(!unique) {
			info += "**Client alredy exists **";
		} else {
			//method sorting called
			clients.add(compareTo(c),c);
			info += "**Added!**";
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
				info += "\n"+i+"\n";
			}	
		}
		return info;
	}

	//**************************************************************//

	//Methods of products

	public String addProduct(Product product) {
		String info = "";
		boolean unique = uniqueProductCode(product.getCode());
		if(unique) {
			products.add(product);
			info += "Added!";
		}
		else
			info += "** Product alredy exists **";

		return info;
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
				info += products.get(i).getInfo()+"\n";
			}	
		}
		return info;
	}
	//**************************************************************//

	//Methods of orders

	public String addOrder(Order order) {
		String info = "";
		boolean unique = uniqueOrderCode(order.getCode());
		if(unique) {
			orders.add(order);
			info += "Added!";
		}
		else
			info += "** Product alredy exists **";

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

	public String showoOrders() {
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