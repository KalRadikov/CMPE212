
public class MatrixTest {

	public static void main(String[] args) {
		Matrix m = new Matrix("example.mat");
		m.print("out.txt");
		System.out.println(m.determinant());
		System.out.println(m.inverse());
		Matrix m2 = Matrix.identity(5);
		m2.print("identity5.txt");
		
	}

}
