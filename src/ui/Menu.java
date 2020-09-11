package ui;

import java.util.Scanner;
import model.RestaurantsManager;


public class Menu {
	private Scanner sc;
	private RestaurantsManager restaurantsManager = new RestaurantsManager();
	final static int EXIT_MENU = 7;


	public Menu() {
		sc = new Scanner(System.in);

	}


	private void executeOperation(int option) {
		switch (option) {
		case 1:
			addRestaurant();

			break;

		case 2:


			break;
		case 3:
			addClient();

			break;
		case 4:
			showRestaurants();

			break;
		case 5:


			break;
		case 6:
			showClients();

			break;
		case 7:
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
		menu += "7. Exit\n";
		menu += "Please enter an option\n";
		return menu;
	}
	private void addRestaurant() {
		System.out.println("ADDING RESTAURANT");
		System.out.println("Please enter the restaurant name: ");
		String name = sc.nextLine();
		System.out.println("Please enter the restaurant NIT: ");
		String nit = sc.nextLine();
		System.out.println("Please enter the restaurant manager name: ");
		String manager = sc.nextLine();
		System.out.println(restaurantsManager.addRestaurant(name, nit, manager));
	}
	private void showRestaurants() {
		System.out.println("\n***DEPLOYING RESTAURANTS LIST***\n");
		System.out.println(restaurantsManager.showRestaurants());
	}
	private void showClients() {
		System.out.println("\n***DEPLOYING CLIIENTS LIST***\n");	
		System.out.println(restaurantsManager.showClients());
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
		System.out.println(restaurantsManager.addClient(name, lastName, idNum, choice,tel,adress));
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
