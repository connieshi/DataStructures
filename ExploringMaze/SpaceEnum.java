
/**
 * @author Connie Shi cs3313@nyu.edu
 * This is the enum class called SpaceEnum that contains the values
 * That mean something within the maze
 *
 */

public enum SpaceEnum {
	
	INITIAL_POSITION ("*"), 
	WALL ("x"), 
	CORRIDOR (" "), 
	VISITED_CORRIDOR ("."), 
	WAY_OUT ("o"), 
	DISCOVERED_EXIT ("E");

	private String characterRepresentation;

	/**
	 * @param characterRepresentation
	 * Constructor that takes in String of character
	 */
	SpaceEnum(String characterRepresentation) {
		this.characterRepresentation = characterRepresentation;
	}
	
	/** 
	 * Returns string representing the current object. 
	 * @return String
	 */
	public String toString(){
		return characterRepresentation;
	}
	
	/**
	 * @param c
	 * @return SpaceEnum
	 * Takes in a char and returns the SpaceEnum representation
	 */
	public static SpaceEnum toSpaceEnum(char c) {
		switch(c) {
		case '*': return INITIAL_POSITION;
		case 'x': return WALL;
		case ' ': return CORRIDOR;
		case '.': return VISITED_CORRIDOR;
		case 'o': return WAY_OUT;
		case 'E': return DISCOVERED_EXIT;
		default: return null; //if file contains invalid character, return null
		}
	}
}

 