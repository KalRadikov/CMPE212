import static org.junit.Assert.*;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;

import org.junit.Test;

public class Operations {

	@Test
	public void testMatrixIntInt() {
		Matrix m = new Matrix(2, 3);
		if (m.getM() != 2 || m.getN() != 3)
			fail("Wrong size");
	}

	@Test
	public void testMatrixString() {
		Matrix m = new Matrix("example.mat");
		m.print("example.out");
		Matrix m2 = new Matrix("example.out");
		if (!m.equals(m2))
			fail("Reading from file failed");
		if (m.getM() != 2 || m.getN() != 3)
			fail("Reading from file failed");

	}

	@Test
	public void testToString() {
		Matrix m = new Matrix("example.mat");
		PrintWriter pw = null;
		try {
			pw = new PrintWriter(new File("example.out2"));
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		pw.print(m);
		pw.close();
		Matrix m2 = new Matrix("example.out2");
		if (!m.equals(m2))
			fail("toString failed");
	}

	@Test
	public void testIdentity() {
		Matrix m = Matrix.identity(5);
		for (int i = 0; i < m.getM(); i++) {
			for (int j = 0; j < m.getN(); j++) {
				if (i == j) {
					if (m.get(i, j) != 1)
						fail("Identity matrix failed");

				} else {
					if (m.get(i, j) != 0)
						fail("Identity matrix failed");
				}
			}
		}
	}

	@Test
	public void testAdd() {
		Matrix a = new Matrix("A.mat");
		Matrix b = new Matrix("A.mat");
		Matrix c = new Matrix("A+A.mat");

		if (!a.add(b).equals(c))
			fail("Addition failed");

	}

	@Test
	public void testSubtract() {
		Matrix a = new Matrix("A.mat");
		Matrix b = new Matrix("A.mat");
		Matrix c = new Matrix("A-A.mat");

		if (!a.subtract(b).equals(c))
			fail("Sub failed");

	}

	@Test
	public void testMultiplyMatrix() {
		Matrix a = new Matrix("A.mat");
		Matrix b = new Matrix("B.mat");
		Matrix c = new Matrix("A*B.mat");

		if (!a.multiply(b).equals(c))
			fail("Multiplication failed");

	}

	@Test
	public void testMultiplyDouble() {
		Matrix a = new Matrix("A.mat");
		Matrix c = new Matrix("xA.mat");

		if (!a.multiply(4).equals(c))
			fail("Multiplication by constant failed");

	}

	@Test
	public void testDivide() {
		Matrix a = new Matrix("A.mat");
		Matrix b = new Matrix("B.mat");
		Matrix c = new Matrix("AdB.mat");

		if (!a.divide(b).equals(c))
			fail("Division failed");
	}

	@Test
	public void testDeterminant() {
		Matrix m = new Matrix("B.mat");
		if (m.determinant() != -92)
			fail("Determinant failed");

	}

	@Test
	public void testInverse() {
		Matrix a = new Matrix("B.mat");
		Matrix c = new Matrix("det-1.mat");

		if (!a.inverse().equals(c))
			fail("Inverse failed");
	}

	@Test
	public void testIsSquare() {
		Matrix m = new Matrix(2, 2);
		if (!m.isSquare())
			fail("isSquare failed");
		Matrix m2 = new Matrix(3, 2);
		if (m2.isSquare())
			fail("isSquare failed");

	}

	@Test
	public void testTranspose() {
		Matrix a = new Matrix("A.mat");
		Matrix c = new Matrix("At.mat");

		if (!a.transpose().equals(c))
			fail("Transpose failed");
	}

}
