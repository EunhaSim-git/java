package BinLinSearch;
import java.security.SecureRandom;
import java.util.Arrays;
import java.util.Scanner;

/**
 * CET - CS Academic Level 3
 * Declaration: I declare that this is my own original work and is free from Plagiarism
 * This class contains BinaryLinearSearch class to search a number, that user entered,
 * utilizing binary and linear search methods recursively and iteratively.
 * Student Name: Eunha Sim
 * Student Number: 041-078-020
 * Section #: 301
 * Course: CST8130 - Data Structures
 * Professor: James Mwangi PhD. 
  */

/**
 * This class consists of methods to search for an integer in a randomly generated array
 * using binary search algorithm and linear search algorithm
 * and utility methods such as a scanner() for reading user input
 * and two built-in nanoTime() and currentTimeMillis() to compute the time taken by the search algorithms.
 * 
 */
public class BinaryLinearSearch {
	private int[] randomInts;
	private int[] sortedRandomInts;
	private	int[][] randomIntArrs;
	private int start;			// shared variables for all search methods
	private int end;
	private int mid;
	protected Scanner sc = new Scanner(System.in);			// Scanner object will be shared with Test class
	private SecureRandom secureRandom = new SecureRandom();			// declare SecureRandom class
	
	/**
	 * The method iteratively searches the key using binary method.
	 * It initializes first index as start of type int and last index as end of type int 
	 * and iterate initializing mid point as a result of computation
	 * searching which side the key is found then dropping the remaining values.
	 * The loop stops when key is found then returns its index value otherwise returns -1.
	 * 
	 * @param sortedArray - an sorted array value from generateRandomInts()
	 * @param key - user input to search
	 * @return - where the key is found, index value. if it's not found then return -1
	 */
	int iterativeBinarySearch(int[] sortedArray, int key) {
		start = 0;
		end = sortedArray.length - 1;
		while (start <= end) {
			mid = start + (end - start) / 2;			// initialize mid index as result of computation followed below
			if (key == sortedArray[mid]) {				// when the key is equal to the value in the array
				return mid;								// it returns its index value
			} 
			else if (key > sortedArray[mid] ) {			// if key is in right hand than mid index value
				start = mid + 1;						// re-assign start value to compute next round
			}
			else {
				end = mid - 1;							// re-assign end value to compute next round
			}
		}
		return -1;			// key is not found
	}
	
	/**
	 * The method recursively searches the key using binary method.
	 * It prints out remaining elements and if the key is not found 
	 * then it calls itself again to keep searching the key.
	 * 
	 * @param sortedArray - an sorted array value from generatedRandomInts()
	 * @param firstIndex - first index of sortedArray parameter to recursively call itself again
	 * @param lastIndex - last index of sortedArray parameter to recursively call itself again
	 * @param key - the user input to search
	 * @return - where the key is found, index value. if it's not found then return -1
	 */
	int recursiveBinarySearch(int[] sortedArray, int firstIndex, int lastIndex, int key) {
		System.out.println(remainingElements(sortedArray, firstIndex, lastIndex));
		if (lastIndex >= firstIndex) {				// if it's true, means the key is not in the array then returns -1
			mid = firstIndex + (lastIndex - firstIndex) / 2;			// initializes mid index 
			if (sortedArray[mid] == key) {			// the key is found, return its index value
				return mid;
			}
			if (sortedArray[mid] > key) {			// the key is in the left side, dropped the right side by re-assigning last index
				return recursiveBinarySearch(sortedArray, firstIndex, mid - 1, key);
			}
			if (sortedArray[mid] < key) {			// the key is in the right side, dropped the left side by re-assigning start index
				return recursiveBinarySearch(sortedArray, mid + 1, lastIndex, key);
			}
		}
		return -1;			// the key is not in the array
	}
	
	/**
	 * The method uses secureRandom() to generate 32 integers ranged 10 to 100.
	 * Then, randomly generated array and the sorted on are stored a two dimensional array to return both arrays.
	 * 
	 * @return result - a two-dimensional array of type int,
	 * 					that stores an array randomInts and sortedRandomInts which is sorted the randomInts
	 */
	int[][] generateRandomInts() {
		randomInts = secureRandom.ints(32, 10, 100).toArray();			// generate a array of type int with size 32 and range from 10 to 100
		sortedRandomInts = Arrays.copyOf(randomInts, randomInts.length);			// copy of an array randomInts of type int
		Arrays.sort(sortedRandomInts);			// sort the randomly generated integers
		randomIntArrs = new int[2][];			// initialize a two-dimensional array to store both arrays
		randomIntArrs[0] = randomInts;
		randomIntArrs[1] = sortedRandomInts;
		return randomIntArrs;			// return both arrays, random ordered array and the same value sorted one
	}
	
	/**
	 * It converts the remaining array value to a type string to print out.
	 * @param remains - an array of type int to search the key in this program
	 * @param startArr - starting index of type integer
	 * @param endArr - last index of type integer
	 * @return array values of type string
	 */
	public String remainingElements(int[] remains, int startArr, int endArr) {
		StringBuilder stringbuilder = new StringBuilder();
		for (int i = startArr; i < endArr; i++) {			// from startArr index to endArr index
			stringbuilder.append(remains[i] + " ");			// append a value in the array
		}
		return stringbuilder.toString();
	}

	/**
	 * The method iteratively searches the key with linear algorithm
	 * by using for loop to set the range to search the key
	 * 
	 * @param sortedArray - an random ordered array value from generatedRandomInts()
	 * @param key - the user input to search
	 * @return - where the key is found, index value. if it's not found then return -1
	 */
	int iterativeLinearSearch(int[] sortedArray, int key) {
		for (int i = 0; i < sortedArray.length; i++) {			// loops within the sortedArray
			if (sortedArray[i] == key) {			// search the key with linear algorithm
				return i;
			}
		}
		return -1;			// the key is not found
	}
	
	/**
	 * The method recursively searches the key with linear algorithm
	 * It calls itself by reducing the array length by 1 until the key is found
	 * 
	 * @param sortedArray - a random ordered array value from generatedRandomInts()
	 * @param size - the length of sortedArray
	 * @param key - the user input to search
	 * @return - where the key is found, index value. if it's not found then return -1
	 */
	int recursiveLinearSearch(int[] sortedArray, int size,  int key) {
		if (size < 0)
			return -1;			// when the key is not found in the array
		if (sortedArray[size] == key)
			return size;
		return recursiveLinearSearch(sortedArray, size - 1, key);			// calls itself again with a reduced size
	}

	/**
	 * Uses nanoTime() and currentTimeMillis() methods from System class
	 * to compute time taken between pre and post and prints out the result
	 * @param preN - result of the method call nanoTime()
	 * @param preM - result of the method call currentTimeMillis()
	 */
	public void timeTaken(long preN, long preM) {
		long postN = System.nanoTime();
		long postM = System.currentTimeMillis();
		long timeN = postN - preN;			// compute the time taken for the search method 
		long timeM = postM - preM;
		System.out.println("Time taken in nanoseconds: " + timeN + "\n" + "Time taken in milliseconds: " + timeM + "\n");
	}
	
	/**
	 * It is a helper method to print out the index value and the search algorithm that was used
	 * 
	 * @param index - where the key in found
	 * @param searchKey - the user input to search
	 * @param method - search algorithm that was used 
	 * @return - the message
	 */
	public String toString(int index, int searchKey, String method) {
		if (index == -1) {
			System.out.println("number " + searchKey + " was not found." + " : " + method + "Search.");
		} else {
			System.out.println("number " + searchKey + " was found at index " + index + " : " + method + "Search.");
		}
		
		return toString();
	}
}
