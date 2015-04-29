import java.util.*;
import java.io.*;

/**
 * @author Connie Shi cs3313@nyu.edu
 * FileInputOutput reads the txt file containing the maze characters
 * Stores it into a 2D array maze
 * Store starting position for later use
 * And contains method to write output files
 */

public class FileInputOutput{

	private File file;
	private File newFile;
	private PrintWriter output;
	private Space [][] maze;

	//Starting position information
	private int startingRow;
	private int startingCol;

	//2D array size information
	private int numberOfRows;
	private int charPerRow;

	/**
	 * @param fileName: Takes in a String and uses that to create a File object
	 * Constructor with param
	 */
	public FileInputOutput(String fileName) {
		file = new File(fileName);
	}

	/**
	 * @return startingRow as int
	 * Getter method
	 * This is the first number given at the top of every maze
	 */
	public int getStartingRow() {
		return startingRow;
	}

	/**
	 * @return 2D Space array maze
	 * Getter method
	 */
	public Space[][] getMaze() {
		return maze;
	}

	/**
	 * @return startingColumn as int
	 * This is the second number given at the top of every maze
	 * Getter method
	 */
	public int getStartingCol() {
		return startingCol;
	}

	/**
	 * @throws FileNotFoundException
	 * readFile uses the file to read the characters
	 * And store the characters into the 2D array
	 */
	public void readFile() throws FileNotFoundException{
	
		//error checking if file can be read or exists
		if (file.canRead() && file.exists() ) {
			if (verifyInputFile() == true) {
				Scanner input = new Scanner(file);
				String startingPosition = input.nextLine();

				//Scans the String to get the two ints
				Scanner scan = new Scanner(startingPosition);
				startingRow = scan.nextInt();
				startingCol = scan.nextInt();

				//set the first maze line as current line
				String currentLine = input.nextLine();

				//gets how many characters per row
				//use method to get how many rows there are
				//so we can use these numbers to create a 2D Space array
				charPerRow= currentLine.length();
				numberOfRows = findHowManyRows();
				maze = new Space [numberOfRows][charPerRow];

				//Populate the space array with the proper space objects
				for(int i = 0; i < numberOfRows; i++) {
					for (int j = 0; j < charPerRow; j++) {
						//Assign maze position to a new Space that takes in 3 arguments
						//Converts char to SpaceEnum, and feed in the row and column position
						maze[i][j] = new Space(SpaceEnum.toSpaceEnum(currentLine.charAt(j)), i, j);
					}
					if (i != numberOfRows - 1) //does not go to the next line
						currentLine = input.nextLine(); //after that row is done, go to the next line
				}	
			}
			else {
				System.out.println("Incorrect input file.");
				System.exit(0);
			}
		}
		//then the file doesn't exist or cannot be read, print error messages
		else {
			System.out.print("Please enter valid file name.");
			System.exit(0);
		}
	}

	/**
	 * @param a: ArrayList containing String objects of all 
	 * 		the space coordinates that was "walked on" in the maze
	 * @param fileWrite: the File object
	 * @throws IOException
	 */
	public void writeFile(ArrayList<String> a, String fileWrite) throws IOException{
		newFile = new File(fileWrite);

		//error checking, if file already exists or cannot be written
		if (newFile.exists() /*|| !newFile.canWrite()*/) {
			System.out.println("File already exists or cannot be written.");
			System.exit(0);
		}

		//no error, system does not exit, now make the file
		newFile.createNewFile();
		output = new PrintWriter(newFile);
		for(int i = 0; i < a.size(); i++) {
			//write every String containing coordinate positions walked
			//into the file, separate by line breaks
			output.println(a.get(i));
		}
		output.close();
	}

	/**
	 * @return int of how many rows there are in the file
	 * 		for purposes of creating the 2D array
	 * @throws FileNotFoundException
	 */
	public int findHowManyRows() throws FileNotFoundException {
		Scanner input = new Scanner(file);
		int countRows = 0;
		while (input.hasNextLine()) {
			countRows++;
			input.nextLine(); //go to the next line
		}
		return countRows-1; //minus one to account for the first row containing integers
	}
	
	/**
	 * @return true if file if correct or false if file is incorrect
	 * @throws FileNotFoundException
	 * Checks if maze is 3x3, if characters are 'x', 'o', or ' '
	 * And if there are same number of columns in each row
	 */
	public boolean verifyInputFile() throws FileNotFoundException {
		Scanner sc = new Scanner(file);
		boolean correctFile = true;
		
		sc.nextLine(); 
		//the guaranteed 2 int start position given, get rid of that

		//overwrite nextLine with current
		String current = sc.nextLine();

		//find how many rows in the maze
		numberOfRows = findHowManyRows();
		
		//set characters in the first row to charPerRow 
		charPerRow = current.length();
		
		//check the the maze is at least 3x3
		if (charPerRow < 3 || numberOfRows < 3){
			correctFile = false;
			return correctFile;
			
		}
		
		int iterate = 0;
		
		while (iterate < numberOfRows-1){ 
						
			//checks that there are the same number of columns in each row
			for (int i = 0; i < numberOfRows; i++ ) {
				if (current.length() != charPerRow) {
					correctFile = false;
					return correctFile;
				}
				//check each character in the lines to make sure they are the valid
				//x, o and space
				for (int j = 0; j < charPerRow; j++ ) {
					if (current.charAt(j) != 'x'
							&& current.charAt(j) != ' '
							&& current.charAt(j) != 'o') {
						correctFile = false;
						return correctFile;
					}
				}
			}

			//progress current to the next line for checking
			current = sc.nextLine();
			iterate++;
		}
		return correctFile;
	}

	/**
	 * Prints out the maze onto console
	 */
	public void print() {		
		for(int i = 0; i < numberOfRows; i++) {
			for (int j = 0; j < charPerRow; j++) {
				System.out.print(maze[i][j].getS());
			}
			System.out.println();
		}
	}
}
