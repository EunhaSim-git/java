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
 * A child class extends FoodItem 
 * to add its own data member; farmName
 */
public class Vegetable extends FoodItem{
	private String farmName;
	
	/**
	 * Calls FoodItem class(parent class) and append its string to display all data members in the class
	 * @return value of all data members
	 */
	public String toString() {
		String s = String.format(" farm supplier: %s", farmName);
		return super.toString() + s;
	}
	
	/**
	 * Calls FoodItem class(parent class) and reads in an input to add to its data member; farmName
	 * @param scanner to read in user input for value of data member
	 * @return true if successful, otherwise returns false
	 */
	protected boolean addItem(Scanner scanner) {
		if (super.addItem(scanner))
			System.out.print("Enter the name of the farm supplier: ");
		farmName = scanner.nextLine();
		return true;

	}

}
