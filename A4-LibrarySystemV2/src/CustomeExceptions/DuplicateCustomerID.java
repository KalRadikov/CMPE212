package CustomeExceptions;

public class DuplicateCustomerID extends Exception {

	public DuplicateCustomerID() {
		super("Duplicate Customer exists with this ID");
	}
}
