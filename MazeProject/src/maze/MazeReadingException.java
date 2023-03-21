package maze;

/**
 * Exception class that manages with errors concerning the text file used to initialize the maze :
 * number of lines or columns incorrect, 
 * use of inexistent labels, 
 * missing departure or arrival box,
 * too many departure or arrival boxes,
 * etc.
 * @author Romain Darous
 */
public class MazeReadingException extends Exception {

	private final String fileName; // the path of the file concerned.
	private final int lineNumber; // the line where the exception occurs.
	private final int columnNumber; // the column where the exception occurs
	private final String errorMessage; // the message displayed by the exception.
	
	/**
	 * Constructor of the MazeReadingException class.
	 * @param fileName the path of the file concerned.
	 * @param lineNumber the line where the exception occurs.
	 * @param columnNumber the column where the exception occurs.
	 * @param errorMessage the message displayed by the exception.
	 */
	public MazeReadingException (String fileName, int lineNumber, int columnNumber, String errorMessage) {
		super(errorMessage);
		this.fileName = fileName;
		this.lineNumber = lineNumber;
		this.columnNumber = columnNumber;
		this.errorMessage = errorMessage + fileName;
	}
	
	/**
	 * Constructor of the MazeReadingException class.
	 * @param fileName the path of the file concerned.
	 * @param errorMessage the message displayed by the exception.
	 */
	public MazeReadingException (String fileName, String errorMessage) {
		super(errorMessage);
		this.fileName = fileName;
		this.lineNumber = -1;
		this.columnNumber = -1;
		this.errorMessage = errorMessage + fileName;
	}
	
	/**
	 * Constructor of the MazeReadingException class.
	 * @param fileName the path of the file concerned.
	 * @param lineNumber the line where the exception occurs.
	 * @param errorMessage the message displayed by the exception.
	 */
	public MazeReadingException (String fileName, int lineNumber, String errorMessage) {
		super(errorMessage);
		this.fileName = fileName;
		this.lineNumber = lineNumber;
		this.columnNumber = -1;
		this.errorMessage = errorMessage + fileName;
	}
	
	/**
	 * Gives the file name where the exception occurs.
	 * @return the name of the file.
	 */
	public final String getFileName() {
		return this.fileName;
	}
	
	/**
	 * Gives the number of the line where the exception occurs.
	 * @return the number of the line.
	 */
	public final int getLineNumber() {
		return this.lineNumber;
	}
	
	/**
	 * Gives the number of the column where the exception occurs.
	 * @return the number of the column.
	 */
	public final int getColumnNumber() {
		return columnNumber;
	}
	
	/**
	 * Gives the error message that must be displayed.
	 * @return the error message.
	 */
	public final String getErrorMessage() {
		return this.errorMessage;
	}
}
