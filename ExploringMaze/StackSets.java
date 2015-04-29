
/**
 * @author Connie Shi cs3313@nyu.edu
 * This is the stacks class that implements SetOfSpaces
 * Reused and modified code given by Joanna Klukowska on class website
 * Wit permission
 */
public class StackSets implements SetOfSpaces {
	
	private Space [] stack;
	private int size;
	private int capacity;
	private static int DEFAULT_CAPACITY = 30;
	
	/**
	 * @param capacity
	 * stackSets constructor that takes the capacity of the array
	 * As a parameter
	 */
	public stackSets(int capacity)
	{
		//if capacity is negative or zero, reset it to default value
		if (capacity <= 0 ) 
			capacity = DEFAULT_CAPACITY;

		//allocate the array for storing characters
		stack = new Space [capacity];

		//set the initial values
		size = 0;
		this.capacity = capacity;	
	}

	/**
	 * Default constructor that calls the param constructor
	 * With default capacity given
	 */
	public stackSets( )
	{
		this(DEFAULT_CAPACITY);
	}
	
	/* (non-Javadoc)
	 * @see SetOfSpaces#add(Space)
	 */
	@Override
	public void add(Space s) {

		//if the stack is full, allocate a larger array
		if (  size == capacity )
			makeLarger();
		//add the new value to the top of the stack
		if (s != null) {
			stack[size] = s;
			size++;
		}
	}
	
	/* (non-Javadoc)
	 * @see SetOfSpaces#remove()
	 */
	@Override
	public Space remove() {
		//if stack is empty return null reference
		if ( size == 0 ) return null;
		//otherwise remove and return character from the top of the stack
		else {
			size--;
			return stack[size];
		}
	}
	
	/**
	 * Method used only by this class to increase the size of the array
	 * If the current size is too small
	 */
	private void makeLarger () {
		//allocate larger array
		Space [] newList = new Space [capacity * 2 ];
		//copy the data over to the new array
		for (int i = 0; i < capacity; i++) {
			newList[i] = stack[i];
		}
		//reset list reference to the new array
		stack = newList;
		//reset the capacity to the new value
		capacity = 2*capacity;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	public String toString() {
		String stackString = size + ": " ; 
		for (int i = 0; i < size; i++ ) {
			stackString += stack[i] + " ";
		}
		return stackString;
	}

	/* (non-Javadoc)
	 * @see SetOfSpaces#isEmpty()
	 */
	@Override
	public boolean isEmpty() {
		if (size==0) 
			return true;
		else 
			return false;
	}
	

	/**
	 * @return Space character
	 * Of the last entered Space in the array
	 */
	public Space peek() {
		int lastElement = size-1;
		if (size == 0)
			lastElement = 0;
		if (stack[lastElement] == null)
			return null;
		else
			return stack[lastElement];
		
	}
	
	/**
	 * @return
	 * Getter for size of array
	 */
	public int getSize() {
		return size;
	}

}

