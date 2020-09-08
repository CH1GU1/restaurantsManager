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

			break;

		case 2:


			break;
		case 3:


			break;
		case 4:


			break;
		case 5:


			break;
		case 6:


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
