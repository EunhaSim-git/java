/**
 * CET - CS Academic Level 3
 * Declaration: I declare that this is my own original work and is free from Plagiarism
 * Lab number: 4
 * Student Name: Eunha Sim
 * Student Number: 041-078-020
 * Section #: 301
 * Course: CST8130 - Data Structures
 * Professor: James Mwangi PhD. 
 */

/**
 * This class consists of methods to search for an integer in a sorted array
 * using recursive linear-search algorithm and recursive binary-search algorithm.
 */
public class Searching {
	/**
	 * Recursively searches the key using binary method.
	 * It prints out remaining elements and if the key is not found 
	 * then it calls itself again to keep searching the key.
	 * 
	 * @param sortedArray - an sorted array value from generatedRandomInts()
	 * @param firstIndex - first index of sortedArray parameter to recursively call itself again
	 * @param lastIndex - last index of sortedArray parameter to recursively call itself again
	 * @param key - the user input to search
	 * @return - where the key is found, index value. if it's not found then return -1
	 */
	int binarySearch(Integer[] sortedArray, int firstIndex, int lastIndex, int key) {
		if (lastIndex >= firstIndex) {				// if it's true, means the key is not in the array then returns -1
			int mid = firstIndex + (lastIndex - firstIndex) / 2;			// initializes mid index 
			if (sortedArray[mid] == key) {			// the key is found, return its index value
				return mid;
			}
			if (sortedArray[mid] > key) {			// the key is in the left side, dropped the right side by re-assigning last index
				return binarySearch(sortedArray, firstIndex, mid - 1, key);
			}
			if (sortedArray[mid] < key) {			// the key is in the right side, dropped the left side by re-assigning start index
				return binarySearch(sortedArray, mid + 1, lastIndex, key);
			}
		}
		return -1;			// the key is not in the array
	}
	/**
	 * Recursively searches the key with linear algorithm
	 * It calls itself by reducing the array length by 1 until the key is found
	 * 
	 * @param sortedArray - a random ordered array value from generatedRandomInts()
	 * @param size - the length of sortedArray
	 * @param key - the user input to search
	 * @return - where the key is found, index value. if it's not found then return -1
	 */
	int linearSearch(Integer[] sortedArray, int size,  int key) {
		if (size < 0)
			return -1;			// when the key is not found in the array
		if (sortedArray[size] == key)
			return size;
		return linearSearch(sortedArray, size - 1, key);			// calls itself again with a reduced size
	}
}
