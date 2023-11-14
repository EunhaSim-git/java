/**
 * CET - CS Academic Level 3
 * Declaration: I declare that this is my own original work and is free from Plagiarism
 * 
 * Purpose: Test the doubly linked list application
 * Lab number: 5
 * Student Name: Eunha Sim
 * Student Number: 041-078-020
 * Section #: 301
 * Course: CST8130 - Data Structures
 * Professor: James Mwangi PhD. 
 */

/**
 * Test the non-generic doubly linked list application
 */
class TestDLLApp {
	/**
	 * Invokes utility methods from DoublyLinkedList and display the outcome
	 * @param args void
	 */
	public static void main(String[] args) {
		DoublyLinkedList newLL = new DoublyLinkedList();			//create an empty list
		
		newLL.insertFirst(22);			
		newLL.insertFirst(44);
		newLL.insertFirst(74);
		newLL.insertFirst(97);			//97 74 44 22
		
		newLL.insertLast(9);
		newLL.insertLast(33);
		newLL.insertLast(55);			//97 74 44 22 9 33 55
		
		System.out.print("[From first_to_last] : ");
		newLL.printForwards();			//output: 97 74 44 22 9 33 55
		System.out.print("\n[From last_to_first] : ");
		newLL.printBackwards();			//output: 55 33 9 22 44 74 97
		
		newLL.deleteFirstNode();			//delete 97
		newLL.deleteLastNode();				//delete 55
		newLL.searchAndDelete(9);
		
		System.out.print("\n[From first_to_last] : ");
		newLL.printForwards();			//output: 74 44 22 33
		
		newLL.insertAfter(22, 69);
		newLL.insertAfter(33, 88);
		System.out.print("\n[From first_to_last] : ");
		newLL.printForwards();			//output: 74 44 22 69 33 88

		
		

	} // end of main() method
} // end class TestDLLApp
