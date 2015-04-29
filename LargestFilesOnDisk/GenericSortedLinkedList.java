
/**
 * @author Connie Shi
 * GenericSortedLinkedList, uses a LinkedList structure to hold information
 *
 * @param <T>
 */
public class GenericSortedLinkedList<T extends Comparable<T>>{
	//head refers to the first node
	private GenericNode<T> head;

	private int numOfElements;

	/**
	 * default constructor
	 */
	public GenericSortedLinkedList() {
		head = null;
		numOfElements = 0;
	}

	/**
	 * @param item
	 * Insert an item into the list
	 * According to its data value
	 * In descending order (the largest at the top)
	 */
	public void insert(T item) {
		if (item != null) {
			GenericNode<T> newNode = new GenericNode<T>(item, null);
			//if list is empty
			if (head == null)
				head = newNode;
			//if the item is bigger than anything in the list already
			else if (newNode.getData().compareTo(head.getData()) < 0 ) {
				newNode.setNext(head);
				head = newNode;
			}
			else {
				GenericNode<T> current = head;
				//if the next one isn't null (current advanced to the last node)
				//or if the correct sequential spot is found
				while (current.getNext() != null && 
						newNode.getData().compareTo(current.getNext().getData()) > 0 ) {
					current = current.getNext();
				}
				//connect the node
				newNode.setNext(current.getNext());
				current.setNext(newNode);
			}
		}
		numOfElements++;
	}

	/**
	 * @return size of the linked list
	 */
	public int size(){
		return numOfElements;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	public String toString() {
		String s = "";
		if (numOfElements == 0)
			return "There are no items in the list";
		GenericNode<T> current = head;
		while (current != null) {
			s += current.getData().toString() + "\n";
			current = current.getNext();
		}
		return s;
	}

	/**
	 * @param n (the number of files to display in console)
	 * @return
	 */
	public String toStringMaxNum(int n) {
		String s = "";
		int counter = 0;
		if (numOfElements == 0)
			return "There are no items in the list";
		GenericNode<T> current = head;
		//get every data print out for all nodes, stops when it's null
		while (counter < n && current != null) {
			s += current.getData().toString() + "\n";
			current = current.getNext();
			counter++;
		}
		return s;
	}
}
