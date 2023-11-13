import java.util.Formatter;
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
 * This is a parent class declared all base data members and base method
 * shared by Fruit, Preserve, and Vegetable classes.
 * It implements Comparable interface to sort out data members by itemCode once it's added.
 * Methods are to display, update, add to the data members.
 */
public class FoodItem implements Comparable<FoodItem>{
	/**
	 * used for searching, sorting
	 */
	private int itemCode;
	/**
	 * can accept any input
	 */
	private String itemName;
	/**
	 * used to record the quantity for selling and buying
	 */
	protected int itemQuantityInStock;		
	/**
	 * display up to two decimal places
	 */
	private float itemCost;
	/**
	 * display up to two decimal places
	 */
	private float itemPrice;

	/**
	 * Method from Comparable interface, used to sort data members by itemCode
	 * @param o instance of FoodItem to compare to
	 * @return int type int value to sort itemCode in ascending order
	 */
	@Override
	public int compareTo(FoodItem o) {
		return Integer.compare(this.itemCode, o.itemCode);
	}

	/**
	 * Gets the instance representing itemCode
	 * @return itemCode
	 */
	public int getItemCode() {
		return this.itemCode;
	}

	/**
	 * To format all data members in a desired way
	 * @param writer file to write to
	 */
	public void outputItem(Formatter writer) {
		String itemType = "";			// set up to check the type of instance
		if (this instanceof Fruit)
			itemType = "f";
		else if (this instanceof Vegetable)
			itemType = "v";
		else if (this instanceof Preserve)
			itemType = "p";
		writer.format("%s\n%d\n%s\n%d\n%.2f\n%.2f\n", 
				itemType, itemCode, itemName, itemQuantityInStock, itemPrice, itemCost);
	}

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
	 * Reads from the Scanner object passed in 
	 * and assigned to the data members of the class with valid data.
	 * if reading from file then execute without prompts.
	 * @param scanner to read in user input for value of data members
	 * @param fromFile to distinct reading from a file or not
	 * @return true if successful, otherwise returns false
	 */
	protected boolean addItem(Scanner scanner, boolean fromFile) {
		if (fromFile == false) {
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
		} else {			// reading from a file
			itemName = scanner.nextLine();
			itemQuantityInStock = scanner.nextInt();
			itemCost = scanner.nextFloat();
			itemPrice = scanner.nextFloat();
			scanner.nextLine();			// retrieve /n after itemPrice
			return true;
		}
	}

	/**
	 * Reads a itemCode to check validation and loops until valid entry entered.
	 * if reading from file then execute without prompts.
	 * @param scanner to read itemCode
	 * @param fromFile to distinct reading from a file or not
	 * @return true if successful, otherwise returns false
	 */
	protected boolean inputCode(Scanner scanner, boolean fromFile) {
		if (fromFile == false) {
			boolean isValidInput = false;
			while (!isValidInput) {
				System.out.print("Enter the code for the item: ");
				try {
					itemCode = scanner.nextInt();			// reads in input as itemCode
					scanner.nextLine();
					isValidInput = true;
				} catch (Exception e) {
					System.out.println("Invalid entry");
					scanner.nextLine();			// retrieve invalid entry
				}
			}
			return true;			// itemCode successfully added to the data member field
		}
		else {			// reading from a file
			itemCode = scanner.nextInt();
			scanner.nextLine();			// retrieve \n after itemCode
			return true;
		}
	}
}
