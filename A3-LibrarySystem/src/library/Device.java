package library;

public class Device extends Item {
	private double rentalCost;

	public double getRentalCost() {
		return rentalCost;
	}

	public void setRentalCost(double rentalCost) {
		this.rentalCost = rentalCost;
	}

	public Device(Device d) {
		super((Item) d);
		rentalCost = d.rentalCost;
	}

	public Device(String name, double rental) {
		super(name);
		rentalCost = rental;
	}

	@Override
	public double getLateFees(int days) {
		return 2 * days + 0.1 * rentalCost;
	}

	@Override
	public String toString() {
		return "Device --> " + super.toString() + ", Cost= " + rentalCost;
	}
	@Override
	protected Device clone()  {
		return new Device(this);
	}
}
