package library;

public class Book extends Item {
	private String authors;
	private String publisher;
	private int year;

	public Book(Book b) {
		super(b);
		authors = b.authors;
		publisher = b.publisher;
		year = b.year;
	}

	public Book(String name, String authors, String publisher, int year) {
		super(name);
		this.authors = authors;
		this.publisher = publisher;
		this.year = year;
	}
	
	public Book(String name, String authors, String publisher, int year, int ID) {
		super(name, ID);
		this.authors = authors;
		this.publisher = publisher;
		this.year = year;
	}

	public String getAuthors() {
		return authors;
	}

	public void setAuthors(String authors) {
		this.authors = authors;
	}

	public String getPublisher() {
		return publisher;
	}

	public void setPublisher(String publisher) {
		this.publisher = publisher;
	}

	public int getYear() {
		return year;
	}

	public void setYear(int year) {
		this.year = year;
	}

	@Override
	public double getLateFees(int days) {
		return 0.5 * days;
	}
	@Override
	public String toString() {
		return "Book --> " + super.toString()+", authors: "+authors+", year:"+year+", Publisher: "+publisher;
	}
	@Override
	protected Book clone()  {
		return new Book(this);
	}
}
