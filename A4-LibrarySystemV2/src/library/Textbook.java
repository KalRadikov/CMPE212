package library;


public class Textbook extends Book {

	public Textbook(Textbook b) {
		super(b);
	}

	public Textbook(String name, String authors, String publisher, int year, int ID) {
		super(name, authors, publisher, year, ID);
	}
	
	public Textbook(String name, String authors, String publisher, int year) {
		super(name, authors, publisher, year);
	}
	
	@Override
	public double getLateFees(int days) {
		return 1 * days;
	}
	@Override
	public String toString() {
		return "Textbook --> " + super.toString();
	}
	@Override
	protected Textbook clone()  {
		return new Textbook(this);
	}
}
