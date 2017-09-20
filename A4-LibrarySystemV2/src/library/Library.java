package library;

import java.util.HashMap;

import CustomeExceptions.DateReturnedBeforeDateRented;
import CustomeExceptions.DuplicateCustomerID;
import CustomeExceptions.DuplicateItemID;
import CustomeExceptions.DuplicateTransactionID;

public class Library {
	
	public HashMap<Integer, Item> items;
	public HashMap<Integer, Customer> customers;
	public HashMap<Integer, Rental> transactions;

	// Regular Constructor
	public Library() {

		items = new HashMap<Integer, Item>();
		customers = new HashMap<Integer, Customer>();
		transactions = new HashMap<Integer, Rental>();
	}

	// copy constructor
	public Library(Library l) {
		transactions = new HashMap<Integer, Rental>();
		items = new HashMap<Integer, Item>();
		customers = new HashMap<Integer, Customer>();
		this.transactions = l.transactions;
		this.customers = l.customers;
		this.items = l.items;
	}
	
	//---------------------------------- Key Methods --------------------------------------//
	public void addItem(Item i) throws DuplicateItemID {
		if (i == null)
			return;

		if (items.containsKey(i.getId())) {
			throw new DuplicateItemID();
		} else {
			items.put(i.getId(), i);
		}
	}

	public void addTransactions(Rental r) throws DuplicateTransactionID, DateReturnedBeforeDateRented {
		if (r == null)
			return;

		if (transactions.containsKey(r.getTID())) {
			throw new DuplicateTransactionID();
		}
		if (r.getRentalDate().after(r.getReturnActual())) {
			throw new DateReturnedBeforeDateRented();
		}
		transactions.put(r.getTID(), r);
	}

	public void addCustomer(Customer c) throws DuplicateCustomerID {
		if (c == null)
			return;
		if (customers.containsKey(c.getID())) {
			throw new DuplicateCustomerID();
		}
		customers.put(c.getID(), c);
	}

	public double getTotalLateFees() {
		int sum = 0;
		for (int iD : transactions.keySet()) {

			sum += transactions.get(iD).getItem().getLateFees(transactions.get(iD).getLateDays());
		}

		return sum;

	}

	public double getTotalRentalCosts() {
		int sum = 0;
		for (int iD : transactions.keySet()) {
			if (transactions.get(iD).getItem() instanceof Device) {
				Device d = (Device) transactions.get(iD).getItem();
				sum += d.getRentalCost();
			}

		}
		return sum;
	}

	//------------------------------ Override Methods ----------------------------------//
	@Override
	public String toString() {
		String s = "";
		for (int iD : transactions.keySet()) {
			s += "\n" + transactions.get(iD) + "\n------\n";
		}
		s += "Total Late Fees= " + getTotalLateFees();
		s += "\nTotal Rental Costs= " + getTotalRentalCosts();
		return s;
	}

	@Override
	protected Library clone() {

		return new Library(this);
	}

}
