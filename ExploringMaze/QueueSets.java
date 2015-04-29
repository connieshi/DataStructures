
/**
 * @author Connie Shi cs3313@nyu.edu
 * This is the queue class that implements SetOfSpaces
 * Reused and modified code given by Joanna Klukowska on class website
 * With permission
 */
public class QueueSets implements SetOfSpaces {

	private final static int DEFAULT_CAPACITY = 30;
	private Space [] queue;
	private int capacity;
	private int size;
	private int front;
	private int back;

	/**
	 * Non-default constructor that takes in the starting capacity
	 * Of the array that is used to implement queues
	 * @param capacity
	 */
	public queueSets(int capacity) {
		this.capacity = capacity;
		queue = new Space [capacity];
		front = 0;
		back = capacity - 1;
	}

	/**
	 * Default constructor that calls the non-default constructor
	 * Automatically creating an array with default size
	 */
	public queueSets() {
		this(DEFAULT_CAPACITY);
	}

	/* (non-Javadoc)
	 * @see SetOfSpaces#add(Space)
	 */
	@Override
	public void add(Space s) {
		//make sure we are not adding a null job
		if (s == null )
			return;
		//check if the queue is big enough
		//to add another element
		if ( size == capacity ) 
			makeLarger();
		//advance back to point to the next available spot
		back = (back+1)%capacity;
		queue[back] = s;
		size++;

	}

	/* (non-Javadoc)
	 * @see SetOfSpaces#remove()
	 */
	@Override
	public Space remove() {
		if (size == 0)
			return null;
		Space current = queue[front];
		queue[front] = null;
		front = (front+1)%capacity;
		size--;
		return current;
	}

	/**
	 * Private method used only within this class to double the size of the array
	 * If the current array is too small to hold Spaces
	 */
	private void makeLarger () {
		//allocate larger array
		Space [] newQueue = new Space [capacity * 2 ];
		//copy the data over to the new array
		int current = front;
		for (int i = 0; i < capacity; i++) {
			newQueue[i] = queue[current];
			current = (current+1) % capacity;
		}
		//reset list reference to the new array
		queue = newQueue;
		//reset the capacity to the new value
		capacity = 2*capacity;
		//reset front and back
		front = 0;
		back = size-1;
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
	 * @return Space character of the
	 * Space about to be popped
	 */
	public Space peek() {
		if (queue[front] == null)
			return null;
		else
			return queue[front];
	}
	
	/**
	 * @return
	 * Getter for size of array
	 */
	public int getSize() {
		return size;
	}
}

