package CustomeExceptions;

public class DuplicateTransactionID extends Exception{

	public DuplicateTransactionID() {
		super("Duplicate transaction exists with this ID");
	}

}
