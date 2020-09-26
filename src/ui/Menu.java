package ui;

import java.io.IOException;
import java.util.Random;
import java.util.Scanner;
import exceptions.NullCodeException;
import exceptions.WrongIdException;
import exceptions.WrongNitException;

import java.math.BigInteger;
import model.RestaurantsManager;


public class Menu {

	//Initialization and constants declaration

	private Scanner sc;
	private RestaurantsManager restaurantsManager = new RestaurantsManager();
	final static int EXIT_MENU = 17;
	Random random = new Random();

	/**
	 *This method initialize the scanner.
	 *<b>pre:</b> <br>
	 *<b>post:</b>The scanner is ready<br>
	 */
	public Menu() {
		sc = new Scanner(System.in);

	}
	/**
	 * This method receives the menu option .
	 * <b>pre:</b>Select a valid option.<br>
	 * 
	 * <b>post:</b><br>
	 */
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
			try {
				addOrder();
			} catch (WrongIdException | WrongNitException | NullCodeException e) {
				System.err.println(e.getMessage());
			}

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
			exportOrder();				

			break;
		case 11:
			importData();			

			break;
		case 12:
			try {
				updateRestuarant();
			} catch (WrongNitException e) {
				System.err.println(e.getMessage());
			}

			break;
		case 13:
			try {
				updateProduct();
			} catch (NullCodeException e) {
				System.err.println(e.getMessage());
			}

			break;
		case 14:
			try {
				updateClient();
			} catch (WrongIdException e) {
				System.err.println(e.getMessage());
			}

			break;
		case 15:
			try {
				updateOrder();
			} catch (IOException | NullCodeException e) {
				System.err.println(e.getMessage());
			}
			break;
		case 16:
			SearchClientByName();

			break;
		case 17:
			exitProgram();
			break;
		default:
			System.out.println("Select a correct option");
			break;
		}

	}
	/**
	 * This method deploy the main program menu.
	 * <b>pre:</b>Select a valid option.<br>
	 * 
	 * <b>post:</b><br>
	 */
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
		menu += "10. Export Order\n";
		menu += "11. Import Data\n";
		menu += "12. Update restaurants\n";
		menu += "13. Update products\n";
		menu += "14. Update clients\n";
		menu += "15. Update orders\n";
		menu += "16. Search client by name\n";
		menu += "17. Exit\n";
		menu += "Please enter an option\n";
		return menu;
	}


	//----------Deserialize--------------


	/**
	 * This method deserialize the program data.
	 * <b>pre:</b>Data must be created for deserialize a previous data.<br>
	 * 
	 * <b>post:</b>Program is now loaded with the last changes<br>
	 */
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


	//Adding objects 


	/**
	 * This method add a restaurant and serialize, could catch an IOException if an error occur during serializing.
	 * <b>pre:</b>The restaurant to add must have a different NIT if it NIT already exist.<br>
	 * <b>pre:</b>The restaurant NIT have to be integer only.<br>
	 * 
	 * <b>post:</b>The restaurant is added and is serialized<br>
	 */
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
	/**
	 * This method add a client and serialize, could catch an IOException if an error occur during serializing.
	 * <b>pre:</b>The client to add must have a different ID number if it ID already exist.<br>
	 * <b>pre:</b>The client ID number have to be integer only.<br>
	 * 
	 * <b>post:</b>The client is added and is serialized<br>
	 */
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
	/**
	 * This method add a product and serialize, could catch an IOException if an error occur during serializing.
	 * <b>pre:</b>The product to add must have a different code if it code already exist.<br>
	 * <b>pre:</b>The product code number have to be integer only.<br>
	 * <b>pre:</b>One restaurant as minimum must be added for register the product linked with his NIT.<br>
	 * 
	 * <b>post:</b>The product is added to a restaurant and is serialized<br>
	 */
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
	/**
	 * This method add a order and serialize, could catch an IOException if an error occur during serializing, could throw WrongIdException, WrongNitException, NullCodeException.
	 * <b>pre:</b>One restaurant as minimum must be added with a product as minimum.<br>
	 * <b>pre:</b>One client as minimum must be added.<br>
	 * 
	 * <b>post:</b>The order is added to a client and is serialized<br>
	 */
	private void addOrder() throws WrongIdException, WrongNitException, NullCodeException  {
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
					System.out.println(restaurantsManager.searchProductByRestaurant(restNit)); 
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
			} else if(restaurantsManager.uniqueClientId(idNum) == true) {
				throw new WrongIdException();
			}else if(restaurantsManager.uniqueRestaurantNit(restNit) == true) {
				throw new WrongNitException();
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

	//------------Export--------------


	
	/**
	 * This method export the orders in .csv format, could catch an IOException if an error occur during exporting.
	 * <b>pre:</b>One order as minimum must be added.<br>
	 * 
	 * <b>post:</b>The order is exported<br>
	 */
	private void exportOrder() {
		System.out.println("***EXPORTING ORDERS***");
		System.out.println("Please entry the separator to use between columns of the .csv file");
		System.out.println("Suggest comma (,)");
		String separ = sc.nextLine();
		try{
			restaurantsManager.exportOrder(separ);
			System.out.println("The data was exported succesfully");
		}catch(IOException fnfe){
			System.out.println("The data can't be export");
		}
	}

	//-------------Import-------------


	
	/**
	 * This method import the program data in .csv format chosen by user, could catch an IOException if an error occur during importing.
	 * <b>pre:</b>The columns of .csv file are separated by comma.<br>
	 * 
	 * <b>post:</b>The data requested in .csv file is imported to the program<br>
	 */
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

	//-------------Searching------------ 

	/**
	 * This method request a client name and lastname, then call a method in Restaurants Manager to execute the binary search, finally show the searching time.
	 * <b>pre:</b>A client must be created.<br>
	 * 
	 * <b>post:</b>The data requested in .csv file is imported to the program<br>
	 */
	private void SearchClientByName() {
		restaurantsManager.sortClientsCorrect();
		System.out.println("**FINDING CLIENT**");
		System.out.println("Enter the first name of client ");
		String name = sc.nextLine();
		System.out.println("Enter the last name of client ");
		String lastName = sc.nextLine();
		boolean found;
		long start = System.currentTimeMillis();
		found = restaurantsManager.searchClientName(name, lastName);
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

	//-------------Deploying------------

	/**
	 * This method call a method in restaurants manager and deploy the restaurants list sorted alphabetically ascending.
	 * <b>pre:</b>A restaurant must be added.<br>
	 * 
	 * <b>post:</b>The restaurants list is deployed alphabetically ascending<br>
	 */
	private void showRestaurants() {
		System.out.println("\n***DEPLOYING RESTAURANTS LIST***\n");
		System.out.println(restaurantsManager.showRestaurants());
	}
	/**
	 * This method call a method in restaurants manager and deploy the clients list.
	 * <b>pre:</b>A client must be added.<br>
	 * 
	 * <b>post:</b>The clients list is deployed<br>
	 */
	private void showClients() {
		System.out.println("\n***DEPLOYING CLIIENTS LIST***\n");	
		System.out.println(restaurantsManager.showClients());
	}
	/**
	 * This method call a method in restaurants manager and deploy the products list.
	 * <b>pre:</b>A client must be added.<br>
	 * 
	 * <b>post:</b>The restaurants list is deployed<br>
	 */
	private void showProducts() {
		System.out.println("\n***DEPLOYING PRODUCTS LIST***\n");	
		System.out.println(restaurantsManager.showProducts());
	}
	/**
	 * This method call a method in restaurants manager and deploy the orders list.
	 * <b>pre:</b>An order must be added.<br>
	 * 
	 * <b>post:</b>The orders list is deployed<br>
	 */
	private void showOrders() {
		System.out.println("\n***DEPLOYING ORDERS LIST***\n");	
		System.out.println(restaurantsManager.showOrders());
	}

	//----------Deploying Sorting--------

	/**
	 * This method call a method in restaurants manager and deploy the clients list sorted by falling telephone .
	 * <b>pre:</b>A client must be added.<br>
	 * 
	 * <b>post:</b>The clients list sorted by falling telephone is deployed<br>
	 */
	private void showClientsSortedByTelephone() {
		System.out.println("\n***DEPLOYING CLIENTS LIST SORTED BY TELEPHONE***\n");	
		restaurantsManager.SortbyClientTelephoneInsertion();
		System.out.println("After sorting by telephone");
		System.out.println(restaurantsManager.getClients());
		restaurantsManager.sortClientsCorrect();
	}
	//*************************************************************************************


	//--------------Update----------------

	/**
	 * This method update restaurants information, could catch an IOException if an error occur during serializing, could throw WrongNitException if restaurant NIT does not exist.
	 * <b>pre:</b>A restaurant must be added.<br>
	 * 
	 * <b>post:</b>The restaurant selected is updated by the user<br>
	 */
	private void updateRestuarant() throws WrongNitException {
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
			throw new WrongNitException();
		}
	}
	/**
	 * This method update clients information, could catch an IOException if an error occur during serializing, could throw WrongIdException if client ID number does not exist.
	 * <b>pre:</b>A client must be added.<br>
	 * 
	 * <b>post:</b>The client selected is updated by the user<br>
	 */
	private void updateClient() throws WrongIdException {
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
			throw new WrongIdException();
		}
	}
	/**
	 * This method update products information, could catch an IOException if an error occur during serializing, could throw NullCodeException if product code does not exist.
	 * <b>pre:</b>A client must be added.<br>
	 * 
	 * <b>post:</b>The client selected is updated by the user<br>
	 */
	private void updateProduct() throws NullCodeException {
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
			throw new NullCodeException();
		}
	}
	/**
	 * This method update orders information, could catch an IOException if an error occur during serializing, could throw NullCodeException if order code does not exist.
	 * <b>pre:</b>A order must be added.<br>
	 * 
	 * <b>post:</b>The order selected is updated by the user<br>
	 */
	private void updateOrder() throws IOException, NullCodeException {
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
			throw new NullCodeException();
		}

	}


	//Program execute 


	/**
	 *This method close the scanner and then, the program.
	 *<b>pre:</b> <br>
	 *<b>post:</b>The program is closed<br>
	 */
	private void exitProgram() {
		sc.close();
		System.out.println("Good bye!");
	}
	/**
	 *This method read the option selected in main menu.
	 *<b>pre:</b>Program is already running<br>
	 *
	 *@return op
	 *
	 *<b>post:</b>The program is closed<br>
	 */
	private int readOption() {
		int op;
		op = Integer.parseInt(sc.nextLine());
		return op;
	}
	/**
	 * This method start the program, and deserialize program data.
	 * <b>pre:</b><br>
	 * 
	 * <b>post:</b><br>
	 */
	public void startMenu() {
		String menu = getMenu();
		loadProgram();
		int option;
		do {
			System.out.println(menu);
			option = readOption();
			executeOperation(option);

		} while (option!=EXIT_MENU);
	}
}