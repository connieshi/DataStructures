import java.io.File;
import java.io.IOException;
import java.text.DecimalFormat;
import java.util.ArrayList;

/**
 * @author Connie Shi
 * Contains main program to run the algorithm for finding the largest
 * n files, given a starting directory absolute path
 * 
 */
public class DirectorySize {
	
	//Stores the String name of files in GenericSortedLinkedList of type FileOnDisk
	static GenericSortedLinkedList<FileOnDisk> listOfFiles = new GenericSortedLinkedList<FileOnDisk>();
	
	//Stores the String canonical path names of all visited directories 
	//To prevent infinite recursion
	static ArrayList<String> visitedDir = new ArrayList<String>();;
	
	//totalSize of all the files and directories within the starting directory, inclusive
	static double totalSize = 0;
	
	//File object of the first directory passed through
	static File fileDirectory;

	/**
	 * @param args
	 * @throws IOException
	 */
	public static void main(String[]args) throws IOException {
		
		//error checking for invalid input
		if (args.length != 2) {
			System.out.println("Enter absolute directory path followed by the number of print outs.");
			System.exit(0);
		}
		boolean error = false;
		
		String directory = args[0];
		fileDirectory = new File(directory);
		String numOfOutputs = args[1];
		
		//check that a number was entered as second arg
		for (int i = 0; i < args[1].length(); i++) {
			if (!Character.isDigit(args[1].charAt(i))) {
				error = true;
			}
		}
		//if invalid input for first args
		if (!fileDirectory.canRead() || !fileDirectory.exists()
				|| (!fileDirectory.isDirectory() && !fileDirectory.isFile())) {
			error = true;
		}
		//quit if there is an eror
		if (error == true) {
			System.out.println("Invalid entry");
			System.exit(0);
		}
		int n = Integer.valueOf(numOfOutputs);
		
		exploreDir(fileDirectory);
		System.out.println("Total Space used:\t");
		sizePrint(totalSize); 
		System.out.println();
		System.out.println("Largest "+ n + " files:");
		System.out.print(listOfFiles.toStringMaxNum(n));
	}

	/**
	 * @param dirOrFile
	 * Recursion for visiting directories and files
	 * @throws IOException
	 */
	private static void exploreDir (File dirOrFile) throws IOException {
		if (dirOrFile.isDirectory()) {
			//if not a shortcut within the directory (prevents infinite recursion)
			if (!alreadyVisited(dirOrFile.getCanonicalPath())) {
				totalSize += dirOrFile.length();
				visitedDir.add(dirOrFile.getCanonicalPath());
				File[] potentialFiles = dirOrFile.listFiles();
				//loop to use recursion to explore all files and directories
				for (int i = 0; i < potentialFiles.length; i++)
					exploreDir(potentialFiles[i]);
			}
		}
		else {
			totalSize += dirOrFile.length();
			FileOnDisk file = new FileOnDisk(dirOrFile.toString(), dirOrFile.length());
			listOfFiles.insert(file);
		}
	} 

	/**
	 * @param totalSize
	 * Given the total size, print it out with the current units
	 * @return
	 */
	private static void sizePrint(double totalSize) {
		DecimalFormat df = new DecimalFormat();
		df.setMaximumFractionDigits(2);
		if (totalSize >= 0 && totalSize < 1024)
			System.out.println(df.format(totalSize) + " bytes");
		if (totalSize >= 1024 && totalSize < 1024*1024)
			System.out.println(df.format(totalSize/(double)1024.0) + " KB");
		if (totalSize >= 1024*1024 && totalSize < 1024*1024*1024)
			System.out.println(df.format(totalSize/(double)(1024.0*1024)) + " MB");
		else
			System.out.println(df.format(totalSize/(double)(1024*1024*1024)) + " GB");
	}
	
	/**
	 * @param dir
	 * Checks if the canonical path of the directory is already in the arraylist
	 * It is there if already visited
	 * @return
	 */
	private static boolean alreadyVisited(String dir) {
		boolean visited = false;
		for (int i = 0; i < visitedDir.size(); i++) {
			if (dir == visitedDir.get(i))
				visited = true;
		}
		return visited;
	}

}
