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
 * This is a parent class declared all base data members and base method
 * shared by Fruit, Preserve, and Vegetable classes.
 * Methods are to display, update, add to the data members.
 */
public class FoodItem {
	protected int itemCode;
	protected String itemName;
	protected int itemQuantityInStock;
	protected float itemCost;
	protected float itemPrice;
	
	/**
	 * This overrides toString method to display all data members in the class
	 */
	@Override
	public String toString() {
		return String.format("Item: %d %s Quantity: %d Price: $%.2f Cost: $%.2f", itemCode, itemName, itemQuantityInStock, itemPrice, itemCost);
	}

	/**
	 * Update itemQuantityInStock field by amount which takes either positive or negative
	 * @param amount integer to add or deduct from itemQuantityInStock
	 * @return true if successful, otherwise returns false
	 */
	protected boolean updateItem(int amount) {
		itemQuantityInStock += amount;
		return true;
	}
	
	/**
	 * It takes item to compare its itemCode to itemCode in the class
	 * @param item FoodItem type to compare its given itemCode
	 * @return true if successful, otherwise returns false
	 */
	protected boolean isEqual(FoodItem item) {
		if (itemCode == item.itemCode)			// if passed itemCode of item is same with existing itemCode
			return true;			// returns true
		return false;
	}
	/**
	 * Reads from the Scanner object passed in 
	 * and assigned to the data members of the class with valid data
	 * @param scanner to read in user input for value of data members
	 * @return true if successful, otherwise returns false
	 */
	protected boolean addItem(Scanner scanner) {
		boolean addItem = false;			// set up for while loop
		while (!addItem) {			// iterate until addItem is successful
			System.out.print("Enter the name for the item: ");
			itemName = scanner.nextLine();
			while (true) {
				System.out.print("Enter the quantity for the item: ");
				try {
					if (scanner.hasNextInt()) {
						itemQuantityInStock = scanner.nextInt();
						if (itemQuantityInStock > 0)			// quantity to add needs to be positive
							break;			// terminate the current loop for quantity
						else 
							System.out.println("Invalid entry");			// when input is negative
					} else {
						throw new Exception();			// when input is not an integer
					}
				} catch (Exception e) {
					System.out.println("Invalid entry");
				} 	scanner.nextLine();			// retrieve invalid entry
			}
			while (true) {
				System.out.print("Enter the cost of the item: ");
				try {
					if (scanner.hasNextFloat()) {
						itemCost = scanner.nextFloat();
						if (itemCost > 0)			// cost needs to be positive
							break;
						else 
							System.out.println("Invalid entry");			// when input is negative
							scanner.nextLine();
					} else {
						throw new Exception();			// when input is not float
					}
				} catch (Exception e) {
					System.out.println("Invalid entry");
					scanner.nextLine();			// retrieve invalid entry
				} 
			}
			while (true) {
				System.out.print("Enter the sales price of the item: ");
				try {
					if (scanner.hasNextFloat()) {
						itemPrice = scanner.nextFloat();
						if (itemPrice > 0)			// price needs to be positive
							break;
						else
							System.out.println("Invalid entry");			// when input is negative
							scanner.nextLine();
					} else {
						throw new Exception();			// retrieve invalid entry
					}
				}  catch (Exception e) {
					System.out.println("Invalid entry");
					scanner.nextLine();			// retrieve invalid entry
				} 
			} addItem = true;			// all item details are valid; set addItem to true to exit the loop
		}			
		scanner.nextLine();
		return addItem;			// returns true; addItem successful
	}
	
	/**
	 * Reads a itemCode to check validation and loops until valid entry entered
	 * @param scanner to read itemCode
	 * @return true if successful, otherwise returns false
	 */
	protected boolean inputCode(Scanner scanner) {
		System.out.print("Enter the code for the item: ");
		try {
			itemCode = scanner.nextInt();			// reads in input as itemCode
			scanner.nextLine();
		} catch (Exception e) {
			System.out.println("Invalid entry");
			scanner.nextLine();			// retrieve invalid entry
			inputCode(scanner);			// loops recursively when invalid
		}
		return true;			// itemCode successfully added to the data member field
	}
}
