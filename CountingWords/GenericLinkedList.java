/**
 * GenericLinkedList class implements the Index interface.
 * It stores nodes in sorted order. 
 * 
 * @author Connie Shi
 * @version 
 *
 * @param <T>
 *    any reference type that implements Comparable<T> interface 
 */
public class GenericLinkedList <T extends Comparable<T>> implements Index<T> {
	private GenericNode<T> head;
	private int numOfElements;
	private GenericNode<T> iterator = head;

	/**
	 * Creates an empty list object. 
	 */
	public GenericLinkedList( ) {
		head = null;
		numOfElements = 0;
	}

	/* (non-Javadoc)
	 * @see proj3_posted.Index#insert(java.lang.Comparable)
	 * 
	 * Inserts the item as a node according to alphabetical order
	 * 
	 */
	@Override
	public void insert(T item) {
		if (item != null) {
			GenericNode<T> newNode = new GenericNode<T>(item, null);
			if (head == null) {
				head = newNode;
			} else if (newNode.getData().compareTo(head.getData()) < 0 ) {
				newNode.setNext(head);
				head = newNode;
			} else {
				GenericNode<T> current = head;
				while (current.getNext() != null && newNode.getData().compareTo(current.getNext().getData()) > 0 ) {
					current = current.getNext();
				}
				newNode.setNext(current.getNext());
				current.setNext(newNode);
			}
		}
		numOfElements++;
	}

	/* (non-Javadoc)
	 * @see proj3_posted.Index#remove(java.lang.Comparable)
	 * 
	 * Removes the element that item is equal to.
	 */
	@Override
	public void remove(T item) {
		if(item != null) {
			numOfElements--;
			if (head == null) {
				System.out.println("Empty list, cannot use remove method.");
				return;
			}
			if (head.getData().compareTo(item) == 0) {
				head = head.getNext();
			}
			GenericNode<T> current = head;
			while (current.getNext() != null && current.getNext().getData().compareTo(item) != 0) {
				current = current.getNext();
			}
			if (current.getNext() != null && current.getNext().getData().compareTo(item) == 0) {
				current.setNext(current.getNext().getNext());
			} else { //not found
				return;
			}
		}
	}

	/* (non-Javadoc)
	 * @see proj3_posted.Index#get(java.lang.Comparable)
	 * 
	 * Looks through the LinkedList for the item that matches
	 * the node that holds that same item, and then return
	 * A reference for that data item so we can modify its count
	 * 
	 */
	@Override
	public T get(T item) {
		GenericNode<T> current = head;
		if (current == null) {
			return null;
		}
		
		while (current != null && current.getData().compareTo(item) != 0) {
			current = current.getNext();
		}
		
		if (current != null) {
			return (T)current.getData();
		}
		
		return null;
	}

	/* (non-Javadoc)
	 * @see proj3_posted.Index#getNext()
	 * 
	 * Iterator is my reference is a node within my LinkedList
	 * It goes to the next one every time getNext is called
	 * 
	 */
	@Override
	public T getNext() {
		if (iterator != null) {
			T toReturn = iterator.getData();
			iterator = iterator.getNext();
			return toReturn;
		}
		return null;
	}

	/* (non-Javadoc)
	 * @see proj3_posted.Index#resetNext()
	 * 
	 * Resetting iterator to my head node to start
	 * From the beginning of my LinkedList
	 * 
	 */
	@Override
	public void resetNext() {
		iterator = head;
	}

	/* (non-Javadoc)
	 * @see proj3_posted.Index#size()
	 * 
	 * The numOfElements keeps track of the size.
	 */
	@Override
	public int size() {
		return numOfElements;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 * 
	 * Uses my helperString method to recursively print out
	 * the items of the node
	 * 
	 */
	@Override
	public String toString() {
		GenericNode<T> current = head;
		return helperString(current);
	}

	/**
	 * Recursion helper method to add all the data
	 * Into my String that is being printed
	 * 
	 * @param theNextOne
	 * @return String that gets printed
	 */
	private String helperString(GenericNode<T> theNextOne) {
		String s = "";
		if (theNextOne == null) {
			return "";
		}
		
		return s += theNextOne.getData().toString() + "\n" + helperString(theNextOne.getNext());
	}
}
