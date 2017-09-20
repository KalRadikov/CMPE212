package CustomeExceptions;

public class CustomerFileReadingException extends Exception {

	public CustomerFileReadingException() {
		super("Cannot read the customer file");
		}

}
