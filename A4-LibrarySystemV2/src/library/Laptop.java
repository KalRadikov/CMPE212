package library;

public class Laptop extends Device {

	public Laptop(Laptop d) {
		super((Laptop) d);
	}

	public Laptop(String name, double rental, int ID) {
		super(name, rental, ID);
	}
	
	public Laptop(String name, double rental) {
		super(name, rental);
	}


	@Override
	public double getLateFees(int days) {
		return 5 * days + 0.2 * getRentalCost();
	}
	@Override
	public String toString() {
		return "Laptop --> " + super.toString();
	}

}
