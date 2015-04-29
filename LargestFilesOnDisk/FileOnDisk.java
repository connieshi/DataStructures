import java.text.DecimalFormat;

/**
 * @author Connie Shi
 * FileOnDisk class creates a FileOnDisk object given the name of the file
 * As well as the size of the file
 *
 */
public class FileOnDisk implements Comparable<FileOnDisk> {
	private String name;
	private double size;
	
	/**
	 * @param name of file
	 * @param size of file
	 */
	public FileOnDisk (String name, long size) {
		this.name = name;
		this.size = size;
	}
	
	/**
	 * @return String of name
	 */
	public String getName() {
		return name;
	}

	/**
	 * @return size of file
	 */
	public double getSize() {
		return size;
	}
	
	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	public String toString() {
		DecimalFormat df = new DecimalFormat("#.##");
		String s = "";
		if (size >= 0 && size < 1024) {
			s+=df.format(size)+" bytes";
		}
		else if (size >= 1024 && size < 1024*1024) {
			size = Math.round(size*10000)/10000;
			s+=df.format(size/1024.0) + " KB";
		}
		else if (size >= 1024*1024 && size < 1024*1024*1024)
			s+=df.format(size/(1024*1024.0)) + " MB";
		else
			s+=df.format(size/(1024*1024*1024.0)) + " GB";
		return s+="\t\t" + name;
	}
	
	/* (non-Javadoc)
	 * @see java.lang.Comparable#compareTo(java.lang.Object)
	 * To compare the this object with the object passed as a param using its size
	 */
	@Override
	public int compareTo(FileOnDisk o) {
		if (this.size < o.size)
			return 1;
		if (this.size > o.size)
			return -1;
		else
			return 0;
	}
}
