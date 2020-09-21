package ui;

import java.io.IOException;
import java.util.Random;
import java.util.Scanner;
import java.math.BigInteger;
import model.RestaurantsManager;


public class Menu {
	private Scanner sc;
	private RestaurantsManager restaurantsManager = new RestaurantsManager();
	final static int EXIT_MENU = 20;
	Random random = new Random();

	public Menu() {
		sc = new Scanner(System.in);

	}
	private void executeOperation(int option) {
		switch (option) {
		case 1:
			addRestaurant();
			break;
		case 2:
			addProducts();
			break;
		case 3:
			addClient();
			break;
		case 4:
			addOrder();
			break;
		case 5:
			showRestaurants();
			break;
		case 6:
			showProducts();
			break;
		case 7:
			showClients();
			break;
		case 8:
			showOrders();

			break;
		case 9:
			showClientsSortedByTelephone();

			break;
		case 10:
			showRestaurantsSortedByNit();
			
			break;
		case 11:
			showClientsSortedById();		

			break;
		case 12:
			exportData();				
			
			break;
		case 13:
			importData();			

			break;
		case 14:
			updateRestuarant();
					
			break;
		case 15:
			updateProduct();
			
			break;
		case 16:
			updateClient();
			
			
			break;
		case 17:
			try {
				updateOrder();
				System.out.println("Saved new update!");
			} catch (IOException e) {
				System.err.println("Data can't be updated");
			}
				

			break;
		case 18:
			loadProgram();	
			
			break;
		case 19:
			SearchClientByName();
			
			break;
		case 20:
			exitProgram();
			break;
		default:
			System.out.println("Select a correct option");
			break;
		}

	}
	private String getMenu() {
		String menu;
		menu = "============================\n";
		menu = "===========WELCOME==========\n";
		menu = "============================\n";
		menu += "=====Restaurant Manager====\n";
		menu += "===========================\n";
		menu += "1. Add restaurant\n";
		menu += "2. Add product\n";
		menu += "3. Add client\n";
		menu += "4. Add Order\n";
		menu += "5. Show restaurants list\n";
		menu += "6. Show products list\n";
		menu += "7. Show clients list\n";
		menu += "8. Show orders list\n";
		menu += "9. Sort clients list by telephone\n";
		menu += "10. Sort restaurants by NIT\n";
		menu += "11. Sort clients by ID\n";
		menu += "12. Export Data\n";
		menu += "13. Import Data\n";
		menu += "14. Update restaurants\n";
		menu += "15. Update products\n";
		menu += "16. Update clients\n";
		menu += "17. Update orders\n";
		menu += "18. Load program data\n";
		menu += "19. Search client by name\n";
		menu += "20. Exit\n";

		menu += "Please enter an option\n";
		return menu;
	}
	//Adding objects 
	private void addRestaurant() {
		System.out.println("ADDING RESTAURANT");
		System.out.println("Please enter the restaurant name: ");
		String name = sc.nextLine();
		System.out.println("Please enter the restaurant NIT: ");
		String nit = sc.nextLine();
		System.out.println("Please enter the restaurant manager name: ");
		String manager = sc.nextLine();
		//SAVING...
		System.out.println("Saving data ...");
		try{
			System.out.println(restaurantsManager.addRestaurant(name, nit, manager));
			System.out.println("The restaurant was saved succesfully");
		}catch(IOException ioe){
			System.out.println("The data can't be saved");
		}

	}
	private void loadProgram() {
		System.out.println("Loading data ...");
		try{
			restaurantsManager.loadData("rest");
			restaurantsManager.loadData("client");
			restaurantsManager.loadData("products");
			restaurantsManager.loadData("orders");
			System.out.println("The program data were loaded succesfully");
		}catch(IOException | ClassNotFoundException e){
			System.out.println("The data can't be load");
		}
	}
	private void addClient() {
		int choice;
		System.out.println("ADDING CLIENT");
		System.out.println("Please enter the client name: ");
		String name = sc.nextLine();
		System.out.println("Please enter the client last name: ");
		String lastName = sc.nextLine();
		System.out.println("Please enter the client ID number: ");
		String idNum = sc.nextLine();
		System.out.println("Please enter the client ID type: ");
		do {
			System.out.println("Please select the type of ID\n1.CC\n2.PP\n3.CE\n4.TI\n");
			choice = Integer.parseInt(sc.nextLine());
		} while(!((choice == 1) || (choice == 2) || (choice == 3) || (choice == 4)));	
		System.out.println("Please enter the client telephone: ");
		String tel = sc.nextLine();
		System.out.println("Please enter the client address: ");
		String adress = sc.nextLine();
		try {
			System.out.println(restaurantsManager.addClient(name, lastName, idNum, choice,tel,adress));
			System.out.println("The client was saved succesfully");
		} catch (IOException e) {
			System.out.println("The data can't be saved");
		}
	}
	private void addProducts() {
		System.out.println("ADDING PRODUCT");
		System.out.println("Please enter the product name: ");
		String name = sc.nextLine();
		System.out.println("Please enter the product code: ");
		String code = sc.nextLine();
		System.out.println("Please enter the product information: ");
		String infoP = sc.nextLine();
		System.out.println("Please enter the product cost: ");
		double cost = Double.parseDouble(sc.nextLine());
		System.out.println("Please enter the restaurant NIT");
		showRestaurants();
		String restNit = sc.nextLine();
		try {
			System.out.println(restaurantsManager.addProduct(name, code, infoP, cost, restNit));
			System.out.println("The product was saved succesfully");
		} catch (IOException e) {
			System.out.println("The data can't be saved");
		}
	}
	private void addOrder()  {
		boolean done = false;
		if(!restaurantsManager.clients.isEmpty() && !restaurantsManager.restaurants.isEmpty() && !restaurantsManager.products.isEmpty()) {
			System.out.println("**ADDING ORDER**\nEntry the client ID number\n");
			showClients();
			String idNum = sc.nextLine();
			System.out.println("**Entry the restaurant NIT**");
			showRestaurants();
			String restNit = sc.nextLine();
			if(!restaurantsManager.uniqueClientId(idNum) && !restaurantsManager.uniqueRestaurantNit(restNit)) {
				int requestedClient = restaurantsManager.searchClientId(idNum);
				int requestedRestaurant = restaurantsManager.searchRestaurantNit(restNit);
				String code = new BigInteger(50, random).toString(32);
				try {
					restaurantsManager.addOrder(code, restaurantsManager.getClients().get(requestedClient).getIdNum(), restaurantsManager.getRestaurants().get(requestedRestaurant).getNit());
				} catch (IOException e) {
					System.out.println("Order cant be added!");
				} 
				int requestedOrderPos = restaurantsManager.searchOrder(code);
				do {
					System.out.println("Enter the code of product for add to order");		
					System.out.println(restaurantsManager.searchProductByRestaurant(restNit)); 		//retorna productos de ese restaurante
					String productCode = sc.nextLine();
					System.out.println("Enter quantity of that product");
					int quantity = Integer.parseInt(sc.nextLine());	
					restaurantsManager.orders.get(requestedOrderPos).addProductInOrderList(productCode, quantity);
					System.out.println("Product added to order list");
					System.out.println("Do you want to add more products?\n1.Yes  /  2.No");
					int option = Integer.parseInt(sc.nextLine());
					if(option == 1)
						done = !false;
					else if(option == 2)
						done = !true;
					System.out.println("Order finalized");
				} while (done);
			}
			try {
				System.out.println("Saving order...");
				restaurantsManager.saveData("orders");
				System.out.println("Order was saved");
			} catch (IOException e) {
				System.out.println("Data can't be saved");
			}
		}
		else
			System.out.println("Check data of clients, restaurants or products");
	}
	
	//Export
	private void exportData() {
		try{
			restaurantsManager.exportData();
			System.out.println("The data was exported succesfully");
		}catch(IOException fnfe){
			System.out.println("The data can't be export");
		}
	}

	//Import
	private void importData() {
		System.out.println("IMPORTING DATA");
		boolean condition = true;
		do {
			System.out.println("1.Import restaurants\n2.Import products\n3.Import clients\n4.Import orders");
			int key = Integer.parseInt(sc.nextLine());
			switch (key) {
			case 1:
				try {
					System.out.println("Enter the patch/name of the file to import restaurants");
					String fileName = sc.nextLine();
					System.out.println("Importing data... please wait");
					restaurantsManager.importRestaurants(fileName);
					System.out.println("The data was imported succesfully");
				} catch (IOException e) {
					System.out.println("The data can't be imported");
				}
				condition = false;
				break;
			case 2:
				try {
				System.out.println("Enter the patch/name of the file to import products");
				String fileName = sc.nextLine();
				System.out.println("Importing data... please wait");
				restaurantsManager.importProducts(fileName);
				System.out.println("The data was imported succesfully");
				} catch (IOException e) {
					System.out.println("The data can't be imported");
				}
				condition = false;
				break;
			case 3:
				try {
					System.out.println("Enter the patch/name of the file to import clients");
					String fileName = sc.nextLine();
					System.out.println("Importing data... please wait");
					restaurantsManager.importClients(fileName);
					System.out.println("The data was imported succesfully");
				} catch (IOException e) {
					System.out.println("The data can't be imported");
				}
				condition = false;
				break;
			case 4:

				break;
			default:
				System.out.println("Select a correct option");
				break;
			}	
		} while (condition);
		

	}
	
	//Searching 
	private void SearchClientByName() {
		System.out.println("**FINDING CLIENT**");
		System.out.println("Enter the first name of client ");
		String name = sc.nextLine();
		System.out.println("Enter the last name of client ");
		String lastName = sc.nextLine();
		boolean found;
		long start = System.currentTimeMillis();
		found = restaurantsManager.searchClientFullName(name, lastName);
		long end = System.currentTimeMillis();
		if(found) {
			System.out.println("Client :"+name+" "+lastName+" was found!");
		} else {
			System.out.println("Client :"+name+" "+lastName+" was not found!");
		}
		System.out.println("Start: "+start);
		System.out.println("End: "+end);
		System.out.println("Searching time was: "+(end-start));
	}



	//Deploying 
	private void showRestaurants() {
		System.out.println("\n***DEPLOYING RESTAURANTS LIST***\n");
		System.out.println(restaurantsManager.showRestaurants());
	}
	private void showClients() {
		System.out.println("\n***DEPLOYING CLIIENTS LIST***\n");	
		System.out.println(restaurantsManager.showClients());
	}
	private void showProducts() {
		System.out.println("\n***DEPLOYING PRODUCTS LIST***\n");	
		System.out.println(restaurantsManager.showProducts());
	}
	private void showOrders() {
		System.out.println("\n***DEPLOYING ORDERS LIST***\n");	
		System.out.println(restaurantsManager.showOrders());
	}

	//Deploying Sorting
	private void showClientsSortedByTelephone() {
		System.out.println("\n***DEPLOYING CLIENTS LIST SORTED BY TELEPHONE***\n");	
		restaurantsManager.sortByClientTelephone();
		System.out.println("After sorting by telephone");
		System.out.println(restaurantsManager.getClients());
	}
	private void showRestaurantsSortedByNit() {
		System.out.println("\n***DEPLOYING RESTAURANTS LIST SORTED BY NIT***\n");	
		restaurantsManager.sortByRestaurantNitInsertion();
		System.out.println(restaurantsManager.getRestaurants());
	}
	private void showClientsSortedById() {
		System.out.println("\n***DEPLOYING CLIENTS LIST SORTED BY ID NUMBER***\n");	
		restaurantsManager.sortByClientIdNumber();
		System.out.println(restaurantsManager.getClients());
	}

	//Update
	private void updateRestuarant() {
		System.out.println("UPDATING RESTAURANT");
		showRestaurants();
		System.out.println("Please enter the restaurant NIT to be update: ");
		String restNit = sc.nextLine();
		if(!restaurantsManager.uniqueRestaurantNit(restNit)) {
			int requested = restaurantsManager.searchRestaurantNit(restNit);
			System.out.println("Please select what you want to update\n1.Name\n2.Manager\n3.Nit");
			int key = Integer.parseInt(sc.nextLine());
			switch (key) {
			case 1:
				System.out.println("Please enter the new restaurant name: ");
				String name = sc.nextLine();
				restaurantsManager.getRestaurants().get(requested).setName(name);
				System.out.println("Saving data ...");
				try{
					restaurantsManager.saveData("rest");
					System.out.println("The restaurant was updated succesfully");
				}catch(IOException ioe){
					System.out.println("The data can't be updated");
				}
				break;
			case 2:
				System.out.println("Please enter the new manager name: ");
				String manager = sc.nextLine();
				restaurantsManager.getRestaurants().get(requested).setManager(manager);
				System.out.println("Saving data ...");
				try{
					restaurantsManager.saveData("rest");
					System.out.println("The restaurant was updated succesfully");
				}catch(IOException ioe){
					System.out.println("The data can't be updated");
				}
				break;
			case 3:
				System.out.println("Please enter the new restaurant NIT: ");
				String nit = sc.nextLine();
				if(restaurantsManager.uniqueRestaurantNit(nit)) {
					restaurantsManager.getRestaurants().get(requested).setNit(nit);
					restaurantsManager.updateNitProducts(restNit, nit);
					restaurantsManager.updateNitOrders(restNit, nit);
					System.out.println("Saving data ...");
					try{
						restaurantsManager.saveData("rest");
						restaurantsManager.saveData("products");
						restaurantsManager.saveData("orders");
						System.out.println("The restaurant was updated succesfully");
					}catch(IOException ioe){
						System.out.println("The data can't be updated");
					}
				} 
				else {
					System.out.println("Nit already exist! Try another");
				}
				break;
			default:
				System.out.println("Select a correct option");
				break;
			}
		} else {
			System.out.println("Restaurant NIT does not exist!");
		}
	}
	private void updateClient() {
		System.out.println("UPDATING CLIENT");
		showClients();
		System.out.println("Please enter the client ID to be update: ");
		String idNum = sc.nextLine();
		if(!restaurantsManager.uniqueClientId(idNum)) {
			int requested = restaurantsManager.searchClientId(idNum);
			System.out.println("Please select what you want to update\n1.Name\n2.Last name\n3.ID number\n4.ID type\n5.Telephone\n6.Addres");
			int key = Integer.parseInt(sc.nextLine());
			switch (key) {
			case 1:
				System.out.println("Please enter the new client name: ");
				String name = sc.nextLine();
				restaurantsManager.getClients().get(requested).setName(name);
				System.out.println("Saving data ...");
				try{
					restaurantsManager.saveData("client");
					System.out.println("The client was updated successfully");
				}catch(IOException ioe){
					System.out.println("The data can't be updated");
				}
				break;
			case 2:
				System.out.println("Please enter the new client last name: ");
				String lastName = sc.nextLine();
				restaurantsManager.getClients().get(requested).setLastName(lastName);
				System.out.println("Saving data ...");
				try{
					restaurantsManager.saveData("client");
					System.out.println("The client was updated successfully");
				}catch(IOException ioe){
					System.out.println("The data can't be updated");
				}
				break;
			case 3:
				System.out.println("Please enter the new client ID: ");
				String newId = sc.nextLine();
				if(restaurantsManager.uniqueClientId(newId)) {
					restaurantsManager.getClients().get(requested).setIdNum(newId);		
					restaurantsManager.updateClientIdOrders(idNum,newId);	
					System.out.println("Saving data ...");
					try{
						restaurantsManager.saveData("client");
						restaurantsManager.saveData("orders");
						System.out.println("The client was updated successfully");
					}catch(IOException ioe){
						System.out.println("The data can't be updated");
					}
				} else {
					System.out.println("ID already registered, try other!");
				}		
				break;
			case 4:
				System.out.println("Please enter the new client ID type: ");
				int choice;
				do {
					System.out.println("Please select the type of ID\n1.CC\n2.PP\n3.CE\n4.TI\n");
					choice = Integer.parseInt(sc.nextLine());
				} while(!((choice == 1) || (choice == 2) || (choice == 3) || (choice == 4)));	
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
					System.out.println("Choice not valid");
					break;					
				}
				restaurantsManager.getClients().get(requested).setIdType(idType);		
				System.out.println("Saving data ...");
				try{
					restaurantsManager.saveData("client");
					System.out.println("The client was updated successfully");
				}catch(IOException ioe){
					System.out.println("The data can't be updated");
				}
				break;
			case 5:
				System.out.println("Please enter the new client telephone: ");
				String tel = sc.nextLine();
				restaurantsManager.getClients().get(requested).setTelephone(tel);
				System.out.println("Saving data ...");
				try{
					restaurantsManager.saveData("client");
					System.out.println("The client was updated successfully");
				}catch(IOException ioe){
					System.out.println("The data can't be updated");
				}
				break;
			case 6:
				System.out.println("Please enter the new client address: ");
				String address = sc.nextLine();
				restaurantsManager.getClients().get(requested).setAddress(address);;
				System.out.println("Saving data ...");
				try{
					restaurantsManager.saveData("client");
					System.out.println("The client was updated successfully");
				}catch(IOException ioe){
					System.out.println("The data can't be updated");
				}
				break;
			default:
				System.out.println("Select a correct option");
				break;
			}
		} else {
			System.out.println("Client ID does not exist");
		}
	}
	private void updateProduct() {
		System.out.println("UPDATING PRODUCT");
		showProducts();
		System.out.println("Please enter the product code to be update: ");
		String pCode = sc.nextLine();
		if(!restaurantsManager.uniqueProductCode(pCode)) {
			int requested = restaurantsManager.searchProductByCode(pCode);
			System.out.println("Please select what you want to update\n1.Name\n2.Information\n3.Cost\n4.Code");
			int key = Integer.parseInt(sc.nextLine());
			switch (key) {
			case 1:
				System.out.println("Please enter the new product name: ");
				String name = sc.nextLine();
				restaurantsManager.getProducts().get(requested).setName(name);
				System.out.println("Saving data ...");
				try{
					restaurantsManager.saveData("products");
					System.out.println("The product was updated successfully");
				}catch(IOException ioe){
					System.out.println("The data can't be updated");
				}
				break;
			case 2:
				System.out.println("Please enter the new information: ");
				String info = sc.nextLine();
				restaurantsManager.getProducts().get(requested).setInfo(info);
				System.out.println("Saving data ...");
				try{
					restaurantsManager.saveData("products");
					System.out.println("The product was updated successfully");
				}catch(IOException ioe){
					System.out.println("The data can't be updated");
				}
				break;
			case 3:
				System.out.println("Please enter the new product price: ");
				double price = Double.parseDouble(sc.nextLine());
				restaurantsManager.getProducts().get(requested).setCost(price);
				System.out.println("Saving data ...");
				try{
					restaurantsManager.saveData("products");
					System.out.println("The product was updated successfully");
				}catch(IOException ioe){
					System.out.println("The data can't be updated");
				}
				break;
			case 4:
				System.out.println("Please enter the new product code: ");
				String code = sc.nextLine();
				if(restaurantsManager.uniqueProductCode(code)) {
					restaurantsManager.getProducts().get(requested).setCode(code);
					restaurantsManager.updateProductOrderCodeFromProduct(pCode,code);
					System.out.println("Saving data ...");
					try{
						restaurantsManager.saveData("products");
						restaurantsManager.saveData("orders");
						System.out.println("The product was updated successfully");
					}catch(IOException ioe){
						System.out.println("The data can't be updated");
					}
				} else {
					System.out.println("Code already in use, try other!");
				}
				break;
			default:
				System.out.println("Select a correct option");
				break;
			}	
		} else {
			System.out.println("Product code does not exist");
		}
	}
	private void updateOrder() throws IOException {
		System.out.println("UPDATING ORDER");
		showOrders();
		System.out.println("Please enter the order code to be update: ");
		String orderCode = sc.nextLine();
		if (!restaurantsManager.uniqueOrderCode(orderCode)) {
			int requested = restaurantsManager.searchOrder(orderCode);
			boolean statusChanged = false;
			do {
				System.out.println("What you want to do?\n1.Delete all order (only for orders when status is requested)\n2.Update order status");
				int key = Integer.parseInt(sc.nextLine());	
				switch (key) {
				case 1:
					if(restaurantsManager.getOrders().get(requested).getOrderStat().equalsIgnoreCase("REQUESTED")) {
						restaurantsManager.getOrders().remove(requested);
						System.out.println("Order deleted!");
						statusChanged = true;
						restaurantsManager.saveData("orders");
					} else {
						System.out.println("Order can't be deleted! Status is not REQUESTED yet");	
						statusChanged = true;
					}
					break;
				case 2:
					System.out.println("Updating status...");
					System.out.println(restaurantsManager.getOrders().get(requested).setOrderStatByCondition(restaurantsManager.getOrders().get(requested).getOrderStat()));	
					statusChanged = true;
					restaurantsManager.saveData("orders");
					break;
				default:
					System.out.println("Enter a correct option");
					break;
				}
			} while(!statusChanged);
		} else {
			System.out.println("Order code does not exist!");
		}

	}
	private void exitProgram() {
		sc.close();
		System.out.println("Good bye!");
	}
	private int readOption() {
		int op;
		op = Integer.parseInt(sc.nextLine());
		return op;
	}
	public void startMenu() {
		String menu = getMenu();
		int option;
		do {
			System.out.println(menu);
			option = readOption();
			executeOperation(option);

		} while (option!=EXIT_MENU);
	}
}
