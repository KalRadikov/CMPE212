package library;

public class Magazine extends Book {

	public Magazine(Magazine b) {
		super(b);
	}

	public Magazine(String name, String authors, String publisher, int year) {
		super(name, authors, publisher, year);
	}

	@Override
	public double getLateFees(int days) {
		return 0.75 * days;
	}
	@Override
	public String toString() {
		return "Magazine --> " + super.toString();
	}
}
