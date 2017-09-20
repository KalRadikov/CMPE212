package library;

public class Adaptor extends Device {

	public Adaptor(Adaptor d) {
		super((Adaptor) d);
	}

	public Adaptor(String name, double rental) {
		super(name, rental);
	}

	@Override
	public double getLateFees(int days) {
		return 2.5 * days + 0.15 * getRentalCost();
	}
	@Override
	public String toString() {
		return "Adaptor --> " + super.toString();
	}
	@Override
	protected Adaptor clone()  {
		return new Adaptor(this);
	}
}
