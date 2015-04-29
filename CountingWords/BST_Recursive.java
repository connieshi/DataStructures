import java.util.ArrayList;

/**
 * BST_Recursive class implements the Index interface.
 * It provides binary search tree structure. 
 *  
 * @author Connie Shi
 *
 * @param <T>
 *    any reference type that implements Comparable<T> interface 
 */

public class BST_Recursive <T extends Comparable<T>> implements Index<T> {
	private BSTNode<T> root;
	private int numOfElements;
	private ArrayList<T> a = new ArrayList<T>();
	private static int iterator = 0;
	private static int added = 0; 

	/**
	 * Creates an empty binary search tree.
	 */
	public BST_Recursive() {
		this.root = null;
		numOfElements = 0; 
	}

	/* (non-Javadoc)
	 * @see proj3_posted.Index#insert(java.lang.Comparable)
	 * 
	 * Keeps track of numOfElements and added to find the size
	 * And how many unique words in total
	 * Then uses my recursion insert helper method to insert the item
	 * 
	 */
	@Override
	public void insert(T item) {
		numOfElements++;
		added++; 
		root = insertHelper(root, item);
	}
	
	/**
	 * The helper method for insert recursion.
	 * @param node
	 * @param newData
	 * @return node -- the reference to the root
	 */
	private BSTNode<T> insertHelper(BSTNode<T> node, T newData){
		if (node == null) {
			BSTNode<T> newNode = new BSTNode<T>(newData);
			return newNode;
		} else if (newData.compareTo(node.getData()) < 0) {
			node.setLeft(insertHelper(node.getLeft(), newData));
		} else {
			node.setRight(insertHelper(node.getRight(), newData));
		}
		return node;
	}

	/**
	 * Recursively traverse the binary search tree in order.
	 * Adds each element's data to the ArrayList according to
	 * Its proper order.
	 * @param node
	 */
	private void recInOrderTraversal(BSTNode<T> node) {
		if (node != null) {
			recInOrderTraversal(node.getLeft());
			a.add(node.getData());
			recInOrderTraversal(node.getRight());
		}
	}

	/* (non-Javadoc)
	 * @see proj3_posted.Index#remove(java.lang.Comparable)
	 */
	@Override
	public void remove(T item) {
		if (item != null) {
			numOfElements--;
			root = recRemove(root, item);
		}
	}

	/**
	 * Recursively find the node that needs to be removed
	 * According to node's data same as T item
	 * @param node - recursively look at each node
	 * @param item - the data that we're looking for in node
	 * @return
	 */
	private BSTNode<T> recRemove(BSTNode<T> node, T item) {
		if (node != null && item != null) {
			if (item.compareTo(node.getData()) > 0) {
				node.setRight(recRemove(node.getRight(), item));
			} else if (item.compareTo(node.getData()) < 0) {
				node.setLeft(recRemove(node.getLeft(), item));
			} else {
				node = removeData(node);
			}
		}
		return node;
	}

	/**
	 * After finding where the node to remove is,
	 * This is the process of actually removing it by changing the data
	 * This uses the getPredecessor method to find what to replace
	 * The data of the node with
	 * @param node
	 * @return 
	 */
	private BSTNode<T> removeData(BSTNode<T> node) {
		T data;

		if (node.getLeft() == null && node.getRight() == null) {
			return null;
		} else if (node.getLeft() == null) {
			return node.getRight();
		} else if (node.getRight() == null) {
			return node.getLeft();
		} else {
			data = getPredecessor(node.getLeft());
			node.setData(data);
			node.setLeft(recRemove(node.getLeft(), data));
			return node;
		}
	}

	/**
	 * The getPredecessor method passes in the param current
	 * Which is the left of the node we are trying to remove
	 * And it goes to the right-most node to find the biggest data
	 * To replace the node that is getting removed to retain
	 * Binary Search Tree structure
	 * @param current
	 * @return T - the data that is in the right most node
	 */
	private T getPredecessor (BSTNode<T> current) {
		while(current.getRight() != null) {
			current = current.getRight();
		}
		return current.getData();

	}

	/* (non-Javadoc)
	 * @see proj3_posted.Index#get(java.lang.Comparable)
	 * 
	 * Calls my recursion help method
	 */
	@Override
	public T get(T item) {
		return getHelper(item, root);
	}
	
	/**
	 * Recursively searches for the item that is passed through
	 * Until that node containing the data is found
	 * Then return the reference so the counter of word can be modified
	 * @param item
	 * @param currentNode
	 * @return
	 */
	private T getHelper(T item, BSTNode<T> currentNode) {
		if (currentNode == null) {
			return null;
		} else if (item.compareTo(currentNode.getData()) > 0) {
			return getHelper(item, currentNode.getRight());
		} else if (item.compareTo(currentNode.getData()) < 0) {
			return getHelper(item, currentNode.getLeft());
		} else {
			return currentNode.getData();
		}
	}
	
	/* (non-Javadoc)
	 * @see proj3_posted.Index#getNext()
	 * Iterator is a static variable that saves my spot of where
	 * In the ArrayList I am at
	 * Iterator gets incremented after the item is returned
	 */
	@Override
	public T getNext() {
		if (iterator < a.size()) {
			return a.get(iterator++);
		} else {
			return null;
		}
	}

	/* (non-Javadoc)
	 * @see proj3_posted.Index#resetNext()
	 * 
	 * Uses recInOrderTraversal to iteratively populate
	 * The ArrayList, and sets the iterator to the beginning
	 * Which is index 0
	 * 
	 */
	@Override
	public void resetNext() { 
		recInOrderTraversal(root);
		iterator = 0;
	}

	/* (non-Javadoc)
	 * @see proj3_posted.Index#size()
	 * 
	 * Could return numOfElements, but that is unreliable
	 * Because numOfElements may be different from the actual
	 * Elements within the BST
	 * Uses recursion to actually look through the tree
	 * 
	 */
	@Override
	public int size() { 
		return recSize(root);
	}
	
	/**
	 * Recursion helper to count how many nodes are in my BST
	 * 
	 * @param node
	 * @return
	 */
	private int recSize(BSTNode<T> node) {
		if (node == null)
			return 0;
		else
			return recSize(node.getLeft()) + recSize(node.getRight()) + 1;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 * 
	 * Uses recInOrderTraversal to find all the nodes in my tree
	 * Adds it to my former array list
	 * Prints it out from where the added stopped to the size of array
	 * (A pretty bad way to implement it, but I couldn't figure out
	 * A different way to do it without messing up the code)
	 * 
	 */
	@Override
	public String toString() {
		String s = "";
		recInOrderTraversal(root);
		for (int i = added; i < a.size(); i++) {
			s += a.get(i).toString()+ "\n";
		}
		return s;
	}
}
