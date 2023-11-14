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

import java.util.Arrays;

/**
 * Consists of sorting algorithms to implement 
 * Bubble sort, Insertion sort, Selection sort, Merge sort, and Quick sort.
 * @param <T> T - generic type T can take any type and compute the sorting algorithms
 */
public class SortingAlgorithms<T extends Comparable<T>>{
	/**
	 * generic type of unsorted array
	 */
	protected T[] randomArr;

	/**
	 * Takes in an generic type of array to initialize instance variable
	 * @param array - generic type of array
	 */
	public SortingAlgorithms(T[] array) {
		this.randomArr = array;
	}

	/**
	 * Swaps left index with right index in the given array
	 * @param i - left index to swap
	 * @param j - right index to swap
	 * @param array - array holding the values to swap
	 */
	private void swap(int i, int j, T[] array) {
		T temp = array[i];
		array[i] = array[j];
		array[j] = temp;
	}

	/**
	 * Displays a sorted array using Bubble sort.
	 * Each iteration swap the biggest element to the right most index
	 * comparing the starting element with adjacent element.
	 * Terminates the loop when sorting completed, before reaching to the last index
	 * @param randomArr - an unsorted origin array to sort
	 */
	public void bubbleSort(T[] randomArr) {
		T[] bubbleArr = Arrays.copyOf(randomArr, randomArr.length);			// create a copy of the randomArr
		System.out.println(Arrays.toString(bubbleArr) + "\n");			// displaying array before sorting
		for (int i = 0; i < bubbleArr.length - 1; i++) {
			boolean didSwap = false;			// to prevent an iteration through sorted array
			for (int j = 0; j < bubbleArr.length - i - 1; j++) {			// length - i -1 as no need to sort the last element of each iteration
				if (bubbleArr[j].compareTo(bubbleArr[j+1]) > 0) {
					swap(j,j+1, bubbleArr);
					didSwap = true;
				}
			}
			if (!didSwap)			// swap has not occurred, the origin array has been sorted
				break;
		}
		System.out.println("Bubble Sort: Simple sorting alorithm - O(n2)Complexity - In-place\n");
		System.out.println("Sorted: " + Arrays.toString(bubbleArr) + "\n");
	}

	/**
	 * Displays a sorted array using Insertion sort.
	 * Iterate from last element to starting element comparing the right most element to the adjacent left one
	 * when left one is bigger than the currentValue, shift it to the right, iterate backwards,
	 * then place currentValue back to the array.
	 * @param randomArr - an unsorted origin array to sort
	 */
	public void insertionSort(T[] randomArr) {
		T[] insertionArr = Arrays.copyOf(randomArr, randomArr.length);			// create a copy of the randomArr
		System.out.println(Arrays.toString(insertionArr) + "\n");			// displaying array before sorting
		for (int i = 1; i < insertionArr.length; i++) {
			T currentValue = insertionArr[i];
			int j = i - 1;			
			while(j >= 0 && insertionArr[j].compareTo(currentValue) > 0) {
				insertionArr[j+1] = insertionArr[j];			// shift(copy) the bigger one to the right
				j--;			// iterate backwards
			}
			insertionArr[j+1] = currentValue;			// place the currenValue to its proper index
		}
		System.out.println("Insertion Sort: Simple sorting alorithm - O(n2)Complexity - In-place\n");
		System.out.println("Sorted: " + Arrays.toString(insertionArr) + "\n");
	}
	/**
	 * Displays sorted array using Selection sort.
	 * In every iteration of inner loop, swap the smallest element with the starting element in each loop
	 * after each inner loop, starting point increment as left of the starting point is properly sorted
	 * @param randomArr - an unsorted origin array to sort
	 */
	public void selectionSort(T[] randomArr) {
		T[] selectionArr = Arrays.copyOf(randomArr, randomArr.length);
		System.out.println(Arrays.toString(selectionArr) + "\n");

		for (int i = 0; i < selectionArr.length - 1; i++) {			// loop through the entire array
			T min = selectionArr[i];			// set up smallest value
			int indexOfMin = i;
			for(int j = i + 1; j < selectionArr.length; j++) {			// loop through the entire array to compare
				if (min.compareTo(selectionArr[j]) > 0) {			// when the smallest value found
					min = selectionArr[j];			// set up the smallest value again
					indexOfMin = j;			// save the index number where the smallest value was found
				}
			}
			swap(i, indexOfMin, selectionArr);			// swap first element with the smallest element
		}
		System.out.println("Selection Sort: Simple sorting alorithm - O(n2)Complexity - In-place\n");
		System.out.println("Sorted: " + Arrays.toString(selectionArr) + "\n");
	}

	/**
	 * Displays a sorted array using Merge sort.
	 * Recursively divides the array into two arrays until one element in the array left,
	 * then calls merge() to combine the arrays into a sorted array
	 * @param randomArr - an unsorted origin array to sort
	 */
	public void mergeSort(Integer[] randomArr) {
		int length = randomArr.length;
		if (length <=1) {
			return;			// each array has only one element
		}
		int middle = length / 2;
		Integer[] leftArray = new Integer[middle];			// divides the array in halves
		Integer[] rightArray = new Integer[length - middle];

		for (int i = 0, j = 0; i < length; i++) {
			if (i < middle) {
				leftArray[i] = randomArr[i];
			} else {			// i >= middle
				rightArray[j] = randomArr[i];
				j++;
			}			// complete putting each elements into the two of divided arrays
		}
		mergeSort(leftArray);			// calls itself again to divide leftarray
		mergeSort(rightArray);			// calls itself again to divide rightarray
		merge(leftArray, rightArray, randomArr);			// calls merge() to re-combine the divided arrays
	}

	/**
	 * Combines and sorts the divided arrays from mergeSort() when invoked by mergeSort()
	 * @param leftArray - left side of the divided array
	 * @param rightArray - right side of the divided array
	 * @param mergeArr - origin array
	 */
	private void merge(Integer[] leftArray, Integer[] rightArray, Integer[] mergeArr) {
		int leftSize = mergeArr.length / 2;
		int rightSize = mergeArr.length - leftSize;
		int i = 0, l = 0, r = 0;			// i for mergeArr, l for left, r for right
		while(l < leftSize && r < rightSize) {			// loops until the end of each side
			if(leftArray[l] < rightArray[r]) {			// when left side element is smaller than right one
				mergeArr[i] = leftArray[l];
				l++;
			}
			else {			// when right side element is same or smaller than left one
				mergeArr[i] = rightArray[r];
				r++;
			}
			i++;			// increment for the origin array
		}
		while(l < leftSize) {			// loop through remaining elements in left side array
			mergeArr[i] = leftArray[l];
			i++;
			l++;
		}
		while(r < rightSize) {			// loop through remaining elements in right side array
			mergeArr[i] = rightArray[r];
			i++;
			r++;
		}
	}

	/**
	 * Displays a sorted array using Quick sort.
	 * Recursively initializes a pivot variable to divide the origin array into 
	 * left array with smaller than the pivot and right array with bigger than the pivot,
	 * by looping through from left and right simultaneously, sorted when inner while loop is terminated,
	 * then when leftP passed rightP, swap the pivot with the index where left and right met
	 * @param randomArr - an unsorted origin array to sort
	 * @param lowIndex - the left most index in the given array to sort
	 * @param highIndex - the right most index in the given array to sort
	 */
	public void quickSort(T[] randomArr, int lowIndex, int highIndex) {
		if(lowIndex >= highIndex) {			// when only element in the array, return
			return;
		}
		T pivot = randomArr[highIndex];
		int leftP = lowIndex;
		int rightP = highIndex;

		while (leftP < rightP) {
			while (randomArr[leftP].compareTo(pivot) <= 0 && leftP < rightP)
				leftP++;
			while(randomArr[rightP].compareTo(pivot) >= 0 && leftP < rightP)
				rightP--;
			swap(leftP, rightP, randomArr);
		}			// when leftP = rightP
		swap(leftP, highIndex, randomArr);			// swap leftP with pivot(=highIndex)
		quickSort(randomArr, lowIndex, leftP - 1);			// left side of pivot(excluding pivot)
		quickSort(randomArr, leftP + 1, highIndex);			// right side of pivot(excluding pivot)
	}
}

