import java.io.File;
import java.io.FileNotFoundException;

import java.util.Formatter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

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
 * It creates an object reading user's input and holds utility method called by main menu
 * and numItems to count the number of object created
 */
public class Inventory{
	/**
	 * type ArrayList to hold FoodItem type
	 */
	protected ArrayList<FoodItem> inventory = new ArrayList<>();
	/**
	 * create to store inputCode given from user
	 */
	private FoodItem foodItem;		
	/**
	 * used to store value after alreadyExists()
	 */
	private int searchedCode;			

	/**
	 * Displays searched item when itemCode matched with the itemCode from user input
	 * @param scanner to read itemCode from user to search
	 */
	public void searchForItem(Scanner scanner) {
		foodItem = new FoodItem();
		foodItem.inputCode(scanner, false);			
		searchedCode = alreadyExists(foodItem);
		if (searchedCode == -1) {
			System.out.println("Code not found in inventory...");
		} else {			// when itemCode matched, display its detail
			System.out.println(inventory.get(searchedCode).toString());
		}
	}

	/**
	 * Reads in an itemCode and quantity to update in the inventory array
	 * It ensures valid input is added for the update
	 * @param scanner to read itemCode to update and to read quantity to buy or sell
	 * @param buyOrSell true means buying operation and false means selling operation
	 * @return true if successful, otherwise returns false
	 */
	protected boolean updateQuantity (Scanner scanner, boolean buyOrSell) {
		if (inventory.isEmpty()) 
			return false;
		foodItem = new FoodItem();
		foodItem.inputCode(scanner, false);			
		if (buyOrSell) {			// buying operation
			if (alreadyExists(foodItem) != -1) {			// condition is true, if the inputCode is existing in FoodItem in the array
				searchedCode = alreadyExists(foodItem);			// the method returns the index where inputCode is
				System.out.print("Enter valid quantity to buy: ");
				try {
					int quantity = scanner.nextInt();
					if (quantity > 0) {			// quantity to add must be positive
						inventory.get(searchedCode).updateItem(quantity);
					} else {
						throw new Exception();
					}
				} catch (Exception e) {
					System.out.println("Invalid quantity...");
					scanner.nextLine();
				}
			} else {			// inputCode user entered is not existing
				System.out.println("Code not found in inventory...");
				return false;			// message prompted, back to main menu
			}
		} else {			// selling operation 
			if (alreadyExists(foodItem) != -1) {
				searchedCode = alreadyExists(foodItem);
				System.out.print("Enter valid quantity to sell: ");
				try {
					int quantity = scanner.nextInt();
					if (quantity > inventory.get(searchedCode).itemQuantityInStock) {			// quantity to sell must not be bigger than the stock
						System.out.println("Insufficient stock in inventory...");
					} else if (quantity < inventory.get(searchedCode).itemQuantityInStock && quantity > 0) {
						inventory.get(searchedCode).updateItem(-quantity);			// deduct from itemQuantityInStock
					} else {
						throw new Exception();
					}
				} catch (Exception e) {
					System.out.println("Invalid quantity...");
					scanner.nextLine();
				}
			} else {			// when code does not exist
				System.out.println("Code not found in inventory...");
				return false;			// message prompted, back to main menu
			}
		} return true;
	}

	/**
	 * Read a filename from user input, 
	 * write to the file by calling outputItem() to write in formatted way
	 * @param scanner to read in filename from user
	 */
	public void saveToFile(Scanner scanner) {
		System.out.println("Enter the filename to save to: ");
		String fileName = scanner.nextLine();
		foodItem = new FoodItem();
		if (inventory.isEmpty())
			System.out.println("There's nothing to save.");
		try (Formatter writer = new Formatter(new File(fileName))) {
			for (int i = 0; i < inventory.size(); i++) {
				inventory.get(i).outputItem(writer);
			}
			System.out.println("Inventory data saved to " + fileName);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * Read a filename from user input to read the file
	 * and insert them into current inventory
	 * @param scanner to read in filename from user
	 */
	public void readFromFile(Scanner scanner) {
		System.out.println("Enter the filename to read from: ");
		String fileName = scanner.nextLine();
		foodItem = new FoodItem();
		try {
			Scanner reader = new Scanner(new File(fileName));
			while (reader.hasNextLine()) {			// loop while there's a value at next line
				if (!addItem(reader, true))			// read value per line
					break;			// abort when itemcode already existed while reading from file
			}
			reader.close();			// close Scanner 
		} catch (FileNotFoundException e) {
			System.out.println("File Not Found, ignoring...");			// prompt message if there's no matching filename
		}
	}

	/**
	 * Converts all data members of all FoodItem type in the array into String
	 * @return String type of all data members of all FoodItem type in the array
	 */
	public String toString() {
		StringBuilder sb = new StringBuilder("Inventory:\n");
		for (int i = 0; i < inventory.size(); i++) {
			sb.append(inventory.get(i).toString() + "\n");
		}
		return sb.toString();
	}

	/**
	 * Reads in an FoodItem
	 * to check passed itemCode of the item is existing in any FoodItem object in inventory
	 * utilizing iterative binary search way.
	 * @param item FoodItem type that will be compared to
	 * @return index of a FoodItem in the inventory array or return -1
	 */
	protected int alreadyExists(FoodItem item) {
		int key = item.getItemCode();			// key is the itemCode to search for
		int lastIndex = inventory.size() - 1;
		int firstIndex = 0;

		while (firstIndex <= lastIndex) {
			int mid = firstIndex + (lastIndex - firstIndex) / 2;			// initialize mid index as result of computation followed below
			if (key == inventory.get(mid).getItemCode()) {				// when the key is equal to the value in the array
				return mid;								// it returns its index value
			} 
			else if (key > inventory.get(mid).getItemCode() ) {			// if key is in right hand than mid index value
				firstIndex = mid + 1;						// re-assign start value to compute next round
			}
			else {
				lastIndex = mid - 1;							// re-assign end value to compute next round
			}
		}
		return -1;
	}

	/**
	 * Reads in user input to create desired type of FoodItem
	 * to add in the inventory array.
	 * once object is created it adds values to its data members in sorted way.
	 * if reading from file then execute without prompts
	 * @param scanner to read in user input for FoodItem type and its values of data members
	 * @param fromFile to distinct reading from a file or not
	 * @return true if successful, otherwise returns false
	 */
	protected boolean addItem(Scanner scanner, boolean fromFile) {
		if (fromFile == false) {
			System.out.print("Do you wish to add a fruit(f), vegetable(v) or a preserve(p)? ");
			switch (scanner.nextLine()) {
			case "f":
				foodItem = new Fruit();
				break;
			case "v":
				foodItem = new Vegetable();
				break;
			case "p":
				foodItem = new Preserve();
				break;
			default:
				System.out.println("Invalid entry");
				addItem(scanner, false);
			}
			foodItem.inputCode(scanner, false);
			if ((alreadyExists(foodItem)) != -1) {
				System.out.println("Item code already exists");
				return false;
			}
			inventory.add(foodItem);			// add into inventory
			inventory.get(inventory.size() - 1).addItem(scanner, false);
			Collections.sort(inventory);			// sort out data members in inventory by itemCode
			return true;
		} else {			// reading from a file
			switch (scanner.nextLine()) {
			case "f":
				foodItem = new Fruit();
				break;
			case "v":
				foodItem = new Vegetable();
				break;
			case "p":
				foodItem = new Preserve();
				break;
			}
			foodItem.inputCode(scanner, true);
			if ((alreadyExists(foodItem)) != -1) {
				System.out.println("Item Code already exists");
				System.out.println("Error encountered while reading the file, aborting...");
				return false;			// terminating to read from a file
			}
			inventory.add(foodItem);
			inventory.get(inventory.size() - 1).addItem(scanner, true);
			Collections.sort(inventory);
			return true;
		}
	}
}


