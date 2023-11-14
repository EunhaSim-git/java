/**
 * CET - CS Academic Level 3
 * Declaration: I declare that this is my own original work and is free from Plagiarism
 * 
 * Purpose: Processing data using Doubly Linked List
 * Lab number: 5
 * Student Name: Eunha Sim
 * Student Number: 041-078-020
 * Section #: 301
 * Course: CST8130 - Data Structures
 * Professor: James Mwangi PhD. 
 */

/**
 * Node class to classify individual node
 * It contains constructor to instantly initialize its data
 * and a method to display each node
 */
class Node {
	/**
	 * of type integer holding value of a node
	 */
	public int mData;
	/**
	 * of type Node, which holds next node in list
	 */
	public Node next;
	/**
	 * of type Node, which holds previous node in list
	 */
	public Node previous;

	/**
	 * Constructor takes an integer to instantly initialize mData
	 * @param d - of type integer value, to be mData of a node
	 */
	public Node(int d)
	{
		mData = d;
	}

	/**
	 * Displays individual node
	 */
	public void displayNode() {
		System.out.print(mData + " ");
	}

} // end class Node
//===================== class design starts here =============================
/**
 * Implements non-generic doubly linked list
 * Contains utility methods as isEmpty, inserting, deleting, searching in list
 * and attributes as first and last nodes
 */
class DoublyLinkedList {
	/**
	 * Head node in list, its previous node is null
	 */
	private Node first;
	/**
	 * Tail node in list, its next node is null
	 */
	private Node last;


	/**
	 * Constructor to instantly initialize first and last node
	 */
	public DoublyLinkedList() {
		first = null;
		last = null;
	}

	/**
	 * Checks if the list is empty
	 * @return - true if it's empty, if not false
	 */
	public boolean isEmpty() {
		if(first==null)
			return true;
		else 
			return false;
	}

	/**
	 * Insert node in place of first node
	 * @param num - mData for the node to be inserted
	 */
	public void insertFirst(int num) {
		Node newNode = new Node(num);
		if(isEmpty()) {			// if the list is empty
			first = newNode;			// the inserting node is first node
		} else {
			first.previous = newNode;
			newNode.next = first;			// linking newNode ahead of first node
			first = newNode;
		}
	}

	/**
	 * Insert node in place of last node
	 * @param num - mData for the node to be inserted
	 */
	public void insertLast(int num) {
		Node newNode = new Node(num);
		if(isEmpty()) 
			first = newNode;

		Node temp = first;
		while(temp.next != null) {			// loops until it reached last node
			temp = temp.next;
		}
		temp.next = newNode;
		newNode.previous = temp;			// linking newNode at the end of list node
		last = newNode;
	}

	/**
	 * Insert newNumber just after numToFind
	 * @param numToFind - mData value of the node to find
	 * @param newNumber - mData for the node to be inserted
	 * @return - true when successfully executed
	 */
	public boolean insertAfter(int numToFind, int newNumber) {
		if(isEmpty()) 
			return false;
		Node temp = first;			// creates copy of temporary node 
		while(temp != null){
			if(temp.mData == numToFind) {
				Node newNode = new Node(newNumber);
				if(temp.next == null) {			//when found number is at last in list
					temp.next=newNode;
					newNode.previous=temp;
					newNode.next=null;
					last=newNode;
				}
				else if(temp.previous == null) {			//when found number is at first in list
					temp.previous=newNode;
					newNode.next=temp;
					newNode.previous=null;
					first=newNode;
				}
				else {			// when found number is neither at first nor at last
					newNode.next = temp.next;
					temp.next.previous = newNode;
					newNode.previous=temp;
					temp.next = newNode;
				}
				return true;			// successfully executed, ends the method
			}
			temp = temp.next;			// set up to find numToFind in this while loop
		}
		return false;
	}

	/**
	 * Delete first node and re-initialize the next node as first node
	 * @return - the first node which has been deleted
	 */
	public Node deleteFirstNode() {
		Node nodeToDelete = first;
		if (isEmpty() || first.next == null) {
			first = null;			// if either empty or only one node in list
		}
		first = first.next;
		first.previous = null;

		return nodeToDelete;
	}

	/**
	 * Delete last node and re-initialize the previous node as last node
	 * @return - the last node which has been deleted
	 */
	public Node deleteLastNode() {
		Node nodeToDelete = last;
		if(isEmpty() || last.previous == null) {
			last = null;			// if either empty or only one node in list
		}
		last = last.previous;
		last.next = null;
		return nodeToDelete;
	}

	/**
	 * Search the node that contains number as mData, delete the node 
	 * then linking the list 
	 * @param number - mData to find and delete
	 * @return - the node that contains the given integer value
	 */
	public Node searchAndDelete(int number) {
		if(isEmpty())
			return null;
		Node temp = first;
		Node nodeToDelete = new Node(number);
		while(temp != null) {			//loop to search the number
			if(temp.mData == number) {			//found the number to delete
				if(temp.previous == null) {			//if it's at first position
					temp = temp.next;
					temp.previous = null;
					first = temp;
				} else if (temp.next == null) {			//if it's at last position
					temp.previous.next = null;
					last = temp.previous;
				} else {			//if it's at neither first nor last
					temp.previous.next = temp.next;
					temp.next.previous = temp.previous;
					temp = temp.next;
				}
			}
			temp = temp.next;
		}
		return nodeToDelete;
	}


	/**
	 * Display the mData of each node from first node to last node
	 */
	public void printForwards() {
		Node nodeToPrint = first;			// copy first node, to print from first
		while(nodeToPrint != null){
			nodeToPrint.displayNode();
			nodeToPrint = nodeToPrint.next;
		}
	}

	/**
	 * Display the mData of each node from last node to first node
	 */
	public void printBackwards() {
		Node nodeToPrint = last;			// copy last node, to print from last
		while(nodeToPrint != null) {
			nodeToPrint.displayNode();
			nodeToPrint = nodeToPrint.previous;
		}
	}
} // end class DoublyLinkedList
