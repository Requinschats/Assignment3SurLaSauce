public class InvalidFileException extends Exception {
	
	public InvalidFileException() {
		super("Error: Input file named XXX cannot be found.");
	}
	
	public InvalidFileException(String message) {
		super(message);
	}
	
}
