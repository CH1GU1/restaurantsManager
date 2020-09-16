package ui;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;
import model.RestaurantsManager;


public class Menu {
	private Scanner sc;
	private RestaurantsManager restaurantsManager = new RestaurantsManager();
	final static int EXIT_MENU = 11;


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
			showRestaurants();

			break;
		case 5:
			showProducts();

			break;
		case 6:
			showClients();

			break;
		case 7:
			showClientsSorted();

			break;
		case 8:
			exportRestaurants();

			break;
		case 9:
			updateRestuarant();

			break;
		case 10:
			loadRestaurants();

			break;
		case 11:
			exitProgram();

			break;
		default:

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
		menu += "4. Show restaurants list\n";
		menu += "5. Show products list\n";
		menu += "6. Show clients list\n";
		menu += "7. Show clients list sorted by telephone\n";
		menu += "8. Export restaurants list\n";
		menu += "9. Update restaurants\n";
		menu += "10. Load program data\n";
		menu += "11. Exit\n";
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
	private void loadRestaurants() {
		System.out.println("Loading data ...");
		try{
			restaurantsManager.loadData("rest");
			restaurantsManager.loadData("client");
			restaurantsManager.loadData("products");
			System.out.println("The restaurants were loaded succesfully");
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
		System.out.println("Please enter the product inforestaurantsManageration: ");
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
	private void addOrder() {
		int choose = 0;
		if(!restaurantsManager.clients.isEmpty()&&!restaurantsManager.restaurants.isEmpty()&&restaurantsManager.products.isEmpty()) {
			System.out.println("ADDING ORDER\nEntry the client ID number\n");
			showClients();
			String idNum = sc.nextLine();
			System.out.println("Entry the restaurant NIT");
			showRestaurants();
			String restNit = sc.nextLine();
			if(!restaurantsManager.uniqueClientId(idNum) && !restaurantsManager.uniqueRestaurantNit(restNit)) {
				int requestedClient = restaurantsManager.searchClientId(idNum);
				int requestedRestaurant = restaurantsManager.searchRestaurantNit(restNit);
				restaurantsManager.addOrder(restaurantsManager.getClients().get(requestedClient).getIdNum(), restaurantsManager.getRestaurants().get(requestedRestaurant).getNit());
				System.out.println("Enter the code of product for add to order");
				restaurantsManager.searchProductByRestaurant(restNit);
				//HASTA AQUI VOY, HAY QUE IMPLEMENTAR EL INGRESO DEL PRODUCTO
				System.out.println(restaurantsManager.restaurants.get(choose-1).showProducts());
				choose3 = Integer.parseInt(sc.nextLine());

				String name = restaurantsManager.restaurants.get(choose-1).products.get(choose3-1).getName();
				String code = restaurantsManager.restaurants.get(choose-1).products.get(choose3-1).getCode();
				String description = restaurantsManager.restaurants.get(choose-1).products.get(choose3-1).getDescription();
				double cost = restaurantsManager.restaurants.get(choose-1).products.get(choose3-1).getCost();
				System.out.println("Please enter the quantity of these product you need");
				int quantity = Integer.parseInt(sc.nextLine());
				restaurantsManager.orders.get(choose2-1).addProducts(name, code, description, cost, restaurantNit, quantity);
				System.out.println("Some product more?\n1. Yes\n2. No");
				int decision = Integer.parseInt(sc.nextLine());
				if(decision == 1)
					finished = false;
				else if(decision == 2)
					finished = true;

			}
		}
		else
			System.out.println("We have no clients or restaurants or products registered yet");
	}
	//Export
	private void exportRestaurants() {
		try{
			restaurantsManager.exportData();
			System.out.println("The data was exported succesfully");
		}catch(IOException fnfe){
			System.out.println("The data can't be export");
		}
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

	//Deploying Sorting
	private void showClientsSorted() {
		System.out.println("\n***DEPLOYING CLIIENTS LIST SORTED BY TELEPHONE***\n");	
		System.out.println(restaurantsManager.showClientsSorted());
	}


	//Update
	private void updateRestuarant() {
		System.out.println("UPDATING RESTAURANT");
		showRestaurants();
		System.out.println("Please enter the restaurant NIT to be update: ");
		String restNit = sc.nextLine();
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
			restaurantsManager.getRestaurants().get(requested).setNit(nit);
			System.out.println("Saving data ...");
			try{
				restaurantsManager.saveData("rest");
				System.out.println("The restaurant was updated succesfully");
			}catch(IOException ioe){
				System.out.println("The data can't be updated");
			}
			break;
		default:
			System.out.println("Select a correct option");
			break;
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
