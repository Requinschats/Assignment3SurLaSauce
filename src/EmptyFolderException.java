public class EmptyFolderException extends Exception {

    public EmptyFolderException() {
        super("Error: Input file named XXX cannot be found.");
    }

    public EmptyFolderException(String message) {
        super(message);
    }
}
