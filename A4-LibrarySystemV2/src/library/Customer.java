package library;

public class Customer {
	
	public static enum Position {STUDENT, EMPLOYEE}
	
	Position type;
	private int id;
	private String name;
	private String dept;
	
	public Customer(String name, String dept, Position type, int ID) {
		this.name = name;
		this.dept = dept;
		this.id = ID;
		this.type = type;
	}
	
	//type = Position.STUDENT;
	
	public Position getType(){
		return type;
	}
	
	public int getID(){
		return id;
	}

	public void setID(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDept() {
		return dept;
	}

	public void setDept(String dept) {
		this.dept = dept;
	}



}
