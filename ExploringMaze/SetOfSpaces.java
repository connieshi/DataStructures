/** 
 * @author Connie Shi cs3313@nyu.edu
 * Interface given by Joanna Klukowska that our subsequent implementing classes
 * Must implement these methods
 * Methods already automatically public abstract
 * 
 */
public interface SetOfSpaces {
	/**
	 * @param s
	 * adds the Space into the stack/queue
	 */
	public abstract void add (Space s);
	
	/**
	 * @return
	 * removes the Space from the stack/queue
	 */
	public abstract Space remove();
	
	/**
	 * @return
	 * if the stack/queue is empty
	 */
	public abstract boolean isEmpty();
}
