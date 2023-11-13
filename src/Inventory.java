import java.util.Scanner;

/**
 * CET - CS Academic Level 3
 * Declaration: I declare that this is my own original work and is free from Plagiarism
 * This class contains driver class to test Inventory application
 * Assignment #1
 * Student Name: Eunha Sim
 * Student Number: 041-078-020
 * Section #: 301
 * Course: CST8130 - Data Structures
 * Professor: James Mwangi PhD. 
  */

/**
 * It creates an object reading user's input and holds utility method called by main menu
 * It holds an array of FoodItem type with maximum size 20 
 * and numItems to count the number of object created
 */
public class Inventory {
	protected FoodItem[] inventory = new FoodItem[20];
	private int numItems = 0;

	/**
	 * Converts all data members of all FoodItem type in the array into String
	 * @return String type of all data members of all FoodItem type in the array
	 */
	public String toString() {
		StringBuilder sb = new StringBuilder("Inventory:\n\n");
		for (int i = 0; i < numItems; i++) {
			sb.append(inventory[i].toString() + "\n");
		}
		return sb.toString();
	}
	
	/**
	 * Reads in an FoodItem to pass to isEqual method
	 * to check passed itemCode of the item is existing in any FoodItem object in the array
	 * @param item FoodItem type that will be compared to
	 * @return index of a FoodItem in the inventory array or return -1
	 */
	protected int alreadyExists(FoodItem item) {
		for (int i = 0; i < numItems; i++) {		// iterate through all FoodItem object
			if (inventory[i].isEqual(item))			// calls isEqual method to compare whether same or not
				return i;
		}
		return -1;
	}
	
	/**
	 * Reads in user input to create desired type of FoodItem
	 * to add in the inventory array
	 * once object is created it adds values to its data members
	 * @param scanner to read in user input for FoodItem type and its values of data members
	 * @return true if successful, otherwise returns false
	 */
	protected boolean addItem(Scanner scanner) {
		System.out.print("Do you wish to add a fruit(f), vegetable(v) or a preserve(p)?");
		switch (scanner.nextLine()) {
		case "f":
			inventory[numItems] = new Fruit();
			break;
		case "v":
			inventory[numItems] = new Vegetable();
			break;
		case "p":
			inventory[numItems] = new Preserve();
			break;
		default:
			System.out.println("Invalid entry");
			addItem(scanner);
		}
		inventory[numItems].inputCode(scanner);
		if ((alreadyExists(inventory[numItems])) != -1) {
			System.out.println("Item code already exists");
			return false;
		}
		inventory[numItems].addItem(scanner);
		numItems++;
		return true;
	}
	
	/**
	 * Reads in an itemCode and quantity to update in the inventory array
	 * It ensures valid input is added for the update
	 * @param scanner to read itemCode to update and to read quantity to buy or sell
	 * @param buyOrSell true means buying operation and false means selling operation
	 * @return true if successful, otherwise returns false
	 */
	protected boolean updateQuantity (Scanner scanner, boolean buyOrSell) {
		if (inventory[0] == null) 
			return false;
		FoodItem fi = new FoodItem();			// create to store inputCode given from user
		fi.inputCode(scanner);			
		int index;
		if (buyOrSell) {			// buying operation
			if (alreadyExists(fi) != -1) {			// condition is true, if the inputCode is existing in FoodItem in the array
				index = alreadyExists(fi);			// the method returns the index where inputCode is
				System.out.print("Enter valid quantity to buy: ");
				try {
					int quantity = scanner.nextInt();
					if (quantity > 0) {			// quantity to add must be positive
						inventory[index].updateItem(quantity);
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
			if (alreadyExists(fi) != -1) {
				index = alreadyExists(fi);
				System.out.print("Enter valid quantity to sell: ");
				try {
					int quantity = scanner.nextInt();
					if (quantity > inventory[index].itemQuantityInStock) {			// quantity to sell must not be bigger than the stock
						System.out.println("Insufficient stock in inventory...");
					} else if (quantity < inventory[index].itemQuantityInStock && quantity > 0) {
						inventory[index].updateItem(-quantity);			// deduct from itemQuantityInStock
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
}


