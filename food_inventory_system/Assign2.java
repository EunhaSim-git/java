import java.util.Scanner;
import java.util.InputMismatchException;

/**
 * CET - CS Academic Level 3
 * Declaration: I declare that this is my own original work and is free from Plagiarism
 * This class contains driver class to test Inventory application
 * Assignment #2
 * Student Name: Eunha Sim
 * Student Number: 041-078-020
 * Section #: 301
 * Course: CST8130 - Data Structures
 * Professor: James Mwangi PhD. 
 */

/**
 * Driver class to display main menu and test Inventory application
 */
public class Assign2 {

	/**
	 * Initializes Inventory class and display main menu
	 * It handles InputMisMatchException iterating until user exits with input 8
	 * @param args void
	 */
	public static void main(String[] args) {
		int menu = 0;
		Inventory inventory = new Inventory();
		Scanner scanner = new Scanner(System.in);			// scanner method to share with other classes
		while (menu != 8) {			// iterates until 5 entered
			displayMenu();			// call helper method to display main menu
			try {			// exception handling
				menu = scanner.nextInt();			// reads in an int type user input 
				scanner.nextLine();			// retrieve previous value
				switch (menu) {			// pass user input to switch condition
				case 1:
					inventory.addItem(scanner, false);			// takes user input to add details for FoodItem
					break;
				case 2:
					System.out.println(inventory.toString());			// display all successful data for inventory item
					break;
				case 3: 			// buying operation passing true
					if (!(inventory.updateQuantity(scanner, true)))			// if updateQuantity method returns false 
						System.out.println("Error...could not buy item");			// it displays the message
					break;
				case 4: 			// selling operation passing 
					if (!(inventory.updateQuantity(scanner, false)))			// if updateQuantity method returns false 
						System.out.println("Error...could not sell item");			// it displays the message
					break;
				case 5:
					inventory.searchForItem(scanner);
					break;
				case 6:
					inventory.saveToFile(scanner);
					break;
				case 7:
					inventory.readFromFile(scanner);
					break;
				case 8: 
					System.out.println("Exiting...");
					break;
				default:			// if input number is not between 1-8
					System.out.println("Incorrect value entered");			// displays the message
				}
			}  catch (InputMismatchException e) {			// if input is not an int
				System.out.println("Incorrect value entered");
				scanner.nextLine();			// retrieve invalid input
			}
		}scanner.close();			// close scanner method
	}

	/**
	 * Helper method to display main menu
	 */
	public static void displayMenu() {
		System.out.println("Please select one of the following: ");
		System.out.println("1: Add Item to Inventory");
		System.out.println("2: Display Curent Inventory");
		System.out.println("3: Buy Item(s)");
		System.out.println("4: Sell Item(s)");
		System.out.println("5: Serch for Item");
		System.out.println("6: Save Inventory to File");
		System.out.println("7: Read Inventory from File");
		System.out.println("8: To Exit");
		System.out.print("> ");
	}
}
