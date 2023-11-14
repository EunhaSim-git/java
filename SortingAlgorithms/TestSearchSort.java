/**
 * CET - CS Academic Level 3
 * Declaration: I declare that this is my own original work and is free from Plagiarism
 * This class contains driver class to test Searching class and SortingAlgorithms class.
 * Lab number: 4
 * Student Name: Eunha Sim
 * Student Number: 041-078-020
 * Section #: 301
 * Course: CST8130 - Data Structures
 * Professor: James Mwangi PhD. 
 */

import java.security.SecureRandom;
import java.util.Arrays;
import java.util.InputMismatchException;
import java.util.Scanner;

/**
 * The test class to display main menu and mark the entry point into the application.
 * It consists of utility methods to compute the time taken by the search algorithms and the sorting algorithms
 * with two built-in nanoTime() and currentTimeMillis(),
 * to generate a random array, to display main and sub menus to test the application.
 * @param args void
 */
public class TestSearchSort{
	/**
	 * Initializes SortingAlgorithms class and display main menu
	 * It handles InputMisMatchException iterating until user exits with input 5
	 * @param args void
	 */
	public static void main(String[] args) {
		long startN, startM;			// set up for timeTaken() method
		int randArrSize = 1000, randArrStart = 120, randArrEnd = 1000;			// variables for genRandomInts()

		Scanner sc = new Scanner(System.in);			// read in inputs through main and sub menus
		SortingAlgorithms<Integer> sortingAlgorithms = null;			// declare a reference variable of type Integer 
		Integer[] randomArr = null;			// to store an array from genRandomInts()
		Searching searching = new Searching();
		Integer[] sortedArr = null;			// to store the sorted array for searching algorithms
		int menu = 0;
		while (menu != 5) {			// loops until user enter 5 to exit the main menu
			int key = 0, keyIndex = 0;			// set up for searching algorithms
			displayMain();
			try {
				menu = sc.nextInt();
			} catch (InputMismatchException e) {			// exception handling
				System.out.println("You must enter an integer value...\n");
				sc.nextLine();			// retrieve invalid input
				continue;			// skip below and back to main menu
			}
			sc.nextLine();						// retrieve \n after integer value
			switch(menu) {
			case 1:
				sortingAlgorithms = new SortingAlgorithms<>(genRandomInts(randArrSize, randArrStart, randArrEnd));
				randomArr = sortingAlgorithms.randomArr;
				System.out.println(Arrays.toString(randomArr));
				sortedArr = Arrays.copyOf(randomArr, randomArr.length);			// create a copy of the randomArr
				Arrays.sort(sortedArr);			// sort the randomArr
				break;
			case 2:
				if (randomArr == null) {			// prompt messages when no array initialized
					System.out.println("No array to search");
					System.out.println("Please initialize an array first\n");
					break;
				}
				System.out.println(Arrays.toString(sortedArr));			// displaying the sorted array
				System.out.print("Please enter an integer value to search: ");
				while (key == 0) {
					try {
						key = sc.nextInt();
					} catch (InputMismatchException e){
						System.out.print("Invalid input, please enter an integer value to search: ");
						sc.nextLine();			// retrieve invalid input
						continue;			// skip below and back to read in a key to search again
					}
					if (key < 120 || key >= 1000) {
						System.out.print("Please enter an integer value to search from 120 to 1000: ");
						sc.nextLine();			// retrieve invalid input
						key = 0;			// to read in a valid key again
					}
				}
				startN = System.nanoTime();
				startM = System.currentTimeMillis();
				keyIndex = searching.binarySearch(sortedArr, 0, sortedArr.length-1, key);			// recursive binary search
				if (keyIndex == -1) {
					System.out.printf("\n%d was not found\nReturning to the main menu\n\n", key);
					break;			// back to main menu
				}
				System.out.printf("\n%d was found at index position %d: Recursive Binary Search\n\n", key, keyIndex);
				timeTaken(startN, startM);
				break;
			case 3:
				if (randomArr == null) {			// prompt messages when no array initialized
					System.out.println("No array to search");
					System.out.println("Please initialize an array first\n");
					break;
				}
				System.out.println(Arrays.toString(sortedArr));			// displaying the sorted array
				System.out.print("Please enter an integer value to search: ");
				while (key == 0) {
					try {
						key = sc.nextInt();
					} catch (InputMismatchException e){
						System.out.print("Invalid input, please enter an integer value to search: ");
						sc.nextLine();			// retrieve invalid input
						continue;			// skip below and back to read in a key to search again
					}
					if (key < 120 || key >= 1000) {
						System.out.print("Please enter an integer value to search from 120 to 1000: ");
						sc.nextLine();			// retrieve invalid input
						key = 0;			// to read in a valid key again
					}
				}
				startN = System.nanoTime();
				startM = System.currentTimeMillis();
				keyIndex = searching.linearSearch(sortedArr, sortedArr.length - 1, key);			// recursive linear search
				if (keyIndex == -1) {
					System.out.printf("\n%d was not found\nReturning to the main menu\n\n", key);
					break;			// back to main menu
				}
				System.out.printf("\n%d was found at index position %d: Recursive Linear Search\n\n", key, keyIndex);
				timeTaken(startN, startM);
				break;
			case 4:
				if (randomArr == null) {			// prompt messages when no array initialized
					System.out.println("No array to search");
					System.out.println("Please initialize an array first\n");
					break;
				}
				String subMenu = " ";
				while (!subMenu.equals("R") && !subMenu.equals("r")) {
					displaySubmenu();
					subMenu = sc.nextLine().toUpperCase();			// set up to all upper-case
					switch(subMenu) {
					case "B":
						startN = System.nanoTime();
						startM = System.currentTimeMillis();
						sortingAlgorithms.bubbleSort(randomArr);			// bubble sort algorithm
						timeTaken(startN, startM);
						break;
					case "I":
						startN = System.nanoTime();
						startM = System.currentTimeMillis();
						sortingAlgorithms.insertionSort(randomArr);			// insertion sort algorithm
						timeTaken(startN, startM);
						break;
					case "S":
						startN = System.nanoTime();
						startM = System.currentTimeMillis();
						sortingAlgorithms.selectionSort(randomArr);			// selection sort algorithm
						timeTaken(startN, startM);
						break;
					case "M":
						startN = System.nanoTime();
						startM = System.currentTimeMillis();
						Integer[] mergeArr = Arrays.copyOf(randomArr, randomArr.length);			// create a copy of randomArr, initialize to mergeArr to sort below
						System.out.println(Arrays.toString(mergeArr) + "\n");			// displaying array before sorting
						sortingAlgorithms.mergeSort(mergeArr);			// merge sort algorithm
						System.out.println("Merge Sort: Recursive Divide-And-Conquer - O(n log n)Complexity - Out-of-place\n");
						System.out.println("Sorted: " + Arrays.toString(mergeArr) + "\n");			// displaying array after sorting
						timeTaken(startN, startM);
						break;
					case "Q":
						startN = System.nanoTime();
						startM = System.currentTimeMillis();
						Integer[] quickArr = Arrays.copyOf(randomArr, randomArr.length);			// create a copy of randomArr, initialize to quickArr to sort below
						System.out.println(Arrays.toString(quickArr) + "\n");			// displaying array before sorting
						sortingAlgorithms.quickSort(quickArr, 0, quickArr.length - 1);			// quick sort algorithm
						System.out.println("Quick Sort: Recursive Divide-And-Conquer - O(n log n)Complexity - In-place\n");
						System.out.println("Sorted: " + Arrays.toString(quickArr) + "\n");			// displaying array after sorting
						timeTaken(startN, startM);
						break;
					case "R":
						System.out.println("Returning to Main Menu\n");
						break;
					default:			// invalid input handles
						System.out.println("Please enter one of the alphabet options provided\n");
						break;
					}
				}
				break;
			case 5:
				System.out.println("Exiting");
				break;
			default:			// invalid integer value handles
				System.out.println("Please enter the choice between 1 to 5\n");
				break;
			}
		}sc.close();			// close scanner 
	}
	/**
	 * Uses SecureRandom class to generate Integer type of an array with user desired size and bound
	 * @param size - array size to create
	 * @param start	- the least value that can be returned
	 * @param end - the upper bound (exclusive) for the returned value
	 * @return randomArr - a array of pseudorandomly chosen int value between start(inclusive) and end(exclusive)
	 */
	private static Integer[] genRandomInts(int size, int start, int end) {
		Integer randomArr[] = new Integer[size];
		SecureRandom secureRandom = new SecureRandom();
		for (int i = 0; i < randomArr.length; i++) {
			randomArr[i] = secureRandom.nextInt(start,end);			// put each pseudorandomly chosen int value to an array
		}
		return randomArr;
	}

	/**
	 * Helper method to execute nanoTime() and currentTimeMillis() 
	 * then display the time taken for the algorithm of the caller
	 * @param startN - nanotime of starting point
	 * @param startM - millitime of starting point
	 */
	private static void timeTaken(long startN, long startM) {
		long endN;
		long endM;
		long nanoTaken;
		long millisTaken;
		endN = System.nanoTime();
		endM = System.currentTimeMillis();
		nanoTaken = (endN - startN);
		millisTaken = (endM - startM);
		System.out.printf("Time taken in nanoseconds: %d\n", nanoTaken);
		System.out.printf("Time taken in milliseconds: %d\n\n", millisTaken);
	}

	/**
	 * Helper method to display main menu
	 */
	private static void displayMain() {
		System.out.println("Select your option in the menu:");
		System.out.println("1: Initialize and populate an array of 1000 random integers.");
		System.out.println("2: Perform a recursive binary search.");
		System.out.println("3: Perform a recursive linear search.");
		System.out.println("4: Sort the array");
		System.out.println("5: Quit");
		System.out.print("> ");
	}

	/**
	 * Helper method to display sub menu
	 */
	private static void displaySubmenu() {
		System.out.println("B. Bubble Sort");
		System.out.println("I. Insertion Sort");
		System.out.println("S. Selection Sort");
		System.out.println("M. Merge Sort");
		System.out.println("Q. Quick Sort");
		System.out.println("R. Return to Main Menu");
		System.out.print("> ");
	}
}
