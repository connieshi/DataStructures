mport java.io.IOException;

/**
 * @author Connie Shi cs3313@nyu.edu
 * Game contains the main program where the algorithm is run
 * Takes in three arguments: input file name, output file name for stack
 * And output file name for queue
 * 
 */
public class Game {
	public static void main(String[]args) throws IOException {
		//three arguments, input, outputStack, and outputQueue
		if (args.length == 3) {
			String inputFile = args[0];
			String outputFileStack = args[1];
			String outputFileQueue = args[2];

			//Create and fun stackImplementation and queueImplementation object containing algorithm method
			//And then write to outputFile
			StackImplementation si = new StackImplementation();
			QueueImplementation qi = new QueueImplementation();
			qi.run(inputFile);
			si.run(inputFile);
			qi.write(outputFileQueue);	
			si.write(outputFileStack);		
		}
		else {
			System.out.print("Invalid arguments. Enter inputFileName outputFileStack outputFileQueue");
		}
	}
}
