package library;

import java.util.ArrayList;

public class Library {
	ArrayList<Rental> transactions;

	public Library() {
		transactions = new ArrayList<Rental>();
	}

	public Library(Library l) {
		transactions = new ArrayList<Rental>();
		for (Rental r : l.transactions) {
			transactions.add(r.clone());
		}
	}

	void addTransactions(Rental r) {
		if (r == null)
			return;
		transactions.add(r);
	}

	double getTotalLateFees() {
		int sum = 0;
		for (Rental r : transactions) {

			sum += r.getItem().getLateFees(r.getLateDays());
		}

		return sum;

	}

	double getTotalRentalCosts() {
		int sum = 0;
		for (Rental r : transactions) {
			if (r.getItem() instanceof Device) {
				Device d = (Device) r.getItem();
				sum += d.getRentalCost();
			}

		}
		return sum;
	}

	@Override
	public String toString() {
		String s = "";
		for (Rental r : transactions) {
			s += "\n" + r + "\n------\n";
		}
		s += "Total Late Fees= " + getTotalLateFees();
		s += "\nTotal Rental Costs= " + getTotalRentalCosts();
		return s;
	}

	@Override
	protected Library clone() {

		return new Library(this);
	}

	public Library cloneAll() {
		Library l = new Library(this);
		for (Rental r : transactions) {
			Rental r2 = r.clone();
			r2.item = r2.item.clone();
			l.addTransactions(r2);
		}
		return l;
	}
}
