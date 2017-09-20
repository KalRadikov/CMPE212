package library;

import java.text.ParseException;

import CustomeExceptions.*;
import library.Customer.Position;

public class QlibraryTest {

	public static void main(String[] args) {
		Library l = new Library();

		//------------------------------ Add Customers to Library ----------------------------------//
		Customer c1 = new Customer("Daniel Smith", "Artsci", Position.STUDENT, 12345);
		Customer c2 = new Customer("John Doe", "ECE", Position.EMPLOYEE, 11114);

		try {
			
			l.addCustomer(c1);
			l.addCustomer(c2);
			
		} catch (DuplicateCustomerID e) {
			System.out.println(e);
		}
		
		//--------------------------------- Add Items to Library -------------------------------------//
		Textbook book; book = new Textbook("Intro to Econ", "Dave", "Publishing2017", 1997, 54321);
		Laptop laptop = new Laptop("SurfaceBook", 1.5, 54433);
		
		try {
			
			l.addItem(book);
			l.addItem(laptop);
			
		} catch (DuplicateItemID e){
			System.out.println(e);
		}
		
		//------------------------------ Add Rental Transactions to Library ----------------------------------//
		Rental rental1;
		Rental rental2;
		
		try {
			rental1 = new Rental("2017/03/28","2017/03/30","2017/04/30", book, c1);
			rental2 = new Rental("2017/02/28","2017/03/30","2017/03/01", laptop, c2);
			
			l.addTransactions(rental1);
			l.addTransactions(rental2);
			
			
		} catch (DateReturnedBeforeDateRented e1) {
			e1.printStackTrace();
		} catch (ParseException e1) {
			e1.printStackTrace();
		} catch (DuplicateTransactionID e){
			System.out.println(e);
		}

		//------------------------------ Test if the Library works ----------------------------------//
		for(int i: l.transactions.keySet()){
			if (l.transactions.get(i).isLate())
				System.out.println(l.transactions.get(i).getLateFees());
		}
		

		System.out.println(l);
		System.out.println("-----------------New copy:---------------");
		System.out.println(l.clone());

	}

}
