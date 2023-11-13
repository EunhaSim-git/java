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
 * A child class extends FoodItem 
 * to add its own data member; farmName
 */
public class Vegetable extends FoodItem{
	/**
	 * distinct data member for Vegetable
	 */
	private String farmName;			

	/**
	 * Calls FoodItem class(parent class) and append its string to display all data members in the class
	 * @return value of all data members
	 */
	@Override
	public String toString() {
		String s = String.format(" farm supplier: %s", farmName);
		return super.toString() + s;
	}

	/**
	 * Calls FoodItem class(parent class) and reads in an input to add to its data member; farmName
	 * if reading from file then execute without prompts
	 * @param scanner to read in user input for each value for data member
	 * @param fromFile to distinct reading from a file or not
	 * @return true if successful, otherwise returns false
	 */
	@Override
	public boolean addItem(Scanner scanner, boolean fromFile) {
		if (fromFile == false) {
			if (super.addItem(scanner, false))
				System.out.print("Enter the name of the farm supplier: ");
			farmName = scanner.nextLine();
			return true;
		} else {
			super.addItem(scanner, true);
			farmName = scanner.nextLine();
		}
		return true;
	}

	/**
	 * calls parent class to write to 'writer' then append formatted string; farmName
	 * @param writer type Formatter, file to write
	 */
	@Override
	public void outputItem(Formatter writer) {
		super.outputItem(writer);
		writer.format("%s\n", farmName);
	}
}