package library;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

import CustomeExceptions.DateReturnedBeforeDateRented;
import library.Customer.Position;

public class Rental {
	private int CID;
	private int TID;
	private int rentalDays;
	private int lateDays;
	private double studentDiscount = 0.75;
	private static int maxTID = 0;
	Item item;
	Customer customer;

	private SimpleDateFormat df = new SimpleDateFormat("yyyy/MM/dd");
	private Date rentalDate;
	private Date returnActual;
	private Date returnEstimate;

	// Instantiate singleton class
	private Calendar calender = Calendar.getInstance();

	public enum rentalStatus {
		ACTIVE, LATE, CLOSED
	}

	rentalStatus status;

	// Copy Constructor
	public Rental(Rental r) {
		this.TID = r.TID;
		this.rentalDays = r.rentalDays;
		this.lateDays = r.lateDays;
		this.item = r.item;
		this.status = rentalStatus.ACTIVE;
	}

	// Regular Constructor
	public Rental(String rentalDate, String returnEstimate, String returnActual, Item item, Customer customer)
			throws DateReturnedBeforeDateRented, ParseException {
		super();
		TID = ++maxTID;
		this.rentalDays = rentalDays;
		this.lateDays = lateDays;
		this.item = item;
		this.rentalDate = df.parse(rentalDate);
		this.returnActual = df.parse(returnActual);
		this.returnEstimate = df.parse(returnEstimate);
	}

	//------------------------------Getter and Setter Methods ----------------------------------//
	public int getTID() {
		return TID;
	}

	public void setTID(int TID) {
		this.TID = TID;
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
	
	public Item getItem() {
		return item;
	}
	
	public Customer getCustomer(){
		return this.customer;
	}
	
	public void setCustomer(Customer customer){
		if (customer != null)
			this.customer = customer;
	}

	public Date getRentalDate() {
		return rentalDate;
	}

	public void setRentalDate(Date rentalDate) {
		this.rentalDate = rentalDate;
	}

	public Date getReturnActual() {
		return returnActual;
	}

	public void setReturnActual(Date returnActual) {
		this.returnActual = returnActual;
	}

	//----------------------------------- Key Methods ----------------------------------------//

	public boolean isLate() {
		if (returnActual.after(returnEstimate)) {
			this.status = rentalStatus.LATE;
			return true;
		} else {
			return false;
		}
	}

	public void itemReturned() {
		this.returnActual = new Date();
		this.status = rentalStatus.CLOSED;
	}
	
	public double getLateFees(){
		double fees = 0;
		Date date = new Date();
		fees = item.getLateFees(this.getLateDays());
		return fees;
	}
	
	public double getRentalCost(){
		if (item instanceof Device) {
			return ((Device)item).getRentalCost();
		} else {
			return -1;
		}
	}
	
	public double getTotalToBePaid(){
		
		if (this.customer.getType() == Position.STUDENT)  {
			return (this.getLateFees() + this.getRentalCost()) * studentDiscount;
		} else {  
			return this.getLateFees() + this.getRentalCost();
		}

	}
	
	//------------------------------ Override Methods ----------------------------------//
	@Override
	public String toString() {
		Date date = new Date();
		int lateDays;
		if(returnActual.getTime() - returnEstimate.getTime() > 0){
			lateDays = this.getLateDays();
		} else {
			lateDays = 0;
		}
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
