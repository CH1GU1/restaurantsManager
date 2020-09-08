package model;
import java.util.ArrayList;



public class RestaurantsManager {


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

	public String addRestaurant(Restaurant restaurant) {
		String info = "";
		boolean unique = uniqueProductCode(restaurant.getNit());
		if(unique) {
			restaurants.add(restaurant);
			info += "Added!";
		}
		else
			info += "** Restaurant alredy exists **";

		return info;
	}

	public boolean uniqueRestaurantCode(String nit){
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
			info = "There no restaurants in list\n";
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

	public String addClient(Client client) {
		String info = "";
		boolean unique = uniqueProductCode(client.getIdNum());
		if(unique) {
			clients.add(client);
			info += "Added!";
		}
		else
			info += "**Client alredy exists **";

		return info;
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
			info = "There no restaurants in list\n";
		}
		else {
			for (int i = 0; i < clients.size(); i++) {
				info += clients.get(i).getInfo()+"\n";
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