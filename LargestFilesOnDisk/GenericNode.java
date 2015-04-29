
/**
 * Defines a generic linked list node storing 
 * a data item of type T and a single reference to next. 
 * @author Joanna Klukowska
 * Modified to fit the program by Connie Shi
 * 
 * @param <T>
 *    any valid reference type
 */
public class GenericNode <T> {
	//reference to the next node
	private GenericNode <T> next;
	//data item stored in the node
	private T data;
	
	/**
	 * Default constructor creates an empty node.
	 */
	public GenericNode () {
		data = null;
		next = null;
	}

	/**
	 * Creates a node with specified data item.
	 * @param data
	 *    data item to store in the node
	 */
	public GenericNode ( T data ) {
		if (data != null )
			this.data = data;
	}
	

	/**
	 * Creates a node with specified data and reference to next.
	 * @param data
	 *    data item to store in the node
	 * @param next
	 */
	public GenericNode ( T data, GenericNode<T> next ) {
		if (data != null )
			this.data = data;
		if (next != null )
			this.next = next;
	}

	/**
	 * @return the next
	 */
	public GenericNode<T> getNext() {
		return next;
	}

	/**
	 * @param next the next to set
	 */
	public void setNext(GenericNode<T> next) {
		this.next = next;
	}

	/**
	 * @return the data
	 */
	public T getData() {
		return data;
	}

	/**
	 * @param data the data to set
	 */
	public void setData(T data) {
		this.data = data;
	}
}
