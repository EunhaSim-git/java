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
 * to add its own data member; jarSize
 */
public class Preserve extends FoodItem{
	private int jarSize;
	
	/**
	 * Calls FoodItem class(parent class) and append its string to display all data members in the class
	 * @return value of all data members
	 */
	public String toString() {
		String s = String.format(" size: %sml", jarSize);
		return super.toString() + s;
	}
	
	/**
	 * Calls FoodItem class(parent class) and reads in an input to add to its data member; jarSize
	 * @param scanner to read in user input for value of data member
	 * @return true if successful, otherwise returns false
	 */
	protected boolean addItem(Scanner scanner) {
		if (super.addItem(scanner)) {
			while (true) {
				System.out.print("Enter the size of the jar in millilitres: ");
				try {
					if (scanner.hasNextInt()) {
						jarSize = scanner.nextInt();
						if (jarSize > 0)			// check if positive
							break;				// then add the input to data member
						else
							System.out.println("Invalid entry");			// input was negative
					} else {
						throw new Exception();			// invalid input
					}
				} catch (Exception e) {
					System.out.println("Invalid entry");
				}
				scanner.nextLine();			// retrieve invalid input
			}
		}
		return true;
	}
}

