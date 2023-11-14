package BinLinSearch;
import java.util.Arrays;

/**
 * CET - CS Academic Level 3
 * Declaration: I declare that this is my own original work and is free from Plagiarism
 * This class contains driver class to test BinaryLinearSearch class
 * Student Name: Eunha Sim
 * Student Number: 041-078-020
 * Section #: 301
 * Course: CST8130 - Data Structures
 * Professor: James Mwangi PhD. 
  */

/**
 * Driver class to display menu options and test search methods
 */
public class Lab3BinLinSearchTest {

	/**
	 * Initializes BinaryLinearSearch class and display menu options
	 * from user to choose, handles input mismatch exceptions, and 
	 * the loop terminates when user enters 4
	 * 
	 * @param args
	 */
	public static void main(String[] args) {
		int menu = 0;
		BinaryLinearSearch binLinSearch = new BinaryLinearSearch();
		int[][] generatedArr = null;
		int[] unsortedArr = null;			// to store an unsorted array from generatedArr
		int[] sortedArr = null;				// to store an sorted array from generatedArr
		int searchKey = 0;
		int result = 0;
		long preN = 0, preM = 0;
		
		while (menu != 4) {			// loop terminates when user enters 4
			System.out.println("Select your option in the menu below");
			System.out.println("1. Initialize and populate an array of 32 random integers.");
			System.out.println("2. Perform a recursive binary and linear search.");
			System.out.println("3. Perform iterative binary and linear search.");
			System.out.println("4. Exit.");
			try {
				menu = binLinSearch.sc.nextInt();
				if (menu > 4 || menu < 1) {
					System.out.println("Please enter from 1 to 4.");
					continue;			// back to menu options
				}
			} catch (Exception e) {
				System.out.println("*****Input Mismatch Ecepion*****");
				binLinSearch.sc.nextLine();
				continue;			// back to menu options
			}
			switch (menu) {
			case 1:
				generatedArr = binLinSearch.generateRandomInts();			// generates an array of size 32
				System.out.println("Array of randomly generated integers: ");
				System.out.print("Unsorted array: ");
				System.out.println(Arrays.toString(generatedArr[0]));
				unsortedArr = generatedArr[0];
				System.out.print("Sorted array: ");
				sortedArr = generatedArr[1];
				System.out.println(Arrays.toString(generatedArr[1]) + "\n");
				break;
			case 2:
				System.out.print("Please enter an integer value to search: ");
				try {
					searchKey = binLinSearch.sc.nextInt();
					if (searchKey > 100 || searchKey < 10) {
						System.out.println("Please enter a number bigger than 10 and smaller than 100.\n");
						break;
					}
				} catch (Exception e) {
					System.out.println("*****Input Mismatch Ecepion*****\n");
					binLinSearch.sc.nextLine();			// retrieve wrong input
					break;
				}
				preN = System.nanoTime();					// compute time before the search
				preM =System.currentTimeMillis();			// compute time before the search
				result = binLinSearch.recursiveBinarySearch(sortedArr, 0, sortedArr.length - 1, searchKey);
				binLinSearch.toString(result, searchKey, "Recursive Binary ");
				binLinSearch.timeTaken(preN, preM);			// calls timeTaken to compute time after the search and print the result
				preN = System.nanoTime();
				preM =System.currentTimeMillis();
				result = binLinSearch.recursiveLinearSearch(unsortedArr, unsortedArr.length - 1, searchKey);
				binLinSearch.toString(result, searchKey, "Recursive Linear ");
				binLinSearch.timeTaken(preN, preM);
				break;
			case 3:
				System.out.print("Please enter an integer value to search: ");
				try {
					searchKey = binLinSearch.sc.nextInt();			// read user input
					if (searchKey > 100 || searchKey < 10) {
						System.out.println("Please enter a number bigger than 10 and smaller than 100.\n");
						break;
					}
				} catch (Exception e) {
					System.out.println("Please enter an integer.\n");
					binLinSearch.sc.nextLine();			// retrieve wrong input
					continue;
				}
				preN = System.nanoTime();					// compute time before the search
				preM =System.currentTimeMillis();			// compute time before the search
				result = binLinSearch.iterativeBinarySearch(sortedArr, searchKey);
				binLinSearch.toString(result, searchKey, "Iterative Binary ");
				binLinSearch.timeTaken(preN, preM); 	// calls timeTaken to compute time after the search and print the result
				preN = System.nanoTime();
				preM =System.currentTimeMillis();
				result = binLinSearch.iterativeLinearSearch(unsortedArr, searchKey);
				binLinSearch.toString(result, searchKey, "Iterative Linear ");
				binLinSearch.timeTaken(preN, preM);
				break;
			case 4:
				System.out.println("Exiting...");			// terminates the loop
				break;
			}
		}
	}

}
