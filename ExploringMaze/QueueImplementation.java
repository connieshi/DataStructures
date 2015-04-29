import java.io.IOException;
import java.util.ArrayList;

/**
 * @author Connie Shi cs3313@nyu.edu
 * This class contains the algorithm and methods for running the actual maze
 * Passes everything through main, prints to console 
 * Queue
 */
public class QueueImplementation {

	private Space[][] maze;
	private Space current;
	private queueSets s;
	private Space pop;
	private ArrayList<String> a;
	private FileInputOutput f;
	private static final int SCREEN_HEIGHT = 30;

	/**
	 * @param fileName
	 * @throws IOException
	 * Running of the program, from reading the file, to using the algorithm
	 * To writing the output file of the places walked
	 */
	public void run(String fileName) throws IOException{
		f = new FileInputOutput(fileName);
		f.readFile();
		maze = f.getMaze();
		s = new queueSets();
		a = new ArrayList<String>();
		boolean stillGoing = true;

		//verifying correctness of first position, it must start with a corridor
		if (maze[f.getStartingRow()][f.getStartingCol()].getS() == SpaceEnum.CORRIDOR) {
			//change the starting space (current) to initial position (denoted by *)
			current = new Space(SpaceEnum.INITIAL_POSITION, f.getStartingRow(), f.getStartingCol());
			setSpace(current, SpaceEnum.INITIAL_POSITION);
			s.add(current);
			pop = s.remove();
			a.add(printLocation(pop));

			while (stillGoing) {
				System.out.println(toString());
				try { 
					Thread.sleep(100); 
				} catch (InterruptedException e) { }
				clearScreen(); //print out blank lines to pseudo-"clear screen"
				
				if (pop.getS() == SpaceEnum.WAY_OUT) {
					setSpace(pop, SpaceEnum.DISCOVERED_EXIT);
					if (checkPrinting(printLocation(pop)) == false) {
						a.add(printLocation(pop));
						System.out.print(toString());
						return;
					}
				}

				//only if pop is not my initial position ("*") then change the corridor to visited corridor
				if(pop.getS() != SpaceEnum.INITIAL_POSITION) {
					setSpace(pop, SpaceEnum.VISITED_CORRIDOR); 
				}

				//add everything traversed into my ArrayList of Spaces visited before finding exit
				if (checkPrinting(printLocation(pop)) == false) {
					a.add(printLocation(pop));
				}

				//push all neighbors that are corridors or WAY_OUTs
				neighbors(current); 

				if (s.peek() != null && s.getSize() != 0) {
					pop = s.remove(); //set pop to last neighbor, remove last neighbor
				} else {
					stillGoing = false;
					System.out.print("No exit");
					return;
				}

				//if the spot has already been visited, just pop it off
				if (s.getSize() != 0 && s.peek() != null && s.peek().getS() == SpaceEnum.VISITED_CORRIDOR) {
					s.remove();
				}
				
				current = pop;
			}
		} else {
			System.out.print("Invalid starting position");
			return;
		}
	}

	/**
	 * @param fileName
	 * @throws IOException
	 * Takes in the name of the file and then writes the output file for QueueImplementation
	 */
	public void write(String fileName) throws IOException {
		FileInputOutput f = new FileInputOutput(fileName);
		f.writeFile(a, fileName); //a is my ArrayList
	}

	/**
	 * @param coordinates
	 * @return
	 */
	public boolean checkPrinting(String coordinates) {
		if (a.get(a.size()-1).equals(coordinates)) {
			return true;
		} else {
			return false;
		}
	}

	/**
	 * @param current
	 * @return String of the coordinates of the positions traversed
	 */
	public String printLocation(Space current) {
		return current.getRow() + " " + current.getCol();
	}

	/**
	 * @param current
	 * @return Space 
	 * Getter method
	 */
	public Space getSpace(Space current) {
		return maze[current.getRow()][current.getCol()];
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 * Prints out the actual maze
	 */
	public String toString() {
		String mazePrint = "";
		for(int i = 0; i < maze.length; i++) {
			for(int j = 0; j < maze[i].length; j++) {
				mazePrint += maze[i][j].getS().toString();
			}
			mazePrint += "\n";
		}
		return mazePrint;
	}

	/**
	 * @param currentSpace
	 * @param type
	 * Changes the SpaceEnum in Space from one type to another type
	 */
	private void setSpace(Space currentSpace, SpaceEnum type) {
		maze[currentSpace.getRow()][currentSpace.getCol()].setS(type);
	}

	/**
	 * @param current
	 * Takes in the current space that we're at
	 * And then pushes all of the valid neighbors onto the queue according to the order
	 * That is stated in the assignment
	 * Valid neighbors = CORRIDOR or WAY_OUT
	 */
	private void neighbors(Space current) {
		if (maze[current.getRow()-1][current.getCol()].getS() == SpaceEnum.CORRIDOR
			|| maze[current.getRow()-1][current.getCol()].getS() == SpaceEnum.WAY_OUT) {
			s.add(maze[current.getRow()-1][current.getCol()]);
		}
		if (maze[current.getRow()][current.getCol()+1].getS() == SpaceEnum.CORRIDOR
			|| maze[current.getRow()][current.getCol()+1].getS() == SpaceEnum.WAY_OUT) {
			s.add(maze[current.getRow()][current.getCol()+1]);
		}
		if (maze[current.getRow()+1][current.getCol()].getS() == SpaceEnum.CORRIDOR
			|| maze[current.getRow()+1][current.getCol()].getS() == SpaceEnum.WAY_OUT) {
			s.add(maze[current.getRow()+1][current.getCol()]);
		}
		if (maze[current.getRow()][current.getCol()-1].getS() == SpaceEnum.CORRIDOR
			|| maze[current.getRow()][current.getCol()-1].getS() == SpaceEnum.WAY_OUT) {
			s.add(maze[current.getRow()][current.getCol()-1]);
		}
	}

	/**
	 * Pseudo-"Clear Screen" by printing out blank lines
	 * This is Joanna Klukowska's code, used in GameOfLife class example
	 */
	private void clearScreen() {
		for (int i = 0; i < SCREEN_HEIGHT; i++) {
			System.out.println(" ");
		}
	}
}
