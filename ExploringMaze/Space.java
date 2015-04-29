
/**
 * @author Connie Shi cs3313@nyu.edu
 * Space class that contains the SpaceEnum
 * Space contains the coordinate positions of each SpaceEnum character
 */
public class Space {
	private int row;
	private int col;
	private SpaceEnum s;
	
	/**
	 * @param s
	 * @param row
	 * @param col
	 * Non-default constructor to make a Space object
	 * Containing the SpaceEnum (what type of space is it?)
	 * And row and column where it gives the position of the space
	 */
	Space(SpaceEnum s, int row, int col) {
		this.s = s;
		this.row = row;
		this.col = col;
	}
	
	/**
	 * Default constructor that calls the non-default constructor
	 * With default values
	 * Never actually used in the code, but good to have
	 */
	Space() {
		this(null, 0, 0);
	}

	/**
	 * @return row
	 * Getter that returns the row position of the Space 
	 */
	public int getRow() {
		return row;
	}

	/**
	 * @return col
	 * Getter that returns the column position of the Space
	 */
	public int getCol() {
		return col;
	}

	/**
	 * @return SpaceEnum s
	 * Getter that returns the SpaceEnum
	 */
	public SpaceEnum getS() {
		return s;
	}

	/**
	 * @param s
	 * Setter that changes the SpaceEnum type
	 */
	public void setS(SpaceEnum s) {
		this.s = s;
	}
}
