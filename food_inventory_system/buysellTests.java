import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.Scanner;

import org.junit.Assert;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

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
 * Junit tests to test buying operation and selling operation 
 * by testing its successful update
 */
class buysellTests {
	private Inventory inventory;
	private ByteArrayInputStream originalIn;
	private ByteArrayOutputStream addItemOutput;
	private PrintStream originalOut;
	private String updateQuantityInput;
	private boolean result;

	/**
	 * To set up addItem method
	 * in order to fill in an FoodItem type of object of all data members 
	 */
	@BeforeEach
	void setUp() {
		inventory = new Inventory();
		originalIn = new ByteArrayInputStream(System.in.toString().getBytes());
		originalOut = System.out;			// copy the original System.in and System.out
		String addItemInput = "f\n111\nApple\n10\n2.5\n3.5\nOrchard\n";			// set up input for addItem method
		System.setIn(new ByteArrayInputStream(addItemInput.getBytes()));
		addItemOutput = new ByteArrayOutputStream();
		System.setOut(new PrintStream(addItemOutput));
		inventory.addItem(new Scanner(System.in));			// call addItem method
		System.setIn(originalIn);			// reset the original System.in and System.out
		System.setOut(originalOut);
	}

	/**
	 * Test buying operation
	 * if user buys 10 then it adds 10 to itemQuantityInStock in an Fruit object with itemCode 111
	 */
	@Test
	void buying10QuantityUpdateTest() {
		updateQuantityInput = "111\n10\n";			// set up input for updateQuantityInput method
		System.setIn(new ByteArrayInputStream(updateQuantityInput.getBytes()));
		ByteArrayOutputStream updateQuantityOutput = new ByteArrayOutputStream();
		System.setOut(new PrintStream(updateQuantityOutput));

		result = inventory.updateQuantity(new Scanner(System.in), true);			// call updateQuantity method
		System.setIn(originalIn);			// reset the original System.in and System.out
		System.setOut(originalOut);
		Assert.assertTrue(result);
	}
	
	/**
	 * Test selling operation
	 * if user sells 5 then it deducts 10 from itemQuantityInStock in an Fruit object with itemCode 111
	 */
	@Test
	void selling5QuantityUpdateTest() {
		updateQuantityInput = "111\n5\n";			// set up input for updateQuantityInput method
		System.setIn(new ByteArrayInputStream(updateQuantityInput.getBytes()));
		ByteArrayOutputStream updateQuantityOutput = new ByteArrayOutputStream();
		System.setOut(new PrintStream(updateQuantityOutput));

		result = inventory.updateQuantity(new Scanner(System.in), false);			// call updateQuantity method
		System.setIn(originalIn);			// reset the original System.in and System.out
		System.setOut(originalOut);
		Assert.assertTrue(result);
	}
}
