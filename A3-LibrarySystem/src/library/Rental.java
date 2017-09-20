package library;

public class Rental {
	private int CID;
	private int rentalDays;
	private int lateDays;
	Item item;

	public Item getItem() {
		return item;
	}

	public Rental(Rental r) {
		this.CID = r.CID;
		this.rentalDays = r.rentalDays;
		this.lateDays = r.lateDays;
		this.item = r.item;
	}

	public Rental(int cID, int rentalDays, int lateDays, Item item) {
		super();
		CID = cID;
		this.rentalDays = rentalDays;
		this.lateDays = lateDays;
		this.item = item;
	}

	public int getCID() {
		return CID;
	}

	public void setCID(int cID) {
		CID = cID;
	}

	public int getRentalDays() {
		return rentalDays;
	}

	public void setRentalDays(int rentalDays) {
		this.rentalDays = rentalDays;
	}

	public int getLateDays() {
		return lateDays;
	}

	public void setLateDays(int lateDays) {
		this.lateDays = lateDays;
	}

	public void setItem(Item item) {
		this.item = item;
	}

	@Override
	public String toString() {
		String s = "Customer ID: " + CID;
		s += "\nItem: " + getItem() + "\n";
		s += "Rental days=" + rentalDays + ", late Days=" + lateDays;
		return s;
	}

	@Override
	protected Rental clone() {
		return new Rental(this);
	}

	@Override
	public boolean equals(Object obj) {
		if (obj == null)
			return false;

		if (!(obj.getClass() == getClass()))
			return false;
		Rental r = (Rental) obj;
		return r.item.equals(this.item) && CID == r.CID;
	}
}
