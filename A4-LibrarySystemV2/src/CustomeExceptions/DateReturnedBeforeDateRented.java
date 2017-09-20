package CustomeExceptions;

public class DateReturnedBeforeDateRented extends Exception{

	public DateReturnedBeforeDateRented() {
		super("Entered date is before the date rented");
	}

}
